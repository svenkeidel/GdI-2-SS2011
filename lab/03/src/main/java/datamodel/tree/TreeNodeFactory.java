/**
 * 
 */
package datamodel.tree;

import java.util.NoSuchElementException;

import datamodel.rucksack.Rucksack;
import datamodel.tree.linked.LinkedTree;
import datamodel.tree.linked.LinkedTreeNode;
import datamodel.tree.sequential.SequentialTree;
import datamodel.tree.sequential.SequentialTreeNode;

/**
 * Constructs TreeNodes based on the type of the tree.
 * 
 * @author Kevin Munk, Jakob Karolus
 * @version 1.0
 *
 */
public final class TreeNodeFactory {

	/**
	 * delivers a TreeNode to the corresponding Tree<br>
	 * use this method to generate your TreeNodes in RucksackTreeBuilder
	 * 
	 * @param tree the tree instance
	 * @param rucksack the rucksack to set for the TreeNode
	 * @return
	 */
	public static TreeNode getNodeForTree(Tree tree, Rucksack rucksack) {
		if(tree instanceof LinkedTree) {
			return new LinkedTreeNode(rucksack);
		} else if(tree instanceof SequentialTree) {
			return new SequentialTreeNode(rucksack);
		} else {
			throw new NoSuchElementException("For the given tree was no node class found.");
		}
	}
}
