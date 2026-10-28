package com.create_level_crossings_dev.create_realistic_railroad_crossings.datagen.lang;

import com.create_level_crossings_dev.create_realistic_railroad_crossings.CRRCUtils;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.registry.CRRCBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class CRRCLanguageProvider extends LanguageProvider {
	public CRRCLanguageProvider(PackOutput output) {
		super(
			// Provided by the GatherDataEvent.
			output,
			// Your mod id.
			CRRCUtils.MODID,
			// The locale to use. You may use multiple language providers for different locales.
			"en_us"
		);
	}

	@Override
	protected void addTranslations() {
		addBlock(CRRCBlocks.Polish.POLISH_CROSSING_WITH_CROSS_1, "Polish Crossing With Cross Singe Track");
		addBlock(CRRCBlocks.Polish.POLISH_CROSSING_WITH_CROSS_2, "Polish Crossing With Cross Dual Track");
	}
}