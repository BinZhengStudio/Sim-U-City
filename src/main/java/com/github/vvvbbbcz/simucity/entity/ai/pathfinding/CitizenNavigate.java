package com.github.vvvbbbcz.simucity.entity.ai.pathfinding;

import net.minecraft.entity.MobEntity;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.world.World;

public class CitizenNavigate extends GroundPathNavigator {
	public CitizenNavigate(MobEntity entitylivingIn, World worldIn) {
		super(entitylivingIn, worldIn);
	}
}
