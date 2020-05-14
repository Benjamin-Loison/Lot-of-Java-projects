package fr.watchdogs.benjaminloison.driveables;

import java.util.Collections;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemMechaAddon extends Item implements IFlanItem
{
    public MechaItemType type;

    public ItemMechaAddon(MechaItemType type1)
    {
        type = type1;
        setMaxStackSize(1);
        type.item = this;
        GameRegistry.registerItem(this, type.shortName, WatchDogs.MODID);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b)
    {
        if(type.description != null)
            Collections.addAll(list, type.description.split("_"));
    }

    @Override
    public InfoType getInfoType()
    {
        return type;
    }
}