package fr.benjaminloison.realfaction.api;

import org.bukkit.Location;

public class PlayerAPI
{
    public static int distance(Location l0, Location l1)
    {
        return (Math.abs(l0.getBlockX()) - Math.abs(l1.getBlockX())) + (Math.abs(l0.getBlockY()) - Math.abs(l1.getBlockY())) + (Math.abs(l0.getBlockZ()) - Math.abs(l1.getBlockZ()));
    }
}