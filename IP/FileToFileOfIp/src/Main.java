import java.io.File;
import java.io.FileWriter;

public class Main
{
    public static void main(String[] args)
    {
        File currentFolder = new File("."),
             folderFiles[] = currentFolder.listFiles(),
             outputFile = new File("ips.txt");
        try
        {
            FileWriter fw = new FileWriter(outputFile);
            int folderFilesLength = folderFiles.length;
            for(int folderFilesIndex = 0; folderFilesIndex < folderFilesLength; folderFilesIndex++)
            {
                fw.write(folderFiles[folderFilesIndex].getName().replace(".txt", ""));
                if(folderFilesIndex != folderFilesLength - 1)
                    fw.write("\n");
            }
            fw.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}