package com.minefus.degraduck.packets;

import com.minefus.degraduck.common.Minefus;

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

    public void handleServerSide(EntityPlayer player)
    {
        player.openGui(Minefus.instance, id, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
    }
}