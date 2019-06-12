import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main
{
    static final String AUDIO_FILE_EXTENSION = ".mp3";
    static ArrayList<String> toRemove;
    static HashMap<String, String> configuration;
    
    // do not consider symbol as part of a word
    // artificial intelligence for strings which do not have a "splitArtistAndSong" to detect author or song name
    // regex for configuration file (several ?)
    // put feat in one
    // manage global organisation name: different cases of a feat, versus...
    
    public static void main(String[] args)
    {
        try
        {
            System.out.println("Loading configuration...");
            toRemove = new ArrayList<String>();
            configuration = new HashMap<String, String>();
            File configurationFile = new File("configuration.txt");
            Scanner scanner = new Scanner(configurationFile);
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                if(line.startsWith("/") || line.equals(""))
                    continue;
                String content = line.substring(1);
                if(line.startsWith("-"))
                    toRemove.add(content);
                else if(line.startsWith("#"))
                {
                    String[] parts = content.split("=");
                    if(parts.length > 1)
                        configuration.put(parts[0], parts[1]);
                }
            }
            scanner.close();
            System.out.println("Configuration loaded !");

            File currentFolder = new File(".");
            File[] currentFiles = currentFolder.listFiles();
            for(File currentFile : currentFiles)
            {
                String fileNameWithExtension = currentFile.getName();
                if(!fileNameWithExtension.endsWith(AUDIO_FILE_EXTENSION))
                    continue;
                String fileName = currentFile.getName().replace(AUDIO_FILE_EXTENSION, "");

                System.out.println("Working on: " + fileNameWithExtension);
                fileName = nameWithRemove(fileName);
                System.out.println("0: " + fileName + "@");
                fileName = removeDoubleSpaces(fileName);
                System.out.println("1: " + fileName + "@");
                fileName = stopUpperCase(fileName);
                System.out.println("2: " + fileName + "@");
                
                
                String key = "splitArtistAndSong";
                if(configuration.containsKey(key))
                {
                    String artistAndSongSplit = configuration.get(key);
                    if(!fileName.contains(artistAndSongSplit))
                    {
                        File newFile = new File(fileName + AUDIO_FILE_EXTENSION);
                        currentFile.renameTo(newFile);
                        continue;
                    }
                    fileName = putFeatInSongName(fileName);
                    System.out.println("3a: " + fileName + "@");
                    fileName = removeDoubleSpaces(fileName);
                    System.out.println("3b: " + fileName + "@");
                    String[] parts = fileName.split(artistAndSongSplit);
                    File newFile = new File(parts[0] + File.separatorChar + parts[1] + AUDIO_FILE_EXTENSION);
                    newFile.getParentFile().mkdir();
                    currentFile.renameTo(newFile);
                }
                else
                {
                    File newFile = new File(fileName + AUDIO_FILE_EXTENSION);
                    currentFile.renameTo(newFile);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    // if executed, consider that name contains "splitArtistAndSong"
    public static String putFeatInSongName(String name)
    {
        String key = configuration.get("splitArtistAndSong");
        String[] parts = name.split(key);
        String author = parts[0];
        String song = parts[1];
        if(author.contains(","))
        {
            int featIndex = author.indexOf(",");
            String feat = author.substring(featIndex + 2);
            author = author.substring(0, featIndex);
            song += " ft. " + feat;
        }
        else if(author.contains(" &"))
        {
            int featIndex = author.indexOf(" &");
            String feat = author.substring(featIndex + 3);
            author = author.substring(0, featIndex);
            song += " ft. " + feat;
        }
        else if(author.contains(" ft.")) // order is important
        {
            int featIndex = author.indexOf(" ft.");
            String feat = author.substring(featIndex + 5);
            author = author.substring(0, featIndex);
            song += " ft. " + feat;
        }
        else if(author.contains(" ft"))
        {
            int featIndex = author.indexOf(" ft");
            String feat = author.substring(featIndex + 4);
            author = author.substring(0, featIndex);
            song += " ft. " + feat;
        }
        else if(author.contains(" feat."))
        {
            int featIndex = author.indexOf(" feat.");
            String feat = author.substring(featIndex + 7);
            author = author.substring(0, featIndex);
            song += " ft. " + feat;
        }
        return author + key + song;
    }
    
    public static String removeDoubleSpaces(String name)
    {
        return name.replaceAll("  ", " ");
    }
    
    public static String stopUpperCase(String name)
    {
        String parts[] = name.split(" "), newName = "";
        int partsLength = parts.length;
        for(int partsIndex = 0; partsIndex < partsLength; partsIndex++)
        {
            String part = parts[partsIndex];
            if(part.toUpperCase().equals(part) && !part.equals(""))
            {
                part = part.charAt(0) + part.substring(1).toLowerCase();
            }
            newName += part;
            if(partsIndex != partsLength - 1)
                newName += " ";
        }
        return newName;
    }
    
    public static String nameWithRemove(String name)
    {
        int toRemoveSize = toRemove.size();
        for(int toRemoveIndex = 0; toRemoveIndex < toRemoveSize; toRemoveIndex++)
        {
            String toRemoveStr = toRemove.get(toRemoveIndex);
            name = name.replace(toRemoveStr, "");
        }
        return name;
    }
}