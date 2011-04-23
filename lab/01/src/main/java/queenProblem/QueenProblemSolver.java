package queenProblem;

import javax.swing.SwingWorker;

/**
 * The solver for the QueenProblem.
 * 
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 * 
 */
public class QueenProblemSolver extends SwingWorker<Boolean, int[]> {

	private PlayField playField;
	private int delay;

	/**
	 * Initiates the Solver with the playFieldSize and initiates the playField.
	 * 
	 * @param playField
	 *            the playField to work on
	 * @param delay
	 *            the delay between an solver move (should not be under 100 ms)
	 */
	public QueenProblemSolver(boolean[][] playField, int delay) {
		this.playField = new PlayField(playField);
		this.delay = delay;
	}

	@Override
	public Boolean doInBackground() throws Exception {
		boolean solved = solve();

		firePropertyChange("solved", null, solved);

		return solved;
	}

	/**
	 * starts the solve method from the upper left corner.
	 * 
	 * @return true if the playField could be solved; otherwise false
	 */
	public boolean solve() {
		
		//TODO: Implement this method
		
		throw new UnsupportedOperationException("Implement me!");
	}
	
	
	/*
	 * OPTIONAL TODO: Implement auxiliary methods if necessary
	 */
	

	/**
	 * @return the playField
	 */
	public boolean[][] getPlayField() {
		return this.playField.getPlayField();
	}


	/**
	 * Sets a queen at the specified position
	 * 
	 * @param row
	 * @param column
	 */
	public void setQueen(int row, int column) {
		//TODO: Implement this method
		
		//OPTIONAL TODO: You may use this event for your implementation
		//firePropertyChange("setQueen", null, new int[] { row, column });
	}

	/**
	 * @return the playFieldSize
	 */
	public int getPlayFieldSize() {
		return this.playField.getPlayFieldSize();
	}
}
