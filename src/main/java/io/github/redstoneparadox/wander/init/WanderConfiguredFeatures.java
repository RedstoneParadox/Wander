package io.github.redstoneparadox.wander.init;

import io.github.redstoneparadox.wander.Wander;
import io.github.redstoneparadox.wander.block.HollowLogBlock;
import io.github.redstoneparadox.wander.world.gen.fallentreedecorator.FoliageFallenTreeDecorator;
import io.github.redstoneparadox.wander.world.gen.fallentreedecorator.MossFallenTreeDecorator;
import io.github.redstoneparadox.wander.world.gen.feature.FallenTreeFeatureConfig;
import io.github.redstoneparadox.wander.world.gen.treedecorator.HangingLeavesTreeDecorator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MangrovePropaguleBlock;
import net.minecraft.tag.BlockTags;
import net.minecraft.unmapped.C_dkqjnekb;
import net.minecraft.unmapped.C_iajygakr;
import net.minecraft.unmapped.C_jjhxbuto;
import net.minecraft.unmapped.C_rgeelgbr;
import net.minecraft.unmapped.C_sismmyuo;
import net.minecraft.util.Holder;
import net.minecraft.util.HolderSet;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.feature.util.ConfiguredFeatureUtil;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.RandomSpreadFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.RandomizedIntBlockStateProvider;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.treedecorator.LeavesVineTreeDecorator;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WanderConfiguredFeatures {
	private static final BeehiveTreeDecorator BEES_001 = new BeehiveTreeDecorator(0.01F);
	public static final Holder<ConfiguredFeature<FallenTreeFeatureConfig, ?>> FALLEN_OAK_TREE = ConfiguredFeatureUtil.register(
			Wander.id("fallen_oak_tree").toString(),
			WanderFeatures.FALLEN_TREE,
			new FallenTreeFeatureConfig(
					Blocks.OAK_LOG.getDefaultState(),
					SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
					UniformIntProvider.create(3, 5),
					direction(),
					false,
					List.of(
							new MossFallenTreeDecorator(0.5f)
					)
			)
	);
	public static final Holder<ConfiguredFeature<FallenTreeFeatureConfig, ?>> SUBMERGED_FALLEN_OAK_TREE = ConfiguredFeatureUtil.register(
			Wander.id("submerged_fallen_oak_tree").toString(),
			WanderFeatures.FALLEN_TREE,
			new FallenTreeFeatureConfig(
					Blocks.OAK_LOG.getDefaultState(),
					SimpleBlockStateProvider.of(WanderBlocks.HOLLOW_OAK_LOG.getDefaultState().with(HollowLogBlock.WATERLOGGED, true)),
					UniformIntProvider.create(2, 5),
					direction(),
					true,
					List.of(
							new FoliageFallenTreeDecorator(
									new WeightedBlockStateProvider(
											DataPool.<BlockState>builder()
													.add(Blocks.SEAGRASS.getDefaultState(), 1)
													.add(Blocks.TALL_SEAGRASS.getDefaultState(), 1)
													.build()
									),
									0.7f
							)
					)
			)
	);
	public static final Holder<ConfiguredFeature<FallenTreeFeatureConfig, ?>> FALLEN_BIRCH_TREE = ConfiguredFeatureUtil.register(
			Wander.id("fallen_birch_tree").toString(),
			WanderFeatures.FALLEN_TREE,
			new FallenTreeFeatureConfig(
					Blocks.BIRCH_LOG.getDefaultState(),
					SimpleBlockStateProvider.of(Blocks.BIRCH_LOG.getDefaultState()),
					UniformIntProvider.create(4, 6),
					direction(),
					false,
					List.of(
							new MossFallenTreeDecorator(0.4f)
					)
			)
	);
	public static final Holder<ConfiguredFeature<FallenTreeFeatureConfig, ?>> OLD_FALLEN_BIRCH_TREE = ConfiguredFeatureUtil.register(
			Wander.id("old_fallen_birch_tree").toString(),
			WanderFeatures.FALLEN_TREE,
			new FallenTreeFeatureConfig(
					Blocks.BIRCH_LOG.getDefaultState(),
					new WeightedBlockStateProvider(
							DataPool.<BlockState>builder()
									.add(WanderBlocks.HOLLOW_BIRCH_LOG.getDefaultState(), 10)
									.add(Blocks.MOSS_BLOCK.getDefaultState(), 3)
									.add(Blocks.MOSS_CARPET.getDefaultState(), 6)
									.build()
					),
					UniformIntProvider.create(4, 6),
					direction(),
					false,
					List.of(
							new MossFallenTreeDecorator(0.4f),
							new FoliageFallenTreeDecorator(
									SimpleBlockStateProvider.of(Blocks.ORANGE_TULIP),
									0.6f
							)
					)
			)
	);
	public static final Holder<ConfiguredFeature<TreeFeatureConfig, ?>> WILLOW_TREE = register(
			Wander.id("willow_tree"),
			Feature.TREE,
			new TreeFeatureConfig.Builder(
					BlockStateProvider.of(Blocks.OAK_LOG),
					new StraightTrunkPlacer(6, 1, 6),
					BlockStateProvider.of(Blocks.JUNGLE_LEAVES),
					new RandomSpreadFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), ConstantIntProvider.create(2), 70),
					Optional.empty(),
					new TwoLayersFeatureSize(3, 0, 2)
			)
					.decorators(List.of(
							new LeavesVineTreeDecorator(0.125F),
							new C_rgeelgbr(
									0.14F,
									1,
									0,
									new RandomizedIntBlockStateProvider(
											BlockStateProvider.of(Blocks.MANGROVE_PROPAGULE.getDefaultState().with(MangrovePropaguleBlock.HANGING, Boolean.valueOf(true))),
											MangrovePropaguleBlock.AGE_4,
											UniformIntProvider.create(0, 4)
									),
									2,
									List.of(Direction.DOWN)
							),
							BEES_001,
							new HangingLeavesTreeDecorator(
									Blocks.JUNGLE_LEAVES.getDefaultState(),
									List.of(Blocks.AIR.getDefaultState()),
									UniformIntProvider.create(3, 6),
									0.2f
							)
					))
					.ignoreVines()
					.build()
	);

	public static <FC extends FeatureConfig, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(Identifier id, F feature, FC featureConfig) {
		return ConfiguredFeatureUtil.register(id.toString(), feature, featureConfig);
	}

	private static IntProvider direction() {
		return UniformIntProvider.create(0, 3);
	}
}
