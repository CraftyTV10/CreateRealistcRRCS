package com.create_level_crossings_dev.create_realistic_railroad_crossings;

import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.renderer.PolishGateRender;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.renderer.PolishSignalWithCrossRender;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.registry.CRRCBlockEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@Mod(value=CRRCClient.MODID, dist=Dist.CLIENT)
public class CRRCClient {
    public static final String MODID = "create_realistic_railroad_crossings";

    public CRRCClient(IEventBus modBus) {
        modBus.addListener(CRRCClient::registerRenderers);
    }
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(CRRCBlockEntities.Polish.POLISH_GATE.get(), PolishGateRender::new);
        event.registerBlockEntityRenderer(CRRCBlockEntities.Polish.POLISH_SIGNAL_WITH_CROSS.get(), PolishSignalWithCrossRender::new);
    }
}
