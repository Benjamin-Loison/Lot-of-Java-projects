package fr.watchdogs.benjaminloison.common.guns;

import fr.watchdogs.benjaminloison.driveables.EntityDriveable;
import fr.watchdogs.benjaminloison.driveables.EnumDriveablePart;

public class DriveableHit extends BulletHit
{
    public EntityDriveable driveable;
    public EnumDriveablePart part;

    public DriveableHit(EntityDriveable d, EnumDriveablePart p, float f)
    {
        super(f);
        part = p;
        driveable = d;
    }
}