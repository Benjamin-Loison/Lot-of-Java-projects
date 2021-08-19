package fr.watchdogs.benjaminloison.commands;

import java.util.ArrayList;
import java.util.List;

import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.entity.EEP;
import fr.watchdogs.benjaminloison.packets.PacketMessage;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;

public class Grade implements ICommand
{
    private List aliases;

    public Grade()
    {
        aliases = new ArrayList();
        aliases.add("grade");
    }

    @Override
    public int compareTo(Object obj)
    {
        return 0;
    }

    @Override
    public String getCommandName()
    {
        return "grade";
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "Usage: /grade <player> <set|is> <grade> [yes/no]";
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
        if(sender instanceof EntityPlayerMP)
        {
            theSender = (EntityPlayerMP)sender;
            if(player == null)
            {
                theSender.addChatComponentMessage(new ChatComponentText(FileAPI.config("player.not.found")));
                return;
            }
            EEP props = EEP.get(player);
            if(list[1].equalsIgnoreCase("set"))
                if(list[3].equalsIgnoreCase("yes"))
                {
                    props.set(list[2], true);
                    if(player != theSender)
                        AltisCraft.instance.network.sendTo(new PacketMessage("the.player.is.now", list[0], list[2]), theSender);
                    AltisCraft.instance.network.sendTo(new PacketMessage("the.player.is.now", list[0], list[2]), player);
                }
                else
                {
                    props.set(list[2], false);
                    if(player != theSender)
                        AltisCraft.instance.network.sendTo(new PacketMessage("the.player.is.no.longer", list[0], list[2]), theSender);
                    AltisCraft.instance.network.sendTo(new PacketMessage("the.player.is.no.longer", list[0], list[2]), player);
                }
            else if(list[1].equalsIgnoreCase("is"))
                if(props.get(list[2]))
                {
                    if(player != theSender)
                        AltisCraft.instance.network.sendTo(new PacketMessage("the.player.is", list[0], list[2]), theSender);
                    AltisCraft.instance.network.sendTo(new PacketMessage("the.player.is", list[0], list[2]), player);
                }
                else
                {
                    if(player != theSender)
                        AltisCraft.instance.network.sendTo(new PacketMessage("the.player.is.not", list[0], list[2]), theSender);
                    AltisCraft.instance.network.sendTo(new PacketMessage("the.player.is.not", list[0], list[2]), player);
                }
        }
        else
        {
            if(player == null)
            {
                AltisCraft.print(FileAPI.config("player.not.found"));
                return;
            }
            EEP props = EEP.get(player);
            if(list[1].equalsIgnoreCase("set"))
                if(list[3].equalsIgnoreCase("yes"))
                {
                    props.set(list[2], true);
                    AltisCraft.instance.network.sendTo(new PacketMessage("the.player.is.now", list[0], list[2]), player);
                }
                else
                {
                    props.set(list[2], false);
                    AltisCraft.instance.network.sendTo(new PacketMessage("the.player.is.no.longer", list[0], list[2]), player);
                }
            else if(list[1].equalsIgnoreCase("is"))
                if(props.get(list[2]))
                    AltisCraft.instance.network.sendTo(new PacketMessage("the.player.is", list[0], list[2]), player);
                else
                    AltisCraft.instance.network.sendTo(new PacketMessage("the.player.is.not", list[0], list[2]), player);
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

    public static boolean doesStringStartWith(String s1, String s2)
    {
        return s2.regionMatches(true, 0, s1, 0, s1.length());
    }

    @Override
    public boolean isUsernameIndex(String[] list, int x)
    {
        return x == 0;
    }
}