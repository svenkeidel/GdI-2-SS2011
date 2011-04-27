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
import javax.swing.JPanel;

import javax.swing.border.Border;

import org.apache.log4j.Logger;

import controller.QueenProblemController;

public class QueenProblemGui extends JFrame { 

	public static final int DEFAULT_DELAY = 1000;
	public static final int DEFAULT_SIZE = 8;

	private static final Logger logger =
		Logger.getLogger(QueenProblemGui.class);

	private Container pane;
	private JPanel fieldPanel;
	private FieldButton[][] fieldArray;
	private JFormattedTextField fieldSize, delay;
	private JButton solve, generate;

	private QueenProblemController controller;

	public QueenProblemGui(QueenProblemController controller) {
		super("Queen Problem");

		this.controller = controller;

		pane = this.getContentPane();
		
		generate = new JButton("Generate");
		generate.setActionCommand("generate");
		generate.addActionListener(this.controller);

		solve = new JButton("Solve it!");
		solve.setActionCommand("solve");
		solve.addActionListener(this.controller);

		NumberFormat integerFormat = NumberFormat.getIntegerInstance();

		fieldSize = new JFormattedTextField(integerFormat);
		fieldSize.setValue(new Integer(DEFAULT_SIZE));
		fieldSize.setColumns(2);

		delay = new JFormattedTextField(integerFormat);
		delay.setValue(new Integer(DEFAULT_DELAY));
		delay.setColumns(4);

		JPanel menu = new JPanel(new GridLayout(1, 6, 5, 0));
		Border border = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		menu.setBorder(border);

		menu.add(new JLabel("Size of Field: ", JLabel.RIGHT));
		menu.add(fieldSize);
		menu.add(new JLabel("Delay: ", JLabel.RIGHT));
		menu.add(delay);
		menu.add(generate);
		menu.add(solve);
		pane.add(menu, BorderLayout.PAGE_START);

		this.fieldPanel = new JPanel();
		pane.add(fieldPanel, BorderLayout.CENTER);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);

		drawField(new boolean[DEFAULT_SIZE][DEFAULT_SIZE]);
	}

	public void pressedSolving() {
		solve.setText("Stop!");
		solve.setActionCommand("stop");
		generate.setEnabled(false);
		fieldSize.setEnabled(false);
		delay.setEnabled(false);
	}

	public void pressedStop() {
		solve.setText("Solve it!");
		solve.setActionCommand("solve");
		generate.setEnabled(true);
		fieldSize.setEnabled(true);
		delay.setEnabled(true);
	}

	public void drawQueen(int row, int col){
		logger.debug("drawQueen("+row+", "+col+")");
		fieldArray[row][col].setText("D");
		fieldArray[row][col].revalidate();
	}

	public void ereaseQueen(int row, int col){
		logger.debug("ereaseQueen("+row+", "+col+")");
		fieldArray[row][col].setText("");
		fieldArray[row][col].revalidate();
	}

	public void clearField() {
		fieldPanel.removeAll();	
		for(int i=0; i<fieldArray.length; i++)
			for(int j=0; j<fieldArray[i].length; j++)
				fieldArray[i][j] = null;
		fieldArray = null;
	}

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

		try {Thread.sleep(100);} catch (Exception e) {logger.error(e.getMessage());}

		setFontSize();
	}

	public void updateField(boolean[][] field) {
		int length = field.length;
		for(int i=0; i<length; i++) {
			for(int j=0; j<length; j++) {
				ereaseQueen(i, j);
				if(field[i][j])
					drawQueen(i, j);
			}
		}
	}

	public int getLength() {
		return ((Number) fieldSize.getValue()).intValue();
	}

	public int getDelay() {
		return ((Number) delay.getValue()).intValue();
	}

	private int getFontSize() {
		double fieldPanelHeight = fieldPanel.getBounds().getHeight();
		logger.debug("fieldPanelHeigth: "+fieldPanelHeight);
		double fieldPanelWidth = fieldPanel.getBounds().getWidth();
		logger.debug("fieldPanelWidth: "+fieldPanelWidth);
		return (int) ((fieldPanelHeight + fieldPanelWidth) / 2 / fieldArray.length / 1.5);
	}


	private void setFontSize() {
		int length = getLength();

		int fontSize = getFontSize();
		logger.debug("fontSize: "+fontSize);

		for(int i=0; i<length; i++)
			for(int j=0; j<length; j++)
				fieldArray[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));
	}
}
