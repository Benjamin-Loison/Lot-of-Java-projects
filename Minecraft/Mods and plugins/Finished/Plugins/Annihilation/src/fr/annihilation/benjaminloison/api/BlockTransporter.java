package fr.annihilation.benjaminloison.api;

import java.util.Iterator;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import fr.annihilation.benjaminloison.main.Annihilation;

public class BlockTransporter
{
    private Block first, second, oldFirst, oldSecond;

    public void interact(Block b, Player p)
    {
        if(!isValid(b))
        {
            p.sendMessage("Can't be set here !");
            return;
        }
        if(first == null)
        {
            oldFirst = b;
            b.setType(Material.QUARTZ_ORE);
            p.sendMessage("First teleporter set !");
            first = b;
        }
        else if(second == null)
            {
                oldSecond = b;
                b.setType(Material.QUARTZ_ORE);
                p.sendMessage("Second teleporter set !");
                second = b;
            }
        else
        {
            restore();
            p.sendMessage("Teleporter removed !");
        }
    }

    public static boolean isActivated(Block b)
    {
        Iterator<BlockTransporter> i = Annihilation.transporterBlocks.values().iterator();
        while(i.hasNext())
        {
            BlockTransporter bt = i.next();
            if(b.equals(bt.first) || b.equals(bt.second))
                if(bt.isActive())
                    return true;
                else
                    return false;
        }
        return false;
    }

    private boolean isActive()
    {
        if(second != null)
            return true;
        return false;
    }

    private void effect()
    {
        if(first == null)
            return;
        World w = first.getWorld();
        Location lF = first.getLocation(), lS = second.getLocation();
        lF.setY(lF.getY() + 1);
        lS.setY(lS.getY() + 1);
        if(second == null)
            w.playEffect(lF, Effect.SMOKE, 20);
        else
        {
            w.playEffect(lF, Effect.EXPLOSION, 20);
            w.playEffect(lS, Effect.EXPLOSION, 20);
        }
    }

    public static void effectAll()
    {
        Iterator<BlockTransporter> i = Annihilation.transporterBlocks.values().iterator();
        while(i.hasNext())
            i.next().effect();
    }

    private boolean isValid(Block b)
    {
        if(b.getType().equals(Material.ENDER_STONE) || b.getType().equals(Material.QUARTZ_ORE))
            return false;
        return true;
    }

    private void restore()
    {
        World w = oldFirst.getWorld();
        w.getBlockAt(oldFirst.getLocation()).setType(oldFirst.getType());
        w.getBlockAt(oldSecond.getLocation()).setType(oldSecond.getType());
        first = null;
        second = null;
    }
}