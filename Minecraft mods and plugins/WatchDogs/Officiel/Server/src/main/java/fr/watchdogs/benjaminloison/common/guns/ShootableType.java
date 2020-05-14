package fr.watchdogs.benjaminloison.common.guns;

import java.util.HashMap;

import fr.watchdogs.benjaminloison.driveables.InfoType;
import fr.watchdogs.benjaminloison.driveables.TypeFile;

public class ShootableType extends InfoType
{
    public boolean explodeOnImpact, explosionBreaksBlocks, breaksGlass, trailParticles;
    public String trailParticleType = "smoke", dropItemOnDetonate, detonateSound = "", dropItemOnReload, dropItemOnShoot, dropItemOnHit;
    public int roundsPerItem = 1, maxStackSize = 1, damageVsLiving = 1, damageVsDriveable = 1, fuse, despawnTime;
    public float fallSpeed = 1, throwSpeed = 1, hitBoxSize = 0.5F, fireRadius, explosionRadius;
    public static HashMap<String, ShootableType> shootables = new HashMap<String, ShootableType>();

    public ShootableType(TypeFile file)
    {
        super(file);
    }

    @Override
    public void postRead(TypeFile file)
    {
        shootables.put(shortName, this);
    }

    @Override
    protected void read(String[] split, TypeFile file)
    {
        super.read(split, file);
        try
        {
            if(split[0].equals("StackSize") || split[0].equals("MaxStackSize"))
                maxStackSize = Integer.parseInt(split[1]);
            else if(split[0].equals("DropItemOnShoot"))
                dropItemOnShoot = split[1];
            else if(split[0].equals("DropItemOnReload"))
                dropItemOnReload = split[1];
            else if(split[0].equals("DropItemOnHit"))
                dropItemOnHit = split[1];
            else if(split[0].equals("RoundsPerItem"))
                roundsPerItem = Integer.parseInt(split[1]);
            else if(split[0].equals("FallSpeed"))
                fallSpeed = Float.parseFloat(split[1]);
            else if(split[0].equals("ThrowSpeed") || split[0].equals("ShootSpeed"))
                throwSpeed = Float.parseFloat(split[1]);
            else if(split[0].equals("HitBoxSize"))
                hitBoxSize = Float.parseFloat(split[1]);
            else if(split[0].equals("HitEntityDamage") || split[0].equals("DamageVsLiving") || split[0].equals("DamageVsPlayer"))
                damageVsLiving = Integer.parseInt(split[1]);
            else if(split[0].equals("DamageVsVehicles"))
                damageVsDriveable = Integer.parseInt(split[1]);
            else if(split[0].equals("BreaksGlass"))
                breaksGlass = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("Fuse"))
                fuse = Integer.parseInt(split[1]);
            else if(split[0].equals("DespawnTime"))
                despawnTime = Integer.parseInt(split[1]);
            else if(split[0].equals("ExplodeOnImpact") || split[0].equals("DetonateOnImpact"))
                explodeOnImpact = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("FireRadius") || split[0].equals("Fire"))
                fireRadius = Float.parseFloat(split[1]);
            else if(split[0].equals("ExplosionRadius") || split[0].equals("Explosion"))
                explosionRadius = Float.parseFloat(split[1]);
            else if(split[0].equals("ExplosionBreaksBlocks"))
                explosionBreaksBlocks = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("DropItemOnDetonate"))
                dropItemOnDetonate = split[1];
            else if(split[0].equals("DetonateSound"))
                detonateSound = split[1];
            else if(split[0].equals("TrailParticles") || split[0].equals("SmokeTrail"))
                trailParticles = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("TrailParticleType"))
                trailParticleType = split[1];
        }
        catch(Exception e)
        {
            System.out.println("Reading grenade file failed.");
            e.printStackTrace();
        }
    }

    public static ShootableType getShootableType(String string)
    {
        return shootables.get(string);
    }
}