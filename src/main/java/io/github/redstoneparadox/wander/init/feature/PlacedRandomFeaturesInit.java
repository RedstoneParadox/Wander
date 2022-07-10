package io.github.redstoneparadox.wander.init.feature;

import io.github.redstoneparadox.wander.Wander;
import net.minecraft.util.Holder;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.InSquarePlacementModifier;
import net.minecraft.world.gen.decorator.NoiseThresholdCountPlacementModifier;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil;

public class PlacedRandomFeaturesInit {
	public static Holder<PlacedFeature> WILLOWS_REGULAR_AND_PINK_SWAMP = PlacedFeatureUtil.register(
			Wander.id("willows_regular_and_pink_swamp").toString(),
			ConfiguredRandomFeaturesInit.WILLOWS_REGULAR_AND_PINK_SWAMP,
			PlacedFeaturesInit.treePlacementModifiers(PlacedFeatureUtil.createCountExtraModifier(1, 0.1F, 1))
	);
	public static Holder<PlacedFeature> FALLEN_OAKS = PlacedFeatureUtil.register(
			Wander.id("fallen_trees_swamp").toString(),
			ConfiguredRandomFeaturesInit.FALLEN_TREES_SWAMP,
			NoiseThresholdCountPlacementModifier.create(-0.5, 2, 1),
			InSquarePlacementModifier.getInstance(),
			PlacedFeatureUtil.WORLD_SURFACE_WG_HEIGHTMAP,
			BiomePlacementModifier.getInstance()
	);
}
