package queenProblem;

import java.beans.PropertyChangeListener;

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
	private static final Logger logger =
		Logger.getLogger(QueenProblemSolver.class);


	private PlayField playField;
	private int delay;
	private int length;

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
		this.length = getPlayFieldSize();
		this.delay = delay;
	}

	@Override
	public Boolean doInBackground() throws Exception {
		logger.info("i'll work in Background");

		PropertyChangeListener[] listeners = getPropertyChangeSupport().getPropertyChangeListeners();
		for(int i=0; i<listeners.length; i++)
			logger.debug(listeners[i].toString());

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

		boolean solved = solveRow(0);

		if(!solved)
			playField.resetField();
		return solved;
	}


	private boolean solveRow(int row) {

		// if there are steps possible
		if(row < length) {

			if(row % 2 == 0) {
				for(int col=0; col<length; col++)
					if(solveCell(row, col)) return true;
			} else {
				for(int col=length - 1; col>=0; col--)
					if(solveCell(row, col)) return true;
			}
		}

		return false;
	}


	private boolean solveCell(int row, int col) {

		// if the step was the solution return true
		if(setQueen(row, col)) {
			if(row == length - 1) return true;

			// else try to solve next row
			else if(solveRow(row + 1))
				return true;

			// if it wasn't the right way make backtracking
			else {
				dropQueen(row, col);
			}
		}

		return false;
	}


	/**
	 * Sets a queen at the specified position
	 *
	 * @param row the row in wich the queen is placed
	 * @param col the column in wich the queen is placed
	 */
	public boolean setQueen(int row, int col) {

		if(playField.setQueen(row, col)) {
			firePropertyChange("setQueen", null, new int[] {row, col});

			try {Thread.sleep(delay);} catch (Exception e){logger.error(e.getMessage());}

			return true;
		} else {
			return false;
		}
	}


	/**
	 * Drops a queen from specified position
	 *
	 * @param row the row in where the queen is droped
	 * @param col the column in wich the queen is droped
	 */
	public boolean dropQueen(int row, int col) {

		if(playField.dropQueen(row, col)) {
			firePropertyChange("dropQueen", null, new int[] {row, col});

			try {Thread.sleep(delay);} catch (Exception e){logger.error(e.getMessage());}

			return true;
		} else {
			return false;
		}
	}


	/**
	 * @return the playField
	 */
	public boolean[][] getPlayField() {
		return this.playField.getPlayField();
	}


	/**
	 * @return the playFieldSize
	 */
	public int getPlayFieldSize() {
		return this.playField.getPlayFieldSize();
	}
}
