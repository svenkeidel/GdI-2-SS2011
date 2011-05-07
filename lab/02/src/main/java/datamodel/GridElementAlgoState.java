/**
 * 
 */
package datamodel;

/**
 * describes the AlgoState of a GridElement
 * 
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 *
 */
public enum GridElementAlgoState {

	NONE,
	/**
	 * a GridElement which was looked at during the calculation
	 */
	LOOKED_AT,
	/**
	 * a GridElement which is part of the chosen path
	 */
	PATH
}
