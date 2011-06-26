package stickDivision;

import java.util.HashMap;
import java.util.Map;

public class StickDivision2 {
	public static Map<Integer, Integer> cutStick(int[] value, int length) {

		int[] result = new int[length + 1];
		int[] bestCut = new int[length + 1];
		result[0] = 0;
		bestCut[0] = 0;
		int q;
		for (int subLength = 1; subLength <= length; subLength++) {
			q = 0;
			bestCut[subLength] = 1;
			for (int cutPosition = 1; cutPosition <= subLength; cutPosition++) {
				if(q < value[cutPosition] + result[subLength - cutPosition]) {
					bestCut[subLength] = cutPosition;
					q = value[cutPosition] + result[subLength - cutPosition];
				}
			}
			result[subLength] = q;
		}

		Map<Integer, Integer> solution = new HashMap<Integer, Integer>();
		for(int i=1; i<result.length; i++)
			solution.put(result[i], bestCut[i]);

		return solution;
	}

	public static void printSolution(
			int[] value, int length, Map<Integer, Integer> result) {
		int bestResult = -1;
		for(int element : result.keySet())
			bestResult = Math.max(bestResult, element);

		System.out.println("Pieces after cutting (length, value):");
		int RestStickLength = length;
		while (RestStickLength > 0) {
			int bestCut = result.get(bestResult);
			bestResult -= value[bestCut];
			RestStickLength -= bestCut;
			System.out.println("["+bestCut+", "+value[bestCut]+"]");
		}
	}

	public static void main (String [] args) {
		int[] value = new int[] {0, 1, 6, 8, 11, 12, 19, 20, 23, 26, 30};
		int length = value.length - 1;
		Map<Integer, Integer> result = cutStick(value, length);
		printSolution(value, length, result);
	}
}
