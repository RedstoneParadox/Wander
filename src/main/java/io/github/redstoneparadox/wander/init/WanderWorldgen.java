package io.github.redstoneparadox.wander.init;

import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import org.quiltmc.qsl.worldgen.biome.api.BiomeModifications;

public class WanderWorldgen {
	public static void addFeaturesToVanilla() {
		// Random Placed Features need to come first to avoid static initialization errors
		if (WanderRandomPlacedFeatures.WILLOWS_REGULAR_AND_PINK_SWAMP.getKey().isPresent()) {
			BiomeModifications.addFeature(
					ctx -> ctx.getBiomeKey() == BiomeKeys.SWAMP,
					GenerationStep.Feature.VEGETAL_DECORATION, WanderRandomPlacedFeatures.WILLOWS_REGULAR_AND_PINK_SWAMP.getKey().get()
			);
		}
	}
}
