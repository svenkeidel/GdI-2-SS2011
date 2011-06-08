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
	
	private TrieNode[] nodeSlots = new TrieNode[15];
	private Integer[] leafSlots = new Integer[15];
	private boolean isLeaf;
	private int depth;
	
	/**
	 * builds a trieNode
	 * 
	 * @param isLeaf Leaf-status
	 */
	public TrieNode(boolean isLeaf, int depth){
		this.isLeaf = isLeaf;
		this.depth = depth;
	}
	
	
	/**
	 * sets a node on a certain NodeSlot
	 * 
	 * @param index the slot you want to set
	 * @param node	the node which will be set on a slot
	 * @return true if successful, false if not
	 */
	public boolean setNodeSlot(int index, TrieNode node){
		
		if		(nodeSlots[index] != null)
					return false;
					//or Exception "there is already a node"
		
		else{		nodeSlots[index] = node;
					return true;
					}
		}
	
	
	/**
	 * sets a LeafValue on a certain LeafSlot
	 * 
	 * @param index the leave you want to set
	 * @param value	the value which will be set on a leaf
	 * @return true if successful, false if not
	 */
	public boolean setLeafSlot(int index, int value){
		
		if		(leafSlots[index] != null)
					return false;
					//or Exception "there is already a value set"

		else{		leafSlots[index] = value;
					return true;
					}
		}
	
	/**
	 * sets whether a node is a leaf or not
	 * @param status true = leaf, false = not
	 */
	public void setLeafStatus(boolean status){
		this.isLeaf = status;
	}
		
	
	/**
	 * returns the LeafStatus
	 * @return isLeaf
	 */
	public boolean getLeafStatus(){
		return this.isLeaf;
	}
	
	
	/**
	 * returns the node's depth
	 * @return depth
	 */
	public int getDepth(){
		return this.depth;
	}
	
	
}
