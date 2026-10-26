package com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland;

import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.entity.PolishCrossingsWithCrossBlockEntity;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.state_properties.CRRCBlockStateProperties;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.state_properties.DualLightStatus;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.registry.CRRCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PolishCrossingsWithCross1Block extends Block {

	public static final EnumProperty<DualLightStatus> DUAL_LIGHT_STATUS = CRRCBlockStateProperties.DUAL_LIGHT_STATUS;

	public PolishCrossingsWithCross1Block(BlockBehaviour.Properties properties) {
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

	@Override
	protected void neighborChanged(@NotNull BlockState state,
	                               Level level,
	                               @NotNull BlockPos pos,
	                               @NotNull Block neighborBlock,
	                               @NotNull BlockPos neighborPos,
	                               boolean movedByPiston) {

		if (!level.isClientSide) {
			BlockEntity be = level.getBlockEntity(pos);

			if (be instanceof PolishCrossingsWithCrossBlockEntity crossing) {
				crossing.updateRedstone();
			}
		}

		super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
	}

	private static <E extends BlockEntity, A extends BlockEntity> @Nullable BlockEntityTicker<A> createTickerHelper(
		BlockEntityType<A> type, BlockEntityType<E> checkedType, BlockEntityTicker<? super E> ticker
	) {
		return checkedType == type ? (BlockEntityTicker<A>) ticker : null;
	}

	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return createTickerHelper(type, CRRCBlockEntities.Polish.POLISH_CROSSING_WITH_CROSS.get(), PolishCrossingsWithCrossBlockEntity::tick);
	}
}