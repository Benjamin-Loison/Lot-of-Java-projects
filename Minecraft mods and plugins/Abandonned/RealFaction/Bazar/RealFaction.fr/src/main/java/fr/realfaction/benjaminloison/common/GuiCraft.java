package fr.realfaction.benjaminloison.common;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class GuiCraft extends GuiContainer
{
    private static final ResourceLocation craftingTableGuiTextures = new ResourceLocation("stationplus", "textures/gui/container/crafting_table.png");

    public GuiCraft(InventoryPlayer p_i1084_1_, World p_i1084_2_, int p_i1084_3_, int p_i1084_4_, int p_i1084_5_)
    {
        super(new ContainerWorkbench(p_i1084_1_, p_i1084_2_, p_i1084_3_, p_i1084_4_, p_i1084_5_));
    }

    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
    }

    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(craftingTableGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}