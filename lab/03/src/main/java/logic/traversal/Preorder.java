package logic.traversal;

import datamodel.tree.Tree;

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
