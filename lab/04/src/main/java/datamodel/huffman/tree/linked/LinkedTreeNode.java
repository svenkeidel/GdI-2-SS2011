package datamodel.huffman.tree.linked;

import datamodel.RGB;

import datamodel.huffman.tree.TreeNode;

/**
 * An implementation of the TreeNode interface for a linked tree.
 */
public class LinkedTreeNode extends TreeNode {
	
	protected TreeNode left, right, parent;

	/**
	 * constructor
	 * 
	 * @param rgb the The color which should be stored in the node
	 */
	public LinkedTreeNode(RGB rgb) {
		super(rgb);
	}

	public LinkedTreeNode() {
		super(null);
	}

	// Getter
	public TreeNode getLeftNode()                  { return left;          }
	public TreeNode getRightNode()                 { return right;         }
	public TreeNode getParentNode()                { return parent;        }

	// Setter
	public void     setLeftNode(TreeNode left)     { this.left   = left;   }
	public void     setRightNode(TreeNode right)   { this.right  = right;  }
	public void     setParentNode(TreeNode parent) { this.parent = parent; }
}
