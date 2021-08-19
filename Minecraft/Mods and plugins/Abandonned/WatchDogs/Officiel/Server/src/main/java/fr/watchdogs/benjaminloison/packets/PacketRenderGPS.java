package fr.altiscraft.benjaminloison.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.watchdogs.benjaminloison.api.LocationAPI;
import io.netty.buffer.ByteBuf;

public class PacketRenderGPS implements IMessage
{
    String[] names;
    LocationAPI[] positions;
    int number;

    public PacketRenderGPS(String[] names, LocationAPI[] positions)
    {
        number = names.length;
        this.names = names;
        this.positions = positions;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(number);
        for(int i = 0; i < number; i++)
            ByteBufUtils.writeUTF8String(buf, names[i]);
        for(int i = 0; i < number; i++)
        {
            buf.writeInt(positions[i].getX());
            buf.writeInt(positions[i].getY());
            buf.writeInt(positions[i].getZ());
            ByteBufUtils.writeUTF8String(buf, positions[i].getDirection());
        }
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {}

    public static class Handler implements IMessageHandler<PacketRenderGPS, IMessage>
    {
        @Override
        public IMessage onMessage(PacketRenderGPS message, MessageContext ctx)
        {
            return null;
        }
    }
}