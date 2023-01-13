package cg3d.shapes;

import java.awt.Component;

import javax.media.j3d.Material;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Point3f;
import javax.vecmath.TexCoord2f;

import cg3d.appearance.TextureAppearance;

public class ImagePanel extends Shape3D {
	public ImagePanel(Component c, String image, float size) {

		TextureAppearance app = new TextureAppearance(c, image, new Material(), true, false);
		app.setTransparencyAttributes(new TransparencyAttributes(TransparencyAttributes.FASTEST, 0f));

		float ar = app.getImageHeight() / (float) app.getImageWidth();

		QuadArray geom = new QuadArray(4, QuadArray.COORDINATES | QuadArray.TEXTURE_COORDINATE_2);
		geom.setCoordinate(0, new Point3f(-size / ar, -size, 0));
		geom.setCoordinate(1, new Point3f(size / ar, -size, 0));
		geom.setCoordinate(2, new Point3f(size / ar, size, 0));
		geom.setCoordinate(3, new Point3f(-size / ar, size, 0));

		geom.setTextureCoordinate(0, 0, new TexCoord2f(0f, 0f));
		geom.setTextureCoordinate(0, 1, new TexCoord2f(1f, 0f));
		geom.setTextureCoordinate(0, 2, new TexCoord2f(1f, 1f));
		geom.setTextureCoordinate(0, 3, new TexCoord2f(0f, 1f));

		this.setGeometry(geom);
		this.setAppearance(app);
	}
}
