package fr.ftpfolderuploader.benjaminloison.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class Main
{
    static File ftpFile;
    static String basics[], path, ip;

    public static void main(String[] args)
    {
        if(args.length <= 2)
        {
            System.out.println("ftpFile path basics");
            System.exit(0);
        }

        ftpFile = new File(args[0]);
        ftpFile.delete();
        path = args[1];
        int len = args.length;
        basics = new String[len - 2];
        for(int i = 2; i < len; i++)
            basics[i - 2] = args[i];
        loadIP();
        list(path);
    }

    public static void loadIP()
    {
        try
        {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
            ip = in.readLine();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void list(String directoryName)
    {
        ArrayList<File> files = new ArrayList<File>();
        File directory = new File(directoryName);
        File[] fList = directory.listFiles();
        for(File file : fList)
        {
            if(file.isFile())
            {
                files.add(file);
                System.out.println(file);
            }
            else if(file.isDirectory())
            {
                list(file.getAbsolutePath());
            }
        }
        System.out.println(directoryName + " " + files.size());
        upload(directory, files);
    }

    public static void writeFilesToUpload(File parentFolderToTreat, FileWriter fw, ArrayList<File> files)
    {
        try
        {
            String parentFolder = (ip + "/" + parentFolderToTreat.getAbsolutePath()).replace("\\", "/"), dir = "";
            String[] folders = parentFolder.split("/");
            for(int i = 0; i < folders.length; i++)
            {
                dir += folders[i] + "/";
                fw.write("mkdir \"" + dir + "\"\n");
            }
            fw.write("cd \"" + parentFolder + "\"\n");
            for(int i = 0; i < files.size(); i++)
                fw.write("mput \"" + files.get(i).getAbsolutePath() + "\"\n");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }

    public static void upload(File folder, ArrayList<File> files)
    {
        write(folder, files);
        uploadCmd();
    }

    public static void uploadCmd()
    {
        try
        {
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "ftp", "-i", "-s:" + ftpFile.getAbsolutePath());
            pb.redirectErrorStream(true);
            BufferedReader inStreamReader = new BufferedReader(new InputStreamReader(pb.start().getInputStream()));
            String line = "", lines = "";
            while((line = inStreamReader.readLine()) != null)
            {
                lines += line + "\n";
            }
            System.out.println(lines);
            if(lines.contains("Not connected."))
                uploadCmd();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void write(File folder, ArrayList<File> files)
    {
        try
        {
            FileWriter fw = new FileWriter(ftpFile);
            for(int i = 0; i < basics.length; i++)
                fw.write(basics[i] + "\n");
            writeFilesToUpload(folder, fw, files);
            fw.write("disconnect\n");
            fw.write("quit");
            fw.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}