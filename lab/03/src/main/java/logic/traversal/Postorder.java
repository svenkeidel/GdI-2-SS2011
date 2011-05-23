package logic.traversal;

import datamodel.tree.Tree;

public class Postorder extends TreeTraversal {

	public Postorder(Tree tree) {
		super(tree);
	}

	protected void traverse() {
		visitLeftSubtree();
		visitRightSubtree();
		visitCurrentNode();
	}
}
