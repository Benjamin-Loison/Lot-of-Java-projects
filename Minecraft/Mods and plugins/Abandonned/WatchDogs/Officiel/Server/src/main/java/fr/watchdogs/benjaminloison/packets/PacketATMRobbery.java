package fr.watchdogs.benjaminloison.packets;

import java.io.File;
import java.util.List;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.api.PresenceAPI;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.entity.EEP;
import fr.watchdogs.benjaminloison.events.ServerTickHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class PacketATMRobbery implements IMessage
{
    static boolean robbery;
    static int x, y, z;

    public PacketATMRobbery()
    {}

    @Override
    public void toBytes(ByteBuf buf)
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        robbery = buf.readBoolean();
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
    }

    public static class Handler implements IMessageHandler<PacketATMRobbery, IMessage>
    {
        @Override
        public IMessage onMessage(PacketATMRobbery m, MessageContext ctx)
        {
            EntityPlayerMP p = ctx.getServerHandler().playerEntity;
            if(robbery)
                if(!ServerTickHandler.robbery)
                    if(PresenceAPI.main(FileAPI.configNumber("amount.police.required.for.robbery"), "police"))
                        try
                        {
                            if(Long.parseLong(FileAPI.read(new File(FileAPI.atm + x + "," + y + "," + z + ".txt"))) >= 0)
                            {
                                WatchDogs.instance.network.sendTo(new PacketMessage("beginning.of.the.robbery"), p);
                                for(EntityPlayerMP player : (List<EntityPlayerMP>)MinecraftServer.getServer().getConfigurationManager().playerEntityList)
                                {
                                    if(FileAPI.config("bank").equals(x + "," + y + "," + z))
                                        WatchDogs.instance.network.sendTo(new PacketMessage("bank.robbery"), player);
                                    if(EEP.get(player).police)
                                        WatchDogs.instance.network.sendTo(new PacketMessage("message.police.atm.robbery", p.getDisplayName(), p.getEntityId(), x, y, z), player);
                                }
                                ServerTickHandler.atmRobbery(p, x, y, z);
                                p.destroyCurrentEquippedItem();
                            }
                            else
                                WatchDogs.instance.network.sendTo(new PacketMessage("you.can.not.robber.this"), p);
                        }
                        catch(Exception e)
                        {}
                    else
                        WatchDogs.instance.network.sendTo(new PacketMessage("must.have.amount.police.required.for.robbery.connected"), p);
                else
                    WatchDogs.instance.network.sendTo(new PacketMessage("already.a.robbery.in.progress"), p);
            else if(ServerTickHandler.robbery)
            {
                ServerTickHandler.robbery = false;
                for(EntityPlayerMP player : (List<EntityPlayerMP>)MinecraftServer.getServer().getConfigurationManager().playerEntityList)
                    if(EEP.get(player).police)
                        WatchDogs.instance.network.sendTo(new PacketMessage("atm.robbery.finished"), player);
            }
            return null;
        }
    }
}