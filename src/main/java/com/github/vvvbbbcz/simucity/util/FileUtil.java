package com.github.vvvbbbcz.simucity.util;

import com.github.vvvbbbcz.simucity.SimUCity;
import net.minecraft.world.World;
import net.minecraftforge.fml.loading.FMLEnvironment;

import java.io.File;

public class FileUtil {
	public static File getModsFolder() {
		return new File(".").toPath().resolve("mods").toFile();
	}

	public static File getSimucityFolder() {
		File folder = getModsFolder().toPath().resolve("simucity").toFile();
		if (!folder.exists()) {
			folder.mkdirs();
		}
		return folder;
	}

	public static File getBuildingsFolder() {
		File folder = getSimucityFolder().toPath().resolve("buildings").toFile();
		if (!folder.exists()) {
			folder.mkdirs();
		}
		return folder;
	}

	/**
	 * Get the folder of the world.<br>
	 * Please add {@code if(!world.isRemote)} by yourself.
	 * @param world
	 * @return the world folder
	 */
	public static File getWorldFolder(World world) {
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
