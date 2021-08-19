
import java.io.File;
import java.io.FileWriter;
import java.util.Locale;
import java.util.Scanner;

public class OptimizeMyPackage
{
    private static final File dire = new File(new File("").getAbsolutePath());
    private static final String version = "Alpha 0.0.2", files[] = dire.list();
    private static String[] args;
    private static boolean moreInfo = false;
    private static EnumLang lang = EnumLang.EN_en;
    private static int lignOptimized = 0;

    public static void main(String argv[])
    {
        args = argv;
        setParameters();
        doProgram();
    }

    public static void setParameters()
    {
        if(Locale.getDefault().toString().equals("FR_fr"))
        {
            lang = EnumLang.FR_fr;
            Print.main(EnumSpecial.param, "OS détecté en Français.");
        }
        for(int i = 0; i < args.length; i++)
        {
            if(args[i].startsWith("moreInfo="))
            {
                if(args[i].equalsIgnoreCase("moreInfo=true"))
                {
                    moreInfo = true;
                    Print.main(EnumSpecial.param, "Parameter moreInfo on true.");
                }
                else if(!args[i].equalsIgnoreCase("moreInfo=false"))
                {
                    Print.main(EnumSpecial.param, EnumSpecial.warning, "Parameter moreInfo= " + i + " (" + args[i] + ")" + "has an error, ignored (moreInfo on false) !");
                }
            }
            else if(args[i].startsWith("lang="))
            {
                if(args[i].equalsIgnoreCase("lang=FR_fr"))
                {
                    lang = EnumLang.FR_fr;
                    Print.main(EnumSpecial.param, "Paramètre lang en Français.");
                }
                else if(!args[i].equalsIgnoreCase("lang=EN_en") && !args[i].equalsIgnoreCase("lang=US_us"))
                {
                    Print.main(EnumSpecial.param, EnumSpecial.warning, "Parameter lang= " + i + " (" + args[i] + ") has an error, ignored (lang on EN_en) !");
                }
            }
            else
            {
                Print.main(EnumSpecial.param, EnumSpecial.warning, "Parameter " + i + " (" + args[i] + ")" + "has an error, ignored !");
            }
        }
        if(lang.equals(EnumLang.US_us))
        {
            lang = EnumLang.EN_en;
        }
    }

    public static void doProgram()
    {
        if(lang.equals(EnumLang.EN_en))
        {
            Print.main("OptimizeMyPackage launched with " + version + " Version !");
        }
        else if(lang.equals(EnumLang.FR_fr))
        {
            Print.main("OptimizeMyPackage lancé avec la Version " + version + " !");
        }
        if(lang.equals(EnumLang.EN_en))
        {
            Print.main("Listening: " + dire.getAbsolutePath());
        }
        else if(lang.equals(EnumLang.FR_fr))
        {
            Print.main("Ecoute de: " + dire.getAbsolutePath());
        }
        for(int i = 0; i < files.length; i++)
        {
            if(files[i].contains(".txt"))
            {
                if(lang.equals(EnumLang.EN_en))
                {
                    Print.main("Working on: " + files[i] + " (" + (i + 1) + "/" + files.length + ")");
                }
                else if(lang.equals(EnumLang.FR_fr))
                {
                    Print.main("Travail sur: " + files[i] + " (" + (i + 1) + "/" + files.length + ")");
                }
                if(moreInfo)
                {
                    if(lang.equals(EnumLang.EN_en))
                    {
                        Print.main(EnumSpecial.moreInfo, "Copying: " + files[i] + " to: " + files[i].replace(".txt", ".temp"));
                    }
                    else if(lang.equals(EnumLang.FR_fr))
                    {
                        Print.main(EnumSpecial.moreInfo, "Copie de: " + files[i] + " en: " + files[i].replace(".txt", ".temp"));
                    }
                }
                File fileTraited = new File(files[i]), fileTemp = new File(files[i].replace(".txt", ".temp"));
                try
                {
                    FileWriter write = new FileWriter(fileTemp);
                    Scanner scan = new Scanner(new File(files[i]), "UTF-8");
                    while(scan.hasNextLine())
                    {
                        String read = scan.nextLine();
                        System.out.println(read);
                        if(!(read.equals("") || read.equals("\n") || read.startsWith("//") || read.startsWith("?") || read.equalsIgnoreCase("ItemID Your ID")))
                        {
                            // TODO: A little of french for me, Si , ou . dans ligne SetupPart alors prendre juste l'entier avant if(lecture.startsWith("SetupPart") && (lecture.contains(",") ||
                            // lecture.contains("."))) {String[] parts = lecture.split(" ");for(int x = 0; x < parts.length; x++) {parts[x].}}
                            if(!read.startsWith("//"))
                            {
                                if(read.contains("//"))
                                {
                                    for(int j = 0; j < read.length(); j++)
                                    {
                                        if(read.charAt(j) == '/')
                                        {
                                            read = read.substring(0, j);
                                        }
                                    }
                                }
                                if(read.startsWith("SetupPart "))
                                {
                                    String toWrite = "", parts[] = read.split(" ");
                                    for(int j = 3; j < 8; j++)
                                    {
                                        parts[j] = parts[j].replace(",", ".");
                                        if(parts[j].contains("."))
                                        {
                                            for(int k = 0; k < parts[j].length(); k++)
                                            {
                                                if(parts[j].charAt(k) == '.')
                                                {
                                                    parts[j] = parts[j].substring(0, k);
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    for(int j = 0; j < parts.length; j++)
                                    {
                                        if(j > 0)
                                        {
                                            toWrite = toWrite + " " + parts[j];
                                        }
                                        else
                                        {
                                            toWrite = toWrite + parts[j];
                                        }
                                    }
                                    if(scan.hasNextLine())
                                    {
                                        write.write(toWrite + "\n");
                                    }
                                    else
                                    {
                                        write.write(toWrite);
                                    }
                                }
                                else if(read.startsWith("ItemID"))
                                {
                                    String[] parts = read.split(" ");
                                    if(isInteger(parts[1]))
                                    {
                                        if(scan.hasNextLine())
                                        {
                                            write.write(read + "\n");
                                        }
                                        else
                                        {
                                            write.write(read);
                                        }
                                    }
                                }
                                else
                                {
                                    if(scan.hasNextLine())
                                    {
                                        write.write(read + "\n");
                                    }
                                    else
                                    {
                                        write.write(read);
                                    }
                                }
                            }
                            else
                            {
                                lignOptimized++;
                            }
                        }
                    }
                    if(moreInfo)
                    {
                        if(lang.equals(EnumLang.EN_en))
                        {
                            Print.main(EnumSpecial.moreInfo, "Copy done !");
                        }
                        else if(lang.equals(EnumLang.FR_fr))
                        {
                            Print.main(EnumSpecial.moreInfo, "Copie terminée !");
                        }
                    }
                    scan.close();
                    write.close();
                    fileTraited.delete();
                    if(moreInfo)
                    {
                        if(lang.equals(EnumLang.EN_en))
                        {
                            Print.main(EnumSpecial.moreInfo, "Original File deleted !");
                        }
                        else if(lang.equals(EnumLang.FR_fr))
                        {
                            Print.main(EnumSpecial.moreInfo, "Fichier original suprimmé !");
                        }
                    }
                    fileTemp.renameTo(new File(fileTemp.getAbsolutePath().replace(".temp", ".txt")));
                    if(moreInfo)
                    {
                        if(lang.equals(EnumLang.EN_en))
                        {
                            Print.main(EnumSpecial.moreInfo, "Temporary File rename to permanant File !");
                        }
                        else if(lang.equals(EnumLang.FR_fr))
                        {
                            Print.main(EnumSpecial.moreInfo, "Fichier temporaire renommé en Fichier permanent !");
                        }
                    }
                }
                catch(Exception e)
                {}
            }
            else
            {
                if(lang.equals(EnumLang.EN_en))
                {
                    Print.main("File: " + files[i] + " not ends .txt, file ignored !");
                }
                else if(lang.equals(EnumLang.FR_fr))
                {
                    Print.main("Fichier: " + files[i] + " pas en .txt, fichier ignoré !");
                }
            }
        }
        if(lignOptimized > 1)
        {
            if(lang.equals(EnumLang.EN_en))
            {
                Print.main("OptimizeMyPackage terminated, " + lignOptimized + " ligns have been removed !");
            }
            else if(lang.equals(EnumLang.FR_fr))
            {
                Print.main("OptimizeMyPackage terminé, " + lignOptimized + " lignes ont été enlevé !");
            }
        }
        else
        {
            if(lang.equals(EnumLang.EN_en))
            {
                Print.main("OptimizeMyPackage terminated, " + lignOptimized + " lign has been removed !");
            }
            else if(lang.equals(EnumLang.FR_fr))
            {
                Print.main("OptimizeMyPackage terminé, " + lignOptimized + " ligne a été enlevé !");
            }
        }
    }

    public static boolean isInteger(String input)
    {
        try
        {
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
}