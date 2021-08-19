package fr.watchdogs.benjaminloison.common.guns;

import java.util.ArrayList;
import java.util.List;

import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.driveables.InfoType;
import fr.watchdogs.benjaminloison.driveables.TypeFile;
import net.minecraft.item.ItemStack;

public class AAGunType extends InfoType
{
    public static List<AAGunType> infoTypes = new ArrayList<AAGunType>();
    public List<BulletType> ammo = new ArrayList<BulletType>();
    public int reloadTime, recoil = 5, accuracy, damage, shootDelay, numBarrels, health, gunnerX, gunnerY, gunnerZ, barrelX[], barrelY[], barrelZ[];
    public String shootSound, reloadSound;
    public float targetRange = 10, topViewLimit = 75, bottomViewLimit = 0;
    public boolean fireAlternately, shareAmmo = false, targetMobs = false, targetPlayers = false, targetVehicles = false, targetPlanes = false, targetMechas = false;

    public AAGunType(TypeFile file)
    {
        super(file);
        infoTypes.add(this);
    }

    @Override
    protected void read(String[] split, TypeFile file)
    {
        super.read(split, file);
        try
        {
            if(split[0].equals("Damage"))
                damage = Integer.parseInt(split[1]);
            else if(split[0].equals("ReloadTime"))
                reloadTime = Integer.parseInt(split[1]);
            else if(split[0].equals("Recoil"))
                recoil = Integer.parseInt(split[1]);
            else if(split[0].equals("Accuracy"))
                accuracy = Integer.parseInt(split[1]);
            else if(split[0].equals("ShootDelay"))
                shootDelay = Integer.parseInt(split[1]);
            else if(split[0].equals("ShootSound"))
                shootSound = split[1];
            else if(split[0].equals("ReloadSound"))
                reloadSound = split[1];
            else if(split[0].equals("FireAlternately"))
                fireAlternately = split[1].equals("True");
            else if(split[0].equals("NumBarrels"))
            {
                numBarrels = Integer.parseInt(split[1]);
                barrelX = new int[numBarrels];
                barrelY = new int[numBarrels];
                barrelZ = new int[numBarrels];
            }
            else if(split[0].equals("Barrel"))
            {
                int id = Integer.parseInt(split[1]);
                barrelX[id] = Integer.parseInt(split[2]);
                barrelY[id] = Integer.parseInt(split[3]);
                barrelZ[id] = Integer.parseInt(split[4]);
            }
            else if(split[0].equals("Health"))
                health = Integer.parseInt(split[1]);
            else if(split[0].equals("TopViewLimit"))
                topViewLimit = Float.parseFloat(split[1]);
            else if(split[0].equals("BottomViewLimit"))
                bottomViewLimit = Float.parseFloat(split[1]);
            else if(split[0].equals("Ammo"))
            {
                BulletType type = BulletType.getBullet(split[1]);
                if(type != null)
                    ammo.add(type);
            }
            else if(split[0].equals("GunnerPos"))
            {
                gunnerX = Integer.parseInt(split[1]);
                gunnerY = Integer.parseInt(split[2]);
                gunnerZ = Integer.parseInt(split[3]);
            }
            else if(split[0].equals("TargetMobs"))
                targetMobs = Boolean.parseBoolean(split[1]);
            else if(split[0].equals("TargetPlayers"))
                targetPlayers = Boolean.parseBoolean(split[1]);
            else if(split[0].equals("TargetVehicles"))
                targetVehicles = Boolean.parseBoolean(split[1]);
            else if(split[0].equals("TargetPlanes"))
                targetPlanes = Boolean.parseBoolean(split[1]);
            else if(split[0].equals("TargetMechas"))
                targetMechas = Boolean.parseBoolean(split[1]);
            else if(split[0].equals("TargetDriveables"))
                targetMechas = targetPlanes = targetVehicles = Boolean.parseBoolean(split[1]);
            else if(split[0].equals("ShareAmmo"))
                shareAmmo = Boolean.parseBoolean(split[1]);
            else if(split[0].equals("TargetRange"))
                targetRange = Float.parseFloat(split[1]);
        }
        catch(Exception e)
        {
            WatchDogs.print("" + e);
        }
    }

    public boolean isAmmo(BulletType type)
    {
        return ammo.contains(type);
    }

    public boolean isAmmo(ItemStack stack)
    {
        if(stack == null)
            return false;
        return stack.getItem() instanceof ItemBullet && isAmmo(((ItemBullet)stack.getItem()).type);
    }

    public static AAGunType getAAGun(String s)
    {
        for(AAGunType gun : infoTypes)
            if(gun.shortName.equals(s))
                return gun;
        return null;
    }
}