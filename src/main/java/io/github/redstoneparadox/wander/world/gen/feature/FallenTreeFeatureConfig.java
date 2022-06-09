package io.github.redstoneparadox.wander.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record FallenTreeFeatureConfig(
		BlockStateProvider log,
		IntProvider height,
		IntProvider direction
) implements FeatureConfig {
	public static final Codec<FallenTreeFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			BlockStateProvider.TYPE_CODEC.fieldOf("log").forGetter(FallenTreeFeatureConfig::log),
			IntProvider.VALUE_CODEC.fieldOf("height").forGetter(FallenTreeFeatureConfig::height),
			IntProvider.VALUE_CODEC.fieldOf("direction").forGetter(FallenTreeFeatureConfig::direction)
	).apply(instance, instance.stable(FallenTreeFeatureConfig::new)));
}
