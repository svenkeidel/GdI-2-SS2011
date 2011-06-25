package stickDivision;

public class StickDivision {
	public static int cutStick(int[] value, int length) {
		int[] result = new int[length + 1];
		result[0] = 0;
		int q;
		for (int subLength = 1; subLength <= length; subLength++) {
			q = 0;
			System.out.println("q = "+q);

			for (int cutPosition = 1; cutPosition <= subLength; cutPosition++) {
				System.out.println("q = max("+q+", "+
						(value[cutPosition] + result[subLength - cutPosition])+")");
				q = Math.max(q, value[cutPosition] + result[subLength - cutPosition]);
			}
			result[subLength] = q;
			System.out.println("result["+subLength+"] = "+result[subLength]);
		}
		return result[length];
	}

	public static void main (String [] args) {
		int[] value = new int[] {1, 6, 8, 11, 12, 19, 20, 23, 26, 30};
		int length = value.length - 1;
		System.out.println("=============================================");
		int result = cutStick(value, length);
		System.out.println("=============================================");
		System.out.println("result = "+result);
	}
}
