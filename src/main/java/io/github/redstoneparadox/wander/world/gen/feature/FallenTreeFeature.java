package io.github.redstoneparadox.wander.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class FallenTreeFeature extends Feature<FallenTreeFeatureConfig> {
	public FallenTreeFeature(Codec<FallenTreeFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeatureContext<FallenTreeFeatureConfig> context) {
		FallenTreeFeatureConfig config = context.getConfig();
		Random random = context.getRandom();
		BlockPos origin = context.getOrigin();
		BlockState log = config.log().getBlockState(random, origin);

		return false;
	}
}
