package fr.title.benjaminloison.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;

public class RenderTickHandler
{
    int time = 0;
    boolean showing = false;

    @SubscribeEvent
    public void onRenderTick(TickEvent.ClientTickEvent event)
    {
        if(Minecraft.getMinecraft().thePlayer != null)
        {
            
        }
    }
}