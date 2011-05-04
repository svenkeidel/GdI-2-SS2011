package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import gui.FieldButton;
import gui.QueenProblemGui;

import queenProblem.QueenProblemSolver;


/**
 * The controller of the Queen Problem.
 *
 * It controls ActionEvent's of the buttons of the gui and
 * PropertyChangeEvent's of the model
 */
public class QueenProblemController implements ActionListener, PropertyChangeListener {

	/**
	 * a reference of the view
	 */
	private QueenProblemGui gui;

	/**
	 * a reference of the model
	 */
	private QueenProblemSolver solver;


	/**
	 * constructor
	 */
	public QueenProblemController(QueenProblemSolver solver) {
		this.solver = solver;
		solver.addPropertyChangeListener(this);
	}


	/**
	 * decide what to do if a button on the gui was pressed
	 */
	public void actionPerformed(ActionEvent evt) {

		// if the "generate"-button was pressed
		if(evt.getActionCommand().equals("generate")) {

			int length = gui.getLength();
			int delay = gui.getDelay();

			solver = new QueenProblemSolver(new boolean[length][length], delay);
			solver.addPropertyChangeListener(this);
			gui.clearField();
			gui.drawField(new boolean[length][length]);

		// if the "solve"-button was pressed
		} else if(evt.getActionCommand().equals("solve")) {

			gui.pressedSolving();
			solver.execute();

		// if the "stop"-button was pressed
		} else if(evt.getActionCommand().equals("stop")) {

			int length = solver.getPlayField().length;
			boolean [][] field = new boolean[length][length];
			System.arraycopy(solver.getPlayField(), 0, field, 0, length);

			int delay = gui.getDelay();
			solver.cancel(true);
			solver = new QueenProblemSolver(field, delay);
			solver.addPropertyChangeListener(this);

			gui.updateField(field);
			gui.pressedStop();

		// if field button was pressed
		} else if(evt.getActionCommand().equals("FieldButton")) {

			FieldButton source = (FieldButton) evt.getSource();
			int row = source.getRow();
			int col = source.getCol();

			if(solver.getPlayField()[row][col]) {
				solver.dropQueen(row, col);
			} else {
				if(solver.isLegalPosition(row,col)) {
					solver.setQueen(row, col);
				} else {
					gui.drawQueen(row, col);
					try { Thread.sleep(1000); } catch (Exception e) {/**Exception*/}
					gui.eraseQueen(row, col);
				}
			}
		}
	}
	

	/**
	 * decides what to do if the model has changed
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("setQueen")) {
			int[] pos = (int[]) evt.getNewValue();
			gui.drawQueen(pos[0], pos[1]);
		} else if(evt.getPropertyName().equals("dropQueen")) {
			int[] pos = (int[]) evt.getNewValue();
			gui.eraseQueen(pos[0], pos[1]);
		} else if(evt.getPropertyName().equals("solved")) {
			gui.pressedStop();
		} else if (evt.getPropertyName().equals("unsolved")) {
			gui.showUnsolved();
			gui.pressedStop();
		}
	}


	/**
	 * sets the view.
	 */
	public void setGui(QueenProblemGui gui) {
		this.gui = gui;
	}
}
