package datamodel.huffman.tree.sequential;

import java.util.PriorityQueue;

import datamodel.RGB;

import datamodel.huffman.tree.AbstractTreeFactory;
import datamodel.huffman.tree.Tree;
import datamodel.huffman.tree.TreeNode;

/**
 * produces sequential trees and treenodes
 * 
 * @apiviz.uses datamodel.huffman.tree.sequential.SequentialTree
 * @apiviz.uses datamodel.huffman.tree.sequential.SequentialTreeNode
 */
public class SequentialTreeFactory extends AbstractTreeFactory {

	private SequentialTree tree = null;

	public Tree produceTree() {
		tree = new SequentialTree(this);
		return tree;
	}

	public Tree produceTree(PriorityQueue<TreeNode> queue) {
		tree = new SequentialTree(this, queue);
		return tree;
	}

	public TreeNode produceTreeNode() {
		return new SequentialTreeNode(tree);
	}

	public TreeNode produceTreeNode(RGB rgb, int value) {
		return new SequentialTreeNode(tree, rgb, value);
	}

	public TreeNode produceTreeNode(RGB rgb) {
		return new SequentialTreeNode(tree, rgb);
	}

	public TreeNode produceTreeNode(int Value) {
		return new SequentialTreeNode(tree, Value);
	}
}
