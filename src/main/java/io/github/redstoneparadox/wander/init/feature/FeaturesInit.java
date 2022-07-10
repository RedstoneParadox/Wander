package io.github.redstoneparadox.wander.init.feature;

import io.github.redstoneparadox.wander.Wander;
import io.github.redstoneparadox.wander.world.gen.feature.ExtendedTreeFeature;
import io.github.redstoneparadox.wander.world.gen.feature.FallenTreeFeature;
import io.github.redstoneparadox.wander.world.gen.feature.FallenTreeFeatureConfig;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class FeaturesInit {
	public static Feature<FallenTreeFeatureConfig> FALLEN_TREE = register(
			Wander.id("fallen_tree"),
			new FallenTreeFeature(FallenTreeFeatureConfig.CODEC)
	);
	public static Feature<TreeFeatureConfig> EXTENDED_TREE = register(
			Wander.id("extended_tree"),
			new ExtendedTreeFeature(TreeFeatureConfig.CODEC)
	);

	private static <C extends FeatureConfig, F extends Feature<C>> F register(Identifier id, F feature) {
		return Registry.register(Registry.FEATURE, id, feature);
	}
}
