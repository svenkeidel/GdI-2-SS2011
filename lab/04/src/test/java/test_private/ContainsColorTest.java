package test_private;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.Vector;

import logic.huffman.HuffmanCode;

import org.junit.Test;
import org.junit.Before;

import datamodel.RGB;
import datamodel.huffman.tree.AbstractTreeFactory;
import datamodel.huffman.tree.Tree;
import datamodel.huffman.tree.TreeNode;
import datamodel.huffman.tree.linked.LinkedTreeFactory;
import logic.trie.TrieCode;

public class ContainsColorTest {

	private Random rn; 
	
	private Tree HuffTree;
	private HuffmanCode HuffCode;
	
	private TrieCode TrieCode;
	
	Vector<RGB> matchingColors;
	Vector<RGB> missmatchingColors;
	
	private final int NUM_OF_COLORS = 10000;
	
	@Before
	public void init() {
		this.TrieCode = new TrieCode();
		
		matchingColors = new Vector<RGB>();
		missmatchingColors = new Vector<RGB>();
		
		AbstractTreeFactory factory = new LinkedTreeFactory();
		PriorityQueue<TreeNode> queue = new PriorityQueue<TreeNode>(1,
				new TreeNode.comparator());
		rn = new Random();
		int random1;
		int random2;
		int random3;
		int random4;
		// fill up the trees
		for (int i = 0; i < NUM_OF_COLORS; i++){
			// fill the queue for the huffmanTree
			random1 = rn.nextInt()%256;
			random2 = rn.nextInt()%256;
			random3 = rn.nextInt()%256;
			random4 = rn.nextInt()%256;
			queue.add(factory.produceTreeNode(new RGB(random1,random2,random3,random4), rn.nextInt()));
			matchingColors.add(new RGB(random1%256,random2,random3,random4));
			missmatchingColors.add(new RGB((random1+1)%256,random2,random3,random4));
			
			TrieCode.addColor(new RGB(random1,random2,random3,random4));
		}
		HuffTree = factory.produceTree(queue);
		HuffCode = new HuffmanCode(HuffTree);
	}
	
	@Test
	public void speedTest() {
		long time;
		
		// Positive tests
		time = System.currentTimeMillis();
		for (int i = 0; i < NUM_OF_COLORS; i++){
			TrieCode.containsColor(matchingColors.get(i));
		}
		System.out.println("Suchzeit bei positiver Suche im Trie: " + (System.currentTimeMillis() - time) + " ms");
		
		time = System.currentTimeMillis();
		for (int i = 0; i < NUM_OF_COLORS; i++){
			HuffCode.containsColor(matchingColors.get(i));
		}
		System.out.println("Suchzeit bei positiver Suche im Huffman: " + (System.currentTimeMillis() - time) + " ms");
		
		// Negative tests
		time = System.currentTimeMillis();
		for (int i = 0; i < NUM_OF_COLORS; i++){
			TrieCode.containsColor(missmatchingColors.get(i));
		}
		System.out.println("Suchzeit bei negativer Suche im Trie: " + (System.currentTimeMillis() - time) + " ms");
		
		time = System.currentTimeMillis();
		for (int i = 0; i < NUM_OF_COLORS; i++){
			HuffCode.containsColor(missmatchingColors.get(i));
		}
		System.out.println("Suchzeit bei negativer Suche im Huffman: " + (System.currentTimeMillis() - time) + " ms");
	}
}
