package test_private;

import datamodel.rucksack.Rucksack;

import datamodel.tree.Tree;
import datamodel.tree.TreeNode;

import datamodel.tree.linked.*;

public class LinkedTreeTest extends AbstractTreeTest {

	/**
	 * Creates a concrete empty tree
	 */
	public Tree createEmptyTree() {
		return new LinkedTree();
	}

	/**
	 * Creates a concrete treenode with the specifed value
	 */
	public TreeNode createTreeNode(int value) {
		return new LinkedTreeNode(0, value);
	}
	
	public TreeNode createTreeNode(Rucksack r) {
		return new LinkedTreeNode(r);
	}
}
