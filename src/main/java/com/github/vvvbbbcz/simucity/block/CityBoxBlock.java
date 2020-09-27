package com.github.vvvbbbcz.simucity.block;

import com.github.vvvbbbcz.simucity.client.gui.screen.ScreenHandler;
import com.github.vvvbbbcz.simucity.util.SUCSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;

import javax.annotation.Nonnull;

public class CityBoxBlock extends Block {
	public CityBoxBlock() {
		super(Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(10.0F, 1.0F));
	}

	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		worldIn.playSound((PlayerEntity) null, pos, SUCSoundEvents.CITY_BOX_ACTIVATED, SoundCategory.BLOCKS, 1.0F, 1.0F);
	}

	@Nonnull
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		worldIn.playSound(player, pos, SUCSoundEvents.COMPUTER, SoundCategory.BLOCKS, 1.0F, 1.0F);
		if (FMLEnvironment.dist == Dist.CLIENT) {
			ScreenHandler.openCityBoxScreen(player);
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.CONSUME;
	}
}
