package logic.traversal;

import datamodel.tree.Tree;

/**
 * Iterates in preorder over a tree.
 */
public class Preorder extends TreeTraversal {

	public Preorder(Tree tree) {
		super(tree);
	}

	protected void traverse() {
		visitCurrentNode();
		visitLeftSubtree();
		visitRightSubtree();
	}
}
