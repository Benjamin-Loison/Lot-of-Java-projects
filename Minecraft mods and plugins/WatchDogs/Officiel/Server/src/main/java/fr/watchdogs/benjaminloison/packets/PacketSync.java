package fr.watchdogs.benjaminloison.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.watchdogs.benjaminloison.entity.EEP;
import io.netty.buffer.ByteBuf;

public class PacketSync implements IMessage
{
    static String name;

    public PacketSync()
    {}

    @Override
    public void toBytes(ByteBuf buf)
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        name = ByteBufUtils.readUTF8String(buf);
    }

    public static class Handler implements IMessageHandler<PacketSync, IMessage>
    {
        @Override
        public IMessage onMessage(PacketSync message, MessageContext ctx)
        {
            EEP.get(ctx.getServerHandler().playerEntity.worldObj.getPlayerEntityByName(name)).sync();
            return null;
        }
    }
}