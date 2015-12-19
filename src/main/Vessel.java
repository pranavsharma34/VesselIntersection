package main;

import java.util.List;

public class Vessel {

	private List<Coordinates> _coordinates;
	private String _name;
	
	public Vessel(List<Coordinates> coordinates, String name)
	{
		setName(name);
		setCoordinates(coordinates);
	}
	
	private void setCoordinates(List<Coordinates> coordinates) 
	{
		_coordinates = coordinates;
	}

	public List<Coordinates> getCoordinates()
	{
		return _coordinates;
	}
	
	public String getName() 
	{
		return _name;
	}

	private void setName(String name) 
	{
		_name = name;
	}
}
