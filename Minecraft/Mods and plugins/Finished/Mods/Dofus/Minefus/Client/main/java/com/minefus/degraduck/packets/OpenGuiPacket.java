package com.minefus.degraduck.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

public class OpenGuiPacket extends AbstractPacket
{
    private int id;

    public OpenGuiPacket()
    {}

    public OpenGuiPacket(int id)
    {
        this.id = id;
    }

    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
        buffer.writeInt(id);
    }

    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
        id = buffer.readInt();
    }

    public void handleClientSide(EntityPlayer player)
    {}
}