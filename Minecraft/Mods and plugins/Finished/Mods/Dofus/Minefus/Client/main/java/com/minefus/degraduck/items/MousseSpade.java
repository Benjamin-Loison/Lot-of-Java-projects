package com.minefus.degraduck.items;

import com.minefus.degraduck.common.Minefus;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemSword;

public class MousseSpade extends ItemSword
{
    public MousseSpade()
    {
        super(Minefus.spade);
        setUnlocalizedName("mousse_spade");
        GameRegistry.registerItem(this, "mousse_spade");
    }

    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(Minefus.MODID + ":Mousse spade");
    }
}