package datamodel.huffman.tree;

import java.util.Map;
import java.util.PriorityQueue;

import datamodel.RGB;

/**
 * produces Trees and Treenodes of a specific kind
 */
public abstract class AbstractTreeFactory {

	public abstract Tree produceTree();
	public abstract Tree produceTree(PriorityQueue<Map.Entry<RGB,Integer>> queue);
	public abstract TreeNode produceTreeNode();
	public abstract TreeNode produceTreeNode(RGB rgb);

}
