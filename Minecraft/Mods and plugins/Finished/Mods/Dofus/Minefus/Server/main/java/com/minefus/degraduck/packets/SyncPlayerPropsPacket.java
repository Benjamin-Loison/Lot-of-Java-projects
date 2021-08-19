package com.minefus.degraduck.packets;

import com.minefus.degraduck.entity.ExtendedPlayer;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class SyncPlayerPropsPacket extends AbstractPacket
{
    private NBTTagCompound data;

    public SyncPlayerPropsPacket()
    {}

    public SyncPlayerPropsPacket(EntityPlayer player)
    {
        data = new NBTTagCompound();
        ExtendedPlayer.get(player).saveNBTData(data);
    }

    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
        ByteBufUtils.writeTag(buffer, data);
    }

    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
        data = ByteBufUtils.readTag(buffer);
    }

    public void handleServerSide(EntityPlayer player)
    {}
}