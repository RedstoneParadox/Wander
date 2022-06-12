package io.github.redstoneparadox.wander.init;

import com.mojang.serialization.Codec;
import com.sun.source.tree.Tree;
import io.github.redstoneparadox.wander.Wander;
import io.github.redstoneparadox.wander.mixin.TreeDecoratorTypeInvoker;
import io.github.redstoneparadox.wander.world.gen.treedecorator.BranchTreeDecorator;
import io.github.redstoneparadox.wander.world.gen.treedecorator.FoliageTreeDecorator;
import io.github.redstoneparadox.wander.world.gen.treedecorator.MossTreeDecorator;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class WanderTreeDecoratorTypes {
	public static final TreeDecoratorType<MossTreeDecorator> MOSS_TREE_DECORATOR = register(
			Wander.id("moss_tree_decorator"),
			MossTreeDecorator.CODEC
	);
	public static final TreeDecoratorType<FoliageTreeDecorator> FOLIAGE_TREE_DECORATOR = register(
			Wander.id("foliage_tree_decorator"),
			FoliageTreeDecorator.CODEC
	);
	public static final TreeDecoratorType<BranchTreeDecorator> BRANCH_TREE_DECORATOR = register(
			Wander.id("branch_tree"),
			BranchTreeDecorator.CODEC
	);

	public static void init() {

	}

	private static <P extends TreeDecorator> TreeDecoratorType<P> register(Identifier id, Codec<P> codec) {
		return TreeDecoratorTypeInvoker.callRegister(id.toString(), codec);
	}
}
