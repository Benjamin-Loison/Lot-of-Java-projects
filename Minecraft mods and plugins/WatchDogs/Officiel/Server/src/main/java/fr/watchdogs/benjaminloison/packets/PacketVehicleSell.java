package fr.altiscraft.benjaminloison.packets;

import java.io.File;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.driveables.PlaneType;
import fr.watchdogs.benjaminloison.entity.EEP;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;

public class PacketVehicleSell implements IMessage
{
    static String carShop;
    static int id;

    public PacketVehicleSell()
    {}

    @Override
    public void toBytes(ByteBuf buf)
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        carShop = ByteBufUtils.readUTF8String(buf);
    }

    public static class Handler implements IMessageHandler<PacketVehicleSell, IMessage>
    {
        @Override
        public IMessage onMessage(PacketVehicleSell message, MessageContext ctx)
        {
            // TODO: A faire
            try
            {
                String[] parts = FileAPI.read(new File(FileAPI.carShop), id).split(" ");
                EntityPlayer player = ctx.getServerHandler().playerEntity;
                if(PlaneType.getPlane(parts[0]) != null)
                    FileAPI.removeLign(carShop, new File(FileAPI.airGarage + player.getDisplayName() + ".txt"));
                else
                    FileAPI.removeLign(carShop, new File(FileAPI.landGarage + player.getDisplayName() + ".txt"));
                EEP.get(player).addMoney(Long.parseLong(parts[1]));
                player.addChatMessage(new ChatComponentText(new ChatComponentTranslation("you.have.earned") + FileAPI.number(Long.parseLong(parts[1])) + new ChatComponentTranslation("money.amount")));
            }
            catch(Exception e)
            {}
            return null;
        }
    }
}