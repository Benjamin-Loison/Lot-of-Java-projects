import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Main
{
    public static void main(String[] args)
    {
        String message = "Hello World !";
        try
        { // look for google key words: Simulate UDP/TCP
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket receivedData = new DatagramPacket(new byte[1024], 1024);
            socket.setSoTimeout(60000);
            socket.send(new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName("localhost"), 12345));
            socket.receive(receivedData);
            socket.close();
            String received = new String(receivedData.getData(), 0, receivedData.getLength());
            System.out.println(received);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}