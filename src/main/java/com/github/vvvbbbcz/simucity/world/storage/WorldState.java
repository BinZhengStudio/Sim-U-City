package com.github.vvvbbbcz.simucity.world.storage;

import com.github.vvvbbbcz.simucity.world.GameMode;
import net.minecraft.client.resources.I18n;

public class WorldState {
	private int gamemode;
	private int worldday;

	public WorldState() {
		this.gamemode = -1;
		this.worldday = -1;
	}

	public WorldState(GameMode gamemode, int worldday) {
		this.gamemode = gamemode.getId();
		this.worldday = worldday;
	}

	public GameMode getGamemode() {
		return GameMode.getModeFromId(this.gamemode);
	}

	public void setGamemode(GameMode gamemode) {
		this.gamemode = gamemode.getId();
	}

	public int getWorldday() {
		return this.worldday;
	}

	public void setWorldday(int day) {
		this.worldday = day;
	}

	public String getDayOfWeek() {
		int week = this.worldday % 7;
		switch (week) {
			case 1:
				return I18n.format("text.week.sunday");
			case 2:
				return I18n.format("text.week.monday");
			case 3:
				return I18n.format("text.week.tuesday");
			case 4:
				return I18n.format("text.week.wednesday");
			case 5:
				return I18n.format("text.week.thursday");
			case 6:
				return I18n.format("text.week.friday");
			case 0:
				return I18n.format("text.week.saturday");
			default:
				return "Unknown";
		}
	}
}
