package com.github.vvvbbbcz.simucity.block;

import com.github.vvvbbbcz.simucity.SimUCity;
import net.minecraft.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SUCBlocks {
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, SimUCity.MODID);
	public static final Block CITY_BOX = register("city_box", new CityBoxBlock());

	private static <T extends Block> T register(String name, T block) {
		BLOCKS.register(name, () -> block);
		return block;
	}
}
