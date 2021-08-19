package fr.annihilation.benjaminloison.api;

import org.bukkit.Location;
import org.bukkit.Material;

public class BlockRespawnAPI
{
    private Location location;
    private Material material;
    private int time = 60;

    public BlockRespawnAPI(Location location, Material material, int time)
    {
        this.location = location;
        this.material = material;
        this.time = time;
    }
    
    public void decrTime()
    {
        time--;
    }
    
    public boolean mustRespawn()
    {
        if(time <= 0)
            return true;
        return false;
    }
    
    public Location getLocation()
    {
        return location;
    }
    
    public Material getMaterial()
    {
        return material;
    }

    public int getTime()
    {
        return time;
    }
}