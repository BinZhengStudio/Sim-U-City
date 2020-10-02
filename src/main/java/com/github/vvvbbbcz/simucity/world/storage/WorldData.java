package com.github.vvvbbbcz.simucity.world.storage;

import com.github.vvvbbbcz.simucity.SimUCity;
import com.google.gson.Gson;
import net.minecraft.world.World;
import net.minecraftforge.fml.loading.FMLEnvironment;

import java.io.*;

public class WorldData implements Serializable {
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
			File worldPath = getWorldFolder(world);
			Gson gson = new Gson();

			try {
				BufferedReader reader = new BufferedReader(new FileReader(new File(worldPath, "worldstate.json")));
				WorldData.state = gson.fromJson(reader, WorldState.class);
				reader.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void saveData(World world) {
		if (!world.isRemote) {
			File worldPath = getWorldFolder(world);
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

	/**
	 * Get the folder of the world.<br>
	 * Please add {@code if(!world.isRemote)} by yourself.
	 * @param world
	 * @return the world folder
	 */
	private static File getWorldFolder(World world) {
		String worldName = world.getServer().getFolderName(); // get the world folder name
		File worldPath;
		if (FMLEnvironment.dist.isDedicatedServer()) {
			worldPath = world.getServer().getDataDirectory().toPath().resolve(worldName).resolve(SimUCity.MODID).toFile();
		} else {
			worldPath = world.getServer().getDataDirectory().toPath().resolve("saves").resolve(worldName).resolve(SimUCity.MODID).toFile();
		}

		if (!worldPath.exists()) {
			worldPath.mkdirs();
		}
		return worldPath;
	}
}
