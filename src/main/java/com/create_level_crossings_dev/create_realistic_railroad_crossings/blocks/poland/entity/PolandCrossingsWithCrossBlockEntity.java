package com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.entity;

import com.create_level_crossings_dev.create_realistic_railroad_crossings.registry.CRRCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PolandCrossingsWithCrossBlockEntity extends BlockEntity {

	public PolandCrossingsWithCrossBlockEntity(BlockPos pos, BlockState state) {
		super(CRRCBlockEntities.Poland.POLAND_CROSSING_WITH_CROSS.get(), pos, state);

	}
}