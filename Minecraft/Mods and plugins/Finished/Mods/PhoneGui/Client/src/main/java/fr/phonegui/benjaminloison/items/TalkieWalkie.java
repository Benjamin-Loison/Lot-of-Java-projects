package fr.phonegui.benjaminloison.items;

import fr.phonegui.benjaminloison.common.PhoneGui;
import fr.phonegui.benjaminloison.gui.GuiTalkieWalkie;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TalkieWalkie extends Item
{
    int number;
    
    public TalkieWalkie(int number)
    {
        this.number = number;
        setUnlocalizedName("talkiewalkie" + number);
        setTextureName(PhoneGui.MODID + ":Talkie Walkie " + number);
        setCreativeTab(CreativeTabs.tabMaterials);
        setMaxStackSize(1);
    }

    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer entity)
    {
        Minecraft.getMinecraft().displayGuiScreen(new GuiTalkieWalkie(number));
        return item;
    }
}