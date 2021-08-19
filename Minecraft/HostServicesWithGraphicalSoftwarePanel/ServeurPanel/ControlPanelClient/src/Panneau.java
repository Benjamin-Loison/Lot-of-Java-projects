import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panneau extends JPanel
{
    final static int port = 9633, taille = 1024;
    static byte buffer[] = new byte[taille];
    static String texte = "Aucune information";

    public static void texte(String text)
    {
        texte = text;
    }

    public static String information()
    {
        try
        {
            InetAddress serveur = InetAddress.getByName("crise.altiscraft.fr");
            String message = "Serveur";
            int length = message.length();
            byte buffer[] = message.getBytes();
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket donneesEmises = new DatagramPacket(buffer, length, serveur, port), donneesRecues = new DatagramPacket(new byte[taille], taille);
            socket.setSoTimeout(500);
            socket.send(donneesEmises);
            socket.receive(donneesRecues);
            if(Integer.parseInt(new String(donneesRecues.getData(), 0, donneesRecues.getLength())) == 1)
            {
                texte = "Serveur lancé !";
            }
            else
            {
                texte = "Serveur arrêté !";
            }
            socket.close();
        }
        catch(SocketTimeoutException ste)
        {
            texte = "Le delai pour la réponse a expiré !";
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return texte;
    }

    public void paintComponent(Graphics g)
    {
        try
        {
            g.drawImage(ImageIO.read(new File("Fond.jpeg")), 0, 0, getWidth(), getHeight(), this);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        g.setFont(new Font("Courier", Font.BOLD, 20));
        g.setColor(Color.red);
        information();
        g.drawString(texte, 10, 20);
    }
}