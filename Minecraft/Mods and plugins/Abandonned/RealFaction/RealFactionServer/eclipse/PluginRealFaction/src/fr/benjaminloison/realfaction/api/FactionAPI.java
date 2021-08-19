package fr.benjaminloison.realfaction.api;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.bukkit.entity.Player;

public class FactionAPI extends FileAPI
{
    public static boolean isPlayerInFaction(String faction, Player p)
    {
        try
        {
            ArrayList<String> players = new ArrayList<String>();
            Scanner scan = new Scanner(factionsFolder + faction + ".txt");
            players.add(scan.nextLine());
            scan.nextLine();
            while(scan.hasNextLine())
                players.add(scan.nextLine());
            scan.close();
        }
        catch(Exception e)
        {}
        return false;
    }

    public static boolean isPlayerInAFaction(Player p)
    {
        return false;
    }

    public static File[] factionsFile()
    {
        String[] factions = factionsFile.list();
        File[] files = new File[factions.length];
        for(int i = 0; i < factions.length; i++)
            files[i] = new File(factions[i]);
        return files;
    }
}