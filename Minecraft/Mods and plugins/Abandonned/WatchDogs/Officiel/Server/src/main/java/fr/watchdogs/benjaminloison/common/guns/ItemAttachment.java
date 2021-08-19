package fr.watchdogs.benjaminloison.common.guns;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.driveables.IFlanItem;
import fr.watchdogs.benjaminloison.driveables.InfoType;
import net.minecraft.item.Item;

public class ItemAttachment extends Item implements IFlanItem
{
    public AttachmentType type;

    public ItemAttachment(AttachmentType t)
    {
        type = t;
        type.item = this;
        maxStackSize = t.maxStackSize;
        GameRegistry.registerItem(this, type.shortName, WatchDogs.MODID);
    }

    @Override
    public InfoType getInfoType()
    {
        return type;
    }
}