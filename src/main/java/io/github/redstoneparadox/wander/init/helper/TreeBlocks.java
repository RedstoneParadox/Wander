package io.github.redstoneparadox.wander.init.helper;

import io.github.redstoneparadox.wander.Wander;
import io.github.redstoneparadox.wander.block.ExtendedLeavesBlock;
import io.github.redstoneparadox.wander.block.HollowLogBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

public class TreeBlocks {
	public final Block leaves;
	public final Block sapling;
	public final Block log;
	public final Block strippedLog;
	public final Block hollowLog;
	public final Block strippedHollowLog;
	public final Block wood;
	public final Block strippedWood;
	public final Block planks;
	public final Block stairs;
	public final Block slab;
	public final Block door;
	public final Block trapdoor;
	public final Block fence;
	public final Block fenceGate;

	private TreeBlocks(String name, MapColor woodColor, MapColor barkColor, boolean extendedLeaves) {
		leaves = register(name, "leaves", extendedLeaves ? createExtendedLeavesBlock() : createLeavesBlock());
		sapling = register(name, "sapling", null);
		log = register(name, "log", createLogBlock(woodColor, barkColor, false));
		strippedLog = register("stripped", name, "log", createLogBlock(woodColor, woodColor, false));
		hollowLog = register("hollow", name, "log", createLogBlock(woodColor, barkColor, true));
		strippedHollowLog = register("stripped_hollow", name, "log", createLogBlock(woodColor, barkColor, true));
		wood = register(name, "wood", createLogBlock(barkColor, barkColor, false));;
		strippedWood = register("stripped", name, "wood", createLogBlock(woodColor, woodColor, false));
		planks = register(name, "planks", new Block(AbstractBlock.Settings
				.of(Material.WOOD, woodColor)
				.strength(2.0F, 3.0F)
				.sounds(BlockSoundGroup.WOOD)
		));
		stairs = register(name, "stairs", new StairsBlock(planks.getDefaultState(), AbstractBlock.Settings.copy(planks)));
		slab = register(name, "slab", new SlabBlock(AbstractBlock.Settings.copy(planks)));
		door = register(name, "door", new DoorBlock(AbstractBlock.Settings
				.of(Material.WOOD, woodColor)
				.strength(3.0F)
				.sounds(BlockSoundGroup.WOOD)
				.nonOpaque()
		));
		trapdoor = register(name, "trapdoor", new TrapdoorBlock(AbstractBlock.Settings
				.of(Material.WOOD, MapColor.OAK_TAN)
				.strength(3.0F)
				.sounds(BlockSoundGroup.WOOD)
				.nonOpaque()
				.allowsSpawning(((blockState, blockView, blockPos, object) -> false))
		));
		fence = register(name, "fence", new FenceBlock(AbstractBlock.Settings.copy(planks)));
		fenceGate = register(name, "fence_gate", new FenceGateBlock(AbstractBlock.Settings.copy(planks)));
	}

	public static TreeBlocks create(String name, MapColor woodColor, MapColor barkColor) {
		return create(name, woodColor, barkColor, false);
	}

	public static TreeBlocks create(String name, MapColor woodColor, MapColor barkColor, boolean extendedLeaves) {
		return new TreeBlocks(name, woodColor, barkColor, extendedLeaves);
	}

	private static PillarBlock createLogBlock(MapColor topMapColor, MapColor sideMapColor, boolean hollow) {
		AbstractBlock.Settings settings = AbstractBlock.Settings.
				of(Material.WOOD, state -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor)
				.strength(2.0F)
				.sounds(BlockSoundGroup.WOOD);

		return hollow ? new HollowLogBlock(settings) : new PillarBlock(settings);
	}

	private static LeavesBlock createLeavesBlock() {
		return new LeavesBlock(
				AbstractBlock.Settings.of(Material.LEAVES)
						.strength(0.2F)
						.ticksRandomly()
						.sounds(BlockSoundGroup.GRASS)
						.nonOpaque()
						.allowsSpawning(TreeBlocks::canSpawnOnLeaves)
						.suffocates(TreeBlocks::never)
						.blockVision(TreeBlocks::never)
		);
	}

	private static ExtendedLeavesBlock createExtendedLeavesBlock() {
		return new ExtendedLeavesBlock(
				AbstractBlock.Settings.of(Material.LEAVES)
						.strength(0.2F)
						.ticksRandomly()
						.sounds(BlockSoundGroup.GRASS)
						.nonOpaque()
						.allowsSpawning(TreeBlocks::canSpawnOnLeaves)
						.suffocates(TreeBlocks::never)
						.blockVision(TreeBlocks::never)
		);
	}

	private static Boolean canSpawnOnLeaves(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
		return type == EntityType.OCELOT || type == EntityType.PARROT;
	}

	/**
	 * A shortcut to always return {@code false} a context predicate, used as
	 * {@code settings.solidBlock(Blocks::never)}.
	 */
	private static boolean never(BlockState state, BlockView world, BlockPos pos) {
		return false;
	}

	private static Block register(String name, Block block) {
		return Registry.register(Registry.BLOCK, Wander.id(name), block);
	}

	private static Block register(String name, String suffix, Block block) {
		return Registry.register(Registry.BLOCK, Wander.id(name + "_" + suffix), block);
	}

	private static Block register(String prefix, String name, String suffix, Block block) {
		return Registry.register(Registry.BLOCK, Wander.id(prefix + "_" + name + "_" + suffix), block);
	}
}
