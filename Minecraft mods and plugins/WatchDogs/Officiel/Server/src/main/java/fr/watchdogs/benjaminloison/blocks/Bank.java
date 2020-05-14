package fr.altiscraft.benjaminloison.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class Bank extends Block
{
    public Bank()
    {
        super(Material.ground);
        setBlockName("bank");
        setHardness(1000);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int int1, float hitX, float hitY, float hitZ)
    {
        if(player.getHeldItem() != null && player.getHeldItem().getItem() == AltisCraft.dynamite)
        {
            world.setBlock(x, y, z, Blocks.air, 0, 2);
            player.destroyCurrentEquippedItem();
        }
        return true;
    }
}