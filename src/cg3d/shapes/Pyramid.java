package cg3d.shapes;

import javax.media.j3d.Appearance;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TriangleArray;
import javax.media.j3d.TriangleFanArray;
import javax.vecmath.Point3f;
import javax.vecmath.TexCoord3f;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;

public class Pyramid extends Shape3D {
	public Pyramid(float h, float b, Appearance app) {
		int[] stripVertexCounts = { 6 }; 
		TriangleFanArray tfa = new TriangleFanArray(6, TriangleArray.COORDINATES | TriangleArray.NORMALS | TriangleArray.TEXTURE_COORDINATE_3, stripVertexCounts);
		Point3f apex = new Point3f(0, h, 0);
		tfa.setCoordinate(0, apex);
		tfa.setCoordinate(1, new Point3f(-b, 0, b));
		tfa.setCoordinate(2, new Point3f(b, 0, b));
		tfa.setCoordinate(3, new Point3f(b, 0, -b));
		tfa.setCoordinate(4, new Point3f(-b, 0, -b));
		tfa.setCoordinate(5, new Point3f(-b, 0, b));
		
		 tfa.setTextureCoordinate(0, 0, new TexCoord3f(0.5f,1f,0.5f));
		 tfa.setTextureCoordinate(0, 1, new TexCoord3f(0f,0f,1f));
		 tfa.setTextureCoordinate(0, 2, new TexCoord3f(1f,0f,1f));
		 tfa.setTextureCoordinate(0, 3, new TexCoord3f(1f,0f,0f));
		 tfa.setTextureCoordinate(0, 4, new TexCoord3f(0f,0f,0f));
		 tfa.setTextureCoordinate(0, 5, new TexCoord3f(0f,0f,1f));
		 
		GeometryInfo gi = new GeometryInfo(tfa);
		NormalGenerator ng = new NormalGenerator();
		ng.generateNormals(gi);
		
		this.setGeometry(gi.getGeometryArray());
		this.setAppearance(app);
	}
}
