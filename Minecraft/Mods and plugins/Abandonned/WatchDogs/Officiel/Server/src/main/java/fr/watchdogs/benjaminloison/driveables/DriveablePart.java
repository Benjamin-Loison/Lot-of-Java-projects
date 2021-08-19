package fr.watchdogs.benjaminloison.driveables;

import com.flansmod.common.vector.Vector3f;

import fr.watchdogs.benjaminloison.common.guns.DriveableHit;
import fr.watchdogs.benjaminloison.common.guns.EntityBullet;
import net.minecraft.nbt.NBTTagCompound;

public class DriveablePart
{
    public EnumDriveablePart type;
    public CollisionBox box;
    public int maxHealth, health, fireTime;
    public boolean onFire, dead;

    public DriveablePart(EnumDriveablePart e, CollisionBox b)
    {
        type = e;
        box = b;
        health = maxHealth = b == null ? 0 : b.health;
    }

    public void update(EntityDriveable driveable)
    {
        if(fireTime > 0)
            fireTime--;
        if(fireTime == 0)
            onFire = false;
        if(onFire)
            health--;
        if(health <= 0 && maxHealth > 0)
            dead = true;
    }

    public void writeToNBT(NBTTagCompound tags)
    {
        tags.setInteger(type.getShortName() + "_Health", health);
        tags.setBoolean(type.getShortName() + "_Fire", onFire);
    }

    public void readFromNBT(NBTTagCompound tags)
    {
        if(!tags.hasKey(type.getShortName() + "_Health"))
        {
            health = maxHealth;
            onFire = false;
            return;
        }
        health = tags.getInteger(type.getShortName() + "_Health");
        onFire = tags.getBoolean(type.getShortName() + "_Fire");
    }

    public float smashIntoGround(EntityDriveable driveable, float damage)
    {
        if(box == null || dead)
            return 0;
        if(!driveable.canHitPart(type))
            return 0;
        if(maxHealth == 0)
            return damage;
        health -= (int)(damage / 2F);
        return damage / 2F;
    }

    public DriveableHit rayTrace(EntityDriveable driveable, Vector3f origin, Vector3f motion)
    {
        if(box == null || health <= 0 || dead)
            return null;
        if(!driveable.canHitPart(type))
            return null;
        if(motion.x != 0)
            if(origin.x < box.x)
            {
                float intersectTime = (box.x - origin.x) / motion.x, intersectY = origin.y + motion.y * intersectTime, intersectZ = origin.z + motion.z * intersectTime;
                if(intersectY >= box.y && intersectY <= box.y + box.h && intersectZ >= box.z && intersectZ <= box.z + box.d)
                    return new DriveableHit(driveable, type, intersectTime);
            }
            else if(origin.x > box.x + box.w)
            {
                float intersectTime = (box.x + box.w - origin.x) / motion.x, intersectY = origin.y + motion.y * intersectTime, intersectZ = origin.z + motion.z * intersectTime;
                if(intersectY >= box.y && intersectY <= box.y + box.h && intersectZ >= box.z && intersectZ <= box.z + box.d)
                    return new DriveableHit(driveable, type, intersectTime);
            }
        if(motion.z != 0)
            if(origin.z < box.z)
            {
                float intersectTime = (box.z - origin.z) / motion.z, intersectX = origin.x + motion.x * intersectTime, intersectY = origin.y + motion.y * intersectTime;
                if(intersectX >= box.x && intersectX <= box.x + box.w && intersectY >= box.y && intersectY <= box.y + box.h)
                    return new DriveableHit(driveable, type, intersectTime);
            }
            else if(origin.z > box.z + box.d)
            {
                float intersectTime = (box.z + box.d - origin.z) / motion.z, intersectX = origin.x + motion.x * intersectTime, intersectY = origin.y + motion.y * intersectTime;
                if(intersectX >= box.x && intersectX <= box.x + box.w && intersectY >= box.y && intersectY <= box.y + box.h)
                    return new DriveableHit(driveable, type, intersectTime);
            }
        if(motion.y != 0)
            if(origin.y < box.y)
            {
                float intersectTime = (box.y - origin.y) / motion.y, intersectX = origin.x + motion.x * intersectTime, intersectZ = origin.z + motion.z * intersectTime;
                if(intersectX >= box.x && intersectX <= box.x + box.w && intersectZ >= box.z && intersectZ <= box.z + box.d)
                    return new DriveableHit(driveable, type, intersectTime);
            }
            else if(origin.y > box.y + box.h)
            {
                float intersectTime = (box.y + box.h - origin.y) / motion.y, intersectX = origin.x + motion.x * intersectTime, intersectZ = origin.z + motion.z * intersectTime;
                if(intersectX >= box.x && intersectX <= box.x + box.w && intersectZ >= box.z && intersectZ <= box.z + box.d)
                    return new DriveableHit(driveable, type, intersectTime);
            }
        return null;
    }

    public void hitByBullet(EntityBullet bullet)
    {
        if(bullet != null)
        {
            health -= bullet.damage * bullet.type.damageVsDriveable;
            if(bullet.type.setEntitiesOnFire)
            {
                fireTime = 20;
                onFire = true;
            }
        }
    }

    private boolean coordIsEntering(float start, float end, float min, float max)
    {
        if(start < min && end >= min)
            return true;
        if(start > max && end <= max)
            return true;
        return false;
    }

    private boolean coordIsIn(float start, float end, float min, float max)
    {
        if(start >= min && start <= max)
            return true;
        if(end >= min && end <= max)
            return true;
        if(start < min && end > max)
            return true;
        if(end < min && start > max)
            return true;
        return false;
    }

    public boolean attack(float damage, boolean fireDamage)
    {
        health -= damage;
        if(fireDamage)
        {
            fireTime = 20;
            onFire = true;
        }
        return health <= 0;
    }
}