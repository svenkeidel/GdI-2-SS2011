/**
 * 
 */
package datamodel.rucksack;

import java.security.InvalidParameterException;
import java.util.Vector;


/**
 * class which represents the rucksack
 * 
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 * 
 */
public class Rucksack {

	private int capacity;
	private Vector<RucksackObject> objects= new Vector<RucksackObject>();

	/**
	 * constructor
	 * 
	 * @param capacity
	 *            the max capacity of this rucksack
	 * 
	 */
	public Rucksack(int capacity) {
		if (capacity >= 0){
			this.capacity = capacity;
		} else {
			throw new InvalidParameterException("No legal capacity");
		}
		// TODO: implement
	}

	// TODO: implement necessary methods and attributes, f.e. getters, toString-method, equals...
	
	public int getCapacity(){
		return this.capacity;
	}
	
	public void setCapacity(int capacity){
		if (capacity >= 0){
			this.capacity = capacity;
		} else {
			throw new InvalidParameterException("No legal capacity");
		}
	}
	
	public int getWeightOfRucksack(){
		int weight = 0;
		
		for (int i = 0; i < objects.size(); i++){
			weight += objects.elementAt(i).getWeight();
		}
		
		return weight;
	}
	
	public int getValueOfRucksack(){
		int value = 0;
		
		for (int i = 0; i < objects.size(); i++){
			value += objects.elementAt(i).getValue();
		}
		
		return value;
	}
	
	/**
	 * 
	 * @param i
	 * @return object at position i
	 */
	public RucksackObject getObject(int i){
		if (i < objects.size()){
			return objects.elementAt(i);
		} else {
			throw new InvalidParameterException("No RucksackObject at this index");
		}
	}
	
	public void removeAll(){
		objects.clear();
	}
	
	/**
	 * tries to add this RucksackObject to the rucksack
	 * 
	 * @param o
	 *            the RucksackObject to insert
	 * @return true if the object fits into the rucksack; otherwise false
	 */
	public boolean insert(RucksackObject o) {
		if (objects.isEmpty()){
			if (o.getWeight() <= capacity){
				objects.add(o);
				return true;
			}
		} else {
			if (o.getWeight() + getWeightOfRucksack() <= capacity){
				objects.add(o);
				return true;
			}
		}
		return false;
	}

}
