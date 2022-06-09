package io.github.redstoneparadox.wander.init;

import io.github.redstoneparadox.wander.Wander;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class WanderItems {
	public static final Item HOLLOW_OAK_LOG = register(
			hollowid("oak"),
			new BlockItem(
					WanderBlocks.HOLLOW_OAK_LOG,
					new QuiltItemSettings().group(ItemGroup.BUILDING_BLOCKS)
			)
	);
	public static final Item HOLLOW_SPRUCE_LOG = register(
			hollowid("spruce"),
			new BlockItem(
					WanderBlocks.HOLLOW_SPRUCE_LOG,
					new QuiltItemSettings().group(ItemGroup.BUILDING_BLOCKS)
			)
	);
	public static final Item HOLLOW_BIRCH_LOG = register(
			hollowid("birch"),
			new BlockItem(
					WanderBlocks.HOLLOW_BIRCH_LOG,
					new QuiltItemSettings().group(ItemGroup.BUILDING_BLOCKS)
			)
	);
	public static final Item HOLLOW_JUNGLE_LOG = register(
			hollowid("jungle"),
			new BlockItem(
					WanderBlocks.HOLLOW_JUNGLE_LOG,
					new QuiltItemSettings().group(ItemGroup.BUILDING_BLOCKS)
			)
	);
	public static final Item HOLLOW_ACACIA_LOG = register(
			hollowid("acacia"),
			new BlockItem(
					WanderBlocks.HOLLOW_ACACIA_LOG,
					new QuiltItemSettings().group(ItemGroup.BUILDING_BLOCKS)
			)
	);
	public static final Item HOLLOW_DARK_OAK_LOG = register(
			hollowid("dark_oak"),
			new BlockItem(
					WanderBlocks.HOLLOW_DARK_OAK_LOG,
					new QuiltItemSettings().group(ItemGroup.BUILDING_BLOCKS)
			)
	);

	public static void init() {

	}

	private static Identifier hollowid(String name) {
		return Wander.id("hollow_" + name + "_log");
	}


	private static Item register(Identifier id, Item item) {
		return Registry.register(Registry.ITEM, id, item);
	}
}
