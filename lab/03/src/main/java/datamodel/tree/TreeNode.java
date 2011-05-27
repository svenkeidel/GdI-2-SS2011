/**
 * 
 */
package datamodel.tree;

import datamodel.rucksack.Rucksack;
import datamodel.rucksack.RucksackObject;

/**
 * An abstract class for a tree node. Holds the rucksack
 * 
 * @author Kevin Munk, Jakob Karolus
 * @version 1.0
 *
 */
public abstract class TreeNode {
	
	private int weight, value;
	private Rucksack rucksack;
	private RucksackObject rucksackobject;
	
	/**
	 * constructor
	 * @param rucksack the rucksack for this node
	 */
	public TreeNode(Rucksack rucksack){
		this.rucksack = rucksack;
	}

	/**
	 * constructor
	 * @param weight the weight of the element
	 * @param value the value of the element
	 */
	public TreeNode(int weight, int value) {
		this.weight = weight;
		this.value = value;
	}

	/**
	 * constructor
	 * @param rucksack the rucksack for this node
	 * @param weight the weight of the element
	 * @param value the value of the element
	 */
	public TreeNode(Rucksack rucksack, int weight, int value) {
		this(weight, value);
		this.rucksack = rucksack;
	}

	/**
	 * @return the weight of the rucksack in this node
	 */
	public int getWeight() {
		return this.weight;
	}

	/**
	 * 
	 * @return the values of the rucksack in this node
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * 
	 * @return the rucksack of this node
	 */
	public Rucksack getRucksack() {
		return this.rucksack;
	}

	public String toString() {
		return "weight: " + weight + "\tvalue: " + value;
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof TreeNode) {
			TreeNode other = (TreeNode) o;
			return this.rucksack.equals(other.getRucksack());
		} else {
			return false;
		}
	}
	
}
