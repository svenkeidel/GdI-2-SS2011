/**
 * 
 */
package datamodel.tree.linked;

import datamodel.tree.Tree;
import datamodel.tree.TreeNode;

/**
 * An implementation of the tree interface.<br>
 * The nodes are Linked to each other.
 * 
 * @author Kevin Munk, Jakob Karolus
 * @version 1.0
 * 
 */
public class LinkedTree extends Tree {

	private TreeNode root;
	private TreeNode currentNode;

	/**
	 * Constructor: Creates an empty sequential tree
	 */
	public LinkedTree() {
		this.root = null;
		this.currentNode = null;
	}
	
	
	/**
	 * Moves to the left child of the current node
	 * 
	 * @return true if left child exists and the move was successful; otherwise
	 *         false
	 */
	public boolean moveToLeftNode() {
		if 		(currentNode.getLinkedNode(1) != null){
					currentNode = currentNode.getLinkedNode(1);
					return true;}
		else 	return false;
	}

	/**
	 * Moves to the right child of the current node
	 * 
	 * @return true if right child exists and the move was successful; otherwise
	 *         false
	 */
	public boolean moveToRightNode() {
		if 		(currentNode.getLinkedNode(2) != null){
					currentNode = currentNode.getLinkedNode(2);
					return true;}
		else 	return false;
	}


	/**
	 * Moves to the parent of the current node
	 * 
	 * @return true if parent exists and the move was successful; otherwise
	 *         false
	 */
	public boolean moveToParentNode() {
		if 		(currentNode.getLinkedNode(0) != null){
//					System.err.println("BACK TO PARENT "+currentNode.getLinkedNode(0).getValue()+"");
					currentNode = currentNode.getLinkedNode(0);
					return true;}
		else 	return false;
	}

	/**
	 * @return true if left child exists; otherwise false
	 */
	public boolean hasLeftNode() {
		if 		(currentNode.getLinkedNode(1) == null)
					return false;
		else 	return true;
	}

	/**
	 * @return true if right child exists; otherwise false
	 */
	public boolean hasRightNode() {
		if 		(currentNode.getLinkedNode(2) == null)
					return false;
		else 	return true;
	}

	/**
	 * @return true if parent exists; otherwise false
	 */
	public boolean hasParentNode() {
		if 		(currentNode.getLinkedNode(0) != null){
					//System.err.println("PARENT TRUE");
					return true;
					}
		else 	{//System.err.println("PARENT FALSE");
				return false;}
		}
	
	
	/**
	 * Sets the left child of the current node
	 * 
	 * @return true if successful; otherwise false (no root set)
	 * 
	 */
	public boolean setLeftNode(TreeNode node) {
		if		(currentNode != null){
					currentNode.setLinkedNode(1, node);
					node.setLinkedNode(0, currentNode);
//					System.err.println("SETLEFT WITH PARENT "+node.getLinkedNode(0).getValue()+"");
					return true;}
		else 	return false;
		}
	

	/**
	 * Sets the right child of the current node
	 * 
	 * @return true if successful; otherwise false (no root set)
	 * 
	 */
	public boolean setRightNode(TreeNode node) {
		if		(currentNode != null){
					currentNode.setLinkedNode(2, node);
					node.setLinkedNode(0, currentNode);
//					System.err.println("SETRIGHT WITH PARENT "+node.getLinkedNode(0).getValue()+"");
					return true;}
		else 	return false;
}

	/**
	 * Sets the current node. If the tree is empty, sets the root.
	 * 
	 */
	public void setCurrentNode(TreeNode node) {
		currentNode = node;
		
		
		if (root == null)
			root = node;
			
		}

	/**
	 * @return the current node or null if the tree is empty
	 */
	public TreeNode getCurrentNode() {
			if (root != null)
			return currentNode;
			else
			return null;
	}

	/**
	 * moves to the root node of this tree
	 * 
	 * @return true if there's a root; otherwise false
	 */
	public boolean moveToRoot() {
		if 		(root == null)
					return false;
		else{	while(this.hasParentNode() == true){
//				System.err.println("while loop movetoRoot");
					this.moveToParentNode();}
				return true;
		}
	}

	/**
	 * clears the whole tree, which includes deleting all nodes and the root
	 * node
	 */
	public void clearTree() {
		root = null;
		currentNode = null;
	}
	
	/**
	 * TODO: add Javadoc
	 */
	public boolean equals(Object o) {
		//TODO: Implement me
		throw new UnsupportedOperationException("Implement Me");
	}
	

}
