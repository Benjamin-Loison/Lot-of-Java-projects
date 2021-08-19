package fr.watchdogs.benjaminloison.driveables;

import com.flansmod.common.vector.Vector3f;

public class CollisionSurface
{
    public Vector3f localisedOrigin, u, v;

    public CollisionSurface(Vector3f o, Vector3f u1, Vector3f v1)
    {
        localisedOrigin = o;
        u = u1;
        v = v1;
    }
}