package fr.chequemod.benjaminloison.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PacketKillNPC implements IMessage
{
    int id;

    public PacketKillNPC(int id)
    {
        this.id = id;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(id);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {}

    public static class Handler implements IMessageHandler<PacketKillNPC, IMessage>
    {
        @Override
        public IMessage onMessage(PacketKillNPC message, MessageContext ctx)
        {
            return null;
        }
    }
}