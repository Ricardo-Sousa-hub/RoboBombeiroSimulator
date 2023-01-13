package cg2d.utils;

public class WtoV {

	int XWmin;
	int YWmin;
	int XWmax;
	int YWmax;
	
	int XVmin;
	int YVmin;
	int XVmax;
	int YVmax;
	
	// Scale factor
	float F;
	
	public WtoV(int XWmin, int YWmin, int XWmax, int YWmax, int XVmin, int YVmin, int XVmax, int YVmax) {
		this.XWmin = XWmin;
		this.YWmin = YWmin;
		this.XWmax = XWmax;
		this.YWmax = YWmax;
		
		this.XVmin = XVmin;
		this.YVmin = YVmin;
		this.XVmax = XVmax;
		this.YVmax = YVmax;
		
		setScaleFactor();
		
	}
	
	public void setScaleFactor() {
		float Fx = (XVmax - XVmin) / (float) (XWmax - XWmin);
		float Fy = (YVmax - YVmin) / (float) (YWmax - YWmin);
		
		if(Fx < Fy)
			F = Fx;
		else
			F = Fy;
	}
	
	public int MapX(int xW) {
		return (int) (F * (xW - XWmin) + XVmin);
	}
	
	public int MapY(int yW) {
		return (int) (F * (yW - YWmin) + YVmin);
	}

	public int MapX(double xW) {
		return (int) (F * (xW - XWmin) + XVmin);
	}
	
	public int MapY(double yW) {
		return (int) (F * (yW - YWmin) + YVmin);
	}

	
}
