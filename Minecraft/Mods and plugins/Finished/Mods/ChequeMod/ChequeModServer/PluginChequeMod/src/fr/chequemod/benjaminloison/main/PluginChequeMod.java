package fr.chequemod.benjaminloison.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.base.Throwables;

public class PluginChequeMod extends JavaPlugin
{
    public static final String NAME = "ChequeMod";

    @Override
    public void onEnable()
    {
        // soft antitheft check (useless because code is now on GitHub)
        //if(!FileAPI.path.contains("Arcanite"))
        //    Throwables.propagate(new Exception("Server not authorized !"));
        FileAPI.createLanguageFile();
        Bukkit.getMessenger().registerIncomingPluginChannel(this, NAME, new PacketListener(this));
    }
}
