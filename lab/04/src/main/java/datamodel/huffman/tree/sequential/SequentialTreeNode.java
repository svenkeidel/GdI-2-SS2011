package datamodel.huffman.tree.sequential;

import datamodel.RGB;

import datamodel.huffman.tree.TreeNode;

/**
 * Tree node implementation for the sequential tree.
 */
public class SequentialTreeNode extends TreeNode {

	private SequentialTree tree;

	/**
	 * constructor
	 * 
	 * @param rgb the color to set
	 * @param value the amount of the color
	 */
	public SequentialTreeNode(SequentialTree tree, RGB rgb, int value) {
		super(rgb, value);
		this.tree = tree;
	}

	/**
	 * constructor
	 * 
	 * @param rgb the color to set
	 */
	public SequentialTreeNode(SequentialTree tree, RGB rgb) {
		super(rgb);
		this.tree = tree;
	}

	/**
	 * constructor
	 * 
	 * @param value the amount of the color
	 */
	public SequentialTreeNode(SequentialTree tree, int Value) {
		super(Value);
		this.tree = tree;
	}
	
	/**
	 * constructor. It sets no color
	 */
	public SequentialTreeNode(SequentialTree tree) {
		super(null);
		this.tree = tree;
	}

	// Getter
	public TreeNode getLeftNode()   { return tree.getLeftNodeOf(this);   }
	public TreeNode getRightNode()  { return tree.getRightNodeOf(this);  }
	public TreeNode getParentNode() { return tree.getParentNodeOf(this); }

	// Setter
	public void setLeftNode(TreeNode left)     { this.tree.setLeftNodeOf(this, left);     }
	public void setRightNode(TreeNode right)   { this.tree.setRightNodeOf(this, right);   }
	public void setParentNode(TreeNode parent) { this.tree.setParentNodeOf(this, parent); }
}
