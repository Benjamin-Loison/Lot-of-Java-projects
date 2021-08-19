package fr.benjaminloison.realfaction.main;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.benjaminloison.realfaction.api.FileAPI;

public class EventHandler implements Listener
{
    RealFaction pl;

    public EventHandler(RealFaction p)
    {
        p.getServer().getPluginManager().registerEvents(this, p);
        pl = p;
    }

    @org.bukkit.event.EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e)
    {
        String args[] = e.getMessage().split(" ");
        Player p = e.getPlayer();
        Location l = e.getPlayer().getLocation();
        int x = l.getBlockX(), y = l.getBlockY(), z = l.getBlockZ();
        World w = e.getPlayer().getWorld();
        if(args[0].equalsIgnoreCase("/rf"))
        {
            e.setCancelled(true);
            if(args.length > 1)
            {
                if(args[1].equalsIgnoreCase("help"))
                    p.sendMessage("/rf <help>");
            }
            else
            {
                p.sendMessage(FileAPI.translate("faction.is"));
            }
        }
    }

    @org.bukkit.event.EventHandler
    public void onPlayerDeath(PlayerDeathEvent e)
    {
        Player p = e.getEntity();
        FileAPI.write("\n" + p.getName(), FileAPI.deathFile);
        p.kickPlayer("[" + RealFaction.NAME + "]" + FileAPI.translate("you.are.dead.wait.minuit"));
    }

    @org.bukkit.event.EventHandler
    public void onBlockBreaking(BlockDamageEvent e)
    {
        Material m = e.getBlock().getType();
        e.getPlayer().sendMessage(m + "");
        if(m == Material.COAL_ORE || m == Material.DIAMOND_ORE || m == Material.EMERALD_ORE || m == Material.GLOWING_REDSTONE_ORE || m == Material.GOLD_ORE || m == Material.IRON_ORE || m == Material.LAPIS_ORE || m == Material.REDSTONE_ORE || m == Material.STONE || m == Material.COBBLESTONE || m == Material.REDSTONE_BLOCK || m == Material.IRON_BLOCK || m == Material.GOLD_BLOCK || m == Material.DIAMOND_BLOCK || m == Material.EMERALD_BLOCK || m == Material.LAPIS_BLOCK)
        {
            if(new Random().nextInt(25) == 0)
            {
                e.getPlayer().sendMessage("Punch !");
                e.getBlock().
            }
            else
            {
                e.getPlayer().sendMessage("Failed !");
                e.setCancelled(true);
            }
        }

    }

    @org.bukkit.event.EventHandler
    public void onBlockBreak(BlockBreakEvent e)
    {
        /*
         * Block b = e.getBlock();
         * Material m = b.getType();
         * World w = b.getWorld();
         * Player p = e.getPlayer();
         * int x = b.getX(), y = b.getY(), z = b.getZ();
         * if(new Random().nextInt(2) == 0 && (m == Material.COAL_ORE || m == Material.DIAMOND_ORE || m == Material.EMERALD_ORE || m == Material.GLOWING_REDSTONE_ORE || m == Material.GOLD_ORE || m ==
         * Material.IRON_ORE || m == Material.LAPIS_ORE || m == Material.REDSTONE_ORE || m == Material.STONE || m == Material.COBBLESTONE || m == Material.REDSTONE_BLOCK || m == Material.IRON_BLOCK ||
         * m == Material.GOLD_BLOCK || m == Material.DIAMOND_BLOCK || m == Material.EMERALD_BLOCK || m == Material.LAPIS_BLOCK))
         * e.setCancelled(true);
         */
    }

    @org.bukkit.event.EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        String pName = p.getName(), ip = p.getAddress().getAddress().toString().replace("/", "");
        if(FileAPI.isInList(pName, FileAPI.deathFile))
        {
            p.kickPlayer("[" + RealFaction.NAME + "]" + FileAPI.translate("you.are.dead.wait.minuit"));
            Bukkit.broadcastMessage(p.getName() + FileAPI.translate("could.not.connect.is.dead"));
        }
        /*
         * try
         * {
         * String message = "S " + pName + " " + ip;
         * DatagramSocket socket = new DatagramSocket();
         * DatagramPacket receivedData = new DatagramPacket(new byte[1024], 1024);
         * socket.setSoTimeout(10000);
         * socket.send(new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(FileAPI.translate("authentification.server.ip")),
         * Integer.parseInt(FileAPI.translate("authentification.server.port"))));
         * socket.receive(receivedData);
         * if(Integer.parseInt(new String(receivedData.getData(), 0, receivedData.getLength())) != 1)
         * {
         * ban(p);
         * pl.print("[FATAL ALERT] Potential hacker: " + pName + " " + ip + " this bug has been protected !");
         * socket.close();
         * return;
         * }
         * else
         * pl.print(FileAPI.translate("connection.authorized.for") + pName + " (" + ip + ") !");
         * socket.close();
         * }
         * catch(SocketTimeoutException ex)
         * {
         * p.kickPlayer(FileAPI.translate("authentification.not.treated.in.time"));
         * pl.print("[FATAL ALERT] Timed out for connecting to authenfication server !");
         * ex.printStackTrace();
         * return;
         * }
         * catch(Exception ex)
         * {
         * p.kickPlayer("Authentification error !");
         * pl.print("[FATAL ALERT] Error in authentification system !");
         * ex.printStackTrace();
         * return;
         * }
         */
    }

    @org.bukkit.event.EventHandler
    public void onInteract(PlayerInteractEvent e)
    {

    }

    @org.bukkit.event.EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e)
    {

    }
}