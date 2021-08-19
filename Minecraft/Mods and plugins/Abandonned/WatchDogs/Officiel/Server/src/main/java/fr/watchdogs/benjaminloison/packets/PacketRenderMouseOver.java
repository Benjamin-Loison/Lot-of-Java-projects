package fr.altiscraft.benjaminloison.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PacketRenderMouseOver implements IMessage
{
    boolean police, cuffed, escorted;

    public PacketRenderMouseOver(boolean police, boolean cuffed, boolean escorted)
    {
        this.police = police;
        this.cuffed = cuffed;
        this.escorted = escorted;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeBoolean(police);
        buf.writeBoolean(cuffed);
        buf.writeBoolean(escorted);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {}

    public static class Handler implements IMessageHandler<PacketRenderMouseOver, IMessage>
    {
        @Override
        public IMessage onMessage(PacketRenderMouseOver message, MessageContext ctx)
        {
            return null;
        }
    }
}