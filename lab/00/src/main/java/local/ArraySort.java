package local;

import java.util.Arrays;

/**
 * provides methods to insert an element in a sorted array.
 *
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 *
 */
public class ArraySort {

	/**
	 * inserts a number into a sorted array of numbers.
	 *
	 * @param element
	 *            the element to insert.
	 * @param array
	 *            the sorted array.
	 * @return the sorted array with the element at the right position.
	 */
	public static int[] insertElement(int element, int[] array) {
		// Copies the array and increases the length of the new array
		int[] tempArray = Arrays.copyOf(array, array.length + 1);
		// Insert the element at the last position of the new array
		tempArray[tempArray.length - 1] = element;
		// Sort the array
		Arrays.sort(tempArray);

		return tempArray;
	}

	/**
	 * inserts a number into a sorted array of numbers.
	 *
	 * @param element
	 *            the element to insert.
	 * @param array
	 *            the sorted array.
	 * @return the sorted array with the element at the right position.
	 */
	public static int[] insertElementFast(int element, int[] array) {

		int length = array.length;

		int[] temp = new int[length+1];

		// if the element must be appended at the end
		if(array[length-1] < element) {
			System.arraycopy(array, 0, temp, 0, length);
			temp[length] = element;

		// if the element must be inserted at the beginning
		} else if(array[0] > element) {
			System.arraycopy(array, 0, temp, 1, length);
			temp[0] = element;

		} else {

			int upper = length;
			int lower = 0;
			int pos   = 0;

			while(upper >= lower) {
				pos = (upper + lower) / 2;

				// if at the right position: break
				if((pos >= length) || (pos == 0) || ((array[pos-1] <= element) && (array[pos] >= element)))
					break;

				// if number to big go right
				if(array[pos] < element)
					lower = pos + 1;
				// if number to low go left
				else if(array[pos - 1] > element)
					upper = pos;
			}

			System.arraycopy(array, 0, temp, 0, pos);
			System.arraycopy(array, pos, temp, pos + 1, length - pos);
			temp[pos] = element;
		}

		return temp;
	}
}
