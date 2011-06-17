package test_private;

import static junit.framework.Assert.*;

import java.util.PriorityQueue;
import java.util.Random;

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
	
	@Before
	public void init() {
		this.TrieCode = new TrieCode();
		
		AbstractTreeFactory factory = new LinkedTreeFactory();
		PriorityQueue<TreeNode> queue = new PriorityQueue<TreeNode>(1,
				new TreeNode.comparator());
		rn = new Random();
		int random1;
		int random2;
		int random3;
		int random4;
		// fill up the trees
		for (int i = 0; i < 100000; i++){
			// fill the queue for the huffmanTree
			random1 = rn.nextInt()%256;
			random2 = rn.nextInt()%256;
			random3 = rn.nextInt()%256;
			random4 = rn.nextInt()%256;
			queue.add(factory.produceTreeNode(new RGB(random1,random2,random3,random4), rn.nextInt()));
			
			TrieCode.addColor(new RGB(random1,random2,random3,random4));
		}
		HuffTree = factory.produceTree(queue);
		HuffCode = new HuffmanCode(HuffTree);
	}
	
	@Test
	public void speedTest() {
		long time1;
		long time2;
		long time3;
		long time4;
		
		time1 = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++){
			TrieCode.containsColor(new RGB(rn.nextInt()%256,rn.nextInt()%256,rn.nextInt()%256,rn.nextInt()%256));
		}
		time2 = System.currentTimeMillis();
		System.out.println("Suchzeit im Trie: " + (time2-time1) + " ms");
		
		time3 = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++){
			HuffCode.containsColor(new RGB(rn.nextInt()%256,rn.nextInt()%256,rn.nextInt()%256,rn.nextInt()%256));
		}
		time4 = System.currentTimeMillis();
		System.out.println("Suchzeit im Huffman: " + (time4-time3) + " ms");
	}
}
