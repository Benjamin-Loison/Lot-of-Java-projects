package fr.watchdogs.benjaminloison.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import fr.watchdogs.benjaminloison.api.DrinkLists;
import fr.watchdogs.benjaminloison.api.DrinkLists.Drink;
import fr.watchdogs.benjaminloison.api.PlayerContainer;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.world.BlockEvent;

public class EventSystem implements IGuiHandler
{
    private int thirstToSet;

    @SubscribeEvent
    public void playerTick(PlayerTickEvent event)
    {
        WatchDogs.proxy.serverTick(event.player);
    }

    @SubscribeEvent
    public void onLogin(PlayerLoggedInEvent event)
    {
        PlayerContainer.addPlayer(event.player);
    }

    @SubscribeEvent
    public void onLogout(PlayerLoggedOutEvent event)
    {
        PlayerContainer.removePlayer(event.player);
    }

    @SubscribeEvent
    public void onAttack(AttackEntityEvent event)
    {
        PlayerContainer playerContainer = PlayerContainer.getPlayer(event.entityPlayer);
        if(playerContainer != null)
            playerContainer.addExhaustion(0.5f);
    }

    @SubscribeEvent
    public void onHurt(LivingHurtEvent event)
    {
        if(event.entity instanceof EntityPlayer)
        {
            PlayerContainer playerContainer = PlayerContainer.getPlayer((EntityPlayer)event.entity);
            if(playerContainer != null)
                playerContainer.addExhaustion(0.4f);
        }
    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event)
    {
        PlayerContainer container = PlayerContainer.getPlayer(event.getPlayer());
        if(container != null)
            container.addExhaustion(0.03f);
    }

    @SubscribeEvent
    public void onFinishUsingItem(PlayerUseItemEvent.Finish event)
    {
        String id = event.item.getUnlocalizedName();
        for(Drink d : DrinkLists.EXTERNAL_DRINKS)
        {
            String possibleID = d.item.getUnlocalizedName();
            if(id.equals(possibleID) && event.item.getItemDamage() == d.item.getItemDamage())
            {
                PlayerContainer playCon = PlayerContainer.getPlayer(event.entityPlayer);
                playCon.addStats(d.replenish, d.saturation);
                break;
            }
        }
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerRespawnEvent event)
    {
        PlayerContainer.getPlayer(event.player).respawnPlayer();
    }

    @SubscribeEvent
    public void onSleep(PlayerSleepInBedEvent event)
    {
        PlayerContainer playerContainer = PlayerContainer.getPlayer(event.entityPlayer);
        EntityPlayer player = playerContainer.getContainerPlayer();
        if(!playerContainer.getStats().isThirstAllowedByDifficulty())
            return;
        if(WatchDogs.config.DISABLE_THIRST_LOSS_FROM_SLEEP)
            return;
        int dayLength = 24000, thirstInterval = 2000, worldTime = (int)(event.entityPlayer.worldObj.getWorldTime() % dayLength), sleepingTime = dayLength - worldTime, newThirst = playerContainer.getStats().thirstLevel - (sleepingTime / thirstInterval);
        if(!player.worldObj.isDaytime())
        {
            if(newThirst <= 8)
                event.result = EntityPlayer.EnumStatus.OTHER_PROBLEM;
            else
                thirstToSet = newThirst;
        }
    }

    @SubscribeEvent
    public void onPlayerWakeUp(PlayerWakeUpEvent event)
    {
        PlayerContainer playerContainer = PlayerContainer.getPlayer(event.entityPlayer);
        if(!playerContainer.getStats().isThirstAllowedByDifficulty())
            return;
        if(WatchDogs.config.DISABLE_THIRST_LOSS_FROM_SLEEP)
            return;
        PlayerContainer player = PlayerContainer.getPlayer(event.entityPlayer);
        player.getStats().setStats(thirstToSet, player.getStats().thirstSaturation);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return WatchDogs.proxy.getServerGui(ID, player, world, x, y, z);
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return null;
    }
}