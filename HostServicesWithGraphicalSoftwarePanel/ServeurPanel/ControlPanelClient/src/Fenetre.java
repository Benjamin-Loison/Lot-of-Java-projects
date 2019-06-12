import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Fenetre extends JFrame implements ActionListener
{
    private Bouton bouton = new Bouton("Rafraichir");

    public Fenetre()
    {
        setTitle("ControlPanel");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new Panneau());
        setVisible(true);
        add(bouton);
        bouton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent arg0)
    {
        if(arg0.getID() == 1001)
        {
            lance();
        }
    }

    final static int port = 9633, taille = 1024;
    static byte buffer[] = new byte[taille];
    static String texte = "Aucune information";

    public static String lance()
    {
        try
        {
            InetAddress serveur = InetAddress.getByName("crise.altiscraft.fr");
            String message = "Lance";
            int length = message.length();
            byte buffer[] = message.getBytes();
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket donneesEmises = new DatagramPacket(buffer, length, serveur, port), donneesRecues = new DatagramPacket(new byte[taille], taille);
            socket.setSoTimeout(500);
            socket.send(donneesEmises);
            socket.receive(donneesRecues);
            if(Integer.parseInt(new String(donneesRecues.getData(), 0, donneesRecues.getLength())) == 1)
            {
                Panneau.texte("Serveur en cours de lancement !");
            }
            else
            {
                Panneau.texte("Erreur !");
            }
            socket.close();
        }
        catch(SocketTimeoutException ste)
        {
            Panneau.texte("Le delai pour la réponse a expiré !");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return texte;
    }
}