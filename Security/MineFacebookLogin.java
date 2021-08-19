import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Main
{
    final static String DELIMITER = "";
    
    public static void main(String[] args)
    {
        // Pas encore là je fais la lecture de mes données brutes et extraction des logins Facebook (avec vérif doublon) avant la récré pour les doublons, dépend de l'API de Facebook
        // Algorithme pour filtrer les identifiants Facebook avec une blacklist, un système d'empêchement de doublons voire un test des identifiants
        HashMap<String, ArrayList<String>> logins = new HashMap<String, ArrayList<String>>();
        try
        {
            File currentDirectory = new File(".");
            File[] files = currentDirectory.listFiles();
            for(File file : files)
            {
                String fileName = file.getName();
                if(fileName.endsWith(".log"))
                {
                    Scanner scan = new Scanner(file);
                    while(scan.hasNextLine())
                    {
                        String line = scan.nextLine();
                        if(line.contains("Website:") && line.contains("facebook.com"))
                        {
                            String username = scan.nextLine().replace(" Username: ", "");
                            String password = scan.nextLine().replace(" Password: ", "");
                            ArrayList<String> passwords;
                            if(logins.containsKey(username))
                            {
                                passwords = logins.get(username);
                                if(passwords.contains(password))
                                    continue;
                                passwords.add(password);
                                logins.put(username, passwords);
                                System.out.println(DELIMITER + username + " " + password + DELIMITER);
                            }
                            else
                            {
                                passwords = new ArrayList<String>();
                                passwords.add(password);
                                logins.put(username, passwords);
                                System.out.println(DELIMITER + username + " " + password + DELIMITER);
                            }
                        }
                    }
                    scan.close();
                }
            }
            /*Iterator<String> iteratorUsernames = logins.keySet().iterator();
            while(iteratorUsernames.hasNext())
            {
                String username = iteratorUsernames.next();
                ArrayList<String> passwords = logins.get(username);
            }*/
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}