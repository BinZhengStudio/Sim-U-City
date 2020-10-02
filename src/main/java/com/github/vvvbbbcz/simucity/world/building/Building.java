package com.github.vvvbbbcz.simucity.world.building;

import com.github.vvvbbbcz.simucity.SimUCity;
import com.github.vvvbbbcz.simucity.block.SUCBlocks;
import com.github.vvvbbbcz.simucity.world.GameMode;
import com.github.vvvbbbcz.simucity.world.storage.WorldData;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Building implements Serializable {
	private static final long serialVersionUID = 1145140011451400002L;
	public String displayName;
	public String type; // / residential commercial (folder name)
	public Block[] structure; // / holds a 3D array of block IDs for this
	public String description = "None";
	public Infrastructure infrastructureRequirement = null;
	public Infrastructure infrastructureProducer = null;
	// building
	public int layerCount = 0;
	public int ftbCount = 0;
	public int ltrCount = 0;
	public String dimensions = "";
	public int elevationLevel = 0;
	public BlockPos primaryXYZ; // first block placed and where control panel block is
	// located
	public BlockPos livingXYZ; // / where folks should walkto to 'live' in the house
	// or work in workplace building
	public boolean buildingComplete = false;
	public int capacity = -1; // single person or mixed couple by default (-1)
	public String buildDirection = "";
	public BlockPos lumbermillMarker = null; // Lumbermill only used as starting point
	// to search for trees, blank means use
	// primaryXYZ
	public int blocksInBuilding = 0; // calculated when loadBuilding called
	public String pk = "0"; // unique ID, used for the store id (Primary key)
	public String author = "Satscape";
	public String displayNameWithoutPK = "";
	public Float rent = 0f;
	public ArrayList<String> tennants = new ArrayList<>();
	public ArrayList<BlockPos> blockLocations = new ArrayList<>();

	public transient HashMap<ItemStack, Integer> requirements = new HashMap<>();
	public transient BlockPos conBoxLocation = null;
	private transient static ArrayList<Building> buildingsRes = new ArrayList<>();
	private transient static ArrayList<Building> buildingsCom = new ArrayList<>();
	private transient static ArrayList<Building> buildingsInd = new ArrayList<>();
	private transient static ArrayList<Building> buildingsOth = new ArrayList<>();
	private transient static ArrayList<Building> buildingsSpec = new ArrayList<>();

//	private Size size = new Size(0, 0, 0);

	private void loadStructure() {
		try {
			if (requirements != null) {
				requirements.clear();
			} else {
				requirements = new HashMap<ItemStack, Integer>();
			}

			displayNameWithoutPK = displayName;

			if (displayName.startsWith("PKID"))   // PKID123-my house.txt
			{
				int hyphen = displayName.indexOf("-");
				this.pk = displayName.substring(4, hyphen);
				displayNameWithoutPK = displayName.substring(hyphen + 1);
			}

			blocksInBuilding = 0;
			File f = new File(getModFolder() + "/buildings/"
					+ type + "/" + displayName + ".txt");

			if (!f.exists()) {
				return;
			}


			FileInputStream fstream = new FileInputStream(
					getModFolder() + "/buildings/" + type
							+ "/" + displayName + ".txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			strLine = br.readLine().toString().toLowerCase().trim(); // dimensions
			String[] d = strLine.split("x");
			// vvvbbbcz: read size
			int[] di = {0, 0, 0};
			di[0] = Integer.parseInt(d[0]);
			di[1] = Integer.parseInt(d[1]);
			di[2] = Integer.parseInt(d[2]);
			structure = new Block[di[0] * di[1] * di[2]];
			this.ltrCount = di[0];
			this.ftbCount = di[1];
			this.layerCount = di[2];
			this.dimensions = d[0] + "x" + d[1] + "x" + d[2];
			strLine = br.readLine().trim(); // key P=5:0;A=23:2; etc
			HashMap<String, Block> key = new HashMap<>();
			d = strLine.split(";");

			for (String s : d) {
				String[] k = s.split("=");
				if (!k[0].toUpperCase().contentEquals("AU") && !k[0].toUpperCase().contentEquals("DESC") && !k[0].toUpperCase().contentEquals("ELEV") && !k[0].toUpperCase().contentEquals("REQ") && !k[0].toUpperCase().contentEquals("INFREQ")) {
					String[] blockInfo = k[1].split(":");
					key.put(k[0], ForgeRegistries.BLOCKS.getValue(new ResourceLocation(blockInfo[0], blockInfo[1]))); // / P minecraft:dirt
				}

				if (k[0].toUpperCase().contentEquals("AU"))   // Author of this
				{
					// building
					// stored in key
					// AU=me;
					this.author = k[1].trim();
				} else if (k[0].toUpperCase().contentEquals("DESC"))   // Description of this
				{
					// building
					// stored in key
					// DESC=building description;
					this.description = k[1];
				} else if (k[0].toUpperCase().contentEquals("ELEV"))   // Elevation constructor box should be at
				{
					// building
					// stored in key
					// ELEV=-1
					this.elevationLevel = Integer.parseInt(k[1]);
				} else if (k[0].toUpperCase().contentEquals("REQ"))   // Elevation constructor box should be at
				{
					// building
					// stored in key
					// REQ=farm.wheat
					this.elevationLevel = Integer.parseInt(k[1]);
				} else if (k[0].toUpperCase().contentEquals("INFREQ"))   // Infrastructure that the building requires to be able to function
				{
					// building
					// stored in key
					// INF=water

					if (k[1].toLowerCase().contentEquals("water")) {
						this.infrastructureRequirement = new InfrastructureWater();
					}

					if (k[1].toLowerCase().contentEquals("electricity")) {
						this.infrastructureRequirement = new InfrastructureElectricity();
					}

				}
			}

			int acount = 0, bcount = 0;

			for (int i = 0; i < this.layerCount; i++) {
				strLine = br.readLine().trim(); // /read one layer
				// AAAABBBBCCCCaaaabbbccc
				bcount = 0;

				for (int ftb = 0; ftb < ftbCount; ftb++) {
					for (int ltr = 0; ltr < ltrCount; ltr++) {
						String ch = strLine.substring(bcount, bcount + 1);
						char cha = ch.charAt(0);

						if (ch.contentEquals("!"))   // living block
						{
							structure[acount] = SUCBlocks.livingBlock;
							//structure[acount] = "999:999";
						} else if (ch.contentEquals("$"))     // /control
						{
							// block
							structure[acount] = SUCBlocks.controlBox;
						} else if (ch.contentEquals("*")) {
							// sim-u-lightbox
							structure[acount] = SUCBlocks.lightBox;
						} else if (ch.contentEquals("+")) {
							// sim-u-lightbox
							structure[acount] = SUCBlocks.lightBox;
						} else if (ch.contentEquals("-")) {
							// sim-u-lightbox
							structure[acount] = SUCBlocks.lightBox;
						} else if (ch.contentEquals("0")) {
							// Special Block: 3
							structure[acount] = SUCBlocks.specialBlock;
						} else if (ch.contentEquals("1")) {
							// Special Block: 3
							structure[acount] = SUCBlocks.specialBlock;
						} else if (ch.contentEquals("2")) {
							// Special Block: 3
							structure[acount] = SUCBlocks.specialBlock;
						} else if (ch.contentEquals("3")) {
							// Special Block: 3
							structure[acount] = SUCBlocks.specialBlock;
						} else if (ch.contentEquals("4")) {
							// Special Block: 3
							structure[acount] = SUCBlocks.specialBlock;
						} else if (ch.contentEquals("5")) {
							// Special Block: 5
							structure[acount] = SUCBlocks.specialBlock;
						} else if (ch.contentEquals("6")) {
							// Special Block: 5
							structure[acount] = SUCBlocks.specialBlock;
						} else if (ch.contentEquals("7")) {
							// Special Block: 5
							structure[acount] = SUCBlocks.specialBlock;
						} else if (ch.contentEquals("8")) {
							// Special Block: 5
							structure[acount] = SUCBlocks.specialBlock;
						} else if ((int) cha >= 48 && (int) cha <= 57) { //special block (air, but special!)
							structure[acount] = SUCBlocks.specialBlock;
						} else {
							//BOOKMARK
							Block value = key.get(ch);
							structure[acount] = value;
							Block block = structure[acount];
							// SimukraftReloaded.log("BID="+bid);
							addToRequirements(block);
						}

						acount++;
						bcount++;

						if (!ch.contentEquals("A")) {
							blocksInBuilding++;
						}
					}
				}
			}

			br.close();
			in.close();
			this.rent = blocksInBuilding * 0.01f;
		} catch (Exception e) {
			SimUCity.LOGGER.warn("Building loadStructure() " + e.toString());
		}
	}

	private void addToRequirements(Block block) {
		int val;
		ItemStack stack = new ItemStack(block, 1);

		if (WorldData.state.getGamemode() == GameMode.NORMAL) {
			String name;

			try {
				name = stack.getDisplayName().getString();
			} catch (Exception e) {
				name = "????";
			}

			if (name.contains("planks") || name.contentEquals("cobblestone")
					|| name.contentEquals("glass") || name.contains("wool")
					|| name.contentEquals("bricks")
					|| name.contentEquals("dirt")
					|| name.contentEquals("stone bricks")
					|| name.contentEquals("fence")
					|| name.contentEquals("stone")
					|| (name.contains("wood") && !name.contains("slab") && !name.contains("door")
					&& !name.contains("stairs") && !name.contains("grass"))) { // TODO 能写出这种if语句的人，我真是服了

				Iterator<Map.Entry<ItemStack, Integer>> it = requirements.entrySet().iterator();
				boolean got = false;

				while (it.hasNext()) {
					Map.Entry<ItemStack, Integer> pairs = it.next();
					ItemStack is = pairs.getKey();

					if (is.getItem() == stack.getItem()) {
						val = pairs.getValue();
						val++;
						pairs.setValue(val);
						got = true;
						break;
					}
				}

				if (!got) {
					requirements.put(stack, 1);
				}
			}
		} else if (WorldData.state.getGamemode() == GameMode.CREATIVE) {
			return;
		} else if (WorldData.state.getGamemode() == GameMode.DIFFICULT) {
			String name;
			try {
				name = stack.getDisplayName().getString();
			} catch (Exception e) {
				name = "????";
			}
			if (!name.contains("grass") && !name.contains("bed")) {
				Iterator<Map.Entry<ItemStack, Integer>> it = requirements.entrySet().iterator();
				boolean got = false;

				while (it.hasNext()) {
					Map.Entry<ItemStack, Integer> pairs = it.next();
					ItemStack is = pairs.getKey();


					if (is.getItem() == stack.getItem()) {
						val = pairs.getValue();
						val++;
						pairs.setValue(val);
						got = true;
						break;
					}
				}

				if (!got) {
					requirements.put(stack, 1);
				}
			}
		}
	}

	private static File getModFolder() {
		File file = new File(".").toPath().resolve(SimUCity.MODID).toFile();
		if (!file.exists()) {
			file.mkdirs();
		}
		return file;
	}

//	private static class Size {
//		private final int length, width, height;
//
//		protected Size(int length, int width, int height) {
//			this.length = length;
//			this.width = width;
//			this.height = height;
//		}
//
//		public int getLength() {
//			return length;
//		}
//
//		public int getWidth() {
//			return width;
//		}
//
//		public int getHeight() {
//			return height;
//		}
//	}
}
