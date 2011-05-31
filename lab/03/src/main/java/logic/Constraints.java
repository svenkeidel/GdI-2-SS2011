/**
 * 
 */
package logic;

/**
 * used to define the constraints for the filter function
 * 
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 * 
 */
public class Constraints {

	private int minValue;
	private int maxValue;
	private int minObjects;
	private int maxObjects;
	

	/**
	 * constructor
	 */
	public Constraints(int minValue, int maxValue, int minObjects,
			int maxObjects) {
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.minObjects = minObjects;
		this.maxObjects = maxObjects;
	}
	
	/**
	 * get for constraints.
	 *
	 * @return 
	 * Index 0   =   minValue
	 * Index 1   =   maxValue
	 * Index 2   =   minObjects
	 * Index 3   =   maxObjects
	 */
	public int getAttribute(int index){
		
		switch (index){
		case 0:		return this.minValue;
		case 1:		return this.maxValue;
		case 2:		return this.minObjects;
		default:	return this.maxObjects;}
		
	}
}
