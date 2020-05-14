package fr.realfaction.benjaminloison.common;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class ForgeGUI extends GuiContainer {

	private static final ResourceLocation texture = new ResourceLocation(ModRealFaction.MODID,
			"textures/gui/container/guiForge.png");

	@SuppressWarnings("unused")

	private TileEntityForge tileForge;

	private IInventory playerInv;

	public ForgeGUI(TileEntityForge tile, InventoryPlayer inventory)

	{

		super(new ForgeConteneur(tile, inventory));

		this.tileForge = tile;

		this.playerInv = inventory;

		this.allowUserInput = false;

		this.ySize = 207;

		this.xSize = 195;

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialRenderTick, int x, int y)

	{

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.getTextureManager().bindTexture(texture);

		int k = (this.width - this.xSize) / 2;

		int l = (this.height - this.ySize) / 2;

		this.drawTexturedModalRect(k, l, 0, 46, this.xSize, this.ySize);

		if (this.tileForge.isBurning())

		{

			int i = this.tileForge.getCookProgress();

			this.drawTexturedModalRect(k + 47, l + 46, 0, 2, 100, i);

		}

	}

	protected void drawGuiContainerForegroundLayer(int x, int y)

	{

		this.fontRendererObj.drawString(this.playerInv.hasCustomInventoryName() ? this.playerInv.getInventoryName()
				: I18n.format(this.playerInv.getInventoryName()), 10, this.ySize - 98, 4210752);

	}

}