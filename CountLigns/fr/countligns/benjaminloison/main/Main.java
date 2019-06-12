package fr.countligns.benjaminloison.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    static ArrayList<String> allFiles = new ArrayList<String>();
    
    public static void main(String[] args)
    {
        countLigns(new File("").getAbsolutePath() + File.separatorChar);
    }
    
    private static void countLigns(String path)
    {
        listFiles(path);
        long files = 0, ligns = 0, bytes = 0;
        for(int i = 0; i < allFiles.size(); i++)
        {
            File file = new File(allFiles.get(i));
            bytes += file.length();
            files++;
            try
            {
                Scanner scanner = new Scanner(file);
                while(scanner.hasNextLine())
                {
                    scanner.nextLine();
                    ligns++;
                }
                scanner.close();
            }
            catch(FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("Files written: " + files);
        System.out.println("Ligns written: " + (ligns + 1));
        System.out.println("Bytes of all codes: " + bytes);
    }
    
    private static void listFiles(String path)
    {
        File folder = new File(path);
        String[] files = folder.list();
        for(int i = 0; i < files.length; i++)
        {
            File file = new File(files[i]);
            String name = file.getName();
            if(!name.contains("."))
                listFiles(path + file.getName() + File.separatorChar);
            else if(!name.endsWith(".jar"))
                allFiles.add(folder.getAbsolutePath() + File.separatorChar + file.getName());
        }
    }
}
