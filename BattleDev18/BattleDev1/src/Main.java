
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main
{
    public static void main(String[] argv) throws Exception
    {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        String line;
        Scanner sc = new Scanner(System.in);
        int req = Integer.parseInt(sc.nextLine());
        double nDiv2 = req / 2.0;
        boolean inf = false;
        int count = 0;
        String[] parts = sc.nextLine().split(" ");
        for(int i = 0; i <= req; i++)
        {
            int newIt = Integer.parseInt(parts[i]);
            arr.add(newIt);
            /*
             * if(i != 0)
             * {
             * int old = arr.get(i - 1);
             * if(old == newIt && old == nDiv2)
             * {
             * inf = true;
             * break;
             * }
             * else
             * {
             * if((nDiv2 >= old && nDiv2 <= newIt) || (nDiv2 <= old && nDiv2 >= newIt))
             * {
             * count++;
             * }
             * }
             * if(i == 1)
             * {
             * if(old != newIt && old == nDiv2)
             * count++;
             * }
             * }
             * else
             * {
             * }
             */
        }
        for(int i = 0; i < req; i++)
        {
            int next = arr.get(i + 1);
            int now = arr.get(i);
            if(next == now && now == nDiv2)
            {
                inf = true;
            }
            if((now <= nDiv2 && nDiv2 <= next && now != nDiv2) || (now >= nDiv2 && nDiv2 >= next && now != nDiv2) )
            {
                System.out.println(i + " " + nDiv2 + " " + (now <= nDiv2 && nDiv2 <= next) + " " + (now >= nDiv2 && nDiv2 >= next));
                count++;
            }
        }
        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données. */
        if(!inf)
        {
            System.out.println(count);
        }
        else
        {
            System.out.println("INF");
        }
    }

    public static int test(int arg)
    {
        Random ran = new Random();
        int r = ran.nextInt(arg + 1);
        return r + arg;
    }
}