package fr.watchdogs.benjaminloison.packets;

import fr.watchdogs.benjaminloison.driveables.EntityDriveable;
import fr.watchdogs.benjaminloison.driveables.EntityPlane;
import fr.watchdogs.benjaminloison.driveables.EntityVehicle;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketDriveableControl extends PacketBase
{
    public int entityId;
    public double posX, posY, posZ, motX, motY, motZ;
    public float yaw, pitch, roll, avelx, avely, avelz, throttle, fuelInTank, steeringYaw;

    public PacketDriveableControl()
    {}

    public PacketDriveableControl(EntityDriveable driveable)
    {
        entityId = driveable.getEntityId();
        posX = driveable.posX;
        posY = driveable.posY;
        posZ = driveable.posZ;
        yaw = driveable.axes.getYaw();
        pitch = driveable.axes.getPitch();
        roll = driveable.axes.getRoll();
        motX = driveable.motionX;
        motY = driveable.motionY;
        motZ = driveable.motionZ;
        avelx = driveable.angularVelocity.x;
        avely = driveable.angularVelocity.y;
        avelz = driveable.angularVelocity.z;
        throttle = driveable.throttle;
        fuelInTank = driveable.driveableData.fuelInTank;
        if(driveable instanceof EntityVehicle)
            steeringYaw = ((EntityVehicle)driveable).wheelsYaw;
        else if(driveable instanceof EntityPlane)
            steeringYaw = ((EntityPlane)driveable).flapsYaw;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        data.writeInt(entityId);
        data.writeDouble(posX);
        data.writeDouble(posY);
        data.writeDouble(posZ);
        data.writeFloat(yaw);
        data.writeFloat(pitch);
        data.writeFloat(roll);
        data.writeDouble(motX);
        data.writeDouble(motY);
        data.writeDouble(motZ);
        data.writeFloat(avelx);
        data.writeFloat(avely);
        data.writeFloat(avelz);
        data.writeFloat(throttle);
        data.writeFloat(fuelInTank);
        data.writeFloat(steeringYaw);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        entityId = data.readInt();
        posX = data.readDouble();
        posY = data.readDouble();
        posZ = data.readDouble();
        yaw = data.readFloat();
        pitch = data.readFloat();
        roll = data.readFloat();
        motX = data.readDouble();
        motY = data.readDouble();
        motZ = data.readDouble();
        avelx = data.readFloat();
        avely = data.readFloat();
        avelz = data.readFloat();
        throttle = data.readFloat();
        fuelInTank = data.readFloat();
        steeringYaw = data.readFloat();
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity)
    {
        EntityDriveable driveable = null;
        for(Object obj : playerEntity.worldObj.loadedEntityList)
            if(obj instanceof EntityDriveable && ((Entity)obj).getEntityId() == entityId)
            {
                driveable = (EntityDriveable)obj;
                break;
            }
        if(driveable != null)
            updateDriveable(driveable, false);
    }

    protected void updateDriveable(EntityDriveable driveable, boolean clientSide)
    {
        driveable.setPositionRotationAndMotion(posX, posY, posZ, yaw, pitch, roll, motX, motY, motZ, avelx, avely, avelz, throttle, steeringYaw);
        driveable.driveableData.fuelInTank = fuelInTank;
    }
}