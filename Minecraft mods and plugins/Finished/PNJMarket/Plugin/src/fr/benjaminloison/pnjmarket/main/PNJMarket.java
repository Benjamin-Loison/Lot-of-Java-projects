package fr.benjaminloison.pnjmarket.main;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;

public class PNJMarket extends JavaPlugin
{
    public static final String MODID = "pnjmarket", NAME = "PNJMarket";
    static PNJMarket plugin;

    @Override
    public void onEnable()
    {
        plugin = this;
        print("Launching...");
        // minecraft_189016
        if(!new File("").getAbsolutePath().contains(new String(new byte[] {0x6d, 0x69, 0x6e, 0x65, 0x63, 0x72, 0x61, 0x66, 0x74, 0x5f, 0x31, 0x38, 0x39, 0x30, 0x31, 0x36}, Charsets.UTF_8)))
            Throwables.propagate(new Throwable("Server non authorized !"));
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "PNJMarket", new PacketListener(this));
        print("Launched !");
    }

    @Override
    public void onDisable()
    {
        print("Disabled !");
    }

    public static void print(Object object)
    {
        plugin.getLogger().info(object.toString());
    }
}