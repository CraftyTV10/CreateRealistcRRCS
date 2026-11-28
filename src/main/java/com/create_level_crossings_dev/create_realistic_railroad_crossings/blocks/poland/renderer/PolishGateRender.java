package com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.renderer;

import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.entity.PolishGateBlockEntity;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.model.PolishGateModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.footdablit2310.footlib.api.rotation.RotationRenderHelper;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class PolishGateRender extends GeoBlockRenderer<PolishGateBlockEntity> {
    public PolishGateRender(BlockEntityRendererProvider.Context ignoredContext) {
        super(new PolishGateModel());
    }

    @Override
    protected void rotateBlock(Direction facing, PoseStack poseStack) {
        switch (facing) {
            case SOUTH -> poseStack.mulPose(Axis.YP.rotationDegrees(180));
            case WEST -> poseStack.mulPose(Axis.YP.rotationDegrees(90));
            case NORTH -> poseStack.mulPose(Axis.YP.rotationDegrees(0));
            case EAST -> poseStack.mulPose(Axis.YP.rotationDegrees(270));
        }

        // Apply the user rotation around the configured block pivot.
        poseStack.translate(-0.5, 0.0, -0.5);
        RotationRenderHelper.apply(poseStack, animatable);
        poseStack.translate(0.5, 0.0, 0.5);

        // Preserve the existing 2-pixel right offset relative to the facing.
        poseStack.translate(-0.125, 0.0, 0.0);
    }

    @Override
    protected Direction getFacing(PolishGateBlockEntity block) {
        BlockState blockState = block.getBlockState();

        if (blockState.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
            return blockState.getValue(BlockStateProperties.HORIZONTAL_FACING);
        }

        return Direction.NORTH;
    }
}
