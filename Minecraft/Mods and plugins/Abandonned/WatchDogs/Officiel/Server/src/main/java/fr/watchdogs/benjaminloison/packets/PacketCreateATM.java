package fr.watchdogs.benjaminloison.packets;

import java.io.File;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.watchdogs.benjaminloison.api.FileAPI;
import io.netty.buffer.ByteBuf;

public class PacketCreateATM implements IMessage
{
    static boolean create;
    static int x, y, z;

    public PacketCreateATM()
    {}

    @Override
    public void toBytes(ByteBuf buf)
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        create = buf.readBoolean();
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
    }

    public static class Handler implements IMessageHandler<PacketCreateATM, IMessage>
    {
        @Override
        public IMessage onMessage(PacketCreateATM message, MessageContext ctx)
        {
            if(create)
                FileAPI.create(0, new File(FileAPI.gang + x + "," + y + "," + z + ".txt"));
            else
                new File(FileAPI.gang + x + "," + y + "," + z + ".txt").delete();
            return null;
        }
    }
}