package logic.traversal;

import java.util.NoSuchElementException;

import datamodel.tree.Tree;
import datamodel.tree.TreeNode;

public class Preorder extends TreeTraversal {

	public Preorder(Tree tree) {
		super(tree);
	}

	@Override
	public TreeNode next() {
		TreeNode next;
		if((next = visitCurrentNode()) != null) return next;
		if((next = visitLeft())        != null) return next;
		if((next = visitRight())       != null) return next;
		throw new NoSuchElementException("this iteration has no more elements");
	}
}
