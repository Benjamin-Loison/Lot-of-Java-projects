package fr.altiscraft.benjaminloison.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.watchdogs.benjaminloison.entity.EEP;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketRestriction implements IMessage
{
    static String entity;
    static boolean cuffed, escorted;

    public PacketRestriction()
    {}

    public PacketRestriction(String entity, boolean cuffed, boolean escorted)
    {
        this.entity = entity;
        this.cuffed = cuffed;
        this.escorted = escorted;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        entity = ByteBufUtils.readUTF8String(buf);
        cuffed = buf.readBoolean();
        escorted = buf.readBoolean();
    }

    public static class Handler implements IMessageHandler<PacketRestriction, IMessage>
    {
        @Override
        public IMessage onMessage(PacketRestriction message, MessageContext ctx)
        {
            EntityPlayerMP policeman = ctx.getServerHandler().playerEntity;
            if(!EEP.get(policeman).police && !EEP.get(policeman).staff)
                return null;
            if(policeman.worldObj.getPlayerEntityByName(entity) instanceof EntityPlayer)
            {
                EEP props = EEP.get((EntityPlayer)policeman.worldObj.getPlayerEntityByName(entity));
                props.set("cuffed", cuffed);
                props.set("escorted", escorted);
            }
            return null;
        }
    }
}