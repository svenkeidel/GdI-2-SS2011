package datamodel.huffman.tree;

import datamodel.RGB;

/**
 * A node in a Huffman tree
 */
public abstract class TreeNode {

	private RGB rgb;

	public TreeNode(RGB rgb) {
		super();
		this.rgb = rgb;
	}

	/**
	 * gets the rgb color of a treenode.
	 *
	 * @return null if the treenode is not a leaf
	 */
	public RGB getRGB() {
		return rgb;
	}

	/**
	 * sets the rgb color of a treenode.
	 */
	public void setRGB(RGB rgb) {
		this.rgb = rgb;
	}

	/**
	 * the node is a leaf if the rgb variable is not null, else it's
	 * inside the huffman tree.
	 */
	public boolean isLeaf() { return rgb != null; }

	// Getter
	public abstract TreeNode getLeftNode();
	public abstract TreeNode getRightNode();
	public abstract TreeNode getParentNode();

	// Setter
	public abstract void setLeftNode(TreeNode left);
	public abstract void setRightNode(TreeNode right);
	public abstract void setParentNode(TreeNode parent);
}
