import java.io.File;
import java.io.FileWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        double ERROR_RANGE = 0.1;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        File[] files = new File(".").listFiles();
        for(File file : files)
        {
            String fileName = file.getName();
            if(fileName.endsWith(".csv"))
            {
                System.out.println("Working on: " + fileName);
                ArrayList<String> lines = new ArrayList<String>();
                try
                {
                    Scanner scanner = new Scanner(file);
                    while(scanner.hasNextLine())
                    {
                        String line = scanner.nextLine(), lineParts[] = line.split(",");
                        if(lineParts[1].charAt(0) != 'y')
                        {
                            lines.add(lineParts[0] + "," + df.format(Double.parseDouble(lineParts[1]) + Math.random() * ERROR_RANGE));
                        }
                        else
                            lines.add(line);
                    }
                    scanner.close();
                    file.delete();
                    FileWriter fw = new FileWriter(file);
                    int linesSize = lines.size();
                    for(int lineIndex = 0; lineIndex < linesSize; lineIndex++)
                    {
                        fw.write(lines.get(lineIndex));
                        if(lineIndex < linesSize - 1)
                            fw.write("\n");
                    }
                    fw.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}