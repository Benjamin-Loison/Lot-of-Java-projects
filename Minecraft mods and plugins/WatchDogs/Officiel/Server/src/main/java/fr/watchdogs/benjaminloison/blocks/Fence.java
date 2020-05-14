package fr.altiscraft.benjaminloison.blocks;

import fr.watchdogs.benjaminloison.entity.EEP;
import fr.watchdogs.benjaminloison.tileentity.TileEntityFence;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class Fence extends Block
{
    public Fence()
    {
        super(Material.ground);
        setBlockName("fence");
        setHardness(1);
        setBlockBounds(0.2F, 0, 0.2F, 0.8F, 0.9F, 0.8F);
    }

    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityFence();
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public void onBlockAdded(World world, int x, int y, int z)
    {
        world.setBlock(x, y + 1, z, AltisCraft.invisible, 0, 2);
        world.setBlock(x, y + 2, z, AltisCraft.invisible, 0, 2);
    }

    public boolean onBlockEventReceived(World world, int x, int y, int z, int id, int value)
    {
        super.onBlockEventReceived(world, x, y, z, id, value);
        TileEntity tileentity = world.getTileEntity(x, y, z);
        return tileentity != null ? tileentity.receiveClientEvent(id, value) : false;
    }

    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer player, int int1, float flox, float floy, float floz)
    {
        if(EEP.get(player).police)
        {
            TileEntity tile = w.getTileEntity(x, y, z);
            if(tile instanceof TileEntityFence)
            {
                byte direction = ((TileEntityFence)tile).getDirection();
                TileEntityFence tileentity = (TileEntityFence)tile;
                if(tileentity.numPlayersUsing == 0)
                {
                    ++tileentity.numPlayersUsing;
                    w.addBlockEvent(x, y, z, this, 1, tileentity.numPlayersUsing);
                    w.notifyBlocksOfNeighborChange(x, y, z, this);
                    w.notifyBlocksOfNeighborChange(x, y - 1, z, this);
                    switch(direction)
                    {
                        case 0:
                            w.setBlock(x + 1, y, z, Blocks.air, 0, 2);
                            w.setBlock(x + 1, y + 1, z, Blocks.air, 0, 2);
                            w.setBlock(x + 1, y + 2, z, Blocks.air, 0, 2);
                            w.setBlock(x + 2, y, z, Blocks.air, 0, 2);
                            w.setBlock(x + 2, y + 1, z, Blocks.air, 0, 2);
                            w.setBlock(x + 2, y + 2, z, Blocks.air, 0, 2);
                            w.setBlock(x + 3, y, z, Blocks.air, 0, 2);
                            w.setBlock(x + 3, y + 1, z, Blocks.air, 0, 2);
                            w.setBlock(x + 3, y + 2, z, Blocks.air, 0, 2);
                            w.setBlock(x + 4, y, z, Blocks.air, 0, 2);
                            w.setBlock(x + 4, y + 1, z, Blocks.air, 0, 2);
                            w.setBlock(x + 4, y + 2, z, Blocks.air, 0, 2);
                            break;
                        case 1:
                            w.setBlock(x, y, z + 1, Blocks.air, 0, 2);
                            w.setBlock(x, y + 1, z + 1, Blocks.air, 0, 2);
                            w.setBlock(x, y + 2, z + 1, Blocks.air, 0, 2);
                            w.setBlock(x, y, z + 2, Blocks.air, 0, 2);
                            w.setBlock(x, y + 1, z + 2, Blocks.air, 0, 2);
                            w.setBlock(x, y + 2, z + 2, Blocks.air, 0, 2);
                            w.setBlock(x, y, z + 3, Blocks.air, 0, 2);
                            w.setBlock(x, y + 1, z + 3, Blocks.air, 0, 2);
                            w.setBlock(x, y + 2, z + 3, Blocks.air, 0, 2);
                            w.setBlock(x, y, z + 4, Blocks.air, 0, 2);
                            w.setBlock(x, y + 1, z + 4, Blocks.air, 0, 2);
                            w.setBlock(x, y + 2, z + 4, Blocks.air, 0, 2);
                            break;
                        case 2:
                            w.setBlock(x - 1, y, z, Blocks.air, 0, 2);
                            w.setBlock(x - 1, y + 1, z, Blocks.air, 0, 2);
                            w.setBlock(x - 1, y + 2, z, Blocks.air, 0, 2);
                            w.setBlock(x - 2, y, z, Blocks.air, 0, 2);
                            w.setBlock(x - 2, y + 1, z, Blocks.air, 0, 2);
                            w.setBlock(x - 2, y + 2, z, Blocks.air, 0, 2);
                            w.setBlock(x - 3, y, z, Blocks.air, 0, 2);
                            w.setBlock(x - 3, y + 1, z, Blocks.air, 0, 2);
                            w.setBlock(x - 3, y + 2, z, Blocks.air, 0, 2);
                            w.setBlock(x - 4, y, z, Blocks.air, 0, 2);
                            w.setBlock(x - 4, y + 1, z, Blocks.air, 0, 2);
                            w.setBlock(x - 4, y + 2, z, Blocks.air, 0, 2);
                            break;
                        case 3:
                            w.setBlock(x, y, z - 1, Blocks.air, 0, 2);
                            w.setBlock(x, y + 1, z - 1, Blocks.air, 0, 2);
                            w.setBlock(x, y + 2, z - 1, Blocks.air, 0, 2);
                            w.setBlock(x, y, z - 2, Blocks.air, 0, 2);
                            w.setBlock(x, y + 1, z - 2, Blocks.air, 0, 2);
                            w.setBlock(x, y + 2, z - 2, Blocks.air, 0, 2);
                            w.setBlock(x, y, z - 3, Blocks.air, 0, 2);
                            w.setBlock(x, y + 1, z - 3, Blocks.air, 0, 2);
                            w.setBlock(x, y + 2, z - 3, Blocks.air, 0, 2);
                            w.setBlock(x, y, z - 4, Blocks.air, 0, 2);
                            w.setBlock(x, y + 1, z - 4, Blocks.air, 0, 2);
                            w.setBlock(x, y + 2, z - 4, Blocks.air, 0, 2);
                            break;
                    }
                }
                else
                {
                    tileentity.numPlayersUsing = 0;
                    w.addBlockEvent(x, y, z, this, 1, tileentity.numPlayersUsing);
                    w.notifyBlocksOfNeighborChange(x, y, z, this);
                    w.notifyBlocksOfNeighborChange(x, y - 1, z, this);
                    switch(direction)
                    {
                        case 0:
                            w.setBlock(x + 1, y, z, AltisCraft.invisible, 0, 2);
                            w.setBlock(x + 1, y + 1, z, AltisCraft.invisible, 0, 2);
                            w.setBlock(x + 1, y + 2, z, AltisCraft.invisible, 0, 2);
                            w.setBlock(x + 2, y, z, AltisCraft.invisible, 0, 2);
                            w.setBlock(x + 2, y + 1, z, AltisCraft.invisible, 0, 2);
                            w.setBlock(x + 2, y + 2, z, AltisCraft.invisible, 0, 2);
                            w.setBlock(x + 3, y, z, AltisCraft.invisible, 0, 2);
                            w.setBlock(x + 3, y + 1, z, AltisCraft.invisible, 0, 2);
                            w.setBlock(x + 3, y + 2, z, AltisCraft.invisible, 0, 2);
                            w.setBlock(x + 4, y, z, AltisCraft.invisible, 0, 2);
                            w.setBlock(x + 4, y + 1, z, AltisCraft.invisible, 0, 2);
                            w.setBlock(x + 4, y + 2, z, AltisCraft.invisible, 0, 2);
                            break;
                        case 1:
                            w.setBlock(x, y, z + 1, AltisCraft.invisible, 0, 2);
                            w.setBlock(x, y + 1, z + 1, AltisCraft.invisible, 0, 2);
                            w.setBlock(x, y + 2, z + 1, AltisCraft.invisible, 0, 2);
                            w.setBlock(x, y, z + 2, AltisCraft.invisible, 0, 2);
                            w.setBlock(x, y + 1, z + 2, AltisCraft.invisible, 0, 2);
                            w.setBlock(x, y + 2, z + 2, AltisCraft.invisible, 0, 2);
                            w.setBlock(x, y, z + 3, AltisCraft.invisible, 0, 2);
                            w.setBlock(x, y + 1, z + 3, AltisCraft.invisible, 0, 2);
                            w.setBlock(x, y + 2, z + 3, AltisCraft.invisible, 0, 2);
                            w.setBlock(x, y, z + 4, AltisCraft.invisible, 0, 2);
                            w.setBlock(x, y + 1, z + 4, AltisCraft.invisible, 0, 2);
                            w.setBlock(x, y + 2, z + 4, AltisCraft.invisible, 0, 2);
                            break;
                        case 2:
                            w.setBlock(x - 1, y, z, AltisCraft.invisible, 0, 2);
                            w.setBlock(x - 1, y + 1, z, AltisCraft.invisible, 0, 2);
                            w.setBlock(x - 1, y + 2, z, AltisCraft.invisible, 0, 2);
                            w.setBlock(x - 2, y, z, AltisCraft.invisible, 0, 2);
                            w.setBlock(x - 2, y + 1, z, AltisCraft.invisible, 0, 2);
                            w.setBlock(x - 2, y + 2, z, AltisCraft.invisible, 0, 2);
                            w.setBlock(x - 3, y, z, AltisCraft.invisible, 0, 2);
                            w.setBlock(x - 3, y + 1, z, AltisCraft.invisible, 0, 2);
                            w.setBlock(x - 3, y + 2, z, AltisCraft.invisible, 0, 2);
                            w.setBlock(x - 4, y, z, AltisCraft.invisible, 0, 2);
                            w.setBlock(x - 4, y + 1, z, AltisCraft.invisible, 0, 2);
                            w.setBlock(x - 4, y + 2, z, AltisCraft.invisible, 0, 2);
                            break;
                        case 3:
                            w.setBlock(x, y, z - 1, AltisCraft.invisible, 0, 2);
                            w.setBlock(x, y + 1, z - 1, AltisCraft.invisible, 0, 2);
                            w.setBlock(x, y + 2, z - 1, AltisCraft.invisible, 0, 2);
                            w.setBlock(x, y, z - 2, AltisCraft.invisible, 0, 2);
                            w.setBlock(x, y + 1, z - 2, AltisCraft.invisible, 0, 2);
                            w.setBlock(x, y + 2, z - 2, AltisCraft.invisible, 0, 2);
                            w.setBlock(x, y, z - 3, AltisCraft.invisible, 0, 2);
                            w.setBlock(x, y + 1, z - 3, AltisCraft.invisible, 0, 2);
                            w.setBlock(x, y + 2, z - 3, AltisCraft.invisible, 0, 2);
                            w.setBlock(x, y, z - 4, AltisCraft.invisible, 0, 2);
                            w.setBlock(x, y + 1, z - 4, AltisCraft.invisible, 0, 2);
                            w.setBlock(x, y + 2, z - 4, AltisCraft.invisible, 0, 2);
                            break;
                    }
                }
            }
        }
        return true;
    }

    public void breakBlock(World w, int x, int y, int z, Block block, int o)
    {
        w.setBlock(x, y + 1, z, Blocks.air, 0, 2);
        w.setBlock(x, y + 2, z, Blocks.air, 0, 2);
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
    {
        if(stack.getItemDamage() == 0)
        {
            TileEntity tile = world.getTileEntity(x, y, z);
            if((tile instanceof TileEntityFence))
                ((TileEntityFence)tile).setDirection((byte)(MathHelper.floor_double(living.rotationYaw * 4 / 362.5D) & 0x3));
        }
    }

    public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis)
    {
        if((axis == ForgeDirection.UP || axis == ForgeDirection.DOWN) && world.getBlockMetadata(x, y, z) == 0)
        {
            TileEntity tile = world.getTileEntity(x, y, z);
            if((tile instanceof TileEntityFence))
            {
                TileEntityFence tileFence = (TileEntityFence)tile;
                byte direction = (byte)(tileFence.getDirection() + 1);
                if(direction > 3)
                    direction = 0;
                tileFence.setDirection(direction);
                return true;
            }
        }
        return false;
    }

    public ForgeDirection[] getValidRotations(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z) == 0 ? new ForgeDirection[] {ForgeDirection.UP, ForgeDirection.DOWN} : ForgeDirection.VALID_DIRECTIONS;
    }
}