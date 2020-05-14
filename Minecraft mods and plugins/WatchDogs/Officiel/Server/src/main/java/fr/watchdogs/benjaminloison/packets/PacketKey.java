package fr.altiscraft.benjaminloison.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.watchdogs.benjaminloison.api.DebugEntityAPI;
import fr.watchdogs.benjaminloison.driveables.EntityDriveable;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;

public class PacketKey implements IMessage
{
    static boolean set;
    static int id;
    static String newOwner;

    public PacketKey()
    {}

    @Override
    public void toBytes(ByteBuf buf)
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        newOwner = ByteBufUtils.readUTF8String(buf);
    }

    public static class Handler implements IMessageHandler<PacketKey, IMessage>
    {
        @Override
        public IMessage onMessage(PacketKey message, MessageContext ctx)
        {
            EntityPlayerMP owner = ctx.getServerHandler().playerEntity;
            if(DebugEntityAPI.isEntity(owner.worldObj.getEntityByID(id)))
            {
                EntityDriveable drive = DebugEntityAPI.getDriveable(owner.worldObj.getEntityByID(id));
                for(int x = 0; x <= 5; x++)
                    if(drive.owners[x].equals("") && !set)
                    {
                        drive.owners[x] = newOwner;
                        set = true;
                        owner.worldObj.getPlayerEntityByName(newOwner).addChatMessage(new ChatComponentText(new ChatComponentTranslation("you.have.received.the.keys.of") + drive.getDriveableType().shortName));
                    }
            }
            if(!set)
                owner.addChatMessage(new ChatComponentTranslation("the.player.has.not.received.the.keys"));
            return null;
        }
    }
}