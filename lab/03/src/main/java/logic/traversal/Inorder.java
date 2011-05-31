package logic.traversal;

import datamodel.tree.Tree;

/**
 * Iterates in inorder over the tree.
 */
public class Inorder extends TreeTraversal {

	public Inorder(Tree tree) {
		super(tree);
	}

	protected void traverse() {
		visitLeftSubtree();
		visitCurrentNode();
		visitRightSubtree();
	}
}
