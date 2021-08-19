package fr.watchdogs.benjaminloison.packets;

import java.util.List;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.watchdogs.benjaminloison.api.LocationAPI;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.entity.EEP;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class PacketGPS implements IMessage
{
    public PacketGPS()
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {}

    @Override
    public void toBytes(ByteBuf buf)
    {}

    public static class Handler implements IMessageHandler<PacketGPS, IMessage>
    {

        @Override
        public IMessage onMessage(PacketGPS message, MessageContext ctx)
        {
            EntityPlayerMP gangPlayer = ctx.getServerHandler().playerEntity;
            int size = 0;
            for(EntityPlayerMP player : (List<EntityPlayerMP>)MinecraftServer.getServer().getConfigurationManager().playerEntityList)
                if(EEP.get(player).gang.equals(EEP.get(gangPlayer).gang))
                    if(player.inventory.hasItem(WatchDogs.gangGPS))
                        size++;
            String[] name = new String[size];
            LocationAPI[] locations = new LocationAPI[size];
            size = 0;
            for(EntityPlayerMP player : (List<EntityPlayerMP>)MinecraftServer.getServer().getConfigurationManager().playerEntityList)
                if(EEP.get(player).gang.equals(EEP.get(gangPlayer).gang))
                    if(player.inventory.hasItem(WatchDogs.gangGPS))
                    {
                        name[size] = player.getDisplayName();
                        locations[size] = new LocationAPI((int)player.posX, (int)player.posY, (int)player.posZ, (int)player.rotationYaw);
                        size++;
                    }
            WatchDogs.instance.network.sendTo(new PacketRenderGPS(name, locations), gangPlayer);
            return null;
        }
    }
}