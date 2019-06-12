import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class Main
{
    private static int part2, part3;
    private static FileWriter fw;

    public static void main(String[] args)
    {
        try
        {
            readFor();
            log("finished");
        }
        catch(Exception e)
        {

        }
    }

    private static void log(String toLog)
    {
        try
        {
            fw = new FileWriter(new File("logs.txt"), true);
            fw.write(toLog + "\n");
            fw.close();
        }
        catch(Exception e)
        {

        }
    }

    private static void readFor()
    {
        try
        {
            Scanner scan = new Scanner(new File("available.txt"));
            while(scan.hasNextLine())
            {
                String ip = scan.nextLine();
                if(!ip.equals(""))
                {
                    Thread th = new Thread()
                    {
                        public void run()
                        {
                            //log("Working on: " + ip);
                            try
                            {
                                String inputLine;
                                BufferedReader in = new BufferedReader(new InputStreamReader(new URL("http://" + ip).openStream()));
                                while((inputLine = in.readLine()) != null)
                                {
                                    if(inputLine.contains("AltisCraft"))
                                    {
                                        log(ip);
                                        return;
                                    }
                                    //log(ip);
                                    //return;
                                }
                                in.close();
                            }
                            catch(Exception e)
                            {
                                //
                            }
                        }
                    };
                    th.start();
                }
            }
            scan.close();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private static void longFor()
    {
        for(part2 = 0; part2 < 256; part2++)
        {
            for(part3 = 0; part3 < 256; part3++)
            {
                Thread th = new Thread()
                {
                    public void run()
                    {
                        String ip = "32.27." + part2 + "." + part3, inputLine;
                        log("Working on: " + ip);
                        try
                        {
                            BufferedReader in = new BufferedReader(new InputStreamReader(new URL("http://" + ip).openStream()));
                            while((inputLine = in.readLine()) != null)
                            {
                                log(inputLine);
                            }
                            in.close();
                        }
                        catch(Exception e)
                        {
                            // e.printStackTrace(); print for all no host
                        }
                    }
                };
                th.start();
            }
        }
    }
}