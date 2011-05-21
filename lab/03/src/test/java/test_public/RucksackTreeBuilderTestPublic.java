/**
 * 
 */
package test_public;

import java.util.Vector;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import datamodel.rucksack.Rucksack;
import datamodel.rucksack.RucksackObject;
import datamodel.tree.linked.LinkedTree;
import datamodel.tree.linked.LinkedTreeNode;
import logic.Constraints;
import logic.RucksackTreeBuilder;

/**
 * public Test class<br>
 * This class only tests method from RucksackTreeBuilder!<br>
 * We strongly advise to write your own test to test your tree implementation!
 * 
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 * 
 */
public class RucksackTreeBuilderTestPublic {

	private RucksackTreeBuilder builderLink = new RucksackTreeBuilder(false);
	private RucksackTreeBuilder builderSeq = new RucksackTreeBuilder(true);

	private RucksackObject o1 = new RucksackObject(10, 15);
	private RucksackObject o2 = new RucksackObject(15, 5);
	private RucksackObject o3 = new RucksackObject(23, 45);
	private RucksackObject o4 = new RucksackObject(200, 34);
	private RucksackObject o5 = new RucksackObject(34, 65);
	private RucksackObject o6 = new RucksackObject(5, 1);

	private Vector<RucksackObject> objects = new Vector<RucksackObject>();

	private static int counter = 0;

	@Before
	public void init() {
		this.objects.clear();
	}

	/**
	 * test for createRucksackTree()<br>
	 * 
	 * 2 Points
	 */
	@Test
	public void createTreeTest1() {

		try {
			// build right tree
			LinkedTree treeLink = new LinkedTree();
			Rucksack rucksack = new Rucksack(50);
			treeLink.setCurrentNode(new LinkedTreeNode(rucksack));
			treeLink.setLeftNode(new LinkedTreeNode(rucksack));
			treeLink.moveToLeftNode();
			treeLink.setLeftNode(new LinkedTreeNode(rucksack));
			treeLink.moveToLeftNode();
			treeLink.setLeftNode(new LinkedTreeNode(rucksack));
			rucksack = new Rucksack(50);
			rucksack.insert(o3);
			treeLink.setRightNode(new LinkedTreeNode(rucksack));
			treeLink.moveToParentNode();
			rucksack = new Rucksack(50);
			rucksack.insert(o2);
			treeLink.setRightNode(new LinkedTreeNode(rucksack));
			treeLink.moveToRightNode();
			treeLink.setLeftNode(new LinkedTreeNode(rucksack));
			rucksack = new Rucksack(50);
			rucksack.insert(o2);
			rucksack.insert(o3);
			treeLink.setRightNode(new LinkedTreeNode(rucksack));
			treeLink.moveToRoot();
			rucksack = new Rucksack(50);
			rucksack.insert(o1);
			treeLink.setRightNode(new LinkedTreeNode(rucksack));
			treeLink.moveToRightNode();
			treeLink.setLeftNode(new LinkedTreeNode(rucksack));
			treeLink.moveToLeftNode();
			treeLink.setLeftNode(new LinkedTreeNode(rucksack));
			treeLink.moveToParentNode();
			rucksack = new Rucksack(50);
			rucksack.insert(o1);
			rucksack.insert(o2);
			treeLink.setRightNode(new LinkedTreeNode(rucksack));
			treeLink.moveToRightNode();
			treeLink.setLeftNode(new LinkedTreeNode(rucksack));

			// add RucksackObjects
			this.objects.add(o1);
			this.objects.add(o2);
			this.objects.add(o3);

			// let builder build tree
			this.builderLink.createRucksackTree(objects, 50);
			this.builderSeq.createRucksackTree(objects, 50);

			// tree equals?
			Assert.assertEquals(treeLink, this.builderLink.getTree());
			Assert.assertEquals(treeLink, this.builderSeq.getTree());

			// inc counter
			counter += 2;
		} catch (AssertionError e) {
			throw e;
		}

	}

	/**
	 * tests the findOptimalRucksack() method<br>
	 * 
	 * 1 Point
	 */
	@Test
	public void findOptimalRucksackTest1() {

		try {
			// add objects
			this.objects.add(o1);
			this.objects.add(o2);
			this.objects.add(o3);

			// build trees
			this.builderLink.createRucksackTree(objects, 50);
			this.builderSeq.createRucksackTree(objects, 50);

			// get optimal rucksack
			Rucksack optimal = new Rucksack(50);
			optimal.insert(o2);
			optimal.insert(o3);

			// equals optimal rucksack?
			Assert.assertEquals(optimal, this.builderLink.findOptimalRucksack());
			Assert.assertEquals(optimal, this.builderSeq.findOptimalRucksack());

			// inc counter
			counter++;

		} catch (AssertionError e) {
			throw e;
		}
	}

	/**
	 * tests the filter() method<br>
	 * 
	 * 2 Points
	 */
	@Test
	public void filterTest1() {

		try {
			// add objects
			this.objects.add(o1);
			this.objects.add(o2);
			this.objects.add(o3);
			this.objects.add(o4);
			this.objects.add(o5);
			this.objects.add(o6);

			// build tree
			this.builderLink.createRucksackTree(objects, 200);
			this.builderSeq.createRucksackTree(objects, 200);

			// get filtered liste
			Vector<Rucksack> filtered = new Vector<Rucksack>();
			Rucksack ruck1 = new Rucksack(200);
			ruck1.insert(o1);
			Rucksack ruck2 = new Rucksack(200);
			ruck2.insert(o6);
			ruck2.insert(o1);
			Rucksack ruck3 = new Rucksack(200);
			ruck3.insert(o2);

			filtered.add(ruck1);
			filtered.add(ruck2);
			filtered.add(ruck3);

			// get constraints and filter tree
			Constraints c = new Constraints(10, 15, -1, 2);
			Vector<Rucksack> temp = this.builderLink.filter(c);

			// compare lists, Link
			for (Rucksack r : filtered) {
				Assert.assertTrue(temp.contains(r));
			}
			Assert.assertEquals(filtered.size(), temp.size());

			// compare lists, Seq
			temp = this.builderSeq.filter(c);

			for (Rucksack r : filtered) {
				Assert.assertTrue(temp.contains(r));
			}
			Assert.assertEquals(filtered.size(), temp.size());

			// inc counter
			counter += 2;
		} catch (AssertionError e) {
			throw e;
		}
	}
	
	@Test
	public void getResult() {
		System.out.println("Points in public tests: " + counter);
	}
}
