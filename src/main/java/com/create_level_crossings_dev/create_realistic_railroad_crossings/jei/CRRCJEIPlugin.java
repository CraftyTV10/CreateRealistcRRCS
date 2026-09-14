package com.create_level_crossings_dev.create_realistic_railroad_crossings.jei;


import com.create_level_crossings_dev.create_realistic_railroad_crossings.CRRCUtils;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class CRRCJEIPlugin implements IModPlugin {
	@Override
	public @NotNull ResourceLocation getPluginUid() {
		return ResourceLocation.fromNamespaceAndPath(CRRCUtils.MODID, "jei_plugin");
	}
}