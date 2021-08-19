package fr.watchdogs.benjaminloison.driveables;

import java.util.ArrayList;

import com.flansmod.common.vector.Vector3f;

public class MechaType extends DriveableType
{
    public int stepHeight = 0;
    public Vector3f leftArmOrigin, rightArmOrigin;
    public boolean squashMobs = false, damageBlocksFromFalling = true, takeFallDamage = true, limitHeadTurn = false;
    public float jumpHeight = 1, jumpVelocity = 1, rotateSpeed = 10, turnLeftModifier = 1, turnRightModifier = 1, moveSpeed = 1, armLength = 1, legLength = 1, heldItemScale = 1, height = 3F, width = 2, chassisHeight = 1, reach = 10, blockDamageFromFalling = 1, fallDamageMultiplier = 1, legSwingLimit = 2, limitHeadTurnValue = 90, legSwingTime = 5, upperArmLimit = 90,
            lowerArmLimit = 90, leftHandModifierX = 0, leftHandModifierY = 0, leftHandModifierZ = 0, rightHandModifierX = 0, rightHandModifierY = 0, rightHandModifierZ = 0;
    public static ArrayList<MechaType> types = new ArrayList<MechaType>();

    public MechaType(TypeFile file)
    {
        super(file);
        types.add(this);
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
            else if(split[0].equals("MoveSpeed"))
                moveSpeed = Float.parseFloat(split[1]);
            else if(split[0].equals("SquashMobs"))
                squashMobs = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("StepHeight"))
                stepHeight = Integer.parseInt(split[1]);
            else if(split[0].equals("JumpHeight"))
            {
                jumpHeight = Float.parseFloat(split[1]);
                jumpVelocity = (float)Math.sqrt(Math.abs(9.81 * (jumpHeight + 0.2) / 200));
            }
            else if(split[0].equals("RotateSpeed"))
                rotateSpeed = Float.parseFloat(split[1]);
            else if(split[0].equals("LeftArmOrigin"))
                leftArmOrigin = new Vector3f(Float.parseFloat(split[1]) / 16, Float.parseFloat(split[2]) / 16, Float.parseFloat(split[3]) / 16);
            else if(split[0].equals("RightArmOrigin"))
                rightArmOrigin = new Vector3f(Float.parseFloat(split[1]) / 16, Float.parseFloat(split[2]) / 16, Float.parseFloat(split[3]) / 16);
            else if(split[0].equals("ArmLength"))
                armLength = Float.parseFloat(split[1]) / 16;
            else if(split[0].equals("LegLength"))
                legLength = Float.parseFloat(split[1]) / 16;
            else if(split[0].equals("HeldItemScale"))
                heldItemScale = Float.parseFloat(split[1]);
            else if(split[0].equals("Height"))
                height = (Float.parseFloat(split[1]) / 16);
            else if(split[0].equals("Width"))
                width = (Float.parseFloat(split[1]) / 16);
            else if(split[0].equals("ChassisHeight"))
                chassisHeight = (Integer.parseInt(split[1])) / 16;
            else if(split[0].equals("FallDamageMultiplier"))
                fallDamageMultiplier = Float.parseFloat(split[1]);
            else if(split[0].equals("BlockDamageFromFalling"))
                blockDamageFromFalling = Float.parseFloat(split[1]);
            else if(split[0].equals("Reach"))
                reach = Float.parseFloat(split[1]);
            else if(split[0].equals("TakeFallDamage"))
                takeFallDamage = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("DamageBlocksFromFalling"))
                damageBlocksFromFalling = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("LegSwingLimit"))
                legSwingLimit = Float.parseFloat(split[1]);
            else if(split[0].equals("LimitHeadTurn"))
            {
                limitHeadTurn = Boolean.parseBoolean(split[1].toLowerCase());
                limitHeadTurnValue = Float.parseFloat(split[2]);
            }
            else if(split[0].equals("LegSwingTime"))
                legSwingTime = Float.parseFloat(split[1]);
            else if(split[0].equals("UpperArmLimit"))
                upperArmLimit = Float.parseFloat(split[1]);
            else if(split[0].equals("LowerArmLimit"))
                lowerArmLimit = Float.parseFloat(split[1]);
            else if(split[0].equals("LeftHandModifier"))
            {
                leftHandModifierX = Float.parseFloat(split[1]) / 16;
                leftHandModifierY = Float.parseFloat(split[2]) / 16;
                leftHandModifierZ = Float.parseFloat(split[3]) / 16;
            }
            else if(split[0].equals("RightHandModifier"))
            {
                rightHandModifierX = Float.parseFloat(split[1]) / 16;
                rightHandModifierY = Float.parseFloat(split[2]) / 16;
                rightHandModifierZ = Float.parseFloat(split[3]) / 16;
            }
        }
        catch(Exception e)
        {}
    }

    public static MechaType getMecha(String find)
    {
        for(MechaType type : types)
            if(type.shortName.equals(find))
                return type;
        return null;
    }
}