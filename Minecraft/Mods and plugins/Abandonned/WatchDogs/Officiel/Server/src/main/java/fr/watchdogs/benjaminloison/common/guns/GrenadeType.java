package fr.watchdogs.benjaminloison.common.guns;

import java.util.ArrayList;

import fr.watchdogs.benjaminloison.driveables.TypeFile;
import net.minecraft.potion.PotionEffect;

public class GrenadeType extends ShootableType
{
    public static ArrayList<GrenadeType> grenades = new ArrayList<GrenadeType>();
    public int numUses = 1, numClips, smokeTime, primeDelay, explodeParticles, meleeDamage = 1, throwDelay;
    public String smokeParticleType = "explode", explodeParticleType = "largesmoke", throwSound = "", dropItemOnThrow, bounceSound = "";
    public float smokeRadius = 5, healAmount, livingProximityTrigger = -1, driveableProximityTrigger = -1, damageToTriggerer = 0, explosionDamageVsLiving = 0, explosionDamageVsDriveable = 0, bounciness = 0.9F;
    public boolean spinWhenThrown = true, isDeployableBag, canThrow = true, penetratesEntities, penetratesBlocks, sticky, stickToThrower, detonateWhenShot, remote;
    public ArrayList<PotionEffect> smokeEffects = new ArrayList<PotionEffect>(), potionEffects = new ArrayList<PotionEffect>();

    public GrenadeType(TypeFile file)
    {
        super(file);
        grenades.add(this);
    }

    @Override
    protected void read(String[] split, TypeFile file)
    {
        super.read(split, file);
        try
        {
            if(split[0].equals("MeleeDamage"))
                meleeDamage = Integer.parseInt(split[1]);
            else if(split[0].equals("ThrowDelay"))
                throwDelay = Integer.parseInt(split[1]);
            else if(split[0].equals("ThrowSound"))
                throwSound = split[1];
            else if(split[0].equals("DropItemOnThrow"))
                dropItemOnThrow = split[1];
            else if(split[0].equals("CanThrow"))
                canThrow = Boolean.parseBoolean(split[1]);
            else if(split[0].equals("Bounciness"))
                bounciness = Float.parseFloat(split[1]);
            else if(split[0].equals("PenetratesEntities"))
                penetratesEntities = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("PenetratesBlocks"))
                penetratesBlocks = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("BounceSound"))
                bounceSound = split[1];
            else if(split[0].equals("Sticky"))
                sticky = Boolean.parseBoolean(split[1]);
            else if(split[0].equals("LivingProximityTrigger"))
                livingProximityTrigger = Float.parseFloat(split[1]);
            else if(split[0].equals("VehicleProximityTrigger"))
                driveableProximityTrigger = Float.parseFloat(split[1]);
            else if(split[0].equals("DamageToTriggerer"))
                damageToTriggerer = Float.parseFloat(split[1]);
            else if(split[0].equals("DetonateWhenShot"))
                detonateWhenShot = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("PrimeDelay") || split[0].equals("TriggerDelay"))
                primeDelay = Integer.parseInt(split[1]);
            else if(split[0].equals("StickToThrower"))
                stickToThrower = Boolean.parseBoolean(split[1]);
            else if(split[0].equals("ExplosionDamageVsLiving"))
                explosionDamageVsLiving = Float.parseFloat(split[1]);
            else if(split[0].equals("ExplosionDamageVsDrivable"))
                explosionDamageVsDriveable = Float.parseFloat(split[1]);
            else if(split[0].equals("NumExplodeParticles"))
                explodeParticles = Integer.parseInt(split[1]);
            else if(split[0].equals("ExplodeParticles"))
                explodeParticleType = split[1];
            else if(split[0].equals("SmokeTime"))
                smokeTime = Integer.parseInt(split[1]);
            else if(split[0].equals("SmokeParticles"))
                smokeParticleType = split[1];
            else if(split[0].equals("SmokeEffect"))
                smokeEffects.add(getPotionEffect(split));
            else if(split[0].equals("SmokeRadius"))
                smokeRadius = Float.parseFloat(split[1]);
            else if(split[0].equals("SpinWhenThrown"))
                spinWhenThrown = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("Remote"))
                remote = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("DeployableBag"))
                isDeployableBag = true;
            else if(split[0].equals("NumUses"))
                numUses = Integer.parseInt(split[1]);
            else if(split[0].equals("HealAmount"))
                healAmount = Float.parseFloat(split[1]);
            else if(split[0].equals("AddPotionEffect") || split[0].equals("PotionEffect"))
                potionEffects.add(getPotionEffect(split));
            else if(split[0].equals("NumClips"))
                numClips = Integer.parseInt(split[1]);
        }
        catch(Exception e)
        {
            System.out.println("Reading grenade file failed.");
            e.printStackTrace();
        }
    }

    public static GrenadeType getGrenade(String s)
    {
        for(GrenadeType grenade : grenades)
        {
            if(grenade.shortName.equals(s))
                return grenade;
        }
        return null;
    }
}