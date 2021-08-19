package fr.watchdogs.benjaminloison.driveables;

import com.flansmod.common.vector.Vector3f;

public class Propeller
{
    public PartType itemType;
    public int ID, x, y, z;
    public EnumDriveablePart planePart;

    public Propeller(int i, int x, int y, int z, EnumDriveablePart part, PartType type)
    {
        ID = i;
        this.x = x;
        this.y = y;
        this.z = z;
        planePart = part;
        itemType = type;
    }

    public Vector3f getPosition()
    {
        return new Vector3f(x / 16, y / 16, z / 16);
    }
}