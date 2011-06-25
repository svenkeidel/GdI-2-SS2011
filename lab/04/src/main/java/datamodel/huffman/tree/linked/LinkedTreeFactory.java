package datamodel.huffman.tree.linked;

import java.util.PriorityQueue;

import datamodel.RGB;

import datamodel.huffman.tree.AbstractTreeFactory;
import datamodel.huffman.tree.Tree;
import datamodel.huffman.tree.TreeNode;

/**
 * produces linked trees and treenodes
 * 
 * @apiviz.uses datamodel.huffman.tree.linked.LinkedTree
 * @apiviz.uses datamodel.huffman.tree.linked.LinkedTreeNode
 */
public class LinkedTreeFactory extends AbstractTreeFactory {

	public Tree produceTree() {
		return new LinkedTree(this);
	}

	public Tree produceTree(PriorityQueue<TreeNode> queue) {
		return new LinkedTree(this, queue);
	}

	public TreeNode produceTreeNode() {
		return new LinkedTreeNode();
	}

	public TreeNode produceTreeNode(RGB rgb, int value) {
		return new LinkedTreeNode(rgb, value);
	}

	public TreeNode produceTreeNode(RGB rgb) {
		return new LinkedTreeNode(rgb);
	}

	public TreeNode produceTreeNode(int Value) {
		return new LinkedTreeNode(Value);
	}
}
