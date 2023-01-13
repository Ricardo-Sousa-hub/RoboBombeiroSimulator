package cg3d.shapes;

import java.text.Normalizer;

import javax.media.j3d.Appearance;
import javax.media.j3d.IndexedQuadArray;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;
import javax.vecmath.Point3f;
import javax.vecmath.TexCoord2f;

import com.sun.j3d.utils.geometry.*;

public class Dice extends Shape3D {
	public Dice(Appearance app) {
		IndexedQuadArray qa = new IndexedQuadArray(12, QuadArray.COORDINATES | QuadArray.TEXTURE_COORDINATE_2, 24);
		Point3f[] coords = { new Point3f(0, 0, 0), new Point3f(1, 0, 0), new Point3f(1, 1, 0), new Point3f(0, 1, 0),
				new Point3f(0, 0, 1), new Point3f(1, 0, 1), new Point3f(1, 1, 1), new Point3f(0, 1, 1) };
		int[] coordIndices = { 0, 3, 2, 1, 0, 1, 5, 4, 1, 2, 6, 5, 2, 3, 7, 6, 3, 0, 4, 7, 4, 5, 6, 7 };
		qa.setCoordinates(0, coords);
		qa.setCoordinateIndices(0, coordIndices);
		TexCoord2f[] tex = { new TexCoord2f(0, 1), new TexCoord2f(1f / 3, 1), new TexCoord2f(2f / 3, 1),
				new TexCoord2f(1, 1), new TexCoord2f(0, 0.5f), new TexCoord2f(1f / 3, 0.5f),
				new TexCoord2f(2f / 3, 0.5f), new TexCoord2f(1, 0.5f), new TexCoord2f(0, 0), new TexCoord2f(1f / 3, 0),
				new TexCoord2f(2f / 3, 0), new TexCoord2f(1, 0) };

		int[] texIndices = { 0, 1, 5, 4, 1, 2, 6, 5, 2, 3, 7, 6, 5, 6, 10, 9, 6, 7, 11, 10, 4, 5, 9, 8 };
		qa.setTextureCoordinates(0, 0, tex);
		qa.setTextureCoordinateIndices(0, 0, texIndices);

		GeometryInfo gi = new GeometryInfo(qa);
		NormalGenerator ng = new NormalGenerator();
		ng.generateNormals(gi);

		this.setGeometry(gi.getGeometryArray());
		this.setAppearance(app);
	}
}
