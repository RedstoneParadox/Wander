package io.github.redstoneparadox.wander.init;

import io.github.redstoneparadox.wander.world.gen.feature.FallenTreeFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Holder;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.util.ConfiguredFeatureUtil;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

public class WanderConfiguredFeatures {
	public static final Holder<ConfiguredFeature<FallenTreeFeatureConfig, ?>> OAK_FALLEN_TREE = ConfiguredFeatureUtil.register(
			"wander:oak_fallen_tree",
			WanderFeatures.FALLEN_TREE,
			new FallenTreeFeatureConfig(
					new WeightedBlockStateProvider(
							DataPool.<BlockState>builder()
									.add(Blocks.OAK_LOG.getDefaultState(), 6)
									.add(WanderBlocks.HOLLOW_OAK_LOG.getDefaultState(), 4)
									.build()
					),
					UniformIntProvider.create(3, 5),
					UniformIntProvider.create(0, 3)
			)
	);
}
