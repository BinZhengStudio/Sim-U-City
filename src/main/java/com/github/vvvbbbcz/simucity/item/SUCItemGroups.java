package com.github.vvvbbbcz.simucity.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class SUCItemGroups {
	public static final ItemGroup SIM_U_CITY = new ItemGroup("sim_u_city") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(SUCItems.CITY_BOX);
		}
	};
}
