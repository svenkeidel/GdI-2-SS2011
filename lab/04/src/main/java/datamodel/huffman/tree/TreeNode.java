package datamodel.huffman.tree;

import java.util.Comparator;

import datamodel.RGB;

/**
 * A node in a Huffman tree
 */
public abstract class TreeNode {


	/**
	 * Comparator to compare two TreeNodes
	 */
	public static class comparator implements Comparator<TreeNode> {
		/**
		 * Compare the amount of a color with another
		 */
		@Override
		public int compare(TreeNode o1, TreeNode o2) {
			return o1.getValue() - o2.getValue();
		}
	}

	private RGB rgb;
	private int value;

	public TreeNode(RGB rgb, int value) {
		this.value = value;
		this.rgb = rgb;
	}

	public TreeNode(RGB rgb) {
		this.rgb = rgb;
	}
	
	public TreeNode(int value) {
		this.value = value;
		this.rgb = null;
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

	public int getValue(){
		return this.value;
	}
	
	public void setValue(int value){
		this.value = value;
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
	
	@Override
	public String toString() {
		StringBuffer out = new StringBuffer();
		out.append(isLeaf() ? "Leaf": "Node");
		if(rgb != null)
			out.append(" [ "+rgb.toString()+", Value ["+value+"] ]");
		else
			out.append(" [ RGB [null], Value ["+value+"] ]");
		return out.toString();
	}
}
