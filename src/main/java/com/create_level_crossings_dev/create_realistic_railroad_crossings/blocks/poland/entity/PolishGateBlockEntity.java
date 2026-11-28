
package com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.entity;

import com.create_level_crossings_dev.create_realistic_railroad_crossings.registry.CRRCBlockEntities;
import com.footdablit2310.footlib.api.generic_base.block.entity.FootRotatableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class PolishGateBlockEntity extends FootRotatableBlockEntity implements GeoBlockEntity {

    private static final int MIN_ANIMATION_TICKS = 100;

    private int animationCooldown = 0;

    private boolean initialized = false;
    private boolean lastPowered;
    private boolean pendingPowered;
    private boolean animatedPowered;

    private boolean animationRequested = false;
    private boolean requestedOpen = false;

    private final AnimatableInstanceCache cache =
            GeckoLibUtil.createInstanceCache(this);

    private static final RawAnimation OPEN =
            RawAnimation.begin().thenPlay("Otwarcie");

    private static final RawAnimation CLOSE =
            RawAnimation.begin().thenPlay("Zamykanie");

    public PolishGateBlockEntity(BlockPos pos, BlockState state) {
        super(CRRCBlockEntities.Polish.POLISH_GATE.get(), pos, state);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(
                this, "polish_gate_controller", 0, state -> {

            if (animationRequested) {
                animationRequested = false;

                return state.setAndContinue(
                        requestedOpen ? OPEN : CLOSE
                );
            }

            return PlayState.CONTINUE;
        }));
    }

    public static void tick(
            Level level,
            BlockPos pos,
            BlockState state,
            PolishGateBlockEntity blockEntity
    ) {
        if (blockEntity.tickRotation()) {
            blockEntity.setChanged();
        }

        // Animation timing is handled on the client.
        if (!level.isClientSide) return;

        boolean powered = state.getValue(BlockStateProperties.POWERED);

        if (!blockEntity.initialized) {
            blockEntity.lastPowered = powered;
            blockEntity.pendingPowered = powered;
            blockEntity.animatedPowered = powered;
            blockEntity.initialized = true;
            return;
        }

        // Remember the latest redstone state.
        if (powered != blockEntity.lastPowered) {
            blockEntity.lastPowered = powered;
            blockEntity.pendingPowered = powered;
        }

        // Count down the cooldown.
        if (blockEntity.animationCooldown > 0) {
            blockEntity.animationCooldown--;
        }

        // Start an animation when the cooldown expires.
        if (blockEntity.animationCooldown == 0
                && blockEntity.pendingPowered != blockEntity.animatedPowered) {

            blockEntity.animatedPowered = blockEntity.pendingPowered;

            // POWERED=false means open.
            blockEntity.requestedOpen = !blockEntity.animatedPowered;

            blockEntity.animationRequested = true;
            blockEntity.animationCooldown = MIN_ANIMATION_TICKS;
        }
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag, registries);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider registries) {
        loadAdditional(tag, registries);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
