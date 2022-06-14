package io.github.redstoneparadox.wander;

import io.github.redstoneparadox.wander.init.WanderBlocks;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.biome.BiomeColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.world.biome.Biome;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

public class WanderClient implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {
		ColorProviderRegistry.BLOCK.register((blockState, blockRenderView, blockPos, i) -> {
			assert blockRenderView != null;
			return blockRenderView.getColor(blockPos, BiomeColors.FOLIAGE_COLOR);
		}, WanderBlocks.WILLOW_LEAVES);

		BlockRenderLayerMap.put(RenderLayer.getCutout(), WanderBlocks.WILLOW_LEAVES);
	}
}