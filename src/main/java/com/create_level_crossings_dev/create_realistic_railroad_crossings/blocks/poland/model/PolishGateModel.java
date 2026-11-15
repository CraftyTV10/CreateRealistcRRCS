package com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.model;

import com.create_level_crossings_dev.create_realistic_railroad_crossings.CRRCUtils;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.PolishGateBlock;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.PolishGateLongBlock;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.entity.PolishGateBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

import java.io.InvalidClassException;

public class PolishGateModel extends GeoModel<PolishGateBlockEntity> {
    private boolean isLongModel(PolishGateBlockEntity animatable) throws InvalidClassException {
        if(animatable.getBlockState().getBlock() instanceof PolishGateLongBlock) {
            return true;
        } else if (animatable.getBlockState().getBlock() instanceof PolishGateBlock) {
            return false;
        } else throw new InvalidClassException("This String class is not supported here.");
    }
    @Override
    public ResourceLocation getModelResource(PolishGateBlockEntity animatable){
        try {
            if (isLongModel(animatable)) {
                return ResourceLocation.fromNamespaceAndPath(CRRCUtils.MODID, "geo/block/polish_gate_long.geo.json");
            } else {
                return ResourceLocation.fromNamespaceAndPath(CRRCUtils.MODID, "geo/block/polish_gate.geo.json");
            }
        } catch (InvalidClassException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResourceLocation getTextureResource(PolishGateBlockEntity animatable) {
        try {
            if (isLongModel(animatable)) {
                return ResourceLocation.fromNamespaceAndPath(CRRCUtils.MODID, "textures/block/polish_gate_long.png");
            } else {
                return ResourceLocation.fromNamespaceAndPath(CRRCUtils.MODID, "textures/block/polish_gate.png");
            }
        } catch (InvalidClassException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResourceLocation getAnimationResource(PolishGateBlockEntity animatable) {
        try {
            if (isLongModel(animatable)) {
                return ResourceLocation.fromNamespaceAndPath(CRRCUtils.MODID, "animations/block/polish_gate_long.animation.json");
            } else {
                return ResourceLocation.fromNamespaceAndPath(CRRCUtils.MODID, "animations/block/polish_gate.animation.json");
            }
        } catch (InvalidClassException e) {
            throw new RuntimeException(e);
        }
    }
}