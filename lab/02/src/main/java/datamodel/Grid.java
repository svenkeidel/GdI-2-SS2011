/**
 *
 */
package datamodel;

import java.security.InvalidParameterException;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

/**
 * Holds the grid data structure<br>
 * notifies observer if necessary
 *
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 *
 */
public class Grid extends Observable {

	private GridElement[][] grid;
	private int rows;
	private int columns;
	private static boolean auto_status;

	/**
	 * Initiates the grid with the specified height and width.
	 *
	 * @param rows
	 *            the height of the grid
	 * @param columns
	 *            the width of the grid
	 * @param observer
	 */
	public Grid(int rows, int columns, Observer observer) {
		GridElement.resetStartEnd();

		if (rows > 0 && columns > 0) {
			this.rows = rows;
			this.columns = columns;
		} else {
			throw new InvalidParameterException(
					"The row and column parameter must be greater than 0");
		}

		if (observer != null)
			this.addObserver(observer);

		initGridElements(observer);
	}

	/**
	 * Initiates the grid with the given grid.
	 *
	 * @param grid
	 *            the specified grid
	 * @param observer
	 * @throws Exception 
	 */
	public Grid(Grid grid, Observer observer){
		this(grid.getRows(), grid.getColumns(), observer);

		// preset the state
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				this.getElementAt(i, j).setState(
						grid.getElementAt(i, j).getState());
			}
		}
	}

	/**
	 * Makes a new internal array and initiates all fields with
	 * GridElementState.FREE.<br>
	 * <br>
	 * Notifies all observers that grid is ready to use.
	 *
	 * @param observer
	 *            the observer for every element to set
	 */
	private void initGridElements(Observer observer) {
		this.grid = new GridElement[rows][columns];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				this.grid[i][j] = new GridElement(i, j);
				if (observer != null)
					this.grid[i][j].addObserver(observer);
			}
		}

		this.setChanged();
		this.notifyObservers(new UpdateEvent(UpdateEvent.GRID_READY, columns,
				rows, null, null));
	}

	/**
	 * Searches the element that has the 'start' state.
	 *
	 * @return the element that has the state 'start' or null if none of the
	 *         elements has this state.
	 */
	public GridElement getStartElement() {
		return GridElement.currentStart;
	}

	/**
	 * Search the element that has the 'end' state.
	 *
	 * @return the element that has the state 'end' or null if none of the
	 *         elements has this state.
	 */
	public GridElement getEndElement() {
		return GridElement.currentEnd;
	}

	/**
	 * Returns the element at the specified position.
	 *
	 * @param row
	 *            the row of the element
	 * @param column
	 *            the column of the element
	 * @return the element at the specified coordinates or null if the
	 *         coordinates are out of bounds.
	 */
	public GridElement getElementAt(int row, int column) {
		if ((row >= 0 && row < grid.length) && (column >= 0 && column < grid[0].length)){
		return grid[row][column];
		} else return null;
	}

	/**
	 * @return a list of knodes of the grid
	 */
	public Vector<GridElement> getKnodes() {
		Vector<GridElement> knodes = new Vector<GridElement>();
		for(int i=0; i<grid.length; i++)
			for(int j=0; j<grid[0].length; j++)
				knodes.add(grid[i][j]);
		return knodes;
	}

	/**
	 * delivers the neighbors of the given GridElement, depending on the second
	 * parameter
	 *
	 * @param element
	 *            the given element
	 * @param diagonal
	 *            if true returns horizontal, vertical and diagonal neighbors;<br>
	 *            if false returns only horizontal and vertical
	 * @return the neighbors of this GridElement
	 */
	public Neighbors getNeighborsFrom(GridElement element, boolean diagonal) {
		return new Neighbors(diagonal, element);
	}

	/**
	 * delivers the neighbors of the given GridElement, depending on the second
	 * parameter
	 *
	 * @param element
	 *            the given element
	 * @return the neighbors of this GridElement
	 */
	public Neighbors getNeighborsFrom(GridElement element) {
		return this.getNeighborsFrom(element, false);
	}

	/**
	 * @return the row count of the grid
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @return the column count of the grid
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * Resets the GridElemetAlgoState of every element of this grid.
	 */
	public void resetGridsAlgoState() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				grid[i][j].resetElement();
			}
		}
	}

	/**
	 * The fieldstates in string
	 * s for start element
	 * e for end element
	 * # for blocked element
	 * _ for free element
	 * ~ for swamp element
	 * ^ for mountain element
	 * 
	 * @return the Sting 
	 */
	public String toStringState(){
		StringBuffer sb = new StringBuffer();
		
		sb.append("*");
		for (int j = 0; j < grid[0].length; j++)
			sb.append("-");
		sb.append("*\n");

		for (int i = 0; i < grid.length; i++){
			sb.append("|");
			for (int j = 0; j < grid[0].length; j++){
				GridElementState state = grid[i][j].getState();
				switch(state) {
					case END:
						sb.append("e");
						break;
					case HIGHWAY:
						sb.append("h");
						break;
					case START:
						sb.append("s");
						break;
					case FREE:
						sb.append(" ");
						break;
					case BLOCKED:
						sb.append("#");
						break;
					case SWAMP:
						sb.append("~");
						break;
					case MOUNTAIN:
						sb.append("^");
						break;
					default:
				}
			}
			sb.append("|\n");
		}

		sb.append("*");
		for (int j = 0; j < grid[0].length; j++)
			sb.append("-");
		sb.append("*\n");

		return sb.toString();
	}
	
	/**
	 * The fieldalgostates in string
	 * L for looked_at element
	 * P for path element
	 * N for none element 
	 * 
	 * @return the String
	 */
	public String toStringAlgo(){
		StringBuffer sb = new StringBuffer();

		sb.append("*");
		for (int j = 0; j < grid[0].length; j++)
			sb.append("-");
		sb.append("*\n");

		for (int i = 0; i < grid.length; i++){
			sb.append("|");
			for (int j = 0; j < grid[0].length; j++){
				GridElementAlgoState state = grid[i][j].getAlgoState();
				switch(state) {
					case LOOKED_AT:
						sb.append("'");
						break;
					case NONE:
						sb.append(" ");
						break;
					case PATH:
						sb.append("=");
						break;
					default:
				}
			}
			sb.append("|\n");
		}

		sb.append("*");
		for (int j = 0; j < grid[0].length; j++)
			sb.append("-");
		sb.append("*\n");

		return sb.toString();
	}
	
	/**
	 * toStringState() and toStringAlgoState() in one method
	 * 
	 * @return the string
	 */
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("Terrain of the field:\n");
		sb.append(toStringState());
		sb.append("\n");
		sb.append("Algorithm state of the field:\n");
		sb.append(toStringAlgo());
		return sb.toString();
	}
	
	public int getLowestWeight(){
		int lowest = Integer.MAX_VALUE;
		
		for (int i = 0; i < grid.length; i++){
			for (int j = 0; j < grid[0].length; j++){
				GridElementState state =  grid[i][j].getState();
				
				if (state != GridElementState.START && state != GridElementState.END){
		
				if (grid[i][j].getWeight() < lowest){
					lowest = grid[i][j].getWeight();
					}
				}
			}
		}
		return lowest;
	}
	
	public static void setAutoStatus(boolean astatus){
		auto_status = astatus;
	}
	
	public static boolean getAutoStatus(){
		return auto_status;
	}
	
	/**
	 * class, which encapsulates the neighbors of a GridElement
	 *
	 * @author Jakob Karolus, Kevin Munk
	 * @version 1.0
	 *
	 */
	public class Neighbors {

		/**
		 * saves the neighbors and wayCosts. The first Element in neighbors corresponds to the first
		 * Element in wayCosts, etc.
		 */
		private Vector<GridElement> neighbors = new Vector<GridElement>();
		private Vector<Integer> wayCosts = new Vector<Integer>();

		/**
		 *
		 * @param diagonal
		 *            if the neighbors should include the diagonal ones
		 * @param element
		 *            the element whose neighbors should be acquired
		 */
		private Neighbors(boolean diagonal, GridElement element) {
			if (diagonal) {
				this.getDiagonalNeighborsOf(element);
			} else {
				this.getNeighborsOf(element);
			}
		}

		/**
		 * Gets the horizontal and vertical neighbors of the element.
		 *
		 * @param element
		 * @return the neighbors and the way costs to the element
		 */
		private void getNeighborsOf(GridElement element) {
			int row = element.getRow();
			int col = element.getColumn();
			neighbors.clear();
			wayCosts.clear();
			GridElement neighbor = null;
			
			if ((neighbor = getElementAt(row-1,col)) != null && 
				neighbor.getState() != GridElementState.BLOCKED){
				// a neighbor-element above element exist
				neighbors.addElement(neighbor);
				wayCosts.addElement(neighbor.getWeight());
				}
			
			if ((neighbor = getElementAt(row,col-1)) != null && 
					neighbor.getState() != GridElementState.BLOCKED){
					// a neighbor-element left element exist
					neighbors.addElement(neighbor);
					wayCosts.addElement(neighbor.getWeight());
					}
			
			if ((neighbor = getElementAt(row,col+1)) != null && 
					neighbor.getState() != GridElementState.BLOCKED){
					// a neighbor-element right element exist
					neighbors.addElement(neighbor);
					wayCosts.addElement(neighbor.getWeight());
					}
			if ((neighbor = getElementAt(row+1,col)) != null && 
					neighbor.getState() != GridElementState.BLOCKED){
					// a neighbor-element below element exist
					neighbors.addElement(neighbor);
					wayCosts.addElement(neighbor.getWeight());
					}			
		}

		/**
		 * Gets all neighbors of the element. Inclusive the diagonal laying
		 * fields.
		 *
		 * @param currentElement
		 * @return the neighbors(incl. diagonal neighbors) and the way costs to
		 *         the element. The diagonal elements have 1,5x greater
		 *         way costs.
		 */
		private void getDiagonalNeighborsOf(GridElement element) {
			int row = element.getRow();
			int col = element.getColumn();
			GridElement neighbor = null;
			
			getNeighborsOf(element);
			
			//x
			// E
			//
			if ((neighbor = getElementAt(row-1,col-1)) != null && 
					neighbor.getState() != GridElementState.BLOCKED){
					// a neighbor-element left above element exist
					neighbors.addElement(neighbor);
					wayCosts.addElement((int)(neighbor.getWeight()*1.5));
					}			
			
			//
			// E
			//x
			if ((neighbor = getElementAt(row+1,col-1)) != null && 
					neighbor.getState() != GridElementState.BLOCKED){
					// a neighbor-element left below element exist
					neighbors.addElement(neighbor);
					wayCosts.addElement((int)(neighbor.getWeight()*1.5));
					}
			
			//
			// E
			//  x
			if ((neighbor = getElementAt(row+1,col+1)) != null && 
					neighbor.getState() != GridElementState.BLOCKED){
					// a neighbor-element right below element exist
					neighbors.addElement(neighbor);
					wayCosts.addElement((int)(neighbor.getWeight()*1.5));
					}
			
			//  x
			// E
			//
			if ((neighbor = getElementAt(row-1,col+1)) != null && 
					neighbor.getState() != GridElementState.BLOCKED){
					// a neighbor-element right above element exist
					neighbors.addElement(neighbor);
					wayCosts.addElement((int)(neighbor.getWeight()*1.5));
					}

		}

		/**
		 * @return the neighbors
		 */
		public Vector<GridElement> getNeighbors() {
			return neighbors;
		}

		/**
		 * @return the wayCosts
		 */
		public Vector<Integer> getWayCosts() {
			return wayCosts;
		}

	}
}
