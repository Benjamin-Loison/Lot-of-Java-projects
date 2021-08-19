package fr.pnjmarket.benjaminloison.common;

import java.util.List;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;

public class PacketSyncDW implements IMessage
{
    private String x;
    private int uuid, z;

    public PacketSyncDW()
    {}

    public PacketSyncDW(String x, int uuid, int z)
    {
        this.x = x;
        this.uuid = uuid;
        this.z = z;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.x = ByteBufUtils.readUTF8String(buf);
        this.uuid = buf.readInt();
        this.z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeUTF8String(buf, this.x);
        buf.writeInt(this.uuid);
        buf.writeInt(this.z);
    }

    public static class Handler implements IMessageHandler<PacketSyncDW, IMessage>
    {
        @Override
        public IMessage onMessage(PacketSyncDW message, MessageContext ctx)
        {
            List<Entity> entlist = ctx.getServerHandler().playerEntity.getEntityWorld().loadedEntityList;
            for(Entity entity : entlist)
            {
                if(entity instanceof EntityMarketPNJ)
                {
                    if(entity.getEntityId() == message.uuid)
                    {
                        entity.getDataWatcher().updateObject(message.z, message.x);
                    }
                }
            }
            return null;
        }
    }

}
