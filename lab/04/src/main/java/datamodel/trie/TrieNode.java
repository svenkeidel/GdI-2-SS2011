/**
 * 
 */
package datamodel.trie;

/**
 * class for a node in the trie<br>
 * save reference to its children and parent
 * 
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 * 
 */
public class TrieNode {

	private TrieNode[] nodeSlots = new TrieNode[16];
	private boolean[] leafSlots;;
	private boolean isLeaf = false;
	private int depth;

	/**
	 * builds a trieNode
	 * 
	 * @param isLeaf Leaf-status
	 * @param depth depth
	 */
	public TrieNode(int depth, boolean isLeaf) {
		
		this.setLeafStatus(isLeaf);
		
		this.depth = depth;
	}

	/**
	 * sets a node on a certain NodeSlot
	 * 
	 * @param index the slot you want to set
	 * @param node the node which will be set on a slot
	 * @return true if successful, false if a node is already set there
	 */
	public boolean setNodeAtSlot(int index, TrieNode node) {

		if (hasNodeAtSlot(index))
			return false;
		// or Exception "there is already a node"

		else {
			nodeSlots[index] = node;
			return true;
		}
	}


	/**
	 * removes a node at certain nodeSlot index
	 * 
	 * @param index index
	 */
	public void removeNodeAtSlot(int index) {
		nodeSlots[index] = null;
	}

	/**
	 * removes a leaf at certain LeafSlot index (sets them false)
	 * 
	 * @param index index
	 */
	public void removeLeafAtSlot(int index) {
		leafSlots[index] = false;
	}

	/**
	 * sets a LeafValue on a certain LeafSlot
	 * 
	 * @param index the leave you want to set true
	 * @return true if successful, false if already a leaf is set there,
	 * exception if leafstatus false
	 */
	public boolean setLeafAtSlot(int index) {
		if (isLeaf) {

			if (hasLeafAtSlot(index))
				return false;
			// or Exception "there is already a value set"

			else {
				leafSlots[index] = true;
				return true;
			}
		} else
			throw new UnsupportedOperationException(
					"Error: This node is not set as Leaf!");
	}

	/**
	 * returns a certain Node at a given slot
	 * 
	 * @param index slot number
	 * @return node or exception
	 */
	public TrieNode getNodeAtSlot(int index) {
		if (hasNodeAtSlot(index)) {
			return nodeSlots[index];
		}

		else
			throw new UnsupportedOperationException(
					"Error: There is no node at this slot!");
	}

	/**
	 * returns a certain leaf-value at a given slot
	 * 
	 * @param index slot number
	 * @return value or exception
	 */
	public boolean getLeafAtSlot(int index) {
		if (isLeaf) {

			if (hasLeafAtSlot(index))
				return leafSlots[index];

			else
				throw new UnsupportedOperationException(
						"Error: There is no value at this Leaf!");
		} else
			throw new UnsupportedOperationException(
					"Error: This node is no Leaf!");
	}

	/**
	 * checks if this node has free leaf-slot at
	 * 
	 * @param index index
	 * @return yes/no
	 */
	public boolean hasLeafAtSlot(int index) {
		if (leafSlots != null){
				if (leafSlots[index] == true)
					return true;
		
				else
					return false;
					}		
		else return false;
	}
		

	/**
	 * checks if this node has a free node-slot at
	 * 
	 * @param index index
	 * @return yes/no
	 */
	public boolean hasNodeAtSlot(int index) {

		if (nodeSlots[index] != null)
			return true;

		else
			return false;
	}

	/**
	 * sets whether a node is a leaf or not
	 * 
	 * @param status true = leaf, false = not
	 */
	public void setLeafStatus(boolean status) {
		
		if (!isLeaf && status){
			leafSlots = new boolean[16];
				for (int i = 0; i < 16; i++){
					leafSlots[i] = false;
				}
		}
		
		if (isLeaf && !status)
			leafSlots = null;
		
		this.isLeaf = status;
	}

	/**
	 * returns the LeafStatus
	 * 
	 * @return isLeaf
	 */
	public boolean getLeafStatus() {
		return this.isLeaf;
	}

	/**
	 * returns the node's depth
	 * 
	 * @return depth
	 */
	public int getDepth() {
		return this.depth;
	}

}
