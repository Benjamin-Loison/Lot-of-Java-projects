import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main
{
    static String escapeLatex(String str)
    {
        return str.replace("%", "\\%").replace("#", "\\#");
    }

    public static void main(String[] args)
    {
        try
        {
            File file = new File("lessons.txt"), newFile = new File("newLessons.txt");
            Scanner scan = new Scanner(file);
            FileWriter fw = new FileWriter(newFile);
            while(scan.hasNextLine())
            {
                String id = scan.nextLine(), title = scan.nextLine(), percent = scan.nextLine();
                int percentInt = Integer.parseInt(percent.replace(" %", ""));
                if(percentInt >= 30)
                    fw.write("\\item \\href{https://openclassrooms.com/fr/courses/" + id + "}{" + escapeLatex(title) + "} " + escapeLatex(percent));
                if(scan.hasNextLine())
                {
                    if(percentInt >= 30)
                        fw.write("\n");
                    scan.nextLine();
                }
            }
            fw.close();
            scan.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}