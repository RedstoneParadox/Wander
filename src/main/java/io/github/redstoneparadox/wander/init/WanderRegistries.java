package io.github.redstoneparadox.wander.init;

import io.github.redstoneparadox.wander.Wander;
import io.github.redstoneparadox.wander.world.gen.fallentreedecorator.FallenTreeDecoratorType;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.util.registry.SimpleRegistry;

public class WanderRegistries {
	public static final SimpleRegistry<FallenTreeDecoratorType> FALLEN_TREE_DECORATOR_TYPE = FabricRegistryBuilder
			.createSimple(FallenTreeDecoratorType.class, Wander.id("fallen_tree_decorator"))
			.attribute(RegistryAttribute.MODDED)
			.attribute(RegistryAttribute.SYNCED)
			.buildAndRegister();
}
