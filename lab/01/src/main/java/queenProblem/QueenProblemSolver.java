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
	private int length;


	/**
	 * Initiate the solver with the playFieldSize and initiate the playField.
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

		if 	(solved){
				firePropertyChange("solved", null, solved);}
		else {
				firePropertyChange("unsolved", null, solved);}

		return solved;
	}


	/**
	 * start the solve method from the upper left corner.
	 *
	 * @return true if the playField could be solved; otherwise false
	 */
	public boolean solve() {
	
		boolean solved = false;

		// if the game can't be solved, reset the field
		try {
			solved = solveRow(0);

			if(!solved)
				playField.resetField();
		} catch (InterruptedException e) {/**Exception*/}

		return solved;
	}


	/**
	 * iterate over one single row and try to solve it. If it's not the
	 * last row try to solve the next row.
	 *
	 * @param row the row to solve
	 *
	 * @return true, if the row could be solved
	 */
	private boolean solveRow(int row) throws InterruptedException {

		if(isCancelled())
			throw new InterruptedException("Cancelled");

		// if there are steps possible
		if(row < length) {

			// change iteration direction to left or right
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


	/**
	 * try to set a queen, and solve next row. If not successful,
	 * drop the queen from this position
	 *
	 * @param row the row to set the queen
	 * @param col the column to set the queen
	 *
	 * @return true if the queen was at the right position and the field
	 * could be solved
	 */
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
	 * Set a queen at the specified position
	 *
	 * @param row the row where the queen shall be
	 * @param col the column where the queen shall be
	 */
	public boolean setQueen(int row, int col) {

		boolean wasSet = getPlayField()[row][col];
		if(playField.setQueen(row, col)) {
			if(!wasSet) {
				firePropertyChange("setQueen", null, new int[] {row, col});
				try {Thread.sleep(delay);} catch (Exception e) {/**Exception*/}
			}
			return true;
		} else {
			return false;
		}
	}


	/**
	 * Remove queen from specified position
	 *
	 * @param row the row from which the queen will be removed
	 * @param col the column from which the queen will be removed
	 */
	public boolean dropQueen(int row, int col) {

		if(playField.dropQueen(row, col)) {
			firePropertyChange("dropQueen", null, new int[] {row, col});

			try {Thread.sleep(delay);} catch (Exception e) {/**Exception*/}

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
