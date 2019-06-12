import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            File currentFolder = new File(".");
            for(File file : currentFolder.listFiles())
            {
                String fileContent = "", newFileContent = "";
                Scanner scanner = new Scanner(file);
                while(scanner.hasNextLine())
                {
                    String line = scanner.nextLine();
                    fileContent += line;
                }
                scanner.close();
                
                // BEGIN FORMATTING
                
                
                
                // END FORMATTING
                
                FileWriter fw = new FileWriter(file);
                fw.write(newFileContent);
                fw.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}