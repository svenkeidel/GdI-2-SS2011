/**
 * 
 */
package logic.algorithm;

/**
 * The enum for all algorithms.
 * 
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 *
 */
public enum Algos {
	
	//TODO: add further algorithm
	Dummy("Dummy");
	
	private String name;
	
	/**
	 * Instantiate the algorithm with a name(used in GUI).
	 * 
	 * @param name the name of the algorithm
	 */
	Algos(String name){
		this.name = name;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
}
