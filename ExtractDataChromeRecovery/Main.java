import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Time.initializeTimer();
        File currentFolder = new File(".");
        File[] files = currentFolder.listFiles();
        for(int i = 0; i < files.length; i++)
        {
            File file = files[i];
            String fName = file.getName();
            if(fName.endsWith(".txt"))
            {
                Disp.print("Working on: " + fName);
                try
                {
                    Scanner scanner = new Scanner(file);
                    ArrayList<String> websites = new ArrayList<String>(), usernames = new ArrayList<String>(), passwords = new ArrayList<String>(), emails = new ArrayList<String>();
                    while(scanner.hasNextLine())
                    {
                        String line = scanner.nextLine();
                        if(line.contains("Website"))
                        {
                            String toAdd = line.replace(" Website:  ", "");
                            if(!websites.contains(toAdd))
                                websites.add(toAdd);
                        }
                        else if(line.contains("Username"))
                        {
                            String toAdd = line.replace(" Username: ", "");
                            if(toAdd.contains("@"))
                            {
                                if(!emails.contains(toAdd))
                                    emails.add(toAdd);
                            }
                            else if(!usernames.contains(toAdd))
                                usernames.add(toAdd);
                        }
                        else if(line.contains("Password"))
                        {
                            if(line.contains("Chrome Password Recovery Report") || line.contains("ChromePasswordDump"))
                                continue;
                            String toAdd = line.replace(" Password: ", "");
                            if(!passwords.contains(toAdd))
                                passwords.add(toAdd);
                        }
                    }

                    Disp.print("Website:");
                    for(int websiteIndex = 0; websiteIndex < websites.size(); websiteIndex++)
                    {
                        Disp.print(websites.get(websiteIndex));
                    }

                    Disp.print("");
                    Disp.print("Emails:");
                    for(int emailIndex = 0; emailIndex < emails.size(); emailIndex++)
                    {
                        Disp.print(emails.get(emailIndex));
                    }

                    Disp.print("");
                    Disp.print("Username:");
                    for(int usernameIndex = 0; usernameIndex < usernames.size(); usernameIndex++)
                    {
                        Disp.print(usernames.get(usernameIndex));
                    }

                    Disp.print("");
                    Disp.print("Password:");
                    for(int passwordIndex = 0; passwordIndex < passwords.size(); passwordIndex++)
                    {
                        Disp.print(passwords.get(passwordIndex));
                    }

                    scanner.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                Disp.print("Do not work on: " + fName);
            }
        }
        Disp.print("Work finished in " + Time.getExecuteTimeInMs() + " ms !");
    }
}