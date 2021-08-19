package fr.watchdogs.benjaminloison.common.guns;

import java.util.ArrayList;
import java.util.List;

import fr.watchdogs.benjaminloison.driveables.EnumWeaponType;
import fr.watchdogs.benjaminloison.driveables.TypeFile;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;

public class BulletType extends ShootableType
{
    public int flak = 0;
    public String flakParticles = "largesmoke";
    public EnumWeaponType weaponType = EnumWeaponType.NONE;
    public String hitSound;
    public boolean setEntitiesOnFire, hasLight, lockOnToPlanes, lockOnToVehicles, lockOnToMechas, lockOnToPlayers, lockOnToLivings;
    public float penetratingPower = 1, maxLockOnAngle = 45, lockOnForce = 1;
    public ArrayList<PotionEffect> hitEffects = new ArrayList<PotionEffect>();
    public static List<BulletType> bullets = new ArrayList<BulletType>();

    public BulletType(TypeFile file)
    {
        super(file);
        bullets.add(this);
    }

    @Override
    protected void read(String[] split, TypeFile file)
    {
        super.read(split, file);
        try
        {
            if(split[0].equals("FlakParticles"))
                flak = Integer.parseInt(split[1]);
            else if(split[0].equals("FlakParticleType"))
                flakParticles = split[1];
            else if(split[0].equals("SetEntitiesOnFire"))
                setEntitiesOnFire = Boolean.parseBoolean(split[1]);
            else if(split[0].equals("HitSound"))
                hitSound = split[1];
            else if(split[0].equals("Penetrates"))
                penetratingPower = (Boolean.parseBoolean(split[1].toLowerCase()) ? 1 : 0.25F);
            else if(split[0].equals("Penetration") || split[0].equals("PenetratingPower"))
                penetratingPower = Float.parseFloat(split[1]);
            else if(split[0].equals("Bomb"))
                weaponType = EnumWeaponType.BOMB;
            else if(split[0].equals("Shell"))
                weaponType = EnumWeaponType.SHELL;
            else if(split[0].equals("Missile"))
                weaponType = EnumWeaponType.MISSILE;
            else if(split[0].equals("WeaponType"))
                weaponType = EnumWeaponType.valueOf(split[1].toUpperCase());
            else if(split[0].equals("HasLight"))
                hasLight = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("LockOnToDriveables"))
                lockOnToPlanes = lockOnToVehicles = lockOnToMechas = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("LockOnToVehicles"))
                lockOnToVehicles = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("LockOnToPlanes"))
                lockOnToPlanes = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("LockOnToMechas"))
                lockOnToMechas = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("LockOnToPlayers"))
                lockOnToPlayers = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("LockOnToLivings"))
                lockOnToLivings = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("MaxLockOnAngle"))
                maxLockOnAngle = Float.parseFloat(split[1]);
            else if(split[0].equals("LockOnForce") || split[0].equals("TurningForce"))
                lockOnForce = Float.parseFloat(split[1]);
            else if(split[0].equals("PotionEffect"))
                hitEffects.add(getPotionEffect(split));
        }
        catch(Exception e)
        {
            System.out.println("Reading bullet file failed.");
            e.printStackTrace();
        }
    }

    public static BulletType getBullet(String s)
    {
        for(BulletType bullet : bullets)
            if(bullet.shortName.equals(s))
                return bullet;
        return null;
    }

    public static BulletType getBullet(Item item)
    {
        for(BulletType bullet : bullets)
            if(bullet.item == item)
                return bullet;
        return null;
    }
}