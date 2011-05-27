/**
 * 
 */
package datamodel.tree;

import java.util.Iterator;

import logic.traversal.TreeTraversalFactory;

/**
 * The abstract class for a tree.
 * 
 * @author Kevin Munk, Jakob Karolus
 * @version 1.0
 * 
 */
public abstract class Tree {

	/**
	 * Moves to the left child of the current node
	 * 
	 * @return true if left child exists and the move was successful; otherwise
	 *         false
	 */
	public abstract boolean moveToLeftNode();

	/**
	 * Moves to the right child of the current node
	 * 
	 * @return true if right child exists and the move was successful; otherwise
	 *         false
	 */
	public abstract boolean moveToRightNode();

	/**
	 * Moves to the parent of the current node
	 * 
	 * @return true if parent exists and the move was successful; otherwise
	 *         false
	 */
	public abstract boolean moveToParentNode();

	/**
	 * @return true if left child exists; otherwise false
	 */
	public abstract boolean hasLeftNode();

	/**
	 * @return true if right child exists; otherwise false
	 */
	public abstract boolean hasRightNode();

	/**
	 * @return true if parent exists; otherwise false
	 */
	public abstract boolean hasParentNode();

	/**
	 * Sets the left child of the current node
	 * 
	 * @return true if successful; otherwise false (no root set)
	 * 
	 */
	public abstract boolean setLeftNode(TreeNode node);

	/**
	 * Sets the right child of the current node
	 * 
	 * @return true if successful; otherwise false (no root set)
	 * 
	 */
	public abstract boolean setRightNode(TreeNode node);

	/**
	 * Sets the current node. If the tree is empty, sets the root.
	 * 
	 */
	public abstract void setCurrentNode(TreeNode node);

	/**
	 * @return the current node or null if the tree is empty
	 */
	public abstract TreeNode getCurrentNode();

	/**
	 * moves to the root node of this tree
	 * 
	 * @return true if there's a root; otherwise false
	 */
	public abstract boolean moveToRoot();

	/**
	 * clears the whole tree, which includes deleting all nodes and the root
	 * node
	 */
	public abstract void clearTree();
	

	public boolean equals(Object o) {
		if(o instanceof Tree) {
			Tree otherTree = (Tree) o;
			Iterator<TreeNode> otherIterator = TreeTraversalFactory.createPreorder(otherTree);
			Iterator<TreeNode> thisIterator = TreeTraversalFactory.createPreorder(this);
			while(otherIterator.hasNext() && thisIterator.hasNext())
				if(!thisIterator.next().equals(otherIterator.next()))
					return false;
			if(otherIterator.hasNext() && thisIterator.hasNext())
				return false;
			return true;
		} else {
			return false;
		}
	}
}
