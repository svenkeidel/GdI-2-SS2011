package logic.algorithm;



/**
 * The enum for Tolerance-Levels.
*
* 
*
*/
public enum A_Star_Tolerance {

	VERYLOW(5.5),

	LOW(4),

	MID(3),

	HIGH(2),

	VERYHIGH(0.75);


	private double value;

	/**
	 * constructor
	 *
	 * @param value value of tolerance level
	 */
	A_Star_Tolerance(double value) {
		this.value = value;
	}

	/**
	 * return the levels the user can choose via GUI
	 *
	 * @return array which contains the levels
	 */
	public static A_Star_Tolerance[] getGUIValues() {
		return A_Star_Tolerance.values();

	}

	public String toString() {
		switch (this) {
		case VERYLOW:
			return "very low";
		case LOW:
			return "low";
		case MID:
			return "mid";
		case HIGH:
			return "high";
		case VERYHIGH:
			return "very high";
		default:
			return "blank";
		}
	}
	
	public static double getToleranceValue(A_Star_Tolerance tol) {
		switch (tol) {
		case VERYLOW:
			return 5.5;
		case LOW:
			return 4;
		case MID:
			return 3;
		case HIGH:
			return 2;
		case VERYHIGH:
			return 0.75;
		default:
			return 3;
		}
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

}