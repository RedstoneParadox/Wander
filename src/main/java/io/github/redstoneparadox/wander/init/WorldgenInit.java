package io.github.redstoneparadox.wander.init;

import io.github.redstoneparadox.wander.init.feature.PlacedRandomFeaturesInit;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import org.quiltmc.qsl.worldgen.biome.api.BiomeModifications;
import org.quiltmc.qsl.worldgen.biome.api.BiomeSelectionContext;

import java.util.function.Predicate;

public class WorldgenInit {
	public static void addFeaturesToVanilla() {
		// Random Placed Features need to come first to avoid static initialization errors
		if (PlacedRandomFeaturesInit.WILLOWS_REGULAR_AND_PINK_SWAMP.getKey().isPresent()) {
			BiomeModifications.addFeature(
					biomePredicate(BiomeKeys.SWAMP),
					GenerationStep.Feature.VEGETAL_DECORATION, PlacedRandomFeaturesInit.WILLOWS_REGULAR_AND_PINK_SWAMP.getKey().get()
			);
		}
		if (PlacedRandomFeaturesInit.FALLEN_OAKS.getKey().isPresent()) {
			BiomeModifications.addFeature(
					biomePredicate(BiomeKeys.SWAMP),
					GenerationStep.Feature.VEGETAL_DECORATION,
					PlacedRandomFeaturesInit.FALLEN_OAKS.getKey().get()
			);
		}
	}

	private static Predicate<BiomeSelectionContext> biomePredicate(RegistryKey<Biome> key) {
		return (ctx -> ctx.getBiomeKey() == key);
	}
}
