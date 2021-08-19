package fr.annihilation.benjaminloison.api;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.bukkit.Location;
import org.bukkit.World;

import net.minecraft.util.org.apache.commons.lang3.StringUtils;

public class FileAPI
{
    public final static String path = new File("").getAbsolutePath() + File.separatorChar;

    public static String number(long number)
    {
        String nbr = new Long(number).toString();
        if(nbr.length() == 4)
            nbr = nbr.charAt(nbr.length() - 4) + " " + nbr.charAt(nbr.length() - 3) + "" + nbr.charAt(nbr.length() - 2) + "" + nbr.charAt(nbr.length() - 1);
        else if(nbr.length() == 5)
            nbr = nbr.charAt(nbr.length() - 5) + "" + nbr.charAt(nbr.length() - 4) + " " + nbr.charAt(nbr.length() - 3) + "" + nbr.charAt(nbr.length() - 2) + "" + nbr.charAt(nbr.length() - 1);
        else if(nbr.length() == 6)
            nbr = nbr.charAt(nbr.length() - 6) + "" + nbr.charAt(nbr.length() - 5) + "" + nbr.charAt(nbr.length() - 4) + " " + nbr.charAt(nbr.length() - 3) + "" + nbr.charAt(nbr.length() - 2) + "" + nbr.charAt(nbr.length() - 1);
        else if(nbr.length() == 7)
            nbr = nbr.charAt(nbr.length() - 7) + " " + nbr.charAt(nbr.length() - 6) + "" + nbr.charAt(nbr.length() - 5) + "" + nbr.charAt(nbr.length() - 4) + " " + nbr.charAt(nbr.length() - 3) + "" + nbr.charAt(nbr.length() - 2) + "" + nbr.charAt(nbr.length() - 1);
        else if(nbr.length() == 8)
            nbr = nbr.charAt(nbr.length() - 8) + "" + nbr.charAt(nbr.length() - 7) + " " + nbr.charAt(nbr.length() - 6) + "" + nbr.charAt(nbr.length() - 5) + "" + nbr.charAt(nbr.length() - 4) + " " + nbr.charAt(nbr.length() - 3) + "" + nbr.charAt(nbr.length() - 2) + "" + nbr.charAt(nbr.length() - 1);
        else if(nbr.length() == 9)
            nbr = nbr.charAt(nbr.length() - 9) + "" + nbr.charAt(nbr.length() - 8) + "" + nbr.charAt(nbr.length() - 7) + " " + nbr.charAt(nbr.length() - 6) + "" + nbr.charAt(nbr.length() - 5) + "" + nbr.charAt(nbr.length() - 4) + " " + nbr.charAt(nbr.length() - 3) + "" + nbr.charAt(nbr.length() - 2) + "" + nbr.charAt(nbr.length() - 1);
        else if(nbr.length() == 10)
            nbr = nbr.charAt(nbr.length() - 10) + " " + nbr.charAt(nbr.length() - 9) + "" + nbr.charAt(nbr.length() - 8) + "" + nbr.charAt(nbr.length() - 7) + " " + nbr.charAt(nbr.length() - 6) + "" + nbr.charAt(nbr.length() - 5) + "" + nbr.charAt(nbr.length() - 4) + " " + nbr.charAt(nbr.length() - 3) + "" + nbr.charAt(nbr.length() - 2) + "" + nbr.charAt(nbr.length() - 1);
        else if(nbr.length() == 11)
            nbr = nbr.charAt(nbr.length() - 11) + "" + nbr.charAt(nbr.length() - 10) + " " + nbr.charAt(nbr.length() - 9) + "" + nbr.charAt(nbr.length() - 8) + "" + nbr.charAt(nbr.length() - 7) + " " + nbr.charAt(nbr.length() - 6) + "" + nbr.charAt(nbr.length() - 5) + "" + nbr.charAt(nbr.length() - 4) + " " + nbr.charAt(nbr.length() - 3) + "" + nbr.charAt(nbr.length() - 2) + "" + nbr.charAt(nbr.length() - 1);
        return nbr;
    }

    public static String afterSlash(String path)
    {
        int charac = 0;
        for(int i = 0; i < path.length(); i++)
            if(path.charAt(i) == '/' || path.charAt(i) == '\\')
                charac = i;
        return path.substring(charac, path.length());
    }

    public static Location location(World world, File fichier)
    {
        String[] co = read(fichier).split(",");
        return new Location(world, Integer.parseInt(co[0]), Integer.parseInt(co[1]), Integer.parseInt(co[2]));
    }

    public static String read(File file)
    {
        try
        {
            Scanner scan = new Scanner(file);
            String buffer = scan.nextLine();
            scan.close();
            return buffer;
        }
        catch(Exception e)
        {}
        return "";
    }

    public static String readStarts(File file, String start)
    {
        try
        {
            Scanner scan = new Scanner(file);
            String lign = "";
            while(scan.hasNextLine())
            {
                lign = scan.nextLine();
                if(StringUtils.startsWithIgnoreCase(lign, start))
                {
                    scan.close();
                    return lign;
                }
            }
            scan.close();
            return "";
        }
        catch(Exception e)
        {}
        return "";
    }
    
    public static SignAPI[] signsListFromArrayList(ArrayList<SignAPI> array)
    {
        SignAPI[] list = new SignAPI[array.size()];
        for(int i = 0; i < list.length; i++)
            list[i] = array.get(i);
        return list;
    }
}