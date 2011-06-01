/**
 * 
 */
package logic;

import java.util.Vector;

import datamodel.rucksack.Rucksack;
import datamodel.rucksack.RucksackObject;
import datamodel.tree.Tree;
import datamodel.tree.TreeNode;
import datamodel.tree.TreeNodeFactory;
import datamodel.tree.linked.LinkedTree;
import datamodel.tree.sequential.SequentialTree;

import logic.traversal.TreeTraversalFactory;

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
	private Vector<Rucksack> filtered = new Vector<Rucksack>();
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
		int depth = 0;
		Rucksack rucksack = new Rucksack(capacity);
		
		TreeNode node = TreeNodeFactory.getNodeForTree(tree, rucksack);
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
	private void setNodes(int depth, Vector<RucksackObject> objects){		
		int size = objects.size();

		if (depth < size){

			// each rucksack is clone get different rucksacks in the tree
			Rucksack rucksack = tree.getCurrentNode().getRucksack().clone();
			TreeNode node = TreeNodeFactory.getNodeForTree(tree, rucksack);
			tree.setLeftNode(node);
			Rucksack rucksack1 = rucksack.clone();

			if (rucksack1.getWeight() + objects.elementAt(depth).getWeight() <= rucksack1.getCapacity()){
				rucksack1.insert(objects.elementAt(depth));
				node = TreeNodeFactory.getNodeForTree(tree, rucksack1);
				tree.setRightNode(node);
				
				// only if a right son was set
				tree.moveToRightNode();
				setNodes(depth+1, objects);
				tree.moveToParentNode();
			}

			// move to left node
			tree.moveToLeftNode();
			setNodes(depth+1, objects);
			tree.moveToParentNode();
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
			Vector<RucksackObject> needed, int capacity) {
		Rucksack rucksack = new Rucksack(capacity);
		
		tree.moveToRoot();
		TreeNode node = TreeNodeFactory.getNodeForTree(tree, rucksack);
		tree.setCurrentNode(node);

		// Packs the needed objects in the rucksack, create a new node
		for (int i = 0; i < needed.size(); i++){
			if (rucksack.getCapacity() >= rucksack.getWeight() + needed.elementAt(i).getWeight()){
				rucksack.insert(needed.elementAt(i));
				node = TreeNodeFactory.getNodeForTree(tree, rucksack);
				tree.setRightNode(node);
				tree.moveToRightNode();
			} else {
				throw new IllegalArgumentException("Needed objects don't fit into the rucksack");
			}
		}

		// and then start the normal procedure
		setNodes(0, objects);
	}

	/**
	 * goes recursively over all nodes of the tree and finds the best rucksack
	 * in the leafs.
	 * 
	 * @return the rucksack with the best value. The method returns null, if no
	 *         root exists
	 */
	public Rucksack findOptimalRucksack() {
		Rucksack optimal = null;

		tree.moveToRoot();
		for(TreeNode t : TreeTraversalFactory.createPreorder(tree))
			if(t != null)
				if(optimal == null || t.getRucksack().getValue() > optimal.getValue())
					optimal = t.getRucksack();

		return optimal;
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
		filtered.clear();
		
		if (tree == null){
			return filtered;
		} else {

			for(TreeNode t : TreeTraversalFactory.createPreorder(tree))
				if(t.consCheck(constraints) && (!filtered.contains(t.getRucksack())))
					filtered.add(t.getRucksack());

			return filtered;
		}
	}
		

	/**
	 * @return the current tree of this builder
	 */
	public Tree getTree() {
		return tree;
	}
}
