package fr.altiscraft.benjaminloison.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;

public class PacketNPC implements IMessage
{
    static int id, key;
    static String value;

    public PacketNPC()
    {}

    @Override
    public void toBytes(ByteBuf buf)
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        key = buf.readInt();
        value = ByteBufUtils.readUTF8String(buf);
    }

    public static class Handler implements IMessageHandler<PacketNPC, IMessage>
    {
        @Override
        public IMessage onMessage(PacketNPC message, MessageContext ctx)
        {
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            if(!MinecraftServer.getServer().getConfigurationManager().func_152596_g((player).getGameProfile()))
            {
                player.addChatMessage(new ChatComponentTranslation("you.are.not.op"));
                return null;
            }
            Entity entity = player.worldObj.getEntityByID(id);
            if(value.equals("") && entity != null)
                entity.setDead();
            else if(key > 19 && entity != null)
                entity.getDataWatcher().updateObject(key, value);
            return null;
        }
    }
}