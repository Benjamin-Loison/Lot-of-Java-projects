package fr.watchdogs.benjaminloison.driveables;

import java.util.ArrayList;

import com.flansmod.common.vector.Vector3f;
import com.flansmod.common.vector.Vector3i;

import cpw.mods.fml.common.network.ByteBufUtils;
import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.api.RotatedAxes;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.common.guns.BulletType;
import fr.watchdogs.benjaminloison.common.guns.EnumFireMode;
import fr.watchdogs.benjaminloison.common.guns.GunType;
import fr.watchdogs.benjaminloison.common.guns.ItemBullet;
import fr.watchdogs.benjaminloison.common.guns.ItemGun;
import fr.watchdogs.benjaminloison.common.guns.ItemTool;
import fr.watchdogs.benjaminloison.packets.PacketDriveableDamage;
import fr.watchdogs.benjaminloison.packets.PacketDriveableGUI;
import fr.watchdogs.benjaminloison.packets.PacketMechaControl;
import fr.watchdogs.benjaminloison.packets.PacketPlaySound;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class EntityMecha extends EntityDriveable
{
    private int ticksSinceUsed, jumpDelay = 0, diamondTimer = 0;
    public int toggleTimer = 0, shootDelayLeft = 0, shootDelayRight = 0, soundDelayLeft = 0, soundDelayRight = 0;
    private float moveX = 0, moveZ = 0, rocketTimer = 0;
    public RotatedAxes legAxes;
    public float prevLegsYaw = 0, legSwing = 0, breakingProgress = 0;
    public MechaInventory inventory;
    public Vector3i breakingBlock = null;
    boolean couldNotFindFuel;

    public EntityMecha(World world)
    {
        super(world);
        setSize(2, 3);
        stepHeight = 3;
        legAxes = new RotatedAxes();
        inventory = new MechaInventory(this);
    }

    public EntityMecha(World world, double x, double y, double z, MechaType type, DriveableData data, NBTTagCompound tags)
    {
        super(world, type, data);
        legAxes = new RotatedAxes();
        setSize(2, 3);
        stepHeight = 3;
        setPosition(x, y, z);
        initType(type, false);
        inventory = new MechaInventory(this, tags);
    }

    public EntityMecha(World world, double x, double y, double z, EntityPlayer placer, MechaType type, DriveableData data, NBTTagCompound tags)
    {
        this(world, x, y, z, type, data, tags);
        rotateYaw(placer.rotationYaw + 90);
        legAxes.rotateGlobalYaw(placer.rotationYaw + 90);
        prevLegsYaw = legAxes.getYaw();
    }

    @Override
    protected void initType(DriveableType type, boolean clientSide)
    {
        super.initType(type, clientSide);
        setSize(((MechaType)type).width, ((MechaType)type).height);
        stepHeight = ((MechaType)type).stepHeight;
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag)
    {
        super.writeEntityToNBT(tag);
        tag.setFloat("LegsYaw", legAxes.getYaw());
        tag.setTag("Inventory", inventory.writeToNBT(new NBTTagCompound()));
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag)
    {
        super.readEntityFromNBT(tag);
        legAxes.setAngles(tag.getFloat("LegsYaw"), 0, 0);
        inventory.readFromNBT(tag.getCompoundTag("Inventory"));
    }

    @Override
    public void writeSpawnData(ByteBuf data)
    {
        super.writeSpawnData(data);
        ByteBufUtils.writeTag(data, inventory.writeToNBT(new NBTTagCompound()));
    }

    @Override
    public void readSpawnData(ByteBuf data)
    {
        super.readSpawnData(data);
        legAxes.rotateGlobalYaw(axes.getYaw());
        prevLegsYaw = legAxes.getYaw();
        inventory.readFromNBT(ByteBufUtils.readTag(data));
    }

    @Override
    public void onMouseMoved(int deltaX, int deltaY)
    {}

    @Override
    public boolean interactFirst(EntityPlayer entityplayer)
    {
        if(isDead)
            return false;
        ItemStack currentItem = entityplayer.getCurrentEquippedItem();
        if(currentItem != null && currentItem.getItem() instanceof ItemTool && ((ItemTool)currentItem.getItem()).type.healDriveables)
            return true;
        MechaType type = getMechaType();
        for(int i = 0; i <= type.numPassengers; i++)
            if(seats[i].interactFirst(entityplayer))
                return true;
        return false;
    }

    public MechaType getMechaType()
    {
        return MechaType.getMecha(driveableType);
    }

    @Override
    public boolean pressKey(int key, EntityPlayer player)
    {
        MechaType type = getMechaType();
        DriveableData data = getDriveableData();
        switch(key)
        {
            case 0:
                return true;
            case 1:
                return true;
            case 2:
                return true;
            case 3:
                return true;
            case 4:
            {
                boolean canThrustCreatively = seats != null && seats[0] != null && seats[0].riddenByEntity instanceof EntityPlayer && ((EntityPlayer)seats[0].riddenByEntity).capabilities.isCreativeMode;
                if(onGround && (jumpDelay == 0) && (canThrustCreatively || data.fuelInTank > data.engine.fuelConsumption) && isPartIntact(EnumDriveablePart.hips))
                {
                    jumpDelay = 20;
                    motionY += type.jumpVelocity;
                    if(!canThrustCreatively)
                        data.fuelInTank -= data.engine.fuelConsumption;
                }
                return true;
            }
            case 5:
                return true;
            case 6:
            {
                seats[0].riddenByEntity.mountEntity(null);
                return true;
            }
            case 7:
            {
                WatchDogs.getPacketHandler().sendToServer(new PacketDriveableGUI(4));
                ((EntityPlayer)seats[0].riddenByEntity).openGui(WatchDogs.instance, 10, worldObj, chunkCoordX, chunkCoordY, chunkCoordZ);
                return true;
            }
            case 8:
                return true;
            case 9:
                return true;
            case 10:
                return true;
            case 11:
                return true;
            case 12:
                return true;
            case 13:
                return true;
            case 14:
                return true;
            case 15:
                return true;
            case 16:
                return true;
            case 17:
                return true;

        }
        return false;
    }

    private boolean useItem(boolean left)
    {
        if(left ? isPartIntact(EnumDriveablePart.leftArm) : isPartIntact(EnumDriveablePart.rightArm))
        {
            boolean creative = !(seats[0].riddenByEntity instanceof EntityPlayer) || ((EntityPlayer)seats[0].riddenByEntity).capabilities.isCreativeMode;
            ItemStack heldStack = left ? inventory.getStackInSlot(EnumMechaSlotType.leftTool) : inventory.getStackInSlot(EnumMechaSlotType.rightTool);
            if(heldStack == null)
                return false;
            Item heldItem = heldStack.getItem();
            MechaType mechaType = getMechaType();
            if(heldItem instanceof ItemMechaAddon)
            {
                MechaItemType toolType = ((ItemMechaAddon)heldItem).type;
                float reach = toolType.reach * mechaType.reach;
                Vector3f lookOrigin = new Vector3f((float)mechaType.seats[0].x / 16, (float)mechaType.seats[0].y / 16 + seats[0].riddenByEntity.getMountedYOffset(), (float)mechaType.seats[0].z / 16);
                lookOrigin = axes.findLocalVectorGlobally(lookOrigin);
                Vector3f.add(lookOrigin, new Vector3f(posX, posY, posZ), lookOrigin);
                Vector3f lookVector = axes.findLocalVectorGlobally(seats[0].looking.findLocalVectorGlobally(new Vector3f(reach, 0, 0)));
                Vector3f lookTarget = Vector3f.add(lookVector, lookOrigin, null);
                MovingObjectPosition hit = worldObj.rayTraceBlocks(lookOrigin.toVec3(), lookTarget.toVec3());
                if(hit != null && hit.typeOfHit == MovingObjectType.BLOCK)
                {
                    if(breakingBlock == null || breakingBlock.x != hit.blockX || breakingBlock.y != hit.blockY || breakingBlock.z != hit.blockZ)
                        breakingProgress = 0;
                    breakingBlock = new Vector3i(hit.blockX, hit.blockY, hit.blockZ);
                }
            }
            else if(heldItem instanceof ItemGun)
            {
                ItemGun gunItem = (ItemGun)heldItem;
                GunType gunType = gunItem.type;
                int delay = left ? shootDelayLeft : shootDelayRight;
                if(delay <= 0)
                {
                    int bulletID = 0;
                    ItemStack bulletStack = null;
                    for(; bulletID < gunType.numAmmoItemsInGun; bulletID++)
                    {
                        ItemStack checkingStack = gunItem.getBulletItemStack(heldStack, bulletID);
                        if(checkingStack != null && checkingStack.getItem() != null && checkingStack.getItemDamage() < checkingStack.getMaxDamage())
                        {
                            bulletStack = checkingStack;
                            break;
                        }
                    }
                    if(bulletStack == null)
                        gunItem.reload(heldStack, gunType, worldObj, this, driveableData, (infiniteAmmo() || creative), false);
                    else if(bulletStack.getItem() instanceof ItemBullet)
                    {
                        shoot(heldStack, gunType, bulletStack, creative, left);
                        bulletStack.setItemDamage(bulletStack.getItemDamage() + 1);
                        gunItem.setBulletItemStack(heldStack, bulletStack, bulletID);
                    }
                }
            }
        }
        return true;
    }

    private void shoot(ItemStack stack, GunType gunType, ItemStack bulletStack, boolean creative, boolean left)
    {
        MechaType mechaType = getMechaType();
        BulletType bulletType = ((ItemBullet)bulletStack.getItem()).type;
        RotatedAxes a = new RotatedAxes();
        Vector3f armVector = new Vector3f(mechaType.armLength, 0, 0), gunVector = new Vector3f(mechaType.armLength + 1.2 * mechaType.heldItemScale, 0.5 * mechaType.heldItemScale, 0), armOrigin = left ? mechaType.leftArmOrigin : mechaType.rightArmOrigin;
        a.rotateGlobalYaw(axes.getYaw());
        armOrigin = a.findLocalVectorGlobally(armOrigin);
        a.rotateLocalPitch(-seats[0].looking.getPitch());
        gunVector = a.findLocalVectorGlobally(gunVector);
        armVector = a.findLocalVectorGlobally(armVector);
        Vector3f bulletOrigin = Vector3f.add(armOrigin, gunVector, null);
        bulletOrigin = Vector3f.add(new Vector3f(posX, posY, posZ), bulletOrigin, null);
        for(int k = 0; k < gunType.numBullets; k++)
            worldObj.spawnEntityInWorld(((ItemBullet)bulletStack.getItem()).getEntity(worldObj, bulletOrigin, armVector, (EntityLivingBase)(seats[0].riddenByEntity), gunType.getSpread(stack) / 2, gunType.getDamage(stack), gunType.getBulletSpeed(stack), bulletStack.getItemDamage(), mechaType));
        if(left)
            shootDelayLeft = gunType.mode == EnumFireMode.SEMIAUTO ? Math.max(gunType.shootDelay, 5) : gunType.shootDelay;
        else
            shootDelayRight = gunType.mode == EnumFireMode.SEMIAUTO ? Math.max(gunType.shootDelay, 5) : gunType.shootDelay;
        if(bulletType.dropItemOnShoot != null && !creative)
            ItemGun.dropItem(worldObj, this, bulletType.dropItemOnShoot);
        if((left ? soundDelayLeft : soundDelayRight) <= 0 && gunType.shootSound != null)
        {
            PacketPlaySound.sendSoundPacket(posX, posY, posZ, WatchDogs.soundRange, dimension, gunType.shootSound, gunType.distortSound);
            if(left)
                soundDelayLeft = gunType.shootSoundLength;
            else
                soundDelayRight = gunType.shootSoundLength;
        }
    }

    @Override
    protected void fall(float f)
    {
        attackEntityFrom(DamageSource.fall, f);
    }

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float i)
    {
        if(isDead)
            return true;
        MechaType type = getMechaType();
        if(damagesource.getDamageType().equals("fall"))
        {
            boolean takeFallDamage = type.takeFallDamage && !stopFallDamage(), damageBlocksFromFalling = type.damageBlocksFromFalling || breakBlocksUponFalling();
            byte wouldBeNegativeDamage;
            if(((i * type.fallDamageMultiplier * vulnerability()) - 2) < 0)
                wouldBeNegativeDamage = 0;
            else
                wouldBeNegativeDamage = 1;
            float damageToInflict = takeFallDamage ? i * ((type.fallDamageMultiplier * vulnerability())) * wouldBeNegativeDamage : 0, blockDamageFromFalling = damageBlocksFromFalling ? i * (type.blockDamageFromFalling) / 10 : 0;
            driveableData.parts.get(EnumDriveablePart.hips).attack(damageToInflict, false);
            checkParts();
            WatchDogs.getPacketHandler().sendToAllAround(new PacketDriveableDamage(this), posX, posY, posZ, WatchDogs.driveableUpdateRange, dimension);
            if(blockDamageFromFalling > 1)
                worldObj.createExplosion(this, posX, posY, posZ, blockDamageFromFalling, FileAPI.configBoolean("weapons.explosions"));
        }
        else if(damagesource.damageType.equals("player") && damagesource.getEntity().onGround && (seats[0] == null || seats[0].riddenByEntity == null))
        {
            ItemStack mechaStack = new ItemStack(type.item, 1, 0);
            mechaStack.stackTagCompound = new NBTTagCompound();
            driveableData.writeToNBT(mechaStack.stackTagCompound);
            inventory.writeToNBT(mechaStack.stackTagCompound);
            entityDropItem(mechaStack, 0.5F);
            setDead();
        }
        else
            driveableData.parts.get(EnumDriveablePart.core).attack(i * vulnerability(), damagesource.isFireDamage());
        return true;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        if(jumpDelay > 0)
            jumpDelay--;
        if(shootDelayLeft > 0)
            shootDelayLeft--;
        if(shootDelayRight > 0)
            shootDelayRight--;
        if(soundDelayLeft > 0)
            soundDelayLeft--;
        if(soundDelayRight > 0)
            soundDelayRight--;
        if(seats[0] == null || seats[0].riddenByEntity == null)
            rightMouseHeld = leftMouseHeld = false;
        MechaType type = this.getMechaType();
        DriveableData data = getDriveableData();
        if(type == null)
        {
            WatchDogs.print("Mecha type null. Not ticking mecha");
            return;
        }
        prevLegsYaw = legAxes.getYaw();
        if(toggleTimer == 0 && autoRepair())
        {
            for(EnumDriveablePart part : EnumDriveablePart.values())
            {
                DriveablePart thisPart = data.parts.get(part);
                boolean hasCreativePlayer = seats != null && seats[0] != null && seats[0].riddenByEntity instanceof EntityPlayer && ((EntityPlayer)seats[0].riddenByEntity).capabilities.isCreativeMode;
                if(thisPart != null && thisPart.health != 0 && thisPart.health < thisPart.maxHealth && (hasCreativePlayer || data.fuelInTank >= 10))
                {
                    thisPart.health += 1;
                    if(!hasCreativePlayer)
                        data.fuelInTank -= 10;
                }
            }
            toggleTimer = 20;
        }
        if(diamondTimer > 0)
            --diamondTimer;
        if(isPartIntact(EnumDriveablePart.hips))
        {
            setSize(type.width, type.height);
            yOffset = type.yOffset;
        }
        else
        {
            setSize(type.width, type.height - type.chassisHeight);
            yOffset = type.yOffset - type.chassisHeight;
        }
        ticksSinceUsed++;
        if(seats[0].riddenByEntity != null)
            ticksSinceUsed = 0;
        if(FileAPI.configNumber("mecha.love") > 0 && ticksSinceUsed > FileAPI.configNumber("mecha.love") * 20)
            setDead();
        if(toggleTimer > 0)
            toggleTimer--;
        if(seats[0] != null)
        {
            if(seats[0].riddenByEntity instanceof EntityLivingBase && !(seats[0].riddenByEntity instanceof EntityPlayer))
                axes.setAngles(((EntityLivingBase)seats[0].riddenByEntity).renderYawOffset + 90, 0, 0);
            else
            {
                if(type.limitHeadTurn)
                {
                    float axesLegs = legAxes.getYaw(), axesBody = axes.getYaw();
                    double dYaw = axesBody - axesLegs;
                    if(dYaw > 180)
                        axesBody -= 360;
                    if(dYaw < -180)
                        axesBody += 360;
                    if(axesLegs + type.limitHeadTurnValue < axesBody)
                        axes.setAngles(axesLegs + type.limitHeadTurnValue, 0, 0);
                    if(axesLegs - type.limitHeadTurnValue > axesBody)
                        axes.setAngles(axesLegs - type.limitHeadTurnValue, 0, 0);
                }
                float yaw = seats[0].looking.getYaw() - seats[0].prevLooking.getYaw();
                axes.rotateGlobalYaw(yaw);
                seats[0].looking.rotateGlobalYaw(-yaw);
            }
        }
        moveX = 0;
        moveZ = 0;
        float jetPack = jetPackPower();
        if(isInWater() && shouldFloat())
        {
            motionY *= 0.89;
            motionY += 0.1;
        }
        if(rocketTimer != 0)
            rocketTimer--;
        Vector3f actualMotion = new Vector3f(0, motionY - (16 / 400), 0);
        motionY = actualMotion.y;
        moveEntity(actualMotion.x, actualMotion.y, actualMotion.z);
        setPosition(posX, posY, posZ);
        if(ticksExisted % 5 == 0)
            WatchDogs.getPacketHandler().sendToAllAround(new PacketMechaControl(this), posX, posY, posZ, WatchDogs.driveableUpdateRange, dimension);
        for(EntitySeat seat : seats)
            if(seat != null)
                seat.updatePosition();
    }

    private float tailFloat(float f)
    {
        return f - MathHelper.floor_float(f);
    }

    public boolean stopFallDamage()
    {
        for(MechaItemType type : getUpgradeTypes())
            if(type.stopMechaFallDamage)
                return true;
        return false;
    }

    public boolean breakBlocksUponFalling()
    {
        for(MechaItemType type : getUpgradeTypes())
            if(type.forceBlockFallDamage)
                return true;
        return false;
    }

    public boolean vacuumItems()
    {
        for(MechaItemType type : getUpgradeTypes())
            if(type.vacuumItems)
                return true;
        return false;
    }

    public boolean refineIron()
    {
        for(MechaItemType type : getUpgradeTypes())
            if(type.refineIron)
                return true;
        return false;
    }

    public MechaItemType diamondDetect()
    {
        for(MechaItemType type : getUpgradeTypes())
            if(type.diamondDetect)
                return type;
        return null;
    }

    public Boolean wasteCompact()
    {
        for(MechaItemType type : getUpgradeTypes())
            if(type.wasteCompact)
                return true;
        return false;
    }

    public float diamondMultiplier()
    {
        float multiplier = 1;
        for(MechaItemType type : getUpgradeTypes())
            multiplier *= type.fortuneDiamond;
        return multiplier;
    }

    public float speedMultiplier()
    {
        float multiplier = 1;
        for(MechaItemType type : getUpgradeTypes())
            multiplier *= type.speedMultiplier;
        return multiplier;
    }

    public float coalMultiplier()
    {
        float multiplier = 1;
        for(MechaItemType type : getUpgradeTypes())
            multiplier *= type.fortuneCoal;
        return multiplier;
    }

    public float redstoneMultiplier()
    {
        float multiplier = 1;
        for(MechaItemType type : getUpgradeTypes())
            multiplier *= type.fortuneRedstone;
        return multiplier;
    }

    public float vulnerability()
    {
        float multiplier = 1;
        for(MechaItemType type : getUpgradeTypes())
            multiplier *= (1 - type.damageResistance);
        return multiplier;
    }

    public float emeraldMultiplier()
    {
        float multiplier = 1;
        for(MechaItemType type : getUpgradeTypes())
            multiplier *= type.fortuneEmerald;
        return multiplier;
    }

    public float ironMultiplier()
    {
        float multiplier = 1;
        for(MechaItemType type : getUpgradeTypes())
            multiplier *= type.fortuneIron;
        return multiplier;
    }

    public int lightLevel()
    {
        int level = 0;
        for(MechaItemType type : getUpgradeTypes())
            level = Math.max(level, type.lightLevel);
        return level;
    }

    public boolean forceDark()
    {
        for(MechaItemType type : getUpgradeTypes())
            if(type.forceDark)
                return true;
        return false;
    }

    public boolean autoCoal()
    {
        for(MechaItemType type : getUpgradeTypes())
            if(type.autoCoal)
                return true;
        return false;
    }

    public boolean autoRepair()
    {
        for(MechaItemType type : getUpgradeTypes())
            if(type.autoRepair)
                return true;
        return false;
    }

    public boolean shouldFloat()
    {
        for(MechaItemType type : getUpgradeTypes())
            if(type.floater)
                return true;
        return false;
    }

    public boolean infiniteAmmo()
    {
        for(MechaItemType type : getUpgradeTypes())
            if(type.infiniteAmmo)
                return true;
        return false;
    }

    public MechaItemType rocketPack()
    {
        for(MechaItemType type : getUpgradeTypes())
            if(type.rocketPack)
                return type;
        return null;
    }

    public boolean shouldFly()
    {
        return rocketPack() != null;
    }

    public float jetPackPower()
    {
        float multiplier = 1;
        for(MechaItemType type : getUpgradeTypes())
            multiplier *= type.rocketPower;
        return multiplier;
    }

    public ArrayList<MechaItemType> getUpgradeTypes()
    {
        ArrayList<MechaItemType> types = new ArrayList<MechaItemType>();
        for(ItemStack stack : inventory.stacks.values())
            if(stack != null && stack.getItem() instanceof ItemMechaAddon)
                types.add(((ItemMechaAddon)stack.getItem()).type);
        return types;
    }

    @Override
    protected void dropItemsOnPartDeath(Vector3f midpoint, DriveablePart part)
    {}

    @Override
    public boolean hasMouseControlMode()
    {
        return false;
    }

    @Override
    public String getBombInventoryName()
    {
        return "";
    }

    @Override
    public String getMissileInventoryName()
    {
        return "";
    }
}