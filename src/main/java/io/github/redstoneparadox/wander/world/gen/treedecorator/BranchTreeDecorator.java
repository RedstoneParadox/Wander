package io.github.redstoneparadox.wander.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.redstoneparadox.wander.init.WanderTreeDecoratorTypes;
import it.unimi.dsi.fastutil.ints.IntComparators;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
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
	public static final Codec<BranchTreeDecorator> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			BlockState.CODEC.fieldOf("log").forGetter(decorator -> decorator.log),
			Codec.floatRange(0.0f, 1.0f).fieldOf("probability").forGetter(decorator -> decorator.probability),
			Codec.floatRange(0.0f, 1.0f).fieldOf("multiplier").forGetter(decorator -> decorator.multiplier)
	).apply(instance, BranchTreeDecorator::new));
	private final BlockState log;
	private final float probability;
	private final float multiplier;

	public BranchTreeDecorator(BlockState log, float probability, float multiplier) {
		this.log = log;
		this.probability = probability;
		this.multiplier = multiplier;
	}

	@Override
	protected TreeDecoratorType<?> getType() {
		return WanderTreeDecoratorTypes.BRANCH_TREE_DECORATOR;
	}


	@Override
	public void generate(class_7402 parameters) {
		TestableWorld world = parameters.method_43316();
		BiConsumer<BlockPos, BlockState> replacer = parameters::method_43318;
		RandomGenerator random = parameters.method_43320();
		List<BlockPos> logPositions = parameters.method_43321();

		List<BlockPos> possiblePositions = new ArrayList<>(logPositions);
		possiblePositions.sort((o1, o2) -> IntComparators.OPPOSITE_COMPARATOR.compare(o1.getY(), o2.getY()));
		possiblePositions = possiblePositions.stream().filter(pos -> {
			for (Direction direction: new Direction[] { NORTH, SOUTH, EAST, WEST }) {
				if (!world.testBlockState(pos.offset(direction), state -> state.isAir() || state.isOf(Blocks.WATER))) return false;
			}

			return true;
		}).toList();

		if (possiblePositions.size() < 4) return;

		possiblePositions = possiblePositions.subList(0, possiblePositions.size() - 3);
		float trueProbability = probability;

		for (Direction direction: new Direction[] { NORTH, SOUTH, EAST, WEST }) {
			if (random.nextFloat() <= trueProbability) {
				int index = random.nextInt(possiblePositions.size());
				BlockPos pos = possiblePositions.get(index).offset(direction);

				if (log.getBlock() instanceof PillarBlock) {
					replacer.accept(pos, log.with(PillarBlock.AXIS, direction.getAxis()));
				} else if (log.getBlock() instanceof FacingBlock) {
					replacer.accept(pos, log.with(FacingBlock.FACING, direction));
				} else {
					replacer.accept(pos, log);
				}

				trueProbability *= multiplier;
			}
		}
	}
}
