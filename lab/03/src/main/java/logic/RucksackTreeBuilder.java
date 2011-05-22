/**
 * 
 */
package logic;

import java.util.Vector;

import datamodel.rucksack.Rucksack;
import datamodel.rucksack.RucksackObject;
import datamodel.tree.Tree;
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
		throw new UnsupportedOperationException("Implement me!");
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
