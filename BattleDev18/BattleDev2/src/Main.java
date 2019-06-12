import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    static int best = 0;

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int etu = Integer.parseInt(sc.nextLine());
        ArrayList<String> arr = new ArrayList<String>();
        ArrayList<ArrayList<String>> allPossibilities = new ArrayList<ArrayList<String>>();
        for(int i = 0; i < etu; i++)
        {
            String line = sc.nextLine();
            arr.add(line);
            allPossibilities.add(creneauxPossibles(line));
        }
        ArrayList<ArrayList<Integer>> allPossibilitiesInt = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < allPossibilities.size(); i++)
        {
            allPossibilitiesInt.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < allPossibilities.size(); i++)
        {
            ArrayList<Integer> ar = new ArrayList<Integer>();
            // if(allPossibilitiesInt.size() == 0)
            // allPossibilitiesInt.add(new ArrayList<Integer>());
            ArrayList<String> posibilites = allPossibilities.get(i);
            for(int j = 0; j < allPossibilities.get(i).size(); j++)
            {
                ArrayList<Integer> tmp = allPossibilitiesInt.get(i);
                tmp.add(j);
                allPossibilitiesInt.set(i, tmp);
            }
            // allPossibilitiesInt.add(ar);
            System.out.println(ar.toString());
        }
        if(possible(arr))
        {
            int a = nb(arr);
            if(a > best)
                best = a;
        }
        next(arr, 0);
        System.out.println(best);

    }

    public static ArrayList<String> creneauxPossibles(String creneaux)
    {
        ArrayList<String> arr = new ArrayList<String>();
        String[] parts = creneaux.split(" ");
        int a = Integer.parseInt(parts[0]), b = Integer.parseInt(parts[1]);
        for(int i = a; i <= b - 60; i++)
        {
            arr.add(i + " " + (i + 60));
        }
        return arr;
    }

    public static ArrayList<String> next(ArrayList<String> lessons, int workOn)
    {
        String work = lessons.get(workOn);
        String[] parts = work.split(" ");
        int c = Integer.parseInt(parts[0]), d = Integer.parseInt(parts[1]);
        if(Math.abs(c - d) > 60)
            lessons.set(workOn, (c + 1) + " " + d);
        else
        {
            if(workOn == lessons.size())
                return lessons;
            workOn++;
        }
        // if already all changed
        if(possible(lessons))
        {
            int a = nb(lessons);
            if(a > best)
                best = a;
        }
        return next(lessons, workOn + 1);
    }

    public static int nb(ArrayList<String> lessons)
    {
        return lessons.size();
    }

    public static boolean possible(ArrayList<String> lessons)
    {
        for(int first = 0; first < lessons.size(); first++)
        {
            for(int second = 0; second < lessons.size(); second++)
            {
                if(first != second)
                {
                    String firstL = lessons.get(first), secondL = lessons.get(second);
                    int firstL0 = Integer.parseInt(firstL.split(" ")[0]), firstL1 = Integer.parseInt(firstL.split(" ")[1]);
                    int secondL0 = Integer.parseInt(secondL.split(" ")[0]), secondL1 = Integer.parseInt(secondL.split(" ")[1]);
                    if((firstL0 <= secondL0 && secondL0 <= firstL1) || (firstL0 <= secondL1 && secondL1 <= firstL1) || (secondL0 <= firstL0 && firstL0 <= secondL1) || (secondL0 <= firstL1 && firstL1 <= secondL1))
                    {
                        return false;
                    }
                    if(firstL0 == secondL0 || firstL1 == secondL0 || firstL0 == secondL1 || firstL1 == secondL1)
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}