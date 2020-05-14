package fr.annihilation.benjaminloison.api;

import java.util.ArrayList;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import fr.annihilation.benjaminloison.main.Annihilation;

public class CampAPI
{
    private boolean destroyed = false;
    private String name;
    private int nexusLife = 75;
    private Location nexus, spawn, nexusProtection[], enderFurnace[];
    static Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

    public CampAPI(String name, Location nexus, Location spawn, Location[] nexusProtection, Location[] enderFurnace)
    {
        this.name = name;
        this.nexus = nexus;
        this.spawn = spawn;
        this.nexusProtection = nexusProtection;
        this.enderFurnace = enderFurnace;
    }

    public void destroyNexus()
    {
        if(nexusLife >= Annihilation.damage)
        {
            Annihilation.event.w.playSound(nexus, Sound.ANVIL_LAND, 1, 0);
            for(Player p : getPlayers())
            {
                p.sendMessage(ChatColor.DARK_PURPLE + "Le nexus se fait attaquer !");
                p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1, 0);
            }
            String color = "";
            if(name.equalsIgnoreCase("Vert"))
                color = ChatColor.GREEN + "Nexus Vert";
            else if(name.equalsIgnoreCase("Bleu"))
                color = ChatColor.BLUE + "Nexus Bleu";
            else if(name.equalsIgnoreCase("Jaune"))
                color = ChatColor.YELLOW + "Nexus Jaune";
            else if(name.equalsIgnoreCase("Rouge"))
                color = ChatColor.RED + "Nexus Rouge";
            Score score = scoreboard.getObjective(ChatColor.DARK_PURPLE + "Annihilation").getScore(color);
            score.setScore(score.getScore() - Annihilation.damage);
            nexusLife -= Annihilation.damage;
        }
        else
        {
            setDestroyed();
            Bukkit.getWorld("Annihilation").getBlockAt(nexus).setType(Material.BEDROCK);
            Bukkit.broadcastMessage(ChatColor.RED + "Le nexus " + name + " a été détruit, les survivants continuent à se battre !");
        }
    }

    public Location getSpawn()
    {
        return spawn;
    }

    public int getNexusLife()
    {
        return nexusLife;
    }

    public static boolean isPlayerInATeam(Player p)
    {
        if(scoreboard.getPlayerTeam(p) != null)
            return true;
        return false;
    }

    public ArrayList<Player> getPlayers()
    {
        ArrayList<Player> teamPlayers = new ArrayList<Player>();
        Set<OfflinePlayer> players = scoreboard.getTeam(name).getPlayers();
        for(OfflinePlayer player : players)
            if(player.isOnline())
                teamPlayers.add(player.getPlayer());
        return teamPlayers;
    }

    public int getNumberAlivesPlayers()
    {
        int number = 0;
        for(Entity entity : Annihilation.event.w.getEntities())
            if(entity instanceof Player)
            {
                Player p = (Player)entity;
                if(getPlayers().contains(p) && !p.isDead())
                    number++;
            }
        return number;
    }

    public String getName()
    {
        return name;
    }

    public boolean getDestroyed()
    {
        return destroyed;
    }

    public void setDestroyed()
    {
        for(Entity entity : Annihilation.event.w.getEntities())
            if(entity instanceof Player)
                ((Player)entity).playSound(entity.getLocation(), Sound.EXPLODE, 1, 0);
        nexusLife = 0;
        String color = "";
        if(name.equalsIgnoreCase("Vert"))
            color = ChatColor.GREEN + "Nexus Vert";
        else if(name.equalsIgnoreCase("Bleu"))
            color = ChatColor.BLUE + "Nexus Bleu";
        else if(name.equalsIgnoreCase("Jaune"))
            color = ChatColor.YELLOW + "Nexus Jaune";
        else if(name.equalsIgnoreCase("Rouge"))
            color = ChatColor.RED + "Nexus Rouge";
        Annihilation.event.w.getBlockAt(nexus).setType(Material.BEDROCK);
        Bukkit.getScoreboardManager().getMainScoreboard().getObjective(ChatColor.DARK_PURPLE + "Annihilation").getScore(color).setScore(0);
        destroyed = true;
    }

    public Location[] getNexusProtection()
    {
        return nexusProtection;
    }

    public Location[] getEnderFurnace()
    {
        return enderFurnace;
    }

    public Location getNexus()
    {
        return nexus;
    }

    public void reset()
    {
        nexusLife = 75;
        destroyed = false;
    }
}