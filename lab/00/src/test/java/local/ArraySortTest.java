package local;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.Arrays;

import local.ArraySort;


/**
 * tests the methods of the class ArraySort.
 * Use these tests to test your insertElementFast method.
 *
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 *
 */
public class ArraySortTest {

	private int[] array;

	/**
	 * initiate the array
	 */
	@Before
	public void init(){
		array = new int[]{1,2,3,4,5};
	}

	/**
	 * Inserts a Element that will be put at the front of the array.
	 * Tests that the array will be the one it should be.
	 */
	@Test
	public void insertAtFrontTest(){
		this.array = ArraySort.insertElementFast(0, array);
		Assert.assertArrayEquals(new int[]{0,1,2,3,4,5}, this.array);
	}

	/**
	 * Inserts a element that is already in the array.
	 * Tests that the array will be the one it should be.
	 */
	@Test
	public void insertSameInMiddleTest(){
		this.array = ArraySort.insertElementFast(2, array);
		Assert.assertArrayEquals(new int[]{1,2,2,3,4,5}, this.array);
	}

	/**
	 * Inserts a element that will be put in the back of the array.
	 * Tests that the array will be the one it should be.
	 */
	@Test
	public void insertAtBackTest(){
		this.array = ArraySort.insertElementFast(42, array);
		Assert.assertArrayEquals(new int[]{1,2,3,4,5,42}, this.array);
	}

	public int[] createRandomArray(int length) {
		Random r = new Random();
		int[] temp = new int[length];
		for(int i=0; i<length; i++)
			temp[i] = r.nextInt(length);
		Arrays.sort(temp);
		return temp;
	}

	@Test
	public void randomArrayTest() {
		Random r = new Random();
		int element;
		int[] randomArray;
		int[] testArray;
		for(int i=1; i<=100; i++) {
			randomArray = createRandomArray(i);
			element = r.nextInt(11);

			testArray = ArraySort.insertElementFast(element, randomArray);
			this.array = ArraySort.insertElement(element, randomArray);

			Assert.assertArrayEquals(testArray, this.array);
		}
	}
}
