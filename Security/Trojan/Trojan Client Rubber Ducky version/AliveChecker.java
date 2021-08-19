import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.TimerTask;

class AliveChecker extends TimerTask
{
    public void run()
    {
        String aliveMsg = Main.PREFIX + "alive";
        try
        {
            Main.socket.send(new DatagramPacket(aliveMsg.getBytes(), aliveMsg.length(), InetAddress.getByName(Main.ip), Main.PORT));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}