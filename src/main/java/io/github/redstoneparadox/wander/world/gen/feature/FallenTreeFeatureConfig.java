package io.github.redstoneparadox.wander.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;

import java.util.List;

public record FallenTreeFeatureConfig(
		BlockState stump,
		BlockStateProvider log,
		IntProvider height,
		IntProvider direction,
		boolean omitStump,
		List<TreeDecorator> decorators
) implements FeatureConfig {
	public static final Codec<FallenTreeFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			BlockState.CODEC.fieldOf("stump").forGetter(FallenTreeFeatureConfig::stump),
			BlockStateProvider.TYPE_CODEC.fieldOf("log").forGetter(FallenTreeFeatureConfig::log),
			IntProvider.VALUE_CODEC.fieldOf("height").forGetter(FallenTreeFeatureConfig::height),
			IntProvider.VALUE_CODEC.fieldOf("direction").forGetter(FallenTreeFeatureConfig::direction),
			Codec.BOOL.fieldOf("omitStump").forGetter(FallenTreeFeatureConfig::omitStump),
			Codec.list(TreeDecorator.TYPE_CODEC).fieldOf("decorators").forGetter(FallenTreeFeatureConfig::decorators)
	).apply(instance, instance.stable(FallenTreeFeatureConfig::new)));
}
