/**
 * 
 */
package datamodel;

/**
 * Encapsulates the update events thrown by the Grid.
 * 
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 * 
 */
public class UpdateEvent {

	/**
	 * GRID_READY Update Event<br>
	 * xPosition and yPosition are the measures of the grid. The state is not relevant.
	 */
	public static final String GRID_READY = "GRID_READY";
	
	/**
	 * ELEMENT_CHANGED Update Event<br>
	 * xPosition and yPosition are the coordinates of the element that changed.
	 * The state shows in what state the element has changed.
	 */
	public static final String ELEMENT_CHANGED = "ELEMENT_CHANGED";
	
	/**
	 * FOUND_SOLUTION Update Event<br>
	 * All other parameters are 0/null.
	 */
	public static final String FOUND_SOLUTION = "FOUND_SOLUTION";
	
	/**
	 * NO_SOLUTION Update Event<br>
	 * All other parameters are 0/null.
	 */
	public static final String NO_SOLUTION = "NO_SOLUTION";
	
	/**
	 * FALSE_PRECONDITIONS Update event<br>
	 * The preconditions to solve the field where not meet.
	 * p.e. no start set, no end set. All other parameters are 0/null.
	 */
	public static final String FALSE_PRECONDITIONS = "FALSE_PRECONDITIONS";

	private String occuredEvent;
	private int xPosition;
	private int yPosition;
	private GridElementState changedState;
	private GridElementAlgoState changedAlgoState;

	/**
	 * Default constructor. Occurred Event should be one of the static strings
	 * in this class.
	 * 
	 * @param occuredEvent
	 *            the event that occurred
	 * @param xPosition
	 *            the x position/width
	 * @param yPosition
	 *            the y position/height
	 * @param changedState
	 *            the GridElementState
	 * @param changedAlgoState
	 *            the GridElementAlgoState
	 */
	public UpdateEvent(String occuredEvent, int xPosition, int yPosition,
			GridElementState changedState, GridElementAlgoState changedAlgoState) {
		this.occuredEvent = occuredEvent;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.changedState = changedState;
		this.changedAlgoState = changedAlgoState;
	}

	/**
	 * @return the occuredEvent
	 */
	public String getOccuredEvent() {
		return occuredEvent;
	}

	/**
	 * @return the xPosition
	 */
	public int getxPosition() {
		return xPosition;
	}

	/**
	 * @return the yPosition
	 */
	public int getyPosition() {
		return yPosition;
	}

	/**
	 * @return the changedState
	 */
	public GridElementState getChangedState() {
		return changedState;
	}

	/**
	 * @return the changedAlgoState
	 */
	public GridElementAlgoState getChangedAlgoState() {
		return changedAlgoState;
	}
}
