package fr.watchdogs.benjaminloison.driveables;

import java.util.ArrayList;

public class MechaItemType extends InfoType
{
    public static ArrayList<MechaItemType> types = new ArrayList<MechaItemType>();
    public EnumMechaItemType type;
    public EnumMechaToolType function = EnumMechaToolType.sword;
    public String soundEffect = "", detectSound = "";
    public int energyShield = 0, lightLevel = 0;
    public boolean floater = false, stopMechaFallDamage = false, forceBlockFallDamage = false, vacuumItems = false, refineIron = false, autoCoal = false, autoRepair = false, rocketPack = false, diamondDetect = false, infiniteAmmo = false, forceDark = false, wasteCompact = false, flameBurst = false;
    public float speed = 1, toolHardness = 1, reach = 1, soundTime = 0, speedMultiplier = 1, damageResistance = 0, fortuneDiamond = 1, fortuneRedstone = 1, fortuneCoal = 1, fortuneEmerald = 1, fortuneIron = 1, rocketPower = 1;

    public MechaItemType(TypeFile file)
    {
        super(file);
    }

    @Override
    protected void read(String[] split, TypeFile file)
    {
        super.read(split, file);
        try
        {
            if(split[0].equals("Type"))
                type = EnumMechaItemType.getToolType(split[1]);
            else if(split[0].equals("ToolType"))
                function = EnumMechaToolType.getToolType(split[1]);
            else if(split[0].equals("Speed"))
                speed = Float.parseFloat(split[1]);
            else if(split[0].equals("ToolHardness"))
                toolHardness = Float.parseFloat(split[1]);
            else if(split[0].equals("Reach"))
                reach = Float.parseFloat(split[1]);
            else if(split[0].equals("AutoFuel"))
                autoCoal = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("Armour"))
                damageResistance = Float.parseFloat(split[1]);
            else if(split[0].equals("CoalMultiplier"))
                fortuneCoal = Float.parseFloat(split[1]);
            else if(split[0].equals("DetectSound"))
                detectSound = split[1];
            else if(split[0].equals("DiamondDetect"))
                diamondDetect = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("DiamondMultiplier"))
                fortuneDiamond = Float.parseFloat(split[1]);
            else if(split[0].equals("EmeraldMultiplier"))
                fortuneEmerald = Float.parseFloat(split[1]);
            else if(split[0].equals("FlameBurst"))
                flameBurst = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("Floatation"))
                floater = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("ForceBlockFallDamage"))
                forceBlockFallDamage = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("ForceDark"))
                forceDark = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("InfiniteAmmo"))
                infiniteAmmo = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("IronMultiplier"))
                fortuneIron = Float.parseFloat(split[1]);
            else if(split[0].equals("IronRefine"))
                refineIron = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("ItemVacuum"))
                vacuumItems = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("LightLevel"))
                lightLevel = Integer.parseInt(split[1]);
            else if(split[0].equals("Nanorepair"))
                autoRepair = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("RedstoneMultiplier"))
                fortuneRedstone = Float.parseFloat(split[1]);
            else if(split[0].equals("RocketPack"))
                rocketPack = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("RocketPower"))
                rocketPower = Float.parseFloat(split[1]);
            else if(split[0].equals("SoundEffect"))
                soundEffect = split[1];
            else if(split[0].equals("SoundTime"))
                soundTime = Float.parseFloat(split[1]);
            else if(split[0].equals("SpeedMultiplier"))
                speedMultiplier = Float.parseFloat(split[1]);
            else if(split[0].equals("StopMechaFallDamage"))
                stopMechaFallDamage = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("WasteCompact"))
                wasteCompact = Boolean.parseBoolean(split[1].toLowerCase());
        }
        catch(Exception e)
        {}
    }

    public static MechaItemType getTool(String find)
    {
        for(MechaItemType type : types)
            if(type.shortName.equals(find))
                return type;
        return null;
    }
}