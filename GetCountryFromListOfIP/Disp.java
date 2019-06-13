import java.io.File;
import java.io.FileWriter;

public class Disp
{
    static File file = new File("log.txt");
    //static FileWriter fw;

    public static void init()
    {
        try
        {
            //fw = new FileWriter(file);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void print(Object obj)
    {
        String objStr = Time.getTimeFormatted() + " " + obj.toString();
        try
        {
            FileWriter fw = new FileWriter(file, true);
            fw.write(objStr + "\n");
            fw.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        //System.out.println(objStr);
    }
}