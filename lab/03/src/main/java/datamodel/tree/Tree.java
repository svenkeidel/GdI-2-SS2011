/**
 * 
 */
package datamodel.tree;

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
	
	/**
	 * a tree equals, if it has the equal nodes at the same positions.
	 *
	 * the method moves the current position in tree to the root node.
	 */
	@Override
	public boolean equals(Object o) {
		
		if (this == null ^ o == null)
			return false;
		else if (this == null && o == null)
			return true;
		else{
		
		if(o instanceof Tree) {
			Tree otherTree = (Tree) o;

			moveToRoot();
			otherTree.moveToRoot();
			return rec_equals(otherTree);
		} else {
			return false;
			}
		}
	}

	/**
	 * walks recursive through the tree and checks if the nodes are
	 * equal.
	 */
	private boolean rec_equals(Tree otherTree) {
		if (this.getCurrentNode() == null ^ otherTree.getCurrentNode() == null)
			return false;
		else if (this.getCurrentNode() == null && otherTree.getCurrentNode() == null)
			return true;
		else{
		// test current node
		if(!this.getCurrentNode().equals(otherTree.getCurrentNode()))
			return false;

		// test left node
		if(this.hasLeftNode() && otherTree.hasLeftNode()) {

			this.moveToLeftNode();
			otherTree.moveToLeftNode();

			if(!rec_equals(otherTree))
				return false;

			this.moveToParentNode();
			otherTree.moveToParentNode();

		} else if(this.hasLeftNode() || otherTree.hasLeftNode()){
			return false;
		}

		// test right node
		if(this.hasRightNode() && otherTree.hasRightNode()) {

			this.moveToRightNode();
			otherTree.moveToRightNode();

			if(!rec_equals(otherTree))
				return false;

			this.moveToParentNode();
			otherTree.moveToParentNode();

		} else if(this.hasRightNode() || otherTree.hasRightNode()){
			return false;
		}

		return true;
		}
	}
}
