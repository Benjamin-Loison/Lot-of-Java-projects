package com.degraduck.minefus.common;

import com.degraduck.minefus.proxy.CommonProxy;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class MinefusEventHandler
{
  private CommonProxy proxy;
  public final MinefusInventoryPlayer minefusInventory = new MinefusInventoryPlayer();
  
  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public void PlayerTickEvent(TickEvent.PlayerTickEvent event)
  {
    if ((event.player instanceof EntityPlayer))
    {
      ItemStack amulette = minefusInventory.getStackInSlot(19);
      if ((amulette != null) && (amulette.getItem() == Items.apple))
      {
          event.player.setAbsorptionAmount(1.0F);
        System.out.println("Ca marche amulette!");
      }
    }
  }
  
  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public void GuiScreenEvent(GuiOpenEvent event)
  {
    if (((event.gui instanceof GuiInventory)) && (!Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode)) {
      event.setCanceled(true);
    }
  }
  
  @SubscribeEvent
  public void onEntityConstructing(EntityEvent.EntityConstructing event)
  {
    if (((event.entity instanceof EntityPlayer)) && (ExtendedPlayer.get((EntityPlayer)event.entity) == null)) {
      ExtendedPlayer.register((EntityPlayer)event.entity);
    }
    if (((event.entity instanceof EntityPlayer)) && (event.entity.getExtendedProperties("ExtPropMinefusInv") == null)) {
        event.entity.registerExtendedProperties("ExtPropMinefusInv", new ExtendedPlayer((EntityPlayer)event.entity));
    }
  }
  
  @SubscribeEvent
  public void onLivingDeathEvent(LivingDeathEvent event)
  {
    if ((!event.entity.worldObj.isRemote) && ((event.entity instanceof EntityPlayer)))
    {
      NBTTagCompound playerData = new NBTTagCompound();
      ((ExtendedPlayer)event.entity.getExtendedProperties("ExtPropMinefusInv")).saveNBTData(playerData);
      CommonProxy.storeEntityData(((EntityPlayer)event.entity).getCommandSenderName() + "ExtPropMinefusInv", playerData);
      
      ExtendedPlayer.saveProxyData((EntityPlayer)event.entity);
    }
  }
  
  @SubscribeEvent
  public void onEntityJoinWorld(EntityJoinWorldEvent event)
  {
    if ((!event.entity.worldObj.isRemote) && ((event.entity instanceof EntityPlayer)))
    {
      NBTTagCompound playerData = CommonProxy.getEntityData(((EntityPlayer)event.entity).getCommandSenderName());
      if (playerData != null) {
        ((ExtendedPlayer)event.entity.getExtendedProperties("ExtPropMinefusInv")).loadNBTData(playerData);
      }
      ((ExtendedPlayer)event.entity.getExtendedProperties("ExtPropMinefusInv")).sync();
    }
  }
}
