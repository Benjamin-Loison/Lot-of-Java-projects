package fr.chequemod.benjaminloison.main;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FileAPI
{
    public final static String path = new File("").getAbsolutePath() + File.separatorChar + "plugins" + File.separatorChar + PluginChequeMod.NAME + File.separatorChar;
    public final static File languageFile = new File(path + "Language.txt");

    public static void createLanguageFile()
    {
        try
        {
            if(!languageFile.exists())
            {
                new File(path).mkdirs();
                FileWriter fw = new FileWriter(languageFile, true);
                fw.write("amount.with.spaces=true\nhere.is.the.money.for.the.cheque=§aHere is the money for the cheque !\nthis.cheque.is.not.for.you=§cThis cheque is not for you !\ncheque.signed=§aCheque signed !\ncheque.of=Cheque of \nmoney.symbol= $");
                fw.close();
            }
        }
        catch(Exception e)
        {}
    }

    public static String number(long number)
    {
        if(translate("amount.with.spaces").equalsIgnoreCase("false"))
            return "" + number;
        String nbr = new Long(number).toString();
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

    public static String translate(String base)
    {
        System.out.println(languageFile.exists() + " " + base);
        try
        {
            FileWriter fw = new FileWriter(path + "Test.txt");
            fw.write("e");
            fw.close();
            System.out.println(languageFile.exists() + " " + languageFile.getAbsolutePath());
            if(!languageFile.exists())
                createLanguageFile();
            System.out.println(languageFile.exists());
            Scanner scan = new Scanner(languageFile);
            System.out.println(scan.hasNextLine());
            while(scan.hasNextLine())
            {
                String lign = scan.nextLine();
                System.out.println(lign);
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
}