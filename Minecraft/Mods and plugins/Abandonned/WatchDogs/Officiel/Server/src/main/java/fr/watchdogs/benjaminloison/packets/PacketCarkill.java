package fr.watchdogs.benjaminloison.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.entity.EEP;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;

public class PacketCarkill implements IMessage
{
    static double x, y, z;
    static int id, damage;

    public PacketCarkill()
    {}

    @Override
    public void toBytes(ByteBuf buf)
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        x = buf.readDouble();
        y = buf.readDouble();
        z = buf.readDouble();
        id = buf.readInt();
        damage = buf.readInt();
    }

    public static class Handler implements IMessageHandler<PacketCarkill, IMessage>
    {
        @Override
        public IMessage onMessage(PacketCarkill message, MessageContext ctx)
        {
            Entity entity = ctx.getServerHandler().playerEntity.worldObj.getEntityByID(message.id);
            if(entity instanceof EntityPlayer)
            {
                EntityPlayer carHited = (EntityPlayer)entity;
                carHited.setPositionAndUpdate(x, y, z);
                carHited.setHealth(carHited.getHealth() - damage);
                if(carHited.getHealth() <= 0)
                    EEP.get(carHited).addInterpol(new ChatComponentTranslation("carkill").getFormattedText(), FileAPI.configNumber("fine.carkill"));
                else
                    EEP.get(carHited).addInterpol(new ChatComponentTranslation("carhit").getFormattedText(), FileAPI.configNumber("fine.carhit"));
            }
            return null;
        }
    }
}