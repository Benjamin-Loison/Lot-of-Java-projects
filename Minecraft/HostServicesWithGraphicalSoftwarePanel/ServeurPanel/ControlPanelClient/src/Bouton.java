import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Bouton extends JButton implements MouseListener
{
    private String name;

    public Bouton(String str)
    {
        super(str);
        name = str;
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.white);
        g2d.drawString(name, getWidth() / 2 - getWidth() / 2 / 4, getHeight() / 2 + 5);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {}

    @Override
    public void mouseEntered(MouseEvent e)
    {}

    @Override
    public void mouseExited(MouseEvent e)
    {}

    @Override
    public void mousePressed(MouseEvent e)
    {}

    @Override
    public void mouseReleased(MouseEvent e)
    {}
}