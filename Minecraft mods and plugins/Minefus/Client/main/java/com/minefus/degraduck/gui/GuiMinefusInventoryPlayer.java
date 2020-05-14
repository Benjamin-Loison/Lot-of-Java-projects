package com.minefus.degraduck.gui;

import org.lwjgl.opengl.GL11;

import com.minefus.degraduck.common.Minefus;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiMinefusInventoryPlayer extends GuiContainer
{
    private float guiX, guiY;
    private static final ResourceLocation iconLocation = new ResourceLocation(Minefus.MODID + ":textures/gui/Inventory.png");
    private final MinefusInventoryPlayer inventoryMinefus;

    public GuiMinefusInventoryPlayer(EntityPlayer player, InventoryPlayer inventory, MinefusInventoryPlayer inventoryMinefus)
    {
        super(new MinefusContainerPlayer(player, inventory, inventoryMinefus));
        this.inventoryMinefus = inventoryMinefus;
        ySize += 52;
    }

    public void drawScreen(int i, int j, float f)
    {
        super.drawScreen(i, j, f);
        guiX = i;
        guiY = j;
    }

    protected void drawGuiContainerForegroundLayer(int i, int j)
    {
        String s = inventoryMinefus.hasCustomInventoryName() ? inventoryMinefus.getInventoryName() : I18n.format(inventoryMinefus.getInventoryName(), new Object[0]);
        fontRendererObj.drawString(s, 122 - fontRendererObj.getStringWidth(s), 105, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(iconLocation);
        int k = (width - xSize) / 2, l = (height - ySize) / 2;
        drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
        drawPlayerModel(k + 80, l + 90, 40, k + 51 - guiX, l + 75 - 50 - guiY, mc.thePlayer);
    }

    public static void drawPlayerModel(int x, int y, int scale, float yaw, float pitch, EntityLivingBase entity)
    {
        ItemStack pet = entity.getEquipmentInSlot(1);
        GL11.glEnable(2903);
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, 50.0F);
        if((pet != null) && (pet.getItem() == Minefus.bilby))
            GL11.glScalef(-scale * 0.8F, scale * 0.8F, scale * 0.8F);
        else if((pet != null) && (pet.getItem() == Minefus.croum))
            GL11.glScalef(-scale * 0.8F, scale * 0.8F, scale * 0.8F);
        else
            GL11.glScalef(-scale, scale, scale);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        float f2 = entity.renderYawOffset, f3 = entity.rotationYaw, f4 = entity.rotationPitch, f5 = entity.prevRotationYawHead, f6 = entity.rotationYawHead;
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-(float)Math.atan(pitch / 40.0F) * 20.0F, 1.0F, 0.0F, 0.0F);
        f2 = ((float)Math.atan(yaw / 40.0F) * 20.0F);
        f3 = ((float)Math.atan(yaw / 40.0F) * 40.0F);
        f4 = (-(float)Math.atan(pitch / 40.0F) * 20.0F);
        f6 = f3;
        f5 = f3;
        GL11.glTranslatef(0.0F, entity.yOffset, 0.0F);
        RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(32826);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(3553);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
}