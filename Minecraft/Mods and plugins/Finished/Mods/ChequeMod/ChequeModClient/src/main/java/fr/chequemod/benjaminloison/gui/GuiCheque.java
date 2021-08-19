package fr.chequemod.benjaminloison.gui;

import org.apache.commons.lang3.StringUtils;
import org.lwjgl.opengl.GL11;

import fr.chequemod.benjaminloison.common.ChequeMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Constants;

public class GuiCheque extends GuiScreen
{
    int guiWidth = 256, guiHeight = 256;
    private GuiTextField amount, to;
    private EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
    private NBTTagCompound nbt = player.getHeldItem().stackTagCompound;
    private boolean signed = false;

    public GuiCheque()
    {
        if(nbt != null && nbt.hasKey("to", Constants.NBT.TAG_STRING) && nbt.hasKey("amount", Constants.NBT.TAG_STRING))
            signed = true;
    }

    @Override
    public void drawScreen(int x, int y, float ticks)
    {
        int guiX = (width - guiWidth) / 2, guiY = (height - guiHeight) / 2;
        GL11.glColor4f(1, 1, 1, 1);
        mc.renderEngine.bindTexture(new ResourceLocation(ChequeMod.MODID, "textures/gui/Cheque.png"));
        drawTexturedModalRect(guiX, guiY, 0, 0, guiWidth, guiHeight);
        fontRendererObj.drawString(I18n.format("cheque.bank.name"), guiX + 140, guiY + 140, 0xFFFFFF);
        fontRendererObj.drawString(I18n.format("cheque.bank.address.number.and.road"), guiX + 140, guiY + 148, 0xFFFFFF);
        fontRendererObj.drawString(I18n.format("cheque.bank.address.zip.code.and.city"), guiX + 140, guiY + 156, 0xFFFFFF);
        fontRendererObj.drawString(I18n.format("cheque.account", player.getEntityId()), guiX + 1, guiY + 132, 0xFFFFFF);
        fontRendererObj.drawString(I18n.format("cheque.mister", player.getDisplayName()), guiX + 1, guiY + 140, 0xFFFFFF);
        fontRendererObj.drawString(I18n.format("cheque.mister.address.number.and.road"), guiX + 1, guiY + 148, 0xFFFFFF);
        fontRendererObj.drawString(I18n.format("cheque.mister.address.zip.code.and.city"), guiX + 1, guiY + 156, 0xFFFFFF);
        fontRendererObj.drawString(I18n.format("cheque.mister.id", player.getUniqueID()), guiX + 1, guiY + 168, 0xFFFFFF);
        fontRendererObj.drawString(I18n.format("for"), guiX + 1, guiY + 110, 0xFFFFFF);
        fontRendererObj.drawString(I18n.format("money.symbol"), guiX + 246, guiY + 111, 0xFFFFFF);
        amount.drawTextBox();
        to.drawTextBox();
        super.drawScreen(x, y, ticks);
    }

    @Override
    public void initGui()
    {
        int guiX = (width - guiWidth) / 2, guiY = (height - guiHeight) / 2;
        buttonList.clear();
        amount = new GuiTextField(fontRendererObj, width / 2 + 67, guiY + 111, 58, 15);
        if(!signed)
            amount.setText(I18n.format("amount"));
        else
            amount.setText(nbt.getString("amount"));
        amount.setMaxStringLength(Integer.parseInt(I18n.format("amount.length")));
        amount.setTextColor(Integer.parseInt(I18n.format("fields.color"), 16));
        amount.setEnableBackgroundDrawing(false);
        to = new GuiTextField(fontRendererObj, width / 2 - 100, guiY + 110, 154, 12);
        if(!signed)
            to.setText(I18n.format("receiver"));
        else
            to.setText(nbt.getString("to"));
        to.setMaxStringLength(23);
        to.setTextColor(Integer.parseInt(I18n.format("fields.color"), 16));
        to.setEnableBackgroundDrawing(false);
        super.initGui();
    }

    protected void keyTyped(char c, int i)
    {
        super.keyTyped(c, i);
        if(!signed)
        {
            amount.textboxKeyTyped(c, i);
            to.textboxKeyTyped(c, i);
            if(i == 28)
                if(StringUtils.isNumeric(amount.getText()) && Long.parseLong(amount.getText()) > 0 && to.getText().length() > 2)
                {
                    ChequeMod.instance.eventHandler.enoughMoney(Long.parseLong(amount.getText()), to.getText());
                    Minecraft.getMinecraft().displayGuiScreen(null);
                }
                else
                    player.addChatMessage(new ChatComponentText(I18n.format("amount.must.be.superior.to.zero.and.without.spaces.and.receiver.name.must.be.longer.than.two.characters")));
        }
        else if(i != 1)
            player.addChatMessage(new ChatComponentText(I18n.format("cheque.already.signed")));
    }

    protected void mouseClicked(int x, int y, int b)
    {
        super.mouseClicked(x, y, b);
        if(!signed)
        {
            amount.mouseClicked(x, y, b);
            to.mouseClicked(x, y, b);
        }
        else
            player.addChatMessage(new ChatComponentText(I18n.format("cheque.already.signed")));
    }

    @Override
    protected void actionPerformed(GuiButton btn)
    {}
}