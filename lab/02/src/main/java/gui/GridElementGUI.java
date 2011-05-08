/**
 *
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;

import datamodel.GridElement;

/**
 * display one single GridElement on the GrindPanel<br>
 * provides GUI function to update itself
 *
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 *
 */
public class GridElementGUI extends JButton {

	/**
	 * the underlying GridElement
	 */
	private GridElement gridElement;

	/**
	 *
	 */
	private static final long serialVersionUID = 5438917479894462711L;

	/**
	 * constructor
	 *
	 * @param gridElement
	 *            the underlying gridElement
	 */
	protected GridElementGUI(GridElement gridElement) {
		super();
		this.gridElement = gridElement;
		this.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 8));

		//set the correct border layout
		Insets insets = getMargin();
		insets.set(0, 0, 0, 0);
		this.setMargin(insets);

		this.setOpaque(true);
	}

	/**
	 * displays the button, depending on its current state
	 */
	protected void paint() {

		switch (this.gridElement.getState()) {
		case FREE: {
			this.setBackground(Color.WHITE);
			break;
		}
		case BLOCKED: {
			this.setBackground(Color.BLACK);
			break;
		}
		case START: {
			this.setBackground(Color.GREEN);
			break;
		}
		case END: {
			this.setBackground(Color.RED);
			break;
		}
		default:
			this.setBackground(Color.WHITE);
		}

	}

	/**
	 * used to display the AlgoState of this element
	 */
	protected void mask() {
		switch (this.gridElement.getAlgoState()) {
		case NONE: {
			this.setText("");
			this.paint();
			break;
		}
		case LOOKED_AT: {
			this.setText(Integer.toString(this.gridElement.getDistance()));
			break;
		}
		case PATH: {
			this.setBackground(this.getBackground().darker());
			break;
		}
		}

	}

	/**
	 * override Size methods
	 */

	public Dimension getPreferredSize() {
		return new Dimension(20, 20);
	}

	public Dimension getMinimumSize(){
		return new Dimension(20, 20);
	}

	public Dimension getMaximumSize(){
		return new Dimension(20, 20);
	}


	/**
	 * @return the gridElement
	 */
	protected GridElement getGridElement() {
		return gridElement;
	}

}
