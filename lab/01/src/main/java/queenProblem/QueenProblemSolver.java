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
	private int length;
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
		this.length = getPlayFieldSize();
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
		logger.info("###########################################");
		logger.info("Try to solve queen problem on a "+length+"x"+length+" field");
		logger.info("###########################################");

		boolean solved = solveRow(0);

		if(solved) {
			logger.info("The problem could be solved");
			playField.printQueensInfo();
		} else {
			logger.info("The problem has no solution");
			playField.resetField();
		}
		return solved;
	}

	private boolean solveRow(int row) {

		logger.debug("----------------------------");
		logger.debug("solveRow("+row+")");
		playField.printQueensDebug();
		logger.debug("----------------------------");

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

		logger.debug("No solution found for row "+row+" with this constellation of queens");
		return false;
	}

	private boolean solveCell(int row, int col) {

		logger.debug("solveCell("+row+", "+col+")");

		// if the step was the solution return true
		if(playField.setQueen(row, col)) {
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
