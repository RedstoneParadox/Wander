package io.github.redstoneparadox.wander.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class WanderItems {
	public static final BlockItem HOLLOW_OAK_LOG = new BlockItem(
			WanderBlocks.HOLLOW_OAK_LOG,
			new QuiltItemSettings().group(ItemGroup.BUILDING_BLOCKS)
	);
	public static final BlockItem HOLLOW_SPRUCE_LOG = new BlockItem(
			WanderBlocks.HOLLOW_SPRUCE_LOG,
			new QuiltItemSettings().group(ItemGroup.BUILDING_BLOCKS)
	);
	public static final BlockItem HOLLOW_BIRCH_LOG = new BlockItem(
			WanderBlocks.HOLLOW_BIRCH_LOG,
			new QuiltItemSettings().group(ItemGroup.BUILDING_BLOCKS)
	);
	public static final BlockItem HOLLOW_JUNGLE_LOG = new BlockItem(
			WanderBlocks.HOLLOW_JUNGLE_LOG,
			new QuiltItemSettings().group(ItemGroup.BUILDING_BLOCKS)
	);
	public static final BlockItem HOLLOW_ACACIA_LOG = new BlockItem(
			WanderBlocks.HOLLOW_ACACIA_LOG,
			new QuiltItemSettings().group(ItemGroup.BUILDING_BLOCKS)
	);
	public static final BlockItem HOLLOW_DARK_OAK_LOG = new BlockItem(
			WanderBlocks.HOLLOW_DARK_OAK_LOG,
			new QuiltItemSettings().group(ItemGroup.BUILDING_BLOCKS)
	);

	public static void init(String modid) {
		register(modid, "hollow_oak_log", HOLLOW_OAK_LOG);
		register(modid, "hollow_spruce_log", HOLLOW_SPRUCE_LOG);
		register(modid, "hollow_birch_log", HOLLOW_BIRCH_LOG);
		register(modid, "hollow_jungle_log", HOLLOW_JUNGLE_LOG);
		register(modid, "hollow_acacia_log", HOLLOW_ACACIA_LOG);
		register(modid, "hollow_dark_oak_log", HOLLOW_DARK_OAK_LOG);
	}

	private static void register(String modid, String name, Item item) {
		Registry.register(Registry.ITEM, new Identifier(modid, name), item);
	}
}
