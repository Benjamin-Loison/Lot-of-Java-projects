package fr.altiscraft.forgetobukkit.main;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class EventHandler implements Listener 
{
	Plugin plugin;
	Logger log;
	
	public EventHandler(Plugin p, Logger l) 
	{
		PluginManager pm = p.getServer().getPluginManager();
		pm.registerEvents(this, p);
		plugin = p;
		log = l;
	}
	
	@org.bukkit.event.EventHandler
	public void onBlockBreakEvent(PlayerJoinEvent event)
	{
		Location loc = new Location(event.getPlayer().getWorld(), 0, 101, 0);
		event.getPlayer().teleport(loc);
	}

}
