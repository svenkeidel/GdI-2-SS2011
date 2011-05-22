package logic.traversal;

import java.util.Iterator;

import datamodel.tree.Tree;
import datamodel.tree.TreeNode;

public class TreeTraversalFactory {

	/**
	 * Create iterable objects
	 */

	public static TreeTraversal createPreorder(Tree tree) {
		return new Preorder(tree);
	}

	public static TreeTraversal createInorder(Tree tree) {
		return new Inorder(tree);
	}

	public static TreeTraversal createPostorder(Tree tree) {
		return new Postorder(tree);
	}

	/**
	 * Create Iterators
	 */

	public static Iterator<TreeNode> createPreorderIterator(Tree tree) {
		return new Preorder(tree).iterator();
	}

	public static Iterator<TreeNode> createInorderIterator(Tree tree) {
		return new Inorder(tree).iterator();
	}

	public static Iterator<TreeNode> createPostorderIterator(Tree tree) {
		return new Postorder(tree).iterator();
	}
}
