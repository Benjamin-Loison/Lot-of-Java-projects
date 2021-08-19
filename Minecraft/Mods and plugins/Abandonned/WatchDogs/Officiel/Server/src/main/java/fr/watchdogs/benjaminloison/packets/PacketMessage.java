package fr.altiscraft.benjaminloison.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PacketMessage implements IMessage
{
    String message;
    Object[] objects;
    long amount;
    int sizeObjects;

    public PacketMessage()
    {}

    public PacketMessage(String message)
    {
        this.message = message;
        amount = 0;
    }

    public PacketMessage(Object... objects)
    {
        message = "";
        amount = 0;
        this.objects = objects;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeUTF8String(buf, message);
        buf.writeLong(amount);
        if(objects != null && objects.length != 0)
        {
            buf.writeInt(objects.length);
            for(int i = 0; i < objects.length; i++)
                ByteBufUtils.writeUTF8String(buf, objects[i].toString());
        }
        else
            buf.writeInt(0);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {}

    public static class Handler implements IMessageHandler<PacketMessage, IMessage>
    {
        @Override
        public IMessage onMessage(PacketMessage msg, MessageContext ctx)
        {
            return null;
        }
    }
}