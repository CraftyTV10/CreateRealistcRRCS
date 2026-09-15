package com.create_level_crossings_dev.create_realistic_railroad_crossings.registry;

import com.create_level_crossings_dev.create_realistic_railroad_crossings.CRRCUtils;
import com.footdablit2310.footlib.easy_register.FootEasyRegisterSystem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class CRRCBlocks {

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
	public class Poland {
		public static void register() {
			REGISTRAR.block(
				"poland_crossing_with_cross",
				() -> new Block(
					BlockBehaviour.Properties.of().destroyTime(3).explosionResistance(5)
				)
			)
				.simpleItem()
				.creativeTab()
				.register();
		}
	}
	public class Serbia {
		public static void register() {

		}
	}
	public class Czech {
		public static void register() {

		}
	}
	public class German {
		public static void register() {

		}
	}
	public class Dutch {
		public static void register() {

		}
	}
	public class Belgium {
		public static void register() {

		}
	}
	public class Soviet {
		public static void register() {

		}
	}
}
