/**
 * 
 */
package datamodel.huffman.tree.linked;

import java.util.PriorityQueue;

import datamodel.huffman.tree.AbstractTreeFactory;
import datamodel.huffman.tree.Tree;
import datamodel.huffman.tree.TreeNode;

/**
 * An implementation of the tree interface.<br>
 * The nodes are Linked to each other.
 * 
 * @author Kevin Munk, Jakob Karolus
 * @version 1.0
 * 
 */
public class LinkedTree extends Tree {

	/**
	 * Constructor: Creates an empty sequential tree
	 */
	LinkedTree(AbstractTreeFactory factory) {
		super(factory);

		this.rootNode = null;
		this.currentNode = null;
	}

	/**
	 * Constructor: Creates a huffman sequential tree
	 */
	LinkedTree(AbstractTreeFactory factory, PriorityQueue<TreeNode> queue) {
		super(factory, queue);
	}
	

	/**
	 * Moves to the left child of the current node
	 * 
	 * @return true if left child exists and the move was successful; otherwise
	 *         false
	 */
	public boolean moveToLeftNode() {
		if (currentNode.getLeftNode() != null) {
			currentNode = currentNode.getLeftNode();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Moves to the right child of the current node
	 * 
	 * @return true if right child exists and the move was successful; otherwise
	 *         false
	 */
	public boolean moveToRightNode() {
		if (currentNode.getRightNode() != null) {
			currentNode = currentNode.getRightNode();
			return true;
		} else {
			return false;
		}
	}


	/**
	 * Moves to the parent of the current node
	 * 
	 * @return true if parent exists and the move was successful; otherwise
	 *         false
	 */
	public boolean moveToParentNode() {
		if (currentNode.getParentNode() != null) {
			currentNode = currentNode.getParentNode();
			return true;
		} else { 
			return false;
		}
	}

	/**
	 * @return true if left child exists; otherwise false
	 */
	public boolean hasLeftNode() {
		if 		(currentNode.getLeftNode() == null)
					return false;
		else 	return true;
	}

	/**
	 * @return true if right child exists; otherwise false
	 */
	public boolean hasRightNode() {
		if 		(currentNode.getRightNode() == null)
					return false;
		else 	return true;
	}

	/**
	 * @return true if parent exists; otherwise false
	 */
	public boolean hasParentNode() {
		if 		(currentNode.getParentNode() != null){
					return true;
					}
		else	return false;
	}
	
	
	/**
	 * Sets the left child of the current node
	 * 
	 * @return true if successful; otherwise false (no rootNode set)
	 * 
	 */
	public boolean setLeftNode(TreeNode node) {
		if (currentNode != null) {
			currentNode.setLeftNode(node);
			node.setParentNode((TreeNode) currentNode);
			return true;
		} else {
			return false;
		}
	}
	

	/**
	 * Sets the right child of the current node
	 * 
	 * @return true if successful; otherwise false (no rootNode set)
	 * 
	 */
	public boolean setRightNode(TreeNode node) {
		if (currentNode != null) {
			currentNode.setRightNode(node);
			node.setParentNode(currentNode);
			return true;
		} else {
			return false;
		}
	}


	/**
	 * Sets the current node. If the tree is empty, sets the rootNode.
	 */
	public void setCurrentNode(TreeNode node) {
		currentNode = node;
		
		if (rootNode == null)
			rootNode = node;
	}


	/**
	 * @return the current node or null if the tree is empty
	 */
	public TreeNode getCurrentNode() {
			if (rootNode != null)
			return currentNode;
			else
			return null;
	}

	/**
	 * moves to the rootNode node of this tree
	 * 
	 * @return true if there's a rootNode; otherwise false
	 */
	public boolean moveToRoot() {
		if 		(rootNode == null)
					return false;
		else{
			currentNode = rootNode;
			return true;
		}
	}

	/**
	 * clears the whole tree, which includes deleting all nodes and the rootNode
	 * node
	 */
	public void clearTree() {
		rootNode = null;
		currentNode = null;
	}	
}
