package io.github.redstoneparadox.wander.world.gen.structure.function;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface StructureFunction {
	boolean apply(World world, BlockPos pos);
}
