package fr.pnjmarket.benjaminloison.common;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;

public class PacketKillNPC implements IMessage
{

    private int uuid;

    public PacketKillNPC(int uuid)
    {
        this.uuid = uuid;
    }

    public PacketKillNPC()
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.uuid = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.uuid);
    }

    public static class Handler implements IMessageHandler<PacketKillNPC, IMessage>
    {
        @Override
        public IMessage onMessage(PacketKillNPC message, MessageContext ctx)
        {
            Entity ent = ctx.getServerHandler().playerEntity.worldObj.getEntityByID(message.uuid);
            if(ent != null)
            {
                ent.setDead();
            }
            return null;
        }

    }

}
