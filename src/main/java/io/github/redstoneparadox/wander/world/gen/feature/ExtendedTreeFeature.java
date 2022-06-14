package io.github.redstoneparadox.wander.world.gen.feature;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import io.github.redstoneparadox.wander.block.ExtendedLeavesBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.state.property.Properties;
import net.minecraft.structure.Structure;
import net.minecraft.tag.BlockTags;
import net.minecraft.unmapped.C_dohzjtxc;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.util.shape.BitSetVoxelSet;
import net.minecraft.util.shape.VoxelSet;
import net.minecraft.world.ModifiableWorld;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.treedecorator.TreeDecorator;

import java.util.List;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.BiConsumer;

// Making a mixin was too painful
public class ExtendedTreeFeature extends Feature<TreeFeatureConfig> {
	private static final int FORCE_STATE_AND_NOTIFY_ALL = 19;

	public ExtendedTreeFeature(Codec<TreeFeatureConfig> codec) {
		super(codec);
	}

	private static boolean isVine(TestableWorld world, BlockPos pos) {
		return world.testBlockState(pos, state -> state.isOf(Blocks.VINE));
	}

	public static boolean isWater(TestableWorld world, BlockPos pos) {
		return world.testBlockState(pos, state -> state.isOf(Blocks.WATER));
	}

	public static boolean isAirOrLeaves(TestableWorld world, BlockPos pos) {
		return world.testBlockState(pos, state -> state.isAir() || state.isIn(BlockTags.LEAVES));
	}

	private static boolean isReplaceablePlant(TestableWorld world, BlockPos pos) {
		return world.testBlockState(pos, state -> {
			Material material = state.getMaterial();
			return material == Material.REPLACEABLE_PLANT || material == Material.REPLACEABLE_UNDERWATER_PLANT || material == Material.NETHER_SHOOTS;
		});
	}

	private static void setBlockStateWithoutUpdatingNeighbors(ModifiableWorld world, BlockPos pos, BlockState state) {
		world.setBlockState(pos, state, 19);
	}

	public static boolean canReplace(TestableWorld world, BlockPos pos) {
		return isAirOrLeaves(world, pos) || isReplaceablePlant(world, pos) || isWater(world, pos);
	}

	private boolean generate(
			StructureWorldAccess world,
			RandomGenerator random,
			BlockPos pos,
			BiConsumer<BlockPos, BlockState> trunkReplacer,
			BiConsumer<BlockPos, BlockState> foliageReplacer,
			BiConsumer<BlockPos, BlockState> biConsumer,
			TreeFeatureConfig config
	) {
		int i = config.trunkPlacer.getHeight(random);
		int j = config.foliagePlacer.getRandomHeight(random, i, config);
		int k = i - j;
		int l = config.foliagePlacer.getRandomRadius(random, k);
		BlockPos blockPos = (BlockPos)config.rootPlacer.map(c_dohzjtxc -> c_dohzjtxc.method_43309(pos, random)).orElse(pos);
		int m = Math.min(pos.getY(), blockPos.getY());
		int n = Math.max(pos.getY(), blockPos.getY()) + i + 1;
		if (m >= world.getBottomY() + 1 && n <= world.getTopY()) {
			OptionalInt optionalInt = config.minimumSize.getMinClippedHeight();
			int o = this.getTopPosition(world, i, blockPos, config);
			if (o >= i || !optionalInt.isEmpty() && o >= optionalInt.getAsInt()) {
				if (config.rootPlacer.isPresent() && !((C_dohzjtxc)config.rootPlacer.get()).method_43168(world, trunkReplacer, random, pos, blockPos, config)) {
					return false;
				} else {
					List<FoliagePlacer.TreeNode> list = config.trunkPlacer.generate(world, foliageReplacer, random, o, blockPos, config);
					list.forEach(treeNode -> config.foliagePlacer.method_27385(world, biConsumer, random, config, o, treeNode, j, l));
					return true;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private int getTopPosition(TestableWorld world, int height, BlockPos pos, TreeFeatureConfig config) {
		BlockPos.Mutable mutable = new BlockPos.Mutable();

		for(int i = 0; i <= height + 1; ++i) {
			int j = config.minimumSize.getRadius(height, i);

			for(int k = -j; k <= j; ++k) {
				for(int l = -j; l <= j; ++l) {
					mutable.set(pos, k, i, l);
					if (!config.trunkPlacer.method_43198(world, mutable) || !config.ignoreVines && isVine(world, mutable)) {
						return i - 2;
					}
				}
			}
		}

		return height;
	}

	@Override
	protected void setBlockState(ModifiableWorld world, BlockPos pos, BlockState state) {
		setBlockStateWithoutUpdatingNeighbors(world, pos, state);
	}

	@Override
	public final boolean place(FeatureContext<TreeFeatureConfig> context) {
		StructureWorldAccess structureWorldAccess = context.getWorld();
		RandomGenerator randomGenerator = context.getRandom();
		BlockPos blockPos = context.getOrigin();
		TreeFeatureConfig treeFeatureConfig = context.getConfig();
		Set<BlockPos> set = Sets.<BlockPos>newHashSet();
		Set<BlockPos> set2 = Sets.<BlockPos>newHashSet();
		Set<BlockPos> set3 = Sets.<BlockPos>newHashSet();
		Set<BlockPos> set4 = Sets.<BlockPos>newHashSet();
		BiConsumer<BlockPos, BlockState> biConsumer = (pos, state) -> {
			set.add(pos.toImmutable());
			structureWorldAccess.setBlockState(pos, state, 19);
		};
		BiConsumer<BlockPos, BlockState> biConsumer2 = (pos, state) -> {
			set2.add(pos.toImmutable());
			structureWorldAccess.setBlockState(pos, state, 19);
		};
		BiConsumer<BlockPos, BlockState> biConsumer3 = (pos, state) -> {
			set3.add(pos.toImmutable());
			structureWorldAccess.setBlockState(pos, state, 19);
		};
		BiConsumer<BlockPos, BlockState> biConsumer4 = (blockPosx, blockState) -> {
			set4.add(blockPosx.toImmutable());
			structureWorldAccess.setBlockState(blockPosx, blockState, 19);
		};
		boolean bl = this.generate(structureWorldAccess, randomGenerator, blockPos, biConsumer, biConsumer2, biConsumer3, treeFeatureConfig);
		if (bl && (!set2.isEmpty() || !set3.isEmpty())) {
			if (!treeFeatureConfig.decorators.isEmpty()) {
				TreeDecorator.class_7402 lv = new TreeDecorator.class_7402(structureWorldAccess, biConsumer4, randomGenerator, set2, set3, set);
				treeFeatureConfig.decorators.forEach(treeDecorator -> treeDecorator.generate(lv));
			}

			return BlockBox.encompassPositions(Iterables.concat(set, set2, set3, set4)).map(blockBox -> {
				VoxelSet voxelSet = placeLogsAndLeaves(structureWorldAccess, blockBox, set2, set4, set);
				Structure.updateCorner(structureWorldAccess, 3, voxelSet, blockBox.getMinX(), blockBox.getMinY(), blockBox.getMinZ());
				return true;
			}).orElse(false);
		} else {
			return false;
		}
	}

	private static VoxelSet placeLogsAndLeaves(
			WorldAccess world, BlockBox box, Set<BlockPos> trunkPositions, Set<BlockPos> decorationPositions, Set<BlockPos> set
	) {
		List<Set<BlockPos>> list = Lists.newArrayList();
		VoxelSet voxelSet = new BitSetVoxelSet(box.getBlockCountX(), box.getBlockCountY(), box.getBlockCountZ());

		for(int j = 0; j < 15; ++j) {
			list.add(Sets.newHashSet());
		}

		BlockPos.Mutable mutable = new BlockPos.Mutable();

		for(BlockPos blockPos : Lists.newArrayList(Sets.union(decorationPositions, set))) {
			if (box.contains(blockPos)) {
				voxelSet.set(blockPos.getX() - box.getMinX(), blockPos.getY() - box.getMinY(), blockPos.getZ() - box.getMinZ());
			}
		}

		for(BlockPos blockPos : Lists.newArrayList(trunkPositions)) {
			if (box.contains(blockPos)) {
				voxelSet.set(blockPos.getX() - box.getMinX(), blockPos.getY() - box.getMinY(), blockPos.getZ() - box.getMinZ());
			}

			for(Direction direction : Direction.values()) {
				mutable.set(blockPos, direction);
				if (!trunkPositions.contains(mutable)) {
					BlockState blockState = world.getBlockState(mutable);
					if (blockState.contains(Properties.DISTANCE_1_7)) {
						((Set)list.get(0)).add(mutable.toImmutable());
						setBlockStateWithoutUpdatingNeighbors(world, mutable, blockState.with(Properties.DISTANCE_1_7, 1));
						if (box.contains(mutable)) {
							voxelSet.set(mutable.getX() - box.getMinX(), mutable.getY() - box.getMinY(), mutable.getZ() - box.getMinZ());
						}
					} else if (blockState.contains(ExtendedLeavesBlock.DISTANCE)) {
						((Set)list.get(0)).add(mutable.toImmutable());
						setBlockStateWithoutUpdatingNeighbors(world, mutable, blockState.with(ExtendedLeavesBlock.DISTANCE, 1));
						if (box.contains(mutable)) {
							voxelSet.set(mutable.getX() - box.getMinX(), mutable.getY() - box.getMinY(), mutable.getZ() - box.getMinZ());
						}
					}
				}
			}
		}

		for(int k = 1; k < 15; ++k) {
			Set<BlockPos> set2 = (Set)list.get(k - 1);
			Set<BlockPos> set3 = (Set)list.get(k);

			for(BlockPos blockPos2 : set2) {
				if (box.contains(blockPos2)) {
					voxelSet.set(blockPos2.getX() - box.getMinX(), blockPos2.getY() - box.getMinY(), blockPos2.getZ() - box.getMinZ());
				}

				for(Direction direction2 : Direction.values()) {
					mutable.set(blockPos2, direction2);
					if (!set2.contains(mutable) && !set3.contains(mutable)) {
						BlockState blockState2 = world.getBlockState(mutable);
						if (blockState2.contains(Properties.DISTANCE_1_7) && !(k < 6)) {
							int l = blockState2.get(Properties.DISTANCE_1_7);
							if (l > k + 1) {
								BlockState blockState3 = blockState2.with(Properties.DISTANCE_1_7, k + 1);
								setBlockStateWithoutUpdatingNeighbors(world, mutable, blockState3);
								if (box.contains(mutable)) {
									voxelSet.set(mutable.getX() - box.getMinX(), mutable.getY() - box.getMinY(), mutable.getZ() - box.getMinZ());
								}

								set3.add(mutable.toImmutable());
							}
						} else if (blockState2.contains(ExtendedLeavesBlock.DISTANCE)) {
							int l = blockState2.get(ExtendedLeavesBlock.DISTANCE);
							if (l > k + 1) {
								BlockState blockState3 = blockState2.with(ExtendedLeavesBlock.DISTANCE, k + 1);
								setBlockStateWithoutUpdatingNeighbors(world, mutable, blockState3);
								if (box.contains(mutable)) {
									voxelSet.set(mutable.getX() - box.getMinX(), mutable.getY() - box.getMinY(), mutable.getZ() - box.getMinZ());
								}

								set3.add(mutable.toImmutable());
							}
						}
					}
				}
			}
		}

		return voxelSet;
	}
}
