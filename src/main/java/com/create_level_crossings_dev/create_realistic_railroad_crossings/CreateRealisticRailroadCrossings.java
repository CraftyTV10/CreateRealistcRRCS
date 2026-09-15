package com.create_level_crossings_dev.create_realistic_railroad_crossings;

import com.create_level_crossings_dev.create_realistic_railroad_crossings.registry.CRRCBlocks;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.registry.CRRCItems;
import com.footdablit2310.footlib.easy_register.FootEasyRegisterSystem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.fml.common.Mod;


@Mod(CreateRealisticRailroadCrossings.MODID)
public class CreateRealisticRailroadCrossings {

    public static final String MODID = "create_realistic_railroad_crossings";
    public static final String MODNAME = "Create: Realistic Railroad Crossings";
    public static final String ABBREVIATED_MOD_NAME = "CRRC";

    public static FootEasyRegisterSystem REGISTRAR;

    public static final CreativeModeTab CREATIVE_MODE_TAB = CreativeModeTab
        .builder()
        .withSearchBar()
        .title(Component.literal(CRRCUtils.MODNAME))
        .build();

    public static final Logger LOGGER = LogUtils.getLogger();

    public CreateRealisticRailroadCrossings(IEventBus modbus) {
        REGISTRAR = new FootEasyRegisterSystem(MODID, modbus, MODNAME);
        CRRCBlocks.register();
        CRRCItems.register();
    }
}
