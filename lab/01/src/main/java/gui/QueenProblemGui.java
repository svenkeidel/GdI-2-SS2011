package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.border.Border;

import controller.QueenProblemController;

public class QueenProblemGui extends JFrame { 

	private Container pane;
	private JPanel fieldPanel;
	private JPanel[][] fieldArray;

	private QueenProblemController controller;

	public QueenProblemGui(QueenProblemController controller) {
		super("Queen Problem");

		this.controller = controller;

		pane = this.getContentPane();
		
		JButton generate = new JButton("Generate");
		generate.addActionListener(controller);
		generate.setActionCommand("generate");

		JButton solve = new JButton("Solve it!");
		solve.addActionListener(controller);
		solve.setActionCommand("solve");

		/* TODO: Try to use better Layout Manager that the components
		 * don't get pushed out of the window
		 */
		JPanel menubar = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		JPanel menu = new JPanel(new GridLayout(1, 6, 20, 20));
		menubar.add(menu);

		menu.add(new JLabel("Size of Field: ", JLabel.RIGHT));
		menu.add(new JTextField("8"));
		menu.add(new JLabel("Delay: ", JLabel.RIGHT));
		menu.add(new JTextField("500"));
		menu.add(generate);
		menu.add(solve);
		pane.add(menubar, BorderLayout.PAGE_START);

		this.fieldPanel = new JPanel(new GridLayout(8, 8));
		pane.add(fieldPanel, BorderLayout.CENTER);

		drawField(new boolean[8][8]);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void drawField(boolean[][] field) {
		int length = field.length;

		fieldArray = new JPanel[8][8];

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
			}
		}
	}
}
