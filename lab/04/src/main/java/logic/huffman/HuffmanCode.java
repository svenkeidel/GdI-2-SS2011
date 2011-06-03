/**
 * 
 */
package logic.huffman;

import io.ImageReader;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import datamodel.RGB;
import datamodel.huffman.tree.Tree;

/**
 * Creates Huffman code from a Huffman tree. Can encrypt and decrypt
 * BufferedImage's
 * 
 * @author Kevin Munk, Jakob Karolus
 * @version 1.0
 * 
 */
public class HuffmanCode {

	private HashMap<RGB, String> huffmanCode = null;
	private Tree huffmanTree = null;

	/**
	 * Initiates this object.
	 * 
	 * @param tree
	 *            the huffman tree to build the code from
	 */
	public HuffmanCode(Tree tree) {
		super();

		// set internal tree
		huffmanTree = tree;

		buildHuffmanCode();
	}

	/**
	 * Retrieves the Huffman code from the internal Huffman tree.
	 * The right subtrees will be coded with 1s and the left subtree will be coded with 0s.
	 */
	private void buildHuffmanCode() {
		//TODO: implement this method
		throw new UnsupportedOperationException("Implement me!");
	}

	
	/**
	 * Encrypt the image data from the ImageReader with the internal Huffman
	 * code
	 * 
	 * @param imageReader
	 *            the data which will be encrypted
	 * @return the image coded as an String. Does not contain other chars than
	 *         '0' and '1'.
	 */
	public String encryptImage(ImageReader imageReader) {
		//TODO: implement this method
		throw new UnsupportedOperationException("Implement me!");
	}

	/**
	 * Decrypt the encrypted image. If the encryptedImage does not contain
	 * enough colors, the remaining pixel won't be filled. If the encryptedImage
	 * has more colors than the height * width pixel specified, the remaining
	 * colors will be ignored.
	 * 
	 * @param encryptedImage
	 *            the encrypted image. the string must only contain '0' and '1'.
	 * @param width
	 *            the width of the returned image
	 * @param height
	 *            the height of the returned image
	 * @return the decrypted image
	 */
	public BufferedImage decryptImage(String encryptedImage, int width,
			int height) {
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		
		//TODO: implement this method
		//use this BufferedImage for your implementation
		
		return image;
	}

	/**
	 * @return the huffmanCode a map which holds for every color the huffman
	 *         code
	 */
	public HashMap<RGB, String> getHuffmanCode() {
		return huffmanCode;
	}

	/**
	 * return true if the given color is in the huffman tree
	 * 
	 * @param color
	 *            the color to search
	 * @return true if tree contains color; otherwise false
	 */
	public boolean containsColor(RGB color) {
		//TODO: implement this method
		throw new UnsupportedOperationException("Implement me!");
	}

	/**
	 * severs the given amount of levels in the stored tree to compress image
	 * information<br>
	 * if the amount is bigger than the actual number of levels nothing is done
	 * 
	 * @param count
	 *            the amount of levels to sever
	 * @return true if severing was successful; otherwise false (count >= number
	 *         of levels)
	 */
	public boolean compress(int count) {
		//TODO: implement this method
		throw new UnsupportedOperationException("Implement me!");
	}

	/**
	 * @return the huffmanTree
	 */
	public Tree getHuffmanTree() {
		return huffmanTree;
	}

}
