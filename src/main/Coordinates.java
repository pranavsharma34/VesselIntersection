package main;

public class Coordinates {

	private int _x;
	private int _y;
	private double _dateTime;
	
	public Coordinates(int x, int y, double dateTime)
	{
		setX(x);
		setY(y);
		setDateTime(dateTime);
	}

	private void setDateTime(double dateTime) 
	{
		_dateTime = dateTime;
	}

	public double getDateTime()
	{
		return _dateTime;
	}
		
	public int getX() 
	{
		return _x;
	}

	private void setX(int x) 
	{
		_x = x * 1000;
	}

	public int getY() 
	{
		return _y;
	}

	private void setY(int y) 
	{
		_y = y * 1000;
	}	
}
