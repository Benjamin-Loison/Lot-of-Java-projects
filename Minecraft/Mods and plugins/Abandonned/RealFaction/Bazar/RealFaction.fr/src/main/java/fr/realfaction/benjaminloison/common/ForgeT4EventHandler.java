package fr.realfaction.benjaminloison.common;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.passive.EntityPig;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class ForgeT4EventHandler {
	
	private int timer;

	@SubscribeEvent
	public void onLivingDeathEvent(LivingDeathEvent event) {
		if (event.entity instanceof EntityPig) {
			if (!event.entity.worldObj.isRemote) {
					EntityPig cochon = new EntityPig(event.entity.worldObj);
					cochon.setPosition(event.entity.lastTickPosX, event.entity.lastTickPosY, event.entity.lastTickPosZ);
					event.entity.worldObj.spawnEntityInWorld(cochon);
			}
		}
	}

	@SubscribeEvent
	public void onTickEvent(TickEvent event)
	{
			this.timer++;
	}
}
