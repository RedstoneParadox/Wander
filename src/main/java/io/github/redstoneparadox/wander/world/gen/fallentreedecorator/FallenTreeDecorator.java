package io.github.redstoneparadox.wander.world.gen.fallentreedecorator;

import com.mojang.serialization.Codec;
import io.github.redstoneparadox.wander.init.WanderRegistries;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;

import java.util.Collection;
import java.util.random.RandomGenerator;

public abstract class FallenTreeDecorator {
	public static final Codec<FallenTreeDecorator> TYPE_CODEC = WanderRegistries.FALLEN_TREE_DECORATOR_TYPE
			.getCodec()
			.dispatch(FallenTreeDecorator::getType, FallenTreeDecoratorType::getCodec);

	protected abstract FallenTreeDecoratorType<?> getType();

	public abstract void generate(StructureWorldAccess world, RandomGenerator random, Collection<BlockPos> logPositions);
}
