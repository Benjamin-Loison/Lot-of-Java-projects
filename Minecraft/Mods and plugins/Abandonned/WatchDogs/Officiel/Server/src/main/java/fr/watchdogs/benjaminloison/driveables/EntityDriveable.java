package fr.watchdogs.benjaminloison.driveables;

import java.util.ArrayList;

import com.flansmod.common.vector.Vector3f;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.api.IControllable;
import fr.watchdogs.benjaminloison.api.IExplodeable;
import fr.watchdogs.benjaminloison.api.RotatedAxes;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.common.guns.BulletHit;
import fr.watchdogs.benjaminloison.common.guns.DriveableHit;
import fr.watchdogs.benjaminloison.common.guns.EntityBullet;
import fr.watchdogs.benjaminloison.common.guns.EntityShootable;
import fr.watchdogs.benjaminloison.common.guns.EnumFireMode;
import fr.watchdogs.benjaminloison.common.guns.GunType;
import fr.watchdogs.benjaminloison.common.guns.InventoryHelper;
import fr.watchdogs.benjaminloison.common.guns.ItemBullet;
import fr.watchdogs.benjaminloison.common.guns.ItemShootable;
import fr.watchdogs.benjaminloison.common.guns.ShootableType;
import fr.watchdogs.benjaminloison.packets.PacketDriveableDamage;
import fr.watchdogs.benjaminloison.packets.PacketPlaySound;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public abstract class EntityDriveable extends Entity implements IControllable, IExplodeable, IEntityAdditionalSpawnData
{
    public boolean couldNotFindFuel = false, fuelling, leftMouseHeld = false, rightMouseHeld = false, syncFromServer = true, varDoor = false, rideable = false;
    public String owners[] = {"", "", "", "", "", "", ""};
    public double serverPosX, serverPosY, serverPosZ, serverYaw, serverPitch, serverRoll;
    public DriveableData driveableData;
    public String driveableType, owner;
    public float throttle, prevRotationRoll, minigunSpeedPrimary, minigunSpeedSecondary, harvesterAngle;
    public EntityWheel[] wheels;
    public Vector3f angularVelocity = new Vector3f(0, 0, 0);
    public int serverPositionTransitionTicker, shootDelayPrimary, shootDelaySecondary, currentGunPrimary, currentGunSecondary;
    public RotatedAxes prevAxes, axes;
    public EntitySeat[] seats;
    private int foundFuel = -1;

    public EntityDriveable(World world)
    {
        super(world);
        axes = new RotatedAxes();
        prevAxes = new RotatedAxes();
        preventEntitySpawning = true;
        setSize(1, 1);
        yOffset = 6 / 16;
        ignoreFrustumCheck = true;
        renderDistanceWeight = 200D;
    }

    public EntityDriveable(World world, DriveableType t, DriveableData d)
    {
        this(world);
        driveableType = t.shortName;
        driveableData = d;
    }

    protected void initType(DriveableType type, boolean clientSide)
    {
        seats = new EntitySeat[type.numPassengers + 1];
        for(int i = 0; i < type.numPassengers + 1; i++)
        {
            seats[i] = new EntitySeat(worldObj, this, i);
            worldObj.spawnEntityInWorld(seats[i]);
        }
        wheels = new EntityWheel[type.wheelPositions.length];
        for(int i = 0; i < wheels.length; i++)
        {
            wheels[i] = new EntityWheel(worldObj, this, i);
            worldObj.spawnEntityInWorld(wheels[i]);
        }
        stepHeight = type.wheelStepHeight;
        yOffset = type.yOffset;
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag)
    {
        driveableData.writeToNBT(tag);
        tag.setString("Type", driveableType);
        tag.setFloat("RotationYaw", axes.getYaw());
        tag.setFloat("RotationPitch", axes.getPitch());
        tag.setFloat("RotationRoll", axes.getRoll());
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag)
    {
        driveableType = tag.getString("Type");
        driveableData = new DriveableData(tag);
        initType(DriveableType.getDriveable(driveableType), false);
        prevRotationYaw = tag.getFloat("RotationYaw");
        prevRotationPitch = tag.getFloat("RotationPitch");
        prevRotationRoll = tag.getFloat("RotationRoll");
        axes = new RotatedAxes(prevRotationYaw, prevRotationPitch, prevRotationRoll);
    }

    @Override
    public void writeSpawnData(ByteBuf data)
    {
        ByteBufUtils.writeUTF8String(data, driveableType);
        NBTTagCompound tag = new NBTTagCompound();
        driveableData.writeToNBT(tag);
        ByteBufUtils.writeTag(data, tag);
        data.writeFloat(axes.getYaw());
        data.writeFloat(axes.getPitch());
        data.writeFloat(axes.getRoll());
        for(EnumDriveablePart ep : EnumDriveablePart.values())
        {
            DriveablePart part = getDriveableData().parts.get(ep);
            data.writeShort((short)part.health);
            data.writeBoolean(part.onFire);
        }
    }

    @Override
    public void readSpawnData(ByteBuf data)
    {
        try
        {
            driveableType = ByteBufUtils.readUTF8String(data);
            driveableData = new DriveableData(ByteBufUtils.readTag(data));
            initType(getDriveableType(), true);
            axes.setAngles(data.readFloat(), data.readFloat(), data.readFloat());
            prevRotationYaw = axes.getYaw();
            prevRotationPitch = axes.getPitch();
            prevRotationRoll = axes.getRoll();
            for(EnumDriveablePart ep : EnumDriveablePart.values())
            {
                DriveablePart part = getDriveableData().parts.get(ep);
                part.health = data.readShort();
                part.onFire = data.readBoolean();
            }
        }
        catch(Exception e)
        {
            WatchDogs.print("Failed to retreive plane type from server.");
            super.setDead();
            e.printStackTrace();
        }
    }

    @Override
    public abstract void onMouseMoved(int deltaX, int deltaY);

    protected boolean canSit(int seat)
    {
        return getDriveableType().numPassengers >= seat && seats[seat].riddenByEntity == null;
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    protected void entityInit()
    {}

    @Override
    public AxisAlignedBB getCollisionBox(Entity entity)
    {
        return null;
    }

    @Override
    public AxisAlignedBB getBoundingBox()
    {
        return boundingBox;
    }

    @Override
    public boolean canBePushed()
    {
        return false;
    }

    @Override
    public double getMountedYOffset()
    {
        return -0.3D;
    }

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float i)
    {
        return isDead || attackPart(EnumDriveablePart.core, damagesource, i);
    }

    @Override
    public void setDead()
    {
        super.setDead();
        for(EntitySeat seat : seats)
            if(seat != null)
                seat.setDead();
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer player)
    {}

    @Override
    public boolean canBeCollidedWith()
    {
        return !isDead;
    }

    @Override
    public void applyEntityCollision(Entity entity)
    {
        if(!isPartOfThis(entity))
            super.applyEntityCollision(entity);
    }

    @Override
    public void setPositionAndRotation2(double d, double d1, double d2, float f, float f1, int i)
    {
        if(ticksExisted > 1)
            return;
        if(!(riddenByEntity instanceof EntityPlayer))
        {
            if(syncFromServer)
                serverPositionTransitionTicker = i + 5;
            else
            {
                double var10 = d - posX, var12 = d1 - posY, var14 = d2 - posZ, var16 = var10 * var10 + var12 * var12 + var14 * var14;
                if(var16 <= 1)
                    return;
                serverPositionTransitionTicker = 3;
            }
            serverPosX = d;
            serverPosY = d1;
            serverPosZ = d2;
            serverYaw = f;
            serverPitch = f1;
        }
    }

    public void setPositionRotationAndMotion(double x, double y, double z, float yaw, float pitch, float roll, double motX, double motY, double motZ, float velYaw, float velPitch, float velRoll, float throt, float steeringYaw)
    {
        setPosition(x, y, z);
        prevRotationYaw = yaw;
        prevRotationPitch = pitch;
        prevRotationRoll = roll;
        setRotation(yaw, pitch, roll);
        motionX = motX;
        motionY = motY;
        motionZ = motZ;
        angularVelocity = new Vector3f(velYaw, velPitch, velRoll);
        throttle = throt;
    }

    @Override
    public void setVelocity(double d, double d1, double d2)
    {
        motionX = d;
        motionY = d1;
        motionZ = d2;
    }

    @Override
    public boolean pressKey(int key, EntityPlayer player)
    {
        if(key == 9 && getDriveableType().modePrimary == EnumFireMode.SEMIAUTO)
        {
            shoot(false);
            return true;
        }
        else if(key == 8 && getDriveableType().modeSecondary == EnumFireMode.SEMIAUTO)
        {
            shoot(true);
            return true;
        }
        return false;
    }

    @Override
    public void updateKeyHeldState(int key, boolean held)
    {
        switch(key)
        {
            case 9:
                leftMouseHeld = held;
                break;
            case 8:
                rightMouseHeld = held;
                break;
        }
    }

    public void shoot(boolean secondary)
    {
        DriveableType type = getDriveableType();
        if(seats[0] == null && !(seats[0].riddenByEntity instanceof EntityLivingBase))
            return;
        if(getShootDelay(secondary) <= 0)
        {
            ArrayList<DriveablePosition> shootPoints = type.shootPoints(secondary);
            EnumWeaponType weaponType = type.weaponType(secondary);
            if(shootPoints.size() == 0)
                return;
            int currentGun = getCurrentGun(secondary);
            if(type.alternate(secondary))
            {
                currentGun = (currentGun + 1) % shootPoints.size();
                setCurrentGun(currentGun, secondary);
                shootEach(type, shootPoints.get(currentGun), currentGun, secondary, weaponType);
            }
            else
                for(int i = 0; i < shootPoints.size(); i++)
                    shootEach(type, shootPoints.get(i), i, secondary, weaponType);
        }
    }

    private void shootEach(DriveableType type, DriveablePosition shootPoint, int currentGun, boolean secondary, EnumWeaponType weaponType)
    {
        Vector3f gunVec = getOrigin(shootPoint), lookVector = getLookVector(shootPoint);
        if(shootPoint instanceof PilotGun)
        {
            PilotGun pilotGun = (PilotGun)shootPoint;
            GunType gunType = pilotGun.type;
            ItemStack bulletItemStack = driveableData.ammo[getDriveableType().numPassengerGunners + currentGun];
            if(gunType != null && bulletItemStack != null && bulletItemStack.getItem() instanceof ItemShootable)
            {
                ShootableType bullet = ((ItemShootable)bulletItemStack.getItem()).type;
                if(gunType.isAmmo(bullet))
                {
                    worldObj.spawnEntityInWorld(((ItemShootable)bulletItemStack.getItem()).getEntity(worldObj, Vector3f.add(gunVec, new Vector3f((float)posX, (float)posY, (float)posZ), null), lookVector, (EntityLivingBase)seats[0].riddenByEntity, gunType.bulletSpread / 2, gunType.damage, 10, bulletItemStack.getItemDamage(), type));
                    PacketPlaySound.sendSoundPacket(posX, posY, posZ, WatchDogs.soundRange, dimension, type.shootSound(secondary), false);
                    int damage = bulletItemStack.getItemDamage();
                    bulletItemStack.setItemDamage(damage + 1);
                    if(damage + 1 == bulletItemStack.getMaxDamage())
                    {
                        bulletItemStack.setItemDamage(0);
                        if(seats[0].riddenByEntity instanceof EntityPlayer && !((EntityPlayer)seats[0].riddenByEntity).capabilities.isCreativeMode)
                        {
                            bulletItemStack.stackSize--;
                            if(bulletItemStack.stackSize <= 0)
                                bulletItemStack = null;
                            driveableData.setInventorySlotContents(getDriveableType().numPassengerGunners + currentGun, bulletItemStack);
                        }
                    }
                    setShootDelay(type.shootDelay(secondary), secondary);
                }
            }
        }
        else
        {
            switch(weaponType)
            {
                case BOMB:
                {
                    int slot = -1;
                    for(int i = driveableData.getBombInventoryStart(); i < driveableData.getBombInventoryStart() + type.numBombSlots; i++)
                    {
                        ItemStack bomb = driveableData.getStackInSlot(i);
                        if(bomb != null && bomb.getItem() instanceof ItemBullet && type.isValidAmmo(((ItemBullet)bomb.getItem()).type, weaponType))
                            slot = i;
                    }
                    if(slot != -1)
                    {
                        int spread = 0;
                        int damageMultiplier = 1;
                        float shellSpeed = 0;
                        ItemStack bulletStack = driveableData.getStackInSlot(slot);
                        ItemBullet bulletItem = (ItemBullet)bulletStack.getItem();
                        EntityShootable bulletEntity = bulletItem.getEntity(worldObj, Vec3.createVectorHelper(posX + gunVec.x, posY + gunVec.y, posZ + gunVec.z), axes.getYaw(), axes.getPitch(), motionX, motionY, motionZ, (EntityLivingBase)seats[0].riddenByEntity, damageMultiplier, driveableData.getStackInSlot(slot).getItemDamage(), type);
                        worldObj.spawnEntityInWorld(bulletEntity);
                        if(type.shootSound(secondary) != null)
                            PacketPlaySound.sendSoundPacket(posX, posY, posZ, WatchDogs.soundRange, dimension, type.shootSound(secondary), false);
                        if(seats[0].riddenByEntity instanceof EntityPlayer && !((EntityPlayer)seats[0].riddenByEntity).capabilities.isCreativeMode)
                        {
                            bulletStack.setItemDamage(bulletStack.getItemDamage() + 1);
                            if(bulletStack.getItemDamage() == bulletStack.getMaxDamage())
                            {
                                bulletStack.setItemDamage(0);
                                bulletStack.stackSize--;
                                if(bulletStack.stackSize == 0)
                                    bulletStack = null;
                            }
                            driveableData.setInventorySlotContents(slot, bulletStack);
                        }
                        setShootDelay(type.shootDelay(secondary), secondary);
                    }
                    break;
                }
                case SHELL:
                {
                    int slot = -1;
                    for(int i = driveableData.getMissileInventoryStart(); i < driveableData.getMissileInventoryStart() + type.numMissileSlots; i++)
                    {
                        ItemStack shell = driveableData.getStackInSlot(i);
                        if(shell != null && shell.getItem() instanceof ItemBullet && type.isValidAmmo(((ItemBullet)shell.getItem()).type, weaponType))
                            slot = i;
                    }
                    if(slot != -1)
                    {
                        int spread = 0, damageMultiplier = 1;
                        float shellSpeed = 3F;
                        ItemStack bulletStack = driveableData.getStackInSlot(slot);
                        ItemBullet bulletItem = (ItemBullet)bulletStack.getItem();
                        EntityShootable bulletEntity = bulletItem.getEntity(worldObj, Vector3f.add(new Vector3f(posX, posY, posZ), gunVec, null), lookVector, (EntityLivingBase)seats[0].riddenByEntity, spread, damageMultiplier, shellSpeed, driveableData.getStackInSlot(slot).getItemDamage(), type);
                        worldObj.spawnEntityInWorld(bulletEntity);
                        if(type.shootSound(secondary) != null)
                            PacketPlaySound.sendSoundPacket(posX, posY, posZ, WatchDogs.soundRange, dimension, type.shootSound(secondary), false);
                        if(seats[0].riddenByEntity instanceof EntityPlayer && !((EntityPlayer)seats[0].riddenByEntity).capabilities.isCreativeMode)
                        {
                            bulletStack.setItemDamage(bulletStack.getItemDamage() + 1);
                            if(bulletStack.getItemDamage() == bulletStack.getMaxDamage())
                            {
                                bulletStack.setItemDamage(0);
                                bulletStack.stackSize--;
                                if(bulletStack.stackSize == 0)
                                    bulletStack = null;
                            }
                            driveableData.setInventorySlotContents(slot, bulletStack);
                        }
                        setShootDelay(type.shootDelay(secondary), secondary);
                    }
                    break;
                }
                default:
                    break;
            }
        }
    }

    public Vector3f getOrigin(DriveablePosition dp)
    {
        Vector3f localGunVec = new Vector3f(dp.position);
        if(dp.part == EnumDriveablePart.turret)
        {
            Vector3f.sub(localGunVec, getDriveableType().turretOrigin, localGunVec);
            localGunVec = seats[0].looking.findLocalVectorGlobally(localGunVec);
            Vector3f.add(localGunVec, getDriveableType().turretOrigin, localGunVec);
        }
        return rotate(localGunVec);
    }

    public Vector3f getLookVector(DriveablePosition dp)
    {
        return axes.getXAxis();
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        DriveableType type = getDriveableType();
        DriveableData data = getDriveableData();
        if(isInWater())
        {
            throttle = 0;
            seats[0].riddenByEntity = null;
        }
        for(int i = 0; i < getDriveableType().numPassengers + 1; i++)
            if(seats[i] == null || !seats[i].addedToChunk)
            {
                seats[i] = new EntitySeat(worldObj, this, i);
                worldObj.spawnEntityInWorld(seats[i]);
            }
        for(int i = 0; i < type.wheelPositions.length; i++)
            if(wheels[i] == null || !wheels[i].addedToChunk)
            {
                wheels[i] = new EntityWheel(worldObj, this, i);
                worldObj.spawnEntityInWorld(wheels[i]);
            }
        if(hasEnoughFuel())
            harvesterAngle += throttle / 5;
        if(type.harvestBlocks && type.health.get(EnumDriveablePart.harvester) != null)
        {
            CollisionBox box = type.health.get(EnumDriveablePart.harvester);
            for(float x = box.x; x <= box.x + box.w; x++)
                for(float y = box.y; y <= box.y + box.h; y++)
                    for(float z = box.z; z <= box.z + box.d; z++)
                    {
                        Vector3f v = axes.findLocalVectorGlobally(new Vector3f(x, y, z));
                        int blockX = (int)Math.round(posX + v.x), blockY = (int)Math.round(posY + v.y), blockZ = (int)Math.round(posZ + v.z);
                        Block block = worldObj.getBlock(blockX, blockY, blockZ);
                        if(type.materialsHarvested.contains(block.getMaterial()) && block.getBlockHardness(worldObj, blockX, blockY, blockZ) >= 0)
                        {
                            ArrayList<ItemStack> stacks = block.getDrops(worldObj, blockX, blockY, blockZ, worldObj.getBlockMetadata(blockX, blockY, blockZ), 0);
                            for(int i = 0; i < stacks.size(); i++)
                            {
                                ItemStack stack = stacks.get(i);
                                if(!InventoryHelper.addItemStackToInventory(driveableData, stack, seats != null && seats[0] != null && seats[0].riddenByEntity instanceof EntityPlayer && ((EntityPlayer)seats[0].riddenByEntity).capabilities.isCreativeMode) && worldObj.getGameRules().getGameRuleBooleanValue("doTileDrops"))
                                    worldObj.spawnEntityInWorld(new EntityItem(worldObj, blockX + 0.5, blockY + 0.5, blockZ + 0.5, stack));
                            }
                            worldObj.func_147480_a(blockX, blockY, blockZ, false);
                        }
                    }
        }
        for(DriveablePart part : getDriveableData().parts.values())
        {
            if(part.box != null)
            {
                part.update(this);
                if(part.onFire)
                {
                    if(worldObj.isRaining() && rand.nextInt(40) == 0)
                        part.onFire = false;
                    Vector3f pos = axes.findLocalVectorGlobally(new Vector3f(part.box.x + part.box.w / 2, part.box.y + part.box.h / 2, part.box.z + part.box.d / 2));
                    if(worldObj.getBlock(MathHelper.floor_double(posX + pos.x), MathHelper.floor_double(posY + pos.y), MathHelper.floor_double(posZ + pos.z)).getMaterial() == Material.water)
                        part.onFire = false;
                }
                else
                {
                    Vector3f pos = axes.findLocalVectorGlobally(new Vector3f(part.box.x / 16 + part.box.w / 32, part.box.y / 16 + part.box.h / 32, part.box.z / 16 + part.box.d / 32));
                    if(worldObj.getBlock(MathHelper.floor_double(posX + pos.x), MathHelper.floor_double(posY + pos.y), MathHelper.floor_double(posZ + pos.z)).getMaterial() == Material.lava)
                        part.onFire = true;
                }
            }
        }
        checkParts();
        prevRotationYaw = axes.getYaw();
        prevRotationPitch = axes.getPitch();
        prevRotationRoll = axes.getRoll();
        prevAxes = axes.clone();
        if(riddenByEntity != null && riddenByEntity.isDead)
            riddenByEntity = null;
        if(riddenByEntity != null && isDead)
            riddenByEntity.mountEntity(null);
        if(riddenByEntity != null)
            riddenByEntity.fallDistance = 0;
        boolean canThrust = (seats[0] != null && seats[0].riddenByEntity instanceof EntityPlayer && ((EntityPlayer)seats[0].riddenByEntity).capabilities.isCreativeMode) || driveableData.fuelInTank > 0;
        if((seats[0] != null && seats[0].riddenByEntity == null) || !canThrust)
        {
            throttle *= 0.98F;
            rightMouseHeld = leftMouseHeld = false;
        }
        if(shootDelayPrimary > 0)
            shootDelayPrimary--;
        if(shootDelaySecondary > 0)
            shootDelaySecondary--;
        if(leftMouseHeld && getDriveableType().modePrimary == EnumFireMode.FULLAUTO)
            shoot(false);
        if(rightMouseHeld && getDriveableType().modeSecondary == EnumFireMode.FULLAUTO)
            shoot(true);
        minigunSpeedPrimary *= 0.9;
        minigunSpeedSecondary *= 0.9;
        if(leftMouseHeld && getDriveableType().modePrimary == EnumFireMode.MINIGUN)
        {
            minigunSpeedPrimary += 0.1;
            if(minigunSpeedPrimary > 1)
                shoot(false);
        }
        if(rightMouseHeld && getDriveableType().modeSecondary == EnumFireMode.MINIGUN)
        {
            minigunSpeedSecondary += 0.1;
            if(minigunSpeedSecondary > 1)
                shoot(true);
        }
        int fuelMultiplier = 2;
        if(data.fuelInTank >= type.fuelTankSize)
            return;
        for(int i = 0; i < data.getSizeInventory(); i++)
        {
            ItemStack stack = data.getStackInSlot(i);
            if(stack == null || stack.stackSize <= 0)
                continue;
            Item item = stack.getItem();
            if(!data.engine.useRFPower)
            {
                if(item instanceof ItemPart)
                {
                    PartType part = ((ItemPart)item).type;
                    if(part.category == 9)
                    {
                        data.fuelInTank += fuelMultiplier;
                        int damage = stack.getItemDamage();
                        stack.setItemDamage(damage + 1);
                        if(damage >= stack.getMaxDamage())
                        {
                            stack.setItemDamage(0);
                            stack.stackSize--;
                            if(stack.stackSize <= 0)
                                data.setInventorySlotContents(i, null);
                        }
                        break;
                    }
                }
            }
        }
    }

    public void checkForCollisions()
    {
        boolean crashInWater = false;
        double speed = getSpeedXYZ();
        for(DriveablePosition p : getDriveableType().collisionPoints)
        {
            if(driveableData.parts.get(p.part).dead)
                continue;
            Vector3f lastRelPos = prevAxes.findLocalVectorGlobally(p.position);
            Vec3 lastPos = Vec3.createVectorHelper(prevPosX + lastRelPos.x, prevPosY + lastRelPos.y, prevPosZ + lastRelPos.z);
            Vector3f currentRelPos = axes.findLocalVectorGlobally(p.position);
            Vec3 currentPos = Vec3.createVectorHelper(posX + currentRelPos.x, posY + currentRelPos.y, posZ + currentRelPos.z);
            MovingObjectPosition hit = worldObj.rayTraceBlocks(lastPos, currentPos, crashInWater);
            if(hit != null && hit.typeOfHit == MovingObjectType.BLOCK)
            {
                int x = hit.blockX, y = hit.blockY, z = hit.blockZ, meta = worldObj.getBlockMetadata(x, y, z);
                Block blockHit = worldObj.getBlock(x, y, z);
                float blockHardness = blockHit.getBlockHardness(worldObj, x, y, z);
                if(!attackPart(p.part, DamageSource.inWall, blockHardness * blockHardness * (float)speed) && FileAPI.configBoolean("vehicles.break.blocks"))
                {
                    worldObj.playAuxSFXAtEntity(null, 2001, x, y, z, Block.getIdFromBlock(blockHit) + (meta << 12));
                    blockHit.dropBlockAsItem(worldObj, x, y, z, meta, 1);
                    worldObj.setBlockToAir(x, y, z);
                }
                else
                    worldObj.createExplosion(this, currentPos.xCoord, currentPos.yCoord, currentPos.zCoord, 1, false);
            }
        }
    }

    @Override
    protected void fall(float k)
    {
        if(k <= 0)
            return;
        int i = MathHelper.ceiling_float_int(k - 10);
        if(i > 0)
            attackPart(EnumDriveablePart.core, DamageSource.fall, i / 5);
    }

    public boolean attackPart(EnumDriveablePart ep, DamageSource source, float damage)
    {
        return driveableData.parts.get(ep).attack(damage, source.isFireDamage());
    }

    public Vector3f rotate(Vector3f inVec)
    {
        return axes.findLocalVectorGlobally(inVec);
    }

    public Vector3f rotate(Vec3 inVec)
    {
        return rotate(inVec.xCoord, inVec.yCoord, inVec.zCoord);
    }

    public Vector3f rotate(double x, double y, double z)
    {
        return rotate(new Vector3f((float)x, (float)y, (float)z));
    }

    public void rotateYaw(float rotateBy)
    {
        if(Math.abs(rotateBy) < 0.01)
            return;
        axes.rotateLocalYaw(rotateBy);
        updatePrevAngles();
    }

    public void rotatePitch(float rotateBy)
    {
        if(Math.abs(rotateBy) < 0.01)
            return;
        axes.rotateLocalPitch(rotateBy);
        updatePrevAngles();
    }

    public void rotateRoll(float rotateBy)
    {
        if(Math.abs(rotateBy) < 0.01)
            return;
        axes.rotateLocalRoll(rotateBy);
        updatePrevAngles();
    }

    public void updatePrevAngles()
    {
        double dYaw = axes.getYaw() - prevRotationYaw, dPitch = axes.getPitch() - prevRotationPitch, dRoll = axes.getRoll() - prevRotationRoll;
        if(dYaw > 180)
            prevRotationYaw += 360;
        else if(dYaw < -180)
            prevRotationYaw -= 360;
        if(dPitch > 180)
            prevRotationPitch += 360;
        else if(dPitch < -180)
            prevRotationPitch -= 360;
        if(dRoll > 180)
            prevRotationRoll += 360;
        else if(dRoll < -180)
            prevRotationRoll -= 360;
    }

    public void setRotation(float rotYaw, float rotPitch, float rotRoll)
    {
        axes.setAngles(rotYaw, rotPitch, rotRoll);
    }

    public boolean isPartOfThis(Entity ent)
    {
        for(EntitySeat seat : seats)
        {
            if(seat == null)
                continue;
            if(ent == seat)
                return true;
            if(seat.riddenByEntity == ent)
                return true;
        }
        return ent == this;
    }

    @Override
    public float getShadowSize()
    {
        return 0;
    }

    public DriveableType getDriveableType()
    {
        return DriveableType.getDriveable(driveableType);
    }

    public DriveableData getDriveableData()
    {
        return driveableData;
    }

    @Override
    public boolean isDead()
    {
        return isDead;
    }

    @Override
    public Entity getControllingEntity()
    {
        return seats[0].getControllingEntity();
    }

    @Override
    public ItemStack getPickedResult(MovingObjectPosition target)
    {
        ItemStack stack = new ItemStack(getDriveableType().item, 1, 0);
        stack.stackTagCompound = new NBTTagCompound();
        driveableData.writeToNBT(stack.stackTagCompound);
        return stack;
    }

    public boolean hasFuel()
    {
        if(seats == null || seats[0] == null || seats[0].riddenByEntity == null)
            return false;
        return seats[0].riddenByEntity instanceof EntityPlayer && ((EntityPlayer)seats[0].riddenByEntity).capabilities.isCreativeMode || driveableData.fuelInTank > 0;
    }

    public boolean hasEnoughFuel()
    {
        if(seats == null || seats[0] == null || seats[0].riddenByEntity == null)
            return false;
        return seats[0].riddenByEntity instanceof EntityPlayer && ((EntityPlayer)seats[0].riddenByEntity).capabilities.isCreativeMode || driveableData.fuelInTank > driveableData.engine.fuelConsumption * throttle;
    }

    public double getSpeedXYZ()
    {
        return Math.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);
    }

    public double getSpeedXZ()
    {
        return Math.sqrt(motionX * motionX + motionZ * motionZ);
    }

    public boolean landVehicle()
    {
        return false;
    }

    public boolean gearDown()
    {
        return true;
    }

    public boolean onGround()
    {
        return onGround;
    }

    public ArrayList<BulletHit> attackFromBullet(Vector3f origin, Vector3f motion)
    {
        ArrayList<BulletHit> hits = new ArrayList<BulletHit>();
        Vector3f relativePosVector = Vector3f.sub(origin, new Vector3f((float)posX, (float)posY, (float)posZ), null), rotatedPosVector = axes.findGlobalVectorLocally(relativePosVector), rotatedMotVector = axes.findGlobalVectorLocally(motion);
        for(DriveablePart part : getDriveableData().parts.values())
        {
            DriveableHit hit = part.rayTrace(this, rotatedPosVector, rotatedMotVector);
            if(hit != null)
                hits.add(hit);
        }
        return hits;
    }

    public float bulletHit(EntityBullet bullet, DriveableHit hit, float penetratingPower)
    {
        getDriveableData().parts.get(hit.part).hitByBullet(bullet);
        checkParts();
        WatchDogs.getPacketHandler().sendToAllAround(new PacketDriveableDamage(this), posX, posY, posZ, 100, dimension);
        return penetratingPower - 5;
    }

    public DriveablePart raytraceParts(Vector3f origin, Vector3f motion)
    {
        Vector3f relativePosVector = Vector3f.sub(origin, new Vector3f((float)posX, (float)posY, (float)posZ), null), rotatedPosVector = axes.findGlobalVectorLocally(relativePosVector), rotatedMotVector = axes.findGlobalVectorLocally(motion);
        for(DriveablePart part : getDriveableData().parts.values())
            if(part.rayTrace(this, rotatedPosVector, rotatedMotVector) != null)
                return part;
        return null;
    }

    public boolean canHitPart(EnumDriveablePart part)
    {
        return true;
    }

    public void checkParts()
    {
        for(DriveablePart part : getDriveableData().parts.values())
            if(part != null && !part.dead && part.health <= 0 && part.maxHealth > 0)
                killPart(part);
        if(getDriveableData().parts.get(EnumDriveablePart.core).dead)
        {
            for(DriveablePart part : driveableData.parts.values())
                if(part.health > 0 && !part.dead)
                    killPart(part);
            setDead();
        }
    }

    private void killPart(DriveablePart part)
    {
        if(part.dead)
            return;
        part.health = 0;
        part.dead = true;
        DriveableType type = getDriveableType();
        Vector3f pos = new Vector3f(0, 0, 0);
        if(part.box != null)
            pos = axes.findLocalVectorGlobally(new Vector3f(part.box.x / 16 + part.box.w / 32, part.box.y / 16 + part.box.h / 32, part.box.z / 16 + part.box.d / 32));
        ArrayList<ItemStack> drops = type.getItemsRequired(part, getDriveableData().engine);
        if(drops != null)
            for(ItemStack stack : drops)
                worldObj.spawnEntityInWorld(new EntityItem(worldObj, posX + pos.x, posY + pos.y, posZ + pos.z, stack.copy()));
        dropItemsOnPartDeath(pos, part);
        if(part.type == EnumDriveablePart.core)
            for(int i = 0; i < getDriveableData().getSizeInventory(); i++)
            {
                ItemStack stack = getDriveableData().getStackInSlot(i);
                if(stack != null)
                    worldObj.spawnEntityInWorld(new EntityItem(worldObj, posX + rand.nextGaussian(), posY + rand.nextGaussian(), posZ + rand.nextGaussian(), stack));
            }
        for(EnumDriveablePart child : part.type.getChildren())
            killPart(getDriveableData().parts.get(child));
    }

    protected abstract void dropItemsOnPartDeath(Vector3f midpoint, DriveablePart part);

    @Override
    public float getPlayerRoll()
    {
        return axes.getRoll();
    }

    @Override
    public void explode()
    {}

    @Override
    public float getCameraDistance()
    {
        return getDriveableType().cameraDistance;
    }

    public boolean isPartIntact(EnumDriveablePart part)
    {
        DriveablePart thisPart = getDriveableData().parts.get(part);
        return thisPart.maxHealth == 0 || thisPart.health > 0;
    }

    public abstract boolean hasMouseControlMode();

    public abstract String getBombInventoryName();

    public abstract String getMissileInventoryName();

    public boolean rotateWithTurret(Seat seat)
    {
        return seat.part == EnumDriveablePart.turret;
    }

    @Override
    public String getCommandSenderName()
    {
        return getDriveableType().name;
    }

    public int getShootDelay(boolean secondary)
    {
        return secondary ? shootDelaySecondary : shootDelayPrimary;
    }

    public float getMinigunSpeed(boolean secondary)
    {
        return secondary ? minigunSpeedSecondary : minigunSpeedPrimary;
    }

    public int getCurrentGun(boolean secondary)
    {
        return secondary ? currentGunSecondary : currentGunPrimary;
    }

    public void setShootDelay(int i, boolean secondary)
    {
        if(secondary)
            shootDelaySecondary = i;
        else
            shootDelayPrimary = i;
    }

    public void setMinigunSpeed(float f, boolean secondary)
    {
        if(secondary)
            minigunSpeedSecondary = f;
        else
            minigunSpeedPrimary = f;
    }

    public void setCurrentGun(int i, boolean secondary)
    {
        if(secondary)
            currentGunSecondary = i;
        else
            currentGunPrimary = i;
    }
}