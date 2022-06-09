package io.github.redstoneparadox.wander.world.gen.feature;

import com.mojang.serialization.Codec;
import io.github.redstoneparadox.wander.block.HollowLogBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

import static net.minecraft.util.math.Direction.*;

public class FallenTreeFeature extends Feature<FallenTreeFeatureConfig> {
	public FallenTreeFeature(Codec<FallenTreeFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeatureContext<FallenTreeFeatureConfig> context) {
		FallenTreeFeatureConfig config = context.getConfig();
		Random random = context.getRandom();
		BlockPos origin = context.getOrigin();
		StructureWorldAccess worldAccess = context.getWorld();
		BlockState log = config.log().getBlockState(random, origin);

		if (log.getBlock() instanceof PillarBlock) {
			Direction direction;
			switch (config.direction().get(random)) {
				case 0 -> direction = NORTH;
				case 1 -> direction = EAST;
				case 2 -> direction = SOUTH;
				case 3 -> direction = WEST;
				default -> throw new IllegalStateException("Unexpected value: " + config.direction().get(random));
			}
			int height = config.height().get(random);
			BlockState rotated = log.with(HollowLogBlock.AXIS, direction.getAxis());

			worldAccess.setBlockState(origin, log, Block.NOTIFY_ALL);

			for (int i = 0; i < height; i++) {
				worldAccess.setBlockState(origin.offset(direction, i + 2), rotated, Block.NOTIFY_ALL);
			}
		}

		return false;
	}
}
