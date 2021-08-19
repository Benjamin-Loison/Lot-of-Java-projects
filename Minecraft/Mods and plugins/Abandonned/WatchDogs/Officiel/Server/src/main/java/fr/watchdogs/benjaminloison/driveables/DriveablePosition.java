package fr.watchdogs.benjaminloison.driveables;

import com.flansmod.common.vector.Vector3f;

public class DriveablePosition
{
    public Vector3f position;
    public EnumDriveablePart part;

    public DriveablePosition(Vector3f v, EnumDriveablePart p)
    {
        position = v;
        part = p;
    }

    public DriveablePosition(String[] split)
    {
        this(new Vector3f(Float.parseFloat(split[1]) / 16, Float.parseFloat(split[2]) / 16, Float.parseFloat(split[3]) / 16), EnumDriveablePart.getPart(split[4]));
    }
}