package fr.realfaction.benjaminloison.proxy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.SideOnly;
import fr.realfaction.benjaminloison.common.GuiCustomMainMenu;
import fr.realfaction.benjaminloison.common.GuiEchapMenu;
import fr.realfaction.benjaminloison.common.GuiMort;
import fr.realfaction.benjaminloison.common.TileEntityForge;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;

public class ClientProxy extends CommonProxy {
	public static int tesrRenderId;

	@Override
	public void registerRender() {
		System.out.println("Méthode côté client");
		FMLCommonHandler.instance().bus().register(this);

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityForge.class, new TileEntityForgeSpecialRenderer());

		tesrRenderId = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new TESRInventoryRenderer());
	}
	
//	@SubscribeEvent @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT) public void onTickGuiMainMenu(TickEvent.ClientTickEvent event) {Minecraft mc = FMLClientHandler.instance().getClient();GuiScreen currentScreen = mc.currentScreen;if ((mc.currentScreen != null) && (mc.currentScreen.getClass().equals(GuiMainMenu.class))){mc.displayGuiScreen(new GuiCustomMainMenu());}}

    @SubscribeEvent @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT) public void onTickGuiF3(TickEvent.ClientTickEvent event) {Minecraft mc = FMLClientHandler.instance().getClient();GuiScreen currentScreen = mc.currentScreen;if ((mc.currentScreen != null) && (mc.currentScreen.getClass().equals(GuiIngameMenu.class))){mc.displayGuiScreen(new GuiEchapMenu());}}
    @SubscribeEvent @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT) public void onTickGuiEchap(TickEvent.ClientTickEvent event) {Minecraft mc = FMLClientHandler.instance().getClient();GuiScreen currentScreen = mc.currentScreen;if ((mc.currentScreen != null) && (mc.currentScreen.getClass().equals(GuiGameOver.class))){mc.displayGuiScreen(new GuiMort());}}

}