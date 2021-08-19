package fr.altiscraft.benjaminloison.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.entity.EEP;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;

public class PacketPlayerFine implements IMessage
{
    static String name;

    public PacketPlayerFine()
    {}

    @Override
    public void toBytes(ByteBuf buf)
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        name = ByteBufUtils.readUTF8String(buf);
    }

    public static class Handler implements IMessageHandler<PacketPlayerFine, IMessage>
    {
        @Override
        public IMessage onMessage(PacketPlayerFine message, MessageContext ctx)
        {
            EntityPlayerMP policeman = ctx.getServerHandler().playerEntity;
            if(!EEP.get(policeman).police)
                return null;
            if(policeman.worldObj.getPlayerEntityByName(name) instanceof EntityPlayer)
            {
                EntityPlayer victim = (EntityPlayer)policeman.worldObj.getPlayerEntityByName(name);
                EEP props = EEP.get(victim);
                if(victim.getDistanceSqToEntity(policeman) < FileAPI.configNumber("range.maximum.to.fine"))
                {
                    victim.addChatMessage(new ChatComponentText("!FINE! " + policeman.getDisplayName() + " " + props.allMoneyInterpol() + " " + props.searchAllReasons()));
                    policeman.addChatMessage(new ChatComponentTranslation("fine.sent"));
                }
                else
                    policeman.addChatMessage(new ChatComponentTranslation("too.far"));
            }
            return null;
        }
    }
}