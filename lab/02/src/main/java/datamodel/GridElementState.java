/**
 * 
 */
package datamodel;

/**
 * describes the GridElementState of a GridElement
 * 
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 * 
 */
public enum GridElementState {

	/**
	 * a free GridElement
	 */
	FREE(4),

	/**
	 * a GridElement which is part of an obstacle
	 */
	BLOCKED(Integer.MAX_VALUE),

	/**
	 * the starting point
	 */
	START(4),

	/**
	 * the end
	 */
	END(4);

	private int wayCost;

	/**
	 * constructor
	 * 
	 * @param wayCost the wayCost of this state
	 */
	GridElementState(int wayCost) {
		this.wayCost = wayCost;
	}

	/**
	 * return the state the user can choose via GUI
	 * 
	 * @return array which contains the states
	 */
	public static GridElementState[] getGUIValues() {
		return GridElementState.values();

	}

	public String toString() {
		switch (this) {
		case FREE:
			return "free";
		case BLOCKED:
			return "blocked";
		case START:
			return "Start";
		case END:
			return "End";
		default:
			return "free";
		}
	}

	/**
	 * @return the wayCost
	 */
	public int getWayCost() {
		return wayCost;
	}

}
