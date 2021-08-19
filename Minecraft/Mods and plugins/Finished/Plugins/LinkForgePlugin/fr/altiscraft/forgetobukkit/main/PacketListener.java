package fr.altiscraft.forgetobukkit.main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.ScoreboardManager;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

public class PacketListener implements PluginMessageListener {
	
	Plugin pl;

	public PacketListener(Plugin plugin) {
		this.pl = plugin;
	}

	@Override
	public void onPluginMessageReceived(String msg, Player player, byte[] message) {
		FileConfiguration cfg = pl.getConfig();
		ArrayList<String> list = (ArrayList<String>) cfg.get("Permissions");
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		System.out.println(msg);
		String command = in.readUTF();
		//String perm = in.readUTF();
		String perm = in.readUTF();
		if(msg.equals("ForgeToBukkit"))
		{
			if(player.isOp())
			{
					if(list.contains(perm))
					{
                        if(perm.equalsIgnoreCase("Player"))
                        {
                            Bukkit.dispatchCommand(player, command);
                        }
                        else
                        {
                            ScoreboardManager boardm = Bukkit.getScoreboardManager();
                            Objective obj = boardm.getMainScoreboard().getObjective(perm);
                            if(obj.getScore(Bukkit.getOfflinePlayer(player.getUniqueId())).getScore() >= 1)
                            {
                                player.setOp(true);
                                Bukkit.dispatchCommand(player, command);
                            }
                            else
                            {
                                player.sendMessage(ChatColor.RED + "Vous ne pouvez pas ouvrir ce shop.");
                            }
                        }
					}
					else
					{
						player.sendMessage(ChatColor.RED + "Vous ne pouvez pas ouvrir ce shop.");
					}
			}
			else
			{
				try
				{
					if(list.contains(perm))
					{
                        if(perm.equalsIgnoreCase("Player"))
                        {
                        	player.setOp(true);
                            Bukkit.dispatchCommand(player, command);
                        }
                        else
                        {
                            ScoreboardManager boardm = Bukkit.getScoreboardManager();
                            Objective obj = boardm.getMainScoreboard().getObjective(perm);
                            if(obj.getScore(Bukkit.getOfflinePlayer(player.getUniqueId())).getScore() >= 1)
                            {
                                player.setOp(true);
                                Bukkit.dispatchCommand(player, command);
                            }
                            else
                            {
                                player.sendMessage(ChatColor.RED + "Vous ne pouvez pas ouvrir ce shop.");
                            }
                        }
					}
					else
					{
						player.sendMessage(ChatColor.RED + "Vous ne pouvez pas ouvrir ce shop.");
					}
				}finally
				{
					player.setOp(false);
				}
			}
		}
		//pl.getLogger().info("Message: " + msg + " Bytes: " + in.readUTF());
		
	}

}
