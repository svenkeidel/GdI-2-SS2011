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
		C(3, 3),
		D(4, 4),
		E(5, 5),
		F(6, 6),
		G(7, 7);
		
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
	 * Amount: 1 2 3 4 5 6 7
	 *
	 *               28 
	 *         ---------------
	 *        15             13       
	 *      ------         -------    
	 *     8      7       7       6   
	 *   ------   G     -----     F   
	 *  5      3       4     3        
	 *  E    -----     D     C        
	 *      2     1                       
	 *      B     A                       
	 */
	@Test
	public void buildHuffmanTreeTest() {
		tree.moveToRoot();

		tree.moveToRightNode();
		isNoLeaf();

		tree.moveToRightNode();
		isLeaf(Color.F);

		tree.moveToParentNode();
		tree.moveToLeftNode();
		isNoLeaf();

		tree.moveToRightNode();
		isLeaf(Color.C);

		tree.moveToParentNode();
		tree.moveToLeftNode();
		isLeaf(Color.D);

		tree.moveToRoot();
		tree.moveToLeftNode();
		isNoLeaf();

		tree.moveToRightNode();
		isLeaf(Color.G);

		tree.moveToParentNode();
		tree.moveToLeftNode();
		isNoLeaf();

		tree.moveToLeftNode();
		isLeaf(Color.E);

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
	 * Amount: 1 2 3 4 5 6 7
	 *
	 *         ---------------
	 *        0               1
	 *      ------         -------    
	 *     0      1       0       1   
	 *   ------   G     -----     F   
	 *  0      1       0     1        
	 *  E    -----     D     C        
	 *      0     1                       
	 *      B     A                       
	 */
	@Test
	public void huffmanCodeTest() {
		HuffmanCode huffmanCode = new HuffmanCode(tree);
		HashMap<RGB, String> code = huffmanCode.getHuffmanCode();
		assertEquals("0011", code.get(Color.A));
		assertEquals("0010", code.get(Color.B));
		assertEquals("101",  code.get(Color.C));
		assertEquals("100",  code.get(Color.D));
		assertEquals("000",  code.get(Color.E));
		assertEquals("11",   code.get(Color.F));
		assertEquals("01",   code.get(Color.G));
	}
}
