package com.minefus.degraduck.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import com.minefus.degraduck.common.Minefus;

import net.minecraft.client.resources.I18n;

public class FileAPI
{
    public final static String path = new File("").getAbsolutePath() + File.separatorChar, mfus = path + "mods" + File.separatorChar + Minefus.NAME + File.separatorChar;

    public static String number(long number)
    {
        if(!Boolean.parseBoolean(config("amount.with.spaces")))
            return "" + number;
        String nbr = "" + number;
        if(nbr.length() == 4)
            nbr = nbr.charAt(nbr.length() - 4) + " " + nbr.charAt(nbr.length() - 3) + nbr.charAt(nbr.length() - 2) + nbr.charAt(nbr.length() - 1);
        else if(nbr.length() == 5)
            nbr = nbr.charAt(nbr.length() - 5) + nbr.charAt(nbr.length() - 4) + " " + nbr.charAt(nbr.length() - 3) + nbr.charAt(nbr.length() - 2) + nbr.charAt(nbr.length() - 1);
        else if(nbr.length() == 6)
            nbr = nbr.charAt(nbr.length() - 6) + nbr.charAt(nbr.length() - 5) + nbr.charAt(nbr.length() - 4) + " " + nbr.charAt(nbr.length() - 3) + nbr.charAt(nbr.length() - 2) + nbr.charAt(nbr.length() - 1);
        else if(nbr.length() == 7)
            nbr = nbr.charAt(nbr.length() - 7) + " " + nbr.charAt(nbr.length() - 6) + nbr.charAt(nbr.length() - 5) + nbr.charAt(nbr.length() - 4) + " " + nbr.charAt(nbr.length() - 3) + nbr.charAt(nbr.length() - 2) + nbr.charAt(nbr.length() - 1);
        else if(nbr.length() == 8)
            nbr = nbr.charAt(nbr.length() - 8) + nbr.charAt(nbr.length() - 7) + " " + nbr.charAt(nbr.length() - 6) + nbr.charAt(nbr.length() - 5) + nbr.charAt(nbr.length() - 4) + " " + nbr.charAt(nbr.length() - 3) + nbr.charAt(nbr.length() - 2) + nbr.charAt(nbr.length() - 1);
        else if(nbr.length() == 9)
            nbr = nbr.charAt(nbr.length() - 9) + nbr.charAt(nbr.length() - 8) + nbr.charAt(nbr.length() - 7) + " " + nbr.charAt(nbr.length() - 6) + nbr.charAt(nbr.length() - 5) + nbr.charAt(nbr.length() - 4) + " " + nbr.charAt(nbr.length() - 3) + nbr.charAt(nbr.length() - 2) + nbr.charAt(nbr.length() - 1);
        else if(nbr.length() == 10)
            nbr = nbr.charAt(nbr.length() - 10) + " " + nbr.charAt(nbr.length() - 9) + nbr.charAt(nbr.length() - 8) + nbr.charAt(nbr.length() - 7) + " " + nbr.charAt(nbr.length() - 6) + nbr.charAt(nbr.length() - 5) + nbr.charAt(nbr.length() - 4) + " " + nbr.charAt(nbr.length() - 3) + nbr.charAt(nbr.length() - 2) + nbr.charAt(nbr.length() - 1);
        else if(nbr.length() == 11)
            nbr = nbr.charAt(nbr.length() - 11) + nbr.charAt(nbr.length() - 10) + " " + nbr.charAt(nbr.length() - 9) + nbr.charAt(nbr.length() - 8) + nbr.charAt(nbr.length() - 7) + " " + nbr.charAt(nbr.length() - 6) + nbr.charAt(nbr.length() - 5) + nbr.charAt(nbr.length() - 4) + " " + nbr.charAt(nbr.length() - 3) + nbr.charAt(nbr.length() - 2) + nbr.charAt(nbr.length() - 1);
        return nbr;
    }

    public static void write(File file, String lign)
    {
        try
        {
            FileWriter fw = new FileWriter(file);
            fw.write(lign);
            fw.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static String config(String string)
    {
    	File file = new File(mfus + "Config.txt");
    	if(!file.exists())
    		return string;
        try
        {
            Scanner scan = new Scanner(file);
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
            return string;
        }
        catch(FileNotFoundException e)
        {
            Minefus.print("ERROR ! " + string + " no configuration found !");
            e.printStackTrace();
            return string;
        }
    }

    public static double x(String str)
    {
        return Double.parseDouble(config(str).split(",")[0]);
    }

    public static double y(String str)
    {
        return Double.parseDouble(config(str).split(",")[1]);
    }

    public static double z(String str)
    {
        return Double.parseDouble(config(str).split(",")[2]);
    }
}