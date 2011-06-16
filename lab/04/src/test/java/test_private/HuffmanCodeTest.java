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

	private Tree tree;
	private HuffmanCode code;

	@Before
	public void init() throws FileNotFoundException, IOException {
		tree = HuffmanTree.getHuffmanTree("test.png");
		code = new HuffmanCode(tree);

	}

	private void createBinaryString() throws FileNotFoundException,
	IOException {
		ImageReader reader = new ImageReader("test2.png");
		String binaryStringFileName = "target/binaryStringRGB";
		
		IO.writeImageBinaryString(binaryStringFileName, reader);
		
		String binaryStringFileName2 = "target/binaryStringHuffman";
		tree = HuffmanTree.getHuffmanTree("test2.png");
		code = new HuffmanCode(tree);
		IO.writeBinaryString(binaryStringFileName2, code.encryptImage(reader));
	}
	
	private void compressTolevel(int level) throws FileNotFoundException,
			IOException {
		tree = HuffmanTree.getHuffmanTree("test2.png");
		code = new HuffmanCode(tree);
		String compressedFileName = "target/compLevel" + level;
		String compressedFileName2 = "target/compLevel" + level + "_2";

		ImageReader reader = new ImageReader("test2.png");
		String encryptedString = code.encryptImage(reader);
		code.compress(level);
		BufferedImage compressedFile = code.decryptImage(encryptedString,
				reader.getWidth(), reader.getHeight());
		IO.saveImage(compressedFileName, compressedFile);

		reader = new ImageReader(compressedFileName + ".png");
		encryptedString = code.encryptImage(reader);
		BufferedImage compressedFile2 = code.decryptImage(encryptedString,
				reader.getWidth(), reader.getHeight());
		IO.saveImage(compressedFileName2, compressedFile2);
	}

	@Test
	public void compressionLevel1Test() throws FileNotFoundException,
			IOException {
		compressTolevel(1);
		createBinaryString();
	}
}
