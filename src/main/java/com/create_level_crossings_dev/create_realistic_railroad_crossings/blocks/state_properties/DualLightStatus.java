package com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.state_properties;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum DualLightStatus implements StringRepresentable {
	// LEFT
	LEFT_LIGHT_ON,
	LEFT_LIGHT_DIM,
	// RIGHT
	RIGHT_LIGHT_ON,
	RIGHT_LIGHT_DIM,
	// BOTH
	BOTH_ON,
	BOTH_DIM,
	BOTH_OFF;

	@Override
	public @NotNull String getSerializedName() {
		return this.name().toLowerCase(Locale.ROOT);
	}
}
