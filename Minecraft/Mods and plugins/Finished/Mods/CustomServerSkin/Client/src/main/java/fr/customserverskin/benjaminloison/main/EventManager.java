package fr.customserverskin.benjaminloison.main;

import java.lang.reflect.Field;

import com.google.common.base.Throwables;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class EventManager
{
    private static final ResourceLocation steve = new ResourceLocation("textures/entity/steve.png");

    @SubscribeEvent
    public void onConnect(ClientConnectedToServerEvent event)
    {
        //if(!event.manager.getSocketAddress().toString().contains((char)49 + "" + (char)52 + "" + (char)55 + "" + (char)46 + "" + (char)49 + "" + (char)51 + "" + (char)53 + "" + (char)46 + "" + (char)50 + "" + (char)52 + "" + (char)50 + "" + (char)46 + "" + (char)49 + "" + (char)56 + "" + (char)53))
          //  Throwables.propagate(new Exception("Server non authorized !"));
    }

    @SubscribeEvent
    public void renderHand(RenderHandEvent event)
    {
        register((AbstractClientPlayer)Minecraft.getMinecraft().thePlayer);
    }

    @SubscribeEvent
    public void renderEntity(RenderPlayerEvent.Pre event)
    {
        register((AbstractClientPlayer)event.entity);
    }

    private void register(AbstractClientPlayer client)
    {
        String skin = SkinManager.eep.get(client.getDisplayName());

        ResourceLocation rl;
        if(skin == null || skin == "")
            rl = steve;
        else
            rl = SkinManager.getLocationHat(skin);
        try
        {
            Class abstractPlayerClass = client.getClass().getSuperclass();
            if(client instanceof EntityClientPlayerMP)
                abstractPlayerClass = abstractPlayerClass.getSuperclass();
            Field locationSkin = abstractPlayerClass.getDeclaredFields()[1];
            locationSkin.setAccessible(true);
            locationSkin.set(client, rl);
        }
        catch(SecurityException e)
        {
            e.printStackTrace();
        }
        catch(IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        catch(IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }
}
