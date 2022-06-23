package io.github.redstoneparadox.wander.util;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;

import java.util.List;

import static net.minecraft.util.math.Direction.EAST;
import static net.minecraft.util.math.Direction.NORTH;
import static net.minecraft.util.math.Direction.SOUTH;
import static net.minecraft.util.math.Direction.WEST;

public record DirectionProvider(List<Direction> directions) {
	public static Codec<DirectionProvider> CODEC = Codec.list(Direction.CODEC)
			.fieldOf("directions")
			.xmap(DirectionProvider::new, provider -> provider.directions)
			.codec();

	public Direction get(RandomGenerator generator) {
		int index = generator.nextInt(directions.size());

		return directions.get(index);
	}

	public static DirectionProvider horizontal() {
		return new DirectionProvider(List.of(NORTH, SOUTH, EAST, WEST));
	}
}
