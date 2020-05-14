package fr.watchdogs.benjaminloison.driveables;

import java.util.ArrayList;

public class VehicleType extends DriveableType
{
    public float turnLeftModifier = 1, turnRightModifier = 1;
    public boolean hasDoor = false, squashMobs = false, fourWheelDrive = false, rotateWheels = false, tank = false;
    public int vehicleShootDelay, vehicleShellDelay;
    public static ArrayList<VehicleType> types = new ArrayList<VehicleType>();

    public VehicleType(TypeFile file)
    {
        super(file);
        types.add(this);
    }

    @Override
    public void preRead(TypeFile file)
    {
        super.preRead(file);
        wheelPositions = new DriveablePosition[4];
    }

    @Override
    protected void postRead(TypeFile file)
    {
        super.postRead(file);
    }

    @Override
    protected void read(String[] split, TypeFile file)
    {
        super.read(split, file);
        try
        {
            if(split[0].equals("TurnLeftSpeed"))
                turnLeftModifier = Float.parseFloat(split[1]);
            else if(split[0].equals("TurnRightSpeed"))
                turnRightModifier = Float.parseFloat(split[1]);
            else if(split[0].equals("SquashMobs"))
                squashMobs = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("FourWheelDrive"))
                fourWheelDrive = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("Tank") || split[0].equals("TankMode"))
                tank = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("HasDoor"))
                hasDoor = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("RotateWheels"))
                rotateWheels = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("ShootDelay"))
                vehicleShootDelay = Integer.parseInt(split[1]);
            else if(split[0].equals("ShellDelay"))
                vehicleShellDelay = Integer.parseInt(split[1]);
            else if(split[0].equals("ShootSound"))
                shootSoundPrimary = split[1];
            else if(split[0].equals("ShellSound"))
                shootSoundSecondary = split[1];
        }
        catch(Exception e)
        {}
    }

    public static VehicleType getVehicle(String find)
    {
        for(VehicleType type : types)
            if(type.shortName.equals(find))
                return type;
        return null;
    }
}