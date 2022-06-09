package io.github.redstoneparadox.wander.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class WanderItems {
	public static final BlockItem HOLLOW_ACACIA_LOG = new BlockItem(
			WanderBlocks.HOLLOW_ACACIA_LOG,
			new QuiltItemSettings().group(ItemGroup.BUILDING_BLOCKS)
	);

	public static void init(String modid) {
		register(modid, "hollow_acacia_log", HOLLOW_ACACIA_LOG);
	}

	private static void register(String modid, String name, Item item) {
		Registry.register(Registry.ITEM, new Identifier(modid, name), item);
	}
}
