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
	
	private int weight, value;
	private Rucksack rucksack;
	private TreeNode leftChild = null;
	private TreeNode rightChild = null;
	private TreeNode parent = null;
	
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
	 * constructor
	 * @param rucksack the rucksack for this node
	 * @param weight the weight of the element
	 * @param value the value of the element
	 * @param parent the parent node of this node
	 * @param leftChild the left child node
	 * @param rightChild the right child node
	 */
	public TreeNode(Rucksack rucksack, int weight, int value, TreeNode parent, TreeNode leftChild, TreeNode rightChild){
		this(rucksack, weight, value);
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.parent = parent;
	}
	
	
	/**
	 * 
	 * @return the linked nodes
	 * @param nodeType 1 = leftChild, 2 = rightChild, default = parent
	 */
	public TreeNode getLinkedNode(int nodeType) {
		
		switch(nodeType){
			case 1:		 return this.leftChild;
			case 2:		 return this.rightChild;
			default: 	 return this.parent;
				
		}
	}
	
	public void setLinkedNode (int nodeType, TreeNode node) {
		
		switch(nodeType){
			case 1:{		this.leftChild = node;
							break;}
			case 2:{		this.rightChild = node;
							break;}
			default:{ 		this.parent = node;
							break;}
			}
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
	
	/**
	 * 
	 * @return the linked nodes
	 * @param nodeType 1 = leftChild, 2 = rightChild, default = parent
	 */
	
	public String toString() {
		return "weight: " + weight + "\tvalue: " + value;
	}

	/*@Override
	public boolean equals(Object o) {
		if(o instanceof TreeNode) {
			TreeNode other = (TreeNode) o;
			return this.rucksack.equals(other.getRucksack());
		} else {
			return false;
		}
	}*/
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof TreeNode) {
			TreeNode other = (TreeNode) o;
			return ((this.getValue() == other.getValue()) && (this.getWeight() == other.getWeight()));
		} else {
			return false;
		}
	}
	
}
