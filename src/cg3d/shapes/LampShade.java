package cg3d.shapes;

import javax.media.j3d.Appearance;
import javax.media.j3d.IndexedQuadArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.vecmath.Point3f;

import com.sun.j3d.utils.geometry.*;

public class LampShade extends Shape3D {
	public LampShade(int n, Appearance app) {

		int totalVertices = n * 4;
		int totalIndices = n * 16;

		IndexedQuadArray geom = new IndexedQuadArray(totalVertices, IndexedQuadArray.COORDINATES, totalIndices);

		// Initial set of vertices that define the first slice
		Point3f[] pts = { new Point3f(1f, 0, 0), new Point3f(1.1f, 0, 0), new Point3f(1.1f - 0.5f, 0.7f, 0),
				new Point3f(1f - 0.5f, 0.7f, 0) };

		// Transformation to generate the unique vertices
		Transform3D tr = new Transform3D();
		tr.rotY(2 * Math.PI / n);

		// Generate all the unique vertices
		int index = 0;
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < 4; i++) {
				geom.setCoordinate(index++, pts[i]);

				tr.transform(pts[i]);
			}
		}

		// Construction of the 4 faces using the actual 4 vertices and the next 4
		// vertices
		int a = 0;
		int b = 1;
		int c = 2;
		int d = 3;

		int e, f, g, h;

		index = 0;
		for (int i = 0; i < n; i++) {

			e = (a + 4) % totalVertices;
			f = (b + 4) % totalVertices;
			g = (c + 4) % totalVertices;
			h = (d + 4) % totalVertices;

			// Back face
			geom.setCoordinateIndex(index++, a);
			geom.setCoordinateIndex(index++, d);
			geom.setCoordinateIndex(index++, h);
			geom.setCoordinateIndex(index++, e);

			// Front face
			geom.setCoordinateIndex(index++, b);
			geom.setCoordinateIndex(index++, f);
			geom.setCoordinateIndex(index++, g);
			geom.setCoordinateIndex(index++, c);

			// Top face
			geom.setCoordinateIndex(index++, c);
			geom.setCoordinateIndex(index++, g);
			geom.setCoordinateIndex(index++, h);
			geom.setCoordinateIndex(index++, d);

			// Bottom face
			geom.setCoordinateIndex(index++, a);
			geom.setCoordinateIndex(index++, e);
			geom.setCoordinateIndex(index++, f);
			geom.setCoordinateIndex(index++, b);

			a = e;
			b = f;
			c = g;
			d = h;
		}

		GeometryInfo gi = new GeometryInfo(geom);
		NormalGenerator ng = new NormalGenerator();
		ng.generateNormals(gi);

		this.setGeometry(gi.getGeometryArray());
		this.setAppearance(app);
	}
}
