package logic.traversal;

import datamodel.tree.Tree;

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
