package com.degraduck.minefus.common;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUIMimibioteInventoryPlayer
  extends GuiContainer
{
  private float xSize_lo;
  private float ySize_lo;
  private static final ResourceLocation iconLocation = new ResourceLocation("modminefusinventory:textures/gui/gui_mimibiote.png");
  private final MinefusInventoryPlayer inventoryMimi;
  
  public GUIMimibioteInventoryPlayer(EntityPlayer player, InventoryPlayer inventoryPlayer, MinefusInventoryPlayer inventoryMinefus)
  {
    super(new MimibioteContainerPlayer(player, inventoryPlayer, inventoryMinefus));
    inventoryMimi = inventoryMinefus;
    ySize += 52;
  }
  
  public void drawScreen(int par1, int par2, float par3)
  {
    super.drawScreen(par1, par2, par3);
    xSize_lo = par1;
    ySize_lo = par2;
  }
  
  protected void drawGuiContainerForegroundLayer(int par1, int par2) {}
  
  protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
  {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    mc.getTextureManager().bindTexture(iconLocation);
    int k = (width - xSize) / 2;
    int l = (height - ySize) / 2;
    drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
  }
}
