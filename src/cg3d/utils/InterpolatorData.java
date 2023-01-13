package cg3d.utils;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;

public class InterpolatorData {
   private final List<Point3f> positions = new ArrayList<Point3f>();
   private final List<Quat4f> orientations = new ArrayList<Quat4f>();
   private final List<Float> knots= new ArrayList<Float>();
   
   public void add(Point3f position, float angleDeg, float alpha) {
	   positions.add(position);
	   knots.add(alpha);
	   
	   AxisAngle4f aa = new AxisAngle4f(0f, 1f, 0f, (float) Math.toRadians(angleDeg));
	   Quat4f q = new Quat4f();
	   q.set(aa);
	   orientations.add(q);
   }
   
   public Point3f[] getPositions() {
	   return positions.toArray(new Point3f[0]);
   }

   
   public Quat4f[] getOrientations() {
	   return orientations.toArray(new Quat4f[0]);
   }
   
   
   public float[] getKnots() {
	   float[] k = new float[knots.size()];
	   for(int i=0; i<knots.size(); i++)
		   k[i] = knots.get(i);
	   return k;
   }
   
   public float[] getUniformKnots() {
	   float[] k = new float[positions.size()];
	   
	   float v = 1f / (k.length-1);
	   for(int i=0; i<k.length; i++)
		   k[i] = i * v;
	   return k;
   }

}