package io.github.redstoneparadox.wander.init;

import io.github.redstoneparadox.wander.Wander;
import io.github.redstoneparadox.wander.util.DirectionProvider;
import io.github.redstoneparadox.wander.world.gen.fallentreedecorator.MossFallenTreeDecorator;
import io.github.redstoneparadox.wander.world.gen.feature.FallenTreeFeatureConfig;
import io.github.redstoneparadox.wander.world.gen.treedecorator.HangingLeavesTreeDecorator;
import io.github.redstoneparadox.wander.world.gen.trunkplacer.WillowTrunkPlacer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Holder;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.feature.util.ConfiguredFeatureUtil;
import net.minecraft.world.gen.foliage.RandomSpreadFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.treedecorator.LeavesVineTreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecorator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WanderConfiguredFeatures {
	// Trees
	protected static final BeehiveTreeDecorator BEES_001 = new BeehiveTreeDecorator(0.01F);
	public static final Holder<ConfiguredFeature<TreeFeatureConfig, ?>> PINK_WILLOW_SWAMP = register(
			Wander.id("pink_willow_swamp"),
			WanderFeatures.EXTENDED_TREE,
			willowConfig(true, true)
	);
	public static final Holder<ConfiguredFeature<TreeFeatureConfig, ?>> PINK_WILLOW = register(
			Wander.id("pink_willow"),
			WanderFeatures.EXTENDED_TREE,
			willowConfig(true, false)
	);
	public static final Holder<ConfiguredFeature<TreeFeatureConfig, ?>> WILLOW_SWAMP = register(
			Wander.id("willow_swamp"),
			WanderFeatures.EXTENDED_TREE,
			willowConfig(false, true)
	);
	public static final Holder<ConfiguredFeature<TreeFeatureConfig, ?>> WILLOW = register(
			Wander.id("willow"),
			WanderFeatures.EXTENDED_TREE,
			willowConfig(false, false)
	);

	// Fallen Trees
	public static final Holder<ConfiguredFeature<FallenTreeFeatureConfig, ?>> FALLEN_OAK = register(
			Wander.id("fallen_oak"),
			WanderFeatures.FALLEN_TREE,
			new FallenTreeFeatureConfig(
					Blocks.OAK_LOG.getDefaultState(),
					SimpleBlockStateProvider.of(Blocks.OAK_LOG),
					UniformIntProvider.create(3, 6),
					new DirectionProvider(true),
					false,
					List.of(
							new MossFallenTreeDecorator(0.2f)
					)
			)
	);
	public static final Holder<ConfiguredFeature<FallenTreeFeatureConfig, ?>> HOLLOW_FALLEN_OAK = register(
			Wander.id("hollow_fallen_oak"),
			WanderFeatures.FALLEN_TREE,
			new FallenTreeFeatureConfig(
					Blocks.OAK_LOG.getDefaultState(),
					SimpleBlockStateProvider.of(WanderBlocks.HOLLOW_OAK_LOG),
					UniformIntProvider.create(3, 6),
					new DirectionProvider(true),
					false,
					List.of(
							new MossFallenTreeDecorator(0.2f)
					)
			)
	);

	public static <FC extends FeatureConfig, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(Identifier id, F feature, FC featureConfig) {
		return ConfiguredFeatureUtil.register(id.toString(), feature, featureConfig);
	}

	private static TreeFeatureConfig willowConfig(boolean pink, boolean vines) {
		BlockState leaves = (pink ? WanderBlocks.PINK_WILLOW_LEAVES : WanderBlocks.WILLOW_LEAVES).getDefaultState();
		List<TreeDecorator> decorators = new ArrayList<>();

		decorators.add(BEES_001);
		decorators.add(new HangingLeavesTreeDecorator(
				leaves,
				List.of(Blocks.AIR.getDefaultState()),
				UniformIntProvider.create(4, 8),
				0.4f
		));
		if (vines) decorators.add(new LeavesVineTreeDecorator(0.125F));

		return new TreeFeatureConfig.Builder(
				BlockStateProvider.of(WanderBlocks.WILLOW_LOG),
				new WillowTrunkPlacer(
						2, 1, 2, Registry.BLOCK.getOrCreateTag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH), UniformIntProvider.create(4, 6), UniformIntProvider.create(6, 9)
				),
				BlockStateProvider.of(leaves),
				new RandomSpreadFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), ConstantIntProvider.create(3), 50),
				Optional.empty(),
				new TwoLayersFeatureSize(3, 0, 2)
		)
				.decorators(decorators)
				.ignoreVines()
				.build();
	}
}
