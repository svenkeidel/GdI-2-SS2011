package test_private;

import java.util.Iterator;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import datamodel.tree.Tree;
import datamodel.tree.TreeNode;

import datamodel.tree.sequential.SequentialTree;
import datamodel.tree.sequential.SequentialTreeNode;

import static logic.traversal.TreeTraversalFactory.*;

public class TreeTraversalTest {

	/**
	 * the tree should have the following layout.
	 *
	 * the number is the value of the node.
	 *          0
	 *        /   \
	 *       1     2
	 *      / \   / \
	 *     3   4 5   6
	 *        / \
	 *       7   8
	 */
	private static Tree tree;
	
	//Tree Elements
	private static TreeNode root, one, two, three, four, five, six, seven, eight;

	private Iterator<TreeNode> iterator;

	/**
	 * Creates a tree in the form like above
	 */
	@BeforeClass
	public static void initClass() {
		tree  = new SequentialTree();
		root  = new SequentialTreeNode(0, 0);
		one   = new SequentialTreeNode(0, 1);
 		two   = new SequentialTreeNode(0, 2);
 		three = new SequentialTreeNode(0, 3);
 		four  = new SequentialTreeNode(0, 4);
 		five  = new SequentialTreeNode(0, 5);
 		six   = new SequentialTreeNode(0, 6);
 		seven = new SequentialTreeNode(0, 7);
 		eight = new SequentialTreeNode(0, 8);

		tree.setCurrentNode(root);
		tree.setLeftNode(one);
		tree.setRightNode(two);
		tree.moveToLeftNode();
		tree.setLeftNode(three);
		tree.setRightNode(four);
		tree.moveToRightNode();
		tree.setLeftNode(seven);
		tree.setRightNode(eight);
		tree.moveToRoot();
		tree.moveToRightNode();
		tree.setLeftNode(five);
		tree.setRightNode(six);
		tree.moveToRoot();
	}

	@Before
	public void init() {
		tree.moveToRoot();
	}
	
	private void checkOrder(int[] order) {
		for(int i = 0; i < order.length; i++)
			assertEquals(order[i], iterator.next().getValue());
		assertFalse(iterator.hasNext());
	}

	@Test 
	public void preorderTest() {
		iterator = createPreorderIterator(tree);
		checkOrder(new int[] {0, 1, 3, 4, 7, 8, 2, 5, 6});
	}

	@Test
	public void inorderTest() {
		iterator = createInorderIterator(tree);
		checkOrder(new int[] {3, 1, 7, 4, 8, 0, 5, 2, 6});
	}

	@Test
	public void postorderTest() {
		iterator = createPostorderIterator(tree);
		checkOrder(new int[] {3, 7, 8, 4, 1, 5, 6, 2, 0});
	}
}
