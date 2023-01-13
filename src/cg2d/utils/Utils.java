package cg2d.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Utils {

	public static BufferedImage getImage(Object obj, String imageFileName) {
		// Reads an image from a folder inside the project's src folder
		BufferedImage bi = null;
		URL imageSrc = null;

		imageSrc = obj.getClass().getClassLoader().getResource(imageFileName);

		try {
			bi = ImageIO.read(imageSrc);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Image could not be read.");
		}

		return bi;
	}

	public static float getMaxValue(float[] v) {
		// Gets the maximum value of v
		float max = 0;
		for (int i = 0; i < v.length; i++)
			if (v[i] > max)
				max = v[i];
		return max;
	}

	public static void drawAxis(Graphics2D g2, Color c, int length, int width) {
		g2.setColor(c);
		g2.setStroke(new BasicStroke(width));
		
		g2.drawLine(-length, 0, length, 0);
		g2.drawLine(0, -length, 0, length);
	}
}
