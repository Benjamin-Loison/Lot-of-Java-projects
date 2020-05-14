package com.minefus.degraduck.items;

import java.util.List;

import com.minefus.degraduck.common.Minefus;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class RoyalBouftouSword extends ItemSword
{
    public RoyalBouftouSword()
    {
        super(Minefus.sword);
        setUnlocalizedName("royal_bouftou_sword");
        GameRegistry.registerItem(this, "royal_bouftou_sword");
    }

    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(Minefus.MODID + ":Royal bouftou sword");
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b)
    {
        list.add("§7Â§oCette épée ne sait pas trop");
        list.add("§7Â§osur quel sabot danser...");
        list.add("");
        list.add("§4  Bonus :");
        super.addInformation(stack, player, list, b);
    }
}