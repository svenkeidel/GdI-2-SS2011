/**
 * 
 */
package io;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.NoSuchElementException;

import datamodel.RGB;

/**
 * Iterator class which provides functionality to process over the single pixel
 * of a given picture
 * 
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 * 
 */
public class ImageIterator implements Iterator<RGB> {

	private BufferedImage image;
	private int heigth;
	private int width;

	private int currentX;
	private int currentY;

	/**
	 * constructor
	 * 
	 * @param image
	 *            the image to iterate over
	 */
	public ImageIterator(BufferedImage image) {
		this.image = image;
		this.currentX = 0;
		this.currentY = 0;
		this.heigth = image.getHeight()-1;
		this.width = image.getWidth()-1;
	}

	@Override
	public boolean hasNext() {
		if (this.currentY > this.heigth) {
			return false;
		}
		return true;
	}

	@Override
	public RGB next() {
		if (hasNext()) {

			// get rgb number
			int rgb = this.image.getRGB(currentX, currentY);

			inc();
			
			return new RGB(rgb);
		} else {
			throw new NoSuchElementException();
		}
	}

	/**
	 * auxiliary method to increment X or Y respectively
	 */
	private void inc() {
		if (this.currentX == this.width) {
			// reset X and inc Y
			this.currentX = 0;
			this.currentY++;
		} else {
			// only inc X
			this.currentX++;
		}
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
