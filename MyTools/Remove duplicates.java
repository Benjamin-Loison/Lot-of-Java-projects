import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        File workingFile = new File("emails.txt");
        ArrayList<String> lines = new ArrayList<String>();
        try
        {
            Scanner scan = new Scanner(workingFile);
            while(scan.hasNextLine())
            {
                String line = scan.nextLine();
                if(!lines.contains(line))
                    lines.add(line);
            }
            scan.close();
            workingFile.delete();
            FileWriter fw = new FileWriter(workingFile);
            Iterator<String> linesIt = lines.iterator();
            while(linesIt.hasNext())
            {
                String line = linesIt.next();
                fw.write(line);
                if(linesIt.hasNext())
                    fw.write("\n");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}