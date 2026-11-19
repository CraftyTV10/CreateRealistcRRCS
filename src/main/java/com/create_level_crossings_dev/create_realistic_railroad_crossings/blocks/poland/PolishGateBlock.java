package com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland;


import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.entity.PolishGateBlockEntity;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.registry.CRRCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.footdablit2310.footlib.api.common.hitbox.VoxelShapeRotationUtil.rotateShape;

public class PolishGateBlock extends Block implements EntityBlock {

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public PolishGateBlock(Properties properties) {
        super(properties);

        registerDefaultState(this.stateDefinition.any()
                .setValue(POWERED, false)
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED, FACING);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new PolishGateBlockEntity(pos, state);
    }

    @Override
    protected void neighborChanged(
            @NotNull BlockState state,
            @NotNull Level level,
            @NotNull BlockPos pos,
            @NotNull Block neighborBlock,
            @NotNull BlockPos neighborPos,
            boolean movedByPiston) {

        super.neighborChanged(
                state, level, pos, neighborBlock,
                neighborPos, movedByPiston
        );

        if (!level.isClientSide) {
            boolean powered = level.hasNeighborSignal(pos);

            if (powered != state.getValue(POWERED)) {
                level.setBlock(
                        pos,
                        state.setValue(POWERED, powered),
                        Block.UPDATE_ALL
                );
            }
        }
    }

    @Override
    protected void onPlace(
            @NotNull BlockState state,
            @NotNull Level level,
            @NotNull BlockPos pos,
            @NotNull BlockState oldState,
            boolean movedByPiston) {

        super.onPlace(state, level, pos, oldState, movedByPiston);

        if (!oldState.is(state.getBlock()) && !level.isClientSide) {
            boolean powered = level.hasNeighborSignal(pos);

            if (powered != state.getValue(POWERED)) {
                level.setBlock(
                        pos,
                        state.setValue(POWERED, powered),
                        Block.UPDATE_ALL
                );
            }
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(POWERED, false);
    }

    private static <E extends BlockEntity, A extends BlockEntity> @Nullable BlockEntityTicker<A> createTickerHelper(
            BlockEntityType<A> type,
            BlockEntityType<E> checkedType,
            BlockEntityTicker<? super E> ticker
    ) {
        return checkedType == type ? (BlockEntityTicker<A>) ticker : null;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            @NotNull Level level,
            @NotNull BlockState state,
            @NotNull BlockEntityType<T> type
    ) {
        return createTickerHelper(type, CRRCBlockEntities.Polish.POLISH_GATE.get(), PolishGateBlockEntity::tick);
    }

    //TODO: Fix hitboxes
    private static final VoxelShape BASE_SHAPE = Block.box(0, 0, 0, 16, 16, 16);

    @Override
    public @NotNull VoxelShape getShape(
            @NotNull BlockState state,
            @NotNull BlockGetter level,
            @NotNull BlockPos pos,
            @NotNull CollisionContext context) {
        return rotateShape(state.getValue(FACING), BASE_SHAPE);
    }

    @Override
    public @NotNull VoxelShape getCollisionShape(
            @NotNull BlockState state,
            @NotNull BlockGetter level,
            @NotNull BlockPos pos,
            @NotNull CollisionContext context) {
        return rotateShape(state.getValue(FACING), BASE_SHAPE);
    }
}
