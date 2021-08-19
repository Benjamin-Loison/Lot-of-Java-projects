package fr.altiscraft.benjaminloison.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.watchdogs.benjaminloison.entity.EEP;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketMouseOver implements IMessage
{
    static String name;

    public PacketMouseOver()
    {}

    @Override
    public void toBytes(ByteBuf buf)
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        name = ByteBufUtils.readUTF8String(buf);
    }

    public static class Handler implements IMessageHandler<PacketMouseOver, IMessage>
    {
        @Override
        public IMessage onMessage(PacketMouseOver message, MessageContext ctx)
        {
            EntityPlayerMP police = ctx.getServerHandler().playerEntity;
            if(!EEP.get(police).staff && !EEP.get(police).police)
                return null;
            EEP props = EEP.get(police.worldObj.getPlayerEntityByName(name));
            AltisCraft.instance.network.sendTo(new PacketRenderMouseOver(props.police, props.cuffed, props.escorted), (EntityPlayerMP)police);
            return null;
        }
    }
}