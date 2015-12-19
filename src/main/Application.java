package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;

public class Application {

	public static void main(String[] args)
	{
		ParseJSON("C:\\Users\\pranavsharma34\\Downloads\\Marine GeoDev - Technical Exercise - Developer\\TestData.json");
	}
	
	public static List<Vessel> ParseJSON(String fileName)
	{
		List<Coordinates> coordinates = new LinkedList<Coordinates>();
		List<Vessel> vessels = new LinkedList<Vessel>();
		
		String fileContent = ReadFile(fileName);
		JSONObject obj = new JSONObject(fileContent);
		
		JSONArray array = obj.getJSONArray("vessels");
		
		for (int j = 0; j < array.length(); j++)
		{
			String name = array.getJSONObject(j).getString("name");
			JSONObject position = array.getJSONObject(j);
			JSONArray positions = position.getJSONArray("positions");
			for (int i = 0; i < positions.length(); i++)
			{
				JSONObject object = positions.getJSONObject(i);
				int x = object.getInt("x");
				int y = object.getInt("y");
				String timestamp = object.getString("timestamp");
				
				Coordinates c = new Coordinates(x, y, ParseStringToTime(timestamp));
				coordinates.add(c);
			}
			Vessel vessel = new Vessel(coordinates, name);
			vessels.add(vessel);
		}
		
		return vessels;
	}

	private static double ParseStringToTime(String timestamp) 
	{
		String time = timestamp.substring(11, 16);
		double minutes = Integer.parseInt(time.substring(0, 2));
		double seconds = Integer.parseInt(time.substring(3, 5));
		return (minutes * 60) + (seconds);
	}

	private static String ReadFile(String fileName) 
	{
		byte[] encoded = null;
		try {
			encoded = Files.readAllBytes(Paths.get(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(encoded);
	}

	@SuppressWarnings("unused")
	private static Date Format(String timestamp) 
	{
		DateFormat format = new SimpleDateFormat("", Locale.ENGLISH);
		try {
			return format.parse(timestamp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
