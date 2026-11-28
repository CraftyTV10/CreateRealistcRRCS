package com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.renderer;

import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.entity.PolishSignalWithCrossBlockEntity;
import com.footdablit2310.footlib.api.rotation.RotationRenderHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class PolishSignalWithCrossRender implements BlockEntityRenderer<PolishSignalWithCrossBlockEntity> {

    public PolishSignalWithCrossRender(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(
            PolishSignalWithCrossBlockEntity blockEntity,
            float partialTick,
            PoseStack poseStack,
            MultiBufferSource bufferSource,
            int packedLight,
            int packedOverlay
    ) {
        poseStack.pushPose();
        RotationRenderHelper.apply(poseStack, blockEntity);
        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(
                blockEntity.getBlockState(),
                poseStack,
                bufferSource,
                packedLight,
                packedOverlay
        );
        poseStack.popPose();
    }
}
