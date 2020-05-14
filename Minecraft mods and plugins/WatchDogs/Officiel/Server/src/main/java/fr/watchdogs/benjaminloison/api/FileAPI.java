package fr.watchdogs.benjaminloison.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import fr.watchdogs.benjaminloison.common.WatchDogs;

public class FileAPI
{
    public final static String acfr = new File("").getAbsolutePath() + File.separatorChar + "mods" + File.separatorChar + WatchDogs.NAME + File.separatorChar, gang = acfr + "Gang" + File.separatorChar, airGarage = acfr + "Air Garage" + File.separatorChar, landGarage = acfr + "Land Garage" + File.separatorChar, atm = acfr + "ATM" + File.separatorChar,
            carShop = acfr + "Car Shop" + File.separatorChar, bank = acfr + "Bank.txt";

    public static String config(String string)
    {
        try
        {
            Scanner scan = new Scanner(new File(acfr + "Config.txt"));
            while(scan.hasNextLine())
            {
                String lign = scan.nextLine();
                if(lign.startsWith(string + "="))
                {
                    scan.close();
                    return lign.replace(string + "=", "");
                }
            }
            scan.close();
            return "";
        }
        catch(FileNotFoundException e)
        {
            WatchDogs.print("ERROR ! " + string + " no configuration found !");
            e.printStackTrace();
            return "";
        }
    }

    public static long configNumber(String string)
    {
        String config = config(string);
        if(StringUtils.isNumeric(config))
            return Long.parseLong(config);
        WatchDogs.print("ERROR ! " + string + " lang " + config + " is not numeric !");
        return 0;
    }

    public static int configNumberInt(String string)
    {
        String config = config(string);
        if(StringUtils.isNumeric(config))
            return Integer.parseInt(config);
        WatchDogs.print("ERROR ! " + string + " lang " + config + " is not numeric !");
        return 0;
    }

    public static double configNumberDouble(String string)
    {
        return configNumberInt(string);
    }

    public static boolean configBoolean(String string)
    {
        return Boolean.getBoolean(config(string));
    }

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

    public static void removeLign(String lign, File file)
    {
        try
        {
            boolean removed = false;
            File tempFile = new File(file.getAbsolutePath().replace(".txt", ".temp"));
            FileWriter fw = new FileWriter(tempFile);
            Scanner scan = new Scanner(file), scanner = new Scanner(file);
            scanner.nextLine();
            while(scan.hasNextLine())
            {
                String read = scan.nextLine(), prevent = "";
                if(scanner.hasNextLine())
                    prevent = scanner.nextLine();
                if(!read.equalsIgnoreCase(lign) || removed)
                    if(removed)
                        if(scan.hasNextLine())
                            fw.write(read + "\n");
                        else
                            fw.write(read);
                    else if(scan.hasNextLine() && !prevent.equalsIgnoreCase(lign))
                        fw.write(read + "\n");
                    else
                        fw.write(read);
                else
                    removed = true;
            }
            fw.close();
            scanner.close();
            scan.close();
            file.delete();
            tempFile.renameTo(file);
        }
        catch(Exception e)
        {}
    }

    public static void addLign(String lign, File file)
    {
        try
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
        catch(Exception e)
        {}
    }

    public static void create(Object lign, File file)
    {
        try
        {
            FileWriter fw = new FileWriter(file);
            fw.write(lign.toString());
            fw.close();
        }
        catch(Exception e)
        {}
    }

    public static void create(File file)
    {
        try
        {
            FileWriter fw = new FileWriter(file);
            fw.write("");
            fw.close();
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
        {}
        return "";
    }

    public static String read(File file, int numberLign)
    {
        try
        {
            Scanner scan = new Scanner(file);
            for(int i = 1; i < numberLign; i++)
                scan.nextLine();
            String buffer = scan.nextLine();
            scan.close();
            return buffer;
        }
        catch(Exception e)
        {}
        return "";
    }
}