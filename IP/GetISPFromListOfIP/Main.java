import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Main
{
    static final String LINE_CONTAINS = "            <p class=\"isp\">";
    static int requestsRunning = 0, requestsCompleted = 0;

    public static void main(String[] args)
    {
        Disp.init();
        Time.initializeTimer();
        ArrayList<String> ips = new ArrayList<String>();
        HashMap<String, Integer> isps = new HashMap<String, Integer>();
        File ipFile = new File("ips.txt");
        try
        {
            Scanner scanner = new Scanner(ipFile);
            while(scanner.hasNextLine())
            {
                String ip = scanner.nextLine();
                ips.add(ip);
            }
            scanner.close();
            for(int ipIndex = 0; ipIndex < ips.size(); ipIndex++)
            {
                String requestThread = "https://www.whoismyisp.org/ip/" + ips.get(ipIndex);
                Thread th = new Thread(new Runnable()
                {
                    public void run()
                    {
                        try
                        {
                            requestsRunning++;
                            URL url = new URL(requestThread);
                            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                            String line;
                            while((line = in.readLine()) != null)
                            {
                                if(line.contains(LINE_CONTAINS))
                                {
                                    String isp = line.replace(LINE_CONTAINS, "").replace("</p>", "");
                                    if(isps.containsKey(isp))
                                    {
                                        int ispCount = isps.get(isp);
                                        isps.put(isp, ispCount + 1);
                                    }
                                    else
                                    {
                                        isps.put(isp, 1);
                                    }
                                    break;
                                }
                            }
                            requestsCompleted++;
                            requestsRunning--;
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                });
                th.start(); // 1 bug
                Thread.sleep(2); // 10 most
            }
            Disp.print("Waiting all threads to finish");
            while(requestsRunning != 0)
            {
                Disp.print("Waiting threads: " + requestsRunning);
                Thread.sleep(1000);
            }
            Iterator<String> ispsKeys = isps.keySet().iterator();
            while(ispsKeys.hasNext())
            {
                String ispKey = ispsKeys.next();
                Disp.print(ispKey + " " + isps.get(ispKey));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}