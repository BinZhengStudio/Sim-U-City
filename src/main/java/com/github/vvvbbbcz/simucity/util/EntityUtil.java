package com.github.vvvbbbcz.simucity.util;

import com.github.vvvbbbcz.simucity.util.math.BlockPosUtil;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

import javax.annotation.Nonnull;

public class EntityUtil {
	public static boolean isEntityAtSite(@Nonnull LivingEntity entityLiving, BlockPos pos, int range) {
		return entityLiving.getPosition().distanceSq(new Vec3i(pos.getX(), pos.getY(), pos.getZ())) < (range * range);
	}

	public static boolean isEntityAtSiteWithMove(@Nonnull LivingEntity entity, BlockPos pos, int range) {
		if (pos.getX() == 0 && pos.getY() == 0 && pos.getZ() == 0) {
			return false;
		}

		if (!isEntityAtSite(entity, pos, 512)) {
			BlockPos spawnPoint = BlockPosUtil.scanForBlockNearPos(entity.getEntityWorld(), pos, 5, 5, 5, 2, Blocks.AIR, Blocks.CAVE_AIR, Blocks.SNOW, Blocks.TALL_GRASS);

			if (spawnPoint == null) {
				spawnPoint = pos;
			}

			entity.setLocationAndAngles(spawnPoint.getX() + 0.5D, spawnPoint.getY(), spawnPoint.getZ() + 0.5D, entity.rotationYaw, entity.rotationPitch);
			return true;
		}

		return EntityUtil.isEntityAtSite(entity, pos, range);
	}

	public static boolean tryToMoveLivingToXYZ(@Nonnull MobEntity living, BlockPos pos, double speed) {
		return living.getNavigator().tryMoveToXYZ(pos.getX(), pos.getY(), pos.getZ(), speed);
	}
}
