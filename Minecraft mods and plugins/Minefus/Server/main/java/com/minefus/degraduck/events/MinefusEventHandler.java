package com.minefus.degraduck.events;

import com.minefus.degraduck.entity.ExtendedPlayer;
import com.minefus.degraduck.proxy.CommonProxy;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class MinefusEventHandler
{
    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event)
    {
        if(((event.entity instanceof EntityPlayer)) && (ExtendedPlayer.get((EntityPlayer)event.entity) == null))
            ExtendedPlayer.register((EntityPlayer)event.entity);
        if(((event.entity instanceof EntityPlayer)) && (event.entity.getExtendedProperties(ExtendedPlayer.EXT_PROP_MINEFUSINV) == null))
            event.entity.registerExtendedProperties(ExtendedPlayer.EXT_PROP_MINEFUSINV, new ExtendedPlayer((EntityPlayer)event.entity));
    }

    @SubscribeEvent
    public void onLivingDeathEvent(LivingDeathEvent event)
    {
        if(event.entity instanceof EntityPlayer)
        {
            NBTTagCompound playerData = new NBTTagCompound();
            ((ExtendedPlayer)event.entity.getExtendedProperties(ExtendedPlayer.EXT_PROP_MINEFUSINV)).saveNBTData(playerData);
            CommonProxy.storeEntityData(((EntityPlayer)event.entity).getCommandSenderName() + ExtendedPlayer.EXT_PROP_MINEFUSINV, playerData);
            ExtendedPlayer.saveProxyData((EntityPlayer)event.entity);
        }
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
        if(event.entity instanceof EntityPlayer)
        {
            NBTTagCompound playerData = CommonProxy.getEntityData(((EntityPlayer)event.entity).getCommandSenderName());
            if(playerData != null)
                ((ExtendedPlayer)event.entity.getExtendedProperties(ExtendedPlayer.EXT_PROP_MINEFUSINV)).loadNBTData(playerData);
            ((ExtendedPlayer)event.entity.getExtendedProperties(ExtendedPlayer.EXT_PROP_MINEFUSINV)).sync();
        }
    }
}