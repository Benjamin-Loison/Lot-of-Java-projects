package fr.annihilation.benjaminloison.api;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.World;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class WEWGAPI
{
    public static void setSchematicSigns(World world, File file)
    {
        SignAPI[] signs = SignAPI.readSigns(file);
        for(int i = 0; i < signs.length; i++)
            signs[i].setSign(world);
    }

    public static boolean isInARegion(WorldGuardPlugin g, World w, Location l)
    {
        if(g.getRegionManager(w).getApplicableRegions(l).size() != 0)
            return true;
        return false;
    }
}