package io.github.redstoneparadox.wander.world.gen.fallentreedecorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.Collection;

import static net.minecraft.util.math.Direction.UP;

public class FoliageFallenTreeDecorator extends FallenTreeDecorator {
	public static final Codec<FoliageFallenTreeDecorator> CODEC = RecordCodecBuilder.create(instance -> instance.group(
					BlockStateProvider.TYPE_CODEC.fieldOf("provider").forGetter(decorator -> decorator.provider),
					Codec.floatRange(0.0f, 1.0f).fieldOf("probability").forGetter(decorator -> decorator.probability)
			).apply(instance, FoliageFallenTreeDecorator::new)
	);
	private final BlockStateProvider provider;
	private final float probability;

	public FoliageFallenTreeDecorator(BlockStateProvider provider, float probability) {
		this.provider = provider;
		this.probability = probability;
	}

	@Override
	protected FallenTreeDecoratorType<?> getType() {
		return FallenTreeDecoratorType.FOLIAGE;
	}

	@Override
	public void generate(StructureWorldAccess world, RandomGenerator random, Collection<BlockPos> logPositions) {
		for (BlockPos logPos: logPositions) {
			BlockPos pos = logPos.offset(UP);

			if (random.nextFloat() <= probability) {
				BlockState state = provider.getBlockState(random, pos);
				world.setBlockState(pos, state, Block.NOTIFY_ALL);
			}
		}
	}
}
