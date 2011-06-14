package test_private;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
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

	@Before
	public void init() {

		trie = new Trie();
		triecode = new TrieCode();
		filled_triecode = new TrieCode();

		filled_triecode.addColor(a);
		filled_triecode.addColor(b);
		filled_triecode.addColor(c);
		filled_triecode.addColor(black);
	}

	@Test
	public void TrieStructure() {

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
		trie.getCurrentNode().setLeafAtSlot(9);
		assertTrue(trie.getCurrentNode().hasLeafAtSlot(9));
		assertFalse(trie.getCurrentNode().hasLeafAtSlot(2));
		assertTrue(trie.getCurrentNode().getLeafAtSlot(9));
		assertTrue(trie.getCurrentNode().getLeafStatus());

		trie.moveToRoot();
		assertEquals(trie.getRoot(), trie.getCurrentNode());
	}

	@Test
	public void addColor() {

		assertFalse(trie.getCurrentNode().hasNodeAtSlot(0));
		assertFalse(triecode.getTrieCodeTree().getCurrentNode()
				.hasNodeAtSlot(0));

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

	@Test
	public void containsColor() {

		assertTrue(filled_triecode.containsColor(a));
		assertTrue(filled_triecode.containsColor(b));
		assertTrue(filled_triecode.containsColor(c));
		assertTrue(filled_triecode.containsColor(black));
		assertFalse(filled_triecode.containsColor(new RGB(123, 123, 123, 123)));
		assertFalse(filled_triecode.containsColor(new RGB(32, 32, 23, 23)));
	}

	@Test
	public void buildTrie() throws FileNotFoundException, IOException {

		ImageReader reader = new ImageReader("test2.png");
		triecode.buildTrie(reader);
		assertTrue(triecode.containsColor(black1));
		assertTrue(triecode.containsColor(green));
		assertTrue(triecode.containsColor(red));
		assertTrue(triecode.containsColor(blue));
		assertFalse(triecode.containsColor(b));
		assertFalse(triecode.containsColor(c));

	}

	@Test
	public void encryptImage() throws FileNotFoundException, IOException {

		ImageReader reader = new ImageReader("test2.png");
		triecode.buildTrie(reader);
		String x = triecode.encryptImage(reader);
		assertTrue(x.length() == 64*32);
		Assert.assertEquals(
				"11001100110011001100110011001100101010101010101010101010101010101001100110011001100110011001100110101010101010101010101010101010100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000110111011101110111011101110111011010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101101110111011101110111011101110111011101110111011101110111011101101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101110111011101110111011101110111010101010101010101010101010101011001100110011001100110011001100100010001000100010001000100010001101110111011101110111011101110111001100110011001100110011001100101010101010101010101010101010101010101010101010101010101010101011011101110111011101110111011101101110111011101110111011101110111101110111011101110111011101110110101010101010101010101010101010110011001100110011001100110011001101110111011101110111011101110110111011101110111011101110111011110111011101110111011101110111011010101010101010101010101010101010101010101010101010101010101010101110111011101110111011101110111010101010101010101010101010101010001000100010001000100010001000101010101010101010101010101010101100110011001100110011001100110010111011101110111011101110111011101010101010101010101010101010101100110011001100110011001100110010101010101010101010101010101010100010001000100010001000100010001011101110111011101110111011101111011101110111011101110111011101101010101010101010101010101010101010101010101010101010101010101011101110111011101110111011101110101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010110011001100110011001100110011001010101010101010101010101010101010101010101010101010101010101010110011001100110011001100110011001001100110011001100110011001100111011101110111011101110111011101110111011101110111011101110111011101110111011101110111011101110110101010101010101010101010101010101010101010101010101010101010101011101110111011101110111011101110101010101010101010101010101010",
				x);

	}

	@Test
	public void decryptImage() throws FileNotFoundException, IOException {

		String temp = "11001100110011001100110011001100101010101010101010101010101010101001100110011001100110011001100110101010101010101010101010101010100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000110111011101110111011101110111011010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101101110111011101110111011101110111011101110111011101110111011101101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101110111011101110111011101110111010101010101010101010101010101011001100110011001100110011001100100010001000100010001000100010001101110111011101110111011101110111001100110011001100110011001100101010101010101010101010101010101010101010101010101010101010101011011101110111011101110111011101101110111011101110111011101110111101110111011101110111011101110110101010101010101010101010101010110011001100110011001100110011001101110111011101110111011101110110111011101110111011101110111011110111011101110111011101110111011010101010101010101010101010101010101010101010101010101010101010101110111011101110111011101110111010101010101010101010101010101010001000100010001000100010001000101010101010101010101010101010101100110011001100110011001100110010111011101110111011101110111011101010101010101010101010101010101100110011001100110011001100110010101010101010101010101010101010100010001000100010001000100010001011101110111011101110111011101111011101110111011101110111011101101010101010101010101010101010101010101010101010101010101010101011101110111011101110111011101110101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010110011001100110011001100110011001010101010101010101010101010101010101010101010101010101010101010110011001100110011001100110011001001100110011001100110011001100111011101110111011101110111011101110111011101110111011101110111011101110111011101110111011101110110101010101010101010101010101010101010101010101010101010101010101011101110111011101110111011101110101010101010101010101010101010";
		ImageReader reader = new ImageReader("test2.png");
		triecode.buildTrie(reader);
		BufferedImage image = triecode.decryptImage(temp, 8, 8);
		Iterator<RGB> iter = reader.iterator();

//		 test decrypted image
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				RGB rgb = iter.next();
				Assert.assertEquals(rgb.getRGBValue(), image.getRGB(j, i));
			}
		}
	}

	@Test
	public void compress() throws FileNotFoundException, IOException {

		ImageReader reader = new ImageReader("test2.png");
		triecode.buildTrie(reader);
		assertTrue(triecode.containsColor(black1));
		assertTrue(triecode.containsColor(green));
		assertTrue(triecode.containsColor(red));
		assertTrue(triecode.containsColor(blue));

		String a = triecode.encryptImage(reader);
		triecode.compress(0);
		String b = triecode.getChoppedTrie().encryptImage(reader);

		assertTrue(a.equals(b));
		
		assertFalse(triecode.compress(24));

		triecode.compress(7);
		assertFalse(triecode.getChoppedTrie().containsColor(black1));
		assertFalse(triecode.getChoppedTrie().containsColor(green));
		assertFalse(triecode.getChoppedTrie().containsColor(red));
		assertFalse(triecode.getChoppedTrie().containsColor(blue));

		String x = triecode.getChoppedTrie().encryptImage(reader);
		assertFalse("11001100110011001100110011001100101010101010101010101010101010101001100110011001100110011001100110101010101010101010101010101010100010001000100010001000100010001000100010001000100010001000100010001000100010001000100010001000110111011101110111011101110111011010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101101110111011101110111011101110111011101110111011101110111011101101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101110111011101110111011101110111010101010101010101010101010101011001100110011001100110011001100100010001000100010001000100010001101110111011101110111011101110111001100110011001100110011001100101010101010101010101010101010101010101010101010101010101010101011011101110111011101110111011101101110111011101110111011101110111101110111011101110111011101110110101010101010101010101010101010110011001100110011001100110011001101110111011101110111011101110110111011101110111011101110111011110111011101110111011101110111011010101010101010101010101010101010101010101010101010101010101010101110111011101110111011101110111010101010101010101010101010101010001000100010001000100010001000101010101010101010101010101010101100110011001100110011001100110010111011101110111011101110111011101010101010101010101010101010101100110011001100110011001100110010101010101010101010101010101010100010001000100010001000100010001011101110111011101110111011101111011101110111011101110111011101101010101010101010101010101010101010101010101010101010101010101011101110111011101110111011101110101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010110011001100110011001100110011001010101010101010101010101010101010101010101010101010101010101010110011001100110011001100110011001001100110011001100110011001100111011101110111011101110111011101110111011101110111011101110111011101110111011101110111011101110110101010101010101010101010101010101010101010101010101010101010101011101110111011101110111011101110101010101010101010101010101010" == x);
		assertTrue(x.length() == 256);

		BufferedImage image = triecode.getChoppedTrie().decryptImage(x, 8, 8);
//		Iterator<RGB> iter = reader.iterator();

		// test correct chopping
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				RGB local_rgb = new RGB(image.getRGB(j, i));

				// This assert is only True if u fill with 1's while decrypting
				// look at TrieCode.decrypt-Function for more Information
				assertTrue(local_rgb.getBrightness() == 255);
				//
				
				assertTrue(local_rgb.getGreen() == 0
						|| local_rgb.getGreen() == 128);
				assertTrue(local_rgb.getBlue() == 0
						|| local_rgb.getBlue() == 128);
				assertTrue(local_rgb.getRed() == 0 || local_rgb.getRed() == 128);
				
//				 RGB rgb = iter.next();
//				 System.err.println("Original RBG: "+Integer.toBinaryString(rgb.getRGBValue())+"   Decrypted RGB: "+Integer.toBinaryString(image.getRGB(j,
//				 i))+"");
			}

		}
	}

	@Test
	public void Six() throws FileNotFoundException, IOException {

		ImageReader reader = new ImageReader("test.png");
		triecode.buildTrie(reader);
		
		
		long start = new Date().getTime();
		String noChopp = triecode.encryptImage(reader);
		System.out.println("String-Length of encrypted test.png after no compression: "+noChopp.length()+" ("+noChopp.length() / 8000+" kBytes)");
		BufferedImage noComp = triecode.decryptImage(noChopp,
				reader.getHeight(), reader.getWidth());
		IO.writeBinaryString("target/NoComp_encrypted", noChopp);
		IO.saveImage("target/NoComp", noComp);
		long runningTime = new Date().getTime() - start;
		System.out.println("Time elapsed for <encrypting, no compressing, decrypting>: "+runningTime+" ms\n");
		
		
		long start1 = new Date().getTime();
		triecode.compress(3);
		String ThreeChopp = triecode.getChoppedTrie().encryptImage(reader);
		System.out.println("String-Length of encrypted test.png after compress(3): "+ThreeChopp.length()+" ("+ThreeChopp.length() / 8000+" kBytes)");
		BufferedImage ThreeComp = triecode.getChoppedTrie().decryptImage(ThreeChopp,
				reader.getHeight(), reader.getWidth());
		IO.writeBinaryString("target/ThreeComp_encrypted", ThreeChopp);
		IO.saveImage("target/ThreeComp", ThreeComp);
		long runningTime1 = new Date().getTime() - start1;
		System.out.println("Time elapsed for <encrypting, compressing(3), decrypting>: "+runningTime1+" ms\n");
		
		
		long start2 = new Date().getTime();
		triecode.compress(6);
		String SixChopp = triecode.getChoppedTrie().encryptImage(reader);
		System.out.println("String-Length of encrypted test.png after compress(6): "+SixChopp.length()+" ("+SixChopp.length() / 8000+" kBytes)");
		BufferedImage SixComp = triecode.getChoppedTrie().decryptImage(SixChopp,
				reader.getHeight(), reader.getWidth());
		IO.writeBinaryString("target/SixComp_encrypted", SixChopp);
		IO.saveImage("target/SixComp", SixComp);
		long runningTime2 = new Date().getTime() - start2;
		System.out.println("Time elapsed for <encrypting, compressing(6), decrypting>: "+runningTime2+" ms\n");

		System.err.println("------------------------------------------------\n");
		
		assertTrue(new ImageReader("target/NoComp_encrypted") != null);
		assertTrue(new ImageReader("target/NoComp.png") != null);
		assertTrue(new ImageReader("target/ThreeComp_encrypted") != null);
		assertTrue(new ImageReader("target/ThreeComp.png") != null);
		assertTrue(new ImageReader("target/SixComp_encrypted") != null);
		assertTrue(new ImageReader("target/SixComp.png") != null);

	}

	@Test
	public void Seven() throws FileNotFoundException, IOException {
		ImageReader test_reader = new ImageReader("test.png");
		triecode.buildTrie(test_reader);

		ImageReader test2_reader = new ImageReader("test2.png");
		String encrypted = triecode.encryptImage(test2_reader);

		BufferedImage decrypted = triecode.decryptImage(encrypted,
				test2_reader.getHeight(), test2_reader.getWidth());
		IO.saveImage("target/Seven", decrypted);
		assertTrue(new ImageReader("target/Seven.png") != null);

		ImageReader pixel_coded = new ImageReader("target/Seven.png");
		ImageReader left = new ImageReader("left.png");
		ImageReader right = new ImageReader("right.png");

		assertTrue(test2_reader.AmountOfColorsInPicture(test2_reader)
				/ test2_reader.countDifferentColors() == 1);
		assertTrue(left.AmountOfColorsInPicture(right) == right
				.AmountOfColorsInPicture(left));

		float x = pixel_coded.AmountOfColorsInPicture(test2_reader);
		float x1 = pixel_coded.countDifferentColors();
		float y = test2_reader.AmountOfColorsInPicture(test_reader);
		float y1 = test2_reader.countDifferentColors();
		float z = left.AmountOfColorsInPicture(right);
		float z1 = left.countDifferentColors();

		System.out.println("" + z + " von " + z1 + " Farben (" + z / z1 * 100
				+ "%) aus left.png sind in right.png enthalten!");
		System.out.println("" + y + " von " + y1 + " Farben (" + y / y1 * 100
				+ "%) aus original test2.png sind in test.png enthalten!");
		System.out.println("" + x + " von " + x1 + " Farben (" + x / x1 * 100
				+ "%) aus coded test2.png sind in test.png enthalten!\n");
		
	}
}
