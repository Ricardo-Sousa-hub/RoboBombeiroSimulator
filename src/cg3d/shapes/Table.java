package cg3d.shapes;

import javax.media.j3d.Appearance;
import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.*;

public class Table extends Group {
   public Table(Appearance topApp, Appearance legApp) {
	   
	   // Top
	   Primitive top = new Box(0.5f, 0.05f, 0.5f, Box.GENERATE_NORMALS | Box.ENABLE_GEOMETRY_PICKING | Box.GENERATE_TEXTURE_COORDS, topApp);
	   //this.addChild(top);
	   Transform3D tr = new Transform3D();
	   tr.setTranslation(new Vector3f(0f, 0.05f + 0.5f, 0f));
	   TransformGroup tg = new TransformGroup(tr);
	   tg.addChild(top);
	   this.addChild(tg);
	   
	   // Legs
	   Primitive leg = new Cylinder(0.05f, 0.5f, legApp);
	   //this.addChild(leg);
	   tr.setTranslation(new Vector3f(0.4f, 0.25f, 0.4f));
	   tg = new TransformGroup(tr);
	   tg.addChild(leg);
	   this.addChild(tg);
	  
	   leg = new Cylinder(0.05f, 0.5f, legApp);
	   //this.addChild(leg);
	   tr.setTranslation(new Vector3f(-0.4f, 0.25f, 0.4f));
	   tg = new TransformGroup(tr);
	   tg.addChild(leg);
	   this.addChild(tg);
	  
	   leg = new Cylinder(0.05f, 0.5f, legApp);
	   //this.addChild(leg);
	   tr.setTranslation(new Vector3f(0.4f, 0.25f, -0.4f));
	   tg = new TransformGroup(tr);
	   tg.addChild(leg);
	   this.addChild(tg);
	  
	   leg = new Cylinder(0.05f, 0.5f, legApp);
	   //this.addChild(leg);
	   tr.setTranslation(new Vector3f(-0.4f, 0.25f, -0.4f));
	   tg = new TransformGroup(tr);
	   tg.addChild(leg);
	   this.addChild(tg);
   }
}
