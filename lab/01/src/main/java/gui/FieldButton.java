package gui;

import javax.swing.JButton;

public class FieldButton extends JButton {
	private int row, col;
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
