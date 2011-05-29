package test_private;

import static junit.framework.Assert.assertTrue;

import java.util.Vector;

import logic.RucksackTreeBuilder;

import org.junit.Before;
import org.junit.Test;

import datamodel.rucksack.Rucksack;
import datamodel.rucksack.RucksackObject;
import datamodel.tree.Tree;

public class RucksackTreeBuilderTest {

	Tree tree;
	private RucksackObject o15 = new RucksackObject(10, 15);
	private RucksackObject o5 = new RucksackObject(15, 5);
	private RucksackObject o45 = new RucksackObject(23, 45);
	private RucksackObject o20 = new RucksackObject(50, 20);

	RucksackTreeBuilder treeBuilder;
	Vector<RucksackObject> objects;
	Vector<RucksackObject> otherObjects;
	Vector<RucksackObject> needed;
	@Before
	public void init(){
		objects = new Vector<RucksackObject>();
		objects.add(o15);
		objects.add(o5);
		objects.add(o45);
		objects.add(o20);
		needed = new Vector<RucksackObject>();
		needed.add(o20);
		otherObjects = new Vector<RucksackObject>();
		otherObjects.add(o15);
		otherObjects.add(o5);
		otherObjects.add(o45);

		treeBuilder = new RucksackTreeBuilder(true);
	}
	
	/**
	 * Tree Layout.
	 *
	 * List of elements: {15, 5, 45, 20}
	 *
	 * Tree for a rucksack with infinite capacity:
	 *                                                    Root
	 *                      -----------------------------------------------------------------
	 *                     {}                                                             {15}
	 *          ---------------------------                              -----------------------------------------
	 *         {}                       {5}                            {15}                                  {15,5}
	 *    ------------           -------------------             -------------------              -----------------------------
	 *   {}        {45}         {5}            {5,45}           {15}          {15,45}            {15,5}                 {15,5,45}
	 *  ----    ----------   ----------   ----------------   ----------   ----------------   ----------------   ----------------------
	 * {} {20} {45} {45,20} {5}   {5,20} {5,45}   {5,45,20} {15} {15,20} {15,45} {15,45,20} {15,5}   {15,5,20} {15,5,45}    {15,5,45,20}
	 *
	 * Tree for a rucksack with a capacity of 60:
	 *                                        Root
	 *                      ---------------------------------------------
	 *                     {}                                           {15}
	 *          -----------------                            ------------------------
	 *         {}             {5}                          {15}                   {15,5}
	 *    ---------      --------------              ---------------        -------------------
	 *   {}     {45}    {5}           {5,45}      {15}       {15,45}      {15,5}               x
	 *  ----    ----    ----          -------   ----------   -------      ----------   
	 * {} {20} {45} x {5} {5,20}    {5,45} x   {15} {15,20} {15,45} x  {15,5}  {15,5,20}  
	 */
	@Test
	public void createRucksackTree_2Param_Test(){
		treeBuilder.createRucksackTree(objects, 60);
		tree = treeBuilder.getTree();
		Rucksack expected = new Rucksack(60);

		tree.moveToRoot();
		assertTrue(expected.equals(tree.getCurrentNode().getRucksack()));
		
		tree.moveToLeftNode();
		assertTrue(expected.equals(tree.getCurrentNode().getRucksack()));
		
		tree.moveToLeftNode();
		assertTrue(expected.equals(tree.getCurrentNode().getRucksack()));
		
		tree.moveToLeftNode();
		assertTrue(expected.equals(tree.getCurrentNode().getRucksack()));
		
		tree.moveToLeftNode();
		assertTrue(expected.equals(tree.getCurrentNode().getRucksack()));
		
		tree.moveToParentNode();
		tree.moveToRightNode();
		expected.insert(o20);
		assertTrue(expected.equals(tree.getCurrentNode().getRucksack())); 
		
		tree.moveToParentNode();
		tree.moveToParentNode();
		tree.moveToRightNode();
		expected.removeAll();
		expected.insert(o45);
		assertTrue(expected.equals(tree.getCurrentNode().getRucksack()));
		
		tree.moveToLeftNode();
		assertTrue(expected.equals(tree.getCurrentNode().getRucksack()));
		
		tree.moveToParentNode();
		tree.moveToParentNode();
		tree.moveToParentNode();
		tree.moveToRightNode();
		expected.removeAll();
		expected.insert(o5);
		assertTrue(expected.equals(tree.getCurrentNode().getRucksack()));
		
		tree.moveToRightNode();
		expected.insert(o45);
		assertTrue(expected.equals(tree.getCurrentNode().getRucksack()));
		
		tree.moveToParentNode();
		tree.moveToParentNode();
		tree.moveToParentNode();
		tree.moveToRightNode();
		expected.removeAll();
		expected.insert(o15);
		assertTrue(expected.equals(tree.getCurrentNode().getRucksack()));
		
		tree.moveToRightNode();
		expected.insert(o5);
		assertTrue(expected.equals(tree.getCurrentNode().getRucksack()));
		
		tree.moveToParentNode();
		expected.removeAll();
		expected.insert(o15);
		assertTrue(expected.equals(tree.getCurrentNode().getRucksack()));
		
		tree.moveToLeftNode();
		tree.moveToLeftNode();
		expected.removeAll();
		expected.insert(o15);
		assertTrue(expected.equals(tree.getCurrentNode().getRucksack()));
		
		tree.moveToRightNode();
		expected.insert(o20);
		assertTrue(expected.equals(tree.getCurrentNode().getRucksack()));
		
		tree.moveToParentNode();
		tree.moveToParentNode();
		tree.moveToRightNode();
		expected.removeAll();
		expected.insert(o15);
		expected.insert(o45);
		assertTrue(expected.equals(tree.getCurrentNode().getRucksack()));
		
		tree.moveToParentNode();
		tree.moveToParentNode();
		tree.moveToRightNode();
		tree.moveToLeftNode();
		tree.moveToRightNode();
		expected.removeAll();
		expected.insert(o15);
		expected.insert(o5);
		expected.insert(o20);
		assertTrue(expected.equals(tree.getCurrentNode().getRucksack()));
	}

	/**
	 * Find the rucksack with the optimal value.
	 *
	 *                                        Root
	 *                      ----------------------------------------
	 *                     {}                                       {15}
	 *          -----------------                         ------------------------
	 *         {}            {5}                         {15}               {15,5}
	 *    ---------      ----------------           ---------------      -------------------
	 *   {}     {45}    {5}          {5,45}       {15}      {15,45}    {15,5}               x
	 *  ----    ----    ----         -------    ----------   -------    ------------    
	 * {} {20} {45} x  {5}{5,20}  {5,45}   x  {15} {15,20} {15,45} x {15,5}  {15,5,20}  
	 *                                                                            ^
	 *                                                                         optimal
	 */
	@Test
	public void findOptimalRucksackTest() {
		treeBuilder.createRucksackTree(objects, 60);
		Rucksack actual = treeBuilder.findOptimalRucksack();
		Rucksack expected = new Rucksack(60);
		expected.insert(o15);
		expected.insert(o5);
	   	expected.insert(o20);
		assertTrue(expected.equals(actual));
		assertTrue(actual.equals(expected));
	}
	
	
	/**
	 * Tree layout:
	 *            root
	 *   ---------------------- 
	 *  x                     {20}
	 *           -----------------------------
	 *         {20}                        {20,15}
	 *     -------------               --------------------
	 *   {20}       {20,5}          {20,15}            {20,15,5}
	 *  -----       --------       -----------       ---------------
	 * {20}  x    {20,5}   x    {20,15}       x   {20,15,5}         x
	 */
	@Test
	public void createRucksackTree_3Param_Test(){
		treeBuilder.createRucksackTree(otherObjects, needed, 60);
		tree = treeBuilder.getTree();
		Rucksack expected = new Rucksack(60);
		
		tree.moveToRoot();
		
		tree.moveToRightNode();
		expected.insert(o20);
		assertTrue(expected.equals(tree.getCurrentNode().getRucksack()));
		
		tree.moveToLeftNode();
		assertTrue(expected.equals(tree.getCurrentNode().getRucksack()));
		
		tree.moveToLeftNode();
		assertTrue(expected.equals(tree.getCurrentNode().getRucksack()));
		
		tree.moveToLeftNode();
		assertTrue(expected.equals(tree.getCurrentNode().getRucksack()));
		
		tree.moveToLeftNode();
		assertTrue(expected.equals(tree.getCurrentNode().getRucksack()));
		
		tree.moveToParentNode();
		tree.moveToParentNode();
		tree.moveToRightNode();
		expected.insert(o5);
		assertTrue(expected.equals(tree.getCurrentNode().getRucksack()));
		
		tree.moveToParentNode();
		tree.moveToParentNode();
		tree.moveToRightNode();
		expected.removeAt(1);
		expected.insert(o15);
		assertTrue(expected.equals(tree.getCurrentNode().getRucksack()));
		
		tree.moveToRightNode();
		expected.insert(o5);
		assertTrue(expected.equals(tree.getCurrentNode().getRucksack()));
		
		tree.moveToLeftNode();
		assertTrue(expected.equals(tree.getCurrentNode().getRucksack()));
	}
}
