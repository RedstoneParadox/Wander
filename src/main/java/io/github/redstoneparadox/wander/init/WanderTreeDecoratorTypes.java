package io.github.redstoneparadox.wander.init;

import com.mojang.serialization.Codec;
import io.github.redstoneparadox.wander.Wander;
import io.github.redstoneparadox.wander.mixin.TreeDecoratorTypeInvoker;
import io.github.redstoneparadox.wander.world.gen.treedecorator.BranchTreeDecorator;
import io.github.redstoneparadox.wander.world.gen.treedecorator.HangingLeavesTreeDecorator;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class WanderTreeDecoratorTypes {
	public static final TreeDecoratorType<BranchTreeDecorator> BRANCH_TREE_DECORATOR = register(
			Wander.id("branch_tree"),
			BranchTreeDecorator.CODEC
	);
	public static final TreeDecoratorType<HangingLeavesTreeDecorator> HANGING_LEAVES = register(
			Wander.id("hanging_leaves"),
			HangingLeavesTreeDecorator.CODEC
	);

	public static void init() {

	}

	private static <P extends TreeDecorator> TreeDecoratorType<P> register(Identifier id, Codec<P> codec) {
		return TreeDecoratorTypeInvoker.callRegister(id.toString(), codec);
	}
}
