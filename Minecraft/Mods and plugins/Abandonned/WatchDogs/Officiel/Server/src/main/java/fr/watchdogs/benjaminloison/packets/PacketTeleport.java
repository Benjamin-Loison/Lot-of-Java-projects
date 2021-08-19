package fr.altiscraft.benjaminloison.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.watchdogs.benjaminloison.api.FileAPI;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketTeleport implements IMessage
{
    static int x, y, z;

    public PacketTeleport()
    {}

    @Override
    public void toBytes(ByteBuf buf)
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
    }

    public static class Handler implements IMessageHandler<PacketTeleport, IMessage>
    {
        @Override
        public IMessage onMessage(PacketTeleport message, MessageContext ctx)
        {
            EntityPlayerMP entity = ctx.getServerHandler().playerEntity;
            if(entity.getDistance(FileAPI.configNumberDouble("spawn.x"), FileAPI.configNumberDouble("spawn.y"), FileAPI.configNumberDouble("spawn.z")) < FileAPI.configNumber("spawn.range"))
                entity.setPositionAndUpdate(x, y, z);
            return null;
        }
    }
}