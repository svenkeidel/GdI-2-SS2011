package queenProblem;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;

import queenProblem.QueenProblemSolver;

/**
 * tests the solver for the QueenProblem; these tests are just for basic
 * functionality, we strongly advise you to write your own tests!
 * 
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 * 
 */
public class QueenProblemTest {

	private boolean[][] field_5x5;
	private boolean[][] field_2x2;
	private boolean[][] field_6x6_withQueen_noSolution;
	private boolean[][] field_8x8_withQueen;
	private boolean[][] field_15x15;
	private QueenProblemSolver solver;
	private static double counter;
	private static Logger logger = Logger.getRootLogger();
	/**
	 * inits the counter
	 */
	@BeforeClass
	public static void init() {
		counter = 0;
		BasicConfigurator.configure();
		logger.setLevel(Level.OFF);
	}

	@Before
	public void beforeTest() {
		this.field_5x5 = new boolean[5][5];
		field_5x5[2][2] = true;
	}

	@Test
	public void setLegalQueenTest() {
		solver = new QueenProblemSolver(field_5x5, 0);
		Assert.assertTrue("setQueen return value was wrongly false", solver.setQueen(0, 1));
		Assert.assertTrue("Legal queen wasn't set.", solver.getPlayField()[0][1]);
	}

	public void trySetQueen(int row, int col) {
		Assert.assertFalse("setQueen return value was wrongly true", solver.setQueen(row, col));
		Assert.assertFalse(
				"Queen was set at a illegal position ["+row+", "+col+"]", 
				solver.getPlayField()[row][col]);
	}

	@Test
	public void setIllegalQueenTest() {
		solver = new QueenProblemSolver(field_5x5, 0);
		field_5x5[2][2] = true;

		trySetQueen(0, 0);
		trySetQueen(0, 2);
		trySetQueen(0, 4);
		trySetQueen(2, 4);
		trySetQueen(4, 4);
		trySetQueen(4, 2);
		trySetQueen(4, 0);
		trySetQueen(2, 0);
	}

	@Test
	public void illegalFieldTest() {
		boolean[][] illegalfield = new boolean[5][5];
		illegalfield[1][1] = true;
		illegalfield[4][4] = true;
		try {
			solver = new QueenProblemSolver(illegalfield, 0);
			Assert.fail("A illegal play field was accepted");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 0,5 Points
	 */
	@Test
	public void solve2x2() {
		try {
			field_2x2 = new boolean[2][2];
			solver = new QueenProblemSolver(field_2x2, 0);
			Assert.assertFalse(solver.solve());

			Assert.assertArrayEquals(new boolean[2][2], solver.getPlayField());

			// inc counter
			counter += 0.5;
		} catch (AssertionError e) {
			throw e;
		}
	}

	/**
	 * 1 Point
	 */
	@Test
	public void solve6x6_noSolution() {
		try {
			field_6x6_withQueen_noSolution = new boolean[6][6];
			field_6x6_withQueen_noSolution[0][0] = true;
			solver = new QueenProblemSolver(field_6x6_withQueen_noSolution, 0);
			Assert.assertFalse(solver.solve());

			boolean[][] expected = new boolean[6][6];
			expected[0][0] = true;
			Assert.assertArrayEquals(expected, solver.getPlayField());

			// inc counter
			counter += 1;
		} catch (AssertionError e) {
			throw e;
		}
	}

	/**
	 * 1,5 Points
	 */
	@Test
	public void solve8x8_withQueen() {
		try {
			field_8x8_withQueen = new boolean[8][8];
			field_8x8_withQueen[3][6] = true;
			solver = new QueenProblemSolver(field_8x8_withQueen, 0);
			Assert.assertTrue(solver.solve());

			boolean[][] expected = new boolean[8][8];
			// set the correct values
			expected[0][1] = true;
			expected[1][5] = true;
			expected[2][0] = true;
			expected[3][6] = true;
			expected[4][3] = true;
			expected[5][7] = true;
			expected[6][2] = true;
			expected[7][4] = true;
			Assert.assertArrayEquals(expected, solver.getPlayField());

			// inc counter
			counter += 1.5;
		} catch (AssertionError e) {
			throw e;
		}
	}

	/**
	 * 2 Points
	 */
	@Test
	public void solve15x15() {
		try {
			field_15x15 = new boolean[15][15];
			solver = new QueenProblemSolver(field_15x15, 0);
			Assert.assertTrue(solver.solve());

			boolean[][] expected = new boolean[15][15];
			// set the correct values
			expected[0][0] = true;
			expected[1][14] = true;
			expected[2][1] = true;
			expected[3][13] = true;
			expected[4][5] = true;
			expected[5][12] = true;
			expected[6][8] = true;
			expected[7][3] = true;
			expected[8][11] = true;
			expected[9][4] = true;
			expected[10][2] = true;
			expected[11][9] = true;
			expected[12][6] = true;
			expected[13][10] = true;
			expected[14][7] = true;

			Assert.assertArrayEquals(expected, solver.getPlayField());

			// inc counter
			counter += 2;
		} catch (AssertionError e) {
			throw e;
		}
	}

	@Test
	public void getResult() {
		System.out.println("Points in public tests: " + counter);
	}

}
