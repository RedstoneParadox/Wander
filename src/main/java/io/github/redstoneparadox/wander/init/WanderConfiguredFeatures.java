package io.github.redstoneparadox.wander.init;

import io.github.redstoneparadox.wander.world.gen.feature.FallenTreeFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Holder;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.util.ConfiguredFeatureUtil;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

public class WanderConfiguredFeatures {
	public static final Holder<ConfiguredFeature<FallenTreeFeatureConfig, ?>> OAK_FALLEN_TREE = ConfiguredFeatureUtil.register(
			"wander:oak_fallen_tree",
			WanderFeatures.FALLEN_TREE,
			new FallenTreeFeatureConfig(
					Blocks.OAK_LOG.getDefaultState(),
					SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
					UniformIntProvider.create(3, 5),
					direction()
			)
	);
	public static final Holder<ConfiguredFeature<FallenTreeFeatureConfig, ?>> BIRCH_FALLEN_TREE = ConfiguredFeatureUtil.register(
			"wander:birch_fallen_tree",
			WanderFeatures.FALLEN_TREE,
			new FallenTreeFeatureConfig(
					Blocks.BIRCH_LOG.getDefaultState(),
					SimpleBlockStateProvider.of(Blocks.BIRCH_LOG.getDefaultState()),
					UniformIntProvider.create(4, 6),
					direction()
			)
	);
	public static final Holder<ConfiguredFeature<FallenTreeFeatureConfig, ?>> OLD_BIRCH_FALLEN_TREE = ConfiguredFeatureUtil.register(
			"wander:old_birch_fallen_tree",
			WanderFeatures.FALLEN_TREE,
			new FallenTreeFeatureConfig(
					WanderBlocks.HOLLOW_BIRCH_LOG.getDefaultState(),
					new WeightedBlockStateProvider(
							DataPool.<BlockState>builder()
									.add(WanderBlocks.HOLLOW_BIRCH_LOG.getDefaultState(), 10)
									.add(Blocks.MOSS_BLOCK.getDefaultState(), 3)
									.add(Blocks.MOSS_CARPET.getDefaultState(), 6)
									.build()
					),
					UniformIntProvider.create(4, 6),
					direction()
			)
	);

	private static IntProvider direction() {
		return UniformIntProvider.create(0, 3);
	}
}
