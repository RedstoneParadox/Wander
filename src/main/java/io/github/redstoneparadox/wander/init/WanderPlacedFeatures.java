package io.github.redstoneparadox.wander.init;

import io.github.redstoneparadox.wander.Wander;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.minecraft.util.Holder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.InSquarePlacementModifier;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil;

public class WanderPlacedFeatures {
	public static final Holder<PlacedFeature> FALLEN_OAK_TREE = PlacedFeatureUtil.register(
			Wander.id("fallen_oak_tree").toString(),
			WanderConfiguredFeatures.FALLEN_OAK_TREE,
			CountPlacementModifier.create(3),
			InSquarePlacementModifier.getInstance(),
			PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
			BiomePlacementModifier.getInstance()
	);
	public static final Holder<PlacedFeature> FALLEN_BIRCH_TREE = PlacedFeatureUtil.register(
			Wander.id("fallen_birch_tree").toString(),
			WanderConfiguredFeatures.FALLEN_BIRCH_TREE,
			CountPlacementModifier.create(3),
			InSquarePlacementModifier.getInstance(),
			PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
			BiomePlacementModifier.getInstance()
	);
	public static final Holder<PlacedFeature> OLD_FALLEN_BIRCH_TREE = PlacedFeatureUtil.register(
			Wander.id("old_fallen_birch_tree").toString(),
			WanderConfiguredFeatures.OLD_FALLEN_BIRCH_TREE,
			CountPlacementModifier.create(3),
			InSquarePlacementModifier.getInstance(),
			PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
			BiomePlacementModifier.getInstance()
	);

	public static void addFeaturesToVanilla() {
		BiomeModifications.addFeature(
				ctx -> ctx.getBiomeKey() == BiomeKeys.SWAMP,
				GenerationStep.Feature.VEGETAL_DECORATION, key("fallen_oak_tree")
		);
	}

	private static RegistryKey<PlacedFeature> key(String name) {
		return RegistryKey.of(BuiltinRegistries.PLACED_FEATURE.getKey(), Wander.id(name));
	}
}
