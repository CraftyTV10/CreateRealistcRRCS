package com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland;

import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.state_properties.CRRCBlockStateProperties;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.state_properties.DualLightStatus;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class PolandCrossingsWithCross2Block extends Block {
	public static final EnumProperty<DualLightStatus> DUAL_LIGHT_STATUS = CRRCBlockStateProperties.DUAL_LIGHT_STATUS;

	public PolandCrossingsWithCross2Block(BlockBehaviour.Properties properties) {
		super(properties);
		registerDefaultState(stateDefinition.any()
			.setValue(DUAL_LIGHT_STATUS, DualLightStatus.BOTH_OFF)
		);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		// this is where the properties are actually added to the state
		pBuilder.add(DUAL_LIGHT_STATUS);
	}
}