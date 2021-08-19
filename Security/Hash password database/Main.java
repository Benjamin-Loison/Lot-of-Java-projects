import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main
{
    static String characters = "~$%&'()*+,-./:;<=>?@[\\]^_`{|}~ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    
    public static void main(String[] args)
    {
        try
        {
            FileWriter fw = new FileWriter("auth.txt");
            File folder = new File("tmp");
            int i = 0;
            for(File file : folder.listFiles())
            {
                i++;
                String[] names = file.getName().replace(".txt", "").split("!")[0].split("_");
                Scanner scan = new Scanner(file);
                // 3 - 15 both
                if(names.length != 2) continue;
                if((names[0].length() < 3 || names[0].length() > 15) || (names[1].length() < 3 || names[1].length() > 15)) continue;
                if(!scan.hasNextLine()) continue;
                String pw = scan.nextLine();
                for(int j = 0; j < pw.length(); j++)
                    if(!characters.contains(pw.charAt(j) + "")) continue;
                if(pw.length() < 8 || pw.length() > 20) continue;
                String hash = BCrypt.hashpw(pw, BCrypt.gensalt(8)), name = names[0] + " " + names[1]; // salt 8
                fw.write(name + " 0.0.0.0 " + hash);
                if(i < folder.listFiles().length)
                    fw.write("\n");
                scan.close();
                System.out.println(name + " " + i + " " + folder.listFiles().length);
            }
            fw.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}