package fr.watchdogs.benjaminloison.driveables;

public enum EnumMechaSlotType
{
    leftTool,
    rightTool,
    leftArm,
    rightArm,
    head,
    leftShoulder,
    rightShoulder,
    hips,
    legs,
    feet,
    u1,
    u2,
    u3,
    u4,
    u5;

    public boolean accepts(EnumMechaItemType type)
    {
        switch(this)
        {
            case rightTool:
                return type == EnumMechaItemType.tool;
            case rightArm:
                return type == EnumMechaItemType.armUpgrade;
            case head:
                return type == EnumMechaItemType.headUpgrade;
            case rightShoulder:
                return type == EnumMechaItemType.shoulderUpgrade;
            case legs:
                return type == EnumMechaItemType.legUpgrade;
            case hips:
                return type == EnumMechaItemType.hipsUpgrade;
            case feet:
                return type == EnumMechaItemType.feetUpgrade;
            case u5:
                return type == EnumMechaItemType.upgrade;
            default:
        }
        return false;
    }
}