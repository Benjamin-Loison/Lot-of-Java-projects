import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main
{
    private static ArrayList<String> users = new ArrayList<String>();
    static double sumClass = 0;
    static int students = 0;
    static HashMap<String, Double> sums = new HashMap<String, Double>();
    static HashMap<String, Pairee> notesProf = new HashMap<String, Pairee>();
    static DecimalFormat formatter = new DecimalFormat("##.##");

    private static void forFunction(int userIndex)
    {
        String user = users.get(userIndex);

        try
        {
            URL url = new URL("https://example.com");
            String urlStr = "default password";

            String authStringEnc = new String(Base64.getEncoder().encode((user + ":" + urlStr).getBytes()));
            HttpURLConnection HttpurlConnection = (HttpURLConnection)url.openConnection();
            HttpurlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);

            // System.out.println(urlStr);
            BufferedReader in = new BufferedReader(new InputStreamReader(HttpurlConnection.getInputStream()));
            System.out.print("Eleve: " + user + "\nNotes:\n");

            students++;
            String inputLine;
            ArrayList<Integer> notes = new ArrayList<Integer>();
            int note = 0;
            while((inputLine = in.readLine()) != null)
            {
                // System.out.println("@" + inputLine + "@");
                if(inputLine.contains("Semaine"))
                {
                    String[] parts = inputLine.split(":");
                    note = Integer.parseInt(parts[1].replaceAll(" ", ""));
                    notes.add(note);
                    System.out.print(note + " ");
                }
                else if(inputLine.contains(" avec "))
                {
                    String[] parts = inputLine.split(" avec ");
                    // String noteStr = parts[0].replaceAll(" ", "");
                    // System.out.println("!" + inputLine + "!");
                    // int note = Integer.parseInt(noteStr);
                    String prof = parts[1].replace("<br />", "");
                    System.out.println(prof);
                    if(notesProf.containsKey(prof))
                    {
                        Pairee pair = notesProf.get(prof); // TODO: display with prof name
                        pair.add(note);
                        notesProf.put(prof, pair);
                    }
                    else
                    {
                        Pairee pair = new Pairee(note);
                        notesProf.put(prof, pair);
                        // System.out.println("New: " + prof + " " + pair.toString());
                    }
                }
            }
            double sume = 0;
            int notesSize = notes.size();
            for(int noteIndex = 0; noteIndex < notesSize; noteIndex++)
            {
                sume += notes.get(noteIndex);
            }

            System.out.println("\nMoyenne: " + formatter.format(sume / notesSize) + "\n");
            sumClass += sume / notesSize;
            sums.put(user, sume / notesSize);
        }
        catch(Exception e)
        {}
    }

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

        int usersSize = users.size();
        for(int userIndex = 0; userIndex < usersSize; userIndex++)
        {
            forFunction(userIndex);
        }
        System.out.println("\nMoyenne de classe: " + sumClass / students);

        Map<String, Double> sorted = sums.entrySet().stream().sorted(comparingByValue()).collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));

        int rank = students;
        for(String key : sorted.keySet())
        {
            System.out.println(rank + " " + key + " " + sorted.get(key));
            rank--;
        }
        HashMap<String, Double> newNotesProf = new HashMap<String, Double>();
        
        
        System.out.println("\n");
        for(String key : notesProf.keySet())
        {
            newNotesProf.put(key, notesProf.get(key).average());
        }
        
        sorted = newNotesProf.entrySet().stream().sorted(comparingByValue()).collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));

        
        rank = sorted.size();
        for(String key : sorted.keySet())
        {
            System.out.println(rank + " " + key + " " + formatter.format(sorted.get(key)));
            rank--;
            
        }
        
    }

}

class Pairee
{
    public double notesNB = 0, somme = 0;

    public Pairee(int note)
    {
        // System.out.println("add: " + note + " " + somme);
        add(note);
    }

    public void add(double note)
    {
        notesNB++;
        // System.out.println("before: " + note + " " + somme);
        somme = somme + note;
        // System.out.println("after: " + note + " " + somme);
    }

    public double average()
    {
        // System.out.println("^" + somme + "^" + notesNB + "^");
        double averag = somme / notesNB;
        // System.out.println("_" + averag + "_");
        return averag;
    }

    @Override
    public String toString()
    {
        return "NotesNB: " + notesNB + " Sum: " + somme + " Average: " + average();
    }
}