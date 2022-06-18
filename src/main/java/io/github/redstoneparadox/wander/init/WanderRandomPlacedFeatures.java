package io.github.redstoneparadox.wander.init;

import io.github.redstoneparadox.wander.Wander;
import net.minecraft.util.Holder;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil;

public class WanderRandomPlacedFeatures {
	public static Holder<PlacedFeature> WILLOWS_REGULAR_AND_PINK_SWAMP = PlacedFeatureUtil.register(
			Wander.id("willows_regular_and_pink_swamp").toString(),
			WanderRandomConfiguredFeatures.WILLOWS_REGULAR_AND_PINK_SWAMP,
			WanderPlacedFeatures.treePlacementModifiers(PlacedFeatureUtil.createCountExtraModifier(1, 0.1F, 1))
	);
}
