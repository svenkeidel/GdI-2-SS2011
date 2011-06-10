package test_private;

import java.io.FileNotFoundException;
import java.io.IOException;

import datamodel.huffman.tree.Tree;

import io.ImageReader;

import static junit.framework.Assert.*;
import org.junit.Test;
import org.junit.Before;

import logic.huffman.HuffmanCode;
import logic.huffman.HuffmanTree;

public class HuffmanCodeTest {
	
	private Tree tree;
	private HuffmanCode code;

	@Before
	public void init() throws FileNotFoundException, IOException {
		tree = HuffmanTree.getHuffmanTree("test.png");
		code = new HuffmanCode(tree);
	}

	@Test
	public void detectCorruptedEncryptedImage() throws FileNotFoundException, IOException {
		ImageReader reader = new ImageReader("test.png");
		StringBuffer encryptedImage = new StringBuffer(code.encryptImage(reader));

		// corrupt image muhuha :P
		encryptedImage.append(0);

		try {
			code.decryptImage(encryptedImage.toString(), 8, 8);

			// Fail, no exception was thrown
			fail();
		} catch(IllegalArgumentException e) {
			// Everything is ok
		}
	}
}
