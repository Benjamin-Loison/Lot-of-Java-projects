package com.minefus.degraduck.events;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;

public class MimibioteKeyHandler
{
    public static final int CUSTOM_INV = 0;
    private static final String[] desc = {"key.mimibioteinventory.desc"};
    private static final int[] keyValues = {37};
    private final KeyBinding[] keys;

    public MimibioteKeyHandler()
    {
        keys = new KeyBinding[desc.length];
        for(int i = 0; i < desc.length; i++)
        {
            keys[i] = new KeyBinding(desc[i], keyValues[i], "key.mimibiote.category");
            ClientRegistry.registerKeyBinding(keys[i]);
        }
    }
}
