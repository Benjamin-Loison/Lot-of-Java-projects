package fr.watchdogs.benjaminloison.packets;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.watchdogs.benjaminloison.entity.EEP;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;

public class PacketSound implements IMessage
{
    static boolean horn;

    public PacketSound()
    {}

    public PacketSound(boolean horn)
    {
        this.horn = horn;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        horn = buf.readBoolean();
    }

    public static class Handler implements IMessageHandler<PacketSound, IMessage>
    {
        @Override
        public IMessage onMessage(PacketSound message, MessageContext ctx)
        {
            EntityPlayerMP driver = ctx.getServerHandler().playerEntity;
            if(driver.ridingEntity == null)
                return null;
            if(horn)
            {
                for(EntityPlayerMP player : (List<EntityPlayerMP>)MinecraftServer.getServer().getConfigurationManager().playerEntityList)
                    if(player.getDistanceToEntity(driver) < 40)
                        player.addChatMessage(new ChatComponentText("!HORN!" + new Random().nextInt(3)));
            }
            else if(!EEP.get(driver).police && !EEP.get(driver).doctor)
                return null;
            else
                for(EntityPlayerMP player : (List<EntityPlayerMP>)MinecraftServer.getServer().getConfigurationManager().playerEntityList)
                    if(player.getDistanceToEntity(driver) < 75)
                        player.addChatMessage(new ChatComponentText("!SIREN!"));
            return null;
        }
    }
}