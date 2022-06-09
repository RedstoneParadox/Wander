package io.github.redstoneparadox.wander.init;

import net.minecraft.util.Holder;
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
}
