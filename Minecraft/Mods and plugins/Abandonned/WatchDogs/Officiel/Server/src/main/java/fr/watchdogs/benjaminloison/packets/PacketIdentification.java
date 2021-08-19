package fr.watchdogs.benjaminloison.packets;

import org.apache.commons.lang3.StringUtils;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;

public class PacketIdentification implements IMessage
{
    static String identification;

    public PacketIdentification()
    {}

    @Override
    public void toBytes(ByteBuf buf)
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        identification = ByteBufUtils.readUTF8String(buf);
    }

    public static class Handler implements IMessageHandler<PacketIdentification, IMessage>
    {
        @Override
        public IMessage onMessage(PacketIdentification message, MessageContext ctx)
        {
            if(!identification.equals(""))
            {
                EntityPlayerMP player = ctx.getServerHandler().playerEntity;
                if(StringUtils.isNumeric(identification))
                {
                    int id = Integer.parseInt(identification);
                    Entity entity = player.worldObj.getEntityByID(id);
                    if(entity instanceof EntityPlayer)
                        player.addChatMessage(new ChatComponentTranslation("message.identification", id, ((EntityPlayer)entity).getDisplayName()));
                    else
                        player.addChatMessage(new ChatComponentTranslation("id.invalid"));
                }
                else
                {
                    Entity entity = player.worldObj.getPlayerEntityByName(identification);
                    if(entity instanceof EntityPlayer)
                        player.addChatMessage(new ChatComponentTranslation("message.identification", entity.getEntityId(), identification));
                    else
                        player.addChatMessage(new ChatComponentTranslation("name.invalid"));
                }
            }
            return null;
        }
    }
}