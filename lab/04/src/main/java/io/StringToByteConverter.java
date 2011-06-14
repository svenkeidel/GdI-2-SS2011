/**
 * 
 */
package io;

import java.math.BigInteger;
import java.util.Vector;

/**
 * Converts a String with binary data(0, 1) into an byte[] or vice versa.
 * 
 * @author Kevin Munk, Jakob Karolus
 * @version 1.0
 * 
 */
public class StringToByteConverter {

	private Vector<Byte> byteArray = new Vector<Byte>();
	private byte currentByte = 0;
	private int posOfCurrent = 0;

	/**
	 * Default constructor
	 */
	public StringToByteConverter() {
		super();
	}

	/**
	 * constructor
	 * 
	 * @param array byte[] which should be converted
	 */
	public StringToByteConverter(byte[] array) {
		this();

		for (int i = 0; i < array.length; i++) {
			byteArray.add(array[i]);
		}
	}

	/**
	 * Adds a whole String with binary data(0, 1) to the internal array. Other
	 * chars than 0 and 1 will be ignored.
	 * 
	 * @param binaryString the string with the binary representation of a number
	 */
	public void addBinaryString(String binaryString) {
		for (int i = 0; i < binaryString.length(); i++) {
			char bit = binaryString.charAt(i);

			if (bit == '0') {
				addZero();
			} else if (bit == '1') {
				addOne();
			}
		}
	}

	/**
	 * Add a 0 at the end of the internal array
	 */
	public void addZero() {
		currentByte = (byte) (currentByte << 1);
		posOfCurrent++;

		if (posOfCurrent == 8) {
			addCurrentToVector();
		}
	}

	/**
	 * Add a 1 at the end of the internal array
	 */
	public void addOne() {
		currentByte = (byte) (currentByte << 1);
		currentByte++;
		posOfCurrent++;

		if (posOfCurrent == 8) {
			addCurrentToVector();
		}
	}

	/**
	 * Will be called if the current byte is full. Adds the current to the
	 * internal array and reset the current.
	 */
	private void addCurrentToVector() {
		byteArray.add(currentByte);
		currentByte = 0;
		posOfCurrent = 0;
	}

	/**
	 * Finalize the current byte. Means that the current will be shifted to the
	 * left.
	 * 
	 * @return the current byte shifted to the left, or null if there are no
	 * unfinished data
	 */
	private Byte getFinalUnfinished() {
		// if we have unfinished data
		if (posOfCurrent != 0) {
			byte finalByte = currentByte;

			finalByte = (byte) (finalByte << (8 - posOfCurrent));

			return finalByte;
		}
		return null;
	}

	/**
	 * Returns the internal data in an array.<br>
	 * the last byte can have 0s which wasn't in the internal array, due to the
	 * length of a byte(8 Bit)
	 * 
	 * @return the internal data.
	 */
	public byte[] getData() {
		@SuppressWarnings("unchecked")
		Vector<Byte> copy = (Vector<Byte>) byteArray.clone();

		Byte last = getFinalUnfinished();

		if (last != null) {
			copy.add(last);
		}

		byte[] array = new byte[copy.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = copy.get(i);
		}

		return array;
	}

	/**
	 * delivers a binaryString of the internal byte[]
	 * 
	 * @return String matching a binary representation of the internal byte[]
	 */
	public String getBinaryString() {
		BigInteger bigInt = new BigInteger(getData());

		return bigInt.toString(2);
	}
}
