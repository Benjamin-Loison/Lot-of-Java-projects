package fr.annihilation.benjaminloison.api;

import org.bukkit.Location;

public class PlayerAPI
{
    public static String firstUpper(String str)
    {
        return str.toUpperCase().charAt(0) + str.substring(1, str.length());
    }

    public static int distance(Location l0, Location l1)
    {
        return Math.abs((Math.abs(l0.getBlockX()) - Math.abs(l1.getBlockX())) + (Math.abs(l0.getBlockY()) - Math.abs(l1.getBlockY())) + (Math.abs(l0.getBlockZ()) - Math.abs(l1.getBlockZ())));
    }
}