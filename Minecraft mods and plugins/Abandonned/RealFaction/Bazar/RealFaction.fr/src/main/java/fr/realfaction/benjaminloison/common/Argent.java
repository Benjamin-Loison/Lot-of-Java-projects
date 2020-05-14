package fr.realfaction.benjaminloison.common;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Argent extends Item {
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, EntityPlayer entity, List list, boolean bo) {
		list.add("Monnaie");
	}
}