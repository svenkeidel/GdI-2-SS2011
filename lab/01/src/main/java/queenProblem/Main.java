/**
 * 
 */
package queenProblem;

import gui.QueenProblemGui;
import controller.QueenProblemController;

/**
 * provides functionality to interact with QueenProblemSolver
 * 
 * @author Kevin Munk, Jakob Karolus
 * @version 1.0
 *
 */
public class Main {

	/**
	 * initializes the solver, solves the problem and prints a solution
	 * 
	 * @param args unused
	 */
	public static void main(String[] args) {
		boolean [][] field = new boolean[8][8];
		QueenProblemSolver solver = new QueenProblemSolver(field, 500);
		QueenProblemController controller = new QueenProblemController();
		QueenProblemGui gui = new QueenProblemGui(controller);
		solver.addPropertyChangeListener(controller);
	}

}
