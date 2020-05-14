package fr.watchdogs.benjaminloison.commands;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.entity.EEP;
import fr.watchdogs.benjaminloison.packets.PacketMessage;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class Money implements ICommand
{
    private List aliases;

    public Money()
    {
        aliases = new ArrayList();
        aliases.add("money");
    }

    @Override
    public int compareTo(Object obj)
    {
        return 0;
    }

    @Override
    public String getCommandName()
    {
        return "money";
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "Usage: /money <player> <add|set> <amount>";
    }

    @Override
    public List getCommandAliases()
    {
        return aliases;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] list)
    {
        EntityPlayerMP theSender = null, player = (EntityPlayerMP)MinecraftServer.getServer().worldServers[0].getPlayerEntityByName(list[0]);
        if(!(sender instanceof EntityPlayerMP))
        {
            if(player == null)
            {
                AltisCraft.print(FileAPI.config("player.not.found"));
                return;
            }
            EEP.get(player).addMoney(Long.parseLong(list[1]));
            AltisCraft.print(FileAPI.config("money.has.been.given"));
            return;
        }
        theSender = (EntityPlayerMP)sender;
        if(player == null)
        {
            AltisCraft.instance.network.sendTo(new PacketMessage("player.not.found"), theSender);
            return;
        }
        EEP props = EEP.get(player);
        if(list.length == 1)
            AltisCraft.instance.network.sendTo(new PacketMessage("command.money", FileAPI.number(props.money)), theSender);
        else if(list.length == 3)
        {
            if(!StringUtils.isNumeric(list[2]))
                return;
            long i = Long.parseLong(list[2]);
            if(list[1].equalsIgnoreCase("add"))
            {
                if(theSender != player)
                    AltisCraft.instance.network.sendTo(new PacketMessage("the.player.has.received", player.getDisplayName(), FileAPI.number(i)), theSender);
                AltisCraft.instance.network.sendTo(new PacketMessage("you.have.received", FileAPI.number(i)), player);
                props.addMoney(i);
            }
            else if(list[1].equalsIgnoreCase("set"))
            {
                if(theSender != player)
                    AltisCraft.instance.network.sendTo(new PacketMessage("the.player.has.his.money.to", player.getDisplayName(), FileAPI.number(i)), theSender);
                AltisCraft.instance.network.sendTo(new PacketMessage("you.have.your.money.set.to", FileAPI.number(i)), player);
                props.money = i;
                props.sync();
            }
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] list)
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