/**
 * 
 */
package logic;

import java.security.InvalidParameterException;
import java.util.Vector;

import datamodel.rucksack.Rucksack;
import datamodel.rucksack.RucksackObject;
import datamodel.tree.Tree;
import datamodel.tree.TreeNode;
import datamodel.tree.linked.LinkedTree;
import datamodel.tree.sequential.SequentialTree;

/**
 * holds all necessary method to create trees, filter them and find optimal
 * rucksacks
 * 
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 * 
 */
public class RucksackTreeBuilder {

	private Tree tree;

	/**
	 * constructor
	 * 
	 * @param flag
	 *            true: uses SequentialTre; false: uses LinkedTree
	 */
	public RucksackTreeBuilder(boolean flag) {
		if (flag) {
			this.tree = new SequentialTree();
		} else {
			this.tree = new LinkedTree();
		}
	}

	/**
	 * creates a tree out of the given RucksackObjects using the following
	 * schematic:<br>
	 * Starting with the first RucksackObject the methods adds it only to the
	 * right node; then processes over the remaining objects
	 * 
	 * @param objects
	 *            the objects to put into the rucksack
	 * @param capacity
	 *            the capacity of the rucksack
	 */
	public void createRucksackTree(Vector<RucksackObject> objects, int capacity) {
		//TODO: implement this method
		int depth = 0;
		Rucksack rucksack;
		if (capacity >= 0)
			rucksack = new Rucksack(capacity);
		else
			throw new InvalidParameterException("No legal capacity-value");
		
		TreeNode node = new TreeNode(rucksack) {};
		tree.setCurrentNode(node);
		if (!objects.isEmpty()){
			setNodes(depth, objects);
		} 
	}

	/**
	 * rek. method to set all possible bags in nodes.
	 * @param depth
	 * @param objects
	 */
	public void setNodes(int depth, Vector<RucksackObject> objects){
		int size = objects.size();

		if (depth < size){
			Rucksack rucksack = tree.getCurrentNode().getRucksack();
			TreeNode node = new TreeNode(rucksack) {};
			tree.setLeftNode(node);
			if (rucksack.getWeightOfRucksack() + objects.elementAt(depth).getWeight() <= rucksack.getCapacity()){
				rucksack.insert(objects.elementAt(depth));
				node = new TreeNode(rucksack) {};
				tree.setRightNode(node);

				// only if a right son was set
				tree.moveToRightNode();
				setNodes(depth+1, objects);
				tree.moveToParentNode();
			}
			tree.moveToLeftNode();
			setNodes(depth+1, objects);
		}
	}
	
	/**
	 * creates a tree out of the given RucksackObjects using the following
	 * schematic:<br>
	 * Starting with the first RucksackObject the methods adds it only to the
	 * right node; then processes over the remaining objects.<br>
	 * The method findOptimalRucksack() on this tree always delivers a rucksack with the given
	 * neededObjects
	 * 
	 * @param objects
	 *            the objects to put into the rucksack
	 * @param neededObjects
	 *            the objects that MUST be in the rucksack at all costs
	 * @param capacity
	 *            the capacity of the rucksack
	 * @throws IllegalArgumentException
	 *             if the neededObjects don't fit into the rucksack
	 */
	public void createRucksackTree(Vector<RucksackObject> objects,
			Vector<RucksackObject> neededObjects, int capacity) {
		//TODO: implement this method
		throw new UnsupportedOperationException("Implement me!");
	}

	/**
	 * goes recursively over all nodes of the tree and finds the best rucksack
	 * in the leafs.
	 * 
	 * @return the rucksack with the best value. The method returns null, if no
	 *         root exists
	 */
	public Rucksack findOptimalRucksack() {
		//TODO: implement this method
		throw new UnsupportedOperationException("Implement me!");
	}

	/**
	 * filters the given tree<br>
	 * each rucksack which doesn't fulfill the constraints not part of the list<br>
	 * 
	 * @param constraints
	 *            the constraints to use, if null the constraints are
	 *            irrelevant.
	 * @return a list containing the filtered rucksacks
	 */
	public Vector<Rucksack> filter(Constraints constraints) {
		//TODO: implement this method
		throw new UnsupportedOperationException("Implement me!");
	}

	/**
	 * @return the current tree of this builder
	 */
	public Tree getTree() {
		return tree;
	}
}
