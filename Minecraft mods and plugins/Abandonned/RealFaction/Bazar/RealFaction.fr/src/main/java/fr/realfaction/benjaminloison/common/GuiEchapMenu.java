package fr.realfaction.benjaminloison.common;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;

@SideOnly(Side.CLIENT)
public class GuiEchapMenu extends GuiScreen {
	private static final Logger logger = LogManager.getLogger();
	private int field_146445_a;
	private int field_146444_f;

	public void initGui() {
		this.field_146445_a = 0;
		this.buttonList.clear();
		byte b0 = -16;
		boolean flag = true;
		this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96 + b0,
				I18n.format("menu.returnToMenu", new Object[0])));

		if (!this.mc.isIntegratedServerRunning()) {
			((GuiButton) this.buttonList.get(0)).displayString = I18n.format("menu.disconnect", new Object[0]);
		}

		this.buttonList.add(new GuiButton(4, this.width / 2 - 100, this.height / 4 + 24 + b0,
				I18n.format("menu.returnToGame", new Object[0])));
		this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 48 + b0, 200, 20,
				I18n.format("menu.options", new Object[0])));
		this.buttonList.add(new GuiButton(6, this.width / 2 - 100, this.height / 4 + 72 + b0, 200, 20,
				I18n.format("TeamSpeak 3", new Object[0])));
		this.buttonList.add(new GuiButton(7, this.width / 2 - 100, this.height / 4 + 120 + b0, 200, 20,
				I18n.format("Site", new Object[0])));
	}

	protected void actionPerformed(GuiButton btn) {
		switch (btn.id) {
		case 0:
			this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
			break;
		case 1:
			btn.enabled = false;
			this.mc.theWorld.sendQuittingDisconnectingPacket();
			this.mc.loadWorld((WorldClient) null);
			this.mc.displayGuiScreen(new GuiMainMenu());
			break;
		case 2:
		case 3:
		default:
			break;
		case 4:
			this.mc.displayGuiScreen((GuiScreen) null);
			this.mc.setIngameFocus();
			break;
		case 6:
			
			try {
				Class oclass = Class.forName("java.awt.Desktop");
				Object object = oclass.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
				oclass.getMethod("browse", new Class[] { URI.class }).invoke(object,
						new Object[] { new URI("ts3server://ts.altiscraft.fr?port=9987") });
			} catch (Throwable throwable) {
				logger.error("Couldn't open link", throwable);

			}
			break;
		case 7:		
			try {
				Class oclass = Class.forName("java.awt.Desktop");
				Object object = oclass.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
				oclass.getMethod("browse", new Class[] { URI.class }).invoke(object,
						new Object[] { new URI("http://www.altiscraft.fr/") });
			} catch (Throwable throwable) {
				logger.error("Couldn't open link", throwable);
			}
			break;
		case 8:
				try {
					Class oclass = Class.forName("java.awt.Desktop");
					Object object = oclass.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
					oclass.getMethod("browse", new Class[] { URI.class }).invoke(object,
							new Object[] { new URI("http://forum.altiscraft.fr") });
				} catch (Throwable throwable) {
					logger.error("Couldn't open link", throwable);
				}
			}
		}

	public void updateScreen() {
		super.updateScreen();
		++this.field_146444_f;
	}

	public void drawScreen(int x, int y, float z) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, I18n.format("menu.game", new Object[0]), this.width / 2, 40,
				16777215);
		this.drawCenteredString(this.fontRendererObj, I18n.format(ModRealFaction.proxy.getDay(Minecraft.getMinecraft().theWorld.getTotalWorldTime()) + " - Heure ", new Object[0]) + ModRealFaction.instance.proxy.parseTime(Minecraft.getMinecraft().theWorld.getWorldTime()), this.width / 2, 40 + 10,
				16777215);
		super.drawScreen(x, y, z);
	}
}