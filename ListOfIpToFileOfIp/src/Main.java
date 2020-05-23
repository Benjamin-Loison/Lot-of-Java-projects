import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        File listOfIpFile = new File("LogZB.log");
        try
        {
            File outputFile = new File("ips.txt");
            FileWriter fw = new FileWriter(outputFile);
            Scanner scanner = new Scanner(listOfIpFile);
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                fw.write(parts[parts.length - 1]);
                if(scanner.hasNextLine())
                    fw.write("\n");
            }
            scanner.close();
            fw.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}