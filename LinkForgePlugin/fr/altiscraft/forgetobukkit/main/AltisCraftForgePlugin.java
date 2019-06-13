package fr.altiscraft.forgetobukkit.main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class AltisCraftForgePlugin extends JavaPlugin {
	
	@Override
	public void onEnable() 
	{
		Listener handler = new EventHandler(this, this.getLogger());
		//Bukkit.getMessenger().registerOutgoingPluginChannel(this, "ForgeToBukkit");
		Bukkit.getMessenger().registerIncomingPluginChannel(this, "ForgeToBukkit", new PacketListener(this));
		this.getLogger().info("[ACPM] Plugin Successfully Enabled!");
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	@Override
	public void onDisable() 
	{
	}

}
