package fr.phonegui.benjaminloison.items;

import net.minecraft.item.Item;

public class TalkieWalkie extends Item
{
    public TalkieWalkie(int number)
    {
        setUnlocalizedName("talkiewalkie" + number);
        setMaxStackSize(1);
    }
}