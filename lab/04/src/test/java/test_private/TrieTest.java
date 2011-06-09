package test_private;


import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import junit.framework.Assert;

import io.IO;
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
	private RGB black1 = new RGB(0, 0, 0, 255);
	private RGB green = new RGB(0, 255, 0, 255);
	private RGB blue = new RGB(0, 0, 255, 255);
	private RGB red = new RGB(255, 0, 0, 255);
	
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
	
		ImageReader reader = new ImageReader("test.png");
		triecode.buildTrie(reader);
		assertTrue(triecode.containsColor(black1));
		assertTrue(triecode.containsColor(green));
		assertTrue(triecode.containsColor(red));
		assertTrue(triecode.containsColor(blue));
		assertFalse(triecode.containsColor(b));
		assertFalse(triecode.containsColor(c));
		
			
	}
	
	@Test public void encryptImage() throws FileNotFoundException, IOException{
		
	
			ImageReader reader = new ImageReader("test.png");
			triecode.buildTrie(reader);
			String x = triecode.encryptImage(reader);
			assertTrue(x.length() > 32);
			Assert.assertEquals(
					"11001100110011001100110011001100101010101010101010101010101010101001100110011001100110011001100110101010101010101010101010101010100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000110111011101110111011101110111011010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101101110111011101110111011101110111011101110111011101110111011101101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101110111011101110111011101110111010101010101010101010101010101011001100110011001100110011001100100010001000100010001000100010001101110111011101110111011101110111001100110011001100110011001100101010101010101010101010101010101010101010101010101010101010101011011101110111011101110111011101101110111011101110111011101110111101110111011101110111011101110110101010101010101010101010101010110011001100110011001100110011001101110111011101110111011101110110111011101110111011101110111011110111011101110111011101110111011010101010101010101010101010101010101010101010101010101010101010101110111011101110111011101110111010101010101010101010101010101010001000100010001000100010001000101010101010101010101010101010101100110011001100110011001100110010111011101110111011101110111011101010101010101010101010101010101100110011001100110011001100110010101010101010101010101010101010100010001000100010001000100010001011101110111011101110111011101111011101110111011101110111011101101010101010101010101010101010101010101010101010101010101010101011101110111011101110111011101110101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010110011001100110011001100110011001010101010101010101010101010101010101010101010101010101010101010110011001100110011001100110011001001100110011001100110011001100111011101110111011101110111011101110111011101110111011101110111011101110111011101110111011101110110101010101010101010101010101010101010101010101010101010101010101011101110111011101110111011101110101010101010101010101010101010",
					x);
		System.err.println("test.png encrypted String without compress(): \n"+x+"\n");
		
	}
	
	@Test public void decryptImage() throws FileNotFoundException, IOException{
		
		String temp = "11001100110011001100110011001100101010101010101010101010101010101001100110011001100110011001100110101010101010101010101010101010100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000110111011101110111011101110111011010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101101110111011101110111011101110111011101110111011101110111011101101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101110111011101110111011101110111010101010101010101010101010101011001100110011001100110011001100100010001000100010001000100010001101110111011101110111011101110111001100110011001100110011001100101010101010101010101010101010101010101010101010101010101010101011011101110111011101110111011101101110111011101110111011101110111101110111011101110111011101110110101010101010101010101010101010110011001100110011001100110011001101110111011101110111011101110110111011101110111011101110111011110111011101110111011101110111011010101010101010101010101010101010101010101010101010101010101010101110111011101110111011101110111010101010101010101010101010101010001000100010001000100010001000101010101010101010101010101010101100110011001100110011001100110010111011101110111011101110111011101010101010101010101010101010101100110011001100110011001100110010101010101010101010101010101010100010001000100010001000100010001011101110111011101110111011101111011101110111011101110111011101101010101010101010101010101010101010101010101010101010101010101011101110111011101110111011101110101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010110011001100110011001100110011001010101010101010101010101010101010101010101010101010101010101010110011001100110011001100110011001001100110011001100110011001100111011101110111011101110111011101110111011101110111011101110111011101110111011101110111011101110110101010101010101010101010101010101010101010101010101010101010101011101110111011101110111011101110101010101010101010101010101010";
		ImageReader reader = new ImageReader("test.png");
		BufferedImage image = triecode.decryptImage(temp, 8, 8);
		Iterator<RGB> iter = reader.iterator();

		// test decrypted image
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				RGB rgb = iter.next();
				Assert.assertEquals(rgb.getRGBValue(), image.getRGB(j, i));
			}
		}
	}
	
	@Test public void compress() throws FileNotFoundException, IOException{
		
		ImageReader reader = new ImageReader("test.png");
		triecode.buildTrie(reader);
		assertTrue(triecode.containsColor(black1));
		assertTrue(triecode.containsColor(green));
		assertTrue(triecode.containsColor(red));
		assertTrue(triecode.containsColor(blue));
		
		String a = triecode.encryptImage(reader);
		triecode.compress(0);
		String b = triecode.encryptImage(reader);
		System.err.println("test.png encrypted String after compress(0): \n"+b+"\n");
		
		assertTrue(a.equals(b));
		assertFalse(triecode.compress(24));
		
		triecode.compress(6);
		assertFalse(triecode.containsColor(black1));
		assertFalse(triecode.containsColor(green));
		assertFalse(triecode.containsColor(red));
		assertFalse(triecode.containsColor(blue));
		
		String x = triecode.encryptImage(reader);
		assertFalse(
				"11001100110011001100110011001100101010101010101010101010101010101001100110011001100110011001100110101010101010101010101010101010100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000110111011101110111011101110111011010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101101110111011101110111011101110111011101110111011101110111011101101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101110111011101110111011101110111010101010101010101010101010101011001100110011001100110011001100100010001000100010001000100010001101110111011101110111011101110111001100110011001100110011001100101010101010101010101010101010101010101010101010101010101010101011011101110111011101110111011101101110111011101110111011101110111101110111011101110111011101110110101010101010101010101010101010110011001100110011001100110011001101110111011101110111011101110110111011101110111011101110111011110111011101110111011101110111011010101010101010101010101010101010101010101010101010101010101010101110111011101110111011101110111010101010101010101010101010101010001000100010001000100010001000101010101010101010101010101010101100110011001100110011001100110010111011101110111011101110111011101010101010101010101010101010101100110011001100110011001100110010101010101010101010101010101010100010001000100010001000100010001011101110111011101110111011101111011101110111011101110111011101101010101010101010101010101010101010101010101010101010101010101011101110111011101110111011101110101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010110011001100110011001100110011001010101010101010101010101010101010101010101010101010101010101010110011001100110011001100110011001001100110011001100110011001100111011101110111011101110111011101110111011101110111011101110111011101110111011101110111011101110110101010101010101010101010101010101010101010101010101010101010101011101110111011101110111011101110101010101010101010101010101010"
				== x);
		System.err.println("test.png encrypted String after compress(6): \n"+x+"\n");
		
		
		BufferedImage image = triecode.decryptImage(x, 8, 8);
		Iterator<RGB> iter = reader.iterator();

		// test correct chopping
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				RGB rgb = iter.next();
				RGB local_rgb = new RGB(image.getRGB(j, i));
				
				assertTrue(local_rgb.getBrightness() == 255);
				assertTrue(local_rgb.getGreen() == 0 || local_rgb.getGreen() == 128);
				assertTrue(local_rgb.getBlue() == 0 || local_rgb.getBlue() == 128);
				assertTrue(local_rgb.getRed() == 0 || local_rgb.getRed() == 128);
				
				System.err.println("Original RBG: "+Integer.toBinaryString(rgb.getRGBValue())+"   Decrypted RGB: "+Integer.toBinaryString(image.getRGB(j, i))+"");
			}
		
		}
	}
	
	@Test public void Six() throws FileNotFoundException, IOException{
		
		ImageReader reader = new ImageReader("test2.png");
		triecode.buildTrie(reader);
		
		String noChopp = triecode.encryptImage(reader);
		BufferedImage noComp = triecode.decryptImage(noChopp, reader.getHeight(), reader.getWidth());
		IO.writeBinaryString("NoComp_encrypted", noChopp);
		IO.saveImage("NoComp", noComp);
		
		triecode.compress(3);
		String ThreeChopp = triecode.encryptImage(reader);
		BufferedImage ThreeComp = triecode.decryptImage(ThreeChopp, reader.getHeight(), reader.getWidth());
		IO.writeBinaryString("ThreeComp_encrypted", ThreeChopp);
		IO.saveImage("ThreeComp", ThreeComp);
		
		triecode.compress(6);
		String SixChopp = triecode.encryptImage(reader);
		BufferedImage SixComp= triecode.decryptImage(SixChopp, reader.getHeight(), reader.getWidth());
		IO.writeBinaryString("SixComp_encrypted", SixChopp);
		IO.saveImage("SixComp", SixComp);
		
		assertTrue(new ImageReader("NoComp_encrypted") != null);
		assertTrue(new ImageReader("NoComp.png") != null);
		assertTrue(new ImageReader("ThreeComp_encrypted") != null);
		assertTrue(new ImageReader("ThreeComp.png") != null);
		assertTrue(new ImageReader("SixComp_encrypted") != null);
		assertTrue(new ImageReader("SixComp.png") != null);
		
		
	}
	
	@Test public void Seven() throws FileNotFoundException, IOException{
		ImageReader reader = new ImageReader("test.png");
		triecode.buildTrie(reader);
		
		ImageReader reader2 = new ImageReader("test2.png");
		String encrypted = triecode.encryptImage(reader2);
		BufferedImage decrypted = triecode.decryptImage(encrypted, reader2.getHeight(), reader2.getWidth());
		IO.saveImage("Seven", decrypted);
		assertTrue(new ImageReader("Seven.png") != null);
	}
}


