package io.github.redstoneparadox.wander.init;

import io.github.redstoneparadox.wander.Wander;
import io.github.redstoneparadox.wander.block.HollowLogBlock;
import io.github.redstoneparadox.wander.world.gen.fallentreedecorator.FoliageFallenTreeDecorator;
import io.github.redstoneparadox.wander.world.gen.fallentreedecorator.MossFallenTreeDecorator;
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

import java.util.ArrayList;
import java.util.List;

public class WanderConfiguredFeatures {
	public static final Holder<ConfiguredFeature<FallenTreeFeatureConfig, ?>> FALLEN_OAK_TREE = ConfiguredFeatureUtil.register(
			Wander.id("fallen_oak_tree").toString(),
			WanderFeatures.FALLEN_TREE,
			new FallenTreeFeatureConfig(
					Blocks.OAK_LOG.getDefaultState(),
					SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
					UniformIntProvider.create(3, 5),
					direction(),
					false,
					new ArrayList<>()
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

	private static IntProvider direction() {
		return UniformIntProvider.create(0, 3);
	}
}
