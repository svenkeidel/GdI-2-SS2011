package test_private;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

import java.util.HashMap;
import java.util.PriorityQueue;

import logic.huffman.HuffmanCode;

import org.junit.Before;
import org.junit.Test;

import datamodel.RGB;
import datamodel.huffman.tree.AbstractTreeFactory;
import datamodel.huffman.tree.Tree;
import datamodel.huffman.tree.TreeNode;
import datamodel.huffman.tree.linked.LinkedTreeFactory;

public class HuffmanTreeTest {

	public static enum Color {
		A(1, 1), B(2, 2), C(3, 4), D(4, 6), E(5, 8), F(6, 9), G(7, 10), W(
				new RGB(255, 255, 255, 255).getRGBValue(), 0);

		private int rgb, amount;

		Color(int rgb, int amount) {
			this.rgb = rgb;
			this.amount = amount;
		}

		public RGB getRGB() {
			return new RGB(rgb);
		}

		public int getAmount() {
			return amount;
		}
	}

	private Tree tree;

	@Before
	public void init() {
		AbstractTreeFactory factory = new LinkedTreeFactory();

		PriorityQueue<TreeNode> queue = new PriorityQueue<TreeNode>(1,
				new TreeNode.comparator());
		queue.add(factory.produceTreeNode(Color.A.getRGB(), Color.A.getAmount()));
		queue.add(factory.produceTreeNode(Color.B.getRGB(), Color.B.getAmount()));
		queue.add(factory.produceTreeNode(Color.C.getRGB(), Color.C.getAmount()));
		queue.add(factory.produceTreeNode(Color.D.getRGB(), Color.D.getAmount()));
		queue.add(factory.produceTreeNode(Color.E.getRGB(), Color.E.getAmount()));
		queue.add(factory.produceTreeNode(Color.F.getRGB(), Color.F.getAmount()));
		queue.add(factory.produceTreeNode(Color.G.getRGB(), Color.G.getAmount()));

		tree = factory.produceTree(queue);
	}

	private void isLeaf(Color col) {
		assertEquals(col.getRGB(), tree.getCurrentNode().getRGB());
		assertTrue(tree.getCurrentNode().isLeaf());
	}

	private void isNoLeaf() {
		assertNull(tree.getCurrentNode().getRGB());
		assertFalse(tree.getCurrentNode().isLeaf());
	}

	/**
	 * Tree Layout. Color: A B C D E F G Amount: 1 2 4 6 8 9 10
	 * 
	 * 40 ------------ 23 17 --------- ---- 13 10 9 8 ------ G F E 7 6 ----- D 4
	 * 3 C --- 2 1 B A
	 */
	@Test
	public void buildHuffmanTreeTest() {
		tree.moveToRoot();
		isNoLeaf();

		tree.moveToRightNode();
		isNoLeaf();

		tree.moveToRightNode();
		isLeaf(Color.E);

		tree.moveToParentNode();
		tree.moveToLeftNode();
		isLeaf(Color.F);

		tree.moveToRoot();
		tree.moveToLeftNode();
		isNoLeaf();

		tree.moveToRightNode();
		isLeaf(Color.G);

		tree.moveToParentNode();
		tree.moveToLeftNode();
		isNoLeaf();

		tree.moveToRightNode();
		isLeaf(Color.D);

		tree.moveToParentNode();
		tree.moveToLeftNode();
		isNoLeaf();

		tree.moveToLeftNode();
		isLeaf(Color.C);

		tree.moveToParentNode();
		tree.moveToRightNode();
		isNoLeaf();

		tree.moveToRightNode();
		isLeaf(Color.A);

		tree.moveToParentNode();
		tree.moveToLeftNode();
		isLeaf(Color.B);
	}
	
	/**
	 * Tree Layout. Color: A B C D E F G Amount: 1 2 4 6 8 9 10
	 * 
	 * Huffman Code Tree: ------------ 0 1 --------- ---- 0 1 0 1 ------ G F E 0
	 * 1 ----- D 0 1 C --- 0 1 B A
	 */
	@Test
	public void huffmanCodeTest() {
		HuffmanCode huffmanCode = new HuffmanCode(tree);
		HashMap<RGB, String> code = huffmanCode.getHuffmanCode();
		assertEquals("00011", code.get(Color.A.getRGB()));
		assertEquals("00010", code.get(Color.B.getRGB()));
		assertEquals("0000", code.get(Color.C.getRGB()));
		assertEquals("001", code.get(Color.D.getRGB()));
		assertEquals("11", code.get(Color.E.getRGB()));
		assertEquals("10", code.get(Color.F.getRGB()));
		assertEquals("01", code.get(Color.G.getRGB()));
	}

	/**
	 * Tree Layout. Color: A B C D E F G Amount: 1 2 4 6 8 9 10
	 * 
	 * 40 ------------ 23 17 --------- ---- 13 10 9 8 W G F E
	 */
	@Test
	public void compressionTest() {
		HuffmanCode code = new HuffmanCode(tree);
		code.compress(3);
		tree = code.getHuffmanTree();

		tree.moveToRoot();
		isNoLeaf();

		tree.moveToRightNode();
		isNoLeaf();

		tree.moveToRightNode();
		isLeaf(Color.E);

		tree.moveToParentNode();
		tree.moveToLeftNode();
		isLeaf(Color.F);

		tree.moveToRoot();
		tree.moveToLeftNode();
		isNoLeaf();

		tree.moveToRightNode();
		isLeaf(Color.G);

		tree.moveToParentNode();
		tree.moveToLeftNode();
		isLeaf(Color.W);
	}
}
