package test_private;


import java.io.FileNotFoundException;
import java.io.IOException;

import io.ImageReader;
import logic.trie.TrieCode;
import datamodel.RGB;
import datamodel.trie.Trie;

import static junit.framework.Assert.*;
import org.junit.Test;
import org.junit.Before;


public class TrieTest {
	
	private Trie trie;
	private TrieCode triecode;
	private TrieCode filled_triecode;
	
	private RGB a = new RGB(0, 255, 0, 255);
	private RGB b = new RGB(255, 255, 255, 255);
	private RGB c = new RGB(170, 255, 170, 255);
	private RGB black = new RGB(0, 0, 0, 0);
	private RGB green = new RGB(0, 255, 0, 0);
	private RGB blue = new RGB(0, 0, 255, 0);
	private RGB red = new RGB(255, 0, 0, 0);
	
	@Before public void init(){
		
		trie = new Trie();
		triecode = new TrieCode();
		filled_triecode = new TrieCode();
		
		filled_triecode.addColor(a);
		filled_triecode.addColor(b);
		filled_triecode.addColor(c);
		filled_triecode.addColor(black);
	}
	
	
	@Test public void TrieStructure(){
		
		trie.moveToRoot();
		
		trie.createChildAt(0, false);
		assertTrue(trie.getCurrentNode().hasNodeAtSlot(0));
		assertFalse(trie.getCurrentNode().hasLeafAtSlot(0));
		assertFalse(trie.getCurrentNode().getLeafStatus());
		
		trie.createChildAt(12, false);
		assertTrue(trie.getCurrentNode().hasNodeAtSlot(12));
		assertFalse(trie.getCurrentNode().hasLeafAtSlot(12));
		
		trie.moveToChild(0);
		assertEquals(trie.getRoot().getNodeAtSlot(0), trie.getCurrentNode());
		assertFalse(trie.getCurrentNode().getLeafStatus());
		
		trie.createChildAt(2, true);
		trie.moveToChild(2);
		trie.getCurrentNode().setLeafValueAtSlot(7, 9);
		assertTrue(trie.getCurrentNode().hasLeafAtSlot(7));
		assertFalse(trie.getCurrentNode().hasLeafAtSlot(2));
		assertTrue(trie.getCurrentNode().getLeafAtSlot(7) == 9);
		assertTrue(trie.getCurrentNode().getLeafStatus());
		
		trie.moveToRoot();
		assertEquals(trie.getRoot(), trie.getCurrentNode());
	}
	
	
	@Test public void addColor(){
		
		assertFalse(trie.getCurrentNode().hasNodeAtSlot(0));
		assertFalse(triecode.getTrieCodeTree().getCurrentNode().hasNodeAtSlot(0));
		
		triecode.addColor(a);
		
		Trie c_trie = triecode.getTrieCodeTree();
		
		assertTrue(c_trie.getRoot() != null);
		assertEquals(c_trie.getCurrentNode(), c_trie.getRoot());
		assertTrue(c_trie.getRoot().hasNodeAtSlot(10));
		assertTrue(c_trie.getCurrentNode().hasNodeAtSlot(10));
		
		assertFalse(c_trie.getCurrentNode().hasNodeAtSlot(2));
		
		c_trie.moveToChild(10);
		assertTrue(c_trie.getCurrentNode().hasNodeAtSlot(10));
		assertFalse(c_trie.getCurrentNode().getLeafStatus());
		
		
		triecode.addColor(b);
		
		c_trie = triecode.getTrieCodeTree();
		
		
		assertTrue(c_trie.getCurrentNode().hasNodeAtSlot(10));
		assertTrue(c_trie.getCurrentNode().hasNodeAtSlot(15));
		
		assertFalse(c_trie.getCurrentNode().hasNodeAtSlot(2));
		
		c_trie.moveToChild(10);
		assertTrue(c_trie.getCurrentNode().hasNodeAtSlot(10));
		assertFalse(c_trie.getCurrentNode().hasNodeAtSlot(15));
		assertFalse(c_trie.getCurrentNode().getLeafStatus());
		
		
		triecode.addColor(c);
		
		c_trie = triecode.getTrieCodeTree();
		
		assertTrue(c_trie.getCurrentNode().hasNodeAtSlot(10));
		assertTrue(c_trie.getCurrentNode().hasNodeAtSlot(15));
		
		c_trie.moveToChild(15);
		
		assertTrue(c_trie.getCurrentNode().hasNodeAtSlot(10));
		assertTrue(c_trie.getCurrentNode().hasNodeAtSlot(15));
		
	}
	
	@Test public void containsColor(){
		
		assertTrue(filled_triecode.containsColor(a));
		assertTrue(filled_triecode.containsColor(b));
		assertTrue(filled_triecode.containsColor(c));
		assertTrue(filled_triecode.containsColor(black));
		assertFalse(filled_triecode.containsColor(new RGB(123,123,123,123)));
		assertFalse(filled_triecode.containsColor(new RGB(32, 32, 23, 23)));	
	}
	
	@Test public void buildTrie() throws FileNotFoundException, IOException{
		try{
		ImageReader reader = new ImageReader("test2.png");
		triecode.buildTrie(reader);
		assertTrue(triecode.containsColor(black));
		assertTrue(triecode.containsColor(green));
		assertTrue(triecode.containsColor(red));
		assertTrue(triecode.containsColor(blue));
		assertFalse(triecode.containsColor(a));
		assertFalse(triecode.containsColor(c));
		
		
		}catch(AssertionError e){
			
		}
		
	}

}
