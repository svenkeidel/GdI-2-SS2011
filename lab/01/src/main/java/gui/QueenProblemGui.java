package gui;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.border.Border;

import controller.QueenProblemController;

/**
 * The gui which represents the the chess field, where the queens are
 * placed.
 */
public class QueenProblemGui extends JFrame { 

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * the default delay, if no other was set
	 */
	public static final int DEFAULT_DELAY = 1000;

	/**
	 * the default field size, if no other was set
	 */
	public static final int DEFAULT_SIZE = 8;

	/**
	 * the content pane of the frame
	 */
	private Container pane;

	/**
	 * the panel in which the chess field will be inserted
	 */
	private JPanel fieldPanel;

	/**
	 * an array of fields
	 */
	private FieldButton[][] fieldArray;

	/**
	 * text fields to insert new field sizes and delays
	 */
	private JFormattedTextField fieldSize, delay;

	/**
	 * buttons to start solving or recreating the field
	 */
	private JButton solve, generate;

	/**
	 * a reference to the controller
	 */
	private QueenProblemController controller;

	/**
	 * constructor
	 * @param controller the controller which coordinates the button actions
	 */
	public QueenProblemGui(QueenProblemController controller) {
		super("Queen Problem");

		this.controller = controller;

		pane = this.getContentPane();
		
		// Init button "Generate"
		generate = new JButton("Generate");
		generate.setActionCommand("generate");
		generate.addActionListener(this.controller);

		// Init button "Solve It"
		solve = new JButton("Solve it!");
		solve.setActionCommand("solve");
		solve.addActionListener(this.controller);

		NumberFormat integerFormat = NumberFormat.getIntegerInstance();

		// Init textfield for field size
		fieldSize = new JFormattedTextField(integerFormat);
		fieldSize.setValue(new Integer(DEFAULT_SIZE));
		fieldSize.setColumns(2);

		// Init textfield for delay
		delay = new JFormattedTextField(integerFormat);
		delay.setValue(new Integer(DEFAULT_DELAY));
		delay.setColumns(4);

		// Init the menu bar
		JPanel menu = new JPanel(new GridLayout(1, 6, 5, 0));
		Border border = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		menu.setBorder(border);

		// add components of the menu
		menu.add(new JLabel("Size of Field: ", JLabel.RIGHT));
		menu.add(fieldSize);
		menu.add(new JLabel("Delay: ", JLabel.RIGHT));
		menu.add(delay);
		menu.add(generate);
		menu.add(solve);
		pane.add(menu, BorderLayout.PAGE_START);

		// Init the field
		this.fieldPanel = new JPanel();
		pane.add(fieldPanel, BorderLayout.CENTER);

		// display the application
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 400, 420);
		this.setResizable(false);
		this.setVisible(true);

		// the filling of the field has to happen after the field was
		// displayed, to get the size of the chess field.
		drawField(new boolean[DEFAULT_SIZE][DEFAULT_SIZE]);
	}


	/**
	 * Triggers gui changes if the "solve"-button was pressed
	 */
	public void pressedSolving() {
		solve.setText("Stop!");
		solve.setActionCommand("stop");
		generate.setEnabled(false);
		fieldSize.setEnabled(false);
		delay.setEnabled(false);
	}

	
	/**
	 * Triggers gui changes if the "stop"-button was pressed
	 */
	public void pressedStop() {
		solve.setText("Solve it!");
		solve.setActionCommand("solve");
		generate.setEnabled(true);
		fieldSize.setEnabled(true);
		delay.setEnabled(true);
	}


	/**
	 * display a queen at the specified position
	 */
	public void drawQueen(int row, int col){
		fieldArray[row][col].setText("D");
		fieldArray[row][col].revalidate();
	}


	/**
	 * erase a queen at the specified position
	 */
	public void eraseQueen(int row, int col){
		fieldArray[row][col].setText("");
		fieldArray[row][col].revalidate();
	}


	/**
	 * destroy the field and free all references to the field buttons
	 */
	public void clearField() {
		fieldPanel.removeAll();	
		for(int i=0; i<fieldArray.length; i++)
			for(int j=0; j<fieldArray[i].length; j++)
				fieldArray[i][j] = null;
		fieldArray = null;
	}


	/**
	 * draw a chess field and insert it into fieldPanel
	 *
	 * @param field to draw a preset of queens
	 */
	public void drawField(boolean[][] field) {

		int length = field.length;

		fieldPanel.setLayout(new GridLayout(length, length));
		fieldArray = new FieldButton[length][length];

		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

		FieldButton newButton;


		for(int i=0; i<length; i++) {
			for(int j=0; j<length; j++) {

				newButton = new FieldButton(i, j);
				newButton.setActionCommand("FieldButton");
				newButton.addActionListener(this.controller);
				newButton.setBorder(border);

				if((i + j) % 2 == 0)
					newButton.setBackground(Color.WHITE);
				else
					newButton.setBackground(Color.GRAY);

				fieldArray[i][j] = newButton;
				fieldPanel.add(newButton);

				if(field[i][j] == true)
					drawQueen(i, j);
			}
		}

		fieldPanel.revalidate();
		((JPanel) pane).repaint();

		try {Thread.sleep(100);} catch (Exception e) {/**Exception*/}

		setFontSize();
	}


	/**
	 * update the queens on the field
	 */
	public void updateField(boolean[][] field) {
		int length = field.length;
		for(int i=0; i<length; i++) {
			for(int j=0; j<length; j++) {
				eraseQueen(i, j);
				if(field[i][j])
					drawQueen(i, j);
			}
		}
	}


	/**
	 * get the requested field size
	 */
	public int getLength() {
		return ((Number) fieldSize.getValue()).intValue();
	}


	/**
	 * get the requested delay
	 */
	public int getDelay() {
		return ((Number) delay.getValue()).intValue();
	}


	/**
	 * calculate the font size proportional to the size of the field
	 */
	private int getFontSize() {
		double fieldPanelHeight = fieldPanel.getBounds().getHeight();
		double fieldPanelWidth = fieldPanel.getBounds().getWidth();
		return (int) ((fieldPanelHeight + fieldPanelWidth) / 2 / fieldArray.length / 1.5);
	}


	/**
	 * set the font size of all buttons of the field
	 */
	private void setFontSize() {
		int length = getLength();

		int fontSize = getFontSize();

		for(int i=0; i<length; i++)
			for(int j=0; j<length; j++)
				fieldArray[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));
	}

	public void showUnsolved(){
		JOptionPane.showMessageDialog(fieldPanel, "Sorry, there is no solution! :/");
	}
}
