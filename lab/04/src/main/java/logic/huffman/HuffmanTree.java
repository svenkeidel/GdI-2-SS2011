/**
 * 
 */
package logic.huffman;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import datamodel.RGB;

import datamodel.huffman.tree.AbstractTreeFactory;
import datamodel.huffman.tree.Tree;
import datamodel.huffman.tree.TreeNode;

import datamodel.huffman.tree.linked.LinkedTreeFactory;

import io.ImageReader;

/**
 * Implements the Huffman algorithm for an image.
 * 
 * @author Kevin Munk, Jakob Karolus
 * @version 1.0
 * 
 */
public class HuffmanTree {

	/**
	 * returns a HuffmanTree which is based on the given file (an image)<br>
	 * Less "weighted"(less probability) subtrees will be placed right.
	 * 
	 * @param file the file to load and generate the tree
	 * @return the Huffman tree for the file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Tree getHuffmanTree(String file) throws FileNotFoundException, IOException {
		HashMap<RGB, Integer> amountOfColorsMap = 
			getHashMapOfImage(new ImageReader(file));

		// Pack Map.Entrys into a sorted list
		PriorityQueue<TreeNode> amountOfColors = 
			new PriorityQueue<TreeNode>(1, new TreeNode.comparator());

		AbstractTreeFactory factory = new LinkedTreeFactory();

		for(Map.Entry<RGB, Integer> entry : amountOfColorsMap.entrySet())
			amountOfColors.add(factory.produceTreeNode(entry.getKey(), entry.getValue()));

		return factory.produceTree(amountOfColors);
	}


	/**
	 * Iterates over an Image and accumulates amount of color.
	 *
	 * @param reader an instance of the ImageReader to iterate over
	 * @return a hashmap of the color of the image and its amount
	 */
	private static HashMap<RGB, Integer> getHashMapOfImage(ImageReader reader) {
		HashMap<RGB, Integer> amountOfColors = new HashMap<RGB, Integer>();
		int value;
		for(RGB color : reader) {
			if(amountOfColors.containsKey(color)) {
				value = amountOfColors.get(color);
				amountOfColors.remove(color);
				amountOfColors.put(color, value + 1);
			} else {
				amountOfColors.put(color, 1);
			}
		}

		return amountOfColors;
	}
}
