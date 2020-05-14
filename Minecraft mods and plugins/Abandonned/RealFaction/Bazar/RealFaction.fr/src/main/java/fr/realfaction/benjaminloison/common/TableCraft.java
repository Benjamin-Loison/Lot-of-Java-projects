package fr.realfaction.benjaminloison.common;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class TableCraft extends GuiContainer {
	private static final ResourceLocation craftingTableGuiTextures = new ResourceLocation(
			"textures/gui/container/crafting_table.png");

	public TableCraft(InventoryPlayer inv, World world, int x, int y, int z) {
		super(new ContainerWorkbench(inv, world, x, y, z));
	}

	protected void drawGuiContainerForegroundLayer(int int1, int int2) {
		this.fontRendererObj.drawString(I18n.format("container.crafting", new Object[0]), 28, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2,
				4210752);
	}

	protected void drawGuiContainerBackgroundLayer(float floate, int int1, int int2) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(craftingTableGuiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
}