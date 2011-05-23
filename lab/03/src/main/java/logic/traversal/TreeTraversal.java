package logic.traversal;

import java.util.Iterator;
import java.util.Vector;

import datamodel.tree.Tree;
import datamodel.tree.TreeNode;

public abstract class TreeTraversal implements Iterable<TreeNode> {


	/**
	 * the tree to iterate over.
	 */
	protected Tree tree;

	/**
	 *
	 */
	protected Vector<TreeNode> traverseVector;


	/**
	 * Constructor.
	 * @param tree the tree to iterate over. 
	 */
	public TreeTraversal(Tree tree) {
		this.tree = tree;
		this.traverseVector = new Vector<TreeNode>();
	}


	/**
	 * Visits the left subtree of a node if it exists.
	 * The currentNode pointer is left were it were.
	 */
	protected void visitLeftSubtree() {
		if(tree.hasLeftNode()) {
			tree.moveToLeftNode();
			traverse();
			tree.moveToParentNode();
		}
	}


	/**
	 * Visits the right subtree of a node if it exists.
	 * The currentNode pointer is left were it were.
	 */
	protected void visitRightSubtree() {
		if(tree.hasRightNode()) {
			tree.moveToRightNode();
			traverse();
			tree.moveToParentNode();
		}
	}


	/**
	 * Visits the current node.
	 * The currentNode pointer is left were it were.
	 */
	protected void visitCurrentNode() {
		traverseVector.add(tree.getCurrentNode());
	}


	/**
	 * traverses over a tree with a concrete strategy and adds nodes to
	 * a vector.
	 */
	protected abstract void traverse();


	/**
	 * returns a vector to iterate over.
	 */
	public Iterator<TreeNode> iterator() {
		traverse();
		return traverseVector.iterator();
	}
}
