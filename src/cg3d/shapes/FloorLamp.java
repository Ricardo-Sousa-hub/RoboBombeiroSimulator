package cg3d.shapes;

import javax.media.j3d.Appearance;
import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.picking.*;


public class FloorLamp extends Group {

	public FloorLamp(Appearance lampShadeApp, Appearance bodyApp) {
		// Base
		Primitive base = new Cylinder(0.3f, 0.05f, Cylinder.GENERATE_NORMALS, 60, 1, bodyApp);
		//this.addChild(base);
		Transform3D tr = new Transform3D();
		tr.setTranslation(new Vector3f(0, 0.025f, 0));
		TransformGroup tg = new TransformGroup(tr);
		tg.addChild(base);
		this.addChild(tg);
		
		// Body
		Primitive body = new Cylinder(0.025f, 1.5f, Cylinder.GENERATE_NORMALS, bodyApp);
		//this.addChild(base);
		tr = new Transform3D();
		tr.setTranslation(new Vector3f(0, 0.75f, 0));
		tg = new TransformGroup(tr);
		tg.addChild(body);
		this.addChild(tg);
		
		// Top
		LampShade lampShade = new LampShade(10, lampShadeApp);
		PickTool.setCapabilities(lampShade, PickTool.INTERSECT_TEST);
		
		//this.addChild(lampShade);
		tr = new Transform3D();
		tr.setScale(0.5);
		tr.setTranslation(new Vector3f(0, 1.2f, 0));
		tg = new TransformGroup(tr);
		tg.addChild(lampShade);
		this.addChild(tg);
	}
}
