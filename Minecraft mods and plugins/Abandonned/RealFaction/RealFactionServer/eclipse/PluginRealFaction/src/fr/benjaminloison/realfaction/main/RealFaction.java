package fr.benjaminloison.realfaction.main;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.benjaminloison.realfaction.api.FileAPI;

public class RealFaction extends JavaPlugin
{
    public static final String PLUGIN_ID = "RF", NAME = "RealFaction";

    @Override
    public void onEnable()
    {
        FileAPI.initialise();
        new EventHandler(this);
        Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable()
        {
            public void run()
            {
                Date date = new Date();
                if(date.getHours() == 0 && date.getMinutes() == 0 && date.getSeconds() == 0)
                {
                    FileAPI.deathFile.delete();
                    print(FileAPI.translate("death.file.deleted"));
                }
            }
        }, 1l, 1l);
    }

    public void print(Object object)
    {
        getLogger().info(object.toString());
    }
}