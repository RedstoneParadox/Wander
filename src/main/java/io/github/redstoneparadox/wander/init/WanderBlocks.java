package io.github.redstoneparadox.wander.init;

import io.github.redstoneparadox.wander.Wander;
import io.github.redstoneparadox.wander.block.ExtendedLeavesBlock;
import io.github.redstoneparadox.wander.block.HollowLogBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

public class WanderBlocks {
	public static final Block HOLLOW_OAK_LOG = register(
			hollowid("oak"),
			createHollowLogBlock(MapColor.OAK_TAN, MapColor.SPRUCE_BROWN)
	);
	public static final Block HOLLOW_SPRUCE_LOG = register(
			hollowid("spruce"),
			createHollowLogBlock(MapColor.SPRUCE_BROWN, MapColor.BROWN)
	);
	public static final Block HOLLOW_BIRCH_LOG = register(
			hollowid("birch"),
			createHollowLogBlock(MapColor.PALE_YELLOW, MapColor.OFF_WHITE)
	);
	public static final Block HOLLOW_JUNGLE_LOG = register(
			hollowid("jungle"),
			createHollowLogBlock(MapColor.DIRT_BROWN, MapColor.SPRUCE_BROWN)
	);
	public static final Block HOLLOW_ACACIA_LOG = register(
			hollowid("acacia"),
			createHollowLogBlock(MapColor.ORANGE, MapColor.STONE_GRAY)
	);
	public static final Block HOLLOW_DARK_OAK_LOG = register(
			hollowid("dark_oak"),
			createHollowLogBlock(MapColor.BROWN, MapColor.BROWN)
	);

	public static final Block WILLOW_LOG = register(
			Wander.id("willow_log"),
			createLogBlock(MapColor.PALE_GREEN, MapColor.BROWN)
	);
	public static final Block WILLOW_LEAVES = register(
			Wander.id("willow_leaves"),
			createExtendedLeavesBlock(BlockSoundGroup.GRASS)
	);


	private static Identifier hollowid(String name) {
		return Wander.id("hollow_" + name + "_log");
	}

	private static PillarBlock createHollowLogBlock(MapColor topMapColor, MapColor sideMapColor) {
		return new HollowLogBlock(
				AbstractBlock.Settings.of(Material.WOOD, state -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor)
						.strength(2.0F)
						.sounds(BlockSoundGroup.WOOD)
		);
	}

	private static PillarBlock createLogBlock(MapColor topMapColor, MapColor sideMapColor) {
		return new PillarBlock(
				AbstractBlock.Settings.of(Material.WOOD, state -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor)
						.strength(2.0F)
						.sounds(BlockSoundGroup.WOOD)
		);
	}

	private static LeavesBlock createLeavesBlock(BlockSoundGroup soundGroup) {
		return new LeavesBlock(
				AbstractBlock.Settings.of(Material.LEAVES)
						.strength(0.2F)
						.ticksRandomly()
						.sounds(soundGroup)
						.nonOpaque()
						.allowsSpawning(WanderBlocks::canSpawnOnLeaves)
						.suffocates(WanderBlocks::never)
						.blockVision(WanderBlocks::never)
		);
	}

	private static ExtendedLeavesBlock createExtendedLeavesBlock(BlockSoundGroup soundGroup) {
		return new ExtendedLeavesBlock(
				AbstractBlock.Settings.of(Material.LEAVES)
						.strength(0.2F)
						.ticksRandomly()
						.sounds(soundGroup)
						.nonOpaque()
						.allowsSpawning(WanderBlocks::canSpawnOnLeaves)
						.suffocates(WanderBlocks::never)
						.blockVision(WanderBlocks::never)
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

	private static Block register(Identifier id, Block block) {
		return Registry.register(Registry.BLOCK, id, block);
	}
}
