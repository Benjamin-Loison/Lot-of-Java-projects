package com.minefus.degraduck.gui;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import com.minefus.degraduck.common.Minefus;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.ISaveFormat;

public class GuiCustomMainMenu extends GuiScreen implements GuiYesNoCallback
{
    private static final Random rand = new Random();
    private float updateCounter;
    private GuiButton buttonResetDemo;
    private int panoramaTimer, fi0, fi1, fi2, fi3, fi4, fi5;
    private DynamicTexture viewportTexture;
    private final Object object = new Object();
    private String fi6, fi7, fi8;
    public static final String fi9 = "Please click " + EnumChatFormatting.UNDERLINE + "here" + EnumChatFormatting.RESET + " for more information.";
    int var = 200, myColor = 0xFFFFFF;
    private ResourceLocation r;
    private final ResourceLocation backGround = new ResourceLocation(Minefus.MODID, "textures/gui/Minefus.png");

    public GuiCustomMainMenu()
    {
        fi7 = fi9;
        fi6 = "";
        if((!GLContext.getCapabilities().OpenGL20) && (!OpenGlHelper.func_153193_b()))
        {
            fi6 = I18n.format("title.oldgl1");
            fi7 = I18n.format("title.oldgl2");
            fi8 = "https://help.mojang.com/customer/portal/articles/325948?ref=game";
        }
    }

    public void updateScreen()
    {
        panoramaTimer += 1;
    }

    public void initGui()
    {
        GL11.glPushMatrix();
        GL11.glScaled(0.7, 0.7, 0);
        GL11.glPopMatrix();
        viewportTexture = new DynamicTexture(256, 256);
        r = mc.getTextureManager().getDynamicTextureLocation("background", viewportTexture);
        int i = height / 4 + 48;
        addSingleplayerMultiplayerButtons(i, 24);
        buttonList.add(new GuiButton(0, width / 2 - 100, i + 72 + 12, 98, 20, I18n.format("menu.options")));
        buttonList.add(new GuiButton(2, width / 2 + 2, i + 72 + 12, 98, 20, I18n.format("menu.quit")));
        Object object = this.object;
        synchronized(object)
        {
            fi2 = fontRendererObj.getStringWidth(fi6);
            fi1 = fontRendererObj.getStringWidth(fi7);
            int j = Math.max(fi2, fi1);
            fi3 = ((width - j) / 2);
            fi4 = (((GuiButton)buttonList.get(0)).yPosition - 24);
            fi5 = (fi3 + j);
            fi0 = (fi4 + 24);
        }
    }

    private void addSingleplayerMultiplayerButtons(int x, int y)
    {
        buttonList.add(new GuiButton(3, width / 2 - 100, x, 98, 20, I18n.format("website")));
        GuiButton ts = new GuiButton(4, width / 2 - 100, x + y * 2, I18n.format("teamspeak"));
        ts.xPosition = (width / 2 + 2);
        ts.width = 98;
        buttonList.add(ts);
        GuiButton shop = new GuiButton(5, width / 2 - 100, x + y * 2, I18n.format("shop"));
        shop.width = 98;
        buttonList.add(shop);
        GuiButton forum = new GuiButton(6, width / 2 - 100, x, I18n.format("forum"));
        forum.xPosition = (width / 2 + 2);
        forum.width = 98;
        buttonList.add(forum);
        GuiButton passwordModify = new GuiButton(7, width / 2 - 40, x + y * 2 + 22, I18n.format("password.modify"));
        passwordModify.width = 80;
        passwordModify.height = 13;
        buttonList.add(passwordModify);
        buttonList.add(new GuiButton(10, width / 2 - 100, x + y * 1, EnumChatFormatting.DARK_GREEN + "Minefus"));
    }

    protected void actionPerformed(GuiButton btn)
    {
        if(btn.id == 0)
            mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
        else if(btn.id == 2)
            mc.shutdown();
        else if(btn.id > 2 && btn.id < 7)
        {
            try
            {
                Class oclass = Class.forName("java.awt.Desktop");
                Method method = oclass.getMethod("getDesktop");
                if(btn.id == 3)
                    oclass.getMethod("browse", new Class[] {URI.class}).invoke(method.invoke(null), new Object[] {new URI(I18n.format("website.url"))});
                else if(btn.id == 4)
                    oclass.getMethod("browse", new Class[] {URI.class}).invoke(method.invoke(null), new Object[] {new URI(I18n.format("teamspeak.url", mc.getSession().getUsername()))});
                else if(btn.id == 5)
                    oclass.getMethod("browse", new Class[] {URI.class}).invoke(method.invoke(null), new Object[] {new URI(I18n.format("shop.url"))});
                else if(btn.id == 5)
                    oclass.getMethod("browse", new Class[] {URI.class}).invoke(method.invoke(null), new Object[] {new URI(I18n.format("forum.url"))});
            }
            catch(Throwable t)
            {}
        }
        else if(btn.id == 7)
            Minecraft.getMinecraft().displayGuiScreen(new GuiChange());
        else if(btn.id == 10)
            FMLClientHandler.instance().connectToServerAtStartup(I18n.format("minefus.ip"), Integer.parseInt(I18n.format("minefus.port")));
    }

    public void confirmClicked(boolean b, int id)
    {
        if((b) && (id == 12))
        {
            ISaveFormat isaveformat = mc.getSaveLoader();
            isaveformat.flushCache();
            isaveformat.deleteWorldDirectory("Demo_World");
            mc.displayGuiScreen(this);
        }
        else if(id == 13)
        {
            if(b)
                try
                {
                    Class oclass = Class.forName("java.awt.Desktop");
                    oclass.getMethod("browse", new Class[] {URI.class}).invoke(oclass.getMethod("getDesktop").invoke(null), new Object[] {new URI(fi8)});
                }
                catch(Throwable t)
                {}
            mc.displayGuiScreen(this);
        }
    }

    private void renderBackGround()
    {
        GL11.glViewport(0, 0, 256, 256);
        mc.getTextureManager().bindTexture(backGround);
        GL11.glDisable(3553);
        GL11.glEnable(3553);
        GL11.glViewport(0, 0, mc.displayWidth, mc.displayHeight);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        GL11.glTexParameteri(3553, 10241, 9729);
        GL11.glTexParameteri(3553, 10240, 9729);
        tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
        int k = width, l = height;
        tessellator.addVertexWithUV(0.0D, 0.0D, zLevel, 0.0D, 0.0D);
        tessellator.addVertexWithUV(0.0D, l, zLevel, 0.0D, 1.0D);
        tessellator.addVertexWithUV(k, l, zLevel, 1.0D, 1.0D);
        tessellator.addVertexWithUV(k, 0.0D, zLevel, 1.0D, 0.0D);
        tessellator.draw();
    }

    public void drawScreen(int x, int y, float t)
    {
        GL11.glDisable(3008);
        renderBackGround();
        GL11.glEnable(3008);
        Tessellator tessellator = Tessellator.instance;
        short short1 = 274;
        int k = width / 2 - short1 / 2;
        byte b0 = 30;
        drawGradientRect(0, 0, width, height, -2130706433, 16777215);
        drawGradientRect(0, 0, width, height, 0, Integer.MIN_VALUE);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        tessellator.setColorOpaque_I(-1);
        GL11.glPushMatrix();
        GL11.glTranslatef(width / 2 + 90, 70.0F, 0.0F);
        GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
        float f1 = 1.8F - MathHelper.abs(MathHelper.sin((float)(Minecraft.getSystemTime() % 1000L) / 1000.0F * 3.1415927F * 2.0F) * 0.1F);
        GL11.glScalef(f1, f1, f1);
        GL11.glPopMatrix();
        if((fi6 != null) && (fi6.length() > 0))
        {
            drawRect(fi3 - 2, fi4 - 2, fi5 + 2, fi0 - 1, 1428160512);
            drawString(fontRendererObj, fi6, fi3, fi4, -1);
            drawString(fontRendererObj, fi7, (width - fi1) / 2, ((GuiButton)buttonList.get(0)).yPosition - 12, -1);
        }
        mc.fontRenderer.drawString(I18n.format("connected.as") + mc.getSession().getUsername(), 5, 5, 0x000000);
        super.drawScreen(x, y, t);
    }

    protected void mouseClicked(int x, int y, int z)
    {
        super.mouseClicked(x, y, z);
        synchronized(object)
        {
            if((fi6.length() > 0) && (x >= fi3) && (x <= fi5) && (y >= fi4) && (y <= fi0))
            {
                GuiConfirmOpenLink guiconfirmopenlink = new GuiConfirmOpenLink(this, fi8, 13, true);
                guiconfirmopenlink.func_146358_g();
                mc.displayGuiScreen(guiconfirmopenlink);
            }
        }
    }
}