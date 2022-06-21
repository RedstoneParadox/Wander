package io.github.redstoneparadox.wander.mixin;

import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(TreeFeatureConfig.Builder.class)
public class TreeFeatureConfigMixin {
	@Shadow private List<TreeDecorator> decorators;

	@Inject(method = "build", at = @At("HEAD"))
	private void build(CallbackInfoReturnable<TreeFeatureConfig> cir) {
		decorators = new ArrayList<>(decorators);
	}
}
