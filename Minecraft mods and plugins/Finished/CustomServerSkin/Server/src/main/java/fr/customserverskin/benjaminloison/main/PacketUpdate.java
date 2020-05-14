package fr.customserverskin.benjaminloison.main;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PacketUpdate implements IMessage
{
    static String user, skin;

    public PacketUpdate()
    {}

    public PacketUpdate(String user, String skin)
    {
        this.user = user;
        this.skin = skin;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeUTF8String(buf, user);
        ByteBufUtils.writeUTF8String(buf, skin);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {}

    public static class Handler implements IMessageHandler<PacketUpdate, IMessage>
    {
        @Override
        public IMessage onMessage(PacketUpdate message, MessageContext ctx)
        {
            return null;
        }
    }
}