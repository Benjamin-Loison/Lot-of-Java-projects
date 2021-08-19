package fr.KFill.Launcher;

import javax.swing.JFrame;

import fr.theshark34.openlauncherlib.util.CrashReporter;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.util.WindowMover;

@SuppressWarnings("serial")
public class LauncherFrame extends JFrame
{
    public static LauncherFrame instance;
    public LauncherPanel launcherPanel;
    private static CrashReporter crashReporter;

    public LauncherFrame() throws Exception
    {
        setTitle("UnionMinecraft");
        setSize(975, 625);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setIconImage(Swinger.getResource("icon.png"));
        setContentPane(launcherPanel = new LauncherPanel());
        WindowMover mover = new WindowMover(this);
        addMouseListener(mover);
        addMouseMotionListener(mover);
        setVisible(true);
    }

    public static void main(String[] args) throws Exception
    {
        Swinger.setSystemLookNFeel();
        Swinger.setResourcePath("/fr/KFill/Launcher/ressources/");
        Launcher.LC_CRASHES_DIR.mkdirs();
        crashReporter = new CrashReporter("UnionMinecraft", Launcher.LC_CRASHES_DIR);
        instance = new LauncherFrame();
    }

    public static LauncherFrame getInstance()
    {
        return instance;
    }

    public LauncherPanel getLauncherPanel()
    {
        return launcherPanel;
    }

    public static CrashReporter getCrashReporter()
    {
        return crashReporter;
    }
}