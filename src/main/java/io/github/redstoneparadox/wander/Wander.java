package io.github.redstoneparadox.wander;

import io.github.redstoneparadox.wander.init.WanderBlocks;
import io.github.redstoneparadox.wander.init.WanderItems;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Wander implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Wander");

	@Override
	public void onInitialize(ModContainer mod) {
		String id = mod.metadata().id();

		WanderBlocks.init(id);
		WanderItems.init(id);

		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());
	}
}
