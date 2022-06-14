package io.github.redstoneparadox.wander.mixin;

import io.github.redstoneparadox.wander.block.ExtendedLeavesBlock;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelSet;
import net.minecraft.world.ModifiableWorld;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.TreeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Mixin(TreeFeature.class)
public class TreeFeatureMixin {
	@Shadow
	private static void setBlockStateWithoutUpdatingNeighbors(ModifiableWorld world, BlockPos pos, BlockState state) {

	}

	@Inject(method = "placeLogsAndLeaves", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/WorldAccess;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;", ordinal = 0), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
	private static void placeLogsAndLeaves_addExtendedLeavesToList(WorldAccess world, BlockBox box, Set<BlockPos> trunkPositions, Set<BlockPos> decorationPositions, Set<BlockPos> set, CallbackInfoReturnable<VoxelSet> cir, List<Set<BlockPos>> list, VoxelSet voxelSet, int i, BlockPos.Mutable mutable) {
		BlockState blockState = world.getBlockState(mutable);
		if (blockState.contains(ExtendedLeavesBlock.DISTANCE)) {
			list.get(0).add(mutable.toImmutable());
			setBlockStateWithoutUpdatingNeighbors(world, mutable, blockState.with(ExtendedLeavesBlock.DISTANCE, 1));
			if (box.contains(mutable)) {
				voxelSet.set(mutable.getX() - box.getMinX(), mutable.getY() - box.getMinY(), mutable.getZ() - box.getMinZ());
			}
		}
	}

	@Inject(method = "placeLogsAndLeaves", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/WorldAccess;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;", ordinal = 1), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
	private static void placeLogsAndLeaves_doSomethingElseWithExtendedLeaves(WorldAccess world, BlockBox box, Set<BlockPos> trunkPositions, Set<BlockPos> decorationPositions, Set<BlockPos> set, CallbackInfoReturnable<VoxelSet> cir, List list, VoxelSet voxelSet, int i, BlockPos.Mutable mutable, int k, Set set2, Set set3, Iterator var12, BlockPos blockPos2, Direction[] var14, int var15, int var16, Direction direction2) {
		BlockState blockState2 = world.getBlockState(mutable);
		if (blockState2.contains(ExtendedLeavesBlock.DISTANCE)) {
			int l = blockState2.get(ExtendedLeavesBlock.DISTANCE);
			if (l > k + 1) {
				BlockState blockState3 = blockState2.with(ExtendedLeavesBlock.DISTANCE, k + 1);
				setBlockStateWithoutUpdatingNeighbors(world, mutable, blockState3);
				if (box.contains(mutable)) {
					voxelSet.set(mutable.getX() - box.getMinX(), mutable.getY() - box.getMinY(), mutable.getZ() - box.getMinZ());
				}

				set3.add(mutable.toImmutable());
			}
		}
	}
}
