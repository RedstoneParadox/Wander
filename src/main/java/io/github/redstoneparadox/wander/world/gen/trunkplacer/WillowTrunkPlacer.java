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
					IntProvider.VALUE_CODEC.fieldOf("extra_branch_steps").forGetter(placer -> placer.extraBranchSteps),
					Codec.FLOAT.fieldOf("place_branch_per_log_probability").forGetter(placer -> placer.placeBranchPerLogProbability),
					IntProvider.VALUE_CODEC.fieldOf("extra_branch_length").forGetter(placer -> placer.extraBranchLength),
					RegistryCodecs.homogeneousList(Registry.BLOCK_KEY).fieldOf("can_grow_through").forGetter(placer -> placer.canGrowThrough),
					Codec.INT.fieldOf("min_branch_height").forGetter(placer -> placer.minBranchHeight)
			).apply(instance, WillowTrunkPlacer::new)
	);
	private final IntProvider extraBranchSteps;
	private final float placeBranchPerLogProbability;
	private final IntProvider extraBranchLength;
	private final HolderSet<Block> canGrowThrough;
	private final int minBranchHeight;

	public WillowTrunkPlacer(int i, int j, int k, IntProvider extraBranchSteps, float placeBranchPerLogProbability, IntProvider extraBranchLength, HolderSet<Block> canGrowThrough, int minBranchHeight) {
		super(i, j, k);
		this.extraBranchSteps = extraBranchSteps;
		this.placeBranchPerLogProbability = placeBranchPerLogProbability;
		this.extraBranchLength = extraBranchLength;
		this.canGrowThrough = canGrowThrough;
		this.minBranchHeight = minBranchHeight;
	}

	@Override
	protected TrunkPlacerType<?> getType() {
		return WanderTrunkPlacerTypes.WILLOW;
	}

	@Override
	public List<FoliagePlacer.TreeNode> generate(
			TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, RandomGenerator randomGenerator, int height, BlockPos startPos, TreeFeatureConfig config
	) {
		List<FoliagePlacer.TreeNode> list = Lists.<FoliagePlacer.TreeNode>newArrayList();
		BlockPos.Mutable mutable = new BlockPos.Mutable();

		for(int i = 0; i < height; ++i) {
			int j = startPos.getY() + i;
			if (this.method_35375(world, replacer, randomGenerator, mutable.set(startPos.getX(), j, startPos.getZ()), config)
					&& i < height - 1
					&& randomGenerator.nextFloat() < this.placeBranchPerLogProbability
					&& i >= minBranchHeight // Copied the Mangrove trunk placer just so I could make this check, lol.
			) {
				Direction direction = Direction.Type.HORIZONTAL.random(randomGenerator);
				int k = this.extraBranchLength.get(randomGenerator);
				int l = Math.max(0, k - this.extraBranchLength.get(randomGenerator) - 1);
				int m = this.extraBranchSteps.get(randomGenerator);
				this.generateBranch(world, replacer, randomGenerator, height, config, list, mutable, j, direction, l, m);
			}

			if (i == height - 1) {
				list.add(new FoliagePlacer.TreeNode(mutable.set(startPos.getX(), j + 1, startPos.getZ()), 0, false));
			}
		}

		return list;
	}

	private void generateBranch(
			TestableWorld testableWorld,
			BiConsumer<BlockPos, BlockState> biConsumer,
			RandomGenerator randomGenerator,
			int i,
			TreeFeatureConfig treeFeatureConfig,
			List<FoliagePlacer.TreeNode> list,
			BlockPos.Mutable mutable,
			int j,
			Direction direction,
			int k,
			int l
	) {
		int m = j + k;
		int n = mutable.getX();
		int o = mutable.getZ();

		for(int p = k; p < i && l > 0; --l) {
			if (p >= 1) {
				int q = j + p;
				n += direction.getOffsetX();
				o += direction.getOffsetZ();
				m = q;
				if (this.method_35375(testableWorld, biConsumer, randomGenerator, mutable.set(n, q, o), treeFeatureConfig)) {
					m = q + 1;
				}

				list.add(new FoliagePlacer.TreeNode(mutable.toImmutable(), 0, false));
			}

			++p;
		}

		if (m - j > 1) {
			BlockPos blockPos = new BlockPos(n, m, o);
			list.add(new FoliagePlacer.TreeNode(blockPos, 0, false));
			list.add(new FoliagePlacer.TreeNode(blockPos.down(2), 0, false));
		}

	}

	@Override
	protected boolean method_43196(TestableWorld testableWorld, BlockPos blockPos) {
		return super.method_43196(testableWorld, blockPos) || testableWorld.testBlockState(blockPos, blockState -> blockState.isIn(this.canGrowThrough));
	}
}
