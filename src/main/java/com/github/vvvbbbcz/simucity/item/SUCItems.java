package com.github.vvvbbbcz.simucity.item;

import com.github.vvvbbbcz.simucity.SimUCity;
import com.github.vvvbbbcz.simucity.block.SUCBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SUCItems {
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, SimUCity.MODID);
	public static final Item CITY_BOX = register("city_box", new BlockItem(SUCBlocks.CITY_BOX, new Item.Properties().group(SUCItemGroups.SIM_U_CITY)));

	private static <T extends Item> T register(String name, T item) {
		ITEMS.register(name, () -> item);
		return item;
	}
}
