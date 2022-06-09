package io.github.redstoneparadox.wander.init;

import com.mojang.serialization.Codec;
import io.github.redstoneparadox.wander.Wander;
import io.github.redstoneparadox.wander.mixin.TreeDecoratorTypeInvoker;
import io.github.redstoneparadox.wander.world.gen.treedecorator.MossTreeDecorator;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class WanderTreeDecoratorTypes {
	public static final TreeDecoratorType<MossTreeDecorator> MOSS_TREE_DECORATOR = register(
			Wander.id("moss_tree_decorator").toString(),
			MossTreeDecorator.CODEC
	);

	public static void init() {

	}

	private static <P extends TreeDecorator> TreeDecoratorType<P> register(String id, Codec<P> codec) {
		return TreeDecoratorTypeInvoker.callRegister(id, codec);
	}
}
