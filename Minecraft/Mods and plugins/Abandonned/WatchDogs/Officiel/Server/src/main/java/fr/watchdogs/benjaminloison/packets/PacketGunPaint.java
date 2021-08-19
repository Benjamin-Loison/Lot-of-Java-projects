package fr.watchdogs.benjaminloison.packets;

import fr.watchdogs.benjaminloison.common.guns.ContainerGunModTable;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketGunPaint extends PacketBase
{
    private String paintjobName;

    public PacketGunPaint()
    {}

    public PacketGunPaint(String name)
    {
        paintjobName = name;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        writeUTF(data, paintjobName);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        paintjobName = readUTF(data);
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity)
    {
        ((ContainerGunModTable)playerEntity.openContainer).clickPaintjob(paintjobName);
    }
}