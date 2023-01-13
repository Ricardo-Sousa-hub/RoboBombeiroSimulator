package cg3d.shapes;

import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Group;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Sphere;

public class BoundsObj extends Group{
  public BoundsObj(BoundingSphere bounds, Color3f color, boolean lines) {
	  Appearance app = new Appearance();
	  if(lines)
	    app.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_LINE, PolygonAttributes.CULL_NONE, 0));
	  else
		  app.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_POINT, PolygonAttributes.CULL_NONE, 0));
	  
      app.setColoringAttributes(new ColoringAttributes(color, ColoringAttributes.SHADE_FLAT));	  
	  
      Sphere sphere = new Sphere((float)bounds.getRadius(), app);
	  Transform3D tr = new Transform3D();
	  Point3d center = new Point3d();;
	  bounds.getCenter(center);
	  tr.setTranslation(new Vector3d(center.x, center.y, center.z));
	  TransformGroup tg = new TransformGroup(tr);
	  tg.addChild(sphere);
	  this.addChild(tg);
  }
}
