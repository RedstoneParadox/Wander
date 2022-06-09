package io.github.redstoneparadox.wander.init;

import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
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
	public static final Holder<PlacedFeature> OAK_FALLEN_TREE = PlacedFeatureUtil.register(
			"wander:oak_fallen_tree",
			WanderConfiguredFeatures.OAK_FALLEN_TREE,
			CountPlacementModifier.create(3),
			InSquarePlacementModifier.getInstance(),
			PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
			BiomePlacementModifier.getInstance()
	);
	public static final Holder<PlacedFeature> BIRCH_FALLEN_TREE = PlacedFeatureUtil.register(
			"wander:birch_fallen_tree",
			WanderConfiguredFeatures.BIRCH_FALLEN_TREE,
			CountPlacementModifier.create(3),
			InSquarePlacementModifier.getInstance(),
			PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
			BiomePlacementModifier.getInstance()
	);
	public static final Holder<PlacedFeature> OLD_BIRCH_FALLEN_TREE = PlacedFeatureUtil.register(
			"wander:old_birch_fallen_tree",
			WanderConfiguredFeatures.OLD_BIRCH_FALLEN_TREE,
			CountPlacementModifier.create(3),
			InSquarePlacementModifier.getInstance(),
			PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
			BiomePlacementModifier.getInstance()
	);

	public static void addFeaturesToVanilla(String modid) {
		BiomeModifications.addFeature(
				ctx -> ctx.getBiomeKey() == BiomeKeys.SWAMP,
				GenerationStep.Feature.VEGETAL_DECORATION, key(modid, "fallen_oak_tree")
		);
	}

	private static RegistryKey<PlacedFeature> key(String modid, String name) {
		return RegistryKey.of(BuiltinRegistries.PLACED_FEATURE.getKey(), new Identifier(modid, name));
	}
}
