package com.create_level_crossings_dev.create_realistic_railroad_crossings.registry;

import com.create_level_crossings_dev.create_realistic_railroad_crossings.CRRCUtils;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.PolishSignalWithCross1Block;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.PolishSignalWithCross2Block;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.PolishGateBlock;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.PolishGateLongBlock;
import com.footdablit2310.footlib.api.easy_register.FootEasyRegisterSystem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredHolder;

public class CRRCBlocks {

	public static final FootEasyRegisterSystem REGISTRAR = CRRCUtils.REGISTRAR;

	public static void register() {
		Polish.register();
		Serbia.register();
		Czech.register();
		German.register();
		Dutch.register();
		Belgium.register();
		Soviet.register();
	}
	public static class Polish {
        public static DeferredHolder<Block, PolishGateBlock> POLISH_GATE;
		public static DeferredHolder<Block, PolishGateLongBlock> POLISH_GATE_LONG;
        public static DeferredHolder<Block, PolishSignalWithCross1Block> POLISH_SIGNAL_WITH_CROSS_1;
		public static DeferredHolder<Block, PolishSignalWithCross2Block> POLISH_SIGNAL_WITH_CROSS_2;

		public static void register() {
			POLISH_SIGNAL_WITH_CROSS_1 = REGISTRAR.block(
					"polish_signal_with_cross_1",
					() -> new PolishSignalWithCross1Block(
						BlockBehaviour.Properties.of().destroyTime(3).explosionResistance(5).noOcclusion()
					)
			).simpleItem().creativeTab(CRRCUtils.CREATIVE_MODE_TAB_NAME).register();

			POLISH_SIGNAL_WITH_CROSS_2 = REGISTRAR.block(
					"polish_signal_with_cross_2",
					() -> new PolishSignalWithCross2Block(
						BlockBehaviour.Properties.of().destroyTime(3).explosionResistance(5).noOcclusion()
					)
			).simpleItem().creativeTab(CRRCUtils.CREATIVE_MODE_TAB_NAME).register();
			POLISH_GATE = REGISTRAR.block(
					"polish_gate",
					() -> new PolishGateBlock(
							BlockBehaviour.Properties.of().destroyTime(3).explosionResistance(5).noOcclusion()
					)
			).simpleItem().creativeTab(CRRCUtils.CREATIVE_MODE_TAB_NAME).register();
			POLISH_GATE_LONG = REGISTRAR.block(
					"polish_gate_long",
					() -> new PolishGateLongBlock(
							BlockBehaviour.Properties.of().destroyTime(3).explosionResistance(5).noOcclusion()
					)
			).simpleItem().creativeTab(CRRCUtils.CREATIVE_MODE_TAB_NAME).register();
		}
	}
	public static class Serbia {
		public static void register() {

		}
	}
	public static class Czech {
		public static void register() {

		}
	}
	public static class German {
		public static void register() {

		}
	}
	public static class Dutch {
		public static void register() {

		}
	}
	public static class Belgium {
		public static void register() {

		}
	}
	public static class Soviet {
		public static void register() {

		}
	}
}
