package fr.watchdogs.benjaminloison.common.guns;

import com.flansmod.common.vector.Vector3f;

import fr.watchdogs.benjaminloison.api.RotatedAxes;
import fr.watchdogs.benjaminloison.common.teams.PlayerData;
import fr.watchdogs.benjaminloison.common.teams.PlayerHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class PlayerHitbox
{
    public EntityPlayer player;
    public RotatedAxes axes;
    public Vector3f rP, o, d;
    public EnumHitboxType type;

    public PlayerHitbox(EntityPlayer player, RotatedAxes axes, Vector3f rotationPoint, Vector3f origin, Vector3f dimensions, EnumHitboxType type)
    {
        this.player = player;
        this.axes = axes;
        o = origin;
        d = dimensions;
        this.type = type;
        rP = rotationPoint;
    }

    public void renderHitbox(World world, Vector3f pos)
    {}

    public PlayerBulletHit raytrace(Vector3f origin, Vector3f motion)
    {
        origin = Vector3f.sub(origin, rP, null);
        origin = axes.findGlobalVectorLocally(origin);
        motion = axes.findGlobalVectorLocally(motion);
        if(motion.x != 0F)
        {
            if(origin.x < o.x)
            {
                float intersectTime = (o.x - origin.x) / motion.x, intersectY = origin.y + motion.y * intersectTime, intersectZ = origin.z + motion.z * intersectTime;
                if(intersectY >= o.y && intersectY <= o.y + d.y && intersectZ >= o.z && intersectZ <= o.z + d.z)
                    return new PlayerBulletHit(this, intersectTime);
            }
            else if(origin.x > o.x + d.x)
            {
                float intersectTime = (o.x + d.x - origin.x) / motion.x, intersectY = origin.y + motion.y * intersectTime, intersectZ = origin.z + motion.z * intersectTime;
                if(intersectY >= o.y && intersectY <= o.y + d.y && intersectZ >= o.z && intersectZ <= o.z + d.z)
                    return new PlayerBulletHit(this, intersectTime);
            }
        }
        if(motion.z != 0F)
        {
            if(origin.z < o.z)
            {
                float intersectTime = (o.z - origin.z) / motion.z, intersectX = origin.x + motion.x * intersectTime, intersectY = origin.y + motion.y * intersectTime;
                if(intersectX >= o.x && intersectX <= o.x + d.x && intersectY >= o.y && intersectY <= o.y + d.y)
                    return new PlayerBulletHit(this, intersectTime);
            }
            else if(origin.z > o.z + d.z)
            {
                float intersectTime = (o.z + d.z - origin.z) / motion.z, intersectX = origin.x + motion.x * intersectTime, intersectY = origin.y + motion.y * intersectTime;
                if(intersectX >= o.x && intersectX <= o.x + d.x && intersectY >= o.y && intersectY <= o.y + d.y)
                    return new PlayerBulletHit(this, intersectTime);
            }
        }
        if(motion.y != 0F)
        {
            if(origin.y < o.y)
            {
                float intersectTime = (o.y - origin.y) / motion.y, intersectX = origin.x + motion.x * intersectTime, intersectZ = origin.z + motion.z * intersectTime;
                if(intersectX >= o.x && intersectX <= o.x + d.x && intersectZ >= o.z && intersectZ <= o.z + d.z)
                    return new PlayerBulletHit(this, intersectTime);
            }
            else if(origin.y > o.y + d.y)
            {
                float intersectTime = (o.y + d.y - origin.y) / motion.y, intersectX = origin.x + motion.x * intersectTime, intersectZ = origin.z + motion.z * intersectTime;
                if(intersectX >= o.x && intersectX <= o.x + d.x && intersectZ >= o.z && intersectZ <= o.z + d.z)
                    return new PlayerBulletHit(this, intersectTime);
            }
        }
        return null;
    }

    public float hitByBullet(EntityBullet bullet, float penetratingPower)
    {
        if(bullet.type.setEntitiesOnFire)
            player.setFire(20);
        for(PotionEffect effect : bullet.type.hitEffects)
            player.addPotionEffect(new PotionEffect(effect));
        float damageModifier = bullet.type.penetratingPower < 0.1F ? penetratingPower / bullet.type.penetratingPower : 1;
        switch(type)
        {
            case BODY:
                break;
            case HEAD:
                damageModifier *= 2F;
                break;
            case LEFTARM:
                damageModifier *= 0.6F;
                break;
            case RIGHTARM:
                damageModifier *= 0.6F;
                break;
            case LEFTITEM:
                break;
            case RIGHTITEM:
                break;
            default:
                break;
        }
        switch(type)
        {
            case BODY:
            case HEAD:
            case LEFTARM:
            case RIGHTARM:
            {
                float hitDamage = bullet.damage * bullet.type.damageVsLiving * damageModifier;
                DamageSource damagesource = bullet.owner == null ? DamageSource.generic : bullet.getBulletDamage(type == EnumHitboxType.HEAD);
                if(player.attackEntityFrom(damagesource, hitDamage))
                {
                    player.arrowHitTimer++;
                    player.hurtResistantTime = player.maxHurtResistantTime / 2;
                }
                return penetratingPower - 1;
            }
            case RIGHTITEM:
            {
                ItemStack currentStack = player.getCurrentEquippedItem();
                if(currentStack != null && currentStack.getItem() instanceof ItemGun)
                {
                    GunType gunType = ((ItemGun)currentStack.getItem()).type;
                    return penetratingPower - gunType.shieldDamageAbsorption;
                }
                else
                    return penetratingPower;
            }
            case LEFTITEM:
            {
                PlayerData data = PlayerHandler.getPlayerData(player);
                if(data.offHandGunSlot != 0)
                {
                    ItemStack leftHandStack = null;
                    leftHandStack = player.inventory.getStackInSlot(data.offHandGunSlot - 1);
                    if(leftHandStack != null && leftHandStack.getItem() instanceof ItemGun)
                    {
                        GunType leftGunType = ((ItemGun)leftHandStack.getItem()).type;
                        return penetratingPower - leftGunType.shieldDamageAbsorption;
                    }
                }
            }
            default:
                return penetratingPower;
        }
    }
}