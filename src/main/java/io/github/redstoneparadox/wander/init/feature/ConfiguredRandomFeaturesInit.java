package io.github.redstoneparadox.wander.init.feature;

import io.github.redstoneparadox.wander.Wander;
import io.github.redstoneparadox.wander.init.feature.ConfiguredFeaturesInit;
import io.github.redstoneparadox.wander.init.feature.PlacedFeaturesInit;
import net.minecraft.util.Holder;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomFeatureConfig;
import net.minecraft.world.gen.feature.WeightedPlacedFeature;

import java.util.List;

public class ConfiguredRandomFeaturesInit {
	public static final Holder<ConfiguredFeature<RandomFeatureConfig, ?>> WILLOWS_REGULAR_AND_PINK_SWAMP = ConfiguredFeaturesInit.register(
			Wander.id("willows_regular_and_pink_swamp"),
			Feature.RANDOM_SELECTOR,
			new RandomFeatureConfig(
					List.of(
							new WeightedPlacedFeature(PlacedFeaturesInit.WILLOW_TREE_SWAMP, 0.95f),
							new WeightedPlacedFeature(PlacedFeaturesInit.PINK_WILLOW_TREE_SWAMP, 0.05f)
					),
					PlacedFeaturesInit.WILLOW_TREE_SWAMP
			)
	);
	public static final Holder<ConfiguredFeature<RandomFeatureConfig, ?>> FALLEN_TREES_SWAMP = ConfiguredFeaturesInit.register(
			Wander.id("fallen_trees_swamp"),
			Feature.RANDOM_SELECTOR,
			new RandomFeatureConfig(
					List.of(
							new WeightedPlacedFeature(PlacedFeaturesInit.FALLEN_OAK, 0.6f),
							new WeightedPlacedFeature(PlacedFeaturesInit.HOLLOW_FALLEN_OAK, 0.4f),
							new WeightedPlacedFeature(PlacedFeaturesInit.FALLEN_WILLOW, 0.6f),
							new WeightedPlacedFeature(PlacedFeaturesInit.HOLLOW_FALLEN_WILLOW, 0.4f)
					),
					PlacedFeaturesInit.HOLLOW_FALLEN_OAK
			)
	);
}
