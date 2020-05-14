package fr.benjaminloison.realfaction.api;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import org.bukkit.Location;
import org.bukkit.World;

import fr.benjaminloison.realfaction.main.RealFaction;

public class FileAPI
{
    public final static String path = new File("").getAbsolutePath() + File.separatorChar, plac = path + "plugins" + File.separatorChar + RealFaction.NAME + File.separatorChar, factionsFolder = path + "Factions" + File.separatorChar;
    public final static File languageFile = new File(plac + "Language.txt"), deathFile = new File(plac + "Death.txt"), factionsFile = new File(factionsFolder);

    public static void write(String[] ligns, File file)
    {
        try
        {
            FileWriter fw = new FileWriter(file, true);
            for(int i = 0; i < ligns.length; i++)
                if(!(i == ligns.length - 1))
                    fw.write(ligns[i] + "\n");
                else
                    fw.write(ligns[i]);
            fw.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void write(String lign, File file)
    {
        try
        {
            FileWriter fw = new FileWriter(file, true);
            fw.write(lign);
            fw.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static String[] readAll(File file)
    {
        try
        {
            int size = 0;
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine())
            {
                scan.nextLine();
                size++;
            }
            String[] ligns = new String[size];
            scan.close();
            Scanner scanner = new Scanner(file);
            for(int i = 0; i < ligns.length; i++)
                ligns[i] = scanner.nextLine();
            scanner.close();
            return ligns;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isInList(String isIn, File file)
    {
        if(!file.exists())
            write("", file);
        String[] ligns = readAll(file);
        for(String lign : ligns)
            if(lign.equalsIgnoreCase(isIn))
                return true;
        return false;
    }

    public static Location location(World world, File file)
    {
        String[] co = read(file).split(",");
        return new Location(world, Integer.parseInt(co[0]), Integer.parseInt(co[1]), Integer.parseInt(co[2]));
    }

    public static void removeLign(String lign, File file)
    {
        try
        {
            int ligns = 0;
            File tempFile = new File(file.getAbsolutePath().replace(".txt", ".temp"));
            FileWriter fw = new FileWriter(tempFile);
            Scanner scan = new Scanner(file), scanner = new Scanner(file);
            while(scan.hasNextLine())
            {
                scanner.nextLine();
                String read = scan.nextLine();
                if(!read.equalsIgnoreCase(lign))
                {
                    if(scan.hasNextLine() && scanner.hasNextLine())
                        fw.write(read + "\n");
                    else
                        fw.write(read);
                    ligns++;
                }
            }
            fw.close();
            scan.close();
            scanner.close();
            file.delete();
            tempFile.renameTo(file);
            if(ligns == 0)
            {
                scan.close();
                file.delete();
            }
            scan.close();
        }
        catch(Exception e)
        {}
    }

    public static void addLign(String lign, File file)
    {
        try
        {
            if(file.exists())
            {
                Scanner scan = new Scanner(file);
                FileWriter fw = new FileWriter(file, true);
                if(scan.hasNextLine())
                {
                    scan.close();
                    fw.write("\n" + lign);
                }
                else
                {
                    scan.close();
                    fw.write(lign);
                }
                fw.close();
            }
            else
            {
                FileWriter fw = new FileWriter(file, true);
                fw.write(lign);
                fw.close();
            }
        }
        catch(Exception e)
        {}
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
        {
            return "";
        }
    }

    public static String translate(String base)
    {
        try
        {
            if(!languageFile.exists())
                return base;
            Scanner scan = new Scanner(languageFile);
            while(scan.hasNextLine())
            {
                String lign = scan.nextLine();
                if(lign.startsWith(base + "="))
                {
                    scan.close();
                    return lign.replace(base + "=", "");
                }
            }
            scan.close();
        }
        catch(Exception e)
        {
            System.out.println("No translation found for: " + base + " in: " + languageFile);
            e.printStackTrace();
        }
        return base;
    }

    public static void initialise()
    {
        new File(plac).mkdirs();
        if(!languageFile.exists())
            write("death.file.deleted=Death file deleted !\nyou.are.dead.wait.minuit= §cVous êtes mort, patientez minuit !", languageFile);
    }
}