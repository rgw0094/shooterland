package com.shooterland.enums;

import com.shooterland.SL;
import com.shooterland.framework.Utils;

import android.graphics.Bitmap;

public enum Tile 
{
	Empty (0),
	Thingie1 (1),
	Thingie2 (2),
	Thingie3 (3),
	Thingie4 (4),
	Thingie5 (5),
	Baddie1 (6),
	Baddie2 (7),
	Baddle3 (8),
	Baddie4 (9),
	Baddie5 (10),
	Block (11),
	BaddieBlock1 (12),
	BaddieBlock2 (13),
	BaddieBlock3 (14),
	BaddieBlock4 (15),
	BaddieBlock5 (16),
	CrackedBlock (17);
	
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
			return SL.Graphics.Tiles[SL.Session.World - 1][_id - 1]; 
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			return SL.Graphics.RoscoeDown1;
		}
	}
	
	public boolean isEmpty()
	{
		return this == Tile.Empty; 
	}
	
	public boolean isThingie()
	{
		return _id >= Thingie1.getId() && _id <= Thingie5.getId();
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
			default:
				return null;
		}
	}
}
