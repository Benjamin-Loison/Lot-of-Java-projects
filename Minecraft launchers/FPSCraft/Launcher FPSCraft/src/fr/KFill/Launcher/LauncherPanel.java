package fr.KFill.Launcher;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import fr.theshark34.openauth.AuthenticationException;
import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.openlauncherlib.util.Saver;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.animation.Animator;
import fr.theshark34.swinger.colored.SColoredBar;
import fr.theshark34.swinger.event.SwingerEvent;
import fr.theshark34.swinger.event.SwingerEventListener;
import fr.theshark34.swinger.textured.STexturedButton;

@SuppressWarnings("serial")
public class LauncherPanel extends JPanel implements SwingerEventListener, KeyListener
{
    private Saver saver = new Saver(new File(Launcher.LC_DIR, "launcher.properties"));
    private JTextField nameField = new JTextField(saver.get("Family Name"));
    String lign = "";
    File nameFile = new File(Launcher.LC_INFOS.getGameDir() + "\\Name.txt");
    private STexturedButton playButton = new STexturedButton(Swinger.getResource("play.png")), quitButton = new STexturedButton(Swinger.getResource("quit.png")), hideButton = new STexturedButton(Swinger.getResource("hide.png"));
    private SColoredBar progressBar = new SColoredBar(new Color(0, 255, 0, 70), new Color(255, 0, 0, 70));
    private JLabel infoLabel = new JLabel("Cliquez sur jouer !", SwingConstants.CENTER);

    public LauncherPanel() throws Exception
    {
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);
        setLayout(null);
        if(nameFile.exists())
        {
            Scanner sc = new Scanner(nameFile);
            if(sc.hasNextLine())
                nameField.setText(sc.nextLine());
            sc.close();
        }
        else
            try
            {
                FileWriter fw = new FileWriter(nameFile);
                fw.write("");
                fw.close();
            }
            catch(Exception e)
            {}

        nameField.setForeground(Color.WHITE);
        nameField.setCaretColor(Color.WHITE);
        nameField.setFont(nameField.getFont().deriveFont(25F));
        nameField.setOpaque(false);
        nameField.setBorder(null);
        nameField.setBounds(655, 212, 306, 25);
        add(nameField);

        playButton.setBounds(777, 250, 192, 42);
        playButton.addEventListener(this);
        add(playButton);

        quitButton.setBounds(955, 2, 16, 16);
        quitButton.addEventListener(this);
        add(quitButton);

        hideButton.setBounds(940, 2, 16, 16);
        hideButton.addEventListener(this);
        add(hideButton);

        progressBar.setBounds(11, 592, 953, 20);
        add(progressBar);

        infoLabel.setForeground(Color.white);
        infoLabel.setBounds(11, 592, 953, 20);
        add(infoLabel);
    }

    public void onEvent(SwingerEvent e)
    {
        if(e.getSource() == playButton)
            play();
        else if(e.getSource() == quitButton)
            Animator.fadeOutFrame(LauncherFrame.getInstance(), 3, new Runnable()
            {
                public void run()
                {
                    System.exit(0);
                }
            });
        else if(e.getSource() == hideButton)
            LauncherFrame.getInstance().setState(JFrame.ICONIFIED);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(Swinger.getResource("background.png"), 0, 0, getWidth(), getHeight(), this);
    }

    private void setFieldsEnabled(boolean enabled)
    {
        nameField.setEnabled(enabled);
        playButton.setEnabled(enabled);
    }

    public SColoredBar getProgressBar()
    {
        return progressBar;
    }

    public void setInfoText(String text)
    {
        infoLabel.setText(text);
    }

    public void keyPressed(KeyEvent event)
    {
        if(event.getKeyCode() == 10)
            play();
    }

    public void play()
    {
        setFieldsEnabled(false);
        if(nameField.getText().startsWith("-") || nameField.getText().endsWith("-"))
        {
            JOptionPane.showMessageDialog(this, "Erreur, veuillez ne pas mettre de - au début ou/et à la fin de votre prénom.", "Error", JOptionPane.ERROR_MESSAGE);
            setFieldsEnabled(true);
            return;
        }
        nameField.setText(nameField.getText().replace(" ", ""));
        nameField.setText(nameField.getText().replace(".", ""));
        nameField.setText(nameField.getText().replace(",", ""));
        nameField.setText(nameField.getText().replace("@", ""));
        nameField.setText(nameField.getText().replace("â", "a"));
        nameField.setText(nameField.getText().replace("à", "a"));
        nameField.setText(nameField.getText().replace("ä", "a"));
        nameField.setText(nameField.getText().replace("é", "e"));
        nameField.setText(nameField.getText().replace("è", "e"));
        nameField.setText(nameField.getText().replace("ê", "e"));
        nameField.setText(nameField.getText().replace("ë", "e"));
        nameField.setText(nameField.getText().replace("ô", "o"));
        nameField.setText(nameField.getText().replace("ö", "o"));
        nameField.setText(nameField.getText().replace("î", "i"));
        nameField.setText(nameField.getText().replace("ï", "i"));
        nameField.setText(nameField.getText().replace("<", ""));
        nameField.setText(nameField.getText().replace(">", ""));
        nameField.setText(nameField.getText().replace("!", ""));
        nameField.setText(nameField.getText().replace("?", ""));
        nameField.setText(nameField.getText().replace("*", ""));
        nameField.setText(nameField.getText().replace("€", ""));
        nameField.setText(nameField.getText().replace("$", ""));
        nameField.setText(nameField.getText().replace("£", ""));
        nameField.setText(nameField.getText().replace(":", ""));
        nameField.setText(nameField.getText().replace(";", ""));
        nameField.setText(nameField.getText().replace("/", ""));
        nameField.setText(nameField.getText().replace("\\\\", ""));
        nameField.setText(nameField.getText().replace("^", ""));
        nameField.setText(nameField.getText().replace("%", ""));
        nameField.setText(nameField.getText().replace("[", ""));
        nameField.setText(nameField.getText().replace("]", ""));
        nameField.setText(nameField.getText().replace("{", ""));
        nameField.setText(nameField.getText().replace("}", ""));
        nameField.setText(nameField.getText().replace("(", ""));
        nameField.setText(nameField.getText().replace(")", ""));
        nameField.setText(nameField.getText().replace("²", ""));
        nameField.setText(nameField.getText().replace("'", ""));
        nameField.setText(nameField.getText().replace("=", ""));
        nameField.setText(nameField.getText().replace("+", ""));
        nameField.setText(nameField.getText().replace("•", ""));
        nameField.setText(nameField.getText().replace("§", ""));
        nameField.setText(nameField.getText().replace("°", ""));
        nameField.setText(nameField.getText().replace("~", ""));
        nameField.setText(nameField.getText().replace("#", ""));

        if(nameField.getText().length() == 0)
        {
            JOptionPane.showMessageDialog(this, "Erreur, veuillez entrer un pseudo.", "Error", JOptionPane.ERROR_MESSAGE);
            setFieldsEnabled(true);
            return;
        }
        if(nameField.getText().length() == 1)
        {
            JOptionPane.showMessageDialog(this, "Erreur, veuillez entrer un pseudo à au moins 2 caractères.", "Error", JOptionPane.ERROR_MESSAGE);
            setFieldsEnabled(true);
            return;
        }
        if(nameField.getText().length() > 16)
        {
            JOptionPane.showMessageDialog(this, "Erreur, veuillez entrer un pseudo avec une longueur inférieur ou égale à 16.", "Error", JOptionPane.ERROR_MESSAGE);
            setFieldsEnabled(true);
            return;
        }
        Thread t = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    Launcher.auth(nameField.getText(), nameField.getText());
                }
                catch(AuthenticationException e)
                {
                    JOptionPane.showMessageDialog(LauncherPanel.this, "Erreur, impossible de se connecter : " + e.getErrorModel().getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    setFieldsEnabled(true);
                    return;
                }
                try
                {
                    FileWriter fw = new FileWriter(nameFile);
                    fw.write(nameField.getText());
                    fw.close();
                }
                catch(Exception e)
                {}
                try
                {
                    Launcher.update();
                }
                catch(Exception e)
                {
                    Launcher.interruptThread();
                    LauncherFrame.getCrashReporter().catchError(e, "Erreur, impossible de mettre Minecraft à jour !");
                    setFieldsEnabled(true);
                    return;
                }
                try
                {
                    Launcher.launch();
                }
                catch(LaunchException e)
                {
                    LauncherFrame.getCrashReporter().catchError(e, "Erreur, impossible de lancer Minecraft !");
                    setFieldsEnabled(true);
                }
                catch(Exception e)
                {}
            }
        };
        t.start();
    }

    public void keyReleased(KeyEvent e)
    {}

    public void keyTyped(KeyEvent e)
    {}
}