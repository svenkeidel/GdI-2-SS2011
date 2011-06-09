/**
 * 
 */
package logic.trie;

import io.ImageReader;

import java.awt.image.BufferedImage;
import java.util.Iterator;

import datamodel.RGB;
import datamodel.trie.Trie;
import datamodel.trie.TrieNode;

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
//		int x = 0;
		Iterator<RGB> i = reader.iterator();
		while(i.hasNext()){
			RGB temp_col = new RGB(i.next().getRGBValue());
			addColor(temp_col);

//			System.err.println("col nr: "+x+": "+temp_col+"");
//			x++;
		}
	}

	/**
	 * encrypts the given picture (via an ImageReader) using the stored trie
	 * 
	 * @param reader the picture via an ImageReader
	 * @return the encrypted picture as a binary string
	 */
	public String encryptImage(ImageReader reader) {
		
		StringBuffer x = new StringBuffer();
		Iterator<RGB> i = reader.iterator();
		
		while(i.hasNext()){
			
			RGB temp_col = new RGB(i.next().getRGBValue());
			int missing = trieCodeTree.getMissingDepth(temp_col);
			int keys = trieCodeTree.getDepth() + 1 - missing;
			
			for	(int j = 0;j < keys; j++){
				int key = temp_col.getTrieKeyForDepth(j);
				x.append(Integer.toBinaryString(key));	
			}
			
			for	(int j = 0;j < missing; j++){
				x.append("1000");	
			}
		}
//		System.err.println(""+x.toString()+"");
		return x.toString();
		
	}

	
	/**
	 * adds the color to this trie<br>
	 * 
	 * @param color
	 *            the color to add
	 */
	public void addColor(RGB color) {
		trieCodeTree.moveToRoot();
		TrieNode root = trieCodeTree.getCurrentNode();
		
		rec_addColor(root, color);
	}
	
	
	/**
	 * recursive submethod to help inserting a color
	 * @param node current node
	 * @param color color
	 */
	public void rec_addColor(TrieNode node, RGB color){
		
		int key = color.getTrieKeyForDepth(node.getDepth());
		boolean isLeaf = (node.getDepth() == 6);
		if		(!node.getLeafStatus()){
						if		(!node.hasNodeAtSlot(key)){
								trieCodeTree.createChildAt(key, isLeaf);
								trieCodeTree.moveToChild(key);}
						else	trieCodeTree.moveToChild(key);
						
						rec_addColor(trieCodeTree.getCurrentNode(), color);
				}
		
		else{			if 		(!node.hasLeafAtSlot(key))	
								trieCodeTree.setLeafSlot(key, key);
		}
		trieCodeTree.moveToRoot();
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
		
		
		int size = picture.length();
		char[] arr = picture.toCharArray();
		int pic_Width = image.getWidth()-1;
		int x = 0; 
		int y = 0;
		int shift_index = 0;
		Integer[] rgb_vals = new Integer[4];
		
		for	(int i = 0; i < size; i = i+32){
			
			shift_index = 7;
			rgb_vals[0] = rgb_vals[1] = rgb_vals[2] = rgb_vals[3] = 0;
			
			for	(int j = 0; j < 32; j = j+4){
				
					char bright_char = arr[i+j];
					int bright_int = (bright_char - '0') << shift_index;
					rgb_vals[0] = rgb_vals[0] + bright_int;
//					System.err.println("BRIGHT-AS: "+bright_int+"");
					
					char red_char = arr[i+j+1];
					int red_int = (red_char - '0') << shift_index;
					rgb_vals[1] = rgb_vals[1] + red_int;
					
					char green_char = arr[i+j+2];
					int green_int = (green_char - '0') << shift_index;
					rgb_vals[2] = rgb_vals[2] + green_int;
					
					char blue_char = arr[i+j+3];
					int blue_int = (blue_char - '0') << shift_index;
					rgb_vals[3] = rgb_vals[3] + blue_int;
					
					shift_index--;
				}
			
			RGB rgb = new RGB(rgb_vals[1], rgb_vals[2], rgb_vals[3], rgb_vals[0]);
//			System.err.println("RGB: "+rgb+"");
			int rgb_val = rgb.getRGBValue();
//			System.err.println("RGBVALUE: "+rgb_val+"");
//			RGB test_rgb = new RGB(rgb_val);
//			System.err.println("RGB-AVAL: "+test_rgb+"");
			image.setRGB(x, y, rgb_val);
			
			if (x == pic_Width) {
				x = 0;
				y++;
			} else 
				x++;
			
			
		}
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
		trieCodeTree.moveToRoot();
		
		for (int i = 0; i < 7; i++){
			
			if		(!trieCodeTree.getCurrentNode().hasNodeAtSlot(color.getTrieKeyForDepth(i)))
						return false;
			
			trieCodeTree.moveToChild(color.getTrieKeyForDepth(i));
		}
		
		if 			(trieCodeTree.getCurrentNode().hasLeafAtSlot(color.getTrieKeyForDepth(7)))
						return true;
		else			return false;
	}

	/**
	 * @return the trieCodeTree
	 */
	public Trie getTrieCodeTree() {
		return trieCodeTree;
	}

}
