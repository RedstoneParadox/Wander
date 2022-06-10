package io.github.redstoneparadox.wander.mixin;

import io.github.redstoneparadox.wander.Wander;
import net.minecraft.util.Holder;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.util.ConfiguredFeatureUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(ConfiguredFeatureUtil.class)
public class ConfiguredFeatureUtilMixin {
	@Inject(method = "register(Ljava/lang/String;Lnet/minecraft/world/gen/feature/Feature;Lnet/minecraft/world/gen/feature/FeatureConfig;)Lnet/minecraft/util/Holder;", at = @At("HEAD"))
	private static <FC extends FeatureConfig, F extends Feature<FC>> void register(String id, F feature, FC featureConfig, CallbackInfoReturnable<Holder<ConfiguredFeature<FC, ?>>> cir) {
		if (Objects.equals(id, "swamp_oak")) {
			Wander.LOGGER.info("We Gottem!");
		}
	}
}
