/**
 *
 */
package test_public;

import java.security.InvalidAlgorithmParameterException;

import org.junit.Test;

import datamodel.Grid;
import datamodel.GridElementAlgoState;
import static datamodel.GridElementAlgoState.*;
import static datamodel.GridElementState.*;

import junit.framework.Assert;

import logic.WayProblemSolver;

import logic.algorithm.AlgorithmFactory;
import logic.algorithm.Algos;

/**
 * public test class
 *
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 *
 */
public class WayProblemSolverTest {

	private WayProblemSolver solver;
	private static int counter;
	private Grid grid;

	private void isPath(int row, int col) {
		Assert.assertEquals("The knode ("+row+", "+col+") is no part of the path", 
				PATH, grid.getElementAt(row, col).getAlgoState());
	}

	private void isLookedAt(int row, int col) {
		GridElementAlgoState state = grid.getElementAt(row, col).getAlgoState();
		Assert.assertEquals("The knode ("+row+", "+col+") hasn't the state LOOKED_AT: ", 
				LOOKED_AT, state);
	}

	private void isNone(int row, int col) {
		Assert.assertEquals("The knode ("+row+", "+col+") hasn't the state NONE: ", 
				NONE, grid.getElementAt(row, col).getAlgoState());
	}

	@Test
	public void mooreTest() throws InvalidAlgorithmParameterException{

		//get a grid
		grid = new Grid(7, 8, null);
		grid.getElementAt(3, 1).setState(START);
		grid.getElementAt(3, 5).setState(END);
		grid.getElementAt(2, 3).setState(BLOCKED);
		grid.getElementAt(3, 3).setState(BLOCKED);
		grid.getElementAt(4, 3).setState(BLOCKED);
		grid.getElementAt(1, 4).setState(BLOCKED);
		grid.getElementAt(1, 5).setState(BLOCKED);
		grid.getElementAt(5, 4).setState(BLOCKED);
		grid.getElementAt(5, 5).setState(BLOCKED);
		grid.getElementAt(5, 6).setState(BLOCKED);

		//solve
		this.solver = new WayProblemSolver(AlgorithmFactory.getAlgorithm(Algos.Moore), null, grid, 0);

		try{
		Assert.assertTrue(this.solver.solve());

		grid = this.solver.getGrid();

		//test some fields
		isPath(0, 3);
		isPath(0, 4);
		isPath(0, 5);
		isPath(0, 6);
		isPath(1, 6);
		isPath(2, 6);

		//check distance
		Assert.assertEquals(48, grid.getEndElement().getDistance());

		//all test successful
		counter++;

		}
		catch(AssertionError e){
			throw e;
		}

	}

	@Test
	public void dijkstraTest() throws InvalidAlgorithmParameterException{

		/*
		 * Tesmap Layout:
		 *
		 *  01234567
		 * 0        0
		 * 1        1
		 * 2 ~~~~~~ 2
		 * 3 ~^^^^~ 3
		 * 4 ~^^^^~ 4
		 * 5 ~^^^^~ 5
		 * 6 ~^##^~ 6
		 * 7S~^##^~Z7
		 *  01234567
		 */

		//get a grid
		grid = new Grid(8, 8, null);
		grid.getElementAt(7, 0).setState(START);
		grid.getElementAt(7, 7).setState(END);
		grid.getElementAt(7, 3).setState(BLOCKED);
		grid.getElementAt(6, 3).setState(BLOCKED);
		grid.getElementAt(7, 4).setState(BLOCKED);
		grid.getElementAt(6, 4).setState(BLOCKED);


		grid.getElementAt(7, 2).setState(MOUNTAIN);
		grid.getElementAt(6, 2).setState(MOUNTAIN);
		grid.getElementAt(5, 2).setState(MOUNTAIN);
		grid.getElementAt(4, 2).setState(MOUNTAIN);
		grid.getElementAt(3, 2).setState(MOUNTAIN);
		grid.getElementAt(3, 3).setState(MOUNTAIN);
		grid.getElementAt(4, 3).setState(MOUNTAIN);
		grid.getElementAt(5, 3).setState(MOUNTAIN);
		grid.getElementAt(3, 4).setState(MOUNTAIN);
		grid.getElementAt(4, 4).setState(MOUNTAIN);
		grid.getElementAt(5, 4).setState(MOUNTAIN);
		grid.getElementAt(3, 5).setState(MOUNTAIN);
		grid.getElementAt(4, 5).setState(MOUNTAIN);
		grid.getElementAt(5, 5).setState(MOUNTAIN);
		grid.getElementAt(6, 5).setState(MOUNTAIN);
		grid.getElementAt(7, 5).setState(MOUNTAIN);

		grid.getElementAt(7, 1).setState(SWAMP);
		grid.getElementAt(6, 1).setState(SWAMP);
		grid.getElementAt(5, 1).setState(SWAMP);
		grid.getElementAt(4, 1).setState(SWAMP);
		grid.getElementAt(3, 1).setState(SWAMP);
		grid.getElementAt(2, 1).setState(SWAMP);
		grid.getElementAt(2, 2).setState(SWAMP);
		grid.getElementAt(2, 3).setState(SWAMP);
		grid.getElementAt(2, 4).setState(SWAMP);
		grid.getElementAt(2, 5).setState(SWAMP);
		grid.getElementAt(2, 6).setState(SWAMP);
		grid.getElementAt(3, 6).setState(SWAMP);
		grid.getElementAt(4, 6).setState(SWAMP);
		grid.getElementAt(5, 6).setState(SWAMP);
		grid.getElementAt(6, 6).setState(SWAMP);
		grid.getElementAt(7, 6).setState(SWAMP);

		this.solver = new WayProblemSolver(AlgorithmFactory.getAlgorithm(Algos.Dijkstra), null, grid, 0);

		try{
			//solve
			Assert.assertTrue(solver.solve());

			grid = this.solver.getGrid();

			//test some fields
			isPath(2, 0);
			isPath(1, 1);
			isPath(1, 6);
			isPath(2, 7);
			isLookedAt(1, 0);
			isLookedAt(2, 1);
			isLookedAt(1, 7);
			isLookedAt(2, 6);

			//check distance
			Assert.assertEquals(72, grid.getEndElement().getDistance());

			counter+=2;
		}
		catch(AssertionError e){
			throw e;
		}
	}

	@Test
	public void AStarTest() throws InvalidAlgorithmParameterException{
		//get a grid
		grid = new Grid(10,10, null);
		grid.getElementAt(2,2).setState(START);
		grid.getElementAt(7,7).setState(END);

		this.solver = new WayProblemSolver(AlgorithmFactory.getAlgorithm(Algos.A_Star), null, grid, 0);

		try{
			//solve
			Assert.assertTrue(this.solver.solve());

			grid = this.solver.getGrid();

			//test some fields
			isPath(3, 3);
			isPath(4, 4);
			isPath(5, 5);
			isPath(6, 6);


			isNone(0, 2);
			isNone(2, 0);
			isNone(1, 5);
			isNone(5, 1);
			isNone(6, 2);
			isNone(2, 6);
			isNone(6, 8);
			isNone(8, 6);
			isNone(7, 8);
			isNone(8, 7);
			isNone(8, 8);

			//check distance
			Assert.assertEquals(30, grid.getEndElement().getDistance());

			counter+=2;
		}
		catch(AssertionError e){
			throw e;
		}
	}

	@Test
	public void getResult() {
		System.out.println("Points in public tests: " + counter);
	}
}
