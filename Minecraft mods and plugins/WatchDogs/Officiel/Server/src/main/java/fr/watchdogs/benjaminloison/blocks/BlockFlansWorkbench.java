package fr.watchdogs.benjaminloison.blocks;

import fr.watchdogs.benjaminloison.common.WatchDogs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockFlansWorkbench extends Block
{
    public BlockFlansWorkbench()
    {
        super(Material.ground);
        setHardness(3);
        setResistance(6);
        setBlockName("flans_workbench");
    }

    @Override
    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if(world.getBlockMetadata(i, j, k) == 1)
            player.openGui(WatchDogs.instance, 2, world, i, j, k);
        return true;
    }

    @Override
    public int damageDropped(int d)
    {
        return d;
    }
}