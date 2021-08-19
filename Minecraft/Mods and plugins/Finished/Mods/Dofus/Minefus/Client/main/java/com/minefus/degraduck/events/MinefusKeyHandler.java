package com.minefus.degraduck.events;

import com.minefus.degraduck.common.Minefus;
import com.minefus.degraduck.packets.OpenGuiPacket;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;

public class MinefusKeyHandler
{
    public static final int CUSTOM_INV = 0;
    private static final String[] desc = {"key.minefusinventory.desc"};
    private static final int[] keyValues = {34};
    private final KeyBinding[] keys;

    public MinefusKeyHandler()
    {
        keys = new KeyBinding[desc.length];
        for(int i = 0; i < desc.length; i++)
        {
            keys[i] = new KeyBinding(desc[i], keyValues[i], "key.minefus.category");
            ClientRegistry.registerKeyBinding(keys[i]);
        }
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
        if((!FMLClientHandler.instance().isGUIOpen(GuiChat.class)) && (keys[0].isPressed()))
            Minefus.PACKET_PIPELINE.sendToServer(new OpenGuiPacket(Minefus.GUI_MINEFUS_INV));
    }
}