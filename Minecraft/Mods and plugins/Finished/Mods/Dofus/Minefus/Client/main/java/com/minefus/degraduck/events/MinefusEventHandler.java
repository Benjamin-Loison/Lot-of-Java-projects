package com.minefus.degraduck.events;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;

import com.minefus.degraduck.api.FileAPI;
import com.minefus.degraduck.entity.ExtendedPlayer;
import com.minefus.degraduck.gui.GuiConnection;
import com.minefus.degraduck.gui.GuiCustomMainMenu;
import com.minefus.degraduck.gui.GuiRegistration;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.EntityEvent;

public class MinefusEventHandler
{
    @SubscribeEvent
    public void GuiScreenEvent(GuiOpenEvent event)
    {
        if(((event.gui instanceof GuiInventory)) && (!Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode))
            event.setCanceled(true);
        /*else if(event.gui instanceof GuiMainMenu)
            try
            {
                int bon = 0;
                String message = "C " + Minecraft.getMinecraft().getSession().getUsername();
                DatagramSocket socket = new DatagramSocket();
                DatagramPacket donneesRecues = new DatagramPacket(new byte[1024], 1024);
                socket.setSoTimeout(5000);
                socket.send(new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName("crise.altiscraft.fr"), FileAPI.configNumberInt("authentification.server.port")));
                socket.receive(donneesRecues);
                socket.close();
                bon = Integer.parseInt(new String(donneesRecues.getData(), 0, donneesRecues.getLength()));
                if(bon == 0)
                {
                    String ip = "ts.altiscraft.fr";
                    BufferedReader in = new BufferedReader(new InputStreamReader(new URL("http://195.154.194.190/Auth.txt").openStream()));
                    String inputLine;
                    while((inputLine = in.readLine()) != null)
                        ip = inputLine;
                    in.close();
                    message = "C " + Minecraft.getMinecraft().getSession().getUsername();
                    socket = new DatagramSocket();
                    donneesRecues = new DatagramPacket(new byte[1024], 1024);
                    socket.setSoTimeout(5000);
                    socket.send(new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(ip), FileAPI.configNumberInt("authentification.server.port")));
                    socket.receive(donneesRecues);
                    socket.close();
                    bon = Integer.parseInt(new String(donneesRecues.getData(), 0, donneesRecues.getLength()));
                    if(bon == 0)
                        Minecraft.getMinecraft().shutdown();
                    else if(bon == 1)
                        event.gui = new GuiCustomMainMenu();
                    else if(bon == 2)
                        event.gui = new GuiRegistration();
                    else
                        event.gui = new GuiConnection();
                }
                else if(bon == 1)
                    event.gui = new GuiCustomMainMenu();
                else if(bon == 2)
                    event.gui = new GuiRegistration();
                else
                    event.gui = new GuiConnection();
            }
            catch(Exception e)
            {}*/
    }

    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event)
    {
        if(((event.entity instanceof EntityPlayer)) && (ExtendedPlayer.get((EntityPlayer)event.entity) == null))
            ExtendedPlayer.register((EntityPlayer)event.entity);
        if(((event.entity instanceof EntityPlayer)) && (event.entity.getExtendedProperties(ExtendedPlayer.EXT_PROP_MINEFUSINV) == null))
            event.entity.registerExtendedProperties(ExtendedPlayer.EXT_PROP_MINEFUSINV, new ExtendedPlayer((EntityPlayer)event.entity));
    }
}