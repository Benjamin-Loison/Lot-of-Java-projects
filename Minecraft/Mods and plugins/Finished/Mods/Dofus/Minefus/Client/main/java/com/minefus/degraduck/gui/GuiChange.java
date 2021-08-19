package com.minefus.degraduck.gui;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

import org.lwjgl.opengl.GL11;

import com.minefus.degraduck.api.FileAPI;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;

public class GuiChange extends GuiScreen
{
    int guiWidth = 256, guiHeight = 149, error = 0, enterPassword = 0, unavailableConnection = 0;
    private GuiTextField name, password;

    @Override
    public void drawScreen(int x, int y, float ticks)
    {
        int guiX = width / 2, guiY = width / 2;
        drawDefaultBackground();
        String passwordModifier = I18n.format("password.modifier"), unavailableConnectionStr = I18n.format("unavailable.connection"), nameStr = I18n.format("name"), newPassword = I18n.format("password.new"), errorStr = I18n.format("error"), enterPasswordStr = I18n.format("enter.a.password");
        GL11.glColor4f(1, 1, 1, 1);
        if(unavailableConnection == 1)
            fontRendererObj.drawString(unavailableConnectionStr, guiX - (fontRendererObj.getStringWidth(unavailableConnectionStr) / 2), 26, 0xFE1B00);
        else if(enterPassword == 1)
            fontRendererObj.drawString(enterPasswordStr, guiX - (fontRendererObj.getStringWidth(enterPasswordStr) / 2), 26, 0xFE1B00);
        else if(error == 1)
            fontRendererObj.drawString(errorStr, guiX - (fontRendererObj.getStringWidth(errorStr) / 2), 26, 0xFE1B00);
        fontRendererObj.drawString(passwordModifier, guiX - (fontRendererObj.getStringWidth(passwordModifier) / 2), 15, 0xFFFFFF);
        fontRendererObj.drawString(nameStr, guiX - (fontRendererObj.getStringWidth(nameStr) / 2), 37, 0xFFFFFF);
        fontRendererObj.drawString(newPassword, guiX - (fontRendererObj.getStringWidth(newPassword) / 2), 76, 0xFFFFFF);
        name.drawTextBox();
        password.drawTextBox();
        super.drawScreen(x, y, ticks);
    }

    @Override
    public void initGui()
    {
        int guiX = width / 2, guiY = (height - guiHeight) / 2;
        buttonList.clear();
        buttonList.add(new GuiButton(0, guiX - 52, 115, 104, 20, I18n.format("password.modify")));
        buttonList.add(new GuiButton(1, guiX - 52, 140, 104, 20, I18n.format("menu.main")));
        name = new GuiTextField(fontRendererObj, width / 2 - 50, 50, 100, 20);
        name.setText(Minecraft.getMinecraft().getSession().getUsername());
        password = new GuiTextField(fontRendererObj, width / 2 - 50, 89, 100, 20);
        password.setMaxStringLength(30);
        password.setFocused(true);
        super.initGui();
    }

    protected void keyTyped(char c, int i)
    {
        super.keyTyped(c, i);
        password.textboxKeyTyped(c, i);
    }

    protected void mouseClicked(int x, int y, int b)
    {
        super.mouseClicked(x, y, b);
        password.mouseClicked(x, y, b);
    }

    @Override
    protected void actionPerformed(GuiButton btn)
    {
        if(btn.id == 0)
        {
            password.getText().replace(" ", "");
            if(password.getText() != "")
            {
                try
                {
                    String message = "A " + name.getText() + " " + password.getText();
                    DatagramSocket socket = new DatagramSocket();
                    DatagramPacket receivedData = new DatagramPacket(new byte[1024], 1024);
                    socket.setSoTimeout(3000);
                    socket.send(new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(I18n.format("authentification.server.ip")), FileAPI.configNumberInt("authentification.server.port")));
                    socket.receive(receivedData);
                    int good = Integer.parseInt(new String(receivedData.getData(), 0, receivedData.getLength()));
                    if(good == -1)
                        Minecraft.getMinecraft().displayGuiScreen(new GuiCustomMainMenu());
                    else if(good == 0)
                        error = 1;
                    socket.close();
                }
                catch(SocketTimeoutException ste)
                {
                    unavailableConnection = 1;
                }
                catch(Exception e)
                {}
            }
            else
                enterPassword = 1;
        }
        else if(btn.id == 1)
            Minecraft.getMinecraft().displayGuiScreen(new GuiCustomMainMenu());
    }
}