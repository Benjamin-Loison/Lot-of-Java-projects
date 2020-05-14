package fr.pnjmarket.benjaminloison.common;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;

public class GuiPNJ extends GuiScreen
{
    int guiWidth = 176;
    int guiHeight = 222;
    private GuiTextField texture, action;
    private EntityMarketPNJ pnj;

    public GuiPNJ(EntityMarketPNJ entityMarketPNJ)
    {
        pnj = entityMarketPNJ;
    }

    public void drawScreen(int x, int y, float tick)
    {
        int guiX = (width - guiWidth) / 2;
        int guiY = (height - guiHeight) / 2;
        GL11.glColor4f(1F, 1F, 1F, 1F);
        mc.renderEngine.bindTexture(new ResourceLocation(ModPNJMarket.MODID, "textures/gui/generic.png"));
        drawTexturedModalRect(guiX + 25, guiY, 0, 0, guiWidth, guiHeight);
        texture.drawTextBox();
        action.drawTextBox();
        super.drawScreen(x, y, tick);
    }

    @Override
    public void initGui()
    {
        int guiX = (width - guiWidth) / 2;
        int guiY = (height - guiHeight) / 2;
        buttonList.clear();
        buttonList.add(new GuiButton(1, width / 2 - 52, guiY + 30, 100, 20, "Changer Texture"));
        texture = new GuiTextField(fontRendererObj, width / 2 - 51, guiY + 13, 98, 15);
        buttonList.add(new GuiButton(2, width / 2 - 52, guiY + 77, 100, 20, "Changer Action"));
        action = new GuiTextField(fontRendererObj, width / 2 - 51, guiY + 107, 98, 15);
        buttonList.add(new GuiButton(3, width / 2 - 52, guiY + 167, 100, 20, "Supprimer"));
        texture.setMaxStringLength(50);
        texture.setText("");
        action.setMaxStringLength(50);
        action.setText("");
        super.initGui();
    }

    protected void keyTyped(char par1, int par2)
    {
        super.keyTyped(par1, par2);
        texture.textboxKeyTyped(par1, par2);
        action.textboxKeyTyped(par1, par2);
    }

    protected void mouseClicked(int x, int y, int btn)
    {
        super.mouseClicked(x, y, btn);
        texture.mouseClicked(x, y, btn);
        action.mouseClicked(x, y, btn);
    }

    @Override
    protected void actionPerformed(GuiButton btn)
    {
        switch(btn.id)
        {
            case 1:
                if(pnj != null)
                {
                    if(!texture.getText().equals(""))
                    {
                        EEPPNJ prop = EEPPNJ.get(pnj);
                        pnj.getDataWatcher().updateObject(20, texture.getText());
                        prop.getDownloadImageHat(prop.getLocationHat(texture.getText()), texture.getText());
                        ModPNJMarket.instance.network.sendToServer(new PacketSyncDW(texture.getText(), pnj.getEntityId(), 20));
                        Minecraft.getMinecraft().displayGuiScreen(null);
                    }
                }
                break;
            case 2:
                if(pnj != null)
                {
                    if(!action.getText().equals(""))
                    {
                        pnj.getDataWatcher().updateObject(21, action.getText());
                        ModPNJMarket.instance.network.sendToServer(new PacketSyncDW(action.getText(), pnj.getEntityId(), 21));
                        Minecraft.getMinecraft().displayGuiScreen(null);
                    }
                }
                break;
            case 3:
                if(pnj != null)
                {
                    ModPNJMarket.instance.network.sendToServer(new PacketKillNPC(pnj.getEntityId()));
                    Minecraft.getMinecraft().displayGuiScreen(null);
                }
                break;
        }
    }
}
