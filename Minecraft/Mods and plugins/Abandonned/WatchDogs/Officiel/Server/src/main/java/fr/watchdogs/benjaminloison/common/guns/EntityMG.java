package fr.watchdogs.benjaminloison.common.guns;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.common.teams.PlayerHandler;
import fr.watchdogs.benjaminloison.packets.PacketMGMount;
import fr.watchdogs.benjaminloison.packets.PacketPlaySound;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityMG extends Entity implements IEntityAdditionalSpawnData
{
    public int ticksSinceUsed, reloadTimer, soundDelay, shootDelay, blockX, blockY, blockZ, direction;
    public GunType type;
    public ItemStack ammo;
    public static List<EntityMG> mgs = new ArrayList<EntityMG>();
    public EntityPlayer gunner;
    public boolean isShooting, wasShooting;

    public EntityMG(World world)
    {
        super(world);
        setSize(1, 1);
        ignoreFrustumCheck = true;
    }

    public EntityMG(World world, int x, int y, int z, int dir, GunType gunType)
    {
        super(world);
        setSize(1, 1);
        blockX = x;
        blockY = y;
        blockZ = z;
        prevPosX = x + 0.5;
        prevPosY = y;
        prevPosZ = z + 0.5;
        setPosition(x + 0.5, y, z + 0.5);
        direction = dir;
        rotationYaw = 0;
        rotationPitch = -60;
        type = gunType;
        ignoreFrustumCheck = true;
        mgs.add(this);
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
        ticksSinceUsed++;
        if(FileAPI.configNumber("life.mg") > 0 && ticksSinceUsed > FileAPI.configNumber("life.mg") * 20)
            setDead();
        if(worldObj.getBlock(blockX, blockY - 1, blockZ) == null)
            setDead();
        prevRotationYaw = rotationYaw;
        prevRotationPitch = rotationPitch;
        if(gunner != null)
        {
            ticksSinceUsed = 0;
            rotationYaw = gunner.rotationYaw - direction * 90;
            for(; rotationYaw < -180; rotationYaw += 360)
            {}
            for(; rotationYaw > 180; rotationYaw -= 360)
            {}
            rotationPitch = gunner.rotationPitch;
            if(rotationYaw > type.sideViewLimit)
                prevRotationYaw = rotationYaw = type.sideViewLimit;
            if(rotationYaw < -type.sideViewLimit)
                prevRotationYaw = rotationYaw = -type.sideViewLimit;
            float angle = direction * 90 + rotationYaw;
            gunner.setPosition((blockX + 0.5 + (type.standBackDist * Math.sin(angle * (float)Math.PI / 180))), blockY + gunner.getYOffset() - 0.5, (blockZ + 0.5 + -(type.standBackDist * Math.cos(angle * (float)Math.PI / 180))));
        }
        else
            rotationPitch--;
        if(rotationPitch < type.topViewLimit)
            rotationPitch = type.topViewLimit;
        if(rotationPitch > type.bottomViewLimit)
            rotationPitch = type.bottomViewLimit;
        if(shootDelay > 0)
            shootDelay--;
        if(reloadTimer > 0)
            reloadTimer--;
        if(ammo != null && ammo.getItemDamage() == ammo.getMaxDamage())
            ammo = null;
        if(ammo == null && gunner != null)
        {
            int slot = findAmmo(gunner);
            if(slot >= 0)
            {
                ammo = gunner.inventory.getStackInSlot(slot);
                if(!gunner.capabilities.isCreativeMode)
                    gunner.inventory.setInventorySlotContents(slot, null);
                reloadTimer = type.reloadTime;
                PacketPlaySound.sendSoundPacket(posX, posY, posZ, WatchDogs.soundRange, dimension, type.reloadSound, false);
            }
        }
        if(isShooting)
        {
            if(gunner == null || gunner.isDead)
                isShooting = false;
            if(ammo == null || reloadTimer > 0 || shootDelay > 0)
                return;
            BulletType bullet = BulletType.getBullet(ammo.getItem());
            if(gunner != null && !gunner.capabilities.isCreativeMode)
                ammo.damageItem(1, gunner);
            shootDelay = type.shootDelay;
            worldObj.spawnEntityInWorld(((ItemBullet)ammo.getItem()).getEntity(worldObj, Vec3.createVectorHelper(blockX + 0.5, blockY + type.pivotHeight, blockZ + 0.5), (direction * 90 + rotationYaw), rotationPitch, gunner, type.bulletSpread, type.damage, ammo.getItemDamage(), type));
            if(soundDelay <= 0)
            {
                soundDelay = type.shootSoundLength;
                PacketPlaySound.sendSoundPacket(posX, posY, posZ, WatchDogs.soundRange, dimension, type.shootSound, type.distortSound);
            }
        }
        if(soundDelay > 0)
            soundDelay--;
    }

    public void mouseHeld(boolean held)
    {
        isShooting = held;
    }

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float i)
    {
        if(damagesource.damageType.equals("player"))
        {
            Entity player = damagesource.getEntity();
            if(player == gunner)
            {
                if(type.mode == EnumFireMode.FULLAUTO)
                    return true;
                if(ammo == null || reloadTimer > 0 || shootDelay > 0)
                    return true;
                BulletType bullet = BulletType.getBullet(ammo.getItem());
                if(gunner != null && !gunner.capabilities.isCreativeMode)
                    ammo.damageItem(1, (EntityLiving)player);
                shootDelay = type.shootDelay;
                worldObj.spawnEntityInWorld(((ItemBullet)ammo.getItem()).getEntity(worldObj, (EntityLivingBase)player, type.bulletSpread, type.damage, type.bulletSpeed, false, ammo.getItemDamage(), type));
                if(soundDelay <= 0)
                {
                    float distortion = type.distortSound ? 1 / (rand.nextFloat() * 0.4F + 0.8F) : 1;
                    PacketPlaySound.sendSoundPacket(posX, posY, posZ, WatchDogs.soundRange, dimension, type.shootSound, type.distortSound);
                    soundDelay = type.shootSoundLength;
                }
            }
            else if(gunner != null)
                return gunner.attackEntityFrom(damagesource, i);
            else if(FileAPI.configBoolean("weapons.break.glass"))
                setDead();
        }
        return true;
    }

    @Override
    public boolean interactFirst(EntityPlayer player)
    {
        if(gunner != null && (gunner instanceof EntityPlayer) && gunner != player)
            return true;
        if(gunner == player)
        {
            mountGun(player, false);
            WatchDogs.getPacketHandler().sendToAllAround(new PacketMGMount(player, this, false), posX, posY, posZ, WatchDogs.driveableUpdateRange, dimension);
            return true;
        }
        if(PlayerHandler.getPlayerData(player).mountingGun != null && !PlayerHandler.getPlayerData(player).mountingGun.isDead)
        {
            PlayerHandler.getPlayerData(player).mountingGun.mountGun(player, false);
            return true;
        }
        mountGun(player, true);
        WatchDogs.getPacketHandler().sendToAllAround(new PacketMGMount(player, this, true), posX, posY, posZ, WatchDogs.driveableUpdateRange, dimension);
        if(ammo == null)
        {
            int slot = findAmmo(player);
            if(slot >= 0)
            {
                ammo = player.inventory.getStackInSlot(slot);
                player.inventory.setInventorySlotContents(slot, null);
                reloadTimer = type.reloadTime;
                worldObj.playSoundAtEntity(this, type.reloadSound, 1, 1 / (rand.nextFloat() * 0.4F + 0.8F));
            }
        }
        return true;
    }

    public void mountGun(EntityPlayer player, boolean mounting)
    {
        if(player == null)
            return;
        Side side = Side.SERVER;
        if(PlayerHandler.getPlayerData(player, side) == null)
            return;
        if(mounting)
        {
            gunner = player;
            PlayerHandler.getPlayerData(player, side).mountingGun = this;
        }
        else
        {
            PlayerHandler.getPlayerData(player, side).mountingGun = null;
            gunner = null;
        }
    }

    public int findAmmo(EntityPlayer player)
    {
        for(int i = 0; i < player.inventory.getSizeInventory(); i++)
            if(type.isAmmo(player.inventory.getStackInSlot(i)))
                return i;
        return -1;
    }

    @Override
    public void setDead()
    {
        dropItem(type.getItem(), 1);
        if(ammo != null)
            entityDropItem(ammo, 0.5F);
        if(gunner != null && PlayerHandler.getPlayerData(gunner) != null)
            PlayerHandler.getPlayerData(gunner).mountingGun = null;
        super.setDead();
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setString("Type", type.shortName);
        if(ammo != null)
            nbttagcompound.setTag("Ammo", ammo.writeToNBT(new NBTTagCompound()));
        nbttagcompound.setInteger("BlockX", blockX);
        nbttagcompound.setInteger("BlockY", blockY);
        nbttagcompound.setInteger("BlockZ", blockZ);
        nbttagcompound.setByte("Dir", (byte)direction);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        type = GunType.getGun(nbttagcompound.getString("Type"));
        blockX = nbttagcompound.getInteger("BlockX");
        blockY = nbttagcompound.getInteger("BlockY");
        blockZ = nbttagcompound.getInteger("BlockZ");
        direction = nbttagcompound.getByte("Dir");
        ammo = ItemStack.loadItemStackFromNBT(nbttagcompound.getCompoundTag("Ammo"));
    }

    @Override
    protected void entityInit()
    {}

    @Override
    public void writeSpawnData(ByteBuf data)
    {
        ByteBufUtils.writeUTF8String(data, type.shortName);
        data.writeInt(direction);
        data.writeInt(blockX);
        data.writeInt(blockY);
        data.writeInt(blockZ);
        ByteBufUtils.writeItemStack(data, ammo);
    }

    @Override
    public void readSpawnData(ByteBuf data)
    {
        try
        {
            type = GunType.getGun(ByteBufUtils.readUTF8String(data));
            direction = data.readInt();
            blockX = data.readInt();
            blockY = data.readInt();
            blockZ = data.readInt();
            ammo = ByteBufUtils.readItemStack(data);
        }
        catch(Exception e)
        {
            WatchDogs.print("Failed to retreive gun type from server.");
            super.setDead();
            e.printStackTrace();
        }
    }

    @Override
    public ItemStack getPickedResult(MovingObjectPosition target)
    {
        return new ItemStack(type.item, 1, 0);
    }
}