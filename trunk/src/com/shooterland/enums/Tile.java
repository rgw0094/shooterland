package com.shooterland.enums;

import com.shooterland.SL;
import com.shooterland.framework.Utils;

import android.graphics.Bitmap;

public enum Tile 
{
	Empty (-1),
	Thingie1 (0),
	Thingie2 (1),
	Thingie3 (2),
	Thingie4 (3),
	Thingie5 (4),
	Thingie6 (5),
	Baddie1 (6),
	Baddie2 (7),
	Baddle3 (8),
	Baddie4 (9),
	Baddie5 (10);
	
	private int _id;
	
	Tile(int id)
	{
		_id = id;
	}
	
	public int getId()
	{
		return _id;
	}
	
	public Bitmap getBitmap()
	{
		try
		{
			return SL.GraphicsManager.Tiles[SL.SessionManager.World - 1][_id]; 
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			return SL.GraphicsManager.RoscoeDown1;
		}
	}
	
	public boolean isEmpty()
	{
		return this == Tile.Empty; 
	}
	
	public boolean isThingie()
	{
		return _id >= Thingie1.getId() && _id <= Thingie6.getId();
	}
	
	public boolean isBaddie()
	{
		return _id >= Baddie1.getId() && _id <= Baddie5.getId();
	}
	
	public static Tile randomThingie()
	{
		switch (Utils.randomInt(0, 5))
		{
			case 0:
				return Thingie1;
			case 1:
				return Thingie2;
			case 2:
				return Thingie3;
			case 3:
				return Thingie4;
			case 4:
				return Thingie5;
			case 5:
				return Thingie6;
			default:
				return null;
		}
	}
}
