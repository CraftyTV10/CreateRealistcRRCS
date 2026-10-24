package com.create_level_crossings_dev.create_realistic_railroad_crossings;

import com.create_level_crossings_dev.create_realistic_railroad_crossings.registry.CRRCBlocks;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.registry.CRRCItems;
import com.footdablit2310.footlib.api.easy_register.FootEasyRegisterSystem;
import com.footdablit2310.footlib.api.easy_register.config.TabConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.fml.common.Mod;

import java.util.Optional;


@Mod(CreateRealisticRailroadCrossings.MODID)
public class CreateRealisticRailroadCrossings {

    public static final String MODID = "create_realistic_railroad_crossings";
    public static final String MODNAME = "Create: Realistic Railroad Crossings";
    public static final String ABBREVIATED_MOD_NAME = "CRRC";

    public static FootEasyRegisterSystem REGISTRAR;

    public static final Logger LOGGER = LogUtils.getLogger();

    public CreateRealisticRailroadCrossings(IEventBus modbus) {
        REGISTRAR = new FootEasyRegisterSystem(MODID, modbus, MODNAME);
        CRRCBlocks.register();
        CRRCItems.register();

        // Do this last
        DeferredHolder<CreativeModeTab, CreativeModeTab> CREATIVE_MODE_TAB = REGISTRAR.registerCreativeTab(new TabConfig(MODNAME, Component.literal(MODNAME), Optional.empty(), true, 89, Optional.empty(), Optional.empty(), Optional.empty()));
    }
}
