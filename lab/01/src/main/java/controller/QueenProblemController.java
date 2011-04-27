package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.apache.log4j.Logger;

import gui.FieldButton;
import gui.QueenProblemGui;

import queenProblem.QueenProblemSolver;

public class QueenProblemController implements ActionListener, PropertyChangeListener {
	private static final Logger logger =
		Logger.getLogger(QueenProblemController.class);

	private QueenProblemGui gui;

	private QueenProblemSolver solver;
	public QueenProblemController(QueenProblemSolver solver) {
		this.solver = solver;
		solver.addPropertyChangeListener(this);
	}

	public void actionPerformed(ActionEvent evt) {

		if(evt.getActionCommand().equals("generate")) {

			int length = gui.getLength();
			int delay = gui.getDelay();
			logger.debug("generate("+length+", "+delay+")");

			solver = new QueenProblemSolver(new boolean[length][length], delay);
			solver.addPropertyChangeListener(this);
			gui.clearField();
			gui.drawField(new boolean[length][length]);

		} else if(evt.getActionCommand().equals("solve")) {

			logger.debug("solve()");
			gui.pressedSolving();
			solver.execute();

		} else if(evt.getActionCommand().equals("stop")) {

			logger.debug("stop()");
			int length = solver.getPlayField().length;
			boolean [][] field = new boolean[length][length];
			System.arraycopy(solver.getPlayField(), 0, field, 0, length);

			int delay = gui.getDelay();
			solver.cancel(true);
			solver = new QueenProblemSolver(field, delay);
			solver.addPropertyChangeListener(this);

			gui.updateField(field);
			gui.pressedStop();

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
					logger.debug("sleep");
					try { Thread.sleep(1000); } catch (Exception e) {logger.error(e.getMessage());}
					gui.ereaseQueen(row, col);
				}
			}
		}
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("setQueen")) {
			int[] pos = (int[]) evt.getNewValue();
			gui.drawQueen(pos[0], pos[1]);
		} else if(evt.getPropertyName().equals("dropQueen")) {
			int[] pos = (int[]) evt.getNewValue();
			gui.ereaseQueen(pos[0], pos[1]);
		} else if(evt.getPropertyName().equals("solved")) {
			gui.pressedStop();
		}
	}

	public void setGui(QueenProblemGui gui) {
		this.gui = gui;
	}
}
