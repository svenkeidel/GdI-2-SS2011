package gui;

import javax.swing.JButton;

/**
 * A button which holds the information of its position on the field
 */
public class FieldButton extends JButton {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 1L;
	private int row, col;

	/**
	 * constructor
	 */
	public FieldButton(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
	/**
	 * @return get row
	 */
	public int getRow() {
		return this.row;
	}
	/**
	 * @return get column
	 */
	public int getCol() {
		return this.col;
	}
}
