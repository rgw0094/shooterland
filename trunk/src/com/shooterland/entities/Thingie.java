package com.shooterland.entities;

import android.graphics.Canvas;

import com.shooterland.SL;
import com.shooterland.framework.AbstractEntity;
import com.shooterland.framework.Utils;

public class Thingie extends AbstractEntity 
{
	private boolean _isAlive;
	private int _id, _targetRow, _targetCol;
	private float _x, _y, _dx, _dy;
	private float _speed = 600.0f;
	private float _createTime, _finishTime;
	
	public Thingie(int id, float x, float y, float targetX, float targetY, int targetRow, int targetCol)
	{
		_id = id;
		_x = x;
		_y = y;
		_targetRow = targetRow;
		_targetCol = targetCol;
		
		if (targetX > x)
			_dx = _speed;
		else if (targetX < x)
			_dx = -_speed;
		
		if (targetY > y)
			_dy = _speed;
		if (targetY < y)
			_dy = -_speed;
		
		_createTime = SL.GameTime;
		_finishTime = _createTime + Utils.distance(x, y, targetX, targetY) / _speed;
	}
	
	@Override
	public void draw(Canvas canvas, float dt) 
	{
		canvas.drawBitmap(SL.GraphicsManager.Thingies[SL.SessionManager.CurrentLevel - 1][_id], _x, _y, null);
	}

	@Override
	public void update(float dt) 
	{
		_x += _dx * dt;
		_y += _dy * dt;
	}

	@Override
	public boolean isAlive() 
	{
		return _isAlive;
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
	
	public int getId()
	{
		return _id;
	}
	
	public boolean reachedTarget()
	{
		return SL.GameTime > _finishTime;
	}

}
