package io.github.redstoneparadox.wander.world.gen.fallentreedecorator;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.StructureWorldAccess;

import java.util.Collection;

import static net.minecraft.util.math.Direction.EAST;
import static net.minecraft.util.math.Direction.NORTH;
import static net.minecraft.util.math.Direction.SOUTH;
import static net.minecraft.util.math.Direction.UP;
import static net.minecraft.util.math.Direction.WEST;

public class MossFallenTreeDecorator extends FallenTreeDecorator {
	public static final Codec<MossFallenTreeDecorator> CODEC = Codec.floatRange(0.0f, 1.0f)
			.fieldOf("probability")
			.xmap(MossFallenTreeDecorator::new, decorator -> decorator.probability)
			.codec();
	private static final BlockState MOSS_CARPET = Blocks.MOSS_CARPET.getDefaultState();
	private final float probability;

	public MossFallenTreeDecorator(float probability) {
		this.probability = probability;
	}

	@Override
	protected FallenTreeDecoratorType<?> getType() {
		return FallenTreeDecoratorType.MOSS;
	}

	@Override
	public void generate(StructureWorldAccess world, RandomGenerator random, Collection<BlockPos> logPositions) {
		for (BlockPos logPos: logPositions) {
			for (Direction direction: new Direction[] {NORTH, SOUTH, EAST, WEST, UP}) {
				BlockPos pos = logPos.offset(direction);
				BlockState support = world.getBlockState(pos.down());

				if (random.nextFloat() <= probability && !logPositions.contains(pos) && support.isSolidBlock(world, pos.down())) {
					world.setBlockState(pos, MOSS_CARPET, Block.NOTIFY_ALL);
				}
			}
		}
	}
}
