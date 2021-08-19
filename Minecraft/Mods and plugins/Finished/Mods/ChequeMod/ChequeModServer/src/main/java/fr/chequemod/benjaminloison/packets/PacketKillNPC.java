package fr.chequemod.benjaminloison.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;

public class PacketKillNPC implements IMessage
{
    static int id;

    @Override
    public void toBytes(ByteBuf buf)
    {}
    
    @Override
    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
    }

    public static class Handler implements IMessageHandler<PacketKillNPC, IMessage>
    {
        @Override
        public IMessage onMessage(PacketKillNPC message, MessageContext ctx)
        {
            Entity entity = ctx.getServerHandler().playerEntity.worldObj.getEntityByID(id);
            if(entity != null)
                entity.setDead();
            return null;
        }
    }
}