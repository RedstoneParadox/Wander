package io.github.redstoneparadox.wander.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.redstoneparadox.wander.util.DirectionProvider;
import io.github.redstoneparadox.wander.world.gen.fallentreedecorator.FallenTreeDecorator;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;

import java.util.List;

public record FallenTreeFeatureConfig(
		BlockState stump,
		BlockStateProvider log,
		IntProvider height,
		DirectionProvider direction,
		boolean omitStump,
		List<FallenTreeDecorator> decorators
) implements FeatureConfig {
	public static final Codec<FallenTreeFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			BlockState.CODEC.fieldOf("stump").forGetter(FallenTreeFeatureConfig::stump),
			BlockStateProvider.TYPE_CODEC.fieldOf("log").forGetter(FallenTreeFeatureConfig::log),
			IntProvider.VALUE_CODEC.fieldOf("height").forGetter(FallenTreeFeatureConfig::height),
			DirectionProvider.CODEC.fieldOf("direction").forGetter(FallenTreeFeatureConfig::direction),
			Codec.BOOL.fieldOf("omitStump").forGetter(FallenTreeFeatureConfig::omitStump),
			Codec.list(FallenTreeDecorator.TYPE_CODEC).fieldOf("decorators").forGetter(FallenTreeFeatureConfig::decorators)
	).apply(instance, instance.stable(FallenTreeFeatureConfig::new)));
}
