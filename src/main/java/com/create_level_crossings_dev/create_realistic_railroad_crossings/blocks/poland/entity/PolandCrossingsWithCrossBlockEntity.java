package com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.entity;

import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.PolandCrossingsWithCross1Block;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.PolandCrossingsWithCross2Block;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.state_properties.CRRCBlockStateProperties;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.state_properties.DualLightStatus;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.registry.CRRCBlockEntities;
import com.simibubi.create.content.trains.entity.TrainStatus;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PolandCrossingsWithCrossBlockEntity extends BlockEntity {

	private static int tickcounter = 0;

	private boolean powered;

	public PolandCrossingsWithCrossBlockEntity(BlockPos pos, BlockState state) {
		super(CRRCBlockEntities.Poland.POLAND_CROSSING_WITH_CROSS.get(), pos, state);
	}

	public static void tick(Level level, BlockPos pos, BlockState state, PolandCrossingsWithCrossBlockEntity be) {
		if (tickcounter == 20) {
			tickcounter = 0;
		}
		tickcounter++;

		if (!be.isPowered()) {
			state.setValue(CRRCBlockStateProperties.DUAL_LIGHT_STATUS, DualLightStatus.BOTH_OFF);
			return;
		}
		if (tickcounter % 10 == 0) {
			if (state.getValue(CRRCBlockStateProperties.DUAL_LIGHT_STATUS) == DualLightStatus.LEFT_LIGHT_ON) {
				state.setValue(CRRCBlockStateProperties.DUAL_LIGHT_STATUS, DualLightStatus.RIGHT_LIGHT_ON);
			} else if (state.getValue(CRRCBlockStateProperties.DUAL_LIGHT_STATUS) == DualLightStatus.RIGHT_LIGHT_ON) {
				state.setValue(CRRCBlockStateProperties.DUAL_LIGHT_STATUS, DualLightStatus.LEFT_LIGHT_ON);
			} else {
				state.setValue(CRRCBlockStateProperties.DUAL_LIGHT_STATUS, DualLightStatus.LEFT_LIGHT_ON);
			}
		}
		if (tickcounter % 20 == 0) {
			//TODO: Add audio for crossing gates.
		}

	}

	public void updateRedstone() {
		assert level != null;
		boolean newPowered = level.hasNeighborSignal(worldPosition);

		if (newPowered != powered) {
			powered = newPowered;
			setChanged();
		}
	}

	public boolean isPowered() {
		return powered;
	}

}