package cg3d.appearance;

import java.awt.image.BufferedImage;
import javax.media.j3d.Appearance;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.ImageComponent3D;
import javax.media.j3d.Texture;
import javax.media.j3d.Texture3D;
import javax.vecmath.Point3d;

public class PNoiseTextureAppearence extends Appearance {
	ImageComponent2D image = null;

	public PNoiseTextureAppearence(PerlinNoise pnoise) {
		BufferedImage[] img = new BufferedImage[128];
		for (int i = 0; i < 128; i++) {
			img[i] = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
			for (int r = 0; r < 128; r++) {
				for (int c = 0; c < 128; c++) {
					double v = pnoise.turbulence(new Point3d(c / 32.0, r / 32.0, i / 32.0), 2, 2, 8);
					int rgb = (int) ((0.55 + 0.35 * Math.sin(3 * (c / 32.0 + v))) * 256);
					rgb = ((rgb << 16) | (rgb << 8) | rgb);
					img[i].setRGB(c, r, rgb);
				}
			}
		}

		ImageComponent3D image = new ImageComponent3D(ImageComponent3D.FORMAT_RGB, img);
		Texture3D texture = new Texture3D(Texture.BASE_LEVEL, Texture.RGBA, image.getWidth(), image.getHeight(),
				image.getDepth());
		texture.setImage(0, image);
		texture.setEnable(true);
		texture.setMagFilter(Texture.BASE_LEVEL_LINEAR);
		texture.setMinFilter(Texture.BASE_LEVEL_LINEAR);
		this.setTexture(texture);

	}

}
