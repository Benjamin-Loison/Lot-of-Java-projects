package fr.watchdogs.benjaminloison.driveables;

import java.util.List;

import com.flansmod.common.vector.Vector3f;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import fr.watchdogs.benjaminloison.api.IControllable;
import fr.watchdogs.benjaminloison.api.RotatedAxes;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.common.guns.EnumFireMode;
import fr.watchdogs.benjaminloison.common.guns.GunType;
import fr.watchdogs.benjaminloison.common.guns.ItemShootable;
import fr.watchdogs.benjaminloison.common.guns.ItemTool;
import fr.watchdogs.benjaminloison.common.guns.ShootableType;
import fr.watchdogs.benjaminloison.packets.PacketPlaySound;
import fr.watchdogs.benjaminloison.packets.PacketSeatUpdates;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemLead;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntitySeat extends Entity implements IControllable, IEntityAdditionalSpawnData
{
    private int driveableID, seatID;
    public EntityDriveable driveable;
    public Seat seatInfo;
    public boolean driver;
    public RotatedAxes looking, prevLooking;
    public int gunDelay, soundDelay;
    public float minigunSpeed, minigunAngle, playerYaw, playerPitch, prevPlayerYaw, prevPlayerPitch;
    private double playerPosX, playerPosY, playerPosZ, prevPlayerPosX, prevPlayerPosY, prevPlayerPosZ;
    private boolean shooting;

    public EntitySeat(World world)
    {
        super(world);
        setSize(1, 1);
        prevLooking = new RotatedAxes();
        looking = new RotatedAxes();
    }

    public EntitySeat(World world, EntityDriveable d, int id)
    {
        this(world);
        driveable = d;
        driveableID = d.getEntityId();
        seatInfo = driveable.getDriveableType().seats[id];
        driver = id == 0;
        setPosition(d.posX, d.posY, d.posZ);
        playerPosX = prevPlayerPosX = posX;
        playerPosY = prevPlayerPosY = posY;
        playerPosZ = prevPlayerPosZ = posZ;
        looking.setAngles((seatInfo.minYaw + seatInfo.maxYaw) / 2, 0, 0);
    }

    @Override
    public void setPositionAndRotation2(double x, double y, double z, float yaw, float pitch, int i)
    {}

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        if(gunDelay > 0)
            gunDelay--;
        if(soundDelay > 0)
            soundDelay--;
        minigunSpeed *= 0.95F;
        minigunAngle += minigunSpeed;
    }

    public void updatePosition()
    {
        prevPlayerPosX = playerPosX;
        prevPlayerPosY = playerPosY;
        prevPlayerPosZ = playerPosZ;
        prevPlayerYaw = playerYaw;
        prevPlayerPitch = playerPitch;
        Vector3f localPosition = new Vector3f(seatInfo.x / 16, seatInfo.y / 16, seatInfo.z / 16);
        if(driveable != null && driveable.seats != null && driveable.seats[0] != null && driveable.seats[0].looking != null)
        {
            RotatedAxes yawOnlyLooking = new RotatedAxes(driveable.seats[0].looking.getYaw(), 0, 0);
            Vector3f rotatedOffset = yawOnlyLooking.findLocalVectorGlobally(seatInfo.rotatedOffset);
            Vector3f.add(localPosition, new Vector3f(rotatedOffset.x, 0, rotatedOffset.z), localPosition);
        }
        Vector3f relativePosition = driveable.axes.findLocalVectorGlobally(localPosition);
        setPosition(driveable.posX + relativePosition.x, driveable.posY + relativePosition.y, driveable.posZ + relativePosition.z);
        if(riddenByEntity != null)
        {
            DriveableType type = driveable.getDriveableType();
            Vec3 yOffset = driveable.rotate(0, riddenByEntity.getYOffset(), 0).toVec3();
            playerPosX = posX + yOffset.xCoord;
            playerPosY = posY + yOffset.yCoord;
            playerPosZ = posZ + yOffset.zCoord;
            riddenByEntity.lastTickPosX = riddenByEntity.prevPosX = prevPlayerPosX;
            riddenByEntity.lastTickPosY = riddenByEntity.prevPosY = prevPlayerPosY;
            riddenByEntity.lastTickPosZ = riddenByEntity.prevPosZ = prevPlayerPosZ;
            riddenByEntity.setPosition(playerPosX, playerPosY, playerPosZ);
            RotatedAxes globalLookAxes = driveable.axes.findLocalAxesGlobally(looking);
            playerYaw = -90 + globalLookAxes.getYaw();
            playerPitch = globalLookAxes.getPitch();
            double dYaw = playerYaw - prevPlayerYaw;
            if(dYaw > 180)
                prevPlayerYaw += 360;
            if(dYaw < -180)
                prevPlayerYaw -= 360;
            if(riddenByEntity instanceof EntityPlayer)
            {
                riddenByEntity.prevRotationYaw = prevPlayerYaw;
                riddenByEntity.prevRotationPitch = prevPlayerPitch;
                riddenByEntity.rotationYaw = playerYaw;
                riddenByEntity.rotationPitch = playerPitch;
            }
        }
    }

    @Override
    public void updateRiderPosition()
    {
        if(riddenByEntity instanceof EntityPlayer)
        {
            riddenByEntity.rotationYaw = playerYaw;
            riddenByEntity.rotationPitch = playerPitch;
            riddenByEntity.prevRotationYaw = prevPlayerYaw;
            riddenByEntity.prevRotationPitch = prevPlayerPitch;
        }
        if(riddenByEntity == null)
            return;
        riddenByEntity.lastTickPosX = riddenByEntity.prevPosX = prevPlayerPosX;
        riddenByEntity.lastTickPosY = riddenByEntity.prevPosY = prevPlayerPosY;
        riddenByEntity.lastTickPosZ = riddenByEntity.prevPosZ = prevPlayerPosZ;
    }

    @Override
    public boolean canBeCollidedWith()
    {
        return !isDead;
    }

    @Override
    protected void entityInit()
    {}

    @Override
    public float getShadowSize()
    {
        return 4;
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tags)
    {}

    @Override
    protected void writeEntityToNBT(NBTTagCompound tags)
    {}

    @Override
    public boolean writeToNBTOptional(NBTTagCompound tags)
    {
        return false;
    }

    @Override
    public boolean writeMountToNBT(NBTTagCompound tags)
    {
        return false;
    }

    @Override
    public void onMouseMoved(int deltaX, int deltaY)
    {
        prevLooking = looking.clone();
        if(driver)
            driveable.onMouseMoved(deltaX, deltaY);
        if(!driver || !driveable.hasMouseControlMode())
        {
            float lookSpeed = 4, newPitch = looking.getPitch() - deltaY / lookSpeed * Minecraft.getMinecraft().gameSettings.mouseSensitivity;
            if(newPitch > -seatInfo.minPitch)
                newPitch = -seatInfo.minPitch;
            if(newPitch < -seatInfo.maxPitch)
                newPitch = -seatInfo.maxPitch;
            float newYaw = looking.getYaw() + deltaX / lookSpeed * Minecraft.getMinecraft().gameSettings.mouseSensitivity, otherNewYaw = newYaw - 360;
            if(newYaw < 0)
                otherNewYaw = newYaw + 360;
            if(!((newYaw >= seatInfo.minYaw && newYaw <= seatInfo.maxYaw) || (otherNewYaw >= seatInfo.minYaw && otherNewYaw <= seatInfo.maxYaw)))
            {
                float newYawDistFromRange = Math.min(Math.abs(newYaw - seatInfo.minYaw), Math.abs(newYaw - seatInfo.maxYaw)), otherNewYawDistFromRange = Math.min(Math.abs(otherNewYaw - seatInfo.minYaw), Math.abs(otherNewYaw - seatInfo.maxYaw));
                if(newYawDistFromRange <= otherNewYawDistFromRange)
                {
                    if(newYaw > seatInfo.maxYaw)
                        newYaw = seatInfo.maxYaw;
                    if(newYaw < seatInfo.minYaw)
                        newYaw = seatInfo.minYaw;
                }
                else
                {
                    if(otherNewYaw > seatInfo.maxYaw)
                        otherNewYaw = seatInfo.maxYaw;
                    if(otherNewYaw < seatInfo.minYaw)
                        otherNewYaw = seatInfo.minYaw;
                    if(newYaw < 0)
                        newYaw = otherNewYaw - 360;
                    else
                        newYaw = otherNewYaw + 360;
                }
            }
            looking.setAngles(newYaw, newPitch, 0);
            WatchDogs.getPacketHandler().sendToServer(new PacketSeatUpdates(this));
        }
    }

    @Override
    public void updateKeyHeldState(int key, boolean held)
    {
        if(driver)
            driveable.updateKeyHeldState(key, held);
        else if(key == 9)
            shooting = held;
    }

    @Override
    public boolean pressKey(int key, EntityPlayer player)
    {
        if(driver)
            return driveable.pressKey(key, player);
        if(key == 6 && riddenByEntity != null && driveable.rideable)
            riddenByEntity.mountEntity(null);
        if(key == 9)
        {
            GunType gun = seatInfo.gunType;
            minigunSpeed += 0.1;
            if(gun != null && gun.mode != EnumFireMode.MINIGUN || minigunSpeed > 2)
            {
                if(gunDelay <= 0)
                {
                    ItemStack bulletItemStack = driveable.getDriveableData().ammo[seatInfo.gunnerID];
                    if(gun != null && bulletItemStack != null && bulletItemStack.getItem() instanceof ItemShootable)
                    {
                        ShootableType bullet = ((ItemShootable)bulletItemStack.getItem()).type;
                        if(gun.isAmmo(bullet))
                        {
                            Vector3f gunOrigin = Vector3f.add(driveable.axes.findLocalVectorGlobally(seatInfo.gunOrigin), new Vector3f(driveable.posX, driveable.posY, driveable.posZ), null);
                            RotatedAxes globalLookAxes = driveable.axes.findLocalAxesGlobally(looking);
                            Vector3f shootVec = driveable.axes.findLocalVectorGlobally(looking.getXAxis()), yOffset = driveable.axes.findLocalVectorGlobally(new Vector3f(0, (float)player.getMountedYOffset(), 0));
                            worldObj.spawnEntityInWorld(((ItemShootable)bulletItemStack.getItem()).getEntity(worldObj, Vector3f.add(yOffset, new Vector3f(gunOrigin.x, gunOrigin.y, gunOrigin.z), null), shootVec, (EntityLivingBase)riddenByEntity, gun.bulletSpread, gun.damage, gun.bulletSpeed, bulletItemStack.getItemDamage(), driveable.getDriveableType()));
                            if(soundDelay <= 0)
                            {
                                PacketPlaySound.sendSoundPacket(posX, posY, posZ, WatchDogs.soundRange, dimension, gun.shootSound, false);
                                soundDelay = gun.shootSoundLength;
                            }
                            int damage = bulletItemStack.getItemDamage();
                            bulletItemStack.setItemDamage(damage + 1);
                            if(damage + 1 == bulletItemStack.getMaxDamage())
                            {
                                bulletItemStack.setItemDamage(0);
                                if(!((EntityPlayer)riddenByEntity).capabilities.isCreativeMode)
                                    driveable.getDriveableData().decrStackSize(3 + seatID, 1);
                            }
                            gunDelay = gun.shootDelay;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean interactFirst(EntityPlayer entityplayer)
    {
        if(isDead)
            return false;
        ItemStack currentItem = entityplayer.getCurrentEquippedItem();
        if(currentItem != null && currentItem.getItem() instanceof ItemTool && ((ItemTool)currentItem.getItem()).type.healDriveables)
            return true;
        if(currentItem != null && currentItem.getItem() instanceof ItemLead)
        {
            if(riddenByEntity != null && riddenByEntity instanceof EntityLiving && !(riddenByEntity instanceof EntityPlayer))
            {
                riddenByEntity.mountEntity(null);
                ((EntityLiving)riddenByEntity).setLeashedToEntity(entityplayer, true);
                return true;
            }
            double checkRange = 10;
            List nearbyMobs = worldObj.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(posX - checkRange, posY - checkRange, posZ - checkRange, posX + checkRange, posY + checkRange, posZ + checkRange));
            for(Object obj : nearbyMobs)
            {
                EntityLiving entity = (EntityLiving)obj;
                if(entity.getLeashed() && entity.getLeashedToEntity() == entityplayer)
                {
                    looking.setAngles(-entity.rotationYaw, entity.rotationPitch, 0);
                    entity.clearLeashed(true, !entityplayer.capabilities.isCreativeMode);
                }
            }
            return true;
        }
        if(riddenByEntity == null && driveable.rideable)
            entityplayer.mountEntity(this);
        return false;
    }

    @Override
    public Entity getControllingEntity()
    {
        return riddenByEntity;
    }

    @Override
    public boolean isDead()
    {
        return isDead;
    }

    @Override
    public void setDead()
    {
        super.setDead();
    }

    @Override
    public ItemStack getPickedResult(MovingObjectPosition target)
    {
        return driveable.getPickedResult(target);
    }

    @Override
    public float getCameraDistance()
    {
        return seatID == 0 ? driveable.getDriveableType().cameraDistance : 5F;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float f)
    {
        return driveable.attackEntityFrom(source, f);
    }

    @Override
    public void writeSpawnData(ByteBuf data)
    {
        data.writeInt(driveableID);
        data.writeInt(seatInfo.id);
    }

    @Override
    public void readSpawnData(ByteBuf data)
    {
        driveableID = data.readInt();
        driveable = (EntityDriveable)worldObj.getEntityByID(driveableID);
        seatID = data.readInt();
        driver = seatID == 0;
        if(driveable != null)
        {
            seatInfo = driveable.getDriveableType().seats[seatID];
            looking.setAngles((seatInfo.minYaw + seatInfo.maxYaw) / 2, 0, 0);
            playerPosX = prevPlayerPosX = posX = driveable.posX;
            playerPosY = prevPlayerPosY = posY = driveable.posY;
            playerPosZ = prevPlayerPosZ = posZ = driveable.posZ;
            setPosition(posX, posY, posZ);
        }
    }

    public float getMinigunSpeed()
    {
        return minigunSpeed;
    }

    @Override
    public float getPlayerRoll()
    {
        return 0;
    }
}