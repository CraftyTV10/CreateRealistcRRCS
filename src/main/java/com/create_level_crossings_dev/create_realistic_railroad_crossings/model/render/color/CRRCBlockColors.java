package com.create_level_crossings_dev.create_realistic_railroad_crossings.model.render.color;

import com.create_level_crossings_dev.create_realistic_railroad_crossings.CRRCUtils;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.state_properties.CRRCBlockStateProperties;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.blocks.state_properties.DualLightStatus;
import com.create_level_crossings_dev.create_realistic_railroad_crossings.registry.CRRCBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import static com.create_level_crossings_dev.create_realistic_railroad_crossings.CRRCUtils.LOGGER;

@EventBusSubscriber(
	modid = CRRCUtils.MODID,
	value = Dist.CLIENT
)
public final class CRRCBlockColors {

	private static final int LEFT_LENS_TINT_INDEX = 0;
	private static final int RIGHT_LENS_TINT_INDEX = 1;

	// ARGB
	public static final int LIGHT_OFF = 0xFF690000;
	public static final int LIGHT_DIM = 0xFFB40000;
	public static final int LIGHT_ON = 0xFFFF0000;
	private static final String ERROR_SOURCE = "CRRCBlockColors#getDualLightColor";

	private static @NonNull IllegalStateException invalidTintIndex(
		@NonNull BlockState state,
		int tintIndex
	) {
		ResourceLocation blockId =
			BuiltInRegistries.BLOCK.getKey(state.getBlock());

		DualLightStatus status =
			state.getValue(CRRCBlockStateProperties.DUAL_LIGHT_STATUS);

		LOGGER.error(
			"{} Unhandled tint index {} for block {} and status {}. "
				+ "Expected tint indices {} or {}.",
			ERROR_SOURCE,
			tintIndex,
			blockId,
			status,
			LEFT_LENS_TINT_INDEX,
			RIGHT_LENS_TINT_INDEX
		);

		return new IllegalStateException(
			ERROR_SOURCE
				+ " Unhandled tint index "
				+ tintIndex
				+ " for block "
				+ blockId
				+ " and status "
				+ status
		);
	}

	private CRRCBlockColors() {}

	private static int getDualLightColor(
		@NonNull BlockState state,
		@Nullable BlockAndTintGetter level,
		@Nullable BlockPos pos,
		int tintIndex
	) {
		if (tintIndex < 0) {
			return 0xFFFFFFFF;
		}

		DualLightStatus status =
			state.getValue(CRRCBlockStateProperties.DUAL_LIGHT_STATUS);


		return switch (status) {

			case BOTH_OFF -> LIGHT_OFF;

			case LEFT_LIGHT_DIM -> {
				if (tintIndex == LEFT_LENS_TINT_INDEX) {
					yield LIGHT_DIM;
				} else if (tintIndex == RIGHT_LENS_TINT_INDEX) {
					yield LIGHT_OFF;
				}

				throw invalidTintIndex(state, tintIndex);
			}

			case LEFT_LIGHT_ON -> {
				if (tintIndex == LEFT_LENS_TINT_INDEX) {
					yield LIGHT_ON;
				} else if (tintIndex == RIGHT_LENS_TINT_INDEX) {
					yield LIGHT_OFF;
				}

				throw invalidTintIndex(state, tintIndex);
			}

			case RIGHT_LIGHT_DIM -> {
				if (tintIndex == LEFT_LENS_TINT_INDEX) {
					yield LIGHT_OFF;
				} else if (tintIndex == RIGHT_LENS_TINT_INDEX) {
					yield LIGHT_DIM;
				}

				throw invalidTintIndex(state, tintIndex);
			}

			case RIGHT_LIGHT_ON -> {
				if (tintIndex == LEFT_LENS_TINT_INDEX) {
					yield LIGHT_OFF;
				} else if (tintIndex == RIGHT_LENS_TINT_INDEX) {
					yield LIGHT_ON;
				}

				throw invalidTintIndex(state, tintIndex);
			}

			case BOTH_DIM -> LIGHT_DIM;

			case BOTH_ON -> LIGHT_ON;
		};
	}

	@SubscribeEvent
	public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {

		event.register(
			CRRCBlockColors::getDualLightColor,
			CRRCBlocks.Polish.POLISH_CROSSING_WITH_CROSS_1.get(),
			CRRCBlocks.Polish.POLISH_CROSSING_WITH_CROSS_2.get()
		);
	}

	@SubscribeEvent
	public static void registerItemColors(RegisterColorHandlersEvent.Item event) {

		event.register(
			(stack, tintIndex) -> {

				if (tintIndex == LEFT_LENS_TINT_INDEX) {
					return LIGHT_OFF;
				} else if (tintIndex == RIGHT_LENS_TINT_INDEX) {
					return LIGHT_OFF;
				} else if (tintIndex < 0) {
					return 0xFFFFFFFF;
				}

				LOGGER.error(
					"{}Unhandled item tint index {}. Expected {}, {}, or a negative index.",
					CRRCUtils.logPrefix("CRRCBlockColors#registerItemColors"),
					tintIndex,
					LEFT_LENS_TINT_INDEX,
					RIGHT_LENS_TINT_INDEX
				);

				throw new IllegalStateException(
					"Unhandled item tint index: " + tintIndex
				);
			},
			CRRCBlocks.Polish.POLISH_CROSSING_WITH_CROSS_1.get().asItem(),
			CRRCBlocks.Polish.POLISH_CROSSING_WITH_CROSS_2.get().asItem()
		);
	}
}