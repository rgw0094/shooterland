package com.shooterland.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.shooterland.SL;
import com.shooterland.enums.*;

public class Shooter 
{
	private float _x = 0.0f;
	private float _y = 0.0f;
	private int _gridCoord = 0;
	private Bitmap _bitmap;
	private Tile _tile;
	private ShooterType _shooterType;
	private Grid _grid;
	
	public Shooter(Grid grid, ShooterType shooterType)
	{
		_grid = grid;
		_tile = Tile.Empty;
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
	
	public boolean canShoot()
	{
		if (_shooterType == ShooterType.SingleRight)
		{
			return _grid.getTile(SL.GridWidth - 1, _gridCoord).isEmpty();
		}
		else if (_shooterType == ShooterType.SingleBottom)
		{
			return _grid.getTile(_gridCoord, SL.GridHeight - 1).isEmpty();
		}
		return false;
	}
	
	public ShooterType getShooterType()
	{
		return _shooterType;
	}
	
	public Tile getTile()
	{
		return _tile;
	}
	
	public void setTile(Tile tile)
	{
		_tile = tile;
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
	
	public void draw(Canvas canvas, float dt) 
	{
		canvas.drawBitmap(_bitmap, _x, _y, null);
		
		if (!_tile.isEmpty())
		{
			canvas.drawBitmap(_tile.getBitmap(), _x, _y, null);
		}
	}

	public void update(float dt) 
	{
		
	}
}
