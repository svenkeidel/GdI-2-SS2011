package datamodel.huffman.tree;

import java.util.PriorityQueue;

import datamodel.RGB;

/**
 * produces Trees and Treenodes of a specific kind
 */
public abstract class AbstractTreeFactory {

	public abstract Tree produceTree();
	public abstract Tree produceTree(PriorityQueue<TreeNode> queue);
	public abstract TreeNode produceTreeNode();
	public abstract TreeNode produceTreeNode(RGB rgb, int value);
	public abstract TreeNode produceTreeNode(RGB rgb);
	public abstract TreeNode produceTreeNode(int Value);

}
