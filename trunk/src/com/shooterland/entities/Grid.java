package com.shooterland.entities;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.location.Location;

import com.shooterland.Shooterland;
import com.shooterland.framework.AbstractEntity;

public class Grid extends AbstractEntity
{
	private int _width;
	private int _height;
	private Rect _pixelBounds;
	private int _squareSize;
	private int[][] _grid;

	public Grid()
	{
		_width = 11;
		_height = 11;
		_squareSize = (Shooterland.ScreenHeight - 50) / _height;
		_grid = new int[_width][_height];
		
		_pixelBounds = new Rect();
		_pixelBounds.top = 5;
		_pixelBounds.left = 100;
		_pixelBounds.bottom = _pixelBounds.top + _height * _squareSize;
		_pixelBounds.right = _pixelBounds.left + _width * _squareSize;
	}
	
	/**
	 * Gets the grid coordinates in which the given pixel point lies.
	 */
	public Point getGridSquare(float pixelX, float pixelY)
	{
		Point p = new Point();
		p.x =  ((int)pixelX - _pixelBounds.left) / _squareSize;
		p.y = ((int)pixelY - _pixelBounds.top) / _squareSize;
		return p;
	}
	
	public boolean isInBounds(float pixelX, float pixelY)
	{
		return _pixelBounds.contains((int)pixelX, (int)pixelY);
	}
	
	public Rect getPixelBounds()
	{
		return _pixelBounds;
	}
	
	public int getSquareSize()
	{
		return _squareSize;
	}
	
	@Override
	public void draw(Canvas canvas, float dt) 
	{
		for (int i = 0; i <= _width; i++)
		{
			//Vertical lines
			int x = _pixelBounds.left + i*_squareSize;
			canvas.drawLine(x, _pixelBounds.top, x, _pixelBounds.bottom, Shooterland.GraphicsManager.BlackPaint);
			
			for (int j = 0; j <= _height; j++)
			{
				//Horizontal lines
				int y = _pixelBounds.top + j * _squareSize;
				canvas.drawLine(_pixelBounds.left, y, _pixelBounds.right, y, Shooterland.GraphicsManager.BlackPaint);
			}
		}
	}

	@Override
	public void update(float dt) 
	{
		
	}
	
}
