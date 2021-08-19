package fr.KFill.Launcher;

import java.awt.Color;
import java.awt.Graphics;
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
public class LauncherPanel extends JPanel implements SwingerEventListener
{
    private Saver saver = new Saver(new File(Launcher.LC_DIR, "launcher.properties"));
    private JTextField usernameField = new JTextField(saver.get("prenom")), nomField = new JTextField(saver.get("nom"));
    String pseudo = "", nom1 = Launcher.LC_INFOS.getGameDir() + "\\" + "nom" + ".txt", nom2 = Launcher.LC_INFOS.getGameDir() + "\\" + "username" + ".txt", ligne = "";
    private STexturedButton playButton = new STexturedButton(Swinger.getResource("play.png")), quitButton = new STexturedButton(Swinger.getResource("quit.png")), hideButton = new STexturedButton(Swinger.getResource("hide.png"));
    private SColoredBar progressBar = new SColoredBar(new Color(0, 255, 0, 70), new Color(255, 0, 0, 70));
    private JLabel infoLabel = new JLabel("Clique sur jouer !", SwingConstants.CENTER);

    public LauncherPanel() throws Exception
    {
        setLayout(null);
        File name1 = new File(nom1), name2 = new File(nom2);
        if(name1.exists())
        {
            Scanner sc = new Scanner(name1);
            while(sc.hasNextLine())
                nomField.setText(sc.nextLine());
            sc.close();
        }
        else
            try
            {
                FileWriter fw = new FileWriter(name1);
                fw.write("");
                fw.close();
            }
            catch(Exception e)
            {}
        if(name2.exists())
        {
            Scanner sc = new Scanner(new File(nom2));
            while(sc.hasNextLine())
                usernameField.setText(sc.nextLine());
            sc.close();
        }
        else
            try
            {
                FileWriter fw = new FileWriter(name1);
                fw.write("");
                fw.close();
            }
            catch(Exception e)
            {}

        usernameField.setForeground(Color.WHITE);
        usernameField.setCaretColor(Color.WHITE);
        usernameField.setFont(usernameField.getFont().deriveFont(25F));
        usernameField.setOpaque(false);
        usernameField.setBorder(null);
        usernameField.setBounds(640, 90, 300, 42);
        add(usernameField);

        nomField.setForeground(Color.WHITE);
        nomField.setCaretColor(Color.WHITE);
        nomField.setFont(nomField.getFont().deriveFont(25F));
        nomField.setOpaque(false);
        nomField.setBorder(null);
        nomField.setBounds(640, 200, 300, 42);
        add(nomField);

        playButton.setBounds(640, 250, 300, 42);
        playButton.addEventListener(this);
        add(playButton);

        quitButton.setBounds(920, 2, 25, 25);
        quitButton.addEventListener(this);
        add(quitButton);

        hideButton.setBounds(900, 2, 25, 25);
        hideButton.addEventListener(this);
        add(hideButton);

        progressBar.setBounds(11, 592, 953, 20);
        add(progressBar);

        infoLabel.setForeground(Color.blue);
        infoLabel.setBounds(11, 592, 953, 20);
        add(infoLabel);
    }

    public void onEvent(SwingerEvent e)
    {
        if(e.getSource() == playButton)
        {
            setFieldsEnabled(false);
            if(usernameField.getText().startsWith("-") || usernameField.getText().endsWith("-"))
            {
                JOptionPane.showMessageDialog(this, "Erreur, veuillez ne pas mettre de - au début ou/et à la fin de votre prénom.", "Erreur", JOptionPane.ERROR_MESSAGE);
                setFieldsEnabled(true);
                return;
            }
            usernameField.setText(usernameField.getText().replace(" ", ""));
            usernameField.setText(usernameField.getText().replace("1", ""));
            usernameField.setText(usernameField.getText().replace("2", ""));
            usernameField.setText(usernameField.getText().replace("3", ""));
            usernameField.setText(usernameField.getText().replace("4", ""));
            usernameField.setText(usernameField.getText().replace("5", ""));
            usernameField.setText(usernameField.getText().replace("6", ""));
            usernameField.setText(usernameField.getText().replace("7", ""));
            usernameField.setText(usernameField.getText().replace("8", ""));
            usernameField.setText(usernameField.getText().replace("9", ""));
            usernameField.setText(usernameField.getText().replace("0", ""));
            usernameField.setText(usernameField.getText().replace(".", ""));
            usernameField.setText(usernameField.getText().replace("_", ""));
            usernameField.setText(usernameField.getText().replace(",", ""));
            usernameField.setText(usernameField.getText().replace("@", ""));
            usernameField.setText(usernameField.getText().replace("â", "a"));
            usernameField.setText(usernameField.getText().replace("à", "a"));
            usernameField.setText(usernameField.getText().replace("ä", "a"));
            usernameField.setText(usernameField.getText().replace("é", "e"));
            usernameField.setText(usernameField.getText().replace("è", "e"));
            usernameField.setText(usernameField.getText().replace("ê", "e"));
            usernameField.setText(usernameField.getText().replace("ë", "e"));
            usernameField.setText(usernameField.getText().replace("ô", "o"));
            usernameField.setText(usernameField.getText().replace("ö", "o"));
            usernameField.setText(usernameField.getText().replace("î", "i"));
            usernameField.setText(usernameField.getText().replace("ï", "i"));
            usernameField.setText(usernameField.getText().replace("<", ""));
            usernameField.setText(usernameField.getText().replace(">", ""));
            usernameField.setText(usernameField.getText().replace("!", ""));
            usernameField.setText(usernameField.getText().replace("?", ""));
            usernameField.setText(usernameField.getText().replace("*", ""));
            usernameField.setText(usernameField.getText().replace("€", ""));
            usernameField.setText(usernameField.getText().replace("$", ""));
            usernameField.setText(usernameField.getText().replace("£", ""));
            usernameField.setText(usernameField.getText().replace(":", ""));
            usernameField.setText(usernameField.getText().replace("/", ""));
            usernameField.setText(usernameField.getText().replace("\\\\", ""));
            usernameField.setText(usernameField.getText().replace("^", ""));
            usernameField.setText(usernameField.getText().replace("%", ""));
            usernameField.setText(usernameField.getText().replace("[", ""));
            usernameField.setText(usernameField.getText().replace("]", ""));
            usernameField.setText(usernameField.getText().replace("{", ""));
            usernameField.setText(usernameField.getText().replace("}", ""));
            usernameField.setText(usernameField.getText().replace("(", ""));
            usernameField.setText(usernameField.getText().replace(")", ""));
            usernameField.setText(usernameField.getText().replace("²", ""));
            usernameField.setText(usernameField.getText().replace("-", ""));
            usernameField.setText(usernameField.getText().replace("'", ""));
            usernameField.setText(usernameField.getText().replace("=", ""));
            usernameField.setText(usernameField.getText().replace("+", ""));
            usernameField.setText(usernameField.getText().replace("•", ""));
            usernameField.setText(usernameField.getText().replace("§", ""));
            usernameField.setText(usernameField.getText().replace("°", ""));
            usernameField.setText(usernameField.getText().replace("~", ""));
            usernameField.setText(usernameField.getText().replace("#", ""));
            usernameField.setText(usernameField.getText().toLowerCase());
            usernameField.setText(usernameField.getText().replaceFirst(".", (usernameField.getText().charAt(0) + "").toUpperCase()));
            if(nomField.getText().startsWith("-") || nomField.getText().endsWith("-"))
            {
                JOptionPane.showMessageDialog(this, "Erreur, veuillez ne pas mettre de - au début ou/et à la fin de votre nom.", "Erreur", JOptionPane.ERROR_MESSAGE);
                setFieldsEnabled(true);
                return;
            }
            nomField.setText(nomField.getText().replace(" ", ""));
            nomField.setText(nomField.getText().replace("1", ""));
            nomField.setText(nomField.getText().replace("2", ""));
            nomField.setText(nomField.getText().replace("3", ""));
            nomField.setText(nomField.getText().replace("4", ""));
            nomField.setText(nomField.getText().replace("5", ""));
            nomField.setText(nomField.getText().replace("6", ""));
            nomField.setText(nomField.getText().replace("7", ""));
            nomField.setText(nomField.getText().replace("8", ""));
            nomField.setText(nomField.getText().replace("9", ""));
            nomField.setText(nomField.getText().replace("0", ""));
            nomField.setText(nomField.getText().replace(".", ""));
            nomField.setText(nomField.getText().replace("_", ""));
            nomField.setText(nomField.getText().replace(",", ""));
            nomField.setText(nomField.getText().replace("@", ""));
            nomField.setText(nomField.getText().replace("â", "a"));
            nomField.setText(nomField.getText().replace("à", "a"));
            nomField.setText(nomField.getText().replace("ä", "a"));
            nomField.setText(nomField.getText().replace("é", "e"));
            nomField.setText(nomField.getText().replace("è", "e"));
            nomField.setText(nomField.getText().replace("ê", "e"));
            nomField.setText(nomField.getText().replace("ë", "e"));
            nomField.setText(nomField.getText().replace("ô", "o"));
            nomField.setText(nomField.getText().replace("ö", "o"));
            nomField.setText(nomField.getText().replace("î", "i"));
            nomField.setText(nomField.getText().replace("ï", "i"));
            nomField.setText(nomField.getText().replace("<", ""));
            nomField.setText(nomField.getText().replace(">", ""));
            nomField.setText(nomField.getText().replace("!", ""));
            nomField.setText(nomField.getText().replace("?", ""));
            nomField.setText(nomField.getText().replace("*", ""));
            nomField.setText(nomField.getText().replace("€", ""));
            nomField.setText(nomField.getText().replace("$", ""));
            nomField.setText(nomField.getText().replace("£", ""));
            nomField.setText(nomField.getText().replace(":", ""));
            nomField.setText(nomField.getText().replace("/", ""));
            nomField.setText(nomField.getText().replace("\\\\", ""));
            nomField.setText(nomField.getText().replace("ç", ""));
            nomField.setText(nomField.getText().replace("%", ""));
            nomField.setText(nomField.getText().replace("[", ""));
            nomField.setText(nomField.getText().replace("]", ""));
            nomField.setText(nomField.getText().replace("{", ""));
            nomField.setText(nomField.getText().replace("}", ""));
            nomField.setText(nomField.getText().replace("(", ""));
            nomField.setText(nomField.getText().replace(")", ""));
            nomField.setText(nomField.getText().replace("²", ""));
            nomField.setText(nomField.getText().replace("-", ""));
            nomField.setText(nomField.getText().replace("'", ""));
            nomField.setText(nomField.getText().replace("=", ""));
            nomField.setText(nomField.getText().replace("+", ""));
            nomField.setText(nomField.getText().replace("•", ""));
            nomField.setText(nomField.getText().replace("§", ""));
            nomField.setText(nomField.getText().replace("°", ""));
            nomField.setText(nomField.getText().replace("~", ""));
            nomField.setText(nomField.getText().replace("#", ""));
            nomField.setText(nomField.getText().toLowerCase());
            nomField.setText(nomField.getText().replaceFirst(".", (nomField.getText().charAt(0) + "").toUpperCase()));

            pseudo = usernameField.getText() + "_" + nomField.getText();

            if(usernameField.getText().length() == 0)
            {
                JOptionPane.showMessageDialog(this, "Erreur, veuillez entrer un prénom.", "Erreur", JOptionPane.ERROR_MESSAGE);
                setFieldsEnabled(true);
                return;
            }
            if(usernameField.getText().length() == 1)
            {
                JOptionPane.showMessageDialog(this, "Erreur, veuillez entrer un prénom à au moins 2 caractères.", "Erreur", JOptionPane.ERROR_MESSAGE);
                setFieldsEnabled(true);
                return;
            }
            if(nomField.getText().length() == 0)
            {
                JOptionPane.showMessageDialog(this, "Erreur, veuillez entrer un nom.", "Erreur", JOptionPane.ERROR_MESSAGE);
                setFieldsEnabled(true);
                return;
            }
            if(nomField.getText().length() == 1)
            {
                JOptionPane.showMessageDialog(this, "Erreur, veuillez entrer un nom à au moins 2 caractères.", "Erreur", JOptionPane.ERROR_MESSAGE);
                setFieldsEnabled(true);
                return;
            }
            if(pseudo.length() > 16)
            {
                JOptionPane.showMessageDialog(this, "Erreur, veuillez entrer un nom et prénom avec une longueur inférieur ou égale à 16.", "Erreur", JOptionPane.ERROR_MESSAGE);
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
                        Launcher.auth(pseudo, pseudo);
                    }
                    catch(AuthenticationException e)
                    {
                        JOptionPane.showMessageDialog(LauncherPanel.this, "Erreur, impossible de se connecter : " + e.getErrorModel().getErrorMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                        setFieldsEnabled(true);
                        return;
                    }
                    try
                    {
                        FileWriter fw = new FileWriter(new File(nom1));
                        fw.write(nomField.getText());
                        fw.close();
                    }
                    catch(Exception e)
                    {}
                    try
                    {
                        FileWriter fw = new FileWriter(new File(nom2));
                        fw.write(usernameField.getText());
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
        usernameField.setEnabled(enabled);
        nomField.setEnabled(enabled);
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
}