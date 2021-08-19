package fr.watchdogs.benjaminloison.driveables;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import net.minecraft.item.Item;

public class ItemPart extends Item implements IFlanItem
{
    public PartType type;

    public ItemPart(PartType type1)
    {
        super();
        type = type1;
        setMaxStackSize(type.stackSize);
        if(type.category == 9)
        {
            setMaxDamage(type.fuel);
            setHasSubtypes(true);
        }
        type.item = this;
        GameRegistry.registerItem(this, type.shortName, WatchDogs.MODID);
    }

    @Override
    public InfoType getInfoType()
    {
        return type;
    }
}