package io.github.redstoneparadox.wander.world.gen.placementmodifiers;

import com.mojang.serialization.Codec;
import io.github.redstoneparadox.wander.init.feature.PlacementModifierTypesInit;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.decorator.DecoratorContext;
import net.minecraft.world.gen.decorator.PlacementModifierType;
import net.minecraft.world.gen.feature.PlacementModifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class UnderwaterPlacementModifier extends PlacementModifier {
	public static UnderwaterPlacementModifier INSTANCE = new UnderwaterPlacementModifier();
	public static Codec<UnderwaterPlacementModifier> CODEC = Codec.unit(INSTANCE);

	@Override
	public Stream<BlockPos> getPositions(DecoratorContext context, RandomGenerator random, BlockPos pos) {
		List<BlockPos> positions = new ArrayList<>();
		StructureWorldAccess access = context.getWorld();

		if (access.getBlockState(pos).isOf(Blocks.WATER) && access.getBlockState(pos.up()).isOf(Blocks.WATER)) {
			positions.add(pos);
		}

		return positions.stream();
	}

	@Override
	public PlacementModifierType<?> getType() {
		return PlacementModifierTypesInit.UNDERWATER_PLACEMENT_MODIFIER;
	}
}
