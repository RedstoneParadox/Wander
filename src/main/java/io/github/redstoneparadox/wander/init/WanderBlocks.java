package io.github.redstoneparadox.wander.init;

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
	public static final Block HOLLOW_OAK_LOG = createHollowLogBlock(MapColor.OAK_TAN, MapColor.SPRUCE_BROWN);
	public static final Block HOLLOW_SPRUCE_LOG = createHollowLogBlock(MapColor.SPRUCE_BROWN, MapColor.BROWN);
	public static final Block HOLLOW_BIRCH_LOG = createHollowLogBlock(MapColor.PALE_YELLOW, MapColor.OFF_WHITE);
	public static final Block HOLLOW_JUNGLE_LOG = createHollowLogBlock(MapColor.DIRT_BROWN, MapColor.SPRUCE_BROWN);
	public static final Block HOLLOW_ACACIA_LOG = createHollowLogBlock(MapColor.ORANGE, MapColor.STONE_GRAY);
	public static final Block HOLLOW_DARK_OAK_LOG = createHollowLogBlock(MapColor.BROWN, MapColor.BROWN);

	public static void init(String modid) {
		register(modid, "hollow_oak_log", HOLLOW_OAK_LOG);
		register(modid, "hollow_spruce_log", HOLLOW_SPRUCE_LOG);
		register(modid, "hollow_birch_log", HOLLOW_BIRCH_LOG);
		register(modid, "hollow_jungle_log", HOLLOW_JUNGLE_LOG);
		register(modid, "hollow_acacia_log", HOLLOW_ACACIA_LOG);
		register(modid, "hollow_dark_oak_log", HOLLOW_DARK_OAK_LOG);
	}

	private static void register(String modid, String name, Block block) {
		Registry.register(Registry.BLOCK, new Identifier(modid, name), block);
	}

	private static PillarBlock createHollowLogBlock(MapColor topMapColor, MapColor sideMapColor) {
		return new HollowLogBlock(
				AbstractBlock.Settings.of(Material.WOOD, state -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor)
						.strength(2.0F)
						.sounds(BlockSoundGroup.WOOD)
		);
	}
}
