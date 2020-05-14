package fr.watchdogs.benjaminloison.driveables;

import fr.watchdogs.benjaminloison.common.guns.AAGunType;
import fr.watchdogs.benjaminloison.common.guns.AttachmentType;
import fr.watchdogs.benjaminloison.common.guns.BulletType;
import fr.watchdogs.benjaminloison.common.guns.GrenadeType;
import fr.watchdogs.benjaminloison.common.guns.GunBoxType;
import fr.watchdogs.benjaminloison.common.guns.GunType;
import fr.watchdogs.benjaminloison.common.guns.ToolType;
import fr.watchdogs.benjaminloison.common.teams.ArmourBoxType;
import fr.watchdogs.benjaminloison.common.teams.ArmourType;

public enum EnumType
{
    part("parts"),
    bullet("bullets"),
    attachment("attachments"),
    grenade("grenades"),
    gun("fr.watchdogs.benjaminloison.common.guns"),
    aa("aaguns"),
    vehicle("vehicles"),
    plane("planes"),
    mechaItem("mechaItems"),
    mecha("mechas"),
    tool("tools"),
    armour("armorFiles"),
    armourBox("armorBoxes"),
    box("boxes"),
    playerClass("classes"),
    team("fr.watchdogs.benjaminloison.common.teams");
    public String folderName;

    private EnumType(String s)
    {
        folderName = s;
    }

    public static EnumType get(String s)
    {
        for(EnumType e : values())
            if(e.folderName.equals(s))
                return e;
        return null;
    }

    public Class<? extends InfoType> getTypeClass()
    {
        switch(this)
        {
            case bullet:
                return BulletType.class;
            case aa:
                return AAGunType.class;
            case vehicle:
                return VehicleType.class;
            case plane:
                return PlaneType.class;
            case mechaItem:
                return MechaItemType.class;
            case mecha:
                return MechaType.class;
            case attachment:
                return AttachmentType.class;
            case gun:
                return GunType.class;
            case grenade:
                return GrenadeType.class;
            case tool:
                return ToolType.class;
            case armour:
                return ArmourType.class;
            case armourBox:
                return ArmourBoxType.class;
            case box:
                return GunBoxType.class;
            case part:
                return PartType.class;
            default:
                return InfoType.class;
        }
    }

    public static EnumType getFromObject(Object o)
    {
        if(o instanceof EntityMecha || o instanceof MechaType)
            return mecha;
        else if(o instanceof EntityPlane || o instanceof PlaneType)
            return plane;
        else if(o instanceof EntityVehicle || o instanceof VehicleType)
            return vehicle;
        return null;
    }
}