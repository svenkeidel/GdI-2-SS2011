package test_private;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import datamodel.tree.Tree;
import datamodel.tree.TreeNode;

public abstract class AbstractTreeTest {
	private static final Logger logger = Logger.getRootLogger();


	/**
	 * the tree should have the following layout.
	 *
	 * the number is the value of the node.
	 *          0
	 *        /   \
	 *       1     2
	 *      / \   / \
	 *     3   4 5   6
	 */
	private Tree tree;
	
	//Tree Elements
	private TreeNode root, one, two, three, four, five, six;

	/**
	 * Creates a concrete empty tree
	 */
	public abstract Tree createEmptyTree();

	/**
	 * Creates a concrete treenode with the specifed value
	 */
	public abstract TreeNode createTreeNode(int value);


	private static boolean configured = false;

	@BeforeClass
	public static void Constructor() {
		if(!configured) {
			BasicConfigurator.configure();
			logger.setLevel(Level.DEBUG);
			configured = true;
		}
	}

	/**
	 * Creates a tree in the form like above
	 */
	@Before
	public void init() {
		tree  = null;
		root = one = two = three = four = five = six = null;

		tree  = createEmptyTree();
		root  = createTreeNode(0);
		one   = createTreeNode(1);
 		two   = createTreeNode(2);
 		three = createTreeNode(3);
 		four  = createTreeNode(4);
 		five  = createTreeNode(5);
 		six   = createTreeNode(6);

		tree.setCurrentNode(root);
		tree.setLeftNode(one);
		tree.setRightNode(two);
		tree.moveToLeftNode();
		tree.setLeftNode(three);
		tree.setRightNode(four);
		tree.moveToRoot();
		tree.moveToRightNode();
		tree.setLeftNode(five);
		tree.setRightNode(six);
		tree.moveToRoot();
	}

	/**
	 * Testing moving operations
	 */

	@Test
	public void moveToLeftNodeTest() {
		tree.moveToLeftNode();
		tree.moveToLeftNode();
		assertEquals(3, tree.getCurrentNode().getValue());
	}

	@Test
	public void moveToRightNodeTest() {
		tree.moveToRightNode();
		tree.moveToRightNode();
		assertEquals(6, tree.getCurrentNode().getValue());
	}

	@Test
	public void moveToParentNodeTest() {
		tree.moveToRightNode();
		tree.moveToRightNode();
		tree.moveToParentNode();
		assertEquals(2, tree.getCurrentNode().getValue());
		tree.moveToLeftNode();
		tree.moveToParentNode();
		assertEquals(2, tree.getCurrentNode().getValue());
		tree.moveToParentNode();
		assertEquals(0, tree.getCurrentNode().getValue());
		tree.moveToLeftNode();
		tree.moveToLeftNode();
		tree.moveToParentNode();
		assertEquals(1, tree.getCurrentNode().getValue());
		tree.moveToRightNode();
		tree.moveToParentNode();
		assertEquals(1, tree.getCurrentNode().getValue());
		tree.moveToParentNode();
		assertEquals(0, tree.getCurrentNode().getValue());
	}

	@Test
	public void moveToRootTest() {
		tree.moveToRightNode();
		tree.moveToRightNode();
		tree.moveToRoot();
		assertEquals(0, tree.getCurrentNode().getValue());
	}


	/**
	 * Testing availability operations
	 */

	@Test
	public void hasLeftNodeTest() {
		assertTrue(tree.hasLeftNode());
		tree.moveToLeftNode();
		assertTrue(tree.hasLeftNode());
		tree.moveToLeftNode();
		assertFalse(tree.hasLeftNode());
	}

	@Test
	public void hasRightNodeTest() {
		assertTrue(tree.hasRightNode());
		tree.moveToRightNode();
		assertTrue(tree.hasRightNode());
		tree.moveToRightNode();
		assertFalse(tree.hasRightNode());
	}

	@Test
	public void hasParentNodeTest() {
		assertFalse(tree.hasParentNode());
		tree.moveToRightNode();
		assertTrue(tree.hasParentNode());
		tree.moveToRoot();
		tree.moveToLeftNode();
		assertTrue(tree.hasParentNode());
		tree.moveToParentNode();
		assertFalse(tree.hasParentNode());
	}


	/**
	 * Testing insertion operations
	 */

	@Test
	public void setLeftNodeTest() {
		Tree tmp = createEmptyTree();
		assertFalse(tmp.setLeftNode(one));
		tmp.setCurrentNode(root);
		assertTrue(tmp.setLeftNode(one));
	}

	@Test
	public void setRightNodeTest() {
		Tree tmp = createEmptyTree();
		assertFalse(tmp.setRightNode(two));
		tmp.setCurrentNode(root);
		assertTrue(tmp.setRightNode(two));
	}

	@Test
	public void setCurrentNodeTest() {
		Tree tmp = createEmptyTree();
		tmp.setCurrentNode(root);
		assertEquals(0, tmp.getCurrentNode().getValue());
	}

	@Test
	public void clearTree() {
		tree.clearTree();
		assertNull(tree.getCurrentNode());
	}

	@Test
	public void equalsTest() {

		TreeNode root, one, two, three, four, five, six;
		Tree tmp = createEmptyTree();

		root  = createTreeNode(0);
		one   = createTreeNode(1);
 		two   = createTreeNode(2);
 		three = createTreeNode(3);
 		four  = createTreeNode(4);
 		five  = createTreeNode(5);
 		six   = createTreeNode(6);

		tmp.setCurrentNode(root);
		tmp.setLeftNode(one);
		tmp.setRightNode(two);
		tmp.moveToLeftNode();
		tmp.setLeftNode(three);
		tmp.setRightNode(four);
		tmp.moveToRoot();
		tmp.moveToRightNode();
		tmp.setLeftNode(five);
		tmp.setRightNode(six);

		assertTrue(tree.equals(tmp));
		assertTrue(tmp.equals(tree));
		assertTrue(tmp.equals(tmp));
		assertTrue(tree.equals(tree));

		tmp.setRightNode(six);
		assertFalse(tree.equals(tmp));
		assertFalse(tmp.equals(tree));
	}
	


}