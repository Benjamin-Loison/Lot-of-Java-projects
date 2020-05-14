package fr.watchdogs.benjaminloison.api;

import net.minecraft.util.MathHelper;

public class LocationAPI
{
    private int x, y, z;
    private String direction;

    public LocationAPI(int x, int y, int z, int sens)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        switch(MathHelper.floor_double((double)(sens * 4.0F / 360.0F) + 0.5D) & 3)
        {
            case 0:
                direction = "Sud";
                break;
            case 1:
                direction = "Ouest";
                break;
            case 2:
                direction = "Nord";
                break;
            case 3:
                direction = "Est";
                break;
        }
    }

    public LocationAPI(int x, int y, int z, String direction)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.direction = direction;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getZ()
    {
        return z;
    }

    public String getDirection()
    {
        return direction;
    }
}