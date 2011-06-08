package test_private;


import logic.trie.TrieCode;
import datamodel.RGB;
import datamodel.trie.Trie;

import static junit.framework.Assert.*;
import org.junit.Test;
import org.junit.Before;


public class TrieTest {
	
	private Trie trie;
	private TrieCode triecode;
	private RGB a = new RGB(0, 255, 0, 255);
	private RGB b = new RGB(255, 255, 255, 255);
	private RGB c = new RGB(170, 255, 170, 255);
	
	@Before public void init(){
		
		trie = new Trie();
		triecode = new TrieCode();
	}
	
	
	@Test public void buildTrie(){
		
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
	

}
