package fr.benjaminloison.forevercraft.main;

import java.io.File;
import java.util.Date;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import fr.benjaminloison.forevercraft.api.FileAPI;

public class Forevercraft extends JavaPlugin
{
    // free houses but one per person
    
    public static final String MODID = "forevercraft", NAME = "Forevercraft", ID = "FOREVERCRAFT_";
    public File config = new File(FileAPI.plac + "config.yml");
    static Forevercraft plugin;

    public WorldGuardPlugin getWorldGuard()
    {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
        if(plugin == null || !(plugin instanceof WorldGuardPlugin))
            return null;
        return (WorldGuardPlugin)plugin;
    }

    public WorldEditPlugin getWorldEdit()
    {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldEdit");
        if(plugin == null || !(plugin instanceof WorldEditPlugin))
            return null;
        return (WorldEditPlugin)plugin;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onEnable()
    {
        plugin = this;
        print("Launching...");
        // minecraft_189016
        if(!new File("").getAbsolutePath().contains(new String(new byte[] {0x6d, 0x69, 0x6e, 0x65, 0x63, 0x72, 0x61, 0x66, 0x74, 0x5f, 0x31, 0x38, 0x39, 0x30, 0x31, 0x36}, Charsets.UTF_8)))
            Throwables.propagate(new Throwable("Server non authorized !"));
        FileAPI.languageFile.getParentFile().mkdirs();
        new EventController(this);
        FileAPI.ifNotExisteWrite(config, new Date().toLocaleString());
        print("Launched !");
    }

    @Override
    public void onDisable()
    {
        print("Disabled !");
    }

    public void print(Object object)
    {
        plugin.getLogger().info(object.toString());
    }
}