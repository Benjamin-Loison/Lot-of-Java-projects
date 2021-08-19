package fr.benjaminloison.pnjmarket.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteStreams;

public class PacketListener implements PluginMessageListener
{
    PNJMarket pl;

    public PacketListener(PNJMarket plugin)
    {
        this.pl = plugin;
    }

    @Override
    public void onPluginMessageReceived(String msg, Player p, byte[] message)
    {
        String name = p.getName(), inf = ByteStreams.newDataInput(message).readUTF();
        if(msg.equals("PNJMarket"))
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "chestcommands open " + inf + " " + name);
    }
}