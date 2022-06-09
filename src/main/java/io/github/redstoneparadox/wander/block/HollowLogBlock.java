package io.github.redstoneparadox.wander.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

import java.util.Objects;

public class HollowLogBlock extends PillarBlock implements Waterloggable {
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
	protected static final VoxelShape NORTH_SIDE = Block.createCuboidShape(0, 0, 0, 16, 16, 2);
	protected static final VoxelShape SOUTH_SIDE = Block.createCuboidShape(0, 0, 14, 16, 16, 16);
	protected static final VoxelShape WEST_SIDE = Block.createCuboidShape(0, 0, 0, 2, 16, 16);
	protected static final VoxelShape EAST_SIDE = Block.createCuboidShape(14, 0, 0, 16, 16, 16);
	protected static final VoxelShape BOTTOM_SIDE = Block.createCuboidShape(0, 0, 0, 16, 2, 16);
	protected static final VoxelShape TOP_SIDE = Block.createCuboidShape(0, 14, 0, 16, 16, 16);
	protected static final VoxelShape X_AXIS = compose(NORTH_SIDE, SOUTH_SIDE, TOP_SIDE, BOTTOM_SIDE);
	protected static final VoxelShape Y_AXIS = compose(NORTH_SIDE, SOUTH_SIDE, EAST_SIDE, WEST_SIDE);
	protected static final VoxelShape Z_AXIS = compose(EAST_SIDE, WEST_SIDE, TOP_SIDE, BOTTOM_SIDE);

	public HollowLogBlock(Settings settings) {
		super(settings);
	}

	private static VoxelShape compose(VoxelShape first, VoxelShape second, VoxelShape third, VoxelShape fourth) {
		VoxelShape shape = VoxelShapes.union(first, second);
		shape = VoxelShapes.union(shape, third);

		return VoxelShapes.union(shape, fourth);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		switch (state.get(AXIS)) {
			case X -> {
				return X_AXIS;
			}
			case Y -> {
				return Y_AXIS;
			}
			case Z -> {
				return Z_AXIS;
			}
			default -> throw new IllegalStateException("Unexpected value: " + state.get(AXIS));
		}
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		BlockPos blockPos = ctx.getBlockPos();
		FluidState fluidState = ctx.getWorld().getFluidState(blockPos);

		return Objects.requireNonNull(super.getPlacementState(ctx)).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (state.get(WATERLOGGED)) {
			world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}
		
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.add(WATERLOGGED);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
	}
}
