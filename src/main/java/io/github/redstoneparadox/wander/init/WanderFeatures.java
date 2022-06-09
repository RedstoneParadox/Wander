package io.github.redstoneparadox.wander.init;

import io.github.redstoneparadox.wander.world.gen.feature.FallenTreeFeature;
import io.github.redstoneparadox.wander.world.gen.feature.FallenTreeFeatureConfig;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class WanderFeatures {
	public static Feature<FallenTreeFeatureConfig> FALLEN_TREE = new FallenTreeFeature(FallenTreeFeatureConfig.CODEC);

	public static void init(String modid) {
		register(modid, "fallen_tree", FALLEN_TREE);
	}

	private static void register(String modid, String name, Feature<? extends FeatureConfig> feature) {
		Registry.register(Registry.FEATURE, new Identifier(modid, name), feature);
	}
}
