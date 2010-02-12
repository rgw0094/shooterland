package com.shooterland.framework;

import java.util.Date;
import java.util.Random;

import com.shooterland.SL;

import android.text.format.Time;

public class Utils 
{
	private static Random _random = new Random();
	
	public static long currentMillis()
	{
		Date date = new Date();
		return date.getTime();
	}
	
	public static float timeSince(float time)
	{
		return SL.GameTime - time;
	}
	
	public static float realTimeSince(float time)
	{
		return SL.RealTime - time;
	}
	
	public static int randomInt(int min, int max)
	{
		return _random.nextInt(max - min) + min;
	}
	
	public static int distance(float x1, float y1, float x2, float y2)
	{
		if (x1 == x2) return (int) Math.abs(y1 - y2);
		if (y1 == y2) return (int) Math.abs(x1 - x2);
		return (int) Math.sqrt(((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1)));
	}
	
	public static int distance(int x1, int y1, int x2, int y2) 
	{
		if (x1 == x2) return Math.abs(y1 - y2);
		if (y1 == y2) return Math.abs(x1 - x2);
		return (int) Math.sqrt(((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1)));
	}

}
