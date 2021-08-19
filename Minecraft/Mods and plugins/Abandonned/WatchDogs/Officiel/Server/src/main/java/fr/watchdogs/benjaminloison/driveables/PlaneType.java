package fr.watchdogs.benjaminloison.driveables;

import java.util.ArrayList;

import com.flansmod.common.vector.Vector3f;

import net.minecraft.item.ItemStack;

public class PlaneType extends DriveableType
{
    public EnumPlaneMode mode = EnumPlaneMode.PLANE;
    public float restingPitch = 0, lookDownModifier = 1, lookUpModifier = 1, rollLeftModifier = 1, rollRightModifier = 1, turnLeftModifier = 1, turnRightModifier = 1, lift = 1;
    public Vector3f bombPosition;
    public int planeShootDelay, planeBombDelay;
    public ArrayList<Propeller> propellers = new ArrayList<Propeller>(), heliPropellers = new ArrayList<Propeller>(), heliTailPropellers = new ArrayList<Propeller>();
    public boolean invInflight = true, hasGear = false, hasDoor = false, hasWing = false;
    public static ArrayList<PlaneType> types = new ArrayList<PlaneType>();

    public PlaneType(TypeFile file)
    {
        super(file);
        types.add(this);
    }

    @Override
    public void preRead(TypeFile file)
    {
        super.preRead(file);
    }

    @Override
    protected void read(String[] split, TypeFile file)
    {
        super.read(split, file);
        try
        {
            if(split[0].equals("Mode"))
                mode = EnumPlaneMode.getMode(split[1]);
            else if(split[0].equals("TurnLeftSpeed"))
                turnLeftModifier = Float.parseFloat(split[1]);
            else if(split[0].equals("TurnRightSpeed"))
                turnRightModifier = Float.parseFloat(split[1]);
            else if(split[0].equals("LookUpSpeed"))
                lookUpModifier = Float.parseFloat(split[1]);
            else if(split[0].equals("LookDownSpeed"))
                lookDownModifier = Float.parseFloat(split[1]);
            else if(split[0].equals("RollLeftSpeed"))
                rollLeftModifier = Float.parseFloat(split[1]);
            else if(split[0].equals("RollRightSpeed"))
                rollRightModifier = Float.parseFloat(split[1]);
            else if(split[0].equals("Lift"))
                lift = Float.parseFloat(split[1]);
            else if(split[0].equals("ShootDelay"))
                planeShootDelay = Integer.parseInt(split[1]);
            else if(split[0].equals("BombDelay"))
                planeBombDelay = Integer.parseInt(split[1]);
            else if(split[0].equals("Propeller"))
            {
                Propeller propeller = new Propeller(Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[4]), EnumDriveablePart.getPart(split[5]), PartType.getPart(split[6]));
                propellers.add(propeller);
                recipe.add(new ItemStack(propeller.itemType.item));
            }
            else if(split[0].equals("HeliPropeller"))
            {
                Propeller propeller = new Propeller(Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[4]), EnumDriveablePart.getPart(split[5]), PartType.getPart(split[6]));
                heliPropellers.add(propeller);
                recipe.add(new ItemStack(propeller.itemType.item));
            }
            else if(split[0].equals("HeliTailPropeller"))
            {
                Propeller propeller = new Propeller(Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[4]), EnumDriveablePart.getPart(split[5]), PartType.getPart(split[6]));
                heliTailPropellers.add(propeller);
                recipe.add(new ItemStack(propeller.itemType.item));
            }
            else if(split[0].equals("PropSoundLength"))
                engineSoundLength = Integer.parseInt(split[1]);
            else if(split[0].equals("PropSound"))
                engineSound = split[1];
            else if(split[0].equals("ShootSound"))
                shootSoundPrimary = split[1];
            else if(split[0].equals("BombSound"))
                shootSoundSecondary = split[1];
            else if(split[0].equals("HasGear"))
                hasGear = split[1].equals("True");
            else if(split[0].equals("HasDoor"))
                hasDoor = split[1].equals("True");
            else if(split[0].equals("HasWing"))
                hasWing = split[1].equals("True");
            else if(split[0].equals("RestingPitch"))
                restingPitch = Float.parseFloat(split[1]);
            else if(split[0].equals("InflightInventory"))
                invInflight = split[1].equals("False");
        }
        catch(Exception e)
        {}
    }

    @Override
    public int numEngines()
    {
        switch(mode)
        {
            case VTOL:
                return Math.max(propellers.size(), heliPropellers.size());
            case PLANE:
                return propellers.size();
            case HELI:
                return heliPropellers.size();
            default:
                return 1;
        }
    }

    @Override
    public ArrayList<ItemStack> getItemsRequired(DriveablePart part, PartType engine)
    {
        ArrayList<ItemStack> stacks = super.getItemsRequired(part, engine);
        for(Propeller propeller : propellers)
            if(propeller.planePart == part.type)
            {
                stacks.add(new ItemStack(propeller.itemType.item));
                stacks.add(new ItemStack(engine.item));
            }
        return stacks;
    }

    public static PlaneType getPlane(String find)
    {
        for(PlaneType type : types)
            if(type.shortName.equals(find))
                return type;
        return null;
    }
}