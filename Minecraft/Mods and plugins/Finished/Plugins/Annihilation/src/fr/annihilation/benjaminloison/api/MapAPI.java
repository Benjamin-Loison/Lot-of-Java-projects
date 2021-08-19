package fr.annihilation.benjaminloison.api;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Score;

import net.minecraft.util.org.apache.commons.lang3.StringUtils;

public class MapAPI
{
    private int[][][] limits;
    private CampAPI[] camps;
    private String name;
    private int phase = 1, damage = 1;

    public MapAPI(CampAPI[] camps, String name, int[][][] limits)
    {
        this.camps = camps;
        this.name = name;
        this.limits = limits;
    }

    public boolean isPlayersOnDifferentTeams()
    {
        boolean twoPlayers = false;
        for(CampAPI camp : camps)
            if(camp.getNumberAlivesPlayers() != 0)
                if(twoPlayers)
                    return true;
                else
                    twoPlayers = true;
        return false;
    }

    public int[] getTeamPlayers()
    {
        int[] teamPlayers = new int[4];
        teamPlayers[0] = getCampByName("Rouge").getNumberAlivesPlayers();
        teamPlayers[1] = getCampByName("Vert").getNumberAlivesPlayers();
        teamPlayers[2] = getCampByName("Bleu").getNumberAlivesPlayers();
        teamPlayers[3] = getCampByName("Jaune").getNumberAlivesPlayers();
        return teamPlayers;
    }

    public void setDamage(int damage)
    {
        this.damage = damage;
    }

    public int getDamage()
    {
        return damage;
    }

    public int mostPeopleCamp()
    {
        int players = 0;
        for(CampAPI camp : camps)
            if(camp.getNumberAlivesPlayers() > players)
                players = camp.getNumberAlivesPlayers();
        return players;
    }

    public String mostPeopleCampString()
    {
        int players = 0;
        String campName = "";
        for(CampAPI camp : camps)
            if(camp.getNumberAlivesPlayers() > players)
            {
                players = camp.getNumberAlivesPlayers();
                campName = camp.getName();
            }
        return campName;
    }

    public boolean isEnderFurnace(Location location)
    {
        for(CampAPI camp : camps)
            for(Location loc : camp.getEnderFurnace())
                if(loc.equals(location))
                    return true;
        return false;
    }

    public int lessPlayersCamp()
    {
        int players = 1000;
        for(CampAPI camp : camps)
            if(camp.getNumberAlivesPlayers() < players)
                players = camp.getNumberAlivesPlayers();
        return players;
    }

    public boolean canJoin(String color)
    {
        int limit = 2;
        if(mostPeopleCamp() - lessPlayersCamp() > limit && !color.equalsIgnoreCase(mostPeopleCampString()))
            return false;
        return true;
    }

    public void nextPhase()
    {
        phase++;
        Score score = Bukkit.getScoreboardManager().getMainScoreboard().getObjective(ChatColor.DARK_PURPLE + "Annihilation").getScore(ChatColor.LIGHT_PURPLE + "Phase");
        score.setScore(score.getScore() + 1);
    }

    public int getPhase()
    {
        return phase;
    }

    public String getName()
    {
        return name;
    }

    public CampAPI getCampByName(String nom)
    {
        for(CampAPI base : camps)
            if(StringUtils.equalsIgnoreCase(nom, base.getName()))
                return base;
        return null;
    }

    public int getNumberNonDestroyedCamps()
    {
        int bas = 0;
        for(CampAPI base : camps)
            if(!base.getDestroyed())
                bas++;
        return bas;
    }

    public CampAPI[] getNonDestroyedCamps()
    {
        CampAPI[] base = new CampAPI[getNumberNonDestroyedCamps()];
        int i = 0;
        for(CampAPI bas : camps)
            if(!bas.getDestroyed())
            {
                base[i] = bas;
                i++;
            }
        return base;
    }

    public int nexusLife(Location location)
    {
        for(CampAPI base : camps)
            if(base.getNexus().equals(location))
                return base.getNexusLife();
        return 0;
    }

    public void damageNexus(Location location)
    {
        for(CampAPI base : camps)
            if(base.getNexus().equals(location))
            {
                base.destroyNexus();
                break;
            }

    }

    public CampAPI[] getBases()
    {
        return camps;
    }

    public int[][][] getLimits()
    {
        return limits;
    }

    public void reset()
    {
        phase = 1;
        damage = 1;
        for(CampAPI camp : camps)
            camp.reset();
    }

    public String campName(Player p)
    {
        for(CampAPI camp : camps)
            if(camp.getPlayers().contains(p))
                return camp.getName();
        return "";
    }

    public boolean isOnlyOneTeam()
    {
        boolean first = false;
        for(CampAPI camp : camps)
            if(camp.getPlayers().size() != 0)
                if(!first)
                    first = true;
                else
                    return true;
        return false;
    }
}