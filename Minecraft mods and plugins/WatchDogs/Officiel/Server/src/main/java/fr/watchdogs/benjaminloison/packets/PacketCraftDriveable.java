package fr.watchdogs.benjaminloison.packets;

import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.driveables.DriveableType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketCraftDriveable extends PacketBase
{
    public String shortName;

    public PacketCraftDriveable()
    {}

    public PacketCraftDriveable(String s)
    {
        shortName = s;
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
        WatchDogs.proxy.craftDriveable(playerEntity, DriveableType.getDriveable(shortName));
    }
}