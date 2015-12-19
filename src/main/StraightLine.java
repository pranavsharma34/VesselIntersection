package main;

public class StraightLine 
{
	public boolean Instersect(double x1, double y1, 
			double x2, double y2, double x3, 
			double y3, double x4, double y4)
	{
		return java.awt.geom.Line2D.linesIntersect(x1, y1, x2, y2, x3, y3, x4, y4);
	}
	
	public Pair<Coordinates, Coordinates> Intersect(Coordinates c1, Coordinates c2,
			Coordinates c3, Coordinates c4)
	{
		int x1 = c1.getX();
		int y1 = c1.getY();
		double t1 = c1.getDateTime();
		int x2 = c2.getX();
		int y2 = c2.getY();
		double t2 = c2.getDateTime();
		int x3 = c3.getX();
		int y3 = c3.getY();
		double t3 = c3.getDateTime();
		int x4 = c4.getX();
		int y4 = c4.getY();
		double t4 = c4.getDateTime();
		
		double d = (x1-x2)*(y3-y4) - (y1-y2)*(x3-x4);
		if (d == 0) return null;
		
		double xi = ((x3-x4)*(x1*y2-y1*x2)-(x1-x2)*(x3*y4-y3*x4))/d;
		double yi = ((y3-y4)*(x1*y2-y1*x2)-(y1-y2)*(x3*y4-y3*x4))/d;
		
		double dateTime1 = (Math.sqrt((int)(xi - x1)^2 + (int)(yi - y1)^2)) / 
				(Math.sqrt((int)(x2 - x1)^2 + (int)(y2 - y1)^2) / (t2 -t1));
		double dateTime2 = (Math.sqrt((int)(xi - x3)^2 + (int)(yi - y3)^2)) / 
				(Math.sqrt((int)(x4 - x3)^2 + (int)(y4 - y3)^2) / (t4 -t3));
		
		Coordinates c5 = new Coordinates((int)xi, (int)yi, dateTime1);
		Coordinates c6 = new Coordinates((int)xi, (int)yi, dateTime2);
		
		return new Pair<Coordinates, Coordinates>(c5, c6);	
	}
	
}
