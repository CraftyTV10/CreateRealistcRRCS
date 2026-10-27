package com.create_level_crossings_dev.create_realistic_railroad_crossings.registry;

import com.create_level_crossings_dev.create_realistic_railroad_crossings.CRRCUtils;
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
		public static DeferredHolder<Block, Block> POLISH_CROSSING_WITH_CROSS_1;
		public static DeferredHolder<Block, Block> POLISH_CROSSING_WITH_CROSS_2;
		public static void register() {
			POLISH_CROSSING_WITH_CROSS_1 = REGISTRAR.block(
					"polish_crossing_with_cross_1",
					() -> new Block(
						BlockBehaviour.Properties.of().destroyTime(3).explosionResistance(5)
					)
				)
				.simpleItem()
				.creativeTab(CRRCUtils.CREATIVE_MODE_TAB_NAME)
				.register();

			POLISH_CROSSING_WITH_CROSS_2 = REGISTRAR.block(
					"polish_crossing_with_cross_2",
					() -> new Block(
						BlockBehaviour.Properties.of().destroyTime(3).explosionResistance(5)
					)
				)
				.simpleItem()
				.creativeTab(CRRCUtils.CREATIVE_MODE_TAB_NAME)
				.register();
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
