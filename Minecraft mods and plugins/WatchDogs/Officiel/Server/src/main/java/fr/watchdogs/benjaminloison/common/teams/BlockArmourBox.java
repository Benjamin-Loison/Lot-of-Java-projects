package fr.watchdogs.benjaminloison.common.teams;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.watchdogs.benjaminloison.api.CraftingInstance;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.common.teams.ArmourBoxType.ArmourBoxEntry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockArmourBox extends Block
{
    public ArmourBoxType type;

    public BlockArmourBox(ArmourBoxType t)
    {
        super(Material.wood);
        type = t;
        setBlockName(type.shortName);
        setHardness(2F);
        setResistance(4F);
        GameRegistry.registerBlock(this, "armor_box_" + type.shortName);
        type.block = this;
        type.item = Item.getItemFromBlock(this);
    }

    public void buyArmour(String shortName, int piece, InventoryPlayer inventory)
    {
        ArmourBoxEntry entryPicked = null;
        for(ArmourBoxEntry page : type.pages)
            if(page.shortName.equals(shortName))
                entryPicked = page;
        CraftingInstance crafting = new CraftingInstance(inventory, entryPicked.requiredStacks[piece], new ItemStack(entryPicked.armours[piece].item));
        if(crafting.canCraft())
            crafting.craft(inventory.player);
    }

    @Override
    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9)
    {
        if(entityplayer.isSneaking())
            return false;
        entityplayer.openGui(WatchDogs.instance, 11, world, i, j, k);
        return true;
    }
}