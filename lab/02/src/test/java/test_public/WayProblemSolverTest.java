/**
 *
 */
package test_public;

import java.security.InvalidAlgorithmParameterException;

import junit.framework.Assert;

import logic.WayProblemSolver;
import logic.algorithm.AlgorithmFactory;
import logic.algorithm.Algos;

import org.junit.Test;

import datamodel.Grid;
import datamodel.GridElementAlgoState;
import datamodel.GridElementState;

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


	@Test
	public void mooreTest() throws InvalidAlgorithmParameterException{

		//get a grid
		Grid grid = new Grid(7, 8, null);
		grid.getElementAt(3, 1).setState(GridElementState.START);
		grid.getElementAt(3, 5).setState(GridElementState.END);
		grid.getElementAt(2, 3).setState(GridElementState.BLOCKED);
		grid.getElementAt(3, 3).setState(GridElementState.BLOCKED);
		grid.getElementAt(4, 3).setState(GridElementState.BLOCKED);
		grid.getElementAt(1, 4).setState(GridElementState.BLOCKED);
		grid.getElementAt(1, 5).setState(GridElementState.BLOCKED);
		grid.getElementAt(5, 4).setState(GridElementState.BLOCKED);
		grid.getElementAt(5, 5).setState(GridElementState.BLOCKED);
		grid.getElementAt(5, 6).setState(GridElementState.BLOCKED);

		//solve
		this.solver = new WayProblemSolver(AlgorithmFactory.getAlgorithm(Algos.Moore), null, grid, 0);

		try{
		Assert.assertTrue(this.solver.solve());

		//test some fields
		Assert.assertEquals(GridElementAlgoState.PATH, this.solver.getGrid().getElementAt(0, 3).getAlgoState());
		Assert.assertEquals(GridElementAlgoState.PATH, this.solver.getGrid().getElementAt(0, 4).getAlgoState());
		Assert.assertEquals(GridElementAlgoState.PATH, this.solver.getGrid().getElementAt(0, 5).getAlgoState());
		Assert.assertEquals(GridElementAlgoState.PATH, this.solver.getGrid().getElementAt(0, 6).getAlgoState());
		Assert.assertEquals(GridElementAlgoState.PATH, this.solver.getGrid().getElementAt(1, 6).getAlgoState());
		Assert.assertEquals(GridElementAlgoState.PATH, this.solver.getGrid().getElementAt(2, 6).getAlgoState());

		//check distance
		Assert.assertEquals(48, this.solver.getGrid().getEndElement().getDistance());

		//all test successful
		counter++;

		}
		catch(AssertionError e){
			throw e;
		}

	}

	@Test
	public void dijkstraTest() throws InvalidAlgorithmParameterException{

		//get a grid
		Grid grid = new Grid(8, 8, null);
		grid.getElementAt(7, 0).setState(GridElementState.START);
		grid.getElementAt(7, 7).setState(GridElementState.END);
		grid.getElementAt(7, 3).setState(GridElementState.BLOCKED);
		grid.getElementAt(6, 3).setState(GridElementState.BLOCKED);
		grid.getElementAt(7, 4).setState(GridElementState.BLOCKED);
		grid.getElementAt(6, 4).setState(GridElementState.BLOCKED);


		grid.getElementAt(7, 2).setState(GridElementState.MOUNTAIN);
		grid.getElementAt(6, 2).setState(GridElementState.MOUNTAIN);
		grid.getElementAt(5, 2).setState(GridElementState.MOUNTAIN);
		grid.getElementAt(4, 2).setState(GridElementState.MOUNTAIN);
		grid.getElementAt(3, 2).setState(GridElementState.MOUNTAIN);
		grid.getElementAt(3, 3).setState(GridElementState.MOUNTAIN);
		grid.getElementAt(4, 3).setState(GridElementState.MOUNTAIN);
		grid.getElementAt(5, 3).setState(GridElementState.MOUNTAIN);
		grid.getElementAt(3, 4).setState(GridElementState.MOUNTAIN);
		grid.getElementAt(4, 4).setState(GridElementState.MOUNTAIN);
		grid.getElementAt(5, 4).setState(GridElementState.MOUNTAIN);
		grid.getElementAt(3, 5).setState(GridElementState.MOUNTAIN);
		grid.getElementAt(4, 5).setState(GridElementState.MOUNTAIN);
		grid.getElementAt(5, 5).setState(GridElementState.MOUNTAIN);
		grid.getElementAt(6, 5).setState(GridElementState.MOUNTAIN);
		grid.getElementAt(7, 5).setState(GridElementState.MOUNTAIN);

		grid.getElementAt(7, 1).setState(GridElementState.SWAMP);
		grid.getElementAt(6, 1).setState(GridElementState.SWAMP);
		grid.getElementAt(5, 1).setState(GridElementState.SWAMP);
		grid.getElementAt(4, 1).setState(GridElementState.SWAMP);
		grid.getElementAt(3, 1).setState(GridElementState.SWAMP);
		grid.getElementAt(2, 1).setState(GridElementState.SWAMP);
		grid.getElementAt(2, 2).setState(GridElementState.SWAMP);
		grid.getElementAt(2, 3).setState(GridElementState.SWAMP);
		grid.getElementAt(2, 4).setState(GridElementState.SWAMP);
		grid.getElementAt(2, 5).setState(GridElementState.SWAMP);
		grid.getElementAt(2, 6).setState(GridElementState.SWAMP);
		grid.getElementAt(3, 6).setState(GridElementState.SWAMP);
		grid.getElementAt(4, 6).setState(GridElementState.SWAMP);
		grid.getElementAt(5, 6).setState(GridElementState.SWAMP);
		grid.getElementAt(6, 6).setState(GridElementState.SWAMP);
		grid.getElementAt(7, 6).setState(GridElementState.SWAMP);

		this.solver = new WayProblemSolver(AlgorithmFactory.getAlgorithm(Algos.Dijkstra), null, grid, 0);

		try{
			//solve
			Assert.assertTrue(solver.solve());

			//test some fields
			Assert.assertEquals(GridElementAlgoState.PATH, this.solver.getGrid().getElementAt(2, 0).getAlgoState());
			Assert.assertEquals(GridElementAlgoState.PATH, this.solver.getGrid().getElementAt(1, 1).getAlgoState());
			Assert.assertEquals(GridElementAlgoState.PATH, this.solver.getGrid().getElementAt(1, 6).getAlgoState());
			Assert.assertEquals(GridElementAlgoState.PATH, this.solver.getGrid().getElementAt(2, 7).getAlgoState());
			Assert.assertEquals(GridElementAlgoState.LOOKED_AT, this.solver.getGrid().getElementAt(1, 0).getAlgoState());
			Assert.assertEquals(GridElementAlgoState.LOOKED_AT, this.solver.getGrid().getElementAt(2, 1).getAlgoState());
			Assert.assertEquals(GridElementAlgoState.LOOKED_AT, this.solver.getGrid().getElementAt(1, 7).getAlgoState());
			Assert.assertEquals(GridElementAlgoState.LOOKED_AT, this.solver.getGrid().getElementAt(2, 6).getAlgoState());

			//check distance
			Assert.assertEquals(72, this.solver.getGrid().getEndElement().getDistance());

			counter+=2;
		}
		catch(AssertionError e){
			throw e;
		}
	}

	@Test
	public void AStarTest() throws InvalidAlgorithmParameterException{
		//get a grid
		Grid grid = new Grid(10,10, null);
		grid.getElementAt(2,2).setState(GridElementState.START);
		grid.getElementAt(7,7).setState(GridElementState.END);

		this.solver = new WayProblemSolver(AlgorithmFactory.getAlgorithm(Algos.A_Star), null, grid, 0);

		try{
			//solve
			Assert.assertTrue(this.solver.solve());

			//test some fields
			Assert.assertEquals(GridElementAlgoState.PATH, this.solver.getGrid().getElementAt(3, 3).getAlgoState());
			Assert.assertEquals(GridElementAlgoState.PATH, this.solver.getGrid().getElementAt(4, 4).getAlgoState());
			Assert.assertEquals(GridElementAlgoState.PATH, this.solver.getGrid().getElementAt(5, 5).getAlgoState());
			Assert.assertEquals(GridElementAlgoState.PATH, this.solver.getGrid().getElementAt(6, 6).getAlgoState());


			Assert.assertEquals(GridElementAlgoState.NONE, this.solver.getGrid().getElementAt(0, 2).getAlgoState());
			Assert.assertEquals(GridElementAlgoState.NONE, this.solver.getGrid().getElementAt(2, 0).getAlgoState());
			Assert.assertEquals(GridElementAlgoState.NONE, this.solver.getGrid().getElementAt(1, 5).getAlgoState());
			Assert.assertEquals(GridElementAlgoState.NONE, this.solver.getGrid().getElementAt(5, 1).getAlgoState());
			Assert.assertEquals(GridElementAlgoState.NONE, this.solver.getGrid().getElementAt(6, 2).getAlgoState());
			Assert.assertEquals(GridElementAlgoState.NONE, this.solver.getGrid().getElementAt(2, 6).getAlgoState());
			Assert.assertEquals(GridElementAlgoState.NONE, this.solver.getGrid().getElementAt(6, 8).getAlgoState());
			Assert.assertEquals(GridElementAlgoState.NONE, this.solver.getGrid().getElementAt(8, 6).getAlgoState());
			Assert.assertEquals(GridElementAlgoState.NONE, this.solver.getGrid().getElementAt(7, 8).getAlgoState());
			Assert.assertEquals(GridElementAlgoState.NONE, this.solver.getGrid().getElementAt(8, 7).getAlgoState());
			Assert.assertEquals(GridElementAlgoState.NONE, this.solver.getGrid().getElementAt(8, 8).getAlgoState());

			//check distance
			Assert.assertEquals(30, this.solver.getGrid().getEndElement().getDistance());

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
