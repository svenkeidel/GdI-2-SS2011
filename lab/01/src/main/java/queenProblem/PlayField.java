package queenProblem;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import org.apache.log4j.Logger;

/**
 * class which represents the playField
 * 
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 * 
 */
public class PlayField implements PropertyChangeListener {

	private boolean[][] playField;
	private int length;
	private static Logger logger = Logger.getRootLogger();


	/**
	 * constructor
	 * 
	 * @param playField
	 *            the playField to set
	 * @param delay
	 */
	public PlayField(boolean[][] playField) {
		if (playField.length == playField[0].length) {
			// Set the length of the field
			this.length = playField.length;
			// Initiate the playField
			this.playField = playField;
		} else {
			System.out.println("The Array must be quadratic!");
		}
	}
	

	/**
	 * implements the PropertyChangeListener method propertyChange()
	 *
	 * The method dispaches the PropertyChangeEvent's from
	 * QueenProblemSolver and sets or drops a Queen from specified
	 * position.
	 *
	 * @param evt the event causing the property change
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("setQueen")) {
			if(evt.getNewValue() instanceof int[]) {
				int[] position = (int[]) evt.getNewValue();
				int row = position[0];
				int col = position[1];
				setQueen(row, col);
			} else {
				throw new IllegalArgumentException("Called propertyChange() with wrong type");
			}

		} else if(evt.getPropertyName().equals("dropQueen")) {

			if(evt.getNewValue() instanceof int[]) {
				int[] position = (int[]) evt.getNewValue();
				int row = position[0];
				int col = position[1];
				dropQueen(row, col);
			} else {
				throw new IllegalArgumentException("Called propertyChange() with wrong type");
			}
		}
	}


	/**
	 * Sets a queen to specified position
	 *
	 * @param row the row in wich the queen is placed
	 * @param col the column in wich the queen is placed
	 */
	private void setQueen(int row, int col){
		if(row < 0 || row >= length || col < 0 || col >= length)
			throw new ArrayIndexOutOfBoundsException(
					"The requested position, to set the queen is out of range");
		logger.debug("setQueen("+row+", "+col+") accepted");
		playField[row][col] = true;
	}


	/**
	 * drops a queen from specified position
	 *
	 * @param row the row in where the queen is droped
	 * @param col the column in wich the queen is droped
	 */
	private void dropQueen(int row, int col){
		if(row < 0 || row >= length || col < 0 || col >= length)
			throw new ArrayIndexOutOfBoundsException(
					"The requested position, to set the queen is out of range");
		logger.debug("dropQueen("+row+", "+col+") accepted");
		playField[row][col] = false;
	}

	
	/**
	 * Use ONLY to get the field, do not set anything!
	 * 
	 * @return the playField
	 */
	protected boolean[][] getPlayField() {
		return playField;
	}


	/**
	 * 
	 * @return the playFieldSize
	 */
	protected int getPlayFieldSize() {
		return this.length;
	}
}
