package com.create_level_crossings_dev.create_realistic_railroad_crossings.registry;

import com.create_level_crossings_dev.create_realistic_railroad_crossings.CRRCUtils;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.poland.entity.PolandCrossingsWithCrossBlockEntity;
import com.footdablit2310.footlib.api.easy_register.FootEasyRegisterSystem;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class CRRCBlockEntities {
	public static final FootEasyRegisterSystem REGISTRAR = CRRCUtils.REGISTRAR;

	public static void register() {
		Poland.register();
		Serbia.register();
		Czech.register();
		German.register();
		Dutch.register();
		Belgium.register();
		Soviet.register();
	}
	public static class Poland {
		public static DeferredHolder<BlockEntityType<?>, BlockEntityType<PolandCrossingsWithCrossBlockEntity>> POLAND_CROSSING_WITH_CROSS;
		public static void register() {
			POLAND_CROSSING_WITH_CROSS = REGISTRAR.blockEntity("poland_crossings_with_cross", PolandCrossingsWithCrossBlockEntity::new)
				.validBlock(CRRCBlocks.Poland.POLAND_CROSSING_WITH_CROSS_1)
				.validBlock(CRRCBlocks.Poland.POLAND_CROSSING_WITH_CROSS_2)
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
