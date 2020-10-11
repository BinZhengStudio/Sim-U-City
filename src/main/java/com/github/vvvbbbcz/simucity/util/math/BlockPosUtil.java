package com.github.vvvbbbcz.simucity.util.math;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class BlockPosUtil {
	/**
	 * Get squared distance.
	 *
	 * @param pos1 the first BlockPos.
	 * @param pos2 the second BlockPos.
	 * @return the square of distance.
	 */
	public static long getDistanceSq(@Nonnull BlockPos pos1, @Nonnull BlockPos pos2) {
		long xDiff = (long) pos1.getX() - pos2.getX();
		long yDiff = (long) pos1.getY() - pos2.getY();
		long zDiff = (long) pos1.getZ() - pos2.getZ();

		long result = xDiff * xDiff + yDiff * yDiff + zDiff * zDiff;
		if (result < 0) {
			throw new IllegalStateException("max-sqrt is to high! Failure to catch overflow with " + xDiff + " | " + yDiff + " | " + zDiff);
		}
		return result;
	}

	@Nullable
	public static BlockPos scanForBlockNearPos(@Nonnull World world, @Nonnull BlockPos pos, int radiusX, int radiusY, int radiusZ, int height, Block... blocks) {
		@Nullable BlockPos closestPos = null;
		double minDistance = Double.MAX_VALUE;

		BlockPos pos1 = pos.add(radiusX, radiusY, radiusZ);
		BlockPos pos2 = pos.add(-radiusX, 0, -radiusZ);

		for (BlockPos pos3 : BlockPos.getAllInBoxMutable(pos1, pos2)) {
			if (checkHeight(world, pos3, height, blocks)) {
				if (world.getBlockState(pos3.down()).getMaterial().isSolid() || world.getBlockState(pos3.down(2)).getMaterial().isSolid()) {
					double distance = getDistanceSq(pos3, pos);
					if (closestPos == null || distance < minDistance) {
						closestPos = pos3;
						minDistance = distance;
					}
				}
			}
		}

		return closestPos;
	}

	private static boolean checkHeight(@Nonnull World world, BlockPos pos, int height, @Nonnull Block... blocks) {
		for (int i = 0; i < height; i++) {
			BlockState state = world.getBlockState(pos.up(i));
			if (!arrayContains(blocks, state.getBlock()) && state.getMaterial().blocksMovement()) {
				return false;
			}
		}
		return true;
	}

	private static boolean arrayContains(@Nonnull Object[] array, Object key) {
		for (Object o : array) {
			if (Objects.equals(key, o)) {
				return true;
			}
		}
		return false;
	}
}
