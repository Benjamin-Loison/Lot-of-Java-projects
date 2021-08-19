package fr.realfaction.benjaminloison.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommonProxy {
	public void registerRender() {
		System.out.println("Méthode côté serveur");
	}

	public static String parseTime(long ticks) {
		long gameTime = ticks;
		long hours = gameTime / 1000 + 6;
		long minutes = (gameTime % 1000) * 60 / 1000;
		if (hours >= 24) {
			hours = 0;
		}

		String mm = "0" + minutes;
		mm = mm.substring(mm.length() - 2, mm.length());

		return hours + ":" + mm;
	}

	public static String getDay(long time) {
		long days = time / 24000 + 1;
		return "Jour " + String.valueOf(days);
	}

	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		switch (ID) {

		case 0:

			return null;

		}

		return null;

	}

	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		switch (ID) {

		case 0:
			return null;

		}

		return null;

	}
}