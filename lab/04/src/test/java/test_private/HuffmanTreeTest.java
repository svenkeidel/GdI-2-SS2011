package test_private;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import datamodel.RGB;

import datamodel.huffman.tree.Tree;

import datamodel.huffman.tree.linked.LinkedTreeFactory;

import static junit.framework.Assert.*;
import org.junit.Test;
import org.junit.Before;

import logic.huffman.HuffmanCode;
import logic.huffman.HuffmanTree;

public class HuffmanTreeTest {
	
	private static enum Color {
		A(1, 1),
		B(2, 2),
		C(3, 4),
		D(4, 6),
		E(5, 8),
		F(6, 9),
		G(7, 10);
		
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
		HashMap<RGB,Integer> amountOfColors = new HashMap<RGB,Integer>();
		amountOfColors.put(Color.A.getRGB(), Color.A.getAmount());
		amountOfColors.put(Color.B.getRGB(), Color.B.getAmount());
		amountOfColors.put(Color.C.getRGB(), Color.C.getAmount());
		amountOfColors.put(Color.D.getRGB(), Color.D.getAmount());
		amountOfColors.put(Color.E.getRGB(), Color.E.getAmount());
		amountOfColors.put(Color.F.getRGB(), Color.F.getAmount());
		amountOfColors.put(Color.G.getRGB(), Color.G.getAmount());

		PriorityQueue<Map.Entry<RGB,Integer>> queue = 
			new PriorityQueue<Map.Entry<RGB,Integer>>(1, 
				new HuffmanTree.ColorComparator());

		tree = new LinkedTreeFactory().produceTree(queue);
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
	 * Tree Layout.
	 * Color:  A B C D E F G
	 * Amount: 1 2 4 6 8 9 10
	 *
	 *                   40
	 *              ------------
	 *             23          17
	 *         ---------      ----
	 *        13       10    9    8
	 *      ------      G    F    E
	 *     7      6
	 *   -----    D
	 *  4     3     
	 *  C    ---       
	 *      2   1       
	 *      B   A
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
	 * Tree Layout.
	 * Color:  A B C D E F G
	 * Amount: 1 2 4 6 8 9 10
	 *
	 * Huffman Code Tree:
	 *              ------------
	 *             0            1
	 *         ---------      ----
	 *        0         1    0    1
	 *      ------      G    F    E
	 *     0      1
	 *   -----    D
	 *  0     1     
	 *  C    ---       
	 *      0   1       
	 *      B   A
	 */
	@Test
	public void huffmanCodeTest() {
		HuffmanCode huffmanCode = new HuffmanCode(tree);
		HashMap<RGB, String> code = huffmanCode.getHuffmanCode();
		assertEquals("00011", code.get(Color.A));
		assertEquals("00010", code.get(Color.B));
		assertEquals("0000",  code.get(Color.C));
		assertEquals("001",   code.get(Color.D));
		assertEquals("11",    code.get(Color.E));
		assertEquals("10",    code.get(Color.F));
		assertEquals("01",    code.get(Color.G));
	}
}
