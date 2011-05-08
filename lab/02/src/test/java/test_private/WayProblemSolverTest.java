/**
 *
 */
package test_private;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import datamodel.Grid;
import datamodel.GridElement;
import static datamodel.GridElementState.*;

import static junit.framework.Assert.*;

import logic.WayProblemSolver;

import logic.algorithm.AlgorithmFactory;
import logic.algorithm.Algos;

/**
 * public .test class
 *
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 *
 */ 
public class WayProblemSolverTest {

	public final static int INFINITE = Integer.MAX_VALUE;

	private WayProblemSolver solver;
	private Vector<GridElement> neighbors;
	private Vector<Integer> wayCosts;
	private Grid grid;

	@Before
	public void before() {
		this.solver    = null;
		this.grid      = null;
		this.neighbors = null;
		this.wayCosts  = null;
	}

	@Test
	public void valideGrid() {
		grid = new Grid(5, 5, null);
		for(GridElement e : grid.getKnodes())
			e.setState(BLOCKED);
		grid.getElementAt(1, 1).setState(START);
		grid.getElementAt(3, 3).setState(END);
		try {
			solver = new WayProblemSolver(AlgorithmFactory.getAlgorithm(Algos.Moore), null, grid, 0);
		} catch (Exception e) {
			fail("A valid playfield wasn't created");
		}
	}

	@Test
	public void invalideGrid_twoStarts() {
		grid = new Grid(5, 5, null);
		for(GridElement e : grid.getKnodes())
			e.setState(BLOCKED);
		grid.getElementAt(1, 1).setState(START);
		grid.getElementAt(1, 2).setState(START);
		grid.getElementAt(3, 3).setState(END);
		try {
			solver = new WayProblemSolver(AlgorithmFactory.getAlgorithm(Algos.Moore), null, grid, 0);
			fail("A invalid playfield with 2 starts was accepted");
		} catch (Exception e) {
			// Everything goes right
		}
	}

	@Test
	public void invalideGrid_twoEnds() {
		grid = new Grid(5, 5, null);
		for(GridElement e : grid.getKnodes())
			e.setState(BLOCKED);
		grid.getElementAt(1, 1).setState(START);
		grid.getElementAt(3, 3).setState(END);
		grid.getElementAt(3, 4).setState(END);
		try {
			solver = new WayProblemSolver(AlgorithmFactory.getAlgorithm(Algos.Moore), null, grid, 0);
			fail("A invalid playfield with 2 ends was accepted");
		} catch (Exception e) {
			// Everything goes right
		}
	}

	@Test
	public void invalideGrid_neitherStartNorEnd() {
		grid = new Grid(5, 5, null);
		for(GridElement e : grid.getKnodes())
			e.setState(BLOCKED);
		try {
			solver = new WayProblemSolver(AlgorithmFactory.getAlgorithm(Algos.Moore), null, grid, 0);
			fail("A invalid playfield with no start and end was accepted");
		} catch (Exception e) {
			// Everything goes right
		}
	}

	@Test
	public void neighbors() {
		grid = new Grid(3, 3, null);
		/*
		 * B = blocked
		 * F = free
		 * T = tested field
		 *
		 * Field:
		 * FFF
		 * FTF
		 * BBB
		 */
		for(GridElement e : grid.getKnodes())
			e.setState(FREE);
		grid.getElementAt(2, 0).setState(BLOCKED);
		grid.getElementAt(2, 1).setState(BLOCKED);
		grid.getElementAt(2, 2).setState(BLOCKED);

		neighbors = grid.getNeighborsFrom(grid.getElementAt(1, 1)).getNeighbors();

		notContains(0, 0);
		contains(0, 1);
		notContains(0, 2);
		contains(1, 0);
		notContains(1, 1);
		contains(1, 2);
		notContains(2, 0);
		notContains(2, 1);
		notContains(2, 2);
	}

	private void notContains(int row, int col) {
		assertFalse("("+row+","+col+") is wrongly contained in neighbors of field (1, 1)", 
				neighbors.contains(grid.getElementAt(row, col)));
	}

	private void contains(int row, int col) {
		assertTrue("("+row+","+col+") is not contained in neighbors of field (1, 1)", 
				neighbors.contains(grid.getElementAt(row, col)));
	}


	@Test
	public void waycosts() {
		grid = new Grid(3, 3, null);
		/*
		 * B = blocked
		 * F = free
		 * M = mountain
		 * S = swamp
		 * T = tested field
		 *
		 * Field:
		 * BFB
		 * STM
		 * BBB
		 *
		 */
		grid.getElementAt(0, 0).setState(BLOCKED);
		grid.getElementAt(0, 1).setState(FREE);
		grid.getElementAt(0, 2).setState(BLOCKED);
		grid.getElementAt(1, 0).setState(SWAMP);
		grid.getElementAt(1, 1).setState(FREE);
		grid.getElementAt(1, 2).setState(MOUNTAIN);
		grid.getElementAt(1, 0).setState(BLOCKED);
		grid.getElementAt(1, 1).setState(BLOCKED);
		grid.getElementAt(1, 2).setState(BLOCKED);

		wayCosts = grid.getNeighborsFrom(grid.getElementAt(1, 1)).getWayCosts();
		
		assertEquals("A free field should have waycosts of 4",
				wayCosts.get(0).intValue(), 4);
		assertEquals("A swamp field should have waycosts of 8",
				wayCosts.get(1).intValue(), 8);
		assertEquals("A mountain field should have waycosts of 10",
				wayCosts.get(2).intValue(), 10);
	}
}
