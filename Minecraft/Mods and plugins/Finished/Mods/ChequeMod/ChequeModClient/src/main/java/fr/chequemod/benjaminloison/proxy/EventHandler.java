package fr.chequemod.benjaminloison.proxy;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.chequemod.benjaminloison.common.NumberAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

public class EventHandler
{
    public boolean isWaiting = false;
    private long amount;
    private String to;

    @SubscribeEvent
    public void chat(ClientChatReceivedEvent event)
    {
        if(isWaiting)
        {
            String message = event.message.getUnformattedText();
            if(message.startsWith(I18n.format("beginning.of.display.money.message")))
            {
                EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
                isWaiting = false;
                long playerMoney = Long.parseLong(message.replace(I18n.format("beginning.of.display.money.message"), ""));
                event.setCanceled(true);
                if(playerMoney >= amount)
                {
                    ByteArrayDataOutput o = ByteStreams.newDataOutput();
                    o.writeUTF(amount + " " + to);
                    Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C17PacketCustomPayload("ChequeMod", o.toByteArray()));
                }
                else
                    player.addChatMessage(new ChatComponentText(I18n.format("you.have.not.enough.money")));
            }
        }
    }

    public void enoughMoney(long amount, String to)
    {
        Minecraft.getMinecraft().thePlayer.sendChatMessage(I18n.format("command.money"));
        this.amount = amount;
        this.to = to;
        isWaiting = true;
    }
}