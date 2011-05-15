package logic.algorithm;



/**
 * The enum for Tolerance-Levels.
*
* 
*
*/
public enum A_Star_Tolerance {
	
	AUTO(007),

	VERYLOW(10),

	LOW(8),

	MID(4),

	HIGH(1),

	VERYHIGH(0.50);


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
		case AUTO:
			return "dynamic";
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
			return 10;
		case LOW:
			return 8;
		case MID:
			return 4;
		case HIGH:
			return 1;
		case VERYHIGH:
			return 0.5;
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