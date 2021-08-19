package fr.watchdogs.benjaminloison.tileentity;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityFence extends TileEntity
{
    public float lidAngle, prevLidAngle;
    public int numPlayersUsing;
    private int ticksSinceSync;
    private byte direction;

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        direction = compound.getByte("Direction");
    }

    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setByte("Direction", direction);
    }

    public byte getDirection()
    {
        return direction;
    }

    public void setDirection(byte direction)
    {
        this.direction = direction;
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        writeToNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, nbttagcompound);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        readFromNBT(pkt.func_148857_g());
        worldObj.markBlockRangeForRenderUpdate(xCoord, yCoord, zCoord, xCoord, yCoord, zCoord);
    }

    @Override
    public boolean receiveClientEvent(int id, int value)
    {
        if(id == 1)
        {
            numPlayersUsing = value;
            return true;
        }
        else
        {
            return super.receiveClientEvent(id, value);
        }
    }

    @Override
    public void updateEntity()
    {
        ++ticksSinceSync;
        float f;
        if(numPlayersUsing != 0 && (ticksSinceSync + xCoord + yCoord + zCoord) % 200 == 0)
        {
            numPlayersUsing = 0;
            f = 5;
            Iterator iterator = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox((double)((float)xCoord - f), (double)((float)yCoord - f), (double)((float)zCoord - f), (double)((float)(xCoord + 1) + f), (double)((float)(yCoord + 1) + f), (double)((float)(zCoord + 1) + f))).iterator();
            while(iterator.hasNext())
            {
                EntityPlayer entityplayer = (EntityPlayer)iterator.next();
                ++numPlayersUsing;
            }
        }
        prevLidAngle = lidAngle;
        f = 0.1F;
        double d2;
        if(numPlayersUsing > 0 && lidAngle == 0)
        {
            d2 = (double)zCoord + 0.5;
            worldObj.playSoundEffect((double)xCoord + 0.5, (double)yCoord + 0.5, d2, "random.chestopen", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
        }
        if(numPlayersUsing == 0 && lidAngle > 0 || numPlayersUsing > 0 && lidAngle < 1)
        {
            float f1 = lidAngle;
            if(numPlayersUsing > 0)
                lidAngle += f;
            else
                lidAngle -= f;
            if(lidAngle > 1)
                lidAngle = 1;
            float f2 = 0.5F;
            if(lidAngle < f2 && f1 >= f2)
                worldObj.playSoundEffect((double)xCoord + 0.5, (double)yCoord + 0.5, (double)zCoord + 0.5, "random.chestclosed", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
            if(lidAngle < 0)
                lidAngle = 0;
        }
    }
}