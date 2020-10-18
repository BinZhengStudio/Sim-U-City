package com.github.vvvbbbcz.simucity.world.building;

import com.github.vvvbbbcz.simucity.util.FileUtil;
import com.google.gson.Gson;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraft.util.datafix.DefaultTypeReferences;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.commons.io.filefilter.SuffixFileFilter;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class Reader implements Serializable {
	private static final long serialVersionUID = 1145140011451400002L;

	public static void readAll() {
		Building.getBuildingList().clear();
		File[] buildingFiles = FileUtil.getBuildingsFolder().listFiles((FileFilter) new SuffixFileFilter("building"));
		if (buildingFiles == null) {
			// TODO Download the building files from our website.
		}
		for (File zipFile : buildingFiles) {
			BuildingInformation information = null;
			Template template = null;
			try {
				ZipInputStream stream = new ZipInputStream(new FileInputStream(zipFile));
				ZipEntry entry;
				while ((entry = stream.getNextEntry()) != null) {
					if (entry.getName().endsWith(".json")) {
						ZipFile file = new ZipFile(zipFile);
						BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(entry)));
						information = new Gson().fromJson(reader, BuildingInformation.class);
					}
					if (entry.getName().endsWith(".nbt")) {
						ZipFile file = new ZipFile(zipFile);
						CompoundNBT compoundnbt = CompressedStreamTools.readCompressed(file.getInputStream(entry));
						template = new Template();
						template.read(NBTUtil.update(DataFixesManager.getDataFixer(), DefaultTypeReferences.STRUCTURE, compoundnbt, compoundnbt.getInt("DataVersion")));
					}
				}

				if (information != null && template != null) {
					Building.getBuildingList().add(new Building(information, template));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
