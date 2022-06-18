package io.github.redstoneparadox.wander.init;

import com.google.common.collect.ImmutableList;
import io.github.redstoneparadox.wander.Wander;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Holder;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.BlockPredicateFilterPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.InSquarePlacementModifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacementModifier;
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil;

import java.util.List;

import static net.minecraft.world.gen.feature.VegetationPlacedFeatures.TREE_THRESHOLD;

public class WanderPlacedFeatures {
	public static final Holder<PlacedFeature> WILLOW_TREE_SWAMP = PlacedFeatureUtil.register(
			Wander.id("willow_swamp").toString(),
			WanderConfiguredFeatures.WILLOW_SWAMP,
			PlacedFeatureUtil.createWouldSurvivePlacementModifier(Blocks.MANGROVE_PROPAGULE)
	);
	public static final Holder<PlacedFeature> PINK_WILLOW_TREE_SWAMP = PlacedFeatureUtil.register(
			Wander.id("pink_willow_swamp").toString(),
			WanderConfiguredFeatures.PINK_WILLOW_SWAMP,
			PlacedFeatureUtil.createWouldSurvivePlacementModifier(Blocks.MANGROVE_PROPAGULE)
	);
	// For saplings
	public static final Holder<PlacedFeature> WILLOW = PlacedFeatureUtil.register(
			Wander.id("willow").toString(),
			WanderConfiguredFeatures.WILLOW,
			PlacedFeatureUtil.createWouldSurvivePlacementModifier(Blocks.MANGROVE_PROPAGULE)
	);
	public static final Holder<PlacedFeature> PINK_WILLOW = PlacedFeatureUtil.register(
			Wander.id("pink_willow").toString(),
			WanderConfiguredFeatures.PINK_WILLOW,
			PlacedFeatureUtil.createWouldSurvivePlacementModifier(Blocks.MANGROVE_PROPAGULE)
	);

	private static final HeightRangePlacementModifier BELOW_SEA_LEVEL = HeightRangePlacementModifier.createUniform(YOffset.BOTTOM, YOffset.fixed(61));

	public static final Holder<PlacedFeature> FALLEN_OAK = register(
			Wander.id("fallen_oak"),
			WanderConfiguredFeatures.FALLEN_OAK,
			List.of(PlacedFeatureUtil.WORLD_SURFACE_WG_HEIGHTMAP)
	);
	public static final Holder<PlacedFeature> HOLLOW_FALLEN_OAK = register(
			Wander.id("hollow_fallen_oak"),
			WanderConfiguredFeatures.HOLLOW_FALLEN_OAK,
			List.of(PlacedFeatureUtil.WORLD_SURFACE_WG_HEIGHTMAP)
	);

	private static Holder<PlacedFeature> register(Identifier id, Holder<? extends ConfiguredFeature<?, ?>> configuredFeature, List<PlacementModifier> modifiers) {
		return PlacedFeatureUtil.register(id.toString(), configuredFeature, modifiers);
	}

	private static ImmutableList.Builder<PlacementModifier> treePlacementModifiersBase(PlacementModifier modifier) {
		return ImmutableList.<PlacementModifier>builder()
				.add(modifier)
				.add(InSquarePlacementModifier.getInstance())
				.add(TREE_THRESHOLD)
				.add(PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP)
				.add(BiomePlacementModifier.getInstance());
	}

	public static List<PlacementModifier> treePlacementModifiers(PlacementModifier modifier) {
		return treePlacementModifiersBase(modifier).build();
	}

	public static List<PlacementModifier> treePlacementModifiers(PlacementModifier modifier, Block block) {
		return treePlacementModifiersBase(modifier)
				.add(BlockPredicateFilterPlacementModifier.create(BlockPredicate.wouldSurvive(block.getDefaultState(), BlockPos.ORIGIN)))
				.build();
	}
}
