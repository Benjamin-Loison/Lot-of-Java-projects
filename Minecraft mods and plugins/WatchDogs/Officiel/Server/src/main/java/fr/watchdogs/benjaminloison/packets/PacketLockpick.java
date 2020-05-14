package fr.altiscraft.benjaminloison.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.watchdogs.benjaminloison.api.DebugEntityAPI;
import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.entity.EEP;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;

public class PacketLockpick implements IMessage
{
    static int id;

    public PacketLockpick()
    {}

    @Override
    public void toBytes(ByteBuf buf)
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
    }

    public static class Handler implements IMessageHandler<PacketLockpick, IMessage>
    {
        @Override
        public IMessage onMessage(PacketLockpick message, MessageContext ctx)
        {
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            if(DebugEntityAPI.isEntity(player.worldObj.getEntityByID(id)))
                DebugEntityAPI.getDriveable(player.worldObj.getEntityByID(id)).rideable = true;
            EEP.get(player).addInterpol(new ChatComponentTranslation("carjacking").getFormattedText(), FileAPI.configNumber("fine.carjacking"));
            return null;
        }
    }
}