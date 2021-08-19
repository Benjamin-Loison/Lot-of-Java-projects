package com.minefus.degraduck.entity;

import com.minefus.degraduck.gui.MimibioteInventoryPlayer;
import com.minefus.degraduck.gui.MinefusInventoryPlayer;
import com.minefus.degraduck.proxy.CommonProxy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayer implements IExtendedEntityProperties
{
    public static final String EXT_PROP_MINEFUSINV = "ExtPropMinefusInv";
    private final EntityPlayer player;
    public final MinefusInventoryPlayer inventory = new MinefusInventoryPlayer();
    public final MimibioteInventoryPlayer inventoryMimibiote = new MimibioteInventoryPlayer();

    public ExtendedPlayer(EntityPlayer player)
    {
        this.player = player;
    }

    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = new NBTTagCompound();
        inventory.writeToNBT(properties);
        compound.setTag(EXT_PROP_MINEFUSINV, properties);
    }

    public void loadNBTData(NBTTagCompound compound)
    {
        inventory.readFromNBT((NBTTagCompound)compound.getTag(EXT_PROP_MINEFUSINV));
    }

    public void init(Entity entity, World world)
    {}

    public final void sync()
    {}

    public static final void register(EntityPlayer player)
    {
        player.registerExtendedProperties(EXT_PROP_MINEFUSINV, new ExtendedPlayer(player));
    }

    public static final ExtendedPlayer get(EntityPlayer player)
    {
        return (ExtendedPlayer)player.getExtendedProperties(EXT_PROP_MINEFUSINV);
    }

    private static final String getSaveKey(EntityPlayer player)
    {
        return player.getCommandSenderName() + ":" + EXT_PROP_MINEFUSINV;
    }

    public static void saveProxyData(EntityPlayer player)
    {
        NBTTagCompound savedData = new NBTTagCompound();
        get(player).saveNBTData(savedData);
        CommonProxy.storeEntityData(getSaveKey(player), savedData);
    }

    public static final void loadProxyData(EntityPlayer player)
    {
        ExtendedPlayer playerData = get(player);
        NBTTagCompound savedData = CommonProxy.getEntityData(getSaveKey(player));
        if(savedData != null)
            playerData.loadNBTData(savedData);
    }
}