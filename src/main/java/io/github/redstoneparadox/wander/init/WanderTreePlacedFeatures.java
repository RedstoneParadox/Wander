package io.github.redstoneparadox.wander.init;

import io.github.redstoneparadox.wander.Wander;
import net.minecraft.block.Blocks;
import net.minecraft.util.Holder;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.InSquarePlacementModifier;
import net.minecraft.world.gen.decorator.SurfaceWaterDepthFilterPlacementModifier;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil;

public class WanderTreePlacedFeatures {
	public static final Holder<PlacedFeature> WILLOW_TREE_SWAMP = PlacedFeatureUtil.register(
			Wander.id("willow_swamp").toString(),
			WanderTreeConfiguredFeatures.WILLOW_SWAMP,
			PlacedFeatureUtil.createWouldSurvivePlacementModifier(Blocks.MANGROVE_PROPAGULE)
	);
	public static final Holder<PlacedFeature> PINK_WILLOW_TREE_SWAMP = PlacedFeatureUtil.register(
			Wander.id("pink_willow_swamp").toString(),
			WanderTreeConfiguredFeatures.PINK_WILLOW_SWAMP,
			PlacedFeatureUtil.createWouldSurvivePlacementModifier(Blocks.MANGROVE_PROPAGULE)
	);
	// For saplings
	public static final Holder<PlacedFeature> WILLOW = PlacedFeatureUtil.register(
			Wander.id("willow").toString(),
			WanderTreeConfiguredFeatures.WILLOW,
			PlacedFeatureUtil.createWouldSurvivePlacementModifier(Blocks.MANGROVE_PROPAGULE)
	);
	public static final Holder<PlacedFeature> PINK_WILLOW = PlacedFeatureUtil.register(
			Wander.id("pink_willow").toString(),
			WanderTreeConfiguredFeatures.PINK_WILLOW,
			PlacedFeatureUtil.createWouldSurvivePlacementModifier(Blocks.MANGROVE_PROPAGULE)
	);
}
