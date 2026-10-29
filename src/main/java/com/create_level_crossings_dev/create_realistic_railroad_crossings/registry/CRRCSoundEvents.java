package com.create_level_crossings_dev.create_realistic_railroad_crossings.registry;

import com.create_level_crossings_dev.create_realistic_railroad_crossings.CRRCUtils;
import com.footdablit2310.footlib.api.easy_register.FootEasyRegisterSystem;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

public class CRRCSoundEvents {
	private static final FootEasyRegisterSystem REGISTRAR = CRRCUtils.REGISTRAR;
	public static DeferredHolder<SoundEvent, SoundEvent> POLISH_CROSSING_WITH_CROSS;
	public static void register() {
		POLISH_CROSSING_WITH_CROSS=REGISTRAR.soundEvent().createVariableRangeEvent("polish_crossing_with_cross");
	}
}
