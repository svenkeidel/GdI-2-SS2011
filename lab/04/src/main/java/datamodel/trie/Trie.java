/**
 * 
 */
package datamodel.trie;

import datamodel.RGB;

/**
 * the whole trie<br>
 * provides communication functionality
 * 
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 * 
 */
public class Trie {

	private TrieNode root;
	private TrieNode currentNode;
	private int depth;

	
	/**
	 * The Trie-Constructor
	 */
	public Trie() {
		root = new TrieNode(1, true);
		currentNode = root;
		depth = 1;
	}

	/**
	 * moves to the root-node
	 * 
	 * @return true if successful, false if not
	 */
	public boolean moveToRoot() {

		if (root != null) {
			currentNode = root;
			return true;
		}

		else
			return false;
	}

	/**
	 * moves to a child.node
	 * 
	 * @return true if successful, false if not
	 */
	public boolean moveToChild(int index) {

		if (currentNode.getNodeAtSlot(index) != null) {
			currentNode = currentNode.getNodeAtSlot(index);
			return true;
		}

		else
			return false;
	}

	/**
	 * creates a child at a given slot
	 * 
	 * @param index slot number
	 * @param isLeaf is it a leaf or not?
	 * @return successful?
	 */
	public boolean createChildAt(int index, boolean isLeaf) {

		int childDepth = currentNode.getDepth() + 1;
		TrieNode childNode = new TrieNode(childDepth, isLeaf);
		depth = childDepth;

		return currentNode.setNodeAtSlot(index, childNode);
	}

	/**
	 * sets a Leaf-value at a given slot
	 * 
	 * @param index slot number
	 * @param value value
	 * @return successful?
	 */
	public boolean setLeafSlot(int index) {

		return currentNode.setLeafAtSlot(index);

	}

	/**
	 * calculates the missing depth of a color in Trie
	 * 
	 * @param color color to check
	 * @return missing depth, 0 if color completely in Trie
	 */
	public int getMissingDepth(RGB color) {
		moveToRoot();
		int missing = depth;

		for (int i = 1; i <= depth; i++) {
			int present_key = color.getTrieKeyForDepth(i);

			if (!currentNode.hasLeafAtSlot(present_key)) {
				if (currentNode.hasNodeAtSlot(present_key)) {
					missing--;
					this.moveToChild(present_key);
				} else
					return missing;
			} else
				missing--;
		}
		return 0;
	}

	/**
	 * get current node
	 * 
	 * @return current node
	 */
	public TrieNode getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(TrieNode node) {
		currentNode = node;
	}

	/**
	 * set the depth of this Trie manually
	 * 
	 * @param depth depth
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}

	/**
	 * returns Trie's depth
	 * 
	 * @return depth
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * get root
	 * 
	 * @return root node
	 */
	public TrieNode getRoot() {
		return root;
	}

	/**
	 * clones a Trie
	 */
	public Trie clone(){
		Trie output_trie = new Trie();
		moveToRoot();
		output_trie.setDepth(getDepth());
		
		TrieNode clone_root = output_trie.getRoot();
		clone_root.setLeafStatus(false);
		
		rec_clone(root, clone_root);
		
		return output_trie;
	}
	
	
	/**
	 * recursive helping function for clone
	 * @param node current node for recursion
	 * @param clone_node clone target node
	 */
	private void rec_clone(TrieNode node, TrieNode clone_node) {

		for (int i = 0; i < 16; i++){
		
		if (!node.getLeafStatus()) {
					if (node.hasNodeAtSlot(i)) {
						clone_node.setNodeAtSlot(i, new TrieNode(node.getNodeAtSlot(i).getDepth(), node.getNodeAtSlot(i).getLeafStatus()));
						rec_clone(node.getNodeAtSlot(i), clone_node.getNodeAtSlot(i));
					} 
				}
		else if (node.getLeafStatus())
				if (node.hasLeafAtSlot(i))
					clone_node.setLeafAtSlot(i);

		}
	}
	
	/**
	 * prepares a trie for extending
	 * --> resolves leafs into nodes
	 */
	public void prepareForExtending(){
		moveToRoot();
		if (this.getDepth() < 8)
		rec_prepareForExtending(root);
		
	}
	
	
	/**
	 * recursive helping function for prepareForeExtending function
	 * @param node current target node
	 */
	private void rec_prepareForExtending(TrieNode node){
		boolean seventh_depth = (node.getDepth() == 7);
		
		if (node.getLeafStatus()) {
				for (int i = 0; i < 16; i++){
							if (node.hasLeafAtSlot(i)) {
								node.setNodeAtSlot(i, new TrieNode(node.getDepth()+1, seventh_depth));
								}
							}
				node.setLeafStatus(false);
		}
		else{ 
				for (int k = 0; k < 16; k++){
							if (node.hasNodeAtSlot(k))
								rec_prepareForExtending(node.getNodeAtSlot(k));
				
				}
		}
	}
}
