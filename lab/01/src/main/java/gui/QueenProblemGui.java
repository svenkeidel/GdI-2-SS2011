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
	private JPanel[][] fieldArray;
	private JFormattedTextField fieldSize;
	private JFormattedTextField delay;

	private QueenProblemController controller;

	public QueenProblemGui(QueenProblemController controller) {
		super("Queen Problem");

		this.controller = controller;

		pane = this.getContentPane();
		
		JButton generate = new JButton("Generate");
		generate.setActionCommand("generate");
		generate.addActionListener(this.controller);

		JButton solve = new JButton("Solve it!");
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

		drawField(new boolean[DEFAULT_SIZE][DEFAULT_SIZE]);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void drawQueen(int row, int col){
		logger.debug("drawQueen("+row+", "+col+")");
		JLabel queen = new JLabel("D");
		queen.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
		fieldArray[row][col].add(queen, BorderLayout.CENTER);
		fieldArray[row][col].revalidate();
	}

	public void ereaseQueen(int row, int col){
		logger.debug("ereaseQueen("+row+", "+col+")");
		fieldArray[row][col].removeAll();
		fieldArray[row][col].repaint();
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
		fieldArray = new JPanel[length][length];

		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

		for(int i=0; i<length; i++) {
			for(int j=0; j<length; j++) {
				fieldArray[i][j] = new JPanel();

				if((i + j) % 2 == 0)
					fieldArray[i][j].setBackground(Color.WHITE);
				else
					fieldArray[i][j].setBackground(Color.GRAY);

				fieldArray[i][j].setBorder(border);
				fieldPanel.add(fieldArray[i][j]);

				if(field[i][j] == true)
					drawQueen(i, j);
			}
		}

		fieldPanel.revalidate();
		((JPanel) pane).repaint();
	}

	public void updateField() {
		clearField();
		int length = getLength();
		drawField(new boolean[length][length]);
	}

	public int getLength() {
		return ((Number) fieldSize.getValue()).intValue();
	}

	public int getDelay() {
		return ((Number) delay.getValue()).intValue();
	}
}
