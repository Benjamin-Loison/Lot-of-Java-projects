package fr.realfaction.benjaminloison.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlocCraft extends Block {
	@SideOnly(Side.CLIENT)
	private IIcon field_150035_a;
	@SideOnly(Side.CLIENT)
	private IIcon field_150034_b;

	protected BlocCraft() {
		super(Material.wood);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int int1, int int2) {
		return int1 == 1 ? this.field_150035_a
				: (int1 == 0 ? Blocks.planks.getBlockTextureFromSide(int1)
						: (int1 != 2 && int1 != 4 ? this.blockIcon : this.field_150034_b));
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon) {
		this.blockIcon = icon.registerIcon(this.getTextureName() + "_side");
		this.field_150035_a = icon.registerIcon(this.getTextureName() + "_top");
		this.field_150034_b = icon.registerIcon(this.getTextureName() + "_front");
	}

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            player.displayGUIWorkbench(x, y, z);
            return true;
        }
        else
        {
            player.displayGUIWorkbench(x, y, z);
            return true;
        }
    }
}