package datamodel.huffman.tree;

import java.util.PriorityQueue;

/**
 * A implementation of your Huffman tree.<br>
 * 
 * @author Kevin Munk, Jakob Karolus
 * @version 1.0
 * 
 */
public abstract class Tree {

	protected AbstractTreeFactory factory;
	protected TreeNode rootNode;
	protected TreeNode currentNode;

	/**
	 * Constructor.
	 * 
	 * Gets a sorted list of the colors and its amount and constructs a huffman
	 * tree out of it.
	 */
	public Tree(AbstractTreeFactory factory, PriorityQueue<TreeNode> queue) {
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
	public void buildTree(PriorityQueue<TreeNode> nodes) {

		TreeNode node;

		// pull the first two nodes, create a new node and put it in the queue.
		// and do it n-1 times
		TreeNode right;
		TreeNode left;
		int sumOfPropability;
		while (nodes.size() > 1) {

			right = nodes.poll();
			sumOfPropability = right.getValue();
			left = nodes.poll();
			sumOfPropability += left.getValue();

			node = factory.produceTreeNode(sumOfPropability);
			node.setLeftNode(left);
			node.setRightNode(right);
			left.setParentNode(node);
			right.setParentNode(node);
			nodes.add(node);
		}

		rootNode = nodes.poll();
		currentNode = rootNode;
	}

	private int maxDepth;

	public int getDepth() {
		moveToRoot();
		maxDepth = 0;
		getDepth(0);
		return maxDepth;
	}

	private void getDepth(int currentDepth) {
		if (currentDepth > maxDepth)
			maxDepth = currentDepth;

		if (hasLeftNode()) {
			moveToLeftNode();
			getDepth(currentDepth + 1);
			moveToParentNode();
		}

		if (hasRightNode()) {
			moveToRightNode();
			getDepth(currentDepth + 1);
			moveToParentNode();
		}
	}

	/**
	 * Moves to the left child of the current node
	 * 
	 * @return true if left child exists and the move was successful; otherwise
	 * false
	 */
	public abstract boolean moveToLeftNode();

	/**
	 * Moves to the right child of the current node
	 * 
	 * @return true if right child exists and the move was successful; otherwise
	 * false
	 */
	public abstract boolean moveToRightNode();

	/**
	 * Moves to the parent of the current node
	 * 
	 * @return true if parent exists and the move was successful; otherwise
	 * false
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

	@Override
	public String toString() {
		StringBuffer out = new StringBuffer();
		out.append("[ ");
		rec_toString(out);
		out.append(" ]");
		return out.toString();
	}

	private void rec_toString(StringBuffer out) {
		if (currentNode.isLeaf()) {
			out.append(currentNode.toString() + ", ");
		}

		if (hasLeftNode()) {
			moveToLeftNode();
			rec_toString(out);
			moveToParentNode();
		}

		if (hasRightNode()) {
			moveToRightNode();
			rec_toString(out);
			moveToParentNode();
		}
	}
}
