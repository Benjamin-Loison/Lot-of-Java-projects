package fr.watchdogs.benjaminloison.packets;

import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.driveables.EntitySeat;
import fr.watchdogs.benjaminloison.driveables.EnumDriveablePart;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketRepairDriveable extends PacketBase
{
    public String shortName;

    public PacketRepairDriveable()
    {}

    public PacketRepairDriveable(EnumDriveablePart part)
    {
        shortName = part.getShortName();
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        writeUTF(data, shortName);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        shortName = readUTF(data);
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity)
    {
        WatchDogs.proxy.repairDriveable(playerEntity, ((EntitySeat)playerEntity.ridingEntity).driveable, ((EntitySeat)playerEntity.ridingEntity).driveable.getDriveableData().parts.get(EnumDriveablePart.getPart(shortName)));
    }
}