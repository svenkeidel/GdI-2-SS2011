/**
 * 
 */
package io;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import datamodel.RGB;

/**
 * provides methods to save an image or a binary string
 * 
 * @author Kevin Munk, Jakob Karolus
 * @version 1.0
 */
public class IO {


	/**
	 * write the given binaryString into a file with the given file path
	 * 
	 * @param file the file path where to write the binary string
	 * @param binaryString the binaryString to write, must only contain 0 and 1.
	 * @throws FileNotFoundException
	 */
	public static void writeBinaryString(String file, String binaryString) throws FileNotFoundException{
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(
				file)));
		
		StringToByteConverter converter = new StringToByteConverter();
		converter.addBinaryString(binaryString);
		byte[] array = converter.getData();
		
		try {
			stream.write(array);
			stream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				stream.close();
			} catch (IOException e) {}
		}
	}

	/**
	 * writes the given Image as a binaryString (as coded by RGB)<br>
	 * use this method to encode your image normally
	 * 
	 * @param file the file path
	 * @param reader the ImageReader containing the image
	 * @throws FileNotFoundException 
	 */
	public static void writeImageBinaryString(String file, ImageReader reader) throws FileNotFoundException{
		StringBuffer binary = new StringBuffer();
		
		for(RGB rbg : reader){
			binary.append(Integer.toBinaryString(rbg.getRGBValue()));
		}
		
		IO.writeBinaryString(file, binary.toString());
		
	}
	
	/**
	 * Saves the given image as a png file at the given position
	 * 
	 * @param file the file path (without ending)
	 * @param image the BufferedImage
	 * @throws IOException 
	 */
	public static void saveImage(String file, BufferedImage image) throws IOException{
		ImageIO.write(image, "png", new File(file + ".png"));
	}
}
