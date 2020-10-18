package com.github.vvvbbbcz.simucity.world.storage;

import com.github.vvvbbbcz.simucity.util.FileUtil;
import com.google.gson.Gson;
import net.minecraft.world.World;

import java.io.*;

public class WorldData implements Serializable {
	private static final long serialVersionUID = 1145140011451400001L;
	/**
	 * the WorldState
	 */
	public static WorldState state = new WorldState();

	/**
	 * the player's credits
	 */
	public static PlayerCredits playerCredits = new PlayerCredits();

	public static void loadData(World world) {
		if (!world.isRemote) {
			File worldPath = FileUtil.getWorldFolder(world);
			Gson gson = new Gson();

			try {
				BufferedReader reader = new BufferedReader(new FileReader(new File(worldPath, "worldstate.json")));
				WorldData.state = gson.fromJson(reader, WorldState.class);
				reader.close();
			} catch (IOException e) {
				WorldData.state = new WorldState();
				e.printStackTrace();
			}
		}
	}

	public static void saveData(World world) {
		if (!world.isRemote) {
			File worldPath = FileUtil.getWorldFolder(world);
			Gson gson = new Gson();

			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(new File(worldPath, "worldstate.json")));
				gson.toJson(state, writer);
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
