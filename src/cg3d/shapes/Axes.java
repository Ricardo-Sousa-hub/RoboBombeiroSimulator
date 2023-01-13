package cg3d.shapes;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.LineArray;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.Shape3D;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

public class Axes extends Shape3D {
	public Axes(Color3f color, int thickness, float length) {
        // Appearance 
		Appearance app = new Appearance();
		app.setColoringAttributes(new ColoringAttributes(color, ColoringAttributes.SHADE_FLAT));
		app.setLineAttributes(new LineAttributes(thickness, LineAttributes.PATTERN_SOLID, true));
			
		// Geometry
		LineArray geom = new LineArray(6, LineArray.COORDINATES);
		geom.setCoordinate(0, new Point3f(0f, 0f, 0f));
		geom.setCoordinate(1, new Point3f(length, 0f, 0f));
		
		geom.setCoordinate(2, new Point3f(0f, 0f, 0f));
		geom.setCoordinate(3, new Point3f(0f, length, 0f));
		
		geom.setCoordinate(4, new Point3f(0f, 0f, 0f));
		geom.setCoordinate(5, new Point3f(0f, 0f, length));
		
		// Set the appearance and the geometry
		this.setAppearance(app);
		this.setGeometry(geom);
	}
}
