package fr.watchdogs.benjaminloison.common.teams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import cpw.mods.fml.relauncher.Side;
import fr.watchdogs.benjaminloison.driveables.EntityDriveable;
import fr.watchdogs.benjaminloison.driveables.EntitySeat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class PlayerHandler
{
    public static Map<String, PlayerData> serverSideData = new HashMap<String, PlayerData>();
    public static ArrayList<String> clientsToRemoveAfterThisRound = new ArrayList<String>();

    public PlayerHandler()
    {
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void onEntityHurt(LivingAttackEvent event)
    {
        if(event instanceof LivingAttackEvent && (event.entityLiving.ridingEntity instanceof EntityDriveable || event.entityLiving.ridingEntity instanceof EntitySeat))
            event.setCanceled(true);
    }

    @SubscribeEvent
    public void onEntityKilled(LivingDeathEvent event)
    {
        if(event.entityLiving instanceof EntityPlayer)
            getPlayerData((EntityPlayer)event.entityLiving).playerKilled();
    }

    public void serverTick()
    {
        for(WorldServer world : MinecraftServer.getServer().worldServers)
            for(Object player : world.playerEntities)
                if(player instanceof EntityPlayer)
                    if(getPlayerData((EntityPlayer)player) != null)
                        getPlayerData((EntityPlayer)player).tick((EntityPlayer)player);
    }

    public static PlayerData getPlayerData(EntityPlayer player)
    {
        if(player == null)
            return null;
        return getPlayerData(player.getCommandSenderName(), Side.SERVER);
    }

    public static PlayerData getPlayerData(String username)
    {
        return getPlayerData(username, Side.SERVER);
    }

    public static PlayerData getPlayerData(EntityPlayer player, Side side)
    {
        if(player == null)
            return null;
        return getPlayerData(player.getCommandSenderName(), side);
    }

    public static PlayerData getPlayerData(String username, Side side)
    {
        if(!serverSideData.containsKey(username))
            serverSideData.put(username, new PlayerData(username));
        return serverSideData.get(username);
    }

    @SubscribeEvent
    public void onPlayerEvent(PlayerEvent event)
    {
        if(event instanceof PlayerLoggedInEvent)
        {
            String username = event.player.getCommandSenderName();
            if(!serverSideData.containsKey(username))
                serverSideData.put(username, new PlayerData(username));
        }
        else if(event instanceof PlayerRespawnEvent)
        {
            String username = event.player.getCommandSenderName();
            if(!serverSideData.containsKey(username))
                serverSideData.put(username, new PlayerData(username));
        }
    }
}