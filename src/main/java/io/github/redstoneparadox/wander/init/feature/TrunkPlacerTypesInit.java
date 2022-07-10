package io.github.redstoneparadox.wander.init.feature;

import com.mojang.serialization.Codec;
import io.github.redstoneparadox.wander.Wander;
import io.github.redstoneparadox.wander.mixin.TrunkPlacerTypeInvoker;
import io.github.redstoneparadox.wander.world.gen.trunkplacer.WillowTrunkPlacer;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class TrunkPlacerTypesInit {
	public static final TrunkPlacerType<WillowTrunkPlacer> WILLOW = register(
			Wander.id("willow"),
			WillowTrunkPlacer.CODEC
	);

	private static <P extends TrunkPlacer> TrunkPlacerType<P> register(Identifier id, Codec<P> codec) {
		return TrunkPlacerTypeInvoker.callRegister(id.toString(), codec);
	}
}
