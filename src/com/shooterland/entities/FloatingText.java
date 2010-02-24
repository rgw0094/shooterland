package com.shooterland.entities;

import java.util.ArrayList;

import com.shooterland.SL;
import com.shooterland.framework.Utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class FloatingText 
{	
	private ArrayList<Bitmap> _bitmaps = new ArrayList<Bitmap>();
	private float _centerX, _centerY; 
	
	public FloatingText(float centerX, float centerY, FloatingTextType type)
	{
		_centerX = centerX;
		_centerY = centerY;
		
		if (type == FloatingTextType.Complete)
		{
			_bitmaps.add(SL.GraphicsManager.Complete_C);
			_bitmaps.add(SL.GraphicsManager.Complete_O);
			_bitmaps.add(SL.GraphicsManager.Complete_M);
			_bitmaps.add(SL.GraphicsManager.Complete_P);
			_bitmaps.add(SL.GraphicsManager.Complete_L);
			_bitmaps.add(SL.GraphicsManager.Complete_E);
			_bitmaps.add(SL.GraphicsManager.Complete_T);
			_bitmaps.add(SL.GraphicsManager.Complete_E);
		} 
		else if (type == FloatingTextType.Pause)
		{
			
		}
	}
	
	public void draw(Canvas canvas, float dt)
	{		
		float count = 1.0f;
		float x, y;
		
		float offset = 0.0f;
		for (Bitmap bitmap : _bitmaps)
		{
			x = _centerX - ((float)(_bitmaps.size() * _bitmaps.get(0).getWidth()) * 0.5f) + count * (float)bitmap.getWidth(); 
			y = _centerY + (float)Math.sin(SL.GameTime * 1.5 + offset) * (float)bitmap.getHeight() * 0.5f;
			
			canvas.drawBitmap(bitmap, x, y, null);
			count += 1.0f;
			offset += (float)(Math.PI / 3.0);
		}
	}
	
	public enum FloatingTextType
	{
		Complete, 
		Pause,
		Defeat
	}
}
