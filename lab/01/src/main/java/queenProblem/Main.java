/**
 * 
 */
package queenProblem;

import org.apache.log4j.BasicConfigurator;
import controller.QueenProblemController;

import gui.QueenProblemGui;

/**
 * provides functionality to interact with QueenProblemSolver
 * 
 * @author Kevin Munk, Jakob Karolus
 * @version 1.0
 *
 */
public class Main {
	private static final int DEFAULT_DELAY = QueenProblemGui.DEFAULT_DELAY;
	private static final int DEFAULT_SIZE = QueenProblemGui.DEFAULT_SIZE;


	public void loggerConfig(){
		BasicConfigurator.configure();
	}

	/**
	 * initialize the solver, solve the problem and print a solution
	 * 
	 * @param args unused
	 */
	public static void main(String[] args) {
		Main main = new Main();
		main.loggerConfig();
		// initialize model, view and controller
		boolean [][] field = new boolean[DEFAULT_SIZE][DEFAULT_SIZE];
		QueenProblemSolver solver = new QueenProblemSolver(field, DEFAULT_DELAY);
		QueenProblemController controller = new QueenProblemController(solver);
		QueenProblemGui gui = new QueenProblemGui(controller);
		controller.setGui(gui);
	}

}
