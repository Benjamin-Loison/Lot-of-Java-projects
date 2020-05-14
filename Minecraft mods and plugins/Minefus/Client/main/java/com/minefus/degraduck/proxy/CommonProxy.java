package com.minefus.degraduck.proxy;

import java.util.HashMap;
import java.util.Map;

import com.minefus.degraduck.common.Minefus;
import com.minefus.degraduck.gui.GuiMinefusInventoryPlayer;
import com.minefus.degraduck.gui.MinefusContainerPlayer;
import com.minefus.degraduck.gui.MinefusInventoryPlayer;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler
{
    private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap();

    public void registerRender()
    {}

    public Object getServerGuiElement(int guiId, EntityPlayer player, World world, int x, int y, int z)
    {
        if(guiId == Minefus.GUI_MINEFUS_INV)
            return new MinefusContainerPlayer(player, player.inventory, new MinefusInventoryPlayer());
        return null;
    }

    public Object getClientGuiElement(int guiId, EntityPlayer player, World world, int x, int y, int z)
    {
        if(guiId == Minefus.GUI_MINEFUS_INV)
            return new GuiMinefusInventoryPlayer(player, player.inventory, new MinefusInventoryPlayer());
        return null;
    }

    public static void storeEntityData(String name, NBTTagCompound compound)
    {
        extendedEntityData.put(name, compound);
    }

    public static NBTTagCompound getEntityData(String name)
    {
        return (NBTTagCompound)extendedEntityData.remove(name);
    }
}