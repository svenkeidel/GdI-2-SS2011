package datamodel.huffman.tree;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Comparator;

import datamodel.RGB;

/**
 * A implementation of your Huffman tree.<br>
 * 
 * @author Kevin Munk, Jakob Karolus
 * @version 1.0
 * 
 */
public abstract class Tree {

	protected AbstractTreeFactory factory;

	/**
	 * Constructor.
	 * 
	 * Gets a sorted list of the colors and its amount and constructs a
	 * huffman tree out of it.
	 */
	public Tree(AbstractTreeFactory factory, PriorityQueue<Map.Entry<RGB,Integer>> queue) {
		this(factory);
		buildTree(queue);
	}


	/**
	 * Constructor.
	 *
	 * initializes an empty tree.
	 */
	public Tree(AbstractTreeFactory factory) {
		this.factory = factory;
	}

	
	/**
	 * builds up an huffman tree from a sorted list of colors.
	 */
	public void buildTree(PriorityQueue<Map.Entry<RGB,Integer>> queue) {
		// create a new queue, create for each entry a new node and put in the new queue.
		Comparator<TreeNode> NodeComparator = new NodeComparator();
		PriorityQueue<TreeNode> nodes = new PriorityQueue<TreeNode>(queue.size(), NodeComparator);
		TreeNode node;

		// all entrys in the queue move in a new queue with a NodeComparator
		for (int i = 0; i < queue.size(); i++){
			node = factory.produceTreeNode(queue.poll().getValue());
			nodes.add(node);
		}

		// pull the first two nodes, create a new node and put it in the queue.
		// and do it n-1 times
		TreeNode tmp1;
		TreeNode tmp2;
		int tmpvalue = 0;
		int size = nodes.size();
		for (int i = 0; i < size; i++){
			tmp1 = nodes.poll();
			tmpvalue += tmp1.getValue();
			tmp2 = nodes.poll();
			tmpvalue += tmp2.getValue();
			node = factory.produceTreeNode(tmpvalue);
			node.setLeftNode(tmp2);
			node.setRightNode(tmp1);
			nodes.add(node);
			// reset tmpvalue for next round;
			tmpvalue = 0;
		}
	}

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
}
