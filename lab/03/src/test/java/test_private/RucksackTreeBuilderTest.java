package test_private;

import java.util.Iterator;
import java.util.Vector;

import datamodel.rucksack.Rucksack;

import datamodel.tree.Tree;
import datamodel.tree.TreeNode;

import datamodel.tree.sequential.SequentialTree;

import static junit.framework.Assert.*;

import org.junit.Before;
import org.junit.Test;

import datamodel.rucksack.RucksackObject;

import logic.RucksackTreeBuilder;

import logic.traversal.TreeTraversalFactory;

public class RucksackTreeBuilderTest {

	Tree tree;
	private RucksackObject o10 = new RucksackObject(10, 15);
	private RucksackObject o15 = new RucksackObject(15, 5);
	private RucksackObject o23 = new RucksackObject(23, 45);
	private RucksackObject o50 = new RucksackObject(50, 20);

	RucksackTreeBuilder treeBuilder;
	Vector<RucksackObject> objects;
	@Before
	public void init(){
		objects = new Vector<RucksackObject>();
		objects.add(o10);
		objects.add(o15);
		objects.add(o23);
		objects.add(o50);

		treeBuilder = new RucksackTreeBuilder(true);
	}
	
	/**
	 * Tree Layout.
	 *
	 * List of elements: {10, 15, 23, 50}
	 *
	 * Tree for a rucksack with infinite capacity:
	 *                                                    Root
	 *                      -----------------------------------------------------------------
	 *                     {}                                                             {10}
	 *          ---------------------------                              -----------------------------------------
	 *         {}                       {15}                            {10}                                {10,15}
	 *    ------------           -------------------             -------------------              -----------------------------
	 *   {}        {23}         {15}          {15,23}           {10}          {10,23}            {10,15}              {10,15,23}
	 *  ----    ----------   ----------   ----------------   ----------   ----------------   ----------------   ----------------------
	 * {} {50} {23} {23,50} {15} {15,50} {15,23} {15,23,50} {10} {10,50} {10,23} {10,23,50} {10,15} {10,15,50} {10,15,23} {10,15,23,50}
	 *
	 * Tree for a rucksack with a capacity of 60:
	 *                                        Root
	 *                      ----------------------------------------
	 *                     {}                                    {10}
	 *          -----------------                      ------------------------
	 *         {}            {15}                     {10}               {10,15}
	 *    ---------      -------------         ---------------      -------------------
	 *   {}     {23}    {15}    {15,23}       {10}      {10,23}    {10,15}    {10,15,23}
	 *  ----    ----    ----    -------    ----------   -------    -------    ----------
	 * {} {50} {23} x  {15} x  {15,23} x  {10} {10,50} {10,23} x  {10,15} x  {10,15,23} x
	 */
	@Test
	public void createRucksackTree_2Param_Test(){
		treeBuilder.createRucksackTree(objects, 60);
		tree = treeBuilder.getTree();
		Iterator<TreeNode> iterator = TreeTraversalFactory.createPreorder(tree);
		Rucksack expected = new Rucksack(60);

		assertNull(iterator.next());
		assertEquals(expected, iterator.next());
		assertEquals(expected, iterator.next());
		assertEquals(expected, iterator.next());
		assertEquals(expected, iterator.next());

		expected.insert(o50);
		// contents: 50
		assertEquals(expected, iterator.next());
		expected.removeAt(0);

		expected.insert(o23);
		// contents: 23
		assertEquals(expected, iterator.next());
		assertEquals(expected, iterator.next());
		expected.removeAt(0);

		expected.insert(o15);
		// contents: 15
		assertEquals(expected, iterator.next());
		assertEquals(expected, iterator.next());
		assertEquals(expected, iterator.next());

		expected.insert(o23);
		// contents: 15, 23
		assertEquals(expected, iterator.next());
		assertEquals(expected, iterator.next());
		expected.removeAll();

		expected.insert(o10);
		// contents: 10
		assertEquals(expected, iterator.next());
		assertEquals(expected, iterator.next());
		assertEquals(expected, iterator.next());
		assertEquals(expected, iterator.next());

		expected.insert(o50);
		// contents: 10, 50
		assertEquals(expected, iterator.next());
		expected.removeAt(1);

		expected.insert(o23);
		// contents: 10, 23
		assertEquals(expected, iterator.next());
		assertEquals(expected, iterator.next());
		expected.removeAt(1);

		expected.insert(o15);
		// contents: 10, 15
		assertEquals(expected, iterator.next());
		assertEquals(expected, iterator.next());

		expected.insert(o23);
		// contents: 10, 15, 23
		assertEquals(expected, iterator.next());
		assertEquals(expected, iterator.next());

		assertFalse(iterator.hasNext());
	}

	/**
	 * Find the rucksack with the optimal value.
	 *
	 *                                        Root
	 *                      ----------------------------------------
	 *                     {}                                    {10}
	 *          -----------------                      ------------------------
	 *         {}            {15}                     {10}               {10,15}
	 *    ---------      -------------         ---------------      -------------------
	 *   {}     {23}    {15}    {15,23}       {10}      {10,23}    {10,15}    {10,15,23}
	 *  ----    ----    ----    -------    ----------   -------    -------    ----------
	 * {} {50} {23} x  {15} x  {15,23} x  {10} {10,50} {10,23} x  {10,15} x  {10,15,23} x
	 *                                                                            ^
	 *                                                                         optimal
	 */
	@Test
	public void findOptimalRucksackTest() {
		treeBuilder.createRucksackTree(objects, 60);
		Rucksack actual = treeBuilder.findOptimalRucksack();
		Rucksack expected = new Rucksack(60);
		expected.insert(o10);
		expected.insert(o15);
	   	expected.insert(o23);
		assertTrue(expected.equals(actual));
		assertTrue(actual.equals(expected));
	}
}
