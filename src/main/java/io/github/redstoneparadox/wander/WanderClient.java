package io.github.redstoneparadox.wander;

import io.github.redstoneparadox.wander.init.BlocksInit;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.render.RenderLayer;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

public class WanderClient implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {
		ColorProviderRegistry.BLOCK.register((blockState, blockRenderView, blockPos, i) -> {
			assert blockRenderView != null;
			return blockRenderView.getColor(blockPos, BiomeColors.FOLIAGE_COLOR);
		}, BlocksInit.WILLOW_LEAVES);
		ColorProviderRegistry.ITEM.register((itemStack, i) -> FoliageColors.getDefaultColor());

		BlockRenderLayerMap.put(RenderLayer.getCutout(), BlocksInit.WILLOW_LEAVES, BlocksInit.PINK_WILLOW_LEAVES);
	}
}
