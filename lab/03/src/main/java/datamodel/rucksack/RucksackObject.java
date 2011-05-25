/**
 * 
 */
package datamodel.rucksack;

/**
 * class which represents a RucksackObject<br>
 * holds their value and weight
 * 
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 *
 */
public class RucksackObject {

	
	private int value;
	private int weight;

	/**
	 * constructor
	 * 
	 * @param value the value of this RucksackObject 
	 * @param weight the weight of this RucksackObject
	 */
	public RucksackObject(int value, int weight){
		this.value = value;
		this.weight = weight;
	}

	/**
	 * @return the value of the RucksackObject.
	 */
	public int getValue(){
		return this.value;
	}
	
	/**
	 * @return the weight of the RucksackObject.
	 */
	public int getWeight(){
		return this.weight;
	}


}
