package logic.traversal;

import java.util.NoSuchElementException;

import datamodel.tree.Tree;
import datamodel.tree.TreeNode;

public class Inorder extends TreeTraversal {

	public Inorder(Tree tree) {
		super(tree);
	}

	@Override
	public TreeNode next() {
		TreeNode next;
		if((next = visitLeftSubtree())  != null) return next;
		if((next = visitCurrentNode())  != null) return next;
		if((next = visitRightSubtree()) != null) return next;
		throw new NoSuchElementException("this iteration has no more elements");
	}
}
