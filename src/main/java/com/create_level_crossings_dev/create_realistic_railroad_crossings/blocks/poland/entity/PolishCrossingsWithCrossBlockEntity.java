package com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.entity;

import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.state_properties.CRRCBlockStateProperties;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.state_properties.DualLightStatus;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.registry.CRRCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.obj.ObjLoader;
import net.neoforged.neoforge.client.model.obj.ObjMaterialLibrary;
import org.jetbrains.annotations.NotNull;

public class PolishCrossingsWithCrossBlockEntity extends BlockEntity {

	private boolean audioOn = false;

	private static final int CYCLE_LENGTH = 20;

	/**
	 * Starts at 20 so the first powered tick becomes 1.
	 */
	private int tickCounter = CYCLE_LENGTH;

	private boolean powered;

	public PolishCrossingsWithCrossBlockEntity(
		BlockPos pos,
		BlockState state
	) {
		super(
			CRRCBlockEntities.Polish.POLISH_CROSSING_WITH_CROSS.get(),
			pos,
			state
		);
	}

	public static void tick(
		Level level,
		BlockPos pos,
		@NotNull BlockState state,
		@NotNull PolishCrossingsWithCrossBlockEntity be
	) {

		be.updateRedstone();

		DualLightStatus currentStatus =
			state.getValue(CRRCBlockStateProperties.DUAL_LIGHT_STATUS);

		if (!be.isPowered()) {

			be.tickCounter = CYCLE_LENGTH;

			if (currentStatus != DualLightStatus.BOTH_OFF) {

				level.setBlock(
					pos,
					state.setValue(
						CRRCBlockStateProperties.DUAL_LIGHT_STATUS,
						DualLightStatus.BOTH_OFF
					),
					3
				);
			}
			if (be.audioOn) {
				//TODO: Sound disable
				be.audioOn = false;
			}

			return;
		}

		be.tickCounter++;

		if (be.tickCounter > CYCLE_LENGTH) {
			be.tickCounter = 1;
		}

		DualLightStatus targetStatus =
			getTargetStatus(be.tickCounter);

		if (targetStatus != currentStatus) {

			level.setBlock(
				pos,
				state.setValue(
					CRRCBlockStateProperties.DUAL_LIGHT_STATUS,
					targetStatus
				),
				3
			);
		}

		// TODO: Crossing bell / gate audio
	}

	private static @NotNull DualLightStatus getTargetStatus(
		int tickCounter
	) {

		// LEFT SIDE
		if (tickCounter <= 10) {

			if (tickCounter == 1 || tickCounter == 9) {
				return DualLightStatus.LEFT_LIGHT_DIM;
			}

			if (tickCounter == 10) {
				return DualLightStatus.BOTH_OFF;
			}

			return DualLightStatus.LEFT_LIGHT_ON;
		}

		// RIGHT SIDE
		if (tickCounter == 11 || tickCounter == 19) {
			return DualLightStatus.RIGHT_LIGHT_DIM;
		}

		if (tickCounter == 20) {
			return DualLightStatus.BOTH_OFF;
		}

		return DualLightStatus.RIGHT_LIGHT_ON;
	}

	public void updateRedstone() {

		assert level != null;

		boolean newPowered =
			level.hasNeighborSignal(worldPosition);

		if (newPowered != powered) {

			powered = newPowered;

			setChanged();
		}
	}

	public boolean isPowered() {
		return powered;
	}
}