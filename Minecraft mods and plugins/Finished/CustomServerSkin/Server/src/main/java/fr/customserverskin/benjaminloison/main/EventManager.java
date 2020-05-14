package fr.customserverskin.benjaminloison.main;

import java.util.Iterator;
import java.util.Set;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class EventManager
{
    @SubscribeEvent
    public void onEntityConstructing(EntityConstructing event)
    {
        Entity entity = event.entity;
        if(!(entity instanceof EntityPlayer))
            return;
        EntityPlayer player = (EntityPlayer)entity;
        if(EEP.get(player) == null)
            EEP.register(player);
    }

    @SubscribeEvent
    public void onLivingDeathEvent(LivingDeathEvent event)
    {
        Entity entity = event.entity;
        if(entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)entity;
            NBTTagCompound playerData = new NBTTagCompound();
            ((EEP)(player.getExtendedProperties(CustomServerSkin.NAME))).saveNBTData(playerData);
            EEP.storeEntityData(player.getDisplayName(), playerData);
            EEP.saveProxyData(player);
        }
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
        Entity entity = event.entity;
        if(entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)entity;
            NBTTagCompound playerData = EEP.getEntityData(player.getDisplayName());
            if(playerData != null)
                ((EEP)(player.getExtendedProperties(CustomServerSkin.NAME))).loadNBTData(playerData);

            EEP.players.put(player.getDisplayName(), EEP.get(player).skin);
            Set<String> collection = EEP.players.keySet();
            Iterator<String> iterator = collection.iterator();
            while(iterator.hasNext())
            {
                String name = iterator.next();
                CustomServerSkin.network.sendTo(new PacketUpdate(name, EEP.players.get(name)), (EntityPlayerMP)player);
            }
        }
    }
}
