package queenProblem;

import javax.swing.SwingWorker;

import org.apache.log4j.Logger;

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
	private static Logger logger = Logger.getRootLogger();

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
		addPropertyChangeListener(this.playField);
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
	public boolean setQueen(int row, int col) {
		logger.debug("Request setQueen("+row+", "+col+")");

		boolean[][] field = getPlayField();
		int length = field.length;

		if(row < 0 || row >= length || col < 0 || col >= length)
			throw new ArrayIndexOutOfBoundsException("The requested position, to set the queen is out of range");

		//  |
		//--Q--
		//  |
		for(int i=0; i<length; i++) {
			if(field[i][col] == true || field[row][i] == true)
				return false;
		}

		//
		//  Q
		//   \
		for(int i=1; row+i < length && col+i < length; i++)
			if(field[row+i][col+i] == true)
				return false;

		//
		//  Q
		// /
		for(int i=1; row+i < length && col-i >= 0; i++)
			if(field[row+i][col-i] == true)
				return false;

		// \
		//  Q
		//
		for(int i=1; row-i >= 0 && col-i >= 0; i++)
			if(field[row-i][col-i] == true)
				return false;

		//   /
		//  Q
		//
		for(int i=1; row-i >= 0 && col+i < length; i++)
			if(field[row-i][col+i] == true)
				return false;

		firePropertyChange("setQueen", null, new int[] {row, col});
		return true;
	}

	/**
	 * @return the playFieldSize
	 */
	public int getPlayFieldSize() {
		return this.playField.getPlayFieldSize();
	}
}
