package cg3d.utils;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.*;

public class ViewLocation extends Group {
  public ViewLocation(Vector3d location, float size, Color3f color ) {
	  
	  Appearance app = new Appearance();
	  app.setColoringAttributes(new ColoringAttributes(new Color3f(color), ColoringAttributes.SHADE_FLAT));
	  Sphere obj = new Sphere(size, app);
	  
	  Transform3D tr = new Transform3D();
	  tr.setTranslation(location);
	  TransformGroup tg = new TransformGroup(tr);
	  
	  tg.addChild(obj);
	  this.addChild(tg);
	  
  }
}
