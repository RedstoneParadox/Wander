package io.github.redstoneparadox.wander.world.gen.fallentreedecorator;

import com.mojang.serialization.Codec;
import io.github.redstoneparadox.wander.init.WanderRegistries;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FallenTreeDecoratorType<D extends FallenTreeDecorator> {
	private final Codec<D> codec;

	public static  <E extends FallenTreeDecorator> FallenTreeDecoratorType<E> register(Identifier id, Codec<E> codec) {
		return Registry.register(WanderRegistries.FALLEN_TREE_DECORATOR_TYPE, id, new FallenTreeDecoratorType<>(codec));
	}

	private FallenTreeDecoratorType(Codec<D> codec) {
		this.codec = codec;
	}

	public Codec<D> getCodec() {
		return codec;
	}
}
