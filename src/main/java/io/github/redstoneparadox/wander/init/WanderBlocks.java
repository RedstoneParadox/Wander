package io.github.redstoneparadox.wander.init;

import io.github.redstoneparadox.wander.Wander;
import io.github.redstoneparadox.wander.block.HollowLogBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;

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

	private static Block register(Identifier id, Block block) {
		return Registry.register(Registry.BLOCK, id, block);
	}
}
