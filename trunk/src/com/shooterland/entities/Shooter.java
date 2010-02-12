package com.shooterland.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.shooterland.SL;
import com.shooterland.framework.AbstractEntity;
import com.shooterland.enums.*;

public class Shooter extends AbstractEntity 
{
	private float _x = 0.0f;
	private float _y = 0.0f;
	private int _gridCoord = 0;
	private Bitmap _bitmap;
	private int _thingie;
	private ShooterType _shooterType;
	
	public Shooter(ShooterType shooterType)
	{
		_thingie = -1;
		_shooterType = shooterType;
		
		if (_shooterType == ShooterType.SingleBottom)
			_bitmap = SL.GraphicsManager.BottomShooter;
		else if (_shooterType == ShooterType.SingleRight)
			_bitmap = SL.GraphicsManager.RightShooter;
	}
	
	public void moveTo(int gridCoord, float x, float y)
	{
		_gridCoord = gridCoord;
		_x = x;
		_y = y;
	}
	
	public int getThingie()
	{
		return _thingie;
	}
	
	public void setThingie(int thingie)
	{
		_thingie = thingie; 
	}
	
	public float getX()
	{
		return _x;
	}
	
	public float getY()
	{
		return _y;
	}
	
	public int getGridCoord()
	{
		return _gridCoord;
	}
	
	@Override
	public void draw(Canvas canvas, float dt) 
	{
		canvas.drawBitmap(_bitmap, _x, _y, null);
		
		if (_thingie != -1)
		{
			canvas.drawBitmap(SL.GraphicsManager.Thingies[SL.SessionManager.CurrentLevel - 1][_thingie], _x, _y, null);
		}
	}

	@Override
	public void update(float dt) 
	{
		
	}

	@Override
	public boolean isAlive() 
	{
		return true;
	}

}
