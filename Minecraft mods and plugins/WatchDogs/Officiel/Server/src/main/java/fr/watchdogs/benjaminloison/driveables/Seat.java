package fr.watchdogs.benjaminloison.driveables;

import com.flansmod.common.vector.Vector3f;

import fr.watchdogs.benjaminloison.common.guns.GunType;

public class Seat
{
    public int x, y, z, id, gunnerID;
    public float minYaw = -360, maxYaw = 360, minPitch = -89, maxPitch = 89;
    public GunType gunType;
    public String gunName;
    public EnumDriveablePart part;
    public Vector3f rotatedOffset = new Vector3f(), gunOrigin = new Vector3f();

    public Seat(String[] split)
    {
        id = Integer.parseInt(split[1]);
        x = Integer.parseInt(split[2]);
        y = Integer.parseInt(split[3]);
        z = Integer.parseInt(split[4]);
        gunOrigin = new Vector3f(x, y, z);
        part = EnumDriveablePart.getPart(split[5]);
        if(split.length > 6)
        {
            minYaw = Float.parseFloat(split[6]);
            maxYaw = Float.parseFloat(split[7]);
            minPitch = Float.parseFloat(split[8]);
            maxPitch = Float.parseFloat(split[9]);
            if(split.length > 10)
            {
                gunType = GunType.getGun(split[10]);
                gunName = split[11];
            }
        }
    }

    public Seat(int dx, int dy, int dz)
    {
        id = 0;
        x = dx;
        y = dy;
        z = dz;
        part = EnumDriveablePart.core;
    }

    public Seat(int dx, int dy, int dz, float y1, float y2, float p1, float p2)
    {
        id = 0;
        x = dx;
        y = dy;
        z = dz;
        part = EnumDriveablePart.core;
        minYaw = y1;
        maxYaw = y2;
        minPitch = p1;
        maxPitch = p2;
    }
}