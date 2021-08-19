package fr.benjaminloison.forevercraft.main;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import fr.benjaminloison.forevercraft.api.FileAPI;
import fr.benjaminloison.forevercraft.api.SignAPI;
import fr.benjaminloison.forevercraft.api.WEWGAPI;

public class EventController implements Listener
{
    Forevercraft pl;
    ArrayList<String> regions = new ArrayList<String>();

    public EventController(Forevercraft p)
    {
        p.getServer().getPluginManager().registerEvents(this, p);
        pl = p;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e)
    {
        String m = e.getMessage(), args[] = m.split(" ");
        Player p = e.getPlayer();
        Location l = e.getPlayer().getLocation();
        // int x = l.getBlockX(), y = l.getBlockY(), z = l.getBlockZ();
        World w = e.getPlayer().getWorld();
        if(args[0].equalsIgnoreCase("/house"))
        {
            e.setCancelled(true);
            boolean isHouseHere = false;
            String argsError = FileAPI.translate("arguments.error") + " /house <info/buy/sell/add/remove> [player]";
            if(args.length == 1)
                p.sendMessage(argsError);
            else
            {
                Iterator<Entry<String, ProtectedRegion>> itRgs = pl.getWorldGuard().getRegionManager(w).getRegions().entrySet().iterator();
                ProtectedRegion region = null;
                boolean isOwner = false;
                while(itRgs.hasNext())
                {
                    Entry<String, ProtectedRegion> enter = itRgs.next();
                    if(enter.getValue().contains(l.getBlockX(), l.getBlockY(), l.getBlockZ()) && enter.getValue().getId().length() >= 7 && enter.getValue().getId().contains("_"))
                    {
                        isHouseHere = true;
                        region = enter.getValue();
                        if(enter.getValue().isMember(p.getName()))
                            isOwner = true;
                    }
                }
                if(!isHouseHere)
                {
                    long distance = 99999;
                    itRgs = pl.getWorldGuard().getRegionManager(w).getRegions().entrySet().iterator();
                    while(itRgs.hasNext())
                    {
                        ProtectedRegion v = itRgs.next().getValue();
                        if(!v.hasMembersOrOwners() && v.getId().startsWith("l"))
                        {
                            BlockVector b = v.getMinimumPoint();
                            long form = (long)l.distance(new Location(w, b.getBlockX(), b.getBlockY(), b.getBlockZ()));
                            if(form < distance)
                                distance = form;
                        }
                    }
                    if(distance == 99999)
                        p.sendMessage(FileAPI.translate("no.house"));
                    else
                        p.sendMessage(FileAPI.translate("no.house.here.nearest.house.at") + FileAPI.numberNormal(distance) + FileAPI.translate("cubs"));
                    return;
                }
                if(args[1].equalsIgnoreCase("info"))
                    if(region.hasMembersOrOwners())
                        if(region.getMembers().size() >= 2)
                            if(region.getId().startsWith("l"))
                                p.sendMessage(FileAPI.translate("house.to.rent.unavailable.owners") + region.getMembers().toPlayersString());
                            else
                                p.sendMessage(FileAPI.translate("house.unavailable.owners") + region.getMembers().toPlayersString());
                        else if(region.getId().startsWith("l"))
                            p.sendMessage(FileAPI.translate("house.to.rent.unavailable.owner") + region.getMembers().toPlayersString());
                        else
                            p.sendMessage(FileAPI.translate("house.unavailable.owner") + region.getMembers().toPlayersString());
                    else if(region.getId().startsWith("l"))
                        p.sendMessage(FileAPI.translate("house.to.rent.available.no.owner.price") + FileAPI.number(SignAPI.price(region.getId())) + " " + FileAPI.translate("the.day") + FileAPI.translate("end.of.sentence"));
                    else
                        p.sendMessage(FileAPI.translate("house.available.no.owner.price") + FileAPI.number(SignAPI.price(region.getId())));
                else if(args[1].equalsIgnoreCase("buy"))
                {
                    if(!isOwner)
                        if(!region.hasMembersOrOwners())
                        {
                            if(!WEWGAPI.hasHouse(pl.getWorldGuard(), w, p))
                                buy(p, region.getId());
                            else
                                p.sendMessage(FileAPI.translate("you.cannot.have.more.than.one.house"));
                        }
                        else if(region.getMembers().size() >= 2)
                            p.sendMessage(FileAPI.translate("house.unavailable.owners") + region.getMembers().toPlayersString());
                        else
                            p.sendMessage(FileAPI.translate("house.unavailable.owner") + region.getMembers().toPlayersString());
                    else if(region.getId().startsWith("l"))
                    {
                        // TODO Erreur WTF translation
                        if(StringUtils.isNumeric(((Sign)w.getBlockAt(new Location(w, SignAPI.x(region.getId()), SignAPI.y(region.getId()), SignAPI.z(region.getId()))).getState()).getLine(2).replace("loué ", "").replace("j à ", "")))
                            if(14 < Integer.parseInt(((Sign)w.getBlockAt(new Location(w, SignAPI.x(region.getId()), SignAPI.y(region.getId()), SignAPI.z(region.getId()))).getState()).getLine(2).replace("loué ", "").replace("j à ", "")))
                            {
                                p.sendMessage(FileAPI.translate("you.cannot.rent.this.time"));
                                return;
                            }
                        buy(p, region.getId());
                    }
                    else
                        p.sendMessage(FileAPI.translate("this.house.is.already.yours"));
                }
                else if(args[1].equalsIgnoreCase("sell"))
                {
                    if(!isOwner)
                    {
                        p.sendMessage(FileAPI.translate("you.are.not.owner"));
                        return;
                    }
                    if(region.getId().startsWith("l"))
                        WEWGAPI.sellLocaRegion(region, w);
                    else
                        WEWGAPI.sellPermRegion(region, w);
                    p.sendMessage(FileAPI.translate("you.have.sold.your.house"));
                }
                else if(args[1].equalsIgnoreCase("add"))
                {
                    if(args.length < 3)
                    {
                        p.sendMessage(argsError);
                        return;
                    }
                    if(!isOwner)
                    {
                        p.sendMessage(FileAPI.translate("you.are.not.owner"));
                        return;
                    }
                    region.getMembers().addPlayer(args[2]);
                    p.sendMessage(FileAPI.translate("you.have.added") + args[2] + FileAPI.translate("to.your.house"));
                }
                else if(args[1].equalsIgnoreCase("remove"))
                {
                    if(args.length < 3)
                    {
                        p.sendMessage(argsError);
                        return;
                    }
                    if(!isOwner)
                    {
                        p.sendMessage(FileAPI.translate("you.are.not.owner"));
                        return;
                    }
                    else if(region.getMembers().size() == 1)
                    {
                        p.sendMessage(FileAPI.translate("you.cannot.removed.yourself.you.are.the.last.owner.sell.the.house.if.you.want"));
                        return;
                    }
                    region.getMembers().removePlayer(args[2]);
                    p.sendMessage(FileAPI.translate("you.have.removed") + args[2] + FileAPI.translate("of.your.house"));
                }
                else
                    p.sendMessage(argsError);
            }
        }
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onSignPlaced(SignChangeEvent e)
    {
        String srg = WEWGAPI.smallRegion(pl.getWorldGuard(), e.getBlock().getWorld(), e.getBlock().getLocation()).getId();
        if(!e.getLine(0).equalsIgnoreCase(""))
            if(StringUtils.isNumeric(e.getLine(0)))
                try
                {
                    pl.getWorldEdit().getSession(e.getPlayer()).getRegionSelector().getRegion().getHeight();
                    int price = Integer.parseInt(e.getLine(0)), x = e.getBlock().getX(), y = e.getBlock().getY(), z = e.getBlock().getZ();
                    String region = price + "_" + x + "_" + y + "_" + z;
                    Bukkit.dispatchCommand(e.getPlayer(), "rg define " + region);
                    Bukkit.dispatchCommand(e.getPlayer(), "rg setparent " + region + " " + srg);
                    Bukkit.dispatchCommand(e.getPlayer(), "rg flag " + region + " use deny");
                    Bukkit.dispatchCommand(e.getPlayer(), "rg flag " + region + " construct deny");
                    e.setLine(0, FileAPI.translate("house.to.sell.sign"));
                    e.setLine(1, FileAPI.translate("at.sign") + FileAPI.number(price));
                    e.setLine(2, "/house " + FileAPI.translate("for"));
                    e.setLine(3, FileAPI.translate("interaction"));
                    Bukkit.broadcastMessage(FileAPI.translate("a.new.house.is.available.in.x") + x + FileAPI.translate("y") + y + FileAPI.translate("z") + z + FileAPI.translate("at.the.price.of") + FileAPI.number(price) + FileAPI.translate("has.been.added.by") + e.getPlayer().getName() + FileAPI.translate("end.of.sentence"));
                }
                catch(IncompleteRegionException ei)
                {
                    e.getBlock().setType(Material.AIR);
                    e.getPlayer().sendMessage(FileAPI.translate("you.need.to.select.a.region"));
                }
            else if(e.getLine(0).contains("g"))
                if(!e.getLine(1).equalsIgnoreCase(""))
                    try
                    {
                        pl.getWorldEdit().getSession(e.getPlayer()).getRegionSelector().getRegion().getHeight();
                        String gang = e.getLine(0).replaceFirst("g", ""), mainPlayer = e.getLine(1), region = "g" + gang + "_" + e.getBlock().getX() + "_" + e.getBlock().getY() + "_" + e.getBlock().getZ();
                        Bukkit.dispatchCommand(e.getPlayer(), "rg define " + region);
                        Bukkit.dispatchCommand(e.getPlayer(), "rg setparent " + region + " " + srg);
                        Bukkit.dispatchCommand(e.getPlayer(), "rg flag " + region + " use deny");
                        Bukkit.dispatchCommand(e.getPlayer(), "rg flag " + region + " construct deny");
                        Bukkit.dispatchCommand(e.getPlayer(), "rg addmember " + region + " g:" + gang);
                        e.setLine(0, FileAPI.translate("house.of.gang"));
                        e.setLine(1, FileAPI.translate("of"));
                        e.setLine(2, gang);
                        e.setLine(3, "(" + mainPlayer.subSequence(0, 12) + ")");
                        Bukkit.broadcastMessage(FileAPI.translate("the.house.of.gang.of") + gang + FileAPI.translate("directed.by") + mainPlayer + FileAPI.translate("has.been.added.by") + e.getPlayer().getName() + FileAPI.translate("end.of.sentence"));
                    }
                    catch(IncompleteRegionException ei)
                    {
                        e.getBlock().setType(Material.AIR);
                        e.getPlayer().sendMessage(FileAPI.translate("you.need.to.select.a.region"));
                    }
                else
                {
                    e.getBlock().setType(Material.AIR);
                    e.getPlayer().sendMessage(FileAPI.translate("need.to.enter.at.the.second.line.the.chief.of.gang.name"));
                }
            else if(e.getLine(0).contains("l"))
                if(StringUtils.isNumeric(e.getLine(0).replace("l", "")))
                    try
                    {
                        pl.getWorldEdit().getSession(e.getPlayer()).getRegionSelector().getRegion().getHeight();
                        int prix = Integer.parseInt(e.getLine(0).replace("l", "")), x = e.getBlock().getX(), y = e.getBlock().getY(), z = e.getBlock().getZ();
                        String region = "l" + prix + "_" + x + "_" + y + "_" + z;
                        Bukkit.dispatchCommand(e.getPlayer(), "rg define " + region);
                        Bukkit.dispatchCommand(e.getPlayer(), "rg setparent " + region + " " + srg);
                        Bukkit.dispatchCommand(e.getPlayer(), "rg flag " + region + " use deny");
                        Bukkit.dispatchCommand(e.getPlayer(), "rg flag " + region + " construct deny");
                        e.setLine(0, FileAPI.translate("house.to.rent.sign"));
                        e.setLine(1, FileAPI.translate("at.sign") + FileAPI.number(prix));
                        e.setLine(2, FileAPI.translate("the.day"));
                        Bukkit.broadcastMessage(FileAPI.translate("a.new.house.to.rent.is.available.in.x") + x + FileAPI.translate("y") + y + FileAPI.translate("z") + z + FileAPI.translate("at.the.price.of") + FileAPI.number(prix) + FileAPI.translate("the.day.this.house.has.been.added.by") + e.getPlayer().getName() + FileAPI.translate("end.of.sentence"));
                    }
                    catch(IncompleteRegionException ei)
                    {
                        e.getBlock().setType(Material.AIR);
                        e.getPlayer().sendMessage(FileAPI.translate("you.need.to.select.a.region"));
                    }
                else
                    pl.print(e.getPlayer().getName() + FileAPI.translate("has.placed") + FileAPI.translate("a.sign") + e.getBlock().getX() + " " + e.getBlock().getY() + " " + e.getBlock().getZ());
    }

    @SuppressWarnings("deprecation")
    public void checkHouses(Player p) throws Exception
    {
        boolean checkLoca = false;
        pl.print(FileAPI.translate("house") + FileAPI.translate("beginning.of.solds.by.absence.of.sign"));
        Iterator<Entry<String, ProtectedRegion>> r = pl.getWorldGuard().getRegionManager(p.getWorld()).getRegions().entrySet().iterator();
        while(r.hasNext())
        {
            Entry<String, ProtectedRegion> e = r.next();
            if(e.getValue().getId().contains("_") && !(e.getValue().getId().startsWith("f") || e.getValue().getId().startsWith("r")))
            {
                Material bloc = p.getWorld().getBlockAt(new Location(p.getWorld(), SignAPI.x(e.getValue().getId()), SignAPI.y(e.getValue().getId()), SignAPI.z(e.getValue().getId()))).getType();
                if(bloc != Material.SIGN && bloc != Material.SIGN_POST && bloc != Material.WALL_SIGN)
                {
                    regions.add(e.getValue().getId());
                    Bukkit.broadcastMessage(FileAPI.translate("region") + e.getValue().getId() + FileAPI.translate("has.been.removed.because.absence.of.sign"));
                }
            }
        }
        for(int i = 0; i < regions.size(); i++)
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rg remove " + regions.get(i) + " -w " + Bukkit.getWorlds().get(0).getName());
        pl.print(FileAPI.translate("house") + FileAPI.translate("end.of.removing.regions.without.sign"));
        pl.print(FileAPI.translate("house") + FileAPI.translate("beginning.of.solds.by.inactivity"));
        File config = new File(FileAPI.plac + "config.txt");
        Scanner scan = new Scanner(config);
        String time = scan.nextLine();
        Date actualDate = new Date(), oldDate = FileAPI.stringToDate(time);
        scan.close();
        oldDate.setDate(oldDate.getDate() + 1);
        if(actualDate.after(oldDate))
        {
            FileWriter fw = new FileWriter(config);
            fw.write(actualDate.toLocaleString());
            fw.close();
            checkLoca = true;
            pl.print(FileAPI.translate("house") + FileAPI.translate("verification.of.rents.in.progress"));
        }
        else
            pl.print(FileAPI.translate("house") + FileAPI.translate("no.verification.of.rents.to.do"));
        r = pl.getWorldGuard().getRegionManager(p.getWorld()).getRegions().entrySet().iterator();
        while(r.hasNext())
        {
            Entry<String, ProtectedRegion> er = r.next();
            if(er.getValue().hasMembersOrOwners() && er.getValue().getId().length() >= 7 && er.getValue().getId().contains("_"))
                if(!er.getValue().getId().startsWith("l") && !er.getValue().getId().startsWith("g") && !er.getValue().getId().startsWith("r"))
                {
                    Iterator<String> i = er.getValue().getMembers().getPlayers().iterator();
                    long ple = 0;
                    while(i.hasNext())
                    {
                        long t = Bukkit.getOfflinePlayer(i.next()).getLastPlayed();
                        if(t > ple)
                            ple = t;
                    }
                    Date j = new Date();
                    j.setTime(ple);
                    j.setDate((j.getDate() + 14));
                    if(actualDate.after(j))
                    {
                        WEWGAPI.sellPermRegion(er.getValue(), p.getWorld());
                        pl.print(FileAPI.translate("house") + er.getValue().getId() + FileAPI.translate("sold.because.inactivity.of.members") + er.getValue().getMembers().toPlayersString() + FileAPI.translate("old.player.time") + j.toLocaleString() + FileAPI.translate("actual.time") + actualDate.toLocaleString());
                    }
                }
                else if(checkLoca && !er.getValue().getId().startsWith("g"))
                {
                    if(((Sign)p.getWorld().getBlockAt(new Location(p.getWorld(), SignAPI.x(er.getValue().getId()), SignAPI.y(er.getValue().getId()), SignAPI.z(er.getValue().getId()))).getState()).getLine(2).equalsIgnoreCase(FileAPI.translate("the.day")) || ((Sign)p.getWorld().getBlockAt(new Location(p.getWorld(), SignAPI.x(er.getValue().getId()), SignAPI.y(er.getValue().getId()), SignAPI.z(er.getValue().getId()))).getState()).getLine(2).equalsIgnoreCase("/house " + FileAPI.translate("for")))
                    {
                        pl.print(FileAPI.translate("house.to.rent") + er.getValue().getId() + FileAPI.translate("sold.because.defective.sign.members") + er.getValue().getMembers().toPlayersString());
                        WEWGAPI.sellLocaRegion(er.getValue(), p.getWorld());
                    }
                    else if(SignAPI.day(p.getWorld(), er.getValue().getId()) <= 1)
                    {
                        WEWGAPI.sellLocaRegion(er.getValue(), p.getWorld());
                        pl.print(FileAPI.translate("house.to.rent") + er.getValue().getId() + FileAPI.translate("sold.because.renewal.of.the.lease.members") + er.getValue().getMembers().toPlayersString());
                    }
                }
        }
        pl.print(FileAPI.translate("house") + FileAPI.translate("end.of.sales.by.inactivity"));
    }

    void buy(Player p, String region)
    {
        ProtectedRegion rg = WEWGAPI.smallRegion(pl.getWorldGuard(), p.getWorld(), p.getLocation());
        Sign sign = (Sign)p.getWorld().getBlockAt(new Location(p.getWorld(), SignAPI.x(rg.getId()), SignAPI.y(rg.getId()), SignAPI.z(rg.getId()))).getState();
        sign.setLine(0, FileAPI.translate("house.of"));
        sign.setLine(1, p.getName());
        if(rg.getId().startsWith("l"))
        {
            int day = 1;
            if(rg.isMember(p.getName()))
                day = SignAPI.day(p.getWorld(), rg.getId()) + 1;
            sign.setLine(2, FileAPI.translate("rented") + day + FileAPI.translate("abbreviation.day.to"));
            p.sendMessage(FileAPI.translate("you.have.rented.this.house"));
        }
        else
        {
            sign.setLine(2, FileAPI.translate("bought.at"));
            p.sendMessage(FileAPI.translate("you.have.bought.this.house"));
        }
        sign.setLine(3, FileAPI.number(SignAPI.price(rg.getId())));
        sign.update();
        rg.getMembers().addPlayer(p.getName());
    }
}