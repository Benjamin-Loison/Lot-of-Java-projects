package fr.watchdogs.benjaminloison.events;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.watchdogs.benjaminloison.common.WatchDogs;

public class CommonTickHandler
{
    public CommonTickHandler()
    {
        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void tick(TickEvent.ServerTickEvent event)
    {
        switch(event.phase)
        {
            case START:
                break;
            case END:
            {
                WatchDogs.playerHandler.serverTick();
                WatchDogs.ticker++;
                break;
            }
        }
    }
}