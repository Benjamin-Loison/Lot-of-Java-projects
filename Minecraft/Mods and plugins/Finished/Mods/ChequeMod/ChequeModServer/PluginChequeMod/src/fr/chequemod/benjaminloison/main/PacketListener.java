package fr.chequemod.benjaminloison.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteStreams;

import net.minecraft.server.v1_7_R4.NBTTagCompound;

public class PacketListener implements PluginMessageListener
{
    PluginChequeMod plugin;

    public PacketListener(PluginChequeMod plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public void onPluginMessageReceived(String msg, Player p, byte[] message)
    {
        if(msg.equals(PluginChequeMod.NAME))
        {
            String[] args = ByteStreams.newDataInput(message).readUTF().split(" ");
            net.minecraft.server.v1_7_R4.ItemStack itemStack = org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack.asNMSCopy(p.getItemInHand());
            NBTTagCompound tag = itemStack.tag;
            System.out.println(msg);
            if(!(itemStack.hasTag() && tag.hasKey("amount") && tag.hasKey("to")))
            {
                System.out.println("signed");
                p.sendMessage(FileAPI.translate("cheque.signed"));
                tag = new NBTTagCompound();
                tag.setString("amount", args[0]);
                tag.setString("to", args[1]);
                itemStack.setTag(tag);
                org.bukkit.inventory.ItemStack item = org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack.asBukkitCopy(itemStack);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(FileAPI.translate("cheque.of") + FileAPI.number(Long.parseLong(args[0])) + FileAPI.translate("money.symbol"));
                item.setItemMeta(meta);
                p.setItemInHand(item);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco take " + p.getName() + " " + Long.parseLong(args[0]));
            }
            else if(tag.getString("to").equalsIgnoreCase(p.getName()))
            {
                p.sendMessage(FileAPI.translate("here.is.the.money.for.the.cheque"));
                p.setItemInHand(null);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + p.getName() + " " + tag.getString("amount"));
            }
            else
                p.sendMessage(FileAPI.translate("this.cheque.is.not.for.you"));
        }
    }
}