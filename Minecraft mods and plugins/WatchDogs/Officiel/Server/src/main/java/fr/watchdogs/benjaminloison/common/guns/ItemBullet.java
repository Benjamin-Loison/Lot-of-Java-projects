package fr.watchdogs.benjaminloison.common.guns;

import com.flansmod.common.vector.Vector3f;

import fr.watchdogs.benjaminloison.driveables.IFlanItem;
import fr.watchdogs.benjaminloison.driveables.InfoType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemBullet extends ItemShootable implements IFlanItem
{
    public BulletType type;

    public ItemBullet(BulletType infoType)
    {
        super(infoType);
        type = infoType;
        setMaxStackSize(type.maxStackSize);
        setHasSubtypes(true);
        type.item = this;
    }

    public EntityShootable getEntity(World worldObj, Vec3 origin, float yaw, float pitch, double motionX, double motionY, double motionZ, EntityLivingBase shooter, float gunDamage, int itemDamage, InfoType shotFrom)
    {
        return new EntityBullet(worldObj, origin, yaw, pitch, motionX, motionY, motionZ, shooter, gunDamage, this.type, shotFrom);
    }

    public EntityShootable getEntity(World worldObj, Vector3f origin, Vector3f direction, EntityLivingBase shooter, float spread, float damage, float speed, int itemDamage, InfoType shotFrom)
    {
        return new EntityBullet(worldObj, origin, direction, shooter, spread, damage, this.type, speed, shotFrom);
    }

    public EntityShootable getEntity(World worldObj, Vec3 origin, float yaw, float pitch, EntityLivingBase shooter, float spread, float damage, int itemDamage, InfoType shotFrom)
    {
        return new EntityBullet(worldObj, origin, yaw, pitch, shooter, spread, damage, this.type, shotFrom);
    }

    public EntityShootable getEntity(World worldObj, EntityLivingBase player, float bulletSpread, float damage, float bulletSpeed, boolean b, int itemDamage, InfoType shotFrom)
    {
        return new EntityBullet(worldObj, player, bulletSpread, damage, this.type, bulletSpeed, b, shotFrom);
    }

    @Override
    public InfoType getInfoType()
    {
        return type;
    }
}