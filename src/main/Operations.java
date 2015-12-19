package main;

import java.util.LinkedList;
import java.util.List;

public class Operations {

	private Vessel _vessel;
	private boolean _intersect;
	private StraightLine _straightLine;
	
	public Operations(Vessel vessel)
	{
		_vessel = vessel;
	}
	
	public Operations(List<Coordinates> coordinates, String name)
	{
		_vessel = new Vessel(coordinates, name);
	}
	
	private double DistanceBetweenPoints(Coordinates c1, Coordinates c2)
	{
		return Math.sqrt((c2.getX() - c1.getX())^2 + (c2.getY() - c1.getY())^2);
	}
	
	private double TimeDifference(Coordinates c1, Coordinates c2)
	{
		return ((c2.getDateTime()) - (c1.getDateTime()));
	}
	
	private double TimeDifferenceInHours(double time)
	{
		return time / 3600;
	}
	
	public double AverageSpeed()
	{
		List<Coordinates> coordinates = _vessel.getCoordinates();
		List<Double> speeds = new LinkedList<Double>();
		for (int i = 0; i + 1 <= coordinates.size(); i++)
		{
			speeds.add(DistanceBetweenPoints(coordinates.get(i), coordinates.get(i+1)) /
					TimeDifference(coordinates.get(i), coordinates.get(i+1)));
		}
		
		double speed = 0;
		for (int i = 0; i < speeds.size(); i++)
		{
			speed = speed + speeds.get(i);
		}
		
		return speed / speeds.size();
	}
	
	public double TotalDistance()
	{
		List<Coordinates> coordinates = _vessel.getCoordinates();
		double distance = 0;
		for (int i = 0; i + 1 <= coordinates.size(); i++)
		{
			distance = distance + DistanceBetweenPoints(coordinates.get(i), coordinates.get(i+1));
		}
		return distance;
	}
	
	private double TotalDistanceInKM(double distance)
	{
		return distance / 1000;
	}
	
	public Pair<List<Pair<Coordinates, Coordinates>>, List<Pair<Coordinates, Coordinates>>> 
		GetPairList(Vessel v1, Vessel v2)
	{
		List<Coordinates> list1 = v1.getCoordinates();
		List<Coordinates> list2 = v2.getCoordinates();

		List<Pair<Coordinates, Coordinates>> pair1 = 
				new LinkedList<Pair<Coordinates, Coordinates>>();
		
		List<Pair<Coordinates, Coordinates>> pair2 = 
				new LinkedList<Pair<Coordinates, Coordinates>>();
		
		for (int i = 0; i + 1 <= list1.size(); i++)
		{
			pair1.add(i, new Pair<Coordinates, Coordinates>(list1.get(i), list1.get(i+1)));
		}
	
		for (int i = 0; i + 1 <= list2.size(); i++)
		{
			pair2.add(i, new Pair<Coordinates, Coordinates>(list2.get(i), list2.get(i+1)));
		}
		
		return new Pair<List<Pair<Coordinates, Coordinates>>, List<Pair<Coordinates, Coordinates>>>(pair1, pair2);
	}
	
	public Pair<Coordinates,Coordinates> Intersection(List<Pair<Coordinates, Coordinates>> pair1, 
			List<Pair<Coordinates, Coordinates>> pair2)
	{
		Pair<Coordinates,Coordinates> intersectionPoints = null;
		
		for (int i = 0; i < pair1.size(); i++)
		{
			for (int j = 0; j < pair2.size(); j++)
			{
				Coordinates c1 = pair1.get(i).getLeft();
				Coordinates c2 = pair1.get(i).getRight();
				Coordinates c3 = pair2.get(j).getLeft();
				Coordinates c4 = pair2.get(j).getRight();
				
				if (_straightLine.Instersect(c1.getX(), c1.getY(), 
						c2.getX(), c2.getY(), 
						c3.getX(), c3.getY(), 
						c4.getX(), c4.getY()))
				{				
					intersectionPoints = _straightLine.Intersect(c1, c2, c3, c4);
					_intersect = true;
					break;
				}
			}
		}
		return intersectionPoints;
	}
	
	public String SendWarning(Coordinates c1, Coordinates c2)
	{
		if (_intersect && EstimatedTime(c1, c2) < 3600)
		{
			System.out.println("The two vessels intersected");
			return "Intersect";
		}
		return null;
	}
	
	public double EstimatedTime(Coordinates c1, Coordinates c2)
	{
		if (c2.getDateTime() > c1.getDateTime())
		{
			return c2.getDateTime() - c1.getDateTime();
		}
		return c1.getDateTime() - c2.getDateTime();
	}
}
