/**
 * 
 */
package logic.trie;

import io.ImageReader;

import java.awt.image.BufferedImage;

import datamodel.RGB;
import datamodel.trie.Trie;

/**
 * provides methods to build and maintain a TrieCode- Tree
 * 
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 * 
 */
public class TrieCode {

	private Trie trieCodeTree;

	/**
	 * constructor
	 * 
	 */
	public TrieCode() {
		this.trieCodeTree = new Trie();
	}

	/**
	 * builds a trie from the given image
	 * 
	 * @param reader
	 *            the image via an ImageReader
	 */
	public void buildTrie(ImageReader reader) {
		//TODO: implement this method
		throw new UnsupportedOperationException("Implement me!");
	}

	/**
	 * encrypts the given picture (via an ImageReader) using the stored trie
	 * 
	 * @param reader the picture via an ImageReader
	 * @return the encrypted picture as a binary string
	 */
	public String encryptImage(ImageReader reader) {
		//TODO: implement this method
		throw new UnsupportedOperationException("Implement me!");
	}

	
	/**
	 * adds the color to this trie<br>
	 * 
	 * @param color
	 *            the color to add
	 */
	public void addColor(RGB color) {
		//TODO: implement this method
		throw new UnsupportedOperationException("Implement me!");
	}


	/**
	 * decrypts the given picture and returns it via a BufferedImage
	 * 
	 * @param picture
	 *            the picture encrypted as a binary string
	 * @param height
	 *            the height of the picture
	 * @param width
	 *            the width of the picture
	 * @return BufferedImage containing the picture
	 */
	public BufferedImage decryptImage(String picture, int height, int width) {
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		
		//TODO: implement this method
		//use the given BufferedImage for your code
		
		return image;
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
	 * return true if the given color is in the trie
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
	 * @return the trieCodeTree
	 */
	public Trie getTrieCodeTree() {
		return trieCodeTree;
	}

}
