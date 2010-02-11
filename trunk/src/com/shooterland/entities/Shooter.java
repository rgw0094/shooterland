package com.shooterland.entities;

import android.graphics.Canvas;

import com.shooterland.Shooterland;
import com.shooterland.framework.AbstractEntity;

public class Shooter extends AbstractEntity 
{
	private float _x = 0.0f;
	private float _y = 0.0f;
	private int _graphicResourceId;
	
	public Shooter(int graphicResourceId)
	{
		_graphicResourceId = graphicResourceId;
	}
	
	public void moveTo(float x, float y)
	{
		_x = x;
		_y = y;
	}
	
	@Override
	public void draw(Canvas canvas, float dt) 
	{
		Shooterland.GraphicsManager.drawBitmap(canvas, _graphicResourceId, _x, _y);
	}

	@Override
	public void update(float dt) 
	{
		
	}

}
