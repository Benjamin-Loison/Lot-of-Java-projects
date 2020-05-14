package fr.altiscraft.benjaminloison.events;

import java.util.List;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.entity.EEP;
import fr.watchdogs.benjaminloison.entity.EntityCustomizableNPC;
import fr.watchdogs.benjaminloison.packets.PacketMessage;
import fr.watchdogs.benjaminloison.proxy.CommonProxy;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemFood;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;

public class ForgeT4EventHandler
{
    @SubscribeEvent
    public void onEntityConstructing(EntityConstructing event)
    {
        if(event.entity instanceof EntityPlayer && EEP.get((EntityPlayer)event.entity) == null)
            EEP.register((EntityPlayer)event.entity);
    }

    @SubscribeEvent
    public void onLivingDeathEvent(LivingDeathEvent event)
    {
        if(event.entity instanceof EntityPlayer)
        {
            NBTTagCompound playerData = new NBTTagCompound();
            ((EEP)(event.entity.getExtendedProperties(EEP.EXT_PROP_NAME))).saveNBTDataMoney(playerData);
            CommonProxy.storeEntityData(((EntityPlayer)event.entity).getDisplayName(), playerData);
            EEP.saveProxyData((EntityPlayer)event.entity);
        }
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
        if(event.entity instanceof EntityPlayer)
        {
            NBTTagCompound playerData = CommonProxy.getEntityData(((EntityPlayer)event.entity).getDisplayName());
            if(playerData != null)
                ((EEP)(event.entity.getExtendedProperties(EEP.EXT_PROP_NAME))).loadNBTData(playerData);
            ((EEP)(event.entity.getExtendedProperties(EEP.EXT_PROP_NAME))).resetInterpol();
            ((EEP)(event.entity.getExtendedProperties(EEP.EXT_PROP_NAME))).resetCuffed();
            ((EEP)(event.entity.getExtendedProperties(EEP.EXT_PROP_NAME))).sync();
        }
    }

    @SubscribeEvent
    public void onChat(ServerChatEvent event)
    {
        String msg = event.message;
        if(msg.startsWith("!HOUSE!"))
        {
            event.setCanceled(true);
            EEP.get(event.player).delMoney(Long.parseLong(msg.replace("!HOUSE!", "")));
        }
    }

    @SubscribeEvent
    public void onPlayerKill(LivingDeathEvent event)
    {
        if(event.entity instanceof EntityPlayerMP && event.source.getEntity() instanceof EntityPlayerMP)
        {
            EntityPlayerMP source = (EntityPlayerMP)event.source.getEntity(), killed = (EntityPlayerMP)event.entity;
            if(source != null && killed != null)
            {
                AltisCraft.instance.network.sendTo(new PacketMessage("you.have.killed", killed.getEntityId()), source);
                AltisCraft.instance.network.sendTo(new PacketMessage("you.have.been.killed.by", source.getEntityId()), killed);
                EEP pSource = EEP.get(source);
                EEP pKilled = EEP.get(killed);
                if(!pSource.police)
                    if(pKilled.police)
                        pSource.addInterpol(FileAPI.config("kill.policeman"), FileAPI.configNumber("fine.for.kill.an.agent"));
                    else if(pKilled.doctor)
                        pSource.addInterpol(FileAPI.config("kill.doctor"), FileAPI.configNumber("fine.for.kill.an.agent"));
                    else
                        pSource.addInterpol(FileAPI.config("kill"), FileAPI.configNumber("fine.for.kill"));
                pKilled.resetInterpol();
                pSource.addCash(pKilled.cash);
                pKilled.cash = 0;
                pKilled.sync();
                for(EntityPlayerMP player : (List<EntityPlayerMP>)MinecraftServer.getServer().getConfigurationManager().playerEntityList)
                {
                    EEP props = EEP.get(player);
                    if(props.police || props.staff && source != player)
                        AltisCraft.instance.network.sendTo(new PacketMessage("the.player.id.has.killed.killed.player.name.id", source.getDisplayName(), source.getEntityId(), killed.getDisplayName(), killed.getEntityId()), player);
                    // TODO: Demander SAMU réanimation
                }
            }
        }
    }

    @SubscribeEvent
    public void handleConstruction(EntityConstructing event)
    {
        if(event.entity instanceof EntityCustomizableNPC)
        {
            DataWatcher dw = event.entity.getDataWatcher();
            dw.addObject(20, FileAPI.config("npc.default.texture"));
            dw.addObject(21, FileAPI.config("npc.default.action"));
            dw.addObject(22, FileAPI.config("npc.default.permission"));
            dw.addObject(23, FileAPI.config("npc.default.x"));
            dw.addObject(24, FileAPI.config("npc.default.y"));
            dw.addObject(25, FileAPI.config("npc.default.z"));
            dw.addObject(26, FileAPI.config("npc.default.direction"));
            dw.addObject(27, FileAPI.config("npc.default.name"));
        }
    }

    @SubscribeEvent
    public void onLivingAttackEvent(LivingAttackEvent event)
    {
        if(event.source != null)
            if(event.source.getSourceOfDamage() instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer)event.source.getSourceOfDamage();
                if(EEP.get(player).cuffed)
                    event.setCanceled(true);
                if(player.inventory.getCurrentItem() == null || event.ammount <= 1)
                    event.setCanceled(true);
            }
            else if(event.source.getSourceOfDamage() instanceof EntityThrowable)
                if(event.ammount <= 1)
                    event.setCanceled(true);
    }

    @SubscribeEvent
    public void onPlayerUseItemEvent(PlayerUseItemEvent.Start event)
    {
        if(event.item.getItem() instanceof ItemFood)
            return;
        if(EEP.get(event.entityPlayer).cuffed)
            event.setCanceled(true);
    }

    @SubscribeEvent
    public void onEntityInteract(PlayerInteractEvent event)
    {
        EntityPlayer player = event.entityPlayer;
        if(EEP.get(player).cuffed)
        {
            if(player.getHeldItem() != null)
                if(player.getHeldItem().getItem() instanceof ItemFood)
                    return;
            event.setCanceled(true);
        }
    }
}