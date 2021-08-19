package fr.benjaminloison.teamspeak.main;

import java.io.File;
import java.io.FileWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class TeamSpeak extends JavaPlugin implements Listener
{
    File ts = new File(new File("").getAbsolutePath() + File.separatorChar + "plugins" + File.separatorChar + "TeamSpeak"), config = new File(ts.getAbsolutePath() + File.separatorChar + "Config.yml");
    String ip, port, queryAdmin, queryPassword, queryPort, queryUserNick;

    @Override
    public void onEnable()
    {
        try
        {
            ts.mkdirs();
            if(!config.exists())
            {
                FileWriter fileWriter = new FileWriter(config);
                fileWriter.write("ip=localhost\nport=9987\nquery_admin=serveradmin\nquery_password=******\nquery_port=10011\nquery_user_nick=BOT TeamSpeak Minecraft");
                fileWriter.close();
            }
            Scanner scan = new Scanner(config);
            ip = scan.nextLine().replace("ip=", "");
            port = scan.nextLine().replace("port=", "");
            queryAdmin = scan.nextLine().replace("query_admin=", "");
            queryPassword = scan.nextLine().replace("query_password=", "");
            queryPort = scan.nextLine().replace("query_port=", "");
            queryUserNick = scan.nextLine().replace("query_user_nick=", "").replace(" ", "%20");
            scan.close();
            getServer().getPluginManager().registerEvents(this, this);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    @EventHandler
    public void onCommandServer(ServerCommandEvent event)
    {
        String command = event.getCommand();
        try
        {
            if(command.startsWith("ts "))
            {
                String message = "PHP " + ip + " " + port + " " + queryAdmin + " " + queryPassword + " " + queryPort + " " + queryUserNick + command.replaceFirst("ts", "");
                DatagramSocket socket = new DatagramSocket();
                DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
                socket.setSoTimeout(5000);
                socket.send(new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName("altiscraft.fr"), 59722));
                socket.receive(packet);
                print(message);
                String[] results = new String(packet.getData(), 0, packet.getLength()).split("\n");
                print("Result: " + results[0]);
                for(int i = 1; i < results.length; i++)
                    print(results[i]);
                socket.close();
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void print(Object object)
    {
        getLogger().info(object.toString());
    }
}