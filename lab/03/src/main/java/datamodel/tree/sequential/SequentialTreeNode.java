/**
 * 
 */
package datamodel.tree.sequential;

import datamodel.rucksack.Rucksack;
import datamodel.tree.TreeNode;

/**
 * Tree node implementation for the sequential tree.
 * 
 * @author Kevin Munk, Jakob Karolus
 * @version 1.0
 *
 */
public class SequentialTreeNode extends TreeNode {

	/**
	 * constructor
	 * 
	 * @param rucksack the rucksack to set
	 */
	public SequentialTreeNode(Rucksack rucksack) {
		super(rucksack);
	}
	
	public SequentialTreeNode(int weight, int value) {
		super(weight, value);
	}

	public SequentialTreeNode(Rucksack rucksack, int weight, int value) {
		super(rucksack, weight, value);
	}
}
