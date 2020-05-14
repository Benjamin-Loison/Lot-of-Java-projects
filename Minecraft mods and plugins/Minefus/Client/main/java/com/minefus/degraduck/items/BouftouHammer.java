package com.minefus.degraduck.items;

import java.util.List;

import com.minefus.degraduck.common.Minefus;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class BouftouHammer extends ItemSword
{
    public BouftouHammer()
    {
        super(Minefus.hammer);
        setUnlocalizedName("bouftou_hammer");
        GameRegistry.registerItem(this, "bouftou_hammer");
    }

    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(Minefus.MODID + ":Bouftou hammer");
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b)
    {
        list.add("§7Â§oVous allez en fracasser des");
        list.add("§7Â§ocranes avec ce marteau !");
        list.add("");
        list.add("§4  Bonus :");
        super.addInformation(stack, player, list, b);
    }
}