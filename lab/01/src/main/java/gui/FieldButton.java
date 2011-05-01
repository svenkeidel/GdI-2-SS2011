package gui;

import javax.swing.JButton;

/**
 * A button wich holds the information of its postion on the field
 */
public class FieldButton extends JButton {

	private int row, col;

	/**
	 * constructor
	 */
	public FieldButton(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return this.row;
	}

	public int getCol() {
		return this.col;
	}
}
