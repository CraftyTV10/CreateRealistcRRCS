package com.create_level_crossings_dev.create_realistic_railroad_crossings.registry;

import com.create_level_crossings_dev.create_realistic_railroad_crossings.CRRCUtils;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.entity.PolishSignalWithCrossBlockEntity;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.entity.PolishGateBlockEntity;
import com.footdablit2310.footlib.api.easy_register.FootEasyRegisterSystem;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class CRRCBlockEntities {
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
        public static DeferredHolder<BlockEntityType<?>, BlockEntityType<PolishGateBlockEntity>> POLISH_GATE;
        public static DeferredHolder<BlockEntityType<?>, BlockEntityType<PolishSignalWithCrossBlockEntity>> POLISH_SIGNAL_WITH_CROSS;

		public static void register() {
			POLISH_SIGNAL_WITH_CROSS = REGISTRAR.blockEntity("polish_signal_with_cross", PolishSignalWithCrossBlockEntity::new)
				.validBlocks(CRRCBlocks.Polish.POLISH_SIGNAL_WITH_CROSS_1, CRRCBlocks.Polish.POLISH_SIGNAL_WITH_CROSS_2)
				.register();
			POLISH_GATE = REGISTRAR.blockEntity("polish_gate", PolishGateBlockEntity::new)
				.validBlocks(CRRCBlocks.Polish.POLISH_GATE, CRRCBlocks.Polish.POLISH_GATE_LONG)
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
