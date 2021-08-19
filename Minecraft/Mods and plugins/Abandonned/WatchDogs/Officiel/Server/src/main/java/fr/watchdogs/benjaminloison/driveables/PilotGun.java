package fr.watchdogs.benjaminloison.driveables;

import com.flansmod.common.vector.Vector3f;

import fr.watchdogs.benjaminloison.common.guns.GunType;

public class PilotGun extends DriveablePosition
{
    public GunType type;

    public PilotGun(String[] split)
    {
        super(new Vector3f(Float.parseFloat(split[1]) / 16, Float.parseFloat(split[2]) / 16, Float.parseFloat(split[3]) / 16), EnumDriveablePart.getPart(split[4]));
        type = GunType.getGun(split[5]);
    }
}