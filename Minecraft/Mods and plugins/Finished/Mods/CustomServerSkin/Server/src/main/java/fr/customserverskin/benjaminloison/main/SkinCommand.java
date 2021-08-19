package fr.customserverskin.benjaminloison.main;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;

public class SkinCommand implements ICommand
{
    private List aliases;
    private static final String usage = "Usage: /skin <skin> [player]";

    public SkinCommand()
    {
        aliases = new ArrayList();
        aliases.add("skin");
    }

    @Override
    public int compareTo(Object o)
    {
        return 0;
    }

    @Override
    public String getCommandName()
    {
        return "skin";
    }

    @Override
    public String getCommandUsage(ICommandSender s)
    {
        return usage;
    }

    @Override
    public List getCommandAliases()
    {
        return aliases;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] list)
    {
        EntityPlayerMP player;
        if(sender instanceof EntityPlayerMP && list.length == 1)
            player = (EntityPlayerMP)sender;
        else
        {
            if(list.length > 1)
            {
                player = (EntityPlayerMP)MinecraftServer.getServer().worldServers[0].getPlayerEntityByName(list[1]);
                if(player == null)
                    CustomServerSkin.warn("This player is not connected !");
            }
            else
            {
                CustomServerSkin.info(usage);
                return;
            }
        }
        EEP props = EEP.get(player);
        if(props != null)
        {
            if(list.length <= 1 && !(sender instanceof EntityPlayerMP))
            {
                CustomServerSkin.info(usage);
                return;
            }
            else if(list.length == 0 && sender instanceof EntityPlayerMP)
            {
                sender.addChatMessage(new ChatComponentText(usage));
                return;
            }
            props.setSkin(list[0]);
            CustomServerSkin.network.sendToAll(new PacketUpdate(player.getDisplayName(), list[0]));
        }
        else
            CustomServerSkin.warn("EEP null for: " + player.getDisplayName() + " report this to the mod author !");
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