package io.github.redstoneparadox.wander.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.redstoneparadox.wander.init.WanderTreeDecoratorTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

import static net.minecraft.util.math.Direction.EAST;
import static net.minecraft.util.math.Direction.NORTH;
import static net.minecraft.util.math.Direction.SOUTH;
import static net.minecraft.util.math.Direction.UP;
import static net.minecraft.util.math.Direction.WEST;

public class MossTreeDecorator extends TreeDecorator {
	public static final Codec<MossTreeDecorator> CODEC = Codec.floatRange(0.0f, 1.0f)
			.fieldOf("probability")
			.xmap(MossTreeDecorator::new, decorator -> decorator.probability)
			.codec();
	private final float probability;

	public MossTreeDecorator(float probability) {
		this.probability = probability;
	}

	@Override
	protected TreeDecoratorType<?> getType() {
		return WanderTreeDecoratorTypes.MOSS_TREE_DECORATOR;
	}

	@Override
	public void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, List<BlockPos> logPositions, List<BlockPos> leavesPositions) {
		for (BlockPos logPos: logPositions) {
			for (Direction direction: new Direction[] {NORTH, SOUTH, EAST, WEST, UP}) {
				BlockPos pos = logPos.offset(direction);

				if (random.nextFloat() <= probability && !logPositions.contains(pos)) {
					replacer.accept(pos, Blocks.MOSS_CARPET.getDefaultState());
				}
			}
		}
	}
}
