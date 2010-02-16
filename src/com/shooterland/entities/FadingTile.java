package com.shooterland.entities;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.shooterland.SL;
import com.shooterland.enums.*;

public class FadingTile 
{
	private Tile _tile;
	private Paint _paint;
	private float _x, _y;
	private float _alpha;
	
	public FadingTile(Tile tile, float x, float y)
	{
		_tile = tile;
		_x = x;
		_y = y;
		_paint = new Paint();
		_paint.setARGB(255, 255, 255, 255);
		_alpha = 255.0f;
	}
	
	public boolean doneFading()
	{
		return _alpha == 0.0f;
	}
	
	public void update(float dt)
	{
		_alpha -= 400.0f * dt;
		if (_alpha < 0.0f)
			_alpha = 0.0f;
		
		_paint.setAlpha((int)_alpha);
	}
	
	public void draw(Canvas canvas, float dt)
	{
		canvas.drawBitmap(_tile.getBitmap(), _x, _y, _paint);
	}
}
