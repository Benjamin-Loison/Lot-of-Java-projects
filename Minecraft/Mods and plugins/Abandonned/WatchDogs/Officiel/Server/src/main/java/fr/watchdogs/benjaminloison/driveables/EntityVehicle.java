package fr.watchdogs.benjaminloison.driveables;

import com.flansmod.common.vector.Vector3f;

import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.api.IExplodeable;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.common.guns.ItemTool;
import fr.watchdogs.benjaminloison.packets.PacketPlaySound;
import fr.watchdogs.benjaminloison.packets.PacketVehicleControl;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityVehicle extends EntityDriveable implements IExplodeable
{
    public int maxFuel, shellDelay, gunDelay, soundPosition, toggleTimer = 0;
    public float wheelsYaw, maxSpeed, wheelsAngle;
    private int ticksSinceUsed = 0;
    public EntityDriveable driveable;
    public boolean good = false;

    public EntityVehicle(World world)
    {
        super(world);
        stepHeight = 1;
    }

    public EntityVehicle(World world, double x, double y, double z, VehicleType type, DriveableData data)
    {
        super(world, type, data);
        stepHeight = 1;
        setPosition(x, y, z);
        initType(type, false);
    }

    public EntityVehicle(World world, double x, double y, double z, int placer, VehicleType type, DriveableData data, String player)
    {
        super(world, type, data);
        stepHeight = 1;
        setPosition(x, y, z);
        rotateYaw(placer);
        initType(type, false);
        owners[0] = player;
        data.fuelInTank = type.fuelTankSize;
    }

    protected void initType(DriveableType type, boolean clientSide)
    {
        super.initType(type, clientSide);
        maxFuel = type.fuelTankSize;
        maxSpeed = type.maxThrottle;
    }

    @Override
    public void readSpawnData(ByteBuf data)
    {
        super.readSpawnData(data);
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag)
    {
        super.writeEntityToNBT(tag);
        tag.setBoolean("VarDoor", varDoor);
        tag.setBoolean("Rideable", rideable);
        for(int i = 0; i < owners.length; i++)
            tag.setString("Owners" + i, owners[i]);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag)
    {
        super.readEntityFromNBT(tag);
        varDoor = tag.getBoolean("VarDoor");
        rideable = tag.getBoolean("Rideable");
        for(int i = 0; i < owners.length; i++)
            owners[i] = tag.getString("Owners" + i);
    }

    @Override
    public void onMouseMoved(int deltaX, int deltaY)
    {}

    @Override
    public void setPositionRotationAndMotion(double x, double y, double z, float yaw, float pitch, float roll, double motX, double motY, double motZ, float velYaw, float velPitch, float velRoll, float throt, float steeringYaw)
    {
        super.setPositionRotationAndMotion(x, y, z, yaw, pitch, roll, motX, motY, motZ, velYaw, velPitch, velRoll, throt, steeringYaw);
        wheelsYaw = steeringYaw;
    }

    @Override
    public boolean interactFirst(EntityPlayer entityplayer)
    {
        if(isDead)
            return false;
        ItemStack currentItem = entityplayer.getCurrentEquippedItem();
        if(currentItem != null && currentItem.getItem() instanceof ItemTool && ((ItemTool)currentItem.getItem()).type.healDriveables)
            return true;
        VehicleType type = getVehicleType();
        for(int i = 0; i <= type.numPassengers; i++)
            if(seats[i] != null)
                if(seats[i].interactFirst(entityplayer))
                {
                    if(i == 0)
                        shellDelay = type.vehicleShellDelay;
                    return true;
                }
        return false;
    }

    @Override
    public boolean pressKey(int key, EntityPlayer player)
    {
        VehicleType type = getVehicleType();
        switch(key)
        {
            case 0:
            {
                throttle += 0.01;
                if(throttle > 1)
                    throttle = 1;
                return true;
            }
            case 1:
            {
                throttle -= 0.01;
                if(throttle < -1)
                    throttle = -1;
                if(throttle < 0 && type.maxNegativeThrottle == 0)
                    throttle = 0;
                return true;
            }
            case 2:
            {
                wheelsYaw -= 1;
                return true;
            }
            case 3:
            {
                wheelsYaw += 1;
                return true;
            }
            case 4:
                return true;
            case 5:
                return true;
            case 6:
                if(toggleTimer <= 0)
                {
                    if(varDoor)
                    {
                        seats[0].riddenByEntity.mountEntity(null);
                        return true;
                    }
                    toggleTimer = 10;
                }
            case 7:
                return true;
            case 9:
                return super.pressKey(key, player);
            case 10:
                return true;
            case 11:
                return true;
            case 12:
                return true;
            case 13:
                return true;
            case 14:
            {
                if(toggleTimer <= 0)
                {
                    for(int i = 0; i < seats[0].driveable.owners.length; i++)
                        if(!good)
                            if(player.getDisplayName().equals(seats[0].driveable.owners[i]))
                            {
                                if(seats[0].driveable.varDoor)
                                {
                                    seats[0].driveable.rideable = false;
                                    seats[0].driveable.varDoor = false;
                                    player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "V" + I18n.format("accent.er") + "hicule ferm" + I18n.format("accent.er") + " !"));
                                    good = true;
                                }
                                else if(!seats[0].driveable.varDoor)
                                {
                                    seats[0].driveable.rideable = true;
                                    seats[0].driveable.varDoor = true;
                                    player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "V" + I18n.format("accent.er") + "hicule ouvert !"));
                                    good = true;
                                }
                            }
                    toggleTimer = 10;
                    WatchDogs.getPacketHandler().sendToServer(new PacketVehicleControl(this));
                    good = false;
                }
                return true;
            }
            case 15:
                return true;
            case 16:
                return true;
            case 17:
                break;
        }
        return false;
    }

    @Override
    public Vector3f getLookVector(DriveablePosition dp)
    {
        return rotate(seats[0].looking.getXAxis());
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        varDoor = rideable;
        VehicleType type = this.getVehicleType();
        DriveableData data = getDriveableData();
        if(type == null)
        {
            WatchDogs.print("Vehicle type null. Not ticking vehicle");
            return;
        }
        ticksSinceUsed++;
        if(seats[0].riddenByEntity != null)
            ticksSinceUsed = 0;
        if(FileAPI.configNumber("life.vehicle") > 0 && ticksSinceUsed > FileAPI.configNumber("life.vehicle") * 20)
            setDead();
        if(shellDelay > 0)
            shellDelay--;
        if(gunDelay > 0)
            gunDelay--;
        if(toggleTimer > 0)
            toggleTimer--;
        if(soundPosition > 0)
            soundPosition--;
        if(hasEnoughFuel())
            wheelsAngle += throttle / 7;
        wheelsYaw *= 0.9;
        if(wheelsYaw > 20)
            wheelsYaw = 20;
        if(wheelsYaw < -20)
            wheelsYaw = -20;
        Vector3f amountToMoveCar = new Vector3f();
        for(EntityWheel wheel : wheels)
        {
            if(wheel == null)
                continue;
            onGround = true;
            wheel.onGround = true;
            wheel.rotationYaw = axes.getYaw();
            if(!type.tank && (wheel.ID == 2 || wheel.ID == 3))
                wheel.rotationYaw += wheelsYaw;
            wheel.motionX *= 0.9;
            wheel.motionY *= 0.9;
            wheel.motionZ *= 0.9;
            wheel.motionY -= 0.98 / 20;
            if(!FileAPI.configBoolean("vehicles.need.fuel") || (seats != null && seats[0] != null && seats[0].riddenByEntity instanceof EntityPlayer && ((EntityPlayer)seats[0].riddenByEntity).capabilities.isCreativeMode) || data.fuelInTank > data.engine.fuelConsumption * throttle)
            {
                if(getVehicleType().tank)
                {
                    boolean left = wheel.ID == 0 || wheel.ID == 3;
                    float turningDrag = 0.02F;
                    wheel.motionX *= 1 - (Math.abs(wheelsYaw) * turningDrag);
                    wheel.motionZ *= 1 - (Math.abs(wheelsYaw) * turningDrag);
                    float velocityScale = 0.04F * (throttle > 0 ? type.maxThrottle : type.maxNegativeThrottle) * data.engine.engineSpeed, steeringScale = 0.1F * (wheelsYaw > 0 ? type.turnLeftModifier : type.turnRightModifier), effectiveWheelSpeed = (throttle + (wheelsYaw * (left ? 1 : -1) * steeringScale)) * velocityScale;
                    wheel.motionX += effectiveWheelSpeed * Math.cos(wheel.rotationYaw * (float)Math.PI / 180);
                    wheel.motionZ += effectiveWheelSpeed * Math.sin(wheel.rotationYaw * (float)Math.PI / 180);
                }
                else
                {
                    {
                        float velocityScale = 0.1F * throttle * (throttle > 0 ? type.maxThrottle : type.maxNegativeThrottle) * data.engine.engineSpeed;
                        wheel.motionX += Math.cos(wheel.rotationYaw * (float)Math.PI / 180) * velocityScale;
                        wheel.motionZ += Math.sin(wheel.rotationYaw * (float)Math.PI / 180) * velocityScale;
                    }
                    if(wheel.ID == 2 || wheel.ID == 3)
                    {
                        float velocityScale = 0.01F * (wheelsYaw > 0 ? type.turnLeftModifier : type.turnRightModifier) * (throttle > 0 ? 1 : -1);
                        wheel.motionX -= wheel.getSpeedXZ() * Math.sin(wheel.rotationYaw * (float)Math.PI / 180) * velocityScale * wheelsYaw;
                        wheel.motionZ += wheel.getSpeedXZ() * Math.cos(wheel.rotationYaw * (float)Math.PI / 180) * velocityScale * wheelsYaw;
                    }
                    else
                    {
                        wheel.motionX *= 0.9;
                        wheel.motionZ *= 0.9;
                    }
                }
            }
            if(type.floatOnWater && worldObj.isAnyLiquid(wheel.boundingBox))
                wheel.motionY += type.buoyancy;
            wheel.moveEntity(wheel.motionX, wheel.motionY, wheel.motionZ);
            Vector3f targetWheelPos = axes.findLocalVectorGlobally(getVehicleType().wheelPositions[wheel.ID].position), currentWheelPos = new Vector3f(wheel.posX - posX, wheel.posY - posY, wheel.posZ - posZ), dPos = ((Vector3f)Vector3f.sub(targetWheelPos, currentWheelPos, null).scale(getVehicleType().wheelSpringStrength));
            if(dPos.length() > 0.001)
            {
                wheel.moveEntity(dPos.x, dPos.y, dPos.z);
                dPos.scale(0.5F);
                Vector3f.sub(amountToMoveCar, dPos, amountToMoveCar);
            }
        }
        moveEntity(amountToMoveCar.x, amountToMoveCar.y, amountToMoveCar.z);
        if(wheels[0] != null && wheels[1] != null && wheels[2] != null && wheels[3] != null)
        {
            Vector3f frontAxleCentre = new Vector3f((wheels[2].posX + wheels[3].posX) / 2, (wheels[2].posY + wheels[3].posY) / 2, (wheels[2].posZ + wheels[3].posZ) / 2), backAxleCentre = new Vector3f((wheels[0].posX + wheels[1].posX) / 2, (wheels[0].posY + wheels[1].posY) / 2, (wheels[0].posZ + wheels[1].posZ) / 2);
            float dx = frontAxleCentre.x - backAxleCentre.x, dy = frontAxleCentre.y - backAxleCentre.y, dz = frontAxleCentre.z - backAxleCentre.z, dxz = (float)Math.sqrt(dx * dx + dz * dz), yaw = (float)Math.atan2(dz, dx), pitch = -(float)Math.atan2(dy, dxz), roll = 0;
            if(type.tank)
                yaw = (float)Math.atan2(wheels[3].posZ - wheels[2].posZ, wheels[3].posX - wheels[2].posX) + (float)Math.PI / 2;
            axes.setAngles(yaw * 180 / (float)Math.PI, pitch * 180 / (float)Math.PI, roll * 180 / (float)Math.PI);
        }
        checkForCollisions();
        if(throttle > 0.01 && throttle < 0.2 && soundPosition == 0 && hasEnoughFuel())
        {
            PacketPlaySound.sendSoundPacket(posX, posY, posZ, 50, dimension, type.startSound, false);
            soundPosition = type.startSoundLength;
        }
        if(throttle > 0.2 && soundPosition == 0 && hasEnoughFuel())
        {
            PacketPlaySound.sendSoundPacket(posX, posY, posZ, 50, dimension, type.engineSound, false);
            soundPosition = type.engineSoundLength;
        }
        for(EntitySeat seat : seats)
            if(seat != null)
                seat.updatePosition();
        if(ticksExisted % 5 == 0)
            WatchDogs.getPacketHandler().sendToAllAround(new PacketVehicleControl(this), posX, posY, posZ, WatchDogs.driveableUpdateRange, dimension);
    }

    private float averageAngles(float a, float b)
    {
        WatchDogs.print("Pre  " + a + " " + b);
        float pi = (float)Math.PI;
        for(; a > b + pi; a -= 2 * pi);
        for(; a < b - pi; a += 2 * pi);
        float avg = (a + b) / 2;
        for(; avg > pi; avg -= 2 * pi);
        for(; avg < -pi; avg += 2 * pi);
        WatchDogs.print("Post " + a + " " + b + " " + avg);
        return avg;
    }

    private Vec3 subtract(Vec3 a, Vec3 b)
    {
        return Vec3.createVectorHelper(a.xCoord - b.xCoord, a.yCoord - b.yCoord, a.zCoord - b.zCoord);
    }

    private Vec3 crossProduct(Vec3 a, Vec3 b)
    {
        return Vec3.createVectorHelper(a.yCoord * b.zCoord - a.zCoord * b.yCoord, a.zCoord * b.xCoord - a.xCoord * b.zCoord, a.xCoord * b.yCoord - a.yCoord * b.xCoord);
    }

    @Override
    public boolean landVehicle()
    {
        return true;
    }

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float i)
    {
        return true;
    }

    public VehicleType getVehicleType()
    {
        return VehicleType.getVehicle(driveableType);
    }

    @Override
    public float getPlayerRoll()
    {
        return axes.getRoll();
    }

    @Override
    protected void dropItemsOnPartDeath(Vector3f midpoint, DriveablePart part)
    {}

    @Override
    public String getBombInventoryName()
    {
        return "Mines";
    }

    @Override
    public String getMissileInventoryName()
    {
        return "Shells";
    }

    @Override
    public boolean hasMouseControlMode()
    {
        return false;
    }

    @Override
    public void setDead()
    {
        super.setDead();
        for(EntityWheel wheel : wheels)
            if(wheel != null)
                wheel.setDead();
    }
}