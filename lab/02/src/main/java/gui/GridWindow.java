/**
 *
 */
package gui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidAlgorithmParameterException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import datamodel.Grid;
import datamodel.GridElement;
import datamodel.GridElementState;

import logic.WayProblemSolver;
import logic.algorithm.A_Star;
import logic.algorithm.AlgorithmFactory;
import logic.algorithm.Algos;
import logic.algorithm.A_Star_Tolerance;

/**
 * provides UI to allow the user to interact with the application
 *
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 *
 */
public class GridWindow extends JFrame {

	//buttons and lists
	private JComboBox algosList;
	private JPanel northPanel;
	private JButton generateField;
	private GridPanel gridPanel;
	private JComboBox toggleMenu;
	private JComboBox toleranceMenu;
	private JButton solveButton;
	private JButton cleanButton;

	private WayProblemSolver solver;

	/**
	 *
	 */
	private static final long serialVersionUID = 6995171417287605700L;

	/**
	 * constructor
	 */
	public GridWindow() {
		super("GridWindow");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// buttons, lists
		this.algosList = new JComboBox(Algos.values());
		this.algosList.setSelectedIndex(1);
		this.algosList.addActionListener(new ActionListener(){
		@Override
			public void actionPerformed(ActionEvent e) {
					//TODO:togglemenu filter and refresh
				}
			});

		toleranceMenu = new JComboBox(A_Star_Tolerance.getGUIValues());
		toleranceMenu.setSelectedIndex(0);
		toleranceMenu.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			updateTolerance();
			}
		});
	
		
	
		
		this.generateField = new JButton("Generate");
		this.generateField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				generateField();
			}

		});

		this.toggleMenu = new JComboBox(GridElementState.getGUIValues());

		this.solveButton = new JButton("Solve");
		this.solveButton.setEnabled(false);
		this.solveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				solve();

			}
		});

		this.cleanButton = new JButton("Clean");
		this.cleanButton.setEnabled(false);
		this.cleanButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clean();
			}
		});
		

		// panels
		this.northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.northPanel.add(this.algosList);
		this.northPanel.add(this.toleranceMenu);
		this.northPanel.add(this.toggleMenu);
		this.northPanel.add(this.generateField);
		this.northPanel.add(this.solveButton);
		this.northPanel.add(this.cleanButton);

		this.gridPanel = new GridPanel(this);

		// add the components
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		this.add(this.northPanel, c);
		c.gridy = 1;
		this.add(this.gridPanel, c);

		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
	}

	/**
	 * generate a new Field with a given Solver
	 *
	 * @return true, if successfully created; otherwise false
	 */
	private boolean generateField() {
		//reset the old values for end and start field
		GridElement.resetStartEnd();
		// get some values
		int width, height;
		try {
			// get width
			String w = JOptionPane.showInputDialog(this,
					"Please type in the your desired width (1-49)", "Generate",
					JOptionPane.QUESTION_MESSAGE);
			width = Integer.valueOf(w);
			if (!(width > 0 && width < 50)) {
				JOptionPane.showMessageDialog(this,
						"Width must be between 1 and 49", "Error",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			// get height
			String h = JOptionPane.showInputDialog(this,
					"Please type in the your desired height (1-49)",
					"Generate", JOptionPane.QUESTION_MESSAGE);
			height = Integer.valueOf(h);
			if (!(height > 0 && height < 50)) {
				JOptionPane.showMessageDialog(this,
						"Height must be between 1 and 49", "Error",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this,
					"Width and Height must be numbers", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		// get the algorithm
		Algos algo = (Algos) this.algosList.getSelectedItem();

		// get a new solver, also build field
		try {
			this.solver = new WayProblemSolver(
					AlgorithmFactory.getAlgorithm(algo), this.gridPanel, width,
					height, 50);
		} catch (InvalidAlgorithmParameterException e) {
			JOptionPane.showMessageDialog(this,
					"Please select a valid Algorithm!", "Invalid Algorithm",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// enable the solve, clean Button
		this.solveButton.setEnabled(true);
		this.cleanButton.setEnabled(false);
		this.pack();
		this.setLocationRelativeTo(null);
		return true;
	}

	/**
	 * Presets a grid and sets the specified algo and delay.
	 *
	 * @param algo
	 * @param grid
	 * @param delay
	 */
	public void presetField(Algos algo, Grid grid, int delay) {
		try {
			this.solver = new WayProblemSolver(
					AlgorithmFactory.getAlgorithm(algo), this.gridPanel, grid, 50);
		} catch (InvalidAlgorithmParameterException e) {
			JOptionPane.showMessageDialog(this,
					"Please select a valid Algorithm!", "Invalid Algorithm",
					JOptionPane.ERROR_MESSAGE);
		}

		this.algosList.setSelectedItem(algo);
		this.pack();
		this.setLocationRelativeTo(null);
	}

	/**
	 * starts the solving in an extra thread
	 */
	public void solve() {

		// disable generate, solve and all buttons
		this.generateField.setEnabled(false);
		this.gridPanel.changeButtons(false);
		this.solveButton.setEnabled(false);
		this.cleanButton.setEnabled(false);

		// resets the play field
		this.solver.getGrid().resetGridsAlgoState();

		try {
			this.solver.setAlgorithm(AlgorithmFactory
					.getAlgorithm((Algos) this.algosList.getSelectedItem()));
		} catch (InvalidAlgorithmParameterException e) {
			JOptionPane.showMessageDialog(this,
					"Please select a valid Algorithm!", "Invalid Algorithm",
					JOptionPane.ERROR_MESSAGE);
		}
		// start solving thread
		new WayProblemSolverThread(this.solver).start();
	}

	/**
	 * @return the toggleMenu
	 */
	protected JComboBox getToggleMenu() {
		return toggleMenu;
	}

	/**
	 * delivers the current state of the ToggleMenu
	 */
	protected GridElementState getToggleState() {
		return (GridElementState) this.toggleMenu.getSelectedItem();
	}

	/**
	 * method, which should be called when solving finished<br>
	 * enables solve button and generate button
	 *
	 * @param flag
	 *            decides the displaying message (no solution found or solution
	 *            found)
	 */
	protected void finishedSolving(boolean flag) {
		this.generateField.setEnabled(true);
		this.solveButton.setEnabled(true);
		this.cleanButton.setEnabled(true);

		if (!flag) {
			JOptionPane.showMessageDialog(this, "No Solution was found",
					"No Solution", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * resets the algoState of all elements
	 */
	public void clean(){
		this.solver.getGrid().resetGridsAlgoState();
		this.gridPanel.changeButtons(true);

	}

	/**
	 * invoked, if there's no start or end and solve was invoked
	 */
	protected void falsePreConditions(){
		JOptionPane.showMessageDialog(this, "You must select at least a start and end element", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	
	protected void updateTolerance(){
		A_Star_Tolerance TOL = (A_Star_Tolerance) this.toleranceMenu.getSelectedItem();
		A_Star.setTolerance(TOL.getValue());
	}
	
	
	
}