package io.github.redstoneparadox.wander;

import io.github.redstoneparadox.wander.init.WanderItems;
import io.github.redstoneparadox.wander.init.WanderPlacedFeatures;
import io.github.redstoneparadox.wander.init.WanderPlacementModifierTypes;
import io.github.redstoneparadox.wander.init.WanderTreeDecoratorTypes;
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

		WanderItems.init();
		WanderTreeDecoratorTypes.init();
		WanderPlacementModifierTypes.init();
		WanderPlacedFeatures.addFeaturesToVanilla();

		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());
	}

	public static Identifier id(String name) {
		return new Identifier(id, name);
	}
}
