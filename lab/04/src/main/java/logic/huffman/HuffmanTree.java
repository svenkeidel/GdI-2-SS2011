/**
 * 
 */
package logic.huffman;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import datamodel.RGB;

import datamodel.huffman.tree.Tree;

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
	 * Comparator to compare two Map.Entrys
	 */
	public static class ColorComparator implements Comparator<Map.Entry<RGB,Integer>> {

		/**
		 * Compare the amount of a color with another
		 */
		public int compare(Map.Entry<RGB,Integer> o1, Map.Entry<RGB,Integer> o2) {
			return o1.getValue() - o2.getValue();
		}

		/**
		 * Two Map.Entrys equals if the have equal colors
		 */
		public boolean equals(Object o) {
			if(this == o)
				return true;
			else if(o == null)
				return false;
			else if(!(o instanceof Map.Entry<?,?>))
				return false;
			else {

				Map.Entry<?,?> other = (Map.Entry<?,?>) o;

				if(!this.equals(other.getKey()))
					return false;
				else
					return true;
			}
		}
	}


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
		PriorityQueue<Map.Entry<RGB,Integer>> amountOfColors = 
			new PriorityQueue<Map.Entry<RGB,Integer>>(1, 
				new ColorComparator());
		amountOfColors.addAll(amountOfColorsMap.entrySet());

		return new LinkedTreeFactory().produceTree(amountOfColors);
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
