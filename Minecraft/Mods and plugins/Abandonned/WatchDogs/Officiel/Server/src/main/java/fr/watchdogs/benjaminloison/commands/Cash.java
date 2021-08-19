package fr.watchdogs.benjaminloison.commands;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.entity.EEP;
import fr.watchdogs.benjaminloison.packets.PacketMessage;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

public class Cash implements ICommand
{
    private List aliases;

    public Cash()
    {
        aliases = new ArrayList();
        aliases.add("cash");
    }

    @Override
    public int compareTo(Object o)
    {
        return 0;
    }

    @Override
    public String getCommandName()
    {
        return "cash";
    }

    @Override
    public String getCommandUsage(ICommandSender s)
    {
        return "Usage: /cash <player> <add|set|buy> <amount> [item]";
    }

    @Override
    public List getCommandAliases()
    {
        return aliases;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] list)
    {
        EntityPlayerMP theSender = (EntityPlayerMP)sender, player = (EntityPlayerMP)MinecraftServer.getServer().worldServers[0].getPlayerEntityByName(list[0]);
        if(player == null)
        {
            AltisCraft.instance.network.sendTo(new PacketMessage("player.not.found"), theSender);
            return;
        }
        EEP props = EEP.get(player);
        if(list.length == 1)
            AltisCraft.instance.network.sendTo(new PacketMessage("command.cash", FileAPI.number(props.cash)), theSender);
        else if(list.length >= 3)
        {
            if(!StringUtils.isNumeric(list[2]))
                return;
            long amount = Long.parseLong(list[2]);
            if(list[1].equalsIgnoreCase("add"))
            {
                if(theSender != player)
                    AltisCraft.instance.network.sendTo(new PacketMessage("the.player.has.received", FileAPI.number(amount)), theSender);
                AltisCraft.instance.network.sendTo(new PacketMessage("you.have.earned", FileAPI.number(amount)), player);
                props.addCash(amount);
            }
            else if(list[1].equalsIgnoreCase("set"))
            {
                if(theSender != player)
                    AltisCraft.instance.network.sendTo(new PacketMessage("the.player.has.been.set.at", FileAPI.number(amount)), theSender);
                AltisCraft.instance.network.sendTo(new PacketMessage("the.player.has.been.set.at", player.getDisplayName(), FileAPI.number(amount)), player);
                props.cash = amount;
                props.sync();
            }
            else if(list[1].equalsIgnoreCase("del"))
            {
                if(theSender != player)
                    AltisCraft.instance.network.sendTo(new PacketMessage("the.player.has.lost", FileAPI.number(amount)), theSender);
                AltisCraft.instance.network.sendTo(new PacketMessage("you.have.lost", FileAPI.number(amount)), player);
                props.delCash(amount);
            }
            else if(list[1].equalsIgnoreCase("buy") && list.length == 4)
            {
                String parts[] = list[3].split(":");
                if(parts[0] != null || parts[1] != null)
                    if(GameRegistry.findItem(parts[0], parts[1]) != null)
                    {
                        Item item = GameRegistry.findItem(parts[0], parts[1]);
                        if(props.buyCashTest(amount, new ItemStack(item)).equals("not.enough.money"))
                            props.creditCash(new ItemStack(item), amount);
                        else if(props.buyCashTest(amount, new ItemStack(item)).equals("not.enough.space"))
                            AltisCraft.instance.network.sendTo(new PacketMessage("not.enough.space"), theSender);
                        else
                            props.buyCash(amount, new ItemStack(item));
                    }
            }
            else if(list[1].equalsIgnoreCase("license") && list.length == 4)
            {
                props.buyLicense(amount, list[3]);
                try
                {
                    Scanner scan = new Scanner(new File(FileAPI.bankFile()));
                    String bankMoney = "" + Long.parseLong(scan.nextLine()) + amount;
                    scan.close();
                    FileWriter fw = new FileWriter(FileAPI.bankFile());
                    fw.write(bankMoney);
                    fw.close();
                }
                catch(Exception e)
                {}
            }
            else if(list[1].equalsIgnoreCase("dye") && list.length == 4)
                props.buyDye(Long.parseLong(list[2]), Integer.parseInt(list[3]));
            else if(list[1].equalsIgnoreCase("buygang"))
                if(!props.gang.equals(""))
                {
                    String parts[] = list[3].split(":");
                    if(parts[0] != null || parts[1] != null)
                        if(GameRegistry.findItem(parts[0], parts[1]) != null)
                            if(props.getMoneyGang() >= amount)
                                props.buyMoneyGang(amount, new ItemStack(GameRegistry.findItem(parts[0], parts[1])), theSender);
                            else
                                AltisCraft.instance.network.sendTo(new PacketMessage("you.have.not.enough.money"), theSender);
                }
                else
                    AltisCraft.instance.network.sendTo(new PacketMessage("you.are.not.in.a.gang"), theSender);
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender s)
    {
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender s, String[] list)
    {
        return list.length == 1 ? getListOfStringsMatchingLastWord(list, getAllUsernames()) : null;
    }

    protected String[] getAllUsernames()
    {
        return MinecraftServer.getServer().getAllUsernames();
    }

    public static List getListOfStringsMatchingLastWord(String[] str1, String... str2)
    {
        ArrayList arraylist = new ArrayList();
        for(int j = 0; j < str2.length; ++j)
            if(doesStringStartWith(str1[str1.length - 1], str2[j]))
                arraylist.add(str2[j]);
        return arraylist;
    }

    public static boolean doesStringStartWith(String str1, String str2)
    {
        return str2.regionMatches(true, 0, str1, 0, str1.length());
    }

    @Override
    public boolean isUsernameIndex(String[] list, int x)
    {
        return x == 0;
    }
}