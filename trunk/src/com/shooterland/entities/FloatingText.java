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
		float width = _bitmaps.get(0).getWidth();		
		float totalWidth = _bitmaps.size() * width;
		float count = 1.0f;
		float x, y;
		
		for (Bitmap bitmap : _bitmaps)
		{
			x = _centerX - (totalWidth * 0.5f) + count * width; 
			y = _centerY + (float)Math.sin(SL.GameTime);
			
			canvas.drawBitmap(bitmap, x, y, null);
			count += 1.0f;;
		}
	}
	
	public enum FloatingTextType
	{
		Complete, 
		Pause,
		Defeat
	}
	
	private class Letter
	{
		public float X, Y;
		Bitmap Bitmap;
		
		public Letter(float x, float y, Bitmap bitmap)
		{
			X = x;
			Y = y;
			Bitmap = bitmap;
		}
	}
}
