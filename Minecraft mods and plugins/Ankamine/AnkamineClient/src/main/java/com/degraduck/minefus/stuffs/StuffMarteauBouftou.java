package com.degraduck.minefus.stuffs;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class StuffMarteauBouftou extends ItemSword
{
    public StuffMarteauBouftou(ToolMaterial material)
    {
        super(material);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon("moddofus:icone_marteau_bouftou");
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4)
    {
        par3List.add("§7Â§oVous allez en fracasser des");
        par3List.add("§7Â§ocranes avec ce marteau !");
        par3List.add("");
        par3List.add("§4  Bonus :");

        super.addInformation(stack, player, par3List, par4);
    }
}
