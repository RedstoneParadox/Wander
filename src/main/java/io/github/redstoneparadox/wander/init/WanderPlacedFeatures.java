package io.github.redstoneparadox.wander.init;

import io.github.redstoneparadox.wander.Wander;
import io.github.redstoneparadox.wander.world.gen.placementmodifiers.UnderwaterPlacementModifier;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.minecraft.client.render.SkyProperties;
import net.minecraft.util.Holder;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.HeightmapPlacementModifier;
import net.minecraft.world.gen.decorator.InSquarePlacementModifier;
import net.minecraft.world.gen.decorator.NoiseThresholdCountPlacementModifier;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil;

public class WanderPlacedFeatures {
	private static final HeightRangePlacementModifier BELOW_SEA_LEVEL = HeightRangePlacementModifier.createUniform(YOffset.BOTTOM, YOffset.fixed(61));
	public static final Holder<PlacedFeature> FALLEN_OAK_TREE = PlacedFeatureUtil.register(
			Wander.id("fallen_oak_tree").toString(),
			WanderConfiguredFeatures.FALLEN_OAK_TREE,
			NoiseThresholdCountPlacementModifier.create(-0.5, 1, 0),
			InSquarePlacementModifier.getInstance(),
			PlacedFeatureUtil.WORLD_SURFACE_WG_HEIGHTMAP,
			BiomePlacementModifier.getInstance()
	);
	public static final Holder<PlacedFeature> SUBMERGED_FALLEN_OAK_TREE = PlacedFeatureUtil.register(
			Wander.id("submerged_fallen_oak_tree").toString(),
			WanderConfiguredFeatures.SUBMERGED_FALLEN_OAK_TREE,
			NoiseThresholdCountPlacementModifier.create(-0.5, 2, 1),
			InSquarePlacementModifier.getInstance(),
			PlacedFeatureUtil.OCEAN_FLOOR_WG_HEIGHTMAP,
			BiomePlacementModifier.getInstance(),
			UnderwaterPlacementModifier.INSTANCE
	);
	public static final Holder<PlacedFeature> FALLEN_BIRCH_TREE = PlacedFeatureUtil.register(
			Wander.id("fallen_birch_tree").toString(),
			WanderConfiguredFeatures.FALLEN_BIRCH_TREE,
			CountPlacementModifier.create(3),
			InSquarePlacementModifier.getInstance(),
			PlacedFeatureUtil.WORLD_SURFACE_WG_HEIGHTMAP,
			BiomePlacementModifier.getInstance()
	);
	public static final Holder<PlacedFeature> OLD_FALLEN_BIRCH_TREE = PlacedFeatureUtil.register(
			Wander.id("old_fallen_birch_tree").toString(),
			WanderConfiguredFeatures.OLD_FALLEN_BIRCH_TREE,
			CountPlacementModifier.create(3),
			InSquarePlacementModifier.getInstance(),
			PlacedFeatureUtil.WORLD_SURFACE_WG_HEIGHTMAP,
			BiomePlacementModifier.getInstance()
	);

	public static void addFeaturesToVanilla() {
		BiomeModifications.addFeature(
				ctx -> ctx.getBiomeKey() == BiomeKeys.SWAMP,
				GenerationStep.Feature.VEGETAL_DECORATION, key("fallen_oak_tree")
		);
		BiomeModifications.addFeature(
				ctx -> ctx.getBiomeKey() == BiomeKeys.SWAMP,
				GenerationStep.Feature.VEGETAL_DECORATION, key("submerged_fallen_oak_tree")
		);
	}

	private static RegistryKey<PlacedFeature> key(String name) {
		return RegistryKey.of(BuiltinRegistries.PLACED_FEATURE.getKey(), Wander.id(name));
	}
}
