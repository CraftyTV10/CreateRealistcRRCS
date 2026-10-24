package com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.state_properties;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum DualLightStatus implements StringRepresentable {
	LEFT_LIGHT_ON,
	RIGHT_LIGHT_ON,
	BOTH_ON,
	BOTH_OFF;

	@Override
	public @NotNull String getSerializedName() {
		return this.name();
	}
}
