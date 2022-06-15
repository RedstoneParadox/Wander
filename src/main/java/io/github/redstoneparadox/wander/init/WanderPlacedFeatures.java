package io.github.redstoneparadox.wander.init;

import com.google.common.collect.ImmutableList;
import io.github.redstoneparadox.wander.Wander;
import io.github.redstoneparadox.wander.world.gen.placementmodifiers.UnderwaterPlacementModifier;
import net.minecraft.block.Block;
import net.minecraft.util.Holder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.BlockPredicateFilterPlacementModifier;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.InSquarePlacementModifier;
import net.minecraft.world.gen.decorator.NoiseThresholdCountPlacementModifier;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacementModifier;
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil;
import org.quiltmc.qsl.worldgen.biome.api.BiomeModifications;

import java.util.List;

import static net.minecraft.world.gen.feature.VegetationPlacedFeatures.TREE_THRESHOLD;

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
	public static final Holder<PlacedFeature> FALLEN_OAK_TREE_COMMON = PlacedFeatureUtil.register(
			Wander.id("fallen_oak_tree_common").toString(),
			WanderConfiguredFeatures.FALLEN_OAK_TREE,
			NoiseThresholdCountPlacementModifier.create(-0.5, 2, 1),
			InSquarePlacementModifier.getInstance(),
			PlacedFeatureUtil.WORLD_SURFACE_WG_HEIGHTMAP,
			BiomePlacementModifier.getInstance()
	);
	public static final Holder<PlacedFeature> SUBMERGED_FALLEN_OAK_TREE = PlacedFeatureUtil.register(
			Wander.id("submerged_fallen_oak_tree").toString(),
			WanderConfiguredFeatures.SUBMERGED_FALLEN_OAK_TREE,
			NoiseThresholdCountPlacementModifier.create(-0.8, 2, 1),
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
	public static Holder<PlacedFeature> WILLOWS_REGULAR_AND_PINK_SWAMP = PlacedFeatureUtil.register(
			Wander.id("willows_regular_and_pink_swamp").toString(),
			WanderConfiguredFeatures.WILLOWS_REGULAR_AND_PINK_SWAMP,
			treePlacementModifiers(PlacedFeatureUtil.createCountExtraModifier(10, 0.1F, 1))
	);


	public static void addFeaturesToVanilla() {
		if (FALLEN_OAK_TREE_COMMON.getKey().isPresent()) {
			BiomeModifications.addFeature(
					ctx -> ctx.getBiomeKey() == BiomeKeys.SWAMP,
					GenerationStep.Feature.VEGETAL_DECORATION, FALLEN_OAK_TREE_COMMON.getKey().get()
			);
		}
		if (SUBMERGED_FALLEN_OAK_TREE.getKey().isPresent()) {
			BiomeModifications.addFeature(
					ctx -> ctx.getBiomeKey() == BiomeKeys.SWAMP,
					GenerationStep.Feature.VEGETAL_DECORATION, SUBMERGED_FALLEN_OAK_TREE.getKey().get()
			);
		}
		if (WILLOWS_REGULAR_AND_PINK_SWAMP.getKey().isPresent()) {
			BiomeModifications.addFeature(
					ctx -> ctx.getBiomeKey() == BiomeKeys.SWAMP,
					GenerationStep.Feature.VEGETAL_DECORATION, WILLOWS_REGULAR_AND_PINK_SWAMP.getKey().get()
			);
		}
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
