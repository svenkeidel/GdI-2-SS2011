package test_private;

import static junit.framework.Assert.fail;
import io.IO;
import io.ImageReader;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import logic.huffman.HuffmanCode;
import logic.huffman.HuffmanTree;

import org.junit.Before;
import org.junit.Test;

import datamodel.huffman.tree.Tree;

public class HuffmanCodeTest {

	private Tree tree, tree2;
	private HuffmanCode code, code2;

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

	private void compressTolevel(int level) throws FileNotFoundException, IOException {
		tree2 = HuffmanTree.getHuffmanTree("test2.png");
		code2 = new HuffmanCode(tree2);
		ImageReader reader = new ImageReader("test2.png");
		String encryptedString = code2.encryptImage(reader);
		code2.compress(level);
		BufferedImage compressionLevel1png = code2.decryptImage(encryptedString, reader.getWidth(), reader.getHeight());
		//IO.writeBinaryString("target/compLevel"+level+"_encrypted", compressionLevel);
		IO.saveImage("target/compLevel"+level, compressionLevel1png);
	}

	@Test
	public void compressionLevel1Test() throws FileNotFoundException, IOException {
		compressTolevel(1);
	}

	@Test
	public void compressionLevel2Test() throws FileNotFoundException, IOException {
		compressTolevel(2);
	}

	@Test
	public void compressionLevel3Test() throws FileNotFoundException, IOException {
		compressTolevel(3);
	}

	@Test
	public void compressionLevel4Test() throws FileNotFoundException, IOException {
		compressTolevel(4);
	}
}
