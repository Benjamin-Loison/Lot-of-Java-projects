package com.degraduck.minefus.common;

import com.degraduck.minefus.proxy.CommonProxy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayer
  implements IExtendedEntityProperties
{
  public static final String EXT_PROP_MINEFUSINV = "ExtPropMinefusInv";
  private final EntityPlayer player;
  public final MinefusInventoryPlayer inventory = new MinefusInventoryPlayer();
  public final MimibioteInventoryPlayer inventoryMimi = new MimibioteInventoryPlayer();
  
  public ExtendedPlayer(EntityPlayer player)
  {
    this.player = player;
  }
  
  public void saveNBTData(NBTTagCompound compound)
  {
    NBTTagCompound properties = new NBTTagCompound();
    inventory.writeToNBT(properties);
    
    compound.setTag("ExtPropMinefusInv", properties);
  }
  
  public void loadNBTData(NBTTagCompound compound)
  {
    NBTTagCompound properties = (NBTTagCompound)compound.getTag("ExtPropMinefusInv");
    
    inventory.readFromNBT(properties);
  }
  
  public void init(Entity entity, World world) {}
  
  public final void sync() {}
  
  public static final void register(EntityPlayer player)
  {
    player.registerExtendedProperties("ExtPropMinefusInv", new ExtendedPlayer(player));
  }
  
  public static final ExtendedPlayer get(EntityPlayer player)
  {
    return (ExtendedPlayer)player.getExtendedProperties("ExtPropMinefusInv");
  }
  
  private static final String getSaveKey(EntityPlayer player)
  {
    return player.getCommandSenderName() + ":" + "ExtPropMinefusInv";
  }
  
  public static void saveProxyData(EntityPlayer player)
  {
    ExtendedPlayer playerData = get(player);
    NBTTagCompound savedData = new NBTTagCompound();
    
    playerData.saveNBTData(savedData);
    CommonProxy.storeEntityData(getSaveKey(player), savedData);
  }
  
  public static final void loadProxyData(EntityPlayer player)
  {
    ExtendedPlayer playerData = get(player);
    NBTTagCompound savedData = CommonProxy.getEntityData(getSaveKey(player));
    if (savedData != null) {
      playerData.loadNBTData(savedData);
    }
    Dofus.packetPipeline.sendTo(new SyncPlayerPropsPacket(player), (EntityPlayerMP)player);
  }
}
