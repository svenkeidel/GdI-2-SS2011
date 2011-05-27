package test_private;

import datamodel.rucksack.Rucksack;

import datamodel.tree.Tree;
import datamodel.tree.TreeNode;

import datamodel.tree.sequential.*;

public class SequentialTreeTest extends AbstractTreeTest {

	/**
	 * Creates a concrete empty tree
	 */
	public Tree createEmptyTree() {
		return new SequentialTree();
	}

	/**
	 * Creates a concrete treenode with the specifed value
	 */
	public TreeNode createTreeNode(int value) {
		return new SequentialTreeNode(0, value);
	}

	public TreeNode createTreeNode(Rucksack r) {
		return new SequentialTreeNode(r);
	}
}
