package io.github.redstoneparadox.wander.world.gen.feature;

import com.mojang.serialization.Codec;
import io.github.redstoneparadox.wander.world.gen.fallentreedecorator.FallenTreeDecorator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static net.minecraft.util.math.Direction.EAST;
import static net.minecraft.util.math.Direction.NORTH;
import static net.minecraft.util.math.Direction.SOUTH;
import static net.minecraft.util.math.Direction.WEST;

public class FallenTreeFeature extends Feature<FallenTreeFeatureConfig> {
	public FallenTreeFeature(Codec<FallenTreeFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeatureContext<FallenTreeFeatureConfig> context) {
		FallenTreeFeatureConfig config = context.getConfig();
		RandomGenerator random = context.getRandom();
		BlockPos origin = context.getOrigin();
		StructureWorldAccess worldAccess = context.getWorld();
		BlockState stump = config.stump();
		int height = config.height().get(random);
		Direction direction = config.direction().get(random);
		List<FallenTreeDecorator> decorators = config.decorators();
		Set<BlockPos> logPositions = new HashSet<>();

		for (int i = 0; i <= height; i++) {
			if (!worldAccess.testBlockState(origin.offset(direction, i + 1), state -> state.isAir() || state.isOf(Blocks.WATER))) {
				return false;
			}
		}

		if (!config.omitStump()) {
			if (!worldAccess.getBlockState(origin.down()).isSolidBlock(worldAccess, origin.down())) return false;

			worldAccess.setBlockState(origin, stump, Block.NOTIFY_ALL);
			if (stump.isFullCube(worldAccess, origin)) logPositions.add(origin);
		}

		BlockPos trunkStart = worldAccess.getTopPosition(Heightmap.Type.OCEAN_FLOOR_WG, origin.offset(direction, 2));

		for (int i = 0; i < height; i++) {
			BlockPos pos = trunkStart.offset(direction, i);
			BlockState log = config.log().getBlockState(random, pos);

			if (log.getBlock() instanceof PillarBlock) {
				log = log.with(PillarBlock.AXIS, direction.getAxis());
			}

			worldAccess.setBlockState(pos, log, Block.NOTIFY_ALL);
			logPositions.add(pos);
		}

		for (FallenTreeDecorator decorator: decorators) {
			decorator.generate(worldAccess, random, logPositions);
		}

		return true;
	}
}
