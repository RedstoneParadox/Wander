package io.github.redstoneparadox.wander.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Waterloggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class ExtendedLeavesBlock extends Block implements Waterloggable {
	public static final int MAX_DISTANCE = 15;
	public static final IntProperty DISTANCE = IntProperty.of("distance", 1, 15);
	public static final BooleanProperty PERSISTENT = Properties.PERSISTENT;
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
	private static final int TICK_DELAY = 1;

	public ExtendedLeavesBlock(AbstractBlock.Settings settings) {
		super(settings);
		this.setDefaultState(
				this.stateManager.getDefaultState().with(DISTANCE, 15).with(PERSISTENT, Boolean.FALSE).with(WATERLOGGED, Boolean.FALSE)
		);
	}

	@Override
	public VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
		return VoxelShapes.empty();
	}

	@Override
	public boolean hasRandomTicks(BlockState state) {
		return state.get(DISTANCE) == 15 && !state.get(PERSISTENT);
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
		if (this.canDecay(state)) {
			dropStacks(state, world, pos);
			world.removeBlock(pos, false);
		}

	}

	protected boolean canDecay(BlockState state) {
		return !state.get(PERSISTENT) && state.get(DISTANCE) == 15;
	}

	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
		world.setBlockState(pos, updateDistanceFromLogs(state, world, pos), 3);
	}

	@Override
	public int getOpacity(BlockState state, BlockView world, BlockPos pos) {
		return 1;
	}

	@Override
	public BlockState getStateForNeighborUpdate(
			BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
	) {
		if (state.get(WATERLOGGED)) {
			world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}

		int i = getDistanceFromLog(neighborState) + 1;
		if (i != 1 || state.get(DISTANCE) != i) {
			world.scheduleBlockTick(pos, this, 1);
		}

		return state;
	}

	private static BlockState updateDistanceFromLogs(BlockState state, WorldAccess world, BlockPos pos) {
		int i = 15;
		BlockPos.Mutable mutable = new BlockPos.Mutable();

		for(Direction direction : Direction.values()) {
			mutable.set(pos, direction);
			i = Math.min(i, getDistanceFromLog(world.getBlockState(mutable)) + 1);
			if (i == 1) {
				break;
			}
		}

		return state.with(DISTANCE, i);
	}

	private static int getDistanceFromLog(BlockState state) {
		if (state.isIn(BlockTags.LOGS)) {
			return 0;
		} else {
			Block block = state.getBlock();
			return block instanceof LeavesBlock ? state.get(LeavesBlock.DISTANCE) : block instanceof ExtendedLeavesBlock ? state.get(DISTANCE) : 15;
		}
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
	}

	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, RandomGenerator random) {
		if (world.hasRain(pos.up())) {
			if (random.nextInt(15) == 1) {
				BlockPos blockPos = pos.down();
				BlockState blockState = world.getBlockState(blockPos);
				if (!blockState.isOpaque() || !blockState.isSideSolidFullSquare(world, blockPos, Direction.UP)) {
					double d = (double)pos.getX() + random.nextDouble();
					double e = (double)pos.getY() - 0.05;
					double f = (double)pos.getZ() + random.nextDouble();
					world.addParticle(ParticleTypes.DRIPPING_WATER, d, e, f, 0.0, 0.0, 0.0);
				}
			}
		}
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(DISTANCE, PERSISTENT, WATERLOGGED);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
		BlockState blockState = this.getDefaultState()
				.with(PERSISTENT, Boolean.TRUE)
				.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
		return updateDistanceFromLogs(blockState, ctx.getWorld(), ctx.getBlockPos());
	}
}
