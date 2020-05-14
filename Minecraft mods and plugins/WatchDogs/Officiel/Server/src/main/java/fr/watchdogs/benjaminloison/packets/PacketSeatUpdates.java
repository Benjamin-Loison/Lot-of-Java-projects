package fr.watchdogs.benjaminloison.packets;

import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.driveables.EntityDriveable;
import fr.watchdogs.benjaminloison.driveables.EntitySeat;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketSeatUpdates extends PacketBase
{
    public int entityId, seatId;
    public float yaw, pitch;

    public PacketSeatUpdates()
    {}

    public PacketSeatUpdates(EntitySeat seat)
    {
        entityId = seat.driveable.getEntityId();
        seatId = seat.seatInfo.id;
        yaw = seat.looking.getYaw();
        pitch = seat.looking.getPitch();
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        data.writeInt(entityId);
        data.writeInt(seatId);
        data.writeFloat(yaw);
        data.writeFloat(pitch);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        entityId = data.readInt();
        seatId = data.readInt();
        yaw = data.readFloat();
        pitch = data.readFloat();
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
        {
            driveable.seats[seatId].prevLooking = driveable.seats[seatId].looking.clone();
            driveable.seats[seatId].looking.setAngles(yaw, pitch, 0F);
            WatchDogs.getPacketHandler().sendToAllAround(this, driveable.posX, driveable.posY, driveable.posZ, WatchDogs.soundRange, driveable.dimension);
        }
    }
}