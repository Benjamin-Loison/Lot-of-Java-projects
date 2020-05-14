package com.degraduck.minefus.proxy;

import java.util.HashMap;
import java.util.Map;

import com.degraduck.minefus.common.Dofus;
import com.degraduck.minefus.common.GUIMinefusInventoryPlayer;
import com.degraduck.minefus.common.MinefusContainerPlayer;
import com.degraduck.minefus.common.MinefusInventoryPlayer;

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
        System.out.println(guiId);
        if(guiId == Dofus.GUI_MINEFUS_INV)
            return new MinefusContainerPlayer(player, player.inventory, new MinefusInventoryPlayer());
        return null;
    }

    public Object getClientGuiElement(int guiId, EntityPlayer player, World world, int x, int y, int z)
    {
        System.out.println(guiId);
        if(guiId == Dofus.GUI_MINEFUS_INV)
            return new GUIMinefusInventoryPlayer(player, player.inventory, new MinefusInventoryPlayer());
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