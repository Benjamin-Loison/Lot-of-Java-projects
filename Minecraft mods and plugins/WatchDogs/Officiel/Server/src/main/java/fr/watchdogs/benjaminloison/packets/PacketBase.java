package fr.watchdogs.benjaminloison.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayerMP;

public abstract class PacketBase
{
    public abstract void encodeInto(ChannelHandlerContext ctx, ByteBuf data);

    public abstract void decodeInto(ChannelHandlerContext ctx, ByteBuf data);

    public abstract void handleServerSide(EntityPlayerMP playerEntity);

    public void writeUTF(ByteBuf data, String s)
    {
        ByteBufUtils.writeUTF8String(data, s);
    }

    public String readUTF(ByteBuf data)
    {
        return ByteBufUtils.readUTF8String(data);
    }
}