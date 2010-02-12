package com.shooterland.entities;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.location.Location;

import com.shooterland.SL;
import com.shooterland.framework.AbstractEntity;

public class Grid extends AbstractEntity
{
	private Rect _pixelBounds;
	private int[][] _grid;

	public Grid()
	{
		_grid = new int[SL.GridWidth][SL.GridHeight];
		reset();
		
		_pixelBounds = new Rect();
		_pixelBounds.top = 5;
		_pixelBounds.left = 100;
		_pixelBounds.bottom = _pixelBounds.top + SL.GridHeight * SL.GridSquareSize;
		_pixelBounds.right = _pixelBounds.left + SL.GridWidth * SL.GridSquareSize;
	}
	
	/**
	 * Gets the grid coordinates in which the given pixel point lies.
	 */
	public Point getGridSquare(float pixelX, float pixelY)
	{
		Point p = new Point();
		p.x =  ((int)pixelX - _pixelBounds.left) / SL.GridSquareSize;
		p.y = ((int)pixelY - _pixelBounds.top) / SL.GridSquareSize;
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
	
	public void addThingie(int gridX, int gridY, int thingie)
	{
		_grid[gridX][gridY] = thingie;
	}
		
	/**
	 * Gets the left-most column with an empty space in the given row. Returns -1
	 * if the row is full.
	 */
	public int getFirstOpenColumn(int row)
	{
		for (int i = SL.GridWidth - 1; i >= 0; i--)
		{
			//Check if row is full
			if (_grid[i][row] != -1)
				return -1;
			
			//We reached the end of the row
			if (i == 0)
				return 0;
			
			if (_grid[i][row] == -1 && _grid[i-1][row] != -1)
				return i;
		}
		
		return -1; //impossible
	}
	
	/**
	 * Gets the top-most row with an empty space in the given column. Returns -1
	 * if the column is full.
	 */
	public int getFirstOpenRow(int column)
	{
		for (int i = SL.GridHeight - 1; i >= 0; i--)
		{
			//Check if column is full
			if (_grid[column][i] != -1)
				return -1;
			
			//We've reached the top of the column
			if (i == 0)
				return 0;
			
			if (_grid[column][i] == -1 && _grid[column][i-1] != -1)
				return i;
		}
		
		return -1; //impossible
	}
	
	@Override
	public void draw(Canvas canvas, float dt) 
	{
		for (int i = 0; i <= SL.GridWidth; i++)
		{
			//Vertical lines
			int x = _pixelBounds.left + i*SL.GridSquareSize;
			canvas.drawLine(x, _pixelBounds.top, x, _pixelBounds.bottom, SL.GraphicsManager.BlackPaint);
			
			for (int j = 0; j <= SL.GridHeight; j++)
			{
				//Horizontal lines
				int y = _pixelBounds.top + j * SL.GridSquareSize;
				canvas.drawLine(_pixelBounds.left, y, _pixelBounds.right, y, SL.GraphicsManager.BlackPaint);
				
				if (i < SL.GridWidth && j < SL.GridHeight && _grid[i][j] >= 0)
					canvas.drawBitmap(SL.GraphicsManager.Thingies[SL.SessionManager.CurrentLevel - 1][_grid[i][j]], x, y, null);
			}
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
	
	private void reset()
	{
		for (int i = 0; i < SL.GridWidth; i++)
		{
			for (int j = 0; j < SL.GridHeight; j++)
			{
				_grid[i][j] = -1;
			}
		}
	}
	
}
