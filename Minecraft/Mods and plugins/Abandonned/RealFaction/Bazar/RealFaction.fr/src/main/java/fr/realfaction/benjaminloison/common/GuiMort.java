package fr.realfaction.benjaminloison.common;

import java.util.Iterator;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;

@SideOnly(Side.CLIENT)
public class GuiMort extends GuiScreen implements GuiYesNoCallback {
	private int field_146347_a;
	private boolean field_146346_f = false;

	public void initGui() {
		this.buttonList.clear();

		if (this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled()) {
			if (this.mc.isIntegratedServerRunning()) {
				this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96,
						I18n.format("deathScreen.deleteWorld", new Object[0])));
			} else {
				this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96,
						I18n.format("deathScreen.leaveServer", new Object[0])));
			}
		} else {
			this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 72,
					I18n.format("deathScreen.respawn", new Object[0])));
			this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96,
					I18n.format("deathScreen.titleScreen", new Object[0])));

			if (this.mc.getSession() == null) {
				((GuiButton) this.buttonList.get(1)).enabled = false;
			}
		}

		GuiButton guibutton;

		for (Iterator iterator = this.buttonList.iterator(); iterator.hasNext(); guibutton.enabled = false) {
			guibutton = (GuiButton) iterator.next();
		}
	}

	protected void keyTyped(char p_73869_1_, int p_73869_2_) {
	}

	protected void actionPerformed(GuiButton p_146284_1_) {
		switch (p_146284_1_.id) {
		case 0:
			this.mc.thePlayer.respawnPlayer();
			this.mc.displayGuiScreen((GuiScreen) null);
			break;
		case 1:
			GuiYesNo guiyesno = new GuiYesNo(this, I18n.format("deathScreen.quit.confirm", new Object[0]), "",
					I18n.format("deathScreen.titleScreen", new Object[0]),
					I18n.format("deathScreen.respawn", new Object[0]), 0);
			this.mc.displayGuiScreen(guiyesno);
			guiyesno.func_146350_a(20);
		}
	}

	public void confirmClicked(boolean p_73878_1_, int p_73878_2_) {
		if (p_73878_1_) {
			this.mc.theWorld.sendQuittingDisconnectingPacket();
			this.mc.loadWorld((WorldClient) null);
			this.mc.displayGuiScreen(new GuiMainMenu());
		} else {
			this.mc.thePlayer.respawnPlayer();
			this.mc.displayGuiScreen((GuiScreen) null);
		}
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
		this.drawGradientRect(0, 0, this.width, this.height, 1615855616, -1602211792);
		GL11.glPushMatrix();
		GL11.glScalef(2.0F, 2.0F, 2.0F);
		boolean flag = this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled();
		String s = flag ? I18n.format("deathScreen.title.hardcore", new Object[0])
				: I18n.format("deathScreen.title", new Object[0]);
		this.drawCenteredString(this.fontRendererObj, s, this.width / 2 / 2, 30, 16777215);
		GL11.glPopMatrix();

		if (flag) {
			this.drawCenteredString(this.fontRendererObj, I18n.format("deathScreen.hardcoreInfo", new Object[0]),
					this.width / 2, 144, 16777215);
		}

		this.drawCenteredString(this.fontRendererObj, I18n.format("", new Object[0]), this.width / 2, 100, 16777215);
		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	}

	public boolean doesGuiPauseGame() {
		return false;
	}

	public void updateScreen() {
		super.updateScreen();
		++this.field_146347_a;
		GuiButton guibutton;

		if (this.field_146347_a == 20) {
			for (Iterator iterator = this.buttonList.iterator(); iterator.hasNext(); guibutton.enabled = true) {
				guibutton = (GuiButton) iterator.next();
			}
		}
	}
}