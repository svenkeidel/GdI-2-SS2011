package queenProblem;


public class PlayField {

	private boolean[][] fieldOrig;
	private boolean[][] field;
	private int length;


	/**
	 * constructor
	 * 
	 * @param playField
	 *            the playField to set
	 * @param delay
	 */
	public PlayField(boolean[][] field) {
		if (field.length == field[0].length) {
			// Set the length of the field
			this.length = field.length;
			// Initiate the playField
			this.field = field;

			if(!isLegalPlayField())
				throw new IllegalArgumentException(
						"The given play field is illegal");

			this.fieldOrig = new boolean[length][length];
			for(int i=0; i<length; i++)
				System.arraycopy(field[i], 0, fieldOrig[i], 0, length);
		} else {
			System.out.println("The Array must be quadratic!");
		}
	}


	/**
	 * Additional function which tests if the position is in array
	 */
	private void isInArray(int row, int col) {
		if(row < 0 || row >= length || col < 0 || col >= length)
			throw new ArrayIndexOutOfBoundsException(
					"The requested position, to set the queen is out of range");
	}


	/**
	 * Set a queen at the specified position
	 */
	public boolean setQueen(int row, int col) {
		if(isLegalPosition(row, col)) {
			field[row][col] = true;
			return true;
		} else {
			return false;
		}
	}


	/**
	 * Remove a queen from specified position
	 */
	public boolean dropQueen(int row, int col) {

		isInArray(row, col);

		// if no queen was there
		if(field[row][col] == false)
			return false;

		// if the queen was preset
		if(fieldOrig[row][col] == true)
			return false;

		field[row][col] = false;
		return true;
	}


	/**
	 * test whether the queens movement paths don't cross other ones
	 */
	public boolean isLegalPosition(int row, int col) {

		isInArray(row, col);

		//  |
		//--Q--
		//  |
		for(int i=0; i<length; i++)
			if((i != row && field[i][col] == true) || (i != col && field[row][i] == true))
				return false;

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

		return true;
	}


	/**
	 * tests if the play field is correct and no queen beats another
	 */
	public boolean isLegalPlayField() {
		for(int i=0; i<length; i++) {
			for (int j=0; j<length; j++) {
				if(field[i][j] == true)
					if(!isLegalPosition(i, j)) return false;
			}
		}
		return true;
	}


	/**
	 * Use ONLY to get the field, do not set anything!
	 * 
	 * @return the playField
	 */
	protected boolean[][] getPlayField() {
		return field;
	}


	/**
	 * 
	 * @return the playFieldSize
	 */
	protected int getPlayFieldSize() {
		return this.length;
	}


	/**
	 * resets the actual field state to the original state
	 */
	public void resetField() {
		for(int i=0; i<length; i++)
			System.arraycopy(fieldOrig[i], 0, field[i], 0, length);
	}

}
