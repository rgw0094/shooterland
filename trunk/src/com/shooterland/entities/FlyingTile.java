package com.shooterland.entities;

import android.graphics.Canvas;

import com.shooterland.SL;
import com.shooterland.enums.*;
import com.shooterland.framework.Utils;

public class FlyingTile 
{
	private Tile _tile;
	private int _targetRow, _targetCol;
	private float _x, _y, _targetX, _targetY;
	private float _speed;
	
	public FlyingTile(Tile tile, float x, float y, float targetX, float targetY, int targetRow, int targetCol)
	{
		_tile = tile;
		_x = x;
		_y = y;
		_targetX = targetX;
		_targetY = targetY;
		_targetRow = targetRow;
		_targetCol = targetCol;
		
		if (targetX != x)
			_speed = (float)SL.GridHeight * (float)SL.GridSquareSize * 3.2f;
		else if (targetY != y)
			_speed = (float)SL.GridHeight * (float)SL.GridSquareSize * 3.2f;
	}
	
	public void draw(Canvas canvas, float dt) 
	{
		canvas.drawBitmap(_tile.getBitmap(), _x, _y, null);
	}

	public void update(float dt) 
	{
		if (_x > _targetX)
		{
			_x -= _speed * dt;
			if (_x < _targetX)
				_x = _targetX;
		}
		
		if (_y > _targetY)
		{
			_y -= _speed * dt;
			if (_y < _targetY)
				_y = _targetY;
		}
	}
	
	public float getX()
	{
		return _x;
	}
	
	public float getY()
	{
		return _y;
	}
	
	public int getTargetRow()
	{
		return _targetRow;
	}
	
	public int getTargetCol()
	{
		return _targetCol;
	}
	
	public Tile getTile()
	{
		return _tile;
	}
	
	public boolean reachedTarget()
	{
		return _x == _targetX && _y == _targetY;
	}

}
