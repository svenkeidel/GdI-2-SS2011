/**
 * 
 */
package datamodel;

/**
 * encapsulate a color by representing it through its RGB color values
 * 
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 * 
 */
public class RGB {

	private int red;
	private int green;
	private int blue;
	private int brightness;

	/**
	 * constructor
	 * 
	 * @param red the red RGB value
	 * @param green the green RGB value
	 * @param blue the blue RGB value
	 * @param brightness the brightness RGB value
	 */
	public RGB(int red, int green, int blue, int brightness) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.brightness = brightness;
	}

	/**
	 * Constructor
	 * 
	 * @param rgb an int value with a complete RGB color
	 */
	public RGB(int rgb) {
		// extract single values
		this.brightness = (rgb >> 24) & 0xff;
		this.red = (rgb >> 16) & 0xff;
		this.green = (rgb >> 8) & 0xff;
		this.blue = rgb & 0xff;
	}

	/**
	 * @return an int with the complete RGB color
	 */
	public int getRGBValue() {
		int rgb = brightness << 8;
		rgb = (rgb | red) << 8;
		rgb = (rgb | green) << 8;
		rgb = (rgb | blue);

		return rgb;
	}

	/**
	 * @return the red RGB value
	 */
	public int getRed() {
		return red;
	}

	/**
	 * @return the green RGB value
	 */
	public int getGreen() {
		return green;
	}

	/**
	 * @return the blue RGB value
	 */
	public int getBlue() {
		return blue;
	}

	/**
	 * @return the brightness
	 */
	public int getBrightness() {
		return brightness;
	}

	public int getTrieKeyForDepth(int depth) {
		if (depth > 0) {
			int key = 0;
			int and_mask = 1;

			int SM_Brightness = ((getBrightness() >> (8 - depth)) & and_mask) << 3;
			int SM_Red = ((getRed() >> (8 - depth)) & and_mask) << 2;
			int SM_Green = ((getGreen() >> (8 - depth)) & and_mask) << 1;
			int SM_Blue = (getBlue() >> (8 - depth)) & and_mask;

			key = (SM_Brightness | SM_Red | SM_Blue | SM_Green);

			return key;
		} else
			throw new UnsupportedOperationException(
					"Invalid depth for key generating!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + blue;
		result = prime * result + brightness;
		result = prime * result + green;
		result = prime * result + red;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RGB other = (RGB) obj;
		if (blue != other.blue)
			return false;
		if (brightness != other.brightness)
			return false;
		if (green != other.green)
			return false;
		if (red != other.red)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RGB [r=" + red + ", g=" + green + ", b=" + blue + ", br="
				+ brightness + "]";
	}

}
