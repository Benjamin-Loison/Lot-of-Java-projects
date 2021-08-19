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
    public final static String path = new File("").getAbsolutePath() + File.separatorChar, acfr = path + "mods" + File.separatorChar + "AltisCraft.fr" + File.separatorChar;
    public final static File options = new File(path + "options.txt");

    public static String number(long number)
    {
        if(!Boolean.parseBoolean(I18n.format("money.with.spaces")))
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

    public static int configNumberInt(String string)
    {
        String config = I18n.format(string);
        if(StringUtils.isNumeric(config))
            return Integer.parseInt(config);
        Minefus.print("ERROR ! " + string + " lang " + config + " is not numeric !");
        return 0;
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
}