package com.minefus.degraduck.gui;

import org.lwjgl.opengl.GL11;

import com.minefus.degraduck.common.Minefus;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiMimibioteInventoryPlayer extends GuiContainer
{
    private float guiX, guiY;
    private static final ResourceLocation iconLocation = new ResourceLocation(Minefus.MODID + ":textures/gui/Mimibiote.png");
    private final MinefusInventoryPlayer inventoryMinefus;

    public GuiMimibioteInventoryPlayer(EntityPlayer player, InventoryPlayer inventory, MinefusInventoryPlayer inventoryMinefus)
    {
        super(new MimibioteContainerPlayer(inventory, inventoryMinefus));
        this.inventoryMinefus = inventoryMinefus;
        ySize += 52;
    }

    public void drawScreen(int x, int y, float z)
    {
        super.drawScreen(x, y, z);
        guiX = x;
        guiY = y;
    }

    protected void drawGuiContainerForegroundLayer(int i, int j)
    {}

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(iconLocation);
        drawTexturedModalRect((width - xSize) / 2, (height - ySize) / 2, 0, 0, xSize, ySize);
    }
}