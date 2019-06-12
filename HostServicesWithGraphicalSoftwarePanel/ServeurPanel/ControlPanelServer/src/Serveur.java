import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Serveur
{
    final static int port = 9633, taille = 1024;
    static byte buffer[] = new byte[taille];
    static String path = new File("").getAbsolutePath();
    static File log = new File(path + "\\Log" + "\\Log.log"), Direction;
    int IP = 0;

    public static void writeLog(String msg)
    {
        Calendar c = Calendar.getInstance();
        int heure = c.get(Calendar.HOUR_OF_DAY), minutes = c.get(Calendar.MINUTE), secondes = c.get(Calendar.SECOND);
        String h = ThereNeedsToZero(heure), m = ThereNeedsToZero(minutes), s = ThereNeedsToZero(secondes), prefix = "[" + h + ":" + m + ":" + s + "] ";
        try
        {
            BufferedWriter output = new BufferedWriter(new FileWriter(log, true));
            output.write(prefix + msg + "\n");
            output.flush();
            output.close();
        }
        catch(IOException e)
        {}
    }

    public static String ThereNeedsToZero(int i)
    {
        String a = Integer.toString(i);
        if(i < 10)
        {
            a = 0 + a;
        }
        return a;
    }

    public static void main(String argv[]) throws Exception
    {
        DatagramSocket socket = new DatagramSocket(port);
        String ligne = "", donnees = "", vraimdp = "";
        File dir = new File(path + "\\Log"), dire = new File(path + "\\Data");
        dir.mkdirs();
        dire.mkdirs();
        lire("Lancement du serveur !");
        while(true)
        {
            lire("Prêt à capter !");
            DatagramPacket paquet = new DatagramPacket(buffer, buffer.length);
            socket.receive(paquet);
            lire("\n" + paquet.getAddress().toString().replace("/", ""));
            donnees = new String(paquet.getData(), 0, paquet.getLength());
            lire("Informations reçues: " + donnees);
            if(donnees.equals("Serveur"))
            {
                lire("Informations sur le serveur...");
                File monFichier = new File(path + "\\Data\\" + "1" + ".txt");
                Scanner sc = new Scanner(monFichier);
                while(sc.hasNextLine())
                {
                    ligne = sc.nextLine();
                    vraimdp = ligne;
                }
                sc.close();
                if(vraimdp.equals("Lance"))
                {
                    socket.send(new DatagramPacket("1".getBytes(), "1".length(), paquet.getAddress(), paquet.getPort()));
                    lire("Serveur lancé !");
                    if(vraimdp.equals("Arret"))
                    {
                        socket.send(new DatagramPacket("0".getBytes(), "0".length(), paquet.getAddress(), paquet.getPort()));
                        lire("Serveur arrêté !");
                    }
                }
            }
            else if(donnees.equals("Lance"))
            {
                lire("Lancement du serveur...");
                Runtime.getRuntime().exec("cmd /c start C:\\Users\\Administrateur\\Desktop\\Client\\DiamondCraft\\Start.bat");
            }
            else if(donnees.equals("Arret"))
            {
                lire("Arrêt du serveur...");
                Runtime.getRuntime().exec("cmd /c DiamondCraft\\Start.bat");
            }
            socket.close();
        }
    }

    public static void lire(String message)
    {
        String chat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(new Date()) + " : " + message;
        System.out.println(chat);
        writeLog(chat);
    }
}