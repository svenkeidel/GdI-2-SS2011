package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.apache.log4j.Logger;

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
			gui.updateField();
		} else if(evt.getActionCommand().equals("solve")) {
			logger.debug("solve()");
			solver.execute();
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
			// TODO: do smth.
		}
	}

	public void setGui(QueenProblemGui gui) {
		this.gui = gui;
	}
}
