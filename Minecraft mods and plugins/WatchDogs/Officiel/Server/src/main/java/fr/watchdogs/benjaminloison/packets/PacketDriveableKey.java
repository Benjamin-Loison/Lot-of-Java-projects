package fr.watchdogs.benjaminloison.packets;

import fr.watchdogs.benjaminloison.api.IControllable;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketDriveableKey extends PacketBase
{
    public int key;

    public PacketDriveableKey()
    {}

    public PacketDriveableKey(int k)
    {
        key = k;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        data.writeInt(key);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        key = data.readInt();
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity)
    {
        if(playerEntity.ridingEntity != null && playerEntity.ridingEntity instanceof IControllable)
            ((IControllable)playerEntity.ridingEntity).pressKey(key, playerEntity);
    }
}