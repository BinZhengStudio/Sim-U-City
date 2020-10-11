package com.github.vvvbbbcz.simucity.entity.ai.pathfinding;

import com.github.vvvbbbcz.simucity.util.EntityUtil;
import com.github.vvvbbbcz.simucity.util.math.BlockPosUtil;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CitizenWalkTo {
	private static final int MIN_RANGE_FOR_DIRECT_PATH = 400;
	private static final int MIN_DISTANCE = 25;
	private static final int NAVIGATE_RANGE = 3;
	private final MobEntity entity;
	private final List<BlockPos> posList = new ArrayList<>();
	private BlockPos currentPos;
	private BlockPos target;

	protected CitizenWalkTo(MobEntity entity) {
		this.entity = entity;
	}

	public boolean walkToBlock(@Nonnull BlockPos target, int range) {
		return walkToBlock(target, range, true);
	}

	public boolean walkToBlock(@Nonnull BlockPos target, int range, boolean onMove) {
		if (!target.equals(this.target)) {
			this.resetPosList();
			this.target = target;
		}

		double distanceToPath = BlockPosUtil.getDistanceSq(entity.getPosition(), target);

		if (distanceToPath <= MIN_RANGE_FOR_DIRECT_PATH) {
			if (distanceToPath <= MIN_DISTANCE) {
				currentPos = null;
			} else {
				currentPos = target;
			}

			posList.clear();
			return this.takeTheDirectPath(target, range, onMove);
		}

		if (currentPos == null) {
			currentPos = this.fillPosList(target, distanceToPath);
		}

		double distanceToPos = BlockPosUtil.getDistanceSq(entity.getPosition(), currentPos);
		double distanceToNextPos = posList.isEmpty() ? BlockPosUtil.getDistanceSq(entity.getPosition(), target) : BlockPosUtil.getDistanceSq(entity.getPosition(), posList.get(0));
		double distancePosNextPos = posList.isEmpty() ? BlockPosUtil.getDistanceSq(currentPos, target) : BlockPosUtil.getDistanceSq(currentPos, posList.get(0));

		if (distanceToPos < MIN_DISTANCE || distanceToNextPos < distancePosNextPos) {
			if (posList.isEmpty()) {
				currentPos = target;
				return this.takeTheDirectPath(target, range, onMove);
			}

			entity.getNavigator().clearPath();
			currentPos = posList.get(0);
			posList.remove(0);
		}

		// Only walk to block
		if (currentPos != null) {
			isLivingAtSiteWithMove(entity, currentPos, NAVIGATE_RANGE);
		}

		return !onMove;
	}

	public List<BlockPos> getPosList() {
		return new ArrayList<>(posList);
	}

	public void addToPosList(BlockPos pos) {
		posList.add(pos);
	}

	public boolean isLivingAtSiteWithMove(MobEntity entity, BlockPos pos, int range) {
		if (!EntityUtil.isEntityAtSiteWithMove(entity, pos, range)) {
			EntityUtil.tryToMoveLivingToXYZ(entity, pos, 1.0D);
			return false;
		}
		return true;
	}

	public MobEntity getEntity() {
		return entity;
	}

	private boolean takeTheDirectPath(@Nonnull BlockPos target, int range, boolean onMove) {
		boolean arrived;
		if (onMove) {
			arrived = this.isLivingAtSiteWithMove(entity, target, range) || EntityUtil.isEntityAtSite(entity, target, range + 1);
		} else {
			arrived = !EntityUtil.isEntityAtSite(entity, target, range);
		}

		if (arrived) {
			this.target = null;
		}
		return arrived;
	}

	@Nonnull
	private BlockPos fillPosList(@Nonnull BlockPos target, double distanceToPath) {
		if (!posList.isEmpty()) {
			posList.remove(0);
		}

		return this.getPos(target, entity.getPosition(), distanceToPath);
	}

	private void resetPosList() {
		currentPos = null;
		posList.clear();
	}

	@Nonnull
	protected BlockPos getPos(@Nonnull BlockPos target, @Nonnull BlockPos position, double distanceToPath) {
		double weight = Double.MAX_VALUE;
		BlockPos posPoint = null;
		double distance = Double.MAX_VALUE;

		/*
		for (BlockPos wayPoint : getWayPoints()) {
			double simpleDistance = BlockPosUtil.getDistanceSq(position, wayPoint);
			double targetDistance = BlockPosUtil.getDistanceSq(wayPoint, target);
			double currentWeight = simpleDistance * simpleDistance + targetDistance + targetDistance;
			if (currentWeight < weight && targetDistance < distanceToPath && simpleDistance > MIN_DISTANCE && simpleDistance < distanceToPath && !posList.contains(wayPoint)) {
				posPoint = wayPoint;
				weight = currentWeight;
				distance = targetDistance;
			}
		}
		 */

		if (posList.contains(posPoint)) {
			return target;
		}

		if (posPoint != null) {
			posList.add(posPoint);

			getPos(target, posPoint, distance);

			return posList.get(0);
		}

		return target;
	}

	public BlockPos getCurrentPos() {
		return currentPos;
	}

	public void reset() {
		this.target = null;
	}
}
