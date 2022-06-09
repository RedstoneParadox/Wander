package io.github.redstoneparadox.wander;

import io.github.redstoneparadox.wander.init.WanderBlocks;
import io.github.redstoneparadox.wander.init.WanderFeatures;
import io.github.redstoneparadox.wander.init.WanderItems;
import io.github.redstoneparadox.wander.init.WanderPlacedFeatures;
import net.minecraft.util.Holder;
import net.minecraft.world.gen.feature.PlacedFeature;
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
		WanderFeatures.init(id);

		final Holder<PlacedFeature> foo = WanderPlacedFeatures.OAK_FALLEN_TREE;

		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());
	}
}
