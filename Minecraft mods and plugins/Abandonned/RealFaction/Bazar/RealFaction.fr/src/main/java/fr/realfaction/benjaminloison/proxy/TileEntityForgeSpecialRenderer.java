package fr.realfaction.benjaminloison.proxy;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.realfaction.benjaminloison.client.ModelBlockForge;
import fr.realfaction.benjaminloison.common.TileEntityForge;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class TileEntityForgeSpecialRenderer extends TileEntitySpecialRenderer {
	public static ModelBlockForge modelf = new ModelBlockForge();
	public static ResourceLocation texturef = new ResourceLocation("realfaction",
			"textures/models/blocks/ModelBlockForge.png");

	public TileEntityForgeSpecialRenderer() {
		func_147497_a(TileEntityRendererDispatcher.instance);
	}

	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialRenderTick) {
		renderTileEntityForgeAt((TileEntityForge) tile, x, y, z, partialRenderTick);
	}

	private void renderTileEntityForgeAt(TileEntityForge tile, double x, double y, double z, float partialRenderTick) {
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(90.0F * tile.getDirection() + 180.0F, 0.0F, 1.0F, 0.0F);
		bindTexture(texturef);
		modelf.renderAll();
		GL11.glPopMatrix();
	}
}