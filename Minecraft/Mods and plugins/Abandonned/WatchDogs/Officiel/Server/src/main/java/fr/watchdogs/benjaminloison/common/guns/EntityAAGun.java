package fr.watchdogs.benjaminloison.common.guns;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.packets.PacketAAGunAngles;
import fr.watchdogs.benjaminloison.packets.PacketPlaySound;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityAAGun extends Entity implements IEntityAdditionalSpawnData
{
    private double sPosX, sPosY, sPosZ, sYaw, sPitch, f0, f1, f2;
    private int sUpdateTime, health, shootDelay;
    public float gunYaw, gunPitch, prevGunYaw, prevGunPitch, barrelRecoil[];
    public AAGunType type;
    public Entity towedByEntity;
    public ItemStack[] ammo;
    public int ticksSinceUsed = 0, reloadTimer, currentBarrel;
    public boolean mouseHeld, wasShooting;
    public EntityPlayer placer;
    public String placerName;
    public Entity target;
    public static final float targetAcquireInterval = 10;

    public EntityAAGun(World world)
    {
        super(world);
        preventEntitySpawning = true;
        setSize(2, 2);
        yOffset = 0;
        gunYaw = 0;
        gunPitch = 0;
        shootDelay = 0;
    }

    public EntityAAGun(World world, AAGunType type1, double d, double d1, double d2, EntityPlayer p)
    {
        this(world);
        placer = p;
        placerName = p.getCommandSenderName();
        type = type1;
        initType();
        setPosition(d, d1, d2);
    }

    @Override
    public void setPosition(double d, double d1, double d2)
    {
        posX = d;
        posY = d1;
        posZ = d2;
        float f = width / 2, f1 = height;
        boundingBox.setBounds(d - f, (d1 - yOffset) + ySize, d2 - f, d + f, (d1 - yOffset) + ySize + f1, d2 + f);
    }

    @Override
    public void setPositionAndRotation2(double d, double d1, double d2, float f, float f1, int i)
    {
        sPosX = d;
        sPosY = d1;
        sPosZ = d2;
        sYaw = f;
        sPitch = f1;
        sUpdateTime = i;
    }

    public void initType()
    {
        health = type.health;
        barrelRecoil = new float[type.numBarrels];
        ammo = new ItemStack[type.numBarrels];
    }

    @Override
    protected void entityInit()
    {}

    @Override
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer)
    {}

    @Override
    public void applyEntityCollision(Entity entity)
    {}

    @Override
    public AxisAlignedBB getCollisionBox(Entity entity)
    {
        return entity.boundingBox;
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
        return 0;
    }

    public void setMouseHeld(boolean held)
    {
        mouseHeld = held;
    }

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float i)
    {
        if(damagesource.damageType.equals("player"))
        {
            if(!(damagesource.getEntity() == riddenByEntity && riddenByEntity != null))
                return riddenByEntity.attackEntityFrom(damagesource, i);
        }
        else
        {
            setBeenAttacked();
            health -= i;
            if(health <= 0)
                setDead();
        }
        return true;
    }

    public Vec3 rotate(double x, double y, double z)
    {
        double cosYaw = Math.cos(180 - gunYaw * (float)Math.PI / 180), sinYaw = Math.sin(180 - gunYaw * (float)Math.PI / 180), cosPitch = Math.cos(gunPitch * (float)Math.PI / 180), sinPitch = Math.sin(gunPitch * (float)Math.PI / 180);
        return Vec3.createVectorHelper(x * cosYaw + (y * sinPitch + z * cosPitch) * sinYaw, y * cosPitch - z * sinPitch, -x * sinYaw + (y * sinPitch + z * cosPitch) * cosYaw);
    }

    @Override
    public boolean canBeCollidedWith()
    {
        return !isDead;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        prevGunYaw = gunYaw;
        prevGunPitch = gunPitch;
        ticksSinceUsed++;
        if(FileAPI.configNumber("life.aa") > 0 && ticksSinceUsed > FileAPI.configNumber("life.aa") * 20)
            setDead();
        if(riddenByEntity != null)
        {
            ticksSinceUsed = 0;
            gunYaw = riddenByEntity.rotationYaw - 90;
            gunPitch = riddenByEntity.rotationPitch;
        }
        if(gunPitch > type.bottomViewLimit)
            gunPitch = type.bottomViewLimit;
        if(gunPitch < -type.topViewLimit)
            gunPitch = -type.topViewLimit;
        for(int i = 0; i < type.numBarrels; i++)
            barrelRecoil[i] *= 0.9;
        if(shootDelay > 0)
            shootDelay--;
        if(isSentry())
        {
            if(target != null && target.isDead)
                target = null;
            if(target == null && ticksExisted % targetAcquireInterval == 0)
                target = getValidTarget();
            if(target != null)
            {
                double dX = target.posX - posX, dY = target.posY - (posY + 1.5), dZ = target.posZ - posZ, distanceToTarget = Math.sqrt(dX * dX + dY * dY + dZ * dZ);
                if(distanceToTarget > type.targetRange)
                    target = null;
                else
                {
                    float newYaw = 180 + (float)Math.atan2(dZ, dX) * 180 / (float)Math.PI, newPitch = -(float)Math.atan2(dY, Math.sqrt(dX * dX + dZ * dZ)) * 180 / (float)Math.PI, turnSpeed = 0.25F;
                    gunYaw += (newYaw - gunYaw) * turnSpeed;
                    gunPitch += (newPitch - gunPitch) * turnSpeed;
                }
            }
        }
        if(!onGround)
            motionY -= 9.8 / 400;
        motionX *= 0.5;
        motionZ *= 0.5;
        moveEntity(motionX, motionY, motionZ);
        if(riddenByEntity != null && riddenByEntity.isDead)
            riddenByEntity = null;
        if(reloadTimer > 0)
            reloadTimer--;
        else
            for(int i = 0; i < type.numBarrels; i++)
            {
                if(ammo[i] != null && ammo[i].getItemDamage() == ammo[i].getMaxDamage())
                    ammo[i] = null;
                if(ammo[i] == null && riddenByEntity != null && riddenByEntity instanceof EntityPlayer)
                {
                    int slot = findAmmo(((EntityPlayer)riddenByEntity));
                    if(slot >= 0)
                    {
                        ammo[i] = ((EntityPlayer)riddenByEntity).inventory.getStackInSlot(slot);
                        if(!((EntityPlayer)riddenByEntity).capabilities.isCreativeMode)
                            ((EntityPlayer)riddenByEntity).inventory.decrStackSize(slot, 1);
                        reloadTimer = type.reloadTime;
                        PacketPlaySound.sendSoundPacket(posX, posY, posZ, 50, dimension, type.reloadSound, true);
                    }
                }
            }
        if(reloadTimer <= 0 && shootDelay <= 0)
            if(mouseHeld && riddenByEntity != null && riddenByEntity instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer)riddenByEntity;
                for(int j = 0; j < type.numBarrels; j++)
                {
                    if(shootDelay <= 0 && ammo[j] != null && (!type.fireAlternately || type.fireAlternately && currentBarrel == j))
                    {
                        BulletType bullet = BulletType.getBullet(ammo[j].getItem());
                        if(!((EntityPlayer)riddenByEntity).capabilities.isCreativeMode)
                            ammo[j].damageItem(1, player);
                        shootDelay = type.shootDelay;
                        barrelRecoil[j] = type.recoil;
                        worldObj.spawnEntityInWorld(((ItemBullet)ammo[j].getItem()).getEntity(worldObj, rotate(type.barrelX[currentBarrel] / 16 - type.barrelZ[currentBarrel] / 16, type.barrelY[currentBarrel] / 16, type.barrelX[currentBarrel] / 16 + type.barrelZ[currentBarrel] / 16).addVector(posX, posY, posZ), gunYaw + 90, gunPitch, player, type.accuracy, type.damage, ammo[j].getItemDamage(), type));
                        PacketPlaySound.sendSoundPacket(posX, posY, posZ, 50, dimension, type.shootSound, true);
                    }
                }
                currentBarrel = (currentBarrel + 1) % type.numBarrels;
            }
            else if(target != null)
            {
                for(int j = 0; j < type.numBarrels; j++)
                {
                    int ammoSlot = j;
                    if(type.shareAmmo)
                        ammoSlot = 0;
                    if(shootDelay <= 0 && ammo[ammoSlot] != null && (!type.fireAlternately || type.fireAlternately && currentBarrel == ammoSlot))
                    {
                        BulletType bullet = BulletType.getBullet(ammo[ammoSlot].getItem());
                        ammo[ammoSlot].setItemDamage(ammo[ammoSlot].getItemDamage() + 1);
                        shootDelay = type.shootDelay;
                        barrelRecoil[ammoSlot] = type.recoil;
                        worldObj.spawnEntityInWorld(((ItemBullet)ammo[ammoSlot].getItem()).getEntity(worldObj, rotate(type.barrelX[currentBarrel] / 16 - type.barrelZ[currentBarrel] / 16, type.barrelY[currentBarrel] / 16, type.barrelX[currentBarrel] / 16 + type.barrelZ[currentBarrel] / 16).addVector(posX, posY + 1.5F, posZ), gunYaw + 90, gunPitch, placer, type.accuracy, type.damage, ammo[ammoSlot].getItemDamage(), type));
                        PacketPlaySound.sendSoundPacket(posX, posY, posZ, 50, dimension, type.shootSound, true);
                    }
                }
                currentBarrel = (currentBarrel + 1) % type.numBarrels;
            }
        WatchDogs.getPacketHandler().sendToAllAround(new PacketAAGunAngles(this), posX, posY, posZ, 50, dimension);
    }

    public boolean isSentry()
    {
        return type.targetMobs || type.targetPlayers;
    }

    public Entity getValidTarget()
    {
        if(placer == null && placerName != null)
            placer = worldObj.getPlayerEntityByName(placerName);
        for(Object obj : worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(type.targetRange, type.targetRange, type.targetRange)))
        {
            Entity candidateEntity = (Entity)obj;
            if((type.targetMobs && candidateEntity instanceof EntityMob) || (type.targetPlayers && candidateEntity instanceof EntityPlayer))
                if(candidateEntity.getDistanceToEntity(this) < type.targetRange)
                    return candidateEntity;
        }
        return null;
    }

    @Override
    public void setDead()
    {
        super.setDead();
        dropItem(type.getItem(), 1);
        for(ItemStack stack : ammo)
            if(stack != null)
                entityDropItem(stack, 0.5F);
    }

    @Override
    public void updateRiderPosition()
    {
        if(riddenByEntity == null)
            return;
        double x = type.gunnerX / 16, y = type.gunnerY / 16, z = type.gunnerZ / 16, cosYaw = Math.cos((-gunYaw / 180) * Math.PI), sinYaw = Math.sin((-gunYaw / 180) * Math.PI), cosPitch = Math.cos((gunPitch / 180) * Math.PI), sinPitch = Math.sin((gunPitch / 180) * Math.PI), x2 = x * cosYaw + z * sinYaw, z2 = -x * sinYaw + z * cosYaw;
        riddenByEntity.setPosition(posX + x2, posY + y, posZ + z2);
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setString("Type", type.shortName);
        nbttagcompound.setInteger("Health", health);
        nbttagcompound.setFloat("RotationYaw", rotationYaw);
        nbttagcompound.setFloat("RotationPitch", rotationPitch);
        for(int i = 0; i < type.numBarrels; i++)
            if(ammo[i] != null)
                nbttagcompound.setTag("Ammo " + i, ammo[i].writeToNBT(new NBTTagCompound()));
        nbttagcompound.setString("Placer", placer.getCommandSenderName());
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        type = AAGunType.getAAGun(nbttagcompound.getString("Type"));
        initType();
        health = nbttagcompound.getInteger("Health");
        rotationYaw = nbttagcompound.getFloat("RotationYaw");
        rotationPitch = nbttagcompound.getFloat("RotationPitch");
        for(int i = 0; i < type.numBarrels; i++)
            ammo[i] = ItemStack.loadItemStackFromNBT(nbttagcompound.getCompoundTag("Ammo " + i));
        placerName = nbttagcompound.getString("Placer");
    }

    @Override
    public float getShadowSize()
    {
        return 0;
    }

    @Override
    public boolean interactFirst(EntityPlayer entityplayer)
    {
        if(riddenByEntity != null && (riddenByEntity instanceof EntityPlayer) && riddenByEntity != entityplayer)
            return true;
        if(riddenByEntity == entityplayer)
        {
            entityplayer.mountEntity(null);
            return true;
        }
        if(!isSentry())
            entityplayer.mountEntity(this);
        for(int i = 0; i < (type.shareAmmo ? 1 : type.numBarrels); i++)
            if(ammo[i] == null)
            {
                int slot = findAmmo(entityplayer);
                if(slot >= 0)
                {
                    ammo[i] = entityplayer.inventory.getStackInSlot(slot).copy();
                    ammo[i].stackSize = 1;
                    if(!entityplayer.capabilities.isCreativeMode)
                        entityplayer.inventory.decrStackSize(slot, 1);
                    reloadTimer = type.reloadTime;
                    worldObj.playSoundAtEntity(this, type.reloadSound, 1, 1 / (rand.nextFloat() * 0.4F + 0.8F));
                }
            }
        return true;
    }

    public int findAmmo(EntityPlayer player)
    {
        for(int i = 0; i < player.inventory.getSizeInventory(); i++)
            if(type.isAmmo(player.inventory.getStackInSlot(i)))
                return i;
        return -1;
    }

    @Override
    public void writeSpawnData(ByteBuf data)
    {
        ByteBufUtils.writeUTF8String(data, type.shortName);
    }

    @Override
    public void readSpawnData(ByteBuf data)
    {
        try
        {
            type = AAGunType.getAAGun(ByteBufUtils.readUTF8String(data));
            initType();
        }
        catch(Exception e)
        {
            WatchDogs.print("Failed to retreive AA gun type from server.");
            super.setDead();
            e.printStackTrace();
        }
    }

    @Override
    public boolean canRiderInteract()
    {
        return false;
    }

    @Override
    public ItemStack getPickedResult(MovingObjectPosition target)
    {
        return new ItemStack(type.item, 1, 0);
    }
}