package io.github.redstoneparadox.wander;

import io.github.redstoneparadox.wander.init.WanderBlocks;
import io.github.redstoneparadox.wander.init.WanderItems;
import io.github.redstoneparadox.wander.init.WanderPlacedFeatures;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Wander implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Wander");
	private static String id = "";

	@Override
	public void onInitialize(ModContainer mod) {
		Wander.id = mod.metadata().id();

		WanderPlacedFeatures.addFeaturesToVanilla();

		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());
	}

	public static Identifier id(String name) {
		return new Identifier(id, name);
	}
}
