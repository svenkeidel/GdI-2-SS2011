/**
 * 
 */
package datamodel.tree;

import datamodel.rucksack.Rucksack;

/**
 * An abstract class for a tree node. Holds the rucksack
 * 
 * @author Kevin Munk, Jakob Karolus
 * @version 1.0
 *
 */
public abstract class TreeNode {
	
	
	/**
	 * constructor
	 * @param rucksack the rucksack for this node
	 */
	public TreeNode(Rucksack rucksack){
			//TODO: implement
		}

	/**
	 * @return the weight of the rucksack in this node
	 */
	public int getWeight() {
		// TODO: implement this method
		throw new UnsupportedOperationException("Implement me!");	}

	/**
	 * 
	 * @return the values of the rucksack in this node
	 */
	public int getValue() {
		// TODO: implement this method
		throw new UnsupportedOperationException("Implement me!");	}

	/**
	 * 
	 * @return the rucksack of this node
	 */
	public Rucksack getRucksack() {
		// TODO: implement this method
		throw new UnsupportedOperationException("Implement me!");	}
	
	//TODO: implement equals

	
}
