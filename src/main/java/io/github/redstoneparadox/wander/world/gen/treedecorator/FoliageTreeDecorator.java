package io.github.redstoneparadox.wander.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.redstoneparadox.wander.init.WanderTreeDecoratorTypes;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

import static net.minecraft.util.math.Direction.UP;

public class FoliageTreeDecorator extends TreeDecorator {
	public static final Codec<FoliageTreeDecorator> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			BlockStateProvider.TYPE_CODEC.fieldOf("provider").forGetter(decorator -> decorator.provider),
			Codec.floatRange(0.0f, 1.0f).fieldOf("probability").forGetter(decorator -> decorator.probability)
		).apply(instance, FoliageTreeDecorator::new)
	);
	private final BlockStateProvider provider;
	private final float probability;

	public FoliageTreeDecorator(BlockStateProvider provider, float probability) {
		this.provider = provider;
		this.probability = probability;
	}

	@Override
	protected TreeDecoratorType<?> getType() {
		return WanderTreeDecoratorTypes.FOLIAGE_TREE_DECORATOR;
	}

	@Override
	public void generate(class_7402 arg) {

	}

	public void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, RandomGenerator random, List<BlockPos> logPositions, List<BlockPos> leavesPositions) {
		for (BlockPos logPos: logPositions) {
			BlockPos pos = logPos.offset(UP);

			if (random.nextFloat() <= probability) {
				BlockState state = provider.getBlockState(random, pos);
				replacer.accept(pos, state);
			}
		}
	}
}
