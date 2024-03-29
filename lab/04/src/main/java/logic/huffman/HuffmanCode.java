package logic.huffman;

import java.lang.StringBuffer;

import datamodel.huffman.tree.TreeNode;

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
	 * @param tree the huffman tree to build the code from
	 */
	public HuffmanCode(Tree tree) {
		super();

		// set internal tree
		huffmanTree = tree;

		buildHuffmanCode();
	}

	/**
	 * Retrieves the Huffman code from the internal Huffman tree. The right
	 * subtrees will be coded with 1s and the left subtree will be coded with
	 * 0s.
	 */
	private void buildHuffmanCode() {
		huffmanTree.moveToRoot();
		huffmanCode = new HashMap<RGB, String>();
		buildCodeForSubtree(new StringBuffer());
	}

	/**
	 * Retrieves the Huffman code from the internal Huffman subtree.
	 */
	private void buildCodeForSubtree(StringBuffer codeSequence) {

		TreeNode currentNode = huffmanTree.getCurrentNode();

		if (currentNode.isLeaf()) {
			RGB currentColor = currentNode.getRGB();
			huffmanCode.put(currentColor, codeSequence.toString());
		}

		if (huffmanTree.hasLeftNode()) {
			huffmanTree.moveToLeftNode();
			buildCodeForSubtree(codeSequence.append(0));
			codeSequence.deleteCharAt(codeSequence.length() - 1);
			huffmanTree.moveToParentNode();
		}

		if (huffmanTree.hasRightNode()) {
			huffmanTree.moveToRightNode();
			buildCodeForSubtree(codeSequence.append(1));
			codeSequence.deleteCharAt(codeSequence.length() - 1);
			huffmanTree.moveToParentNode();
		}
	}

	/**
	 * Encrypt the image data from the ImageReader with the internal Huffman
	 * code
	 * 
	 * @param imageReader the data which will be encrypted
	 * @return the image coded as an String. Does not contain other chars than
	 * '0' and '1'.
	 */
	public String encryptImage(ImageReader imageReader) {
		StringBuffer out = new StringBuffer();

		for (RGB color : imageReader)
			if(huffmanCode.containsKey(color))
				out.append(huffmanCode.get(color));

		return out.toString();
	}

	/**
	 * Decrypt the encrypted image. If the encryptedImage does not contain
	 * enough colors, the remaining pixel won't be filled. If the encryptedImage
	 * has more colors than the height * width pixel specified, the remaining
	 * colors will be ignored.
	 * 
	 * @param encryptedImage the encrypted image. the string must only contain
	 * '0' and '1'.
	 * @param width the width of the returned image
	 * @param height the height of the returned image
	 * @return the decrypted image
	 */
	public BufferedImage decryptImage(String encryptedImage, int width,
			int height) {
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);

		// Convert string to bool array for performance

		try {
			imagePosition = 0;
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					image.setRGB(x, y, decryptColor(encryptedImage)
							.getRGBValue());
				}
			}
		} catch (IllegalArgumentException e) {
			// Ignore
		}

		return image;
	}

	private int imagePosition;

	/**
	 * decrypts a codesequence into a color
	 */
	private RGB decryptColor(String encImage) {
		huffmanTree.moveToRoot();

		// Iterate through the tree along the codeword
		while (!huffmanTree.getCurrentNode().isLeaf()) {

			try {

				if (encImage.charAt(imagePosition) == '1')
					huffmanTree.moveToRightNode();
				else
					huffmanTree.moveToLeftNode();

				imagePosition++;

			} catch (Exception e) {
				throw new IllegalArgumentException(
						"Picture can't be decrypted correctly");
			}
		}

		RGB nextColor = huffmanTree.getCurrentNode().getRGB();

		// skip rest of codeword
		if (huffmanTree.getCurrentNode().isCutOff()) {
			do {

				if (encImage.charAt(imagePosition) == '1')
					huffmanTree.moveToRightNode();
				else
					huffmanTree.moveToLeftNode();

				imagePosition++;

			} while (!huffmanTree.getCurrentNode().isLeaf());
		}

		return nextColor;
	}

	/**
	 * @return the huffmanCode a map which holds for every color the huffman
	 * code
	 */
	public HashMap<RGB, String> getHuffmanCode() {
		return huffmanCode;
	}

	/**
	 * return true if the given color is in the huffman tree
	 * 
	 * @param color the color to search
	 * @return true if tree contains color; otherwise false
	 */
	public boolean containsColor(RGB color) {
		return huffmanTree.containsColor(color);
	}

	/**
	 * severs the given amount of levels in the stored tree to compress image
	 * information<br>
	 * if the amount is bigger than the actual number of levels nothing is done
	 * 
	 * @param count the amount of levels to sever
	 * @return true if severing was successful; otherwise false (count >= number
	 * of levels)
	 */
	public boolean compress(int count) {
		int depth = huffmanTree.getDepth();
		if (count >= depth)
			return false;
		compress(count, depth, 0);
		return true;
	}

	/**
	 * recursively traverse through the tree. If the wanted depth is reached,
	 * remove the colors below and cut of the subtree.
	 * 
	 * @param count the count of level to cut
	 * @param depth the number of levels below the root node
	 * @param currentDepth the depth traversed down the tree
	 */
	private void compress(int count, int depth, int currentDepth) {

		if (depth - currentDepth <= count
				&& (huffmanTree.hasLeftNode() || huffmanTree.hasRightNode())) {
			removeCuttedColors(0);
			huffmanTree.getCurrentNode().cutOff();
			huffmanTree.getCurrentNode().setRGB(new RGB(255, 255, 255, 255));
		} else {

			if (huffmanTree.hasLeftNode()) {
				huffmanTree.moveToLeftNode();
				compress(count, depth, currentDepth + 1);
				huffmanTree.moveToParentNode();
			}

			if (huffmanTree.hasRightNode()) {
				huffmanTree.moveToRightNode();
				compress(count, depth, currentDepth + 1);
				huffmanTree.moveToParentNode();
			}
		}
	}

	/**
	 * Remove the cutted colors from the encoding map.
	 */
	private void removeCuttedColors(int levelsUnderCut) {
		if (huffmanTree.getCurrentNode().isLeaf())
			huffmanCode.remove(huffmanTree.getCurrentNode().getRGB());

		if (huffmanTree.hasLeftNode()) {
			huffmanTree.moveToLeftNode();
			removeCuttedColors(levelsUnderCut+1);
			huffmanTree.moveToParentNode();
		}

		if (huffmanTree.hasRightNode()) {
			huffmanTree.moveToRightNode();
			removeCuttedColors(levelsUnderCut+1);
			huffmanTree.moveToParentNode();
		}
	}

	/**
	 * @return the huffmanTree
	 */
	public Tree getHuffmanTree() {
		return huffmanTree;
	}

}
