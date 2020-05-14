package fr.realfaction.benjaminloison.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonLanguage;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;

@SideOnly(Side.CLIENT)
public class GuiCustomMainMenu extends GuiScreen implements GuiYesNoCallback {
	private static final Logger logger = LogManager.getLogger();
	private static final Random rand = new Random();
	private float updateCounter;
	private String splashText;
	private GuiButton buttonResetDemo;
	private int panoramaTimer;
	private DynamicTexture viewportTexture;
	private final Object field_104025_t = new Object();
	private String field_92025_p;
	private String field_146972_A;
	private String field_104024_v;
	private static final ResourceLocation splashTexts = new ResourceLocation("stationplus", "info/infos.txt");
	public static final String field_96138_a = "Please click " + EnumChatFormatting.UNDERLINE + "here"
			+ EnumChatFormatting.RESET + " for more information.";
	private int field_92024_r;
	private int field_92023_s;
	private int field_92022_t;
	private int field_92021_u;
	private int field_92020_v;
	private int field_92019_w;
	private ResourceLocation field_110351_G;
	private final ResourceLocation backGround = new ResourceLocation("realfaction", "textures/gui/factions.png");

	public GuiCustomMainMenu() {
		this.field_146972_A = field_96138_a;
		this.splashText = "";
		BufferedReader bufferedreader = null;

		try {
			ArrayList arraylist = new ArrayList();
			bufferedreader = new BufferedReader(new InputStreamReader(
					Minecraft.getMinecraft().getResourceManager().getResource(splashTexts).getInputStream(),
					Charsets.UTF_8));

			String s;
			while ((s = bufferedreader.readLine()) != null) {
				s = s.trim();

				if (!s.isEmpty()) {
					arraylist.add(s);
				}
			}

			if (!arraylist.isEmpty()) {
				do {
					this.splashText = ((String) arraylist.get(rand.nextInt(arraylist.size())));
				} while (this.splashText.hashCode() == 125780783);
			}

			if (bufferedreader != null) {
				try {
					bufferedreader.close();
				} catch (IOException localIOException) {
				}
			}

			this.updateCounter = rand.nextFloat();
		} catch (IOException localIOException1) {
		} finally {
			if (bufferedreader != null) {
				try {
					bufferedreader.close();
				} catch (IOException localIOException3) {
				}
			}
		}

		this.field_92025_p = "";

		if ((!GLContext.getCapabilities().OpenGL20) && (!OpenGlHelper.func_153193_b())) {
			this.field_92025_p = I18n.format("title.oldgl1", new Object[0]);
			this.field_146972_A = I18n.format("title.oldgl2", new Object[0]);
			this.field_104024_v = "https://help.mojang.com/customer/portal/articles/325948?ref=game";
		}
	}

	public void updateScreen() {
		this.panoramaTimer += 1;
	}

	public boolean doesGuiPauseGame() {
		return false;
	}

	protected void keyTyped(char p_73869_1_, int p_73869_2_) {
	}

	public void initGui() {
		this.viewportTexture = new DynamicTexture(256, 256);
		this.field_110351_G = this.mc.getTextureManager().getDynamicTextureLocation("background", this.viewportTexture);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		if ((calendar.get(2) + 1 == 11) && (calendar.get(5) == 9)) {
			this.splashText = "Happy birthday, ez!";
		} else if ((calendar.get(2) + 1 == 6) && (calendar.get(5) == 1)) {
			this.splashText = "Happy birthday, Notch!";
		} else if ((calendar.get(2) + 1 == 12) && (calendar.get(5) == 24)) {
			this.splashText = "Merry X-mas!";
		} else if ((calendar.get(2) + 1 == 1) && (calendar.get(5) == 1)) {
			this.splashText = "Happy new year!";
		} else if ((calendar.get(2) + 1 == 10) && (calendar.get(5) == 31)) {
			this.splashText = "OOoooOOOoooo! Spooky!";
		} else if ((calendar.get(2) + 1 == 7) && (calendar.get(5) == 6)) {
			this.splashText = "Joyeux anniversaire Zedokf_DrM !";
		}

		boolean flag = true;
		int i = this.height / 4 + 48;

		if (this.mc.isDemo()) {
			addDemoButtons(i, 24);
		} else {
			addSingleplayerMultiplayerButtons(i, 24);
		}

		this.buttonList.add(new GuiButton(0, this.width - 100, 2, 98, 20,
				I18n.format("menu.options", new Object[0])));
		this.buttonList.add(
				new GuiButton(4, this.width - 100, this.height - 22, 98, 20, I18n.format("menu.quit", new Object[0])));
		this.buttonList.add(new GuiButtonLanguage(5, 2, 2));
		Object object = this.field_104025_t;

		synchronized (this.field_104025_t) {
			this.field_92023_s = this.fontRendererObj.getStringWidth(this.field_92025_p);
			this.field_92024_r = this.fontRendererObj.getStringWidth(this.field_146972_A);
			int j = Math.max(this.field_92023_s, this.field_92024_r);
			this.field_92022_t = ((this.width - j) / 2);
			this.field_92021_u = (((GuiButton) this.buttonList.get(0)).yPosition - 24);
			this.field_92020_v = (this.field_92022_t + j);
			this.field_92019_w = (this.field_92021_u + 24);
		}
	}

	private void addSingleplayerMultiplayerButtons(int x, int y) {
		this.buttonList.add(new GuiButton(1, 2, this.height - 22, 98, 20, I18n.format("Site", new Object[0])));
		this.buttonList.add(new GuiButton(20, 2, this.height - 46, EnumChatFormatting.DARK_GREEN + "RealFaction"));
		this.buttonList.add(new GuiButton(30, 2, this.height - 70, EnumChatFormatting.DARK_GREEN + "RealFaction HardCore"));
		GuiButton fmlModButton = new GuiButton(6, 104, this.height - 22, "TeamSpeak 3");
		GuiButton webSiteButton = new GuiButton(21, 1000, x + y * 2, "");
		webSiteButton.width = 98;
		fmlModButton.width = 98;
		this.buttonList.add(webSiteButton);
		this.buttonList.add(fmlModButton);
	}

	private void addDemoButtons(int x, int y) {
		this.buttonList.add(new GuiButton(11, this.width / 2 - 100, x, I18n.format("menu.playdemo", new Object[0])));
		this.buttonList.add(this.buttonResetDemo = new GuiButton(12, 2, x + y * 1,
				I18n.format("menu.resetdemo", new Object[0])));
		ISaveFormat isaveformat = this.mc.getSaveLoader();
		WorldInfo worldinfo = isaveformat.getWorldInfo("Demo_World");

		if (worldinfo == null) {
			this.buttonResetDemo.enabled = false;
		}
	}

	protected void actionPerformed(GuiButton button) {
		if (button.id == 0) {
			this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
		}

		if (button.id == 5) {
			this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager()));
		}

		if (button.id == 1) {
			try {
				Class oclass = Class.forName("java.awt.Desktop");
				Object object = oclass.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
				oclass.getMethod("browse", new Class[] { URI.class }).invoke(object,
						new Object[] { new URI("http://www.altiscraft.fr/") });
			} catch (Throwable throwable) {
				logger.error("Couldn't open link", throwable);
			}
		}

		if (button.id == 2) {
			try {
				Class oclass = Class.forName("java.awt.Desktop");
				Object object = oclass.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
				oclass.getMethod("browse", new Class[] { URI.class }).invoke(object,
						new Object[] { new URI("http://forum.altiscraft.fr") });
			} catch (Throwable throwable) {
				logger.error("Couldn't open link", throwable);
			}
		}

		if (button.id == 4) {
			this.mc.shutdown();
		}

		if (button.id == 6) {
			try {
				Class oclass = Class.forName("java.awt.Desktop");
				Object object = oclass.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
				oclass.getMethod("browse", new Class[] { URI.class }).invoke(object,
						new Object[] { new URI("ts3server://ts.altiscraft.fr?port=9987") });
			} catch (Throwable throwable) {
				logger.error("Couldn't open link", throwable);
			}
		}

		if (button.id == 11) {
			this.mc.launchIntegratedServer("Demo_World", "Demo_World", DemoWorldServer.demoWorldSettings);
		}

		if (button.id == 12) {
			ISaveFormat isaveformat = this.mc.getSaveLoader();
			WorldInfo worldinfo = isaveformat.getWorldInfo("Demo_World");

			if (worldinfo != null) {
				GuiYesNo guiyesno = GuiSelectWorld.func_152129_a(this, worldinfo.getWorldName(), 12);
				this.mc.displayGuiScreen(guiyesno);
			}
		}

		if (button.id == 20) {
			FMLClientHandler.instance().connectToServerAtStartup("195.154.194.190", 25550);
		}
		
		if (button.id == 30) {
			FMLClientHandler.instance().connectToServerAtStartup("195.154.194.190", 25580);
		}

		if (button.id == 21) {
			try {
				Class oclass = Class.forName("java.awt.Desktop");
				Object object = oclass.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
				oclass.getMethod("browse", new Class[] { URI.class }).invoke(object,
						new Object[] { new URI("http://www.altiscraft.fr/Boutique(en_création)") });
			} catch (Throwable throwable) {
				logger.error("Couldn't open link", throwable);
			}
		}
	}

	public void confirmClicked(boolean p_73878_1_, int id) {
		if ((p_73878_1_) && (id == 12)) {
			ISaveFormat isaveformat = this.mc.getSaveLoader();
			isaveformat.flushCache();
			isaveformat.deleteWorldDirectory("Demo_World");
			this.mc.displayGuiScreen(this);
		} else if (id == 13) {
			if (p_73878_1_) {
				try {
					Class oclass = Class.forName("java.awt.Desktop");
					Object object = oclass.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
					oclass.getMethod("browse", new Class[] { URI.class }).invoke(object,
							new Object[] { new URI(this.field_104024_v) });
				} catch (Throwable throwable) {
					logger.error("Couldn't open link", throwable);
				}
			}

			this.mc.displayGuiScreen(this);
		}
	}

	private void renderBackGround() {
		GL11.glViewport(0, 0, 256, 256);
		this.mc.getTextureManager().bindTexture(this.backGround);
		GL11.glDisable(3553);
		GL11.glEnable(3553);
		GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		GL11.glTexParameteri(3553, 10241, 9729);
		GL11.glTexParameteri(3553, 10240, 9729);
		tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
		int k = this.width;
		int l = this.height;
		tessellator.addVertexWithUV(0.0D, 0.0D, this.zLevel, 0.0D, 0.0D);
		tessellator.addVertexWithUV(0.0D, l, this.zLevel, 0.0D, 1.0D);
		tessellator.addVertexWithUV(k, l, this.zLevel, 1.0D, 1.0D);
		tessellator.addVertexWithUV(k, 0.0D, this.zLevel, 1.0D, 0.0D);
		tessellator.draw();
	}

	public void drawScreen(int x, int y, float partialTick) {
		GL11.glDisable(3008);
		renderBackGround();
		GL11.glEnable(3008);
		Tessellator tessellator = Tessellator.instance;
		short short1 = 274;
		int k = this.width / 2 - short1 / 2;
		byte b0 = 30;
		drawGradientRect(0, 0, this.width, this.height, -2130706433, 16777215);
		drawGradientRect(0, 0, this.width, this.height, 0, Integer.MIN_VALUE);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		tessellator.setColorOpaque_I(-1);
		GL11.glPushMatrix();
		GL11.glTranslatef(this.width / 2 + 90, 70.0F, 0.0F);
		GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
		float f1 = 1.8F - MathHelper
				.abs(MathHelper.sin((float) (Minecraft.getSystemTime() % 1000L) / 1000.0F * 3.1415927F * 2.0F) * 0.1F);
		f1 = f1 * 100.0F / (this.fontRendererObj.getStringWidth(this.splashText) + 32);
		GL11.glScalef(f1, f1, f1);
		drawCenteredString(this.fontRendererObj, this.splashText, 0, -8, 65280);
		GL11.glPopMatrix();

		if ((this.field_92025_p != null) && (this.field_92025_p.length() > 0)) {
			drawRect(this.field_92022_t - 2, this.field_92021_u - 2, this.field_92020_v + 2, this.field_92019_w - 1,
					1428160512);
			drawString(this.fontRendererObj, this.field_92025_p, this.field_92022_t, this.field_92021_u, -1);
			drawString(this.fontRendererObj, this.field_146972_A, (this.width - this.field_92024_r) / 2,
					((GuiButton) this.buttonList.get(0)).yPosition - 12, -1);
		}

		super.drawScreen(x, y, partialTick);
	}

	protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
		super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
		Object object = this.field_104025_t;

		synchronized (this.field_104025_t) {
			if ((this.field_92025_p.length() > 0) && (p_73864_1_ >= this.field_92022_t)
					&& (p_73864_1_ <= this.field_92020_v) && (p_73864_2_ >= this.field_92021_u)
					&& (p_73864_2_ <= this.field_92019_w)) {
				GuiConfirmOpenLink guiconfirmopenlink = new GuiConfirmOpenLink(this, this.field_104024_v, 13, true);
				guiconfirmopenlink.func_146358_g();
				this.mc.displayGuiScreen(guiconfirmopenlink);
			}
		}
	}
}