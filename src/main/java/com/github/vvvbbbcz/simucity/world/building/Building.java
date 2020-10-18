package com.github.vvvbbbcz.simucity.world.building;

import net.minecraft.world.gen.feature.template.Template;

import java.util.ArrayList;
import java.util.List;

public class Building {
	private static final List<Building> BUILDINGS = new ArrayList<>();
	private final BuildingInformation information;
	private final Template template;

	public Building(BuildingInformation information, Template template) {
		this.information = information;
		this.template = template;
	}

	public static List<Building> getBuildingList() {
		return BUILDINGS;
	}

	public BuildingInformation getInformation() {
		return this.information;
	}

	public Template getTemplate() {
		return this.template;
	}
}
