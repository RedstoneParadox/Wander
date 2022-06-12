package io.github.redstoneparadox.wander.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import io.github.redstoneparadox.wander.init.WanderTreeDecoratorTypes;
import it.unimi.dsi.fastutil.ints.IntComparator;
import it.unimi.dsi.fastutil.ints.IntComparators;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

import static net.minecraft.util.math.Direction.EAST;
import static net.minecraft.util.math.Direction.NORTH;
import static net.minecraft.util.math.Direction.SOUTH;
import static net.minecraft.util.math.Direction.WEST;

public class BranchTreeDecorator extends TreeDecorator {
	public static final Codec<BranchTreeDecorator> CODEC = BlockState.CODEC
			.fieldOf("log")
			.xmap(BranchTreeDecorator::new, decorator -> decorator.log)
			.codec();
	private final BlockState log;

	public BranchTreeDecorator(BlockState log) {
		this.log = log;
	}

	@Override
	protected TreeDecoratorType<?> getType() {
		return WanderTreeDecoratorTypes.BRANCH_TREE_DECORATOR;
	}

	@Override
	public void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, List<BlockPos> logPositions, List<BlockPos> leavesPositions) {
		List<BlockPos> possiblePositions = new ArrayList<>(logPositions);
		possiblePositions.sort((o1, o2) -> IntComparators.OPPOSITE_COMPARATOR.compare(o1.getY(), o2.getY()));
		possiblePositions = possiblePositions.stream().filter(pos -> {
			for (Direction direction: new Direction[] { NORTH, SOUTH, EAST, WEST }) {
				if (!world.testBlockState(pos.offset(direction), state -> state.isAir() || state.isOf(Blocks.WATER))) return false;
			}

			return true;
		}).toList();

		if (possiblePositions.size() < 4) return;

		for (Direction direction: new Direction[] { NORTH, SOUTH, EAST, WEST }) {
			BlockPos pos = possiblePositions.get(0).offset(direction);

			replacer.accept(pos, log);
		}
	}
}
