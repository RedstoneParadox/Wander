package io.github.redstoneparadox.wander.init;

import io.github.redstoneparadox.wander.Wander;
import net.minecraft.util.Holder;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomFeatureConfig;
import net.minecraft.world.gen.feature.WeightedPlacedFeature;

import java.util.List;

public class WanderRandomConfiguredFeatures {
	public static final Holder<ConfiguredFeature<RandomFeatureConfig, ?>> WILLOWS_REGULAR_AND_PINK_SWAMP = WanderConfiguredFeatures.register(
			Wander.id("willows_regular_and_pink_swamp"),
			Feature.RANDOM_SELECTOR,
			new RandomFeatureConfig(
					List.of(
							new WeightedPlacedFeature(WanderPlacedFeatures.WILLOW_TREE_SWAMP, 0.95f),
							new WeightedPlacedFeature(WanderPlacedFeatures.PINK_WILLOW_TREE_SWAMP, 0.05f)
					),
					WanderPlacedFeatures.WILLOW_TREE_SWAMP
			)
	);
}
