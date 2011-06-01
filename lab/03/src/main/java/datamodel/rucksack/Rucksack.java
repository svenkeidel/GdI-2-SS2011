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
			this.objects.clear();
		} else {
			throw new InvalidParameterException("No legal capacity");
		}
	}

	/**
	 * @return the capacity of the Rucksack.
	 */
	public int getCapacity(){
		return this.capacity;
	}


	/**
	 * set the capacity of the Rucksack new, if it legal.
	 * @param capacity
	 * 		the new capacity.
	 */
	public void setCapacity(int capacity){
		if (capacity >= 0 && capacity >= getWeight()){
			this.capacity = capacity;
		} else {
			throw new InvalidParameterException("No legal capacity");
		}
	}


	/**
	 * @return the number of elements in the Rucksack.
	 */
	public int getSize(){
		return objects.size();
	}
	
	/** 
	 * @return the complete weight of the Rucksack.
	 */
	public int getWeight(){
		int weight = 0;
		
		for (int i = 0; i < objects.size(); i++){
			weight += objects.elementAt(i).getWeight();
		}
		
		return weight;
	}


	/**
	 * @return the complete value of all RucksackObjects.
	 */
	public int getValue(){
		int value = 0;
		
		for (int i = 0; i < objects.size(); i++){
			value += objects.elementAt(i).getValue();
		}
		
		return value;
	}


	public Vector<RucksackObject> getElements(){
		return objects;
	}
	
	
	/**
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


	/**
	 * Clones a Rucksack and inserts the elements of the old rucksack.
	 */
	@Override
	public Rucksack clone(){
		Rucksack r = new Rucksack(this.capacity);
		for (int i = 0; i < objects.size(); i++){
			r.insert(objects.elementAt(i));
		}
		return r;
	}


	/**
	 * removes a element at a specific position i
	 * @param i
	 */
	public void removeAt(int i){
		if (i < objects.size()){
			objects.remove(i);
		} else {
			throw new InvalidParameterException("No RucksackObject at this index");
		}
	}


	/**
	 * remove all RucksackObjects
	 */
	public void removeAll(){
		objects.clear();
	}


	/**
	 * Two rucksacks are equal, if the have equal elements at the same
	 * positions.
	 */
	@Override
	public boolean equals(Object o){
		if (this == null || o == null){
			throw new InvalidParameterException("One or booth bags are null");
		}
		if(o instanceof Rucksack) {
			Rucksack r = (Rucksack) o;
			if (r.getCapacity() != getCapacity()
					|| r.objects.size() != objects.size()
					|| r.getValue() != getValue()
					|| r.getWeight() != getWeight()){
				return false;
			} 
			// capacity, size of objectsvector, value and weight is equal
			// so check if the objects are the same
			for (int i = 0; i < r.objects.size(); i++){
				if (!(objects.contains(r.getObject(i)))){
					return false;
				}
				if (!(r.objects.contains(getObject(i)))){
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}


	/**
	 * tries to add this RucksackObject to the rucksack.
	 * 
	 * @param o
	 *            the RucksackObject to insert
	 * @return true if the object fits into the rucksack; otherwise false
	 */
	public boolean insert(RucksackObject o) {
		if (this != null && o != null){
			if (objects.isEmpty() && o.getWeight() <= capacity){
				objects.add(o);
				return true;
			} else {
				if (o.getWeight() + getWeight() <= capacity){
					objects.add(o);
					return true;
				}
			}
		} 
		return false;
	}
	
	/**
	 * get the number of objects in the rucksack.
	 */
	public int getAmountOfObjects(){
		return objects.size();
	}

}
