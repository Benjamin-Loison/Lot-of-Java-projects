import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            int duplicata = 0;
            File currentFolder = new File(".");
            File[] currentFiles = currentFolder.listFiles();
            ArrayList<String> ips = new ArrayList<String>();
            for(int currentFilesIndex = 0; currentFilesIndex < currentFiles.length; currentFilesIndex++)
            {
                File currentFile = currentFiles[currentFilesIndex];
                if(!currentFile.getName().endsWith(".log"))
                    continue;
                Scanner scanner = new Scanner(currentFile);
                while(scanner.hasNextLine())
                {
                    String line = scanner.nextLine();
                    if(ips.contains(line))
                        duplicata++;
                    else
                        ips.add(line);
                }
                scanner.close();
            }
            System.out.println(duplicata);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}