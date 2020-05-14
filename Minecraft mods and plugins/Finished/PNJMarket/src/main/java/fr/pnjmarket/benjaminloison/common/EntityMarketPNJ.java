package fr.pnjmarket.benjaminloison.common;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.world.World;

public class EntityMarketPNJ extends EntityLiving
{

    public EntityMarketPNJ(World world)
    {
        super(world);
    }

    public boolean isMovementCeased()
    {
        return true;
    }

    @Override
    public void onLivingUpdate()
    {}

    @SideOnly(Side.CLIENT)
    public boolean interact(EntityPlayer player)
    {
        EntityClientPlayerMP playerMP = (EntityClientPlayerMP)player;
        DataWatcher dw = getDataWatcher();
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        if(!player.isSneaking())
        {
            out.writeUTF(dw.getWatchableObjectString(21));
            playerMP.sendQueue.addToSendQueue(new C17PacketCustomPayload("PNJMarket", out.toByteArray()));
        }
        else
            return false;
        return true;
    }

    @Override
    public boolean isEntityInvulnerable()
    {
        return true;
    }

    public void writeEntityToNBT(NBTTagCompound nbt)
    {
        super.writeEntityToNBT(nbt);
        DataWatcher dw = getDataWatcher();
        nbt.setString("AltisCraftTexture", dw.getWatchableObjectString(20));
        nbt.setString("AltisCraftAction", dw.getWatchableObjectString(21));
    }

    public void readEntityFromNBT(NBTTagCompound nbt)
    {
        super.readEntityFromNBT(nbt);
        DataWatcher dw = getDataWatcher();
        String tex = nbt.getString("AltisCraftTexture");
        String act = nbt.getString("AltisCraftAction");
        dw.updateObject(20, tex);
        dw.updateObject(21, act);
    }
}