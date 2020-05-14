package com.minefus.degraduck.items;

import com.minefus.degraduck.common.Minefus;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class QuestHelmet extends ItemArmor
{
    public QuestHelmet()
    {
        super(Minefus.bouftouArmor, 0, 0);
        setUnlocalizedName("quest_helmet");
        GameRegistry.registerItem(this, "quest_helmet");
    }

    public boolean getIsRepairable(ItemStack input, ItemStack repair)
    {
        return true;
    }
}