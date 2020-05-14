package fr.benjaminloison.forevercraft.api;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Sign;

public class SignAPI
{
    public static int price(String sign)
    {
        if(sign.startsWith("r") || sign.startsWith("f"))
            return 0;
        return Integer.parseInt(sign.split("_")[0].replace("l", ""));
    }

    public static int x(String sign)
    {
        if(sign.startsWith("r") || sign.startsWith("f"))
            return 0;
        String[] parts = sign.split("_");
        if(parts.length <= 1)
            return -1;
        if(parts[1].equals("") || parts[1].equals(null))
            return -1;
        return Integer.parseInt(parts[1]);
    }

    public static int y(String sign)
    {
        if(sign.startsWith("r") || sign.startsWith("f"))
            return 0;
        String[] parts = sign.split("_");
        if(parts.length <= 1)
            return -1;
        if(parts[1].equals("") || parts[1].equals(null))
            return -1;
        return Integer.parseInt(parts[2]);
    }

    public static int z(String sign)
    {
        if(sign.startsWith("r") || sign.startsWith("f"))
            return 0;
        String[] parts = sign.split("_");
        if(parts.length <= 1)
            return -1;
        if(parts[1].equals("") || parts[1].equals(null))
            return -1;
        return Integer.parseInt(parts[3]);
    }

    public static int day(World w, String sign)
    {
        if(!sign.startsWith("l"))
            return 0;
        return Integer.parseInt(((Sign)w.getBlockAt(new Location(w, x(sign), y(sign), z(sign))).getState()).getLine(2).replace("loué ", "").replace("j à", ""));
    }
}