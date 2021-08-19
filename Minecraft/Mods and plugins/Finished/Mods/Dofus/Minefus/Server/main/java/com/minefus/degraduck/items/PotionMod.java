package com.minefus.degraduck.items;

import com.minefus.degraduck.api.FileAPI;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PotionMod extends ItemFood
{
    String tp;

    public PotionMod(String tp, String name, String texture)
    {
        super(0, 0, true);
        this.tp = tp;
        setUnlocalizedName(name);
        setAlwaysEdible();
        GameRegistry.registerItem(this, name);
    }

    public EnumAction getItemUseAction(ItemStack item)
    {
        return EnumAction.drink;
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int i, float hitX, float hitY, float hitZ)
    {
        player.setPositionAndUpdate(FileAPI.x(tp), FileAPI.y(tp), FileAPI.z(tp));
        return false;
    }
}