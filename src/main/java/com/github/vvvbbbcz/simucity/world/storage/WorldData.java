package com.github.vvvbbbcz.simucity.world.storage;

import com.github.vvvbbbcz.simucity.SimUCity;
import net.minecraft.world.World;
import net.minecraftforge.fml.loading.FMLEnvironment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class WorldData {
	private static World world;

	public static void setWorld(World worldIn) {
		world = worldIn;
	}

	public static <T> void saveData(String fileName, List<T> data) {
		if (!world.isRemote) {
			String worldName = world.getServer().getFolderName();
			File worldPath;
			if (FMLEnvironment.dist.isClient()) {
				worldPath = world.getServer().getDataDirectory().toPath().resolve("saves").resolve(worldName).resolve(SimUCity.MODID).toFile();
			} else {
				worldPath = world.getServer().getDataDirectory().toPath().resolve(worldName).resolve(SimUCity.MODID).toFile();
			}

			if (!worldPath.exists()) {
				worldPath.mkdirs();
			}

			File file = new File(worldPath, fileName);

			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				for (T line : data) {
					writer.write(line + "\r\n");
				}
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

//	public static <T> ArrayList<T> loadData(String fileName, Class<? extends T> dataType) {
//	}
}
