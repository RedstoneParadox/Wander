package io.github.redstoneparadox.wander.init.feature;

import com.mojang.serialization.Codec;
import io.github.redstoneparadox.wander.Wander;
import io.github.redstoneparadox.wander.world.gen.placementmodifiers.UnderwaterPlacementModifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.PlacementModifierType;
import net.minecraft.world.gen.feature.PlacementModifier;

public class PlacementModifierTypesInit {
	public static final PlacementModifierType<UnderwaterPlacementModifier> UNDERWATER_PLACEMENT_MODIFIER = register(
			Wander.id("underwater_placement_modifier").toString(),
			UnderwaterPlacementModifier.CODEC
	);

	public static void init() {

	}

	private static <P extends PlacementModifier> PlacementModifierType<P> register(String id, Codec<P> codec) {
		return Registry.register(Registry.PLACEMENT_MODIFIER_TYPE, id, () -> codec);
	}
}
