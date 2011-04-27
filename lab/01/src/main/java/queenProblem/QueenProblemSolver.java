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
	public QueenProblemSolver(boolean[][] field, int delay) {
		this.playField = new PlayField(field);
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
		boolean [][] field = getPlayField();
		logger.info("#########################################");
		logger.info("solve "+length+"x"+length+" queen problem");
		logger.info("#########################################");
		logger.info("queen preset:");
		for(int i=0; i<length; i++)
			for(int j=0; j<length; j++)
				if(field[i][j])
					logger.info("["+i+","+j+"]");


		boolean solved = false;

		try {
			solved = solveRow(0);

			if(!solved)
				playField.resetField();
		} catch (InterruptedException e) {
			logger.info(e.getMessage());
		}

		return solved;
	}


	private boolean solveRow(int row) throws InterruptedException {

		if(isCancelled())
			throw new InterruptedException("Cancelled");

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


	private boolean solveCell(int row, int col) throws InterruptedException {

		if(isCancelled())
			throw new InterruptedException("Cancelled");

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

		boolean wasSet = getPlayField()[row][col];
		if(playField.setQueen(row, col)) {
			if(!wasSet) {
				firePropertyChange("setQueen", null, new int[] {row, col});
				try {Thread.sleep(delay);} catch (Exception e) {logger.error(e.getMessage());}
			}
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

			try {Thread.sleep(delay);} catch (Exception e) {logger.error(e.getMessage());}

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

	/**
	 * if a queen could be placed here
	 */
	public boolean isLegalPosition(int row, int col) {
		return this.playField.isLegalPosition(row, col);
	}
}
