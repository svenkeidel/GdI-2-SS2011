package queenProblem;


/**
 * class which represents the playField
 * 
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 * 
 */
public class PlayField {

	private boolean[][] playField;
	private int playFieldSize;

	/**
	 * constructor
	 * 
	 * @param playField
	 *            the playField to set
	 * @param delay
	 */
	public PlayField(boolean[][] playField) {
		if (playField.length == playField[0].length) {
			// Set the playFieldSize
			this.playFieldSize = playField.length;
			// Initiate the playField
			this.playField = playField;
		} else {
			System.out.println("The Array must be quadratic!");
		}
	}
	
	
	//OPTIONAL TODO: Implement auxiliary methods if necessary

	
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
		return this.playFieldSize;
	}
}
