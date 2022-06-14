package io.github.redstoneparadox.wander.world.gen.trunkplacer;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.redstoneparadox.wander.init.WanderTrunkPlacerTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.HolderSet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryCodecs;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class WillowTrunkPlacer extends TrunkPlacer {
	public static final Codec<WillowTrunkPlacer> CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
					Codec.intRange(0, 32).fieldOf("base_height").forGetter(placer -> placer.baseHeight),
					Codec.intRange(0, 24).fieldOf("height_rand_a").forGetter(placer -> placer.firstRandomHeight),
					Codec.intRange(0, 24).fieldOf("height_rand_b").forGetter(placer -> placer.secondRandomHeight),
					RegistryCodecs.homogeneousList(Registry.BLOCK_KEY).fieldOf("can_grow_through").forGetter(placer -> placer.canGrowThrough),
					IntProvider.VALUE_CODEC.fieldOf("branch_length").forGetter(placer -> placer.branchLength),
					IntProvider.VALUE_CODEC.fieldOf("branch_height").forGetter(placer -> placer.branchHeight)
			).apply(instance, WillowTrunkPlacer::new)
	);

	private final HolderSet<Block> canGrowThrough;
	private final IntProvider branchLength;
	private final IntProvider branchHeight;

	public WillowTrunkPlacer(int i, int j, int k, HolderSet<Block> canGrowThrough, IntProvider branchLength, IntProvider branchHeight) {
		super(i, j, k);
		this.canGrowThrough = canGrowThrough;
		this.branchLength = branchLength;
		this.branchHeight = branchHeight;
	}

	@Override
	protected TrunkPlacerType<?> getType() {
		return WanderTrunkPlacerTypes.WILLOW;
	}

	@Override
	public List<FoliagePlacer.TreeNode> generate(
			TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, RandomGenerator randomGenerator, int height, BlockPos startPos, TreeFeatureConfig config
	) {
		List<FoliagePlacer.TreeNode> nodes = Lists.<FoliagePlacer.TreeNode>newArrayList();

		for(int i = 0; i < height; ++i) {
			method_35375(world, replacer, randomGenerator, startPos.up(i), config);
		}

		for (Direction direction: new Direction[] { Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST }) {
			generateBranch(world, replacer, randomGenerator, startPos.up(height - 1), direction, nodes, config);
		}

		return nodes;
	}

	private void generateBranch(
			TestableWorld world,
			BiConsumer<BlockPos, BlockState> replacer,
			RandomGenerator randomGenerator,
			BlockPos start,
			Direction direction,
			List<FoliagePlacer.TreeNode> nodes,
			TreeFeatureConfig config
			)
	{
		int length = branchLength.get(randomGenerator);
		int height = branchHeight.get(randomGenerator);

		for (int i = 0; i < length; i++) {
			int y = (int) Math.floor(height * Math.sqrt(1 - Math.pow((double) i /length - 1, 2.0)));
			method_35375(world, replacer, randomGenerator, start.offset(direction, i).up(y), config);

			nodes.add(new FoliagePlacer.TreeNode(start.offset(direction, i).up(y + 1), 0, false));
		}
	}

	@Override
	protected boolean method_43196(TestableWorld testableWorld, BlockPos blockPos) {
		return super.method_43196(testableWorld, blockPos) || testableWorld.testBlockState(blockPos, blockState -> blockState.isIn(this.canGrowThrough));
	}
}
