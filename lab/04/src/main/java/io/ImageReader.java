/**
 * 
 */
package io;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;


import datamodel.RGB;

/**
 * encapsulates the step of reading the image
 * 
 * @author Jakob Karolus, Kevin Munk
 * @version 1.0
 *
 */
public class ImageReader implements Iterable<RGB> {
	
	private BufferedImage image = null;
	
	/**
	 * reads the picture from the given file 
	 * 
	 * @param file the path to the image
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ImageReader(String file) throws FileNotFoundException, IOException {
		super();
		
		this.image = getBufferedImage(file);
	}

	/**
	 * reads the picture from the given file and returns it as a bufferedImage
	 * 
	 * @param file the file name
	 * @return the bufferedImage
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private BufferedImage getBufferedImage(String file) throws FileNotFoundException, IOException{
		 return ImageIO.read(new FileInputStream(file));
	}

	@Override
	public Iterator<RGB> iterator() {
		return new ImageIterator(image);
	}

	/**
	 * @return the height of the image
	 */
	public int getHeight() {
		return image.getHeight();
	}

	/**
	 * @return the width of the image
	 */
	public int getWidth() {
		return image.getWidth();
	}
}
