package cg3d.shapes;

// ccarreto 
// Last rev. 4/12/2021

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

public class Floor extends Shape3D {

	// Class Floor - Creates a square tile floor on the XZ plane
	// divisions - number of tiles in the X and Z direction
	// min, max - range of coordinates in the X and Z direction
	// color1, color2 - colors of the tiles
	// fill - flag to fill or draw the tiles
	public Floor(int divisions, float min, float max, Color3f color1, Color3f color2, boolean fill) {
		
		// === Define the dimensions of the geometry === 
		// Range in X
		int m = divisions;
		float a = min;
		float b = max;
		float divX = (b - a) / m;

		// Range in Z
		int n = divisions;
		float c = min;
		float d = max;
		float divZ = (d - c) / n;

		// Total vertices
		int totalPts = m * n * 4;

		// === Generate the data of the vertices (coordinates and colors) ===
		Point3f[] pts = new Point3f[totalPts]; // Array of vertices
		Color3f[] col = new Color3f[totalPts]; // Array of colors of vertices

		int idx = 0; // Vertex index counter 
		boolean invert = true; // Flag to invert the color
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {

				// Vertex 0
				float x = a + i * divX;
				float z = c + j * divZ;
				float y = 0f;
				pts[idx] = new Point3f(x, y, z);
				col[idx] = (invert ? color1 : color2);
				idx++;

				// Vertex 1
				x = a + i * divX;
				z = c + (j+1) * divZ;
				y = 0f;
				pts[idx] = new Point3f(x, y, z);
				col[idx] = (invert ? color1 : color2);
				idx++;

				// Vertex 2
				x = a + (i+1) * divX;
				z = c + (j+1) * divZ;
				y = 0f;
				pts[idx] = new Point3f(x, y, z);
				col[idx] = (invert ? color1 : color2);
				idx++;
				
				// Vertex 3
				x = a + (i+1) * divX;
				z = c + j * divZ;
				y = 0f;
				pts[idx] = new Point3f(x, y, z);
				col[idx] = (invert ? color1 : color2);
				idx++;

				invert = !invert;
			}
			if(divisions % 2 == 0)
				invert = !invert;	
		}

		// === Define the geometry ===
		// Use the colors of the vertices if the fill parameter is true
		QuadArray geom = null;
		
		if(fill == true) {
			geom = new QuadArray(totalPts, QuadArray.COORDINATES | QuadArray.COLOR_3);
			geom.setCoordinates(0, pts);
			geom.setColors(0,  col);
		}
		else  {
			geom = new QuadArray(totalPts, QuadArray.COORDINATES);
			geom.setCoordinates(0, pts);	
		}
		
		
		Appearance app =  new Appearance();
		
		PolygonAttributes pa = new PolygonAttributes();
        if(fill == true) {
          pa.setPolygonMode(PolygonAttributes.POLYGON_FILL);        	
        }
        else {
       	  pa.setPolygonMode(PolygonAttributes.POLYGON_LINE);
       	  app.setColoringAttributes(new ColoringAttributes(color1, ColoringAttributes.SHADE_FLAT));
        }
		
        pa.setCullFace(PolygonAttributes.CULL_NONE);
   
        app.setPolygonAttributes(pa);
        
		this.setGeometry(geom); // Set the geometry
		this.setAppearance(app); // Set the appearance
	}
}
