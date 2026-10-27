package com.create_level_crossings_dev.create_realistic_railroad_crossings;

import com.footdablit2310.footlib.api.easy_register.FootEasyRegisterSystem;
import net.minecraft.world.item.CreativeModeTab;
import org.slf4j.Logger;

public class CRRCUtils {
	public static final String MODID = CreateRealisticRailroadCrossings.MODID;
	public static final String ABBREVIATED_MOD_NAME = CreateRealisticRailroadCrossings.ABBREVIATED_MOD_NAME;
	public static final String MODNAME = CreateRealisticRailroadCrossings.MODNAME;
	public static final String CREATIVE_MODE_TAB_NAME = MODID+"_creative_mode_tab";

	public static final FootEasyRegisterSystem REGISTRAR = CreateRealisticRailroadCrossings.REGISTRAR;

	public static final Logger LOGGER = CreateRealisticRailroadCrossings.LOGGER;

	public static String logPrefix(String location) {
		return "[" + CRRCUtils.MODNAME + "][" + location + "]";
	}
}
