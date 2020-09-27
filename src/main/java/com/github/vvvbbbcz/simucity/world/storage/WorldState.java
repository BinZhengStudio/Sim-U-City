package com.github.vvvbbbcz.simucity.world.storage;

import com.github.vvvbbbcz.simucity.world.GameMode;

import java.util.ArrayList;

public class WorldState {
	private static GameMode gameMode;

	public static void setGameMode(GameMode mode) {
		gameMode = mode;
		saveStates();
	}

	public static GameMode getGameMode() {
		return gameMode;
	}

	public static void saveStates() {
		ArrayList<String> data = new ArrayList<>();
		data.add("gamemode=" + gameMode.getName());
		WorldData.saveData("worldstate.bz", data);
	}
}
