package io.github.redstoneparadox.wander.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.treedecorator.TreeDecorator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		Random random = context.getRandom();
		BlockPos origin = context.getOrigin();
		StructureWorldAccess worldAccess = context.getWorld();
		BlockState stump = config.stump();
		int height = config.height().get(random);
		Direction direction;
		switch (config.direction().get(random)) {
			case 0 -> direction = NORTH;
			case 1 -> direction = EAST;
			case 2 -> direction = SOUTH;
			case 3 -> direction = WEST;
			default -> throw new IllegalStateException("Unexpected value: " + config.direction().get(random));
		}
		List<TreeDecorator> decorators = config.decorators();
		List<BlockPos> logPositions = new ArrayList<>();

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

		for (TreeDecorator decorator: decorators) {
			decorator.generate(worldAccess, (pos, state) -> {
				if (state.canPlaceAt(worldAccess, pos)) {
					worldAccess.setBlockState(pos, state, 19);
				}
			}, random, logPositions, new ArrayList<>());
		}

		return true;
	}
}
