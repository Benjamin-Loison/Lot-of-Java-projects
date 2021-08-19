import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main
{
    private final static File NOTES_FILE = new File("Data" + File.separatorChar + "notes.txt");
    private final static long CURRENT_TIME = new Date().getTime();
    private final static String CURRENT_TIME_STR = String.valueOf(CURRENT_TIME);
    private final static char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes)
    {
        char[] hexChars = new char[bytes.length * 2];
        for(int j = 0; j < bytes.length; j++)
        {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    private static void writeDefault(String hash)
    {
        try
        {
            FileWriter fw = new FileWriter(NOTES_FILE);
            fw.write(CURRENT_TIME_STR + "\n");
            fw.write(hash);
            fw.close();
            System.exit(0);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void sendNotification(String toLog)
    {
        try
        {
            File logFile = new File("Data" + File.separatorChar + CURRENT_TIME_STR + ".txt");
            FileWriter fw = new FileWriter(logFile);
            fw.write(toLog);
            fw.close();
            System.out.println("Sent");
            Runtime.getRuntime().exec("python pronote.py");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        try
        {
            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.navigate().to(new URL("https://id.index-education.net/pronote/eleve.html"));
            Thread.sleep(2500);

            driver.findElement(By.id("id_53")).sendKeys("USERNAME");
            driver.findElement(By.id("id_54")).sendKeys("PASSWORD");
            driver.findElement(By.id("id_43")).click();
            Thread.sleep(1000);

            driver.findElement(By.id("GInterface.Instances[0].Instances[0]_Combo2")).click();
            Thread.sleep(500);

            String notesMenu = driver.findElement(By.id("GInterface.Instances[1].Instances[1]_Contenu_1")).getText();

            driver.quit();

            if(!notesMenu.contains(">")) // prevent 404/new semestre
                return;

            // System.out.println(notesMenu);
            String hash = bytesToHex(MessageDigest.getInstance("MD5").digest(notesMenu.getBytes("UTF-8")));
            // System.out.println(hash);

            File dataFolder = new File("Data");
            if(!dataFolder.exists())
            {
                dataFolder.mkdir();
                writeDefault(hash);
            }

            Scanner scan = new Scanner(NOTES_FILE);
            long time = Long.parseLong(scan.nextLine());
            String oldHash = scan.nextLine();
            scan.close();

            if(!oldHash.equals(hash) && time + 72000000 >= CURRENT_TIME) // if website changed more than 20 hours ago,
                                                                         // do not send notification
            {
                sendNotification(notesMenu + "\n" + oldHash + "\n" + hash + "\n" + time + "\n" + CURRENT_TIME);
            }
            else
            {
                System.out.println("Do not send");
            }
            writeDefault(hash);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
