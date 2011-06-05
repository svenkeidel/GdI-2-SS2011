package datamodel.huffman.tree.linked;

import java.util.Map;
import java.util.PriorityQueue;

import datamodel.RGB;

import datamodel.huffman.tree.AbstractTreeFactory;
import datamodel.huffman.tree.Tree;
import datamodel.huffman.tree.TreeNode;

/**
 * produces linked trees and treenodes
 */
public class LinkedTreeFactory extends AbstractTreeFactory {

	public Tree produceTree() {
		return new LinkedTree(this);
   	}

	public Tree produceTree(PriorityQueue<Map.Entry<RGB,Integer>> queue) {
		return new LinkedTree(this, queue);
	}

	public TreeNode produceTreeNode() {
		return new LinkedTreeNode();
	}

	public TreeNode produceTreeNode(RGB rgb) {
		return new LinkedTreeNode(rgb);
	}
}
