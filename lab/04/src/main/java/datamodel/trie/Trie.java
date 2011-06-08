/**
 * 
 */
package datamodel.trie;

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
	
	
	/**
	 * The Trie-Constructor
	 */
	public Trie(){
		root = new TrieNode(false, 0);
		currentNode = root;
	}
	
	
	/**
	 * moves to the root-node
	 * @return true if successful, false if not
	 */
	public boolean moveToRoot(){
		
		if		(root != null){
					currentNode = root;
					return true;}
		
		else		return false;
	}
	
	
	/**
	 * moves to a child.node
	 * @return true if successful, false if not
	 */
	public boolean moveToChild(int index){
		
		if		(currentNode.getNodeAtSlot(index) != null){
					currentNode = currentNode.getNodeAtSlot(index);
					return true;}
		
		else		return false;
	}

	
	/**
	 * creates a child at a given slot
	 * @param index slot number
	 * @param isLeaf is it a leaf or not?
	 * @return successful?
	 */
	public boolean createChildAt(int index, boolean isLeaf){
		
		int childDepth = currentNode.getDepth() + 1;
		TrieNode childNode = new TrieNode(isLeaf, childDepth);
		
		return currentNode.setNodeAtSlot(index, childNode);
	}
	
	
	/**
	 * sets a Leaf-value at a given slot
	 * @param index slot number
	 * @param value value
	 * @return successful?
	 */
	public boolean setLeafSlot(int index, Integer value){

		return currentNode.setLeafValueAtSlot(index, value);
		
	}
	
	
	/**
	 * get current node
	 * @return current node
	 */
	public TrieNode getCurrentNode(){
		return currentNode;
	}
	
	
	/**
	 * get root
	 * @return root node
	 */
	public TrieNode getRoot(){
		return root;
	}
}
