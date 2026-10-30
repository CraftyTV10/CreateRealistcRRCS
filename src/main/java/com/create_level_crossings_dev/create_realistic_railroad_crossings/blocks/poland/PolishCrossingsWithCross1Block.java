package com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland;

import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.entity.PolishCrossingsWithCrossBlockEntity;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.state_properties.CRRCBlockStateProperties;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.state_properties.DualLightStatus;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.registry.CRRCBlockEntities;
import com.footdablit2310.footlib.api.common.hitbox.VoxelShapeRotationUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Objects;

public class PolishCrossingsWithCross1Block extends Block implements EntityBlock {

	public static final EnumProperty<DualLightStatus> DUAL_LIGHT_STATUS = Objects.requireNonNull(CRRCBlockStateProperties.DUAL_LIGHT_STATUS);
	public static final EnumProperty<Direction> FACING = Objects.requireNonNull(BlockStateProperties.HORIZONTAL_FACING);


	public static final @NotNull VoxelShape BASE = Block.box(
		-5, 0, -5,
		5, 1, 5
	);

	public static final @NotNull VoxelShape POLE = Block.box(
		-1, 0, -1,
		1, 52, 1
	);

	public static final @NotNull VoxelShape HOUSING = Block.box(
		-11, 32, -4,
		11, 42, 2
	);

	public static final @NotNull VoxelShape CROSSBUCKS = Block.box(
		-10, 42, -2,
		10, 54, 0
	);

	public static final @NotNull VoxelShape SHAPE = Shapes.or(
		BASE,
		POLE,
		HOUSING,
		CROSSBUCKS
	).move(0.5, 0, 0.5);

	private static final Map<Direction, VoxelShape> ROTATED_SHAPES = VoxelShapeRotationUtil.cacheRotations(SHAPE);

	public PolishCrossingsWithCross1Block(BlockBehaviour.Properties properties) {
		super(properties);
		registerDefaultState(stateDefinition.any()
			.setValue(DUAL_LIGHT_STATUS, DualLightStatus.BOTH_OFF)
			.setValue(FACING, Direction.NORTH)
		);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		// this is where the properties are actually added to the state
		builder.add(DUAL_LIGHT_STATUS);
		builder.add(FACING);
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

	@Override
	public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
		return new PolishCrossingsWithCrossBlockEntity(pos, state);
	}

	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
		return createTickerHelper(type, CRRCBlockEntities.Polish.POLISH_CROSSING_WITH_CROSS.get(), PolishCrossingsWithCrossBlockEntity::tick);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		if (context.getHorizontalDirection().getOpposite() == Direction.NORTH) {
			return this.defaultBlockState();
		}

		return this.defaultBlockState().setValue(
			FACING,
			context.getHorizontalDirection().getOpposite()
		);
	}

	@Override
	public @NotNull VoxelShape getShape(
		BlockState state,
		@NotNull BlockGetter level,
		@NotNull BlockPos pos,
		@NotNull CollisionContext context) {

		return ROTATED_SHAPES.get(state.getValue(FACING));
	}

	@Override
	public @NotNull VoxelShape getCollisionShape(
		@NotNull BlockState state,
		@NotNull BlockGetter level,
		@NotNull BlockPos pos,
		@NotNull CollisionContext context) {
		return ROTATED_SHAPES.get(state.getValue(FACING));
	}
}