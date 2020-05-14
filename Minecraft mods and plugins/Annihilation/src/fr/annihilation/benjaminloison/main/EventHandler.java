package fr.annihilation.benjaminloison.main;

import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import fr.annihilation.benjaminloison.api.BlockTransporter;
import fr.annihilation.benjaminloison.api.CampAPI;
import fr.annihilation.benjaminloison.api.EnumClass;
import fr.annihilation.benjaminloison.api.FileAPI;
import fr.annihilation.benjaminloison.api.PlayerAPI;
import fr.annihilation.benjaminloison.api.WEWGAPI;
import net.minecraft.util.org.apache.commons.lang3.StringUtils;

@SuppressWarnings("deprecation")
public class EventHandler implements Listener
{
    Annihilation pl;
    Random rand = new Random();
    public World w;
    Location lobby;
    boolean first = true, moveFirst = true;
    short seuil = 300;
    private int random, log = 1, nexus = 10, melon = 2, gravel = 2, gold = 4, iron = 2, coal = 2, lapis = 4, redstone = 4, diamond = 6, emerald = 6;
    Scoreboard sc;

    public EventHandler(Annihilation p)
    {
        sc = Bukkit.getScoreboardManager().getMainScoreboard();
        PluginManager pm = p.getServer().getPluginManager();
        pm.registerEvents(this, p);
        pl = p;
        w = Bukkit.getWorld("Annihilation");
        lobby = new Location(w, 10000, 100, 10000);
    }

    @org.bukkit.event.EventHandler
    public void onCommandServer(ServerCommandEvent e)
    {
        String args[] = e.getCommand().split(" ");
        if(args[0].equalsIgnoreCase("annihilation"))
            if(args[1].equalsIgnoreCase("ban"))
            {
                Iterator<String> bans = pl.banned.iterator();
                String ban = "";
                while(bans.hasNext())
                    ban = ban + bans.next() + " ";
                if(args.length > 2)
                    if(args[2].equalsIgnoreCase("add"))
                    {
                        ban(Bukkit.getPlayer(args[3]));
                        pl.print("ban added " + args[3]);
                    }
                    else if(args[2].equalsIgnoreCase("remove"))
                    {
                        pl.banned.remove(args[3]);
                        pl.print("ban removed " + args[3]);
                    }
                    else if(args[2].equalsIgnoreCase("clear"))
                    {
                        pl.banned.clear();
                        pl.print("ban cleared");
                    }
                    else
                        pl.print("ban: " + ban);
                else
                    pl.print("ban: " + ban);
            }
    }

    @org.bukkit.event.EventHandler
    public void onCommandPlayer(PlayerCommandPreprocessEvent e)
    {
        String args[] = e.getMessage().split(" ");
        Player p = e.getPlayer();
        Location l = p.getLocation();
        if(args[0].equalsIgnoreCase("/team"))
        {
            e.setCancelled(true);
            if(args.length > 1)
            {
                if(args[1].equalsIgnoreCase("Rouge") || args[1].equalsIgnoreCase("Vert") || args[1].equalsIgnoreCase("Bleu") || args[1].equalsIgnoreCase("Jaune") || args[1].equalsIgnoreCase("Aleatoire"))
                    joinTeam(p, args[1]);
            }
            else
            {
                int[] teamPlayers = Annihilation.map.getTeamPlayers();
                p.sendMessage(ChatColor.RED + "Equipe rouge: " + teamPlayers[0]);
                p.sendMessage(ChatColor.GREEN + "Equipe verte: " + teamPlayers[1]);
                p.sendMessage(ChatColor.BLUE + "Equipe bleu: " + teamPlayers[2]);
                p.sendMessage(ChatColor.YELLOW + "Equipe jaune: " + teamPlayers[3]);
                Bukkit.dispatchCommand(p, "list");
            }
        }
        else if(args[0].equalsIgnoreCase("/t"))
        {
            e.setCancelled(true);
            if(sc.getPlayerTeam(p) == null)
            {
                p.sendMessage(ChatColor.RED + "Vous n'avez pas d'équipe !");
                return;
            }
            if(args.length > 1)
                for(OfflinePlayer player : sc.getPlayerTeam(p).getPlayers())
                {
                    if(player instanceof Player)
                        ((Player)player).sendMessage("[EQUIPE] " + ChatColor.GOLD + p.getName() + ": " + ChatColor.WHITE + e.getMessage().replaceFirst("/t ", ""));
                }
            else
                p.sendMessage(ChatColor.RED + "Write a message !");
        }
        else if(args[0].equalsIgnoreCase("/annihilation"))
        {
            e.setCancelled(true);
            if(p.hasPermission("staff"))
            {
                if(args[1].equalsIgnoreCase("phase"))
                {
                    if(args[2].equalsIgnoreCase("next"))
                        nextPhase();
                }
                else if(args[1].equalsIgnoreCase("ban"))
                {
                    Iterator<String> bans = pl.banned.iterator();
                    String ban = "";
                    while(bans.hasNext())
                        ban = ban + bans.next() + " ";
                    if(args.length > 3)
                    {
                        if(args[2].equalsIgnoreCase("add"))
                            ban(Bukkit.getPlayer(args[3]));
                        else if(args[2].equalsIgnoreCase("remove"))
                            pl.banned.remove(args[3]);
                        else if(args[2].equalsIgnoreCase("clear"))
                            pl.banned.clear();
                    }
                    else
                        p.sendMessage("ban: " + ban);
                }
                else
                    p.sendMessage(ChatColor.RED + "Vous n'avez pas la permission de faire ceci !");
            }
        }
        else if(args[0].equalsIgnoreCase("/map"))
        {
            e.setCancelled(true);
            if(Annihilation.voting)
                if(args.length > 1)
                {
                    boolean goodName = false;
                    for(int i = 0; i < Annihilation.maps.length; i++)
                        if(args[1].equalsIgnoreCase(Annihilation.maps[i]))
                        {
                            goodName = true;
                            Annihilation.mapVotes[i]++;
                            if(Annihilation.votes.containsValue(p))
                            {
                                p.sendMessage(ChatColor.DARK_PURPLE + "Vous avez voté pour la carte: " + Annihilation.maps[i] + " au lieu de votre ancien vote pour la carte: " + Annihilation.votes.get(p));
                                Annihilation.votes.remove(p);
                            }
                            else
                                p.sendMessage(ChatColor.DARK_PURPLE + "Vous avez voté pour la carte: " + Annihilation.maps[i]);
                            Annihilation.votes.put(p, Annihilation.maps[i]);
                            break;
                        }
                    if(!goodName)
                        p.sendMessage(ChatColor.DARK_PURPLE + "Cartes disponibles: " + Annihilation.mapsList);
                }
                else
                    p.sendMessage(ChatColor.DARK_PURPLE + "Cartes disponibles: " + Annihilation.mapsList);
            else
                p.sendMessage(ChatColor.RED + "Aucun vote en cours !");
        }
        else if(args[0].equalsIgnoreCase("/class"))
        {
            e.setCancelled(true);
            if(Annihilation.loading || Annihilation.playing && !Annihilation.map.campName(p).equals("") && PlayerAPI.distance(l, lobby) < 250)
                if(args.length > 1)
                {
                    if(!CampAPI.isPlayerInATeam(p))
                    {
                        p.sendMessage("Choose first a team !");
                        return;
                    }
                    Annihilation.classes.remove(p);
                    if(args[1].equalsIgnoreCase("Acrobat"))
                    {
                        Annihilation.classes.put(p, EnumClass.Acrobat);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Acrobat");
                        p.sendMessage("You become a Acrobat !");
                    }
                    else if(args[1].equalsIgnoreCase("Alchemist"))
                    {
                        Annihilation.classes.put(p, EnumClass.Alchemist);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Alchemist");
                        p.sendMessage("You become a Alchemist !");
                    }
                    else if(args[1].equalsIgnoreCase("Archer"))
                    {
                        Annihilation.classes.put(p, EnumClass.Archer);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Archer");
                        p.sendMessage("You become a Archer !");
                    }
                    else if(args[1].equalsIgnoreCase("Assassin"))
                    {
                        Annihilation.classes.put(p, EnumClass.Assassin);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Assassin");
                        p.sendMessage("You become a Assassin !");
                    }
                    else if(args[1].equalsIgnoreCase("Bard"))
                    {
                        Annihilation.classes.put(p, EnumClass.Bard);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Bard");
                        p.sendMessage("You become a Bard !");
                    }
                    else if(args[1].equalsIgnoreCase("Berseker"))
                    {
                        Annihilation.classes.put(p, EnumClass.Berseker);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Berseker");
                        p.sendMessage("You become a Berseker !");
                    }
                    else if(args[1].equalsIgnoreCase("Bloodmage"))
                    {
                        Annihilation.classes.put(p, EnumClass.Bloodmage);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Bloodmage");
                        p.sendMessage("You become a Bloodmage !");
                    }
                    else if(args[1].equalsIgnoreCase("Builder"))
                    {
                        Annihilation.classes.put(p, EnumClass.Builder);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Builder");
                        p.sendMessage("You become a Builder !");
                    }
                    else if(args[1].equalsIgnoreCase("Dasher"))
                    {
                        Annihilation.classes.put(p, EnumClass.Dasher);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Dasher");
                        p.sendMessage("You become a Dasher !");
                    }
                    else if(args[1].equalsIgnoreCase("Defender"))
                    {
                        Annihilation.classes.put(p, EnumClass.Defender);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Defender");
                        p.sendMessage("You become a Defender !");
                    }
                    else if(args[1].equalsIgnoreCase("Enchanter"))
                    {
                        Annihilation.classes.put(p, EnumClass.Enchanter);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Enchanter");
                        p.sendMessage("You become a Enchanter !");
                    }
                    else if(args[1].equalsIgnoreCase("Engineer"))
                    {
                        Annihilation.classes.put(p, EnumClass.Engineer);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Engineer");
                        p.sendMessage("You become a Engineer !");
                    }
                    else if(args[1].equalsIgnoreCase("Farmer"))
                    {
                        Annihilation.classes.put(p, EnumClass.Farmer);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Farmer");
                        p.sendMessage("You become a Farmer !");
                    }
                    else if(args[1].equalsIgnoreCase("Handyman"))
                    {
                        Annihilation.classes.put(p, EnumClass.Handyman);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Handyman");
                        p.sendMessage("You become a Handyman !");
                    }
                    else if(args[1].equalsIgnoreCase("Healer"))
                    {
                        Annihilation.classes.put(p, EnumClass.Healer);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Healer");
                        p.sendMessage("You become a Healer !");
                    }
                    else if(args[1].equalsIgnoreCase("Hero"))
                    {
                        Annihilation.classes.put(p, EnumClass.Hero);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Hero");
                        p.sendMessage("You become a Hero !");
                    }
                    else if(args[1].equalsIgnoreCase("Hunter"))
                    {
                        Annihilation.classes.put(p, EnumClass.Hunter);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Hunter");
                        p.sendMessage("You become a Hunter !");
                    }
                    else if(args[1].equalsIgnoreCase("Iceman"))
                    {
                        Annihilation.classes.put(p, EnumClass.Iceman);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Iceman");
                        p.sendMessage("You become a Iceman !");
                    }
                    else if(args[1].equalsIgnoreCase("Immobilizer"))
                    {
                        Annihilation.classes.put(p, EnumClass.Immobilizer);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Immobilizer");
                        p.sendMessage("You become a Immobilizer !");
                    }
                    else if(args[1].equalsIgnoreCase("Lumberjack"))
                    {
                        Annihilation.classes.put(p, EnumClass.Lumberjack);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Lumberjack");
                        p.sendMessage("You become a Lumberjack !");
                    }
                    else if(args[1].equalsIgnoreCase("Mercenary"))
                    {
                        Annihilation.classes.put(p, EnumClass.Mercenary);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Mercenary");
                        p.sendMessage("You become a Mercenary !");
                    }
                    else if(args[1].equalsIgnoreCase("Miner"))
                    {
                        Annihilation.classes.put(p, EnumClass.Miner);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Miner");
                        p.sendMessage("You become a Miner !");
                    }
                    else if(args[1].equalsIgnoreCase("RiftWalker"))
                    {
                        Annihilation.classes.put(p, EnumClass.RiftWalker);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "RiftWalker");
                        p.sendMessage("You become a RiftWalker !");
                    }
                    else if(args[1].equalsIgnoreCase("Scoot"))
                    {
                        Annihilation.classes.put(p, EnumClass.Scoot);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Scoot");
                        p.sendMessage("You become a Scoot !");
                    }
                    else if(args[1].equalsIgnoreCase("Scorpio"))
                    {
                        Annihilation.classes.put(p, EnumClass.Scorpio);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Scorpio");
                        p.sendMessage("You become a Scorpio !");
                    }
                    else if(args[1].equalsIgnoreCase("Sniper"))
                    {
                        Annihilation.classes.put(p, EnumClass.Sniper);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Sniper");
                        p.sendMessage("You become a Sniper !");
                    }
                    else if(args[1].equalsIgnoreCase("Spider"))
                    {
                        Annihilation.classes.put(p, EnumClass.Spider);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Spider");
                        p.sendMessage("You become a Spider !");
                    }
                    else if(args[1].equalsIgnoreCase("Spy"))
                    {
                        Annihilation.classes.put(p, EnumClass.Spy);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Spy");
                        p.sendMessage("You become a Spy !");
                    }
                    else if(args[1].equalsIgnoreCase("Sucubus"))
                    {
                        Annihilation.classes.put(p, EnumClass.Sucubus);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Sucubus");
                        p.sendMessage("You become a Sucubus !");
                    }
                    else if(args[1].equalsIgnoreCase("Swapper"))
                    {
                        Annihilation.classes.put(p, EnumClass.Swapper);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Swapper");
                        p.sendMessage("You become a Swapper !");
                    }
                    else if(args[1].equalsIgnoreCase("Thor"))
                    {
                        Annihilation.classes.put(p, EnumClass.Thor);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Thor");
                        p.sendMessage("You become a Thor !");
                    }
                    else if(args[1].equalsIgnoreCase("Tinkerer"))
                    {
                        Annihilation.classes.put(p, EnumClass.Tinkerer);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Tinkerer");
                        p.sendMessage("You become a Tinkerer !");
                    }
                    else if(args[1].equalsIgnoreCase("Transporter"))
                    {
                        // TODO Annihilation.classes.put(p, EnumClass.Transporter);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Transporter");
                        Annihilation.transporterBlocks.put(p, new BlockTransporter());
                        p.sendMessage("You become a Transporter !");
                    }
                    else if(args[1].equalsIgnoreCase("Vampire"))
                    {
                        Annihilation.classes.put(p, EnumClass.Vampire);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Vampire");
                        p.sendMessage("You become a Vampire !");
                    }
                    else if(args[1].equalsIgnoreCase("Warrior"))
                    {
                        Annihilation.classes.put(p, EnumClass.Warrior);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Warrior");
                        p.sendMessage("You become a Warrior !");
                    }
                    else if(args[1].equalsIgnoreCase("Wizard"))
                    {
                        Annihilation.classes.put(p, EnumClass.Wizard);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Wizard");
                        p.sendMessage("You become a Wizard !");
                    }
                    else
                    {
                        Annihilation.classes.put(p, EnumClass.Civilian);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + Annihilation.map.campName(p) + "Civilian");
                        p.sendMessage("You become a Civilian !");
                    }
                }
                else
                    p.sendMessage("Classes: Acrobat, Alchemist, Archer, Assassin, Bard, Berseker, Bloodmage, Builder, Dasher, Defender, Enchanter, Engineer, Farmer, Handyman, Healer, Hero, Hunter, Iceman, Immobilizer, Lumberjack, Mercenary, Miner, RiftWalker, Scoot, Scorpio, Sniper, Spider, Spy, Sucubus, Swapper, Thor, Transporter, Vampire, Warrior, Wizard and Civilian");
            else
                p.sendMessage("The game must be running and you must be in a team and not on the map !");
        }
    }

    String colorFromTeam(String team)
    {
        if(team.equalsIgnoreCase("vert"))
            return "§a";
        else if(team.equalsIgnoreCase("rouge"))
            return "§c";
        else if(team.equalsIgnoreCase("jaune"))
            return "§e";
        else if(team.equalsIgnoreCase("bleu"))
            return "§9";
        else
            return "§f";
    }

    // TODO Finished loading game and spawning but no stuff of classes

    @org.bukkit.event.EventHandler
    public void onPlayerDeath(PlayerDeathEvent e)
    {
        Player p = e.getEntity();
        if(p.getKiller() == null)
            Bukkit.broadcastMessage(colorFromTeam(Annihilation.map.campName(p)) + p.getName() + "(" + Annihilation.classes.get(p) + ") " + ChatColor.GRAY + "est mort !");
        else
            Bukkit.broadcastMessage(colorFromTeam(Annihilation.map.campName(p.getKiller())) + p.getKiller().getName() + "(" + Annihilation.classes.get(p.getKiller()) + ")" + ChatColor.GRAY + " a tué " + colorFromTeam(Annihilation.map.campName(p)) + p.getName() + "(" + Annihilation.classes.get(p) + ")");
        new BukkitRunnable()
        {
            public void run()
            {
                try
                {
                    p.spigot().respawn();
                    if(Annihilation.playing)
                        if(sc.getPlayerTeam(p) != null)
                            if(!Annihilation.map.getCampByName(sc.getPlayerTeam(p).getName()).getDestroyed())
                            {
                                p.teleport(Annihilation.map.getCampByName(sc.getPlayerTeam(p).getName()).getSpawn());
                                loadStuff(p);
                                return;
                            }
                            else
                                ban(p);
                    p.teleport(lobby);
                    clearPlayer(p);
                    pl.checkEndGame();
                    Annihilation.transporterBlocks.remove(p);
                }
                catch(Throwable e)
                {
                    e.printStackTrace();
                }
            }
        }.runTaskLater(pl, 20);
    }

    public void clearPlayer(Player p)
    {
        p.setLevel(0);
        p.setExp(0);
        p.setFoodLevel(20);
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        p.setHealth(20);
        p.getActivePotionEffects().clear();
    }

    @org.bukkit.event.EventHandler
    public void onPlayerSpawnEvent(PlayerRespawnEvent e)
    {
        pl.checkEndGame();
        Player p = e.getPlayer();
        if(Annihilation.playing)
            if(sc.getPlayerTeam(p) != null)
            {
                p.teleport(Annihilation.map.getCampByName(sc.getPlayerTeam(p).getName()).getSpawn());
                new BukkitRunnable()
                {
                    public void run()
                    {
                        loadStuff(p);
                    }
                }.runTaskLater(pl, 1);
                return;
            }
        p.teleport(lobby);
    }

    void loadStuff(Player p)
    {
        if(Annihilation.classes.get(p).equals(EnumClass.Alchemist) && Annihilation.map.getPhase() > 3)
        {
            p.getInventory().addItem(new ItemStack(Material.BREWING_STAND_ITEM, 1));
            p.getInventory().addItem(new ItemStack(Material.GLASS_BOTTLE, 3));
        }
        else if(Annihilation.classes.get(p).equals(EnumClass.Archer))
        {
            p.getInventory().addItem(new ItemStack(Material.ARROW, 8));
        }
        else if(Annihilation.classes.get(p).equals(EnumClass.Assassin))
        {
            p.getInventory().addItem(new ItemStack(Material.WOOD_AXE, 1));
        }
        else if(Annihilation.classes.get(p).equals(EnumClass.Berseker))
        {
            p.getInventory().addItem(new ItemStack(Material.WOOD_SWORD, 1));
            p.getInventory().addItem(new ItemStack(Material.LEATHER_HELMET, 1));
        }
        else if(Annihilation.classes.get(p).equals(EnumClass.Bloodmage) && Annihilation.map.getPhase() > 3)
        {
            p.getInventory().addItem(new ItemStack(Material.GLOWSTONE_DUST, 1));
        }
        else if(Annihilation.classes.get(p).equals(EnumClass.Builder))
        {
            p.getInventory().addItem(new ItemStack(Material.WOOD, 8));
        }
        else if(Annihilation.classes.get(p).equals(EnumClass.Dasher))
        {
            p.getInventory().addItem(new ItemStack(Material.GOLD_AXE, 8));
        }
        else if(Annihilation.classes.get(p).equals(EnumClass.Defender))
        {
            p.getInventory().addItem(new ItemStack(Material.WOOD_SWORD, 1));
            p.getInventory().addItem(new ItemStack(Material.LEATHER_HELMET, 1));
        }
        else if(Annihilation.classes.get(p).equals(EnumClass.Enchanter))
        {
            p.getInventory().addItem(new ItemStack(Material.EXP_BOTTLE, 8));
        }
        else if(Annihilation.classes.get(p).equals(EnumClass.Farmer))
        {
            p.getInventory().addItem(new ItemStack(Material.CARROT_ITEM, 4));
            p.getInventory().addItem(new ItemStack(Material.POTATO_ITEM, 8));
            p.getInventory().addItem(new ItemStack(Material.SEEDS, 4));
        }
        else if(Annihilation.classes.get(p).equals(EnumClass.Immobilizer))
        {
            p.getInventory().addItem(new ItemStack(Material.WEB, 2));
        }
        else if(Annihilation.classes.get(p).equals(EnumClass.Lumberjack))
        {
            p.getInventory().addItem(new ItemStack(Material.STONE_AXE, 1));
        }
        else if(Annihilation.classes.get(p).equals(EnumClass.Mercenary))
        {
            p.getInventory().addItem(new ItemStack(Material.STONE_SWORD, 1));
        }
        else if(Annihilation.classes.get(p).equals(EnumClass.Miner))
        {
            p.getInventory().addItem(new ItemStack(Material.STONE_PICKAXE, 1));
        }
        else if(Annihilation.classes.get(p).equals(EnumClass.Scoot))
        {
            p.getInventory().addItem(new ItemStack(Material.FISHING_ROD, 1));
        }
        else if(Annihilation.classes.get(p).equals(EnumClass.Scorpio))
        {
            p.getInventory().addItem(new ItemStack(Material.NETHER_STAR, 1));
            p.getInventory().addItem(new ItemStack(Material.WOOD_SWORD, 1));
        }
        else if(Annihilation.classes.get(p).equals(EnumClass.Thor))
        {
            p.getInventory().addItem(new ItemStack(Material.GOLD_AXE, 1));
        }
        else if(Annihilation.classes.get(p).equals(EnumClass.Transporter))
        {
            p.getInventory().addItem(new ItemStack(Material.QUARTZ, 1));
        }
        else if(Annihilation.classes.get(p).equals(EnumClass.Warrior))
        {
            p.getInventory().addItem(new ItemStack(Material.STONE_SWORD, 1));
        }
    }

    @org.bukkit.event.EventHandler
    public void onDamaged(EntityDamageEvent e)
    {
        // Bukkit.broadcastMessage(e..toString());
    }

    @org.bukkit.event.EventHandler
    public void onSignChanged(SignChangeEvent e)
    {
        if(e.getPlayer().isOp())
        {
            String l0 = e.getLine(0);
            if(l0.equalsIgnoreCase(""))
                return;
            if(l0.equalsIgnoreCase("Rouge") || l0.equalsIgnoreCase("Vert") || l0.equalsIgnoreCase("Bleu") || l0.equalsIgnoreCase("Jaune") || l0.equalsIgnoreCase("Aleatoire"))
            {
                if(l0.equalsIgnoreCase("Rouge"))
                    e.setLine(1, ChatColor.RED + "Rouge");
                else if(l0.equalsIgnoreCase("Vert"))
                    e.setLine(1, ChatColor.GREEN + "Vert");
                else if(l0.equalsIgnoreCase("Bleu"))
                    e.setLine(1, ChatColor.BLUE + "Bleu");
                else if(l0.equalsIgnoreCase("Jaune"))
                    e.setLine(1, ChatColor.YELLOW + "Jaune");
                else if(l0.equalsIgnoreCase("Aleatoire"))
                    e.setLine(1, ChatColor.WHITE + "Aléatoire");
                e.setLine(0, ChatColor.WHITE + "[" + ChatColor.DARK_PURPLE + "EQUIPE" + ChatColor.WHITE + "]");
                e.setLine(2, ChatColor.LIGHT_PURPLE + "Rejoindre");
            }
            else if(l0.equalsIgnoreCase("Potion") || l0.equalsIgnoreCase("Potions") || l0.equalsIgnoreCase("Arme") || l0.equalsIgnoreCase("Armes"))
            {
                e.setLine(0, ChatColor.WHITE + "[" + ChatColor.DARK_PURPLE + "MAGASIN" + ChatColor.WHITE + "]");
                if(l0.equalsIgnoreCase("Potion") || l0.equalsIgnoreCase("Potions"))
                    e.setLine(1, "Potions");
                else
                    e.setLine(1, "Armes");
            }
        }
    }

    @org.bukkit.event.EventHandler
    public void onBlockPlaced(BlockPlaceEvent e)
    {
        Block b = e.getBlock();
        Material m = b.getType();
        Player p = e.getPlayer();
        Location l = b.getLocation();
        if(pl.getWorldGuard().getRegionManager(w).getApplicableRegions(l).size() > 0 && !p.getGameMode().equals(GameMode.CREATIVE))
        {
            p.sendMessage(ChatColor.RED + "Vous ne pouvez pas poser ceci ici !");
            e.setCancelled(true);
            return;
        }
        else if(m.equals(Material.IRON_ORE) || m.equals(Material.COAL_ORE) || m.equals(Material.REDSTONE_ORE) || m.equals(Material.EMERALD_ORE) || m.equals(Material.GOLD_ORE) || m.equals(Material.DIAMOND_ORE) || m.equals(Material.LOG) || m.equals(Material.LOG_2) || m.equals(Material.GRAVEL) || m.equals(Material.MELON_BLOCK))
        {
            p.sendMessage(ChatColor.RED + "Vous ne pouvez pas poser ceci !");
            e.setCancelled(true);
        }
    }

    @org.bukkit.event.EventHandler
    public void onBlockBreak(BlockBreakEvent e)
    {
        Player p = e.getPlayer();
        if(PlayerAPI.distance(lobby, p.getLocation()) < 100 && !p.getName().equalsIgnoreCase("Benjamin_Loison"))
        {
            p.sendMessage(ChatColor.RED + "Vous ne pouvez pas casser ceci au lobby !");
            e.setCancelled(true);
            return;
        }
        Location l = e.getBlock().getLocation();
        Material m = e.getBlock().getType();
        if(m.equals(Material.ENDER_STONE))
            if(!Annihilation.map.getCampByName(sc.getPlayerTeam(p).getName()).getNexus().equals(l))
            {
                e.setCancelled(true);
                if(Annihilation.map.getPhase() > 1)
                {
                    if(Annihilation.map.nexusLife(l) != 1)
                        p.sendMessage(ChatColor.LIGHT_PURPLE + "Vous endommagez le Nexus !");
                    else
                        p.sendMessage(ChatColor.DARK_PURPLE + "Vous avez cassé le Nexus !");
                    p.giveExp(nexus);
                    Annihilation.map.damageNexus(l);
                }
                else
                    p.sendMessage(ChatColor.RED + "Nexus invulnérable avant la phase 2 !");
            }
            else
            {
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "Vous ne pouvez pas endommager votre propre Nexus !");
            }
        else
        {
            if(pl.getWorldGuard().getRegionManager(w).getApplicableRegions(l).size() > 0 && !p.getGameMode().equals(GameMode.CREATIVE))
            {
                p.sendMessage(ChatColor.RED + "Vous ne pouvez pas casser ceci ici !");
                e.setCancelled(true);
                return;
            }
            if(m.equals(Material.IRON_ORE))
            {
                p.getInventory().addItem(new ItemStack(m, 1));
                e.setCancelled(true);
                w.getBlockAt(l).setType(Material.COBBLESTONE);
                pl.addBlock(l, m, 300);
                p.giveExp(iron);
            }
            else if(m.equals(Material.COAL_ORE))
            {
                p.getInventory().addItem(new ItemStack(Material.COAL, 1));
                e.setCancelled(true);
                w.getBlockAt(l).setType(Material.COBBLESTONE);
                pl.addBlock(l, m, 300);
                p.giveExp(coal);
            }
            else if(m.equals(Material.GRAVEL))
            {
                random = rand.nextInt(9);
                p.giveExp(gravel);
                if(random == 8)
                    p.getInventory().addItem(new ItemStack(Material.STRING, 1));
                else if(random < 4)
                    p.getInventory().addItem(new ItemStack(Material.FLINT, 1));
                else
                    p.getInventory().addItem(new ItemStack(Material.FEATHER, 1));
                e.setCancelled(true);
                w.getBlockAt(l).setType(Material.COBBLESTONE);
                pl.addBlock(l, m, 225);
            }
            else if(m.equals(Material.GOLD_ORE))
            {
                p.getInventory().addItem(new ItemStack(m, 1));
                e.setCancelled(true);
                w.getBlockAt(l).setType(Material.COBBLESTONE);
                pl.addBlock(l, m, 600);
                p.giveExp(gold);
            }
            else if(m.equals(Material.MELON_BLOCK))
            {
                p.getInventory().addItem(new ItemStack(Material.MELON, 3));
                e.setCancelled(true);
                w.getBlockAt(l).setType(Material.AIR);
                pl.addBlock(l, m, 600);
                p.giveExp(melon);
            }
            else if(m.equals(Material.REDSTONE_ORE))
            {
                p.getInventory().addItem(new ItemStack(Material.REDSTONE, 3));
                e.setCancelled(true);
                w.getBlockAt(l).setType(Material.COBBLESTONE);
                pl.addBlock(l, m, 750);
                p.giveExp(redstone);
            }
            else if(m.equals(Material.LAPIS_ORE))
            {
                p.getInventory().addItem(new ItemStack(Material.getMaterial("lapislazuli"), 3));
                e.setCancelled(true);
                w.getBlockAt(l).setType(Material.COBBLESTONE);
                pl.addBlock(l, m, 750);
                p.giveExp(lapis);
            }
            else if(m.equals(Material.EMERALD_ORE))
            {
                p.getInventory().addItem(new ItemStack(Material.EMERALD, 1));
                e.setCancelled(true);
                w.getBlockAt(l).setType(Material.COBBLESTONE);
                pl.addBlock(l, m, 800);
                p.giveExp(emerald);
            }
            else if(m.equals(Material.LOG) || m.equals(Material.LOG_2))
            {
                p.getInventory().addItem(new ItemStack(m, 1));
                e.setCancelled(true);
                w.getBlockAt(l).setType(Material.AIR);
                pl.addBlock(l, m, 225);
                p.giveExp(log);
            }
            else if(m.equals(Material.DIAMOND_ORE))
                if(Annihilation.map.getPhase() > 2)
                {
                    p.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
                    e.setCancelled(true);
                    w.getBlockAt(l).setType(Material.COBBLESTONE);
                    pl.addBlock(l, m, 900);
                    p.giveExp(diamond);
                }
                else
                {
                    p.sendMessage(ChatColor.RED + "Diamant non disponible avant la phase 3 !");
                    e.setCancelled(true);
                }
        }
    }

    @org.bukkit.event.EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        String pName = p.getName(), ip = p.getAddress().getAddress().toString().replace("/", "");
        try
        {
            String message = "S " + pName + " " + ip;
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket receivedData = new DatagramPacket(new byte[1024], 1024);
            socket.setSoTimeout(10000);
            socket.send(new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName("altiscraft.fr"), 59722));
            socket.receive(receivedData);
            if(Integer.parseInt(new String(receivedData.getData(), 0, receivedData.getLength())) != 1)
            {
                ban(p);
                pl.print("[FATAL ALERT] Potential hacker: " + pName + " " + ip + " this bug has been protected !");
                socket.close();
                return;
            }
            else
                pl.print("connection.authorized.for" + pName + " (" + ip + ") !");
            socket.close();
        }
        catch(SocketTimeoutException ex)
        {
            p.kickPlayer("authentification.not.treated.in.time");
            pl.print("[FATAL ALERT] Timed out for connecting to authenfication server !");
            ex.printStackTrace();
            return;
        }
        catch(Exception ex)
        {
            p.kickPlayer("Authentification error !");
            pl.print("[FATAL ALERT] Error in authentification system !");
            ex.printStackTrace();
            return;
        }
        if(!pl.banned.contains(p.getName()))
            p.teleport(lobby);
        else
        {
            Bukkit.broadcastMessage(p.getName() + " est banni jusqu'à la prochaine partie !");
            p.kickPlayer(ChatColor.BLUE + "[" + Annihilation.NAME + "] " + ChatColor.RED + "Vous êtes banni jusqu'à la prochaine partie !");
            return;
        }
        if(!p.getName().equals("Benjamin_Loison") && p.isOp())
            p.setOp(false);
        if(Annihilation.classes.get(p) == null)
            Annihilation.classes.put(p, EnumClass.Civilian);
        clearPlayer(p);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " civilian");
        if(first)
        {
            first = false;
            if(sc.getTeams().size() == 0 && sc.getObjectives().size() == 0)
                pl.preapareGame();
            else
            {
                pl.clearGame();
                pl.preapareGame();
            }
        }
    }

    @org.bukkit.event.EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e)
    {
        // TODO: Optimiser le ban
        if(Annihilation.playing && !pl.banned.contains(e.getPlayer()))
            ban(e.getPlayer());
        pl.checkEndGame();
    }

    @org.bukkit.event.EventHandler
    public void onItemSpawn(ItemSpawnEvent e)
    {
        EntityType t = e.getEntityType();
        if(t.equals(EntityType.ENDER_CRYSTAL))
            e.setCancelled(true);
    }

    @org.bukkit.event.EventHandler
    public void onProjectilLaunched(ProjectileLaunchEvent e)
    {
        Projectile pro = e.getEntity();
        LivingEntity entity = pro.getShooter();
        if(entity instanceof Player)
        {
            Player p = ((Player)entity);
            if(p.getItemInHand().getType().equals(Material.NETHER_STAR))
                p.sendMessage(pro.getVelocity().toString());
        }
    }

    @org.bukkit.event.EventHandler
    public void onItemInteract(PlayerInteractEvent e)
    {
        ItemStack itemStack = e.getItem();
        if(itemStack != null)
            if(itemStack.getType().equals(Material.NETHER_STAR))
                e.getPlayer().throwEgg();
    }

    @org.bukkit.event.EventHandler
    public void onPlayerMove(PlayerMoveEvent e)
    {
        if(moveFirst)
        {
            moveFirst = false;
            vote(false);
        }
    }

    String joinBestTeam()
    {
        short players = 100;
        String campName = "";
        for(CampAPI camp : Annihilation.map.getBases())
            if(camp.getNumberAlivesPlayers() < players)
                campName = camp.getName();
        return campName;
    }

    public boolean canJoin(String color)
    {
        if((Annihilation.map.getCampByName(color).getNumberAlivesPlayers() + 1) - (Annihilation.map.lessPlayersCamp()) < seuil)
            return true;
        return false;
    }

    public void joinTeam(Player p, String color)
    {
        // TODO: Bug remove player
        color = PlayerAPI.firstUpper(color);
        if(PlayerAPI.distance(lobby, p.getLocation()) < 100)
            if(Annihilation.map != null)
                if(Annihilation.loading || Annihilation.playing)
                    if(canJoin(color))
                    {
                        if(sc.getPlayerTeam(p) != null)
                            if(sc.getPlayerTeam(p).getName().equalsIgnoreCase(color))
                            {
                                p.sendMessage(ChatColor.DARK_RED + "Vous ne pouvez pas rejoindre l'équipe: " + color);
                                return;
                            }
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + p.getName() + " " + color + "Civilian");
                        sc.getTeam(color).addPlayer(p);
                        clearPlayer(p);
                        p.sendMessage(ChatColor.DARK_PURPLE + "Vous avez rejoind l'équipe: " + color);
                        if(Annihilation.playing)
                            spawn(p);
                    }
                    else
                        p.sendMessage(ChatColor.DARK_RED + "Vous ne pouvez pas rejoindre l'équipe: " + color);
                else
                    p.sendMessage(ChatColor.DARK_PURPLE + "Vous ne pouvez pas encore rentrer dans cette équipe !");
            else
                p.sendMessage(ChatColor.RED + "Aucune carte sélectionnée, vous ne pouvez pas encore rejoindre d'équipe !");
    }

    // TODO: après en chargement || en jeu if(pl.carte.peutRentrer(panneau))
    // TODO: Problème sens des fours (peut-être coffres) au chargement de la schematic

    @org.bukkit.event.EventHandler
    public void onInteract(PlayerInteractEvent e)
    {
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
        {
            Block b = e.getClickedBlock();
            Material mat = b.getType();
            Location l = b.getLocation();
            Player p = e.getPlayer();
            if(mat == Material.SIGN || mat == Material.SIGN_POST || mat == Material.WALL_SIGN)
            {
                Sign sign = (Sign)e.getClickedBlock().getState();
                String l0 = sign.getLine(0), l1 = sign.getLine(1), l2 = sign.getLine(2);
                if(StringUtils.containsIgnoreCase(l2, "Rejoindre"))
                    joinTeam(p, l1.substring(2, l1.length()));
                else if(StringUtils.containsIgnoreCase(l0, "MAGASIN") && WEWGAPI.isInARegion(pl.getWorldGuard(), w, l))
                {
                    if(StringUtils.containsIgnoreCase(l1, "Potions"))
                    {
                        if(Annihilation.map.getPhase() == 3)
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "chc open Potions " + p.getName());
                        else if(Annihilation.map.getPhase() > 3)
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "chc open PotionsAmeliorees " + p.getName());
                    }
                    else if(StringUtils.containsIgnoreCase(l1, "Armes"))
                        if(Annihilation.map.getPhase() == 3)
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "chc open Armes " + p.getName());
                        else if(Annihilation.map.getPhase() > 3)
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "chc open ArmesAmeliorees " + p.getName());
                }
            }
            else if(p.getItemInHand().getType().equals(Material.QUARTZ) && Annihilation.classes.get(p).equals(EnumClass.Transporter) && mat != Material.LOG && mat != Material.ENDER_STONE && mat != Material.IRON_ORE && mat != Material.GOLD_ORE && mat != Material.DIAMOND_ORE && mat != Material.REDSTONE_ORE && mat != Material.EMERALD_ORE && mat != Material.LAPIS_ORE && mat != Material.MELON_BLOCK && mat != Material.GRAVEL)
                Annihilation.transporterBlocks.get(p).interact(b, p);
        }
        // else if(e.getAction().equals(Action.LEFT_CLICK_BLOCK) && )
        {

        }
    }

    public void ban(Player p)
    {
        pl.banned.add(p.getName());
        p.kickPlayer(ChatColor.BLUE + "[" + Annihilation.NAME + "] " + ChatColor.RED + "Vous êtes banni jusqu'à la prochaine partie !");
    }

    public void spawn(Player p)
    {
        CampAPI camp = Annihilation.map.getCampByName(sc.getPlayerTeam(p).getName());
        if(!camp.getDestroyed())
        {
            p.teleport(camp.getSpawn());
            if(Annihilation.classes.get(p).equals(EnumClass.Scorpio))
                p.getInventory().addItem(new ItemStack(Material.NETHER_STAR));
            else if(Annihilation.classes.get(p).equals(EnumClass.Transporter))
                p.getInventory().addItem(new ItemStack(Material.QUARTZ));
        }
        else
            ban(p);
    }

    public void vote(boolean isEnd)
    {
        if(!isEnd)
            if(Annihilation.maps.length > 1)
            {
                // TODO: Plusieurs Map: se servir du scoreboard display sidebar pour les votes
                Annihilation.voting = true;
                Bukkit.broadcastMessage(ChatColor.RED + "Vote de la carte en cours, /carte <Carte>, /carte pour savoir les cartes disponibles, vous avez 30 secondes pour voter !");
            }
            else
            {
                Annihilation.map = Annihilation.avaiablesMaps[0];
                Annihilation.gameMap = Annihilation.maps[0];
                Bukkit.broadcastMessage(ChatColor.RED + "Qu'une carte est disponible, elle a donc été choisie de force, carte: " + ChatColor.DARK_PURPLE + Annihilation.maps[0] + ChatColor.RED + " chargement d'une minute !");
                startGame(Annihilation.gameMap);
            }
        else
        {
            // TODO: Choix classe pendant attente chargement carte + Portail nether changer classe
            Annihilation.voting = false;
            int mostVoted = 0, number = 0;
            String mapMostVoted = Annihilation.maps[0];
            for(int i = 0; i < Annihilation.maps.length; i++)
                if(mostVoted < Annihilation.mapVotes[i])
                {
                    mostVoted = Annihilation.mapVotes[i];
                    mapMostVoted = Annihilation.maps[i];
                    number = i;
                }
            Annihilation.map = Annihilation.avaiablesMaps[number];
            Annihilation.gameMap = mapMostVoted;
            Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "La carte " + mapMostVoted + " a été le plus voté (" + mostVoted + "x), début de l'Annihilation !");
            startGame(Annihilation.gameMap);
        }
    }

    // TODO: Relancement patch

    public void startGame(String map)
    {
        // TODO: Ajouter enderdragon barre avec Phase 1 - 4:34 (avec jauge en fonction)
        Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "Chargement de la carte: " + map + ", un lag important peut être ressenti !");
        Player p = null;
        for(OfflinePlayer op : Bukkit.getOperators())
            if(op instanceof Player)
            {
                p = (Player)op;
                break;
            }
        if(p == null)
            for(Entity entity : w.getEntities())
                if(entity instanceof Player)
                {
                    p = (Player)entity;
                    break;
                }
        boolean isOp = false;
        if(!p.isOp())
        {
            p.setOp(true);
            isOp = true;
        }
        for(CampAPI camp : Annihilation.map.getBases())
        {
            Location[] l = camp.getNexusProtection();
            Bukkit.dispatchCommand(p, "rg remove Carte_Base" + camp.getName() + "_Protection");
            Bukkit.dispatchCommand(p, "rg remove Carte_Base" + camp.getName() + "_Nexus");
            Bukkit.dispatchCommand(p, "/pos1 " + Math.round(l[0].getBlockX()) + "," + Math.round(l[0].getBlockY()) + "," + Math.round(l[0].getBlockZ()));
            Bukkit.dispatchCommand(p, "/pos2 " + l[1].getBlockX() + "," + l[1].getBlockY() + "," + l[1].getBlockZ());
            Bukkit.dispatchCommand(p, "rg define Carte_Base" + camp.getName() + "_Protection");
            Bukkit.dispatchCommand(p, "rg flag Carte_Base" + camp.getName() + "_Protection chest-access allow");
            Bukkit.dispatchCommand(p, "/pos1 " + camp.getNexus().getBlockX() + "," + camp.getNexus().getBlockY() + "," + camp.getNexus().getBlockZ());
            Bukkit.dispatchCommand(p, "/pos2 " + camp.getNexus().getBlockX() + "," + camp.getNexus().getBlockY() + "," + camp.getNexus().getBlockZ());
            Bukkit.dispatchCommand(p, "rg define Carte_Base" + camp.getName() + "_Nexus");
            Bukkit.dispatchCommand(p, "rg flag Carte_Base" + camp.getName() + "_Nexus build allow");
            Bukkit.dispatchCommand(p, "rg setparent Carte_Base" + camp.getName() + "_Nexus Carte_Base" + camp.getName() + "_Protection");
        }
        Bukkit.dispatchCommand(p, "rg remove Limit_Bottom");
        Bukkit.dispatchCommand(p, "rg remove Limit_Top");
        Bukkit.dispatchCommand(p, "rg remove Limit_Left");
        Bukkit.dispatchCommand(p, "rg remove Limit_Right");
        Bukkit.dispatchCommand(p, "rg remove Limit_Back");
        Bukkit.dispatchCommand(p, "rg remove Limit_Front");
        int[][][] l = Annihilation.map.getLimits();
        Bukkit.dispatchCommand(p, "/pos1 " + l[0][0][0] + "," + l[0][0][1] + "," + l[0][0][2]);
        Bukkit.dispatchCommand(p, "/pos2 " + l[0][1][0] + "," + l[0][1][1] + "," + l[0][1][2]);
        Bukkit.dispatchCommand(p, "rg define Limit_Bottom");
        Bukkit.dispatchCommand(p, "/pos1 " + l[1][0][0] + "," + l[1][0][1] + "," + l[1][0][2]);
        Bukkit.dispatchCommand(p, "/pos2 " + l[1][1][0] + "," + l[1][1][1] + "," + l[1][1][2]);
        Bukkit.dispatchCommand(p, "rg define Limit_Top");
        Bukkit.dispatchCommand(p, "/pos1 " + l[2][0][0] + "," + l[2][0][1] + "," + l[2][0][2]);
        Bukkit.dispatchCommand(p, "/pos2 " + l[2][1][0] + "," + l[2][1][1] + "," + l[2][1][2]);
        Bukkit.dispatchCommand(p, "rg define Limit_Left");
        Bukkit.dispatchCommand(p, "/pos1 " + l[3][0][0] + "," + l[3][0][1] + "," + l[3][0][2]);
        Bukkit.dispatchCommand(p, "/pos2 " + l[3][1][0] + "," + l[3][1][1] + "," + l[3][1][2]);
        Bukkit.dispatchCommand(p, "rg define Limit_Right");
        Bukkit.dispatchCommand(p, "/pos1 " + l[4][0][0] + "," + l[4][0][1] + "," + l[4][0][2]);
        Bukkit.dispatchCommand(p, "/pos2 " + l[4][1][0] + "," + l[4][1][1] + "," + l[4][1][2]);
        Bukkit.dispatchCommand(p, "rg define Limit_Back");
        Bukkit.dispatchCommand(p, "/pos1 " + l[5][0][0] + "," + l[5][0][1] + "," + l[5][0][2]);
        Bukkit.dispatchCommand(p, "/pos2 " + l[5][1][0] + "," + l[5][1][1] + "," + l[5][1][2]);
        Bukkit.dispatchCommand(p, "rg define Limit_Front");
        Bukkit.dispatchCommand(p, "tp " + p.getName() + " -1 -1 -3");
        // Bukkit.dispatchCommand(p, "/schem load " + map);
        // Bukkit.dispatchCommand(p, "/paste");
        Bukkit.dispatchCommand(p, "back");
        if(isOp)
            p.setOp(false);
        p.sendMessage(ChatColor.RED + "Vous avez servi à construire le jeu, ne faite pas attention aux messages au-dessus.");
        Annihilation.loading = true;
    }

    public void endGame()
    {
        Annihilation.ending = true;
        clearGame();
    }

    public void clearGame()
    {
        Bukkit.broadcastMessage(ChatColor.RED + "Le jeu se réinitialise !");
        for(Team team : sc.getTeams())
            for(OfflinePlayer member : team.getPlayers())
                team.removePlayer(member);
        for(Player player : w.getPlayers())
            if(PlayerAPI.distance(player.getLocation(), lobby) > 100)
            {
                player.setLevel(0);
                player.setExp(0);
                player.setFoodLevel(20);
                player.getInventory().clear();
                player.getInventory().setArmorContents(null);
                player.setHealth(20);
                player.teleport(lobby);
            }
        List<Entity> entities = Bukkit.getWorld("Annihilation").getEntities();
        for(Entity entity : entities)
            if(StringUtils.containsIgnoreCase(entity.getClass().getName(), "item"))
                entity.remove();
        ArrayList<Entry<String, ProtectedRegion>> rgToDelete = new ArrayList<Entry<String, ProtectedRegion>>();
        for(Entry<String, ProtectedRegion> region : pl.getWorldGuard().getRegionManager(w).getRegions().entrySet())
            if(StringUtils.startsWithIgnoreCase(region.getValue().getId(), "Carte_"))
                rgToDelete.add(region);
        for(Entry<String, ProtectedRegion> region : rgToDelete)
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rg remove " + region.getValue().getId() + " -w Annihilation");
        pl.banned.clear();
        pl.preapareGame();
    }

    public void nextPhase()
    {
        int phase = Annihilation.map.getPhase();
        if(phase == 1)
            Bukkit.broadcastMessage(ChatColor.RED + "Phase 2 (10m): Les nexus ne sont plus invincibles !");
        else if(phase == 2)
            Bukkit.broadcastMessage(ChatColor.RED + "Phase 3 (10m): Les diamants sont maintenant minables !");
        else if(phase == 3)
        {
            WEWGAPI.setSchematicSigns(w, new File(FileAPI.path + "plugins" + File.separatorChar + "WorldEdit" + File.separatorChar + "schematics" + File.separatorChar + "Coastal" + ".schematic"));
            Bukkit.broadcastMessage(ChatColor.RED + "Phase 4 (10m): Magasins rajoutés aux nexus !");
        }
        else if(phase == 4)
        {
            Bukkit.broadcastMessage(ChatColor.RED + "Phase 5 (permanante): Les nexus prennent 2x plus de dégâts et le magasin de potions a été amélioré !");
            Annihilation.damage = 2;
        }
        if(Annihilation.map.getPhase() < 5)
            Annihilation.map.nextPhase();
    }
}