package fr.annihilation.benjaminloison.main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import fr.annihilation.benjaminloison.api.BlockRespawnAPI;
import fr.annihilation.benjaminloison.api.BlockTransporter;
import fr.annihilation.benjaminloison.api.CampAPI;
import fr.annihilation.benjaminloison.api.EnumClass;
import fr.annihilation.benjaminloison.api.FileAPI;
import fr.annihilation.benjaminloison.api.MapAPI;
import net.minecraft.server.v1_7_R4.EntityItem;

@SuppressWarnings("deprecation")
public class Annihilation extends JavaPlugin
{
    public static MapAPI map, avaiablesMaps[];
    public static EventHandler event;
    public static final String MODID = "annihilation", NAME = "Annihilation";
    static File dire = new File(FileAPI.path + "plugins" + File.separatorChar + NAME + File.separatorChar);
    public static String maps[], mapsList = "", gameMap = "", files[] = dire.list();
    public static int mapVotes[], damage = 1;
    public static boolean loading, playing, voting, activeGame = true, ending;
    public static Map<Player, String> votes = new HashMap<Player, String>();
    public static Map<Player, Integer> points = new HashMap<Player, Integer>();
    public static Map<Player, EnumClass> classes = new HashMap<Player, EnumClass>();
    public static Map<Player, BlockTransporter> transporterBlocks = new HashMap<Player, BlockTransporter>();
    public ArrayList<BlockRespawnAPI> blocks = new ArrayList<BlockRespawnAPI>(), bufferBlocks = new ArrayList<BlockRespawnAPI>();
    public ArrayList<String> banned = new ArrayList<String>();
    Scoreboard sc;

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

    @Override
    public void onEnable()
    {
        // TODO: Faire give stuff de base et après mette default gamemode 2
        print("Launching !");
        sc = Bukkit.getScoreboardManager().getMainScoreboard();
        event = new EventHandler(this);
        print("Files: " + files.length + " " + dire);
        maps = new String[files.length];
        avaiablesMaps = new MapAPI[files.length];
        for(int i = 0; i < files.length; i++)
        {
            maps[i] = FileAPI.afterSlash(files[i]).replace(".yml", "");
            avaiablesMaps[i] = readMap(files[i]);
        }
        mapVotes = new int[maps.length];
        for(int i = 0; i < maps.length; i++)
            mapsList = mapsList + maps[i];
        Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable()
        {
            boolean first = true, ticks = true, firstInGame = true, start = false;
            int ticked = 0;
            Plugin p = Bukkit.getPluginManager().getPlugin(Annihilation.NAME);
            Annihilation pl = null;

            public void run()
            {
                if(event.w.getPlayers().size() > 0)
                {
                    
                    if(ending)
                    {
                        ending = false;
                        ticks = true;
                        firstInGame = false;
                        start = false;
                        loading = false;
                        playing = false;
                        voting = false;
                        ticked = 0;
                        map.reset();
                        if(activeGame)
                            event.vote(false);
                        else
                            Bukkit.broadcastMessage(ChatColor.RED + "Aucune partie reprévue !");
                    }
                    if(first)
                    {
                        first = false;
                        if(p instanceof Annihilation)
                            pl = (Annihilation)p;
                    }
                    if(playing)
                    {
                        BlockTransporter.effectAll();
                        if(ticked == 12000)
                        {
                            ticked = 0;
                            Annihilation.event.nextPhase();
                        }
                        if(ticked % 1200 == 0)
                            checkEndGame();
                    }
                    if(loading)
                    {
                        if(ticked == 1200 && !firstInGame && !start)
                        {
                            Scoreboard sc = Bukkit.getScoreboardManager().getMainScoreboard();
                            Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "Carte " + map.getName() + " chargée avec succès !");
                            Bukkit.broadcastMessage(ChatColor.RED + "Phase 1 (10m): Les nexus sont invincibles !");
                            sc.getObjective(ChatColor.DARK_PURPLE + "Annihilation").setDisplaySlot(DisplaySlot.SIDEBAR);
                            event.w.setTime(0);
                            for(Entity entity : event.w.getEntities())
                                if(entity instanceof Player)
                                {
                                    Player p = (Player)entity;
                                    if(sc.getPlayerTeam(p) != null)
                                        p.teleport(map.getCampByName(sc.getPlayerTeam(p).getName()).getSpawn());
                                }
                            start = true;
                            playing = true;
                            voting = false;
                            checkEndGame();
                        }
                        if(firstInGame && !start)
                        {
                            firstInGame = false;
                            ticked = 0;
                        }
                        if(voting)
                        {
                            if(ticks)
                            {
                                ticks = false;
                                ticked = 0;
                            }
                            else if(ticked == 600)
                            {
                                ticks = true;
                                ticked = 0;
                                Annihilation.event.vote(true);
                            }
                        }
                    }
                    ticked++;
                    pl.checkBlocks();
                }
            }
        }, 1l, 1l);
        ShapedRecipe arrow = new ShapedRecipe(new ItemStack(Material.ARROW, 2));
        arrow.shape("#", "X");
        arrow.setIngredient('#', Material.FLINT);
        arrow.setIngredient('X', Material.STICK);
        Bukkit.addRecipe(arrow);
        print("Launched !");
    }

    public void addBlock(Location location, Material material, int time)
    {
        blocks.add(new BlockRespawnAPI(location, material, time));
    }

    public void checkBlocks()
    {
        for(BlockRespawnAPI block : blocks)
            if(block.mustRespawn())
            {
                event.w.getBlockAt(block.getLocation()).setType(block.getMaterial());
                bufferBlocks.add(block);
            }
            else
                block.decrTime();
        for(BlockRespawnAPI bloc : bufferBlocks)
            blocks.remove(bloc);
        bufferBlocks.clear();
    }

    public MapAPI readMap(String path)
    {
        File f = new File(dire + File.separator + path);
        CampAPI[] c = new CampAPI[4];
        c[0] = readCamp(f, "Bleu");
        c[1] = readCamp(f, "Rouge");
        c[2] = readCamp(f, "Vert");
        c[3] = readCamp(f, "Jaune");
        String[] posBottom = FileAPI.readStarts(f, "Limit_Bottom:").replace("Limit_Bottom:", "").split("\\+"), posBottom0 = posBottom[0].split(","), posBottom1 = posBottom[1].split(","), posTop = FileAPI.readStarts(f, "Limit_Top:").replace("Limit_Top:", "").split("\\+"), posTop0 = posTop[0].split(","), posTop1 = posTop[1].split(","),
                posLeft = FileAPI.readStarts(f, "Limit_Left:").replace("Limit_Left:", "").split("\\+"), posLeft0 = posLeft[0].split(","), posLeft1 = posLeft[1].split(","), posRight = FileAPI.readStarts(f, "Limit_Right:").replace("Limit_Right:", "").split("\\+"), posRight0 = posRight[0].split(","), posRight1 = posRight[1].split(","),
                posBack = FileAPI.readStarts(f, "Limit_Back:").replace("Limit_Back:", "").split("\\+"), posBack0 = posBack[0].split(","), posBack1 = posBack[1].split(","), posFront = FileAPI.readStarts(f, "Limit_Front:").replace("Limit_Front:", "").split("\\+"), posFront0 = posFront[0].split(","), posFront1 = posFront[1].split(",");
        int[][][] limits = new int[6][2][3];
        for(int i = 0; i < 3; i++)
        {
            limits[0][0][i] = Integer.parseInt(posBottom0[i]);
            limits[0][1][i] = Integer.parseInt(posBottom1[i]);
            limits[1][0][i] = Integer.parseInt(posTop0[i]);
            limits[1][1][i] = Integer.parseInt(posTop1[i]);
            limits[2][0][i] = Integer.parseInt(posLeft0[i]);
            limits[2][1][i] = Integer.parseInt(posLeft1[i]);
            limits[3][0][i] = Integer.parseInt(posRight0[i]);
            limits[3][1][i] = Integer.parseInt(posRight1[i]);
            limits[4][0][i] = Integer.parseInt(posBack0[i]);
            limits[4][1][i] = Integer.parseInt(posBack1[i]);
            limits[5][0][i] = Integer.parseInt(posFront0[i]);
            limits[5][1][i] = Integer.parseInt(posFront1[i]);
        }
        return new MapAPI(c, FileAPI.afterSlash(path).replace(".yml", ""), limits);
    }

    public CampAPI readCamp(File f, String c)
    {
        // TODO: Coder EnderFurnace
        String[] e = FileAPI.readStarts(f, c + "Camp_EnderFurnace:").replace(c + "Camp_EnderFurnace:", "").split("\\+"), p = FileAPI.readStarts(f, c + "Camp_Protection:").replace(c + "Camp_Protection:", "").split("\\+"), p0 = p[0].split(","), p1 = p[1].split(","), pN = FileAPI.readStarts(f, c + "Camp_Nexus:").replace(c + "Camp_Nexus:", "").split(","),
                pS = FileAPI.readStarts(f, c + "Camp_Spawn:").replace(c + "Camp_Spawn:", "").split(",");
        Location n = new Location(event.w, Integer.parseInt(pN[0]), Integer.parseInt(pN[1]), Integer.parseInt(pN[2])), s = new Location(event.w, Integer.parseInt(pS[0]), Integer.parseInt(pS[1]), Integer.parseInt(pS[2])), nP[] = new Location[2], eF[] = new Location[e.length];
        nP[0] = new Location(event.w, Integer.parseInt(p0[0]), Integer.parseInt(p0[1]), Integer.parseInt(p0[2]));
        nP[1] = new Location(event.w, Integer.parseInt(p1[0]), Integer.parseInt(p1[1]), Integer.parseInt(p1[2]));
        for(int i = 0; i < e.length; i++)
        {
            String[] co = e[i].split(",");
            eF[i] = new Location(event.w, Integer.parseInt(co[0]), Integer.parseInt(co[1]), Integer.parseInt(co[2]));
        }
        return new CampAPI(c, n, s, nP, eF);
    }

    public void checkEndGame()
    {
        if(map != null && playing)
        {
            for(CampAPI camp : map.getBases())
                if(!camp.getDestroyed() && camp.getNexusLife() == 0)
                {
                    Bukkit.broadcastMessage(ChatColor.RED + "Le nexus de la base " + camp.getName() + " a été détruit, les survivants de l'équipe continuent de se battre !");
                    camp.setDestroyed();
                }
            if(map.getNumberNonDestroyedCamps() == 1 && map.isOnlyOneTeam())
            {
                CampAPI camp = map.getNonDestroyedCamps()[0];
                Bukkit.broadcastMessage(ChatColor.RED + "La base " + camp.getName() + " n'a pas été détruite, victoire de l'équipe " + camp.getName() + " !");
                event.endGame();
            }
        }
    }

    public void clearGame()
    {
        event.clearGame();
        for(Team team : sc.getTeams())
            team.unregister();
        for(Objective objective : sc.getObjectives())
            objective.unregister();
    }

    public void preapareGame()
    {
        // TODO: remplacer objectif Annihilation par le nom de la carte Map: <NomCarte> (bold yellow)
        // TODO: Avant vote carte, utiliser scoreboard setdisplay sidebar comme compteur des joueurs dans les équipes
        Team red = sc.getTeam("Rouge"), green = sc.getTeam("Vert"), yellow = sc.getTeam("Jaune"), blue = sc.getTeam("Bleu");
        if(red == null)
        {
            red = sc.registerNewTeam("Rouge");
            green = sc.registerNewTeam("Vert");
            yellow = sc.registerNewTeam("Jaune");
            blue = sc.registerNewTeam("Bleu");
        }
        red.setAllowFriendlyFire(false);
        red.setCanSeeFriendlyInvisibles(true);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard teams option Rouge color red");
        green.setAllowFriendlyFire(false);
        green.setCanSeeFriendlyInvisibles(true);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard teams option Vert color green");
        yellow.setAllowFriendlyFire(false);
        yellow.setCanSeeFriendlyInvisibles(true);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard teams option Jaune color yellow");
        blue.setAllowFriendlyFire(false);
        blue.setCanSeeFriendlyInvisibles(true);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard teams option Bleu color blue");
        Objective annihilation = sc.getObjective(ChatColor.DARK_PURPLE + "Annihilation");
        if(annihilation == null)
            annihilation = sc.registerNewObjective(ChatColor.DARK_PURPLE + "Annihilation", "dummy");
        annihilation.getScore(ChatColor.LIGHT_PURPLE + "Phase").setScore(1);
        annihilation.getScore(ChatColor.RED + "Nexus Rouge").setScore(75);
        annihilation.getScore(ChatColor.GREEN + "Nexus Vert").setScore(75);
        annihilation.getScore(ChatColor.YELLOW + "Nexus Jaune").setScore(75);
        annihilation.getScore(ChatColor.BLUE + "Nexus Bleu").setScore(75);
        for(Entity entity : event.w.getEntities())
            if(entity instanceof EntityItem)
                entity.remove();
    }

    @Override
    public void onDisable()
    {}

    public void print(Object object)
    {
        System.out.println("[" + NAME + "] " + object);
    }
}