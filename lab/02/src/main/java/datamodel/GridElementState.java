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
	 * a swamp
	 */
	SWAMP(8),
	
	/**
	 * a mountain
	 */
	MOUNTAIN(10),
	
	HIGHWAY(1),

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
			return "Free";
		case BLOCKED:
			return "Blocked";
		case SWAMP:
			return "Swamp";
		case MOUNTAIN:
			return "Mountain";
		case START:
			return "Start";
		case HIGHWAY:
			return "Highway";
		case END:
			return "End";
		default:
			return "Free";
		}
	}

	/**
	 * @return the wayCost
	 */
	public int getWayCost() {
		return wayCost;
	}

}
