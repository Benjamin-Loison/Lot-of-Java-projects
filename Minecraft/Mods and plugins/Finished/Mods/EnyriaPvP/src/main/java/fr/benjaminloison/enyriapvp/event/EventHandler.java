package fr.benjaminloison.enyriapvp.event;

import fr.benjaminloison.enyriapvp.stuff.Blockz;
import fr.benjaminloison.enyriapvp.stuff.Itemz;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler
{
    @SubscribeEvent
    public void onBlockDestroyedPomme(BreakEvent event)
    {
        if(event.getPlayer().capabilities.isCreativeMode)
            return;
        if(event.state.getBlock().equals(Blockz.enyriumOre))
            event.getPlayer().inventory.addItemStackToInventory(new ItemStack(Itemz.enyrium, 1));
        else if(event.state.getBlock().equals(Blockz.agateOre))
            event.getPlayer().inventory.addItemStackToInventory(new ItemStack(Itemz.agate, 1));
        else if(event.state.getBlock().equals(Blockz.cyaniteOre))
            event.getPlayer().inventory.addItemStackToInventory(new ItemStack(Itemz.cyanite, 1));
        else if(event.state.getBlock().equals(Blockz.cesiumOre))
            event.getPlayer().inventory.addItemStackToInventory(new ItemStack(Itemz.cesium, 1));
    }
}