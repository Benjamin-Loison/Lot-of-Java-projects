package com.flansmod.common.guns;

import java.util.Collections;
import java.util.List;

import com.flansmod.common.FlansMod;
import com.flansmod.common.types.IFlanItem;
import com.flansmod.common.types.InfoType;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemAttachment extends Item implements IFlanItem {
	public AttachmentType type;

	public ItemAttachment(AttachmentType t) {
		type = t;
		type.item = this;
		maxStackSize = t.maxStackSize;
		setCreativeTab(CreativeTabs.tabMaterials);
		GameRegistry.registerItem(this, type.shortName, FlansMod.MODID);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		return type.colour;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon) {
		itemIcon = icon.registerIcon("FlansMod:" + type.iconPath);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List lines, boolean b) {
		if (type.description != null) {
			Collections.addAll(lines, type.description.split("_"));
		}
	}

	@Override
	public InfoType getInfoType() {
		return type;
	}
}
