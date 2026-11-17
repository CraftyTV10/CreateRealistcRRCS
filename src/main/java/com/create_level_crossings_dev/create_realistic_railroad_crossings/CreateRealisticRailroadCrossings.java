package com.create_level_crossings_dev.create_realistic_railroad_crossings;

import com.create_level_crossings_dev.create_realistic_railroad_crossings.model.render.color.CRRCBlockColors;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.registry.CRRCBlockEntities;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.registry.CRRCBlocks;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.registry.CRRCItems;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.registry.CRRCSoundEvents;
import com.footdablit2310.footlib.api.easy_register.FootEasyRegisterSystem;
import com.footdablit2310.footlib.api.easy_register.config.TabConfig;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.fml.common.Mod;

import java.util.Optional;


@Mod(value=CreateRealisticRailroadCrossings.MODID, dist={Dist.CLIENT, Dist.DEDICATED_SERVER})
public class CreateRealisticRailroadCrossings {

    public static final String MODID = "create_realistic_railroad_crossings";
    public static final String MODNAME = "Create: Realistic Railroad Crossings";
    public static final String ABBREVIATED_MOD_NAME = "CRRC";

    public static FootEasyRegisterSystem REGISTRAR;

    public static final Logger LOGGER = LogUtils.getLogger();

    public CreateRealisticRailroadCrossings(IEventBus modbus) {
        REGISTRAR = new FootEasyRegisterSystem(MODID, modbus, MODNAME);
        CRRCBlocks.register();
        CRRCBlockEntities.register();
        CRRCSoundEvents.register();
        CRRCItems.register();
        modbus.addListener(CRRCBlockColors::registerBlockColors);
        modbus.addListener(CRRCBlockColors::registerItemColors);

        // Register CreativeTab last so it can be populated beforehand.

	    REGISTRAR.registerCreativeTab(
		    new TabConfig(
                "create_realistic_railroad_crossings_creative_mode_tab",
			    Component.literal(MODNAME),
			    Optional.empty(),
			    true,
			    96,
			    Optional.empty(),
			    Optional.empty(),
			    Optional.empty()
		    )
	    );
    }
}
