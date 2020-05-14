package fr.watchdogs.benjaminloison.common.guns;

import com.flansmod.common.vector.Vector3f;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.driveables.InfoType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public abstract class ItemShootable extends Item
{
    public ShootableType type;

    public ItemShootable(ShootableType t)
    {
        type = t;
        maxStackSize = type.maxStackSize;
        setMaxDamage(type.roundsPerItem);
        GameRegistry.registerItem(this, type.shortName, WatchDogs.MODID);
    }

    public abstract EntityShootable getEntity(World worldObj, Vec3 origin, float yaw, float pitch, double motionX, double motionY, double motionZ, EntityLivingBase shooter, float gunDamage, int itemDamage, InfoType shotFrom);

    public abstract EntityShootable getEntity(World worldObj, Vector3f origin, Vector3f direction, EntityLivingBase shooter, float spread, float damage, float speed, int itemDamage, InfoType shotFrom);

    public abstract EntityShootable getEntity(World worldObj, Vec3 origin, float yaw, float pitch, EntityLivingBase shooter, float spread, float damage, int itemDamage, InfoType shotFrom);

    public abstract EntityShootable getEntity(World worldObj, EntityLivingBase player, float bulletSpread, float damage, float bulletSpeed, boolean b, int itemDamage, InfoType shotFrom);
}