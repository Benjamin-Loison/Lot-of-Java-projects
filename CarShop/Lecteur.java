import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;

public class Lecteur
{
    public static void main(String argv[]) throws Exception
    {
        String[] listefichiers;
        File dire = new File(new File("").getAbsolutePath());
        listefichiers = dire.list();
        lire("Listening: " + dire.getAbsolutePath());
        for(int i = 0; i < listefichiers.length; i++)
        {
            if(listefichiers[i].endsWith(".txt"))
            {
                lire("Travail en cours sur: " + listefichiers[i]);
                Vehicle[] vehicles = new Vehicle[100];
                int line = 0;
                Scanner scan = new Scanner(new File(listefichiers[i]));
                while(scan.hasNextLine())
                {
                    String lect = scan.nextLine();
                    String[] part = lect.split(" ");
                    vehicles[line] = new Vehicle(part[0], Integer.parseInt(part[1]));
                    line++;
                }
                Concessionnaire cons = new Concessionnaire(vehicles);
                scan.close();
                for(int x = 0; x < cons.getVehicles().length; x++)
                {
                    if(vehicles[x] != null)
                        lire(vehicles[x].getName() + vehicles[x].getPrice());
                }
            }
            else
            {
                lire("Fichier: " + listefichiers[i] + " pas en .txt, fichier ignoré !");
            }
        }
    }

    public static void lire(String message)
    {
        Date aujourdhui = new Date();
        DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
        String chat = "";
        chat = shortDateFormat.format(aujourdhui) + " : " + message;
        System.out.println(chat);
    }

    public static boolean isInteger(String input)
    {
        try
        {
            Integer.parseInt(input);
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }
}
