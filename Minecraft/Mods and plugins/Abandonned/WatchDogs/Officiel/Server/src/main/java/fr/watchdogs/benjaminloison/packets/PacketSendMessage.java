package fr.altiscraft.benjaminloison.packets;

import java.util.List;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.entity.EEP;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;

public class PacketSendMessage implements IMessage
{
    static String message, perm;
    static Object[] objects;
    static int numberObjects;

    public PacketSendMessage()
    {}

    public PacketSendMessage(String message, String perm)
    {
        this.message = message;
        this.perm = perm;
        numberObjects = 0;
    }

    public PacketSendMessage(String message)
    {
        this.message = message;
        perm = "";
        numberObjects = 0;
    }

    public PacketSendMessage(String perm, Object... objects)
    {
        message = "";
        this.perm = perm;
        this.objects = objects;
        numberObjects = this.objects.length;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeUTF8String(buf, message);
        ByteBufUtils.writeUTF8String(buf, perm);
        buf.writeInt(numberObjects);
        if(objects != null)
            for(int i = 0; i < objects.length; i++)
                ByteBufUtils.writeUTF8String(buf, objects[i].toString());
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        message = ByteBufUtils.readUTF8String(buf);
        perm = ByteBufUtils.readUTF8String(buf);
        numberObjects = buf.readInt();
        for(int i = 0; i < numberObjects; i++)
            objects[i] = ByteBufUtils.readUTF8String(buf);
    }

    public static class Handler implements IMessageHandler<PacketSendMessage, IMessage>
    {
        @Override
        public IMessage onMessage(PacketSendMessage msg, MessageContext ctx)
        {
            Object[] objs = null;
            if(objects != null)
            {
                objs = new Object[objects.length - 1];
                for(int i = 1; i < objects.length; i++)
                    objs[i - 1] = objects[i];
            }
            if(message.length() < FileAPI.configNumber("minimum.message.length"))
                return null;
            for(EntityPlayerMP player : (List<EntityPlayerMP>)MinecraftServer.getServer().getConfigurationManager().playerEntityList)
                if(perm.equals(""))
                    if(objs != null)
                        AltisCraft.instance.network.sendTo(new PacketMessage(objects[0], objs), player);
                    else
                        player.addChatMessage(new ChatComponentTranslation(message));
                else if(EEP.get(player).get(perm))
                    if(objects != null)
                        AltisCraft.instance.network.sendTo(new PacketMessage(objects[0], objs), player);
                    else
                        player.addChatMessage(new ChatComponentTranslation(message));

            AltisCraft.instance.network.sendTo(new PacketMessage("message.has.been.sent"), ctx.getServerHandler().playerEntity);
            return null;
        }
    }
}