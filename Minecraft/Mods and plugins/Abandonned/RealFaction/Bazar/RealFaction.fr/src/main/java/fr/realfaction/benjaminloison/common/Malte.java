package fr.realfaction.benjaminloison.common;

import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class Malte extends BlockCrops {
	@SideOnly(Side.CLIENT)
	private IIcon[] field_149869_a;

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int int1, int int2) {
		if (int2 < 7) {
			if (int2 == 6) {
				int2 = 5;
			}

			return this.field_149869_a[int2 >> 1];
		} else {
			return this.field_149869_a[3];
		}
	}

	protected Item func_149866_i() {
		return ModRealFaction.Malt;
	}

	protected Item func_149865_P() {
		return ModRealFaction.Malt;
	}

	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int int1, float flo, int int2) {
		super.dropBlockAsItemWithChance(world, x, y, z, int1, flo, int2);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);
		if (metadata >= 7 && world.rand.nextInt(50) == 0)
			ret.add(new ItemStack(ModRealFaction.Malt));
		return ret;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon) {
		this.field_149869_a = new IIcon[4];

		for (int i = 0; i < this.field_149869_a.length; ++i) {
			this.field_149869_a[i] = icon.registerIcon(this.getTextureName() + "_stage_" + i);
		}
	}
}