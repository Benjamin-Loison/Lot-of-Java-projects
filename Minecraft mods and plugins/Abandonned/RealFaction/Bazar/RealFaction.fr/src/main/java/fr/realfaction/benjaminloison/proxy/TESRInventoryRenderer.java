package fr.realfaction.benjaminloison.proxy;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import fr.realfaction.benjaminloison.common.ModRealFaction;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class TESRInventoryRenderer implements ISimpleBlockRenderingHandler {
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		if (block == ModRealFaction.BlocForge) {
			GL11.glPushMatrix();
			GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(0.0F, -1.0F, 0.0F);
			Minecraft.getMinecraft().getTextureManager().bindTexture(TileEntityForgeSpecialRenderer.texturef);
			TileEntityForgeSpecialRenderer.modelf.renderAll();
			GL11.glPopMatrix();
		}

	}

	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {
		return false;
	}

	public int getRenderId() {
		return ClientProxy.tesrRenderId;
	}

	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}
}