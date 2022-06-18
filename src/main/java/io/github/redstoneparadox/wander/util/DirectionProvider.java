package io.github.redstoneparadox.wander.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;

public class DirectionProvider {
	public static Codec<DirectionProvider> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			Codec.BOOL.fieldOf("horizontal").forGetter(provider -> provider.horizontal)
	).apply(instance, DirectionProvider::new));
	private final boolean horizontal;

	public DirectionProvider(boolean horizontal) {
		this.horizontal = horizontal;
	}

	public Direction get(RandomGenerator generator) {
		int value = generator.range(0, horizontal ? 4 : 6);
		if (horizontal) value += 2;

		return Direction.byId(value);
	}
}
