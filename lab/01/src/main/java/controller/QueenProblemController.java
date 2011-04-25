package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class QueenProblemController implements ActionListener, PropertyChangeListener {

	public void actionPerformed(ActionEvent evt) {
		if(evt.getActionCommand().equals("generate")) {
			// TODO: do smth.
		} else if(evt.getActionCommand().equals("solve")) {
			// TODO: do smth.
		}
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("setQueen")) {
			// TODO: do smth.
		} else if(evt.getPropertyName().equals("dropQueen")) {
			// TODO: do smth.
		} else if(evt.getPropertyName().equals("solved")) {
			// TODO: do smth.
		}
	}
}
