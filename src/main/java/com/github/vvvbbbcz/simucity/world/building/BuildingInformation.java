package com.github.vvvbbbcz.simucity.world.building;

import net.minecraft.util.math.BlockPos;

public class BuildingInformation {
	private final String name;
	private final String credit;
//	private final BlockPos livingPos;

	public BuildingInformation(String name, String credit) {
		this.name = name;
		this.credit = credit;
//		this.livingPos = livingPos;
	}

	public String getName() {
		return this.name;
	}

	public String getCredit() {
		return this.credit;
	}

//	public BlockPos getLivingPos() {
//		return this.livingPos;
//	}
}
