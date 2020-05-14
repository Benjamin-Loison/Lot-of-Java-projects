package fr.watchdogs.benjaminloison.driveables;

import com.flansmod.common.vector.Vector3f;

public class CollisionBox
{
    public float x, y, z, w, h, d;
    public int health;
    public EnumDriveablePart part;

    public CollisionBox(int health, int x, int y, int z, int w, int h, int d)
    {
        this.health = health;
        this.x = x / 16;
        this.y = y / 16;
        this.z = z / 16;
        this.w = w / 16;
        this.h = h / 16;
        this.d = d / 16;
    }

    public Vector3f getCentre()
    {
        return new Vector3f(x + w / 2, y + h / 2, z + d / 2);
    }
}
