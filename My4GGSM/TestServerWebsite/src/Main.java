import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Main
{
    private final static int port = 12345;
    private static byte buffer[] = new byte[1024];

    public static void main(String[] args)
    {
        System.out.println("Launching server on the port " + port + " !");
        try
        {
            DatagramSocket socket = new DatagramSocket(port);
            while(true)
            {
                System.out.println("Waiting new requests !");
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                InetAddress address = packet.getAddress();
                String ip = address.toString().replace("/", "");
                String packetData = new String(packet.getData(), 0, packet.getLength());
                System.out.println(ip + " " + packetData);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}