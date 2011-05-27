/**
 * 
 */
package datamodel.tree.linked;

import datamodel.rucksack.Rucksack;
import datamodel.tree.TreeNode;

/**
 * An implementation of the TreeNode interface for a linked tree.
 * 
 * @author Kevin Munk, Jakob Karolus
 * @version 1.0
 * 
 */
public class LinkedTreeNode extends TreeNode {
	

	
	/**
	 * constructor
	 * 
	 * @param rucksack the rucksack to set
	 */
	public LinkedTreeNode(Rucksack rucksack) {
		super(rucksack);
	}

	public LinkedTreeNode(int weight, int value) {
		super(weight, value);
	}

	public LinkedTreeNode(Rucksack rucksack, int weight, int value) {
		super(rucksack, weight, value);
	}

	public LinkedTreeNode(Rucksack rucksack, int weight, int value, TreeNode parent, TreeNode leftChild, TreeNode rightChild){
	super(rucksack, weight, value, parent, leftChild, rightChild);
	}
}
