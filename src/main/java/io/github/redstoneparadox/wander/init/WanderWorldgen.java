package io.github.redstoneparadox.wander.init;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import org.quiltmc.qsl.worldgen.biome.api.BiomeModifications;
import org.quiltmc.qsl.worldgen.biome.api.BiomeSelectionContext;

import java.util.function.Predicate;

public class WanderWorldgen {
	public static void addFeaturesToVanilla() {
		// Random Placed Features need to come first to avoid static initialization errors
		if (WanderRandomPlacedFeatures.WILLOWS_REGULAR_AND_PINK_SWAMP.getKey().isPresent()) {
			BiomeModifications.addFeature(
					biomePredicate(BiomeKeys.SWAMP),
					GenerationStep.Feature.VEGETAL_DECORATION, WanderRandomPlacedFeatures.WILLOWS_REGULAR_AND_PINK_SWAMP.getKey().get()
			);
		}
		if (WanderRandomPlacedFeatures.FALLEN_OAKS.getKey().isPresent()) {
			BiomeModifications.addFeature(
					biomePredicate(BiomeKeys.SWAMP),
					GenerationStep.Feature.VEGETAL_DECORATION,
					WanderRandomPlacedFeatures.FALLEN_OAKS.getKey().get()
			);
		}
	}

	private static Predicate<BiomeSelectionContext> biomePredicate(RegistryKey<Biome> key) {
		return (ctx -> ctx.getBiomeKey() == key);
	}
}
