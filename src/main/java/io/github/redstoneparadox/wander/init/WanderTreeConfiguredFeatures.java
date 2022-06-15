package io.github.redstoneparadox.wander.init;

import io.github.redstoneparadox.wander.Wander;
import io.github.redstoneparadox.wander.world.gen.treedecorator.HangingLeavesTreeDecorator;
import io.github.redstoneparadox.wander.world.gen.trunkplacer.WillowTrunkPlacer;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Holder;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.RandomSpreadFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.LeavesVineTreeDecorator;

import java.util.List;
import java.util.Optional;

public class WanderTreeConfiguredFeatures {
	public static final Holder<ConfiguredFeature<TreeFeatureConfig, ?>> WILLOW = WanderConfiguredFeatures.register(
			Wander.id("willow"),
			WanderFeatures.EXTENDED_TREE,
			new TreeFeatureConfig.Builder(
					BlockStateProvider.of(WanderBlocks.WILLOW_LOG),
					new WillowTrunkPlacer(
							2, 1, 2, Registry.BLOCK.getOrCreateTag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH), UniformIntProvider.create(4, 6), UniformIntProvider.create(6, 9)
					),
					BlockStateProvider.of(WanderBlocks.WILLOW_LEAVES),
					new RandomSpreadFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), ConstantIntProvider.create(3), 50),
					Optional.empty(),
					new TwoLayersFeatureSize(3, 0, 2)
			)
					.decorators(List.of(
							WanderConfiguredFeatures.BEES_001,
							new HangingLeavesTreeDecorator(
									WanderBlocks.WILLOW_LEAVES.getDefaultState().with(LeavesBlock.PERSISTENT, true),
									List.of(Blocks.AIR.getDefaultState()),
									UniformIntProvider.create(4, 8),
									0.4f
							)
					))
					.ignoreVines()
					.build()
	);
	public static final Holder<ConfiguredFeature<TreeFeatureConfig, ?>> WILLOW_SWAMP = WanderConfiguredFeatures.register(
			Wander.id("willow_swamp"),
			WanderFeatures.EXTENDED_TREE,
			new TreeFeatureConfig.Builder(
					BlockStateProvider.of(WanderBlocks.WILLOW_LOG),
					new WillowTrunkPlacer(
							2, 1, 2, Registry.BLOCK.getOrCreateTag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH), UniformIntProvider.create(4, 6), UniformIntProvider.create(6, 9)
					),
					BlockStateProvider.of(WanderBlocks.WILLOW_LEAVES),
					new RandomSpreadFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), ConstantIntProvider.create(3), 50),
					Optional.empty(),
					new TwoLayersFeatureSize(3, 0, 2)
			)
					.decorators(List.of(
							new LeavesVineTreeDecorator(0.125F),
							WanderConfiguredFeatures.BEES_001,
							new HangingLeavesTreeDecorator(
									WanderBlocks.WILLOW_LEAVES.getDefaultState().with(LeavesBlock.PERSISTENT, true),
									List.of(Blocks.AIR.getDefaultState()),
									UniformIntProvider.create(4, 8),
									0.4f
							)
					))
					.ignoreVines()
					.build()
	);
	public static final Holder<ConfiguredFeature<TreeFeatureConfig, ?>> PINK_WILLOW = WanderConfiguredFeatures.register(
			Wander.id("pink_willow"),
			WanderFeatures.EXTENDED_TREE,
			new TreeFeatureConfig.Builder(
					BlockStateProvider.of(WanderBlocks.WILLOW_LOG),
					new WillowTrunkPlacer(
							2, 1, 2, Registry.BLOCK.getOrCreateTag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH), UniformIntProvider.create(4, 6), UniformIntProvider.create(6, 9)
					),
					BlockStateProvider.of(WanderBlocks.PINK_WILLOW_LEAVES),
					new RandomSpreadFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), ConstantIntProvider.create(3), 50),
					Optional.empty(),
					new TwoLayersFeatureSize(3, 0, 2)
			)
					.decorators(List.of(
							WanderConfiguredFeatures.BEES_001,
							new HangingLeavesTreeDecorator(
									WanderBlocks.PINK_WILLOW_LEAVES.getDefaultState().with(LeavesBlock.PERSISTENT, true),
									List.of(Blocks.AIR.getDefaultState()),
									UniformIntProvider.create(4, 8),
									0.4f
							)
					))
					.ignoreVines()
					.build()
	);
	public static final Holder<ConfiguredFeature<TreeFeatureConfig, ?>> PINK_WILLOW_SWAMP = WanderConfiguredFeatures.register(
			Wander.id("pink_willow_swamp"),
			WanderFeatures.EXTENDED_TREE,
			new TreeFeatureConfig.Builder(
					BlockStateProvider.of(WanderBlocks.WILLOW_LOG),
					new WillowTrunkPlacer(
							2, 1, 2, Registry.BLOCK.getOrCreateTag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH), UniformIntProvider.create(4, 6), UniformIntProvider.create(6, 9)
					),
					BlockStateProvider.of(WanderBlocks.PINK_WILLOW_LEAVES),
					new RandomSpreadFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), ConstantIntProvider.create(3), 50),
					Optional.empty(),
					new TwoLayersFeatureSize(3, 0, 2)
			)
					.decorators(List.of(
							new LeavesVineTreeDecorator(0.125F),
							WanderConfiguredFeatures.BEES_001,
							new HangingLeavesTreeDecorator(
									WanderBlocks.PINK_WILLOW_LEAVES.getDefaultState().with(LeavesBlock.PERSISTENT, true),
									List.of(Blocks.AIR.getDefaultState()),
									UniformIntProvider.create(4, 8),
									0.4f
							)
					))
					.ignoreVines()
					.build()
	);
}
