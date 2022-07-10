package io.github.redstoneparadox.wander.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.redstoneparadox.wander.init.feature.TreeDecoratorTypesInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class HangingLeavesTreeDecorator extends TreeDecorator {
	public static final Codec<HangingLeavesTreeDecorator> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			BlockState.CODEC.fieldOf("leaf_state").forGetter(decorator -> decorator.leafState),
			Codec.list(BlockState.CODEC).fieldOf("can_replace").forGetter(decorator -> decorator.canReplace),
			IntProvider.VALUE_CODEC.fieldOf("leaf_length").forGetter(decorator -> decorator.leafLength),
			Codec.floatRange(0.0f, 1.0f).fieldOf("probability").forGetter(decorator -> decorator.probability)
	).apply(instance, HangingLeavesTreeDecorator::new));
	private final BlockState leafState;
	private final List<BlockState> canReplace;
	private final IntProvider leafLength;
	private final float probability;

	public HangingLeavesTreeDecorator(BlockState leaves, List<BlockState> canReplace, IntProvider leafLength, float probability) {
		// canReplace.add(Blocks.AIR.getDefaultState());

		this.leafState = leaves;
		this.canReplace = canReplace;
		this.leafLength = leafLength;
		this.probability = probability;
	}

	@Override
	protected TreeDecoratorType<?> getType() {
		return TreeDecoratorTypesInit.HANGING_LEAVES;
	}

	@Override
	public void generate(class_7402 parameters) {
		TestableWorld world = parameters.method_43316();
		BiConsumer<BlockPos, BlockState> replacer = parameters::method_43318;
		RandomGenerator random = parameters.method_43320();
		List<BlockPos> leafPositions = parameters.method_43322();

		List<BlockPos> possiblePositions = new ArrayList<>(leafPositions);
		possiblePositions = possiblePositions.stream().filter(pos -> world.testBlockState(pos.down(), BlockState::isAir)).toList();


		for (BlockPos pos: possiblePositions) {
			if (random.nextFloat() < probability) {
				int length = random.nextInt(leafLength.get(random) + 1);

				for (int i = 0; i < length; i++) {
					BlockPos pos2 = pos.down(i + 1);
					if (leafState.contains(Properties.WATERLOGGED)) {
						replacer.accept(pos2, leafState.with(Properties.WATERLOGGED, world.testBlockState(pos2, state -> state.isOf(Blocks.WATER))));
					} else {
						replacer.accept(pos2, leafState);
					}
				}
			}
		}
	}
}
