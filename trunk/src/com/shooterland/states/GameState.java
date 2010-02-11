package com.shooterland.states;

import android.R.raw;
import android.graphics.Canvas;
import android.graphics.Point;

import com.shooterland.*;
import com.shooterland.entities.Grid;
import com.shooterland.entities.Shooter;
import com.shooterland.framework.*;

public class GameState extends AbstractState
{
	private Grid _grid;
	private Shooter _bottomShooter;
	private Shooter _rightShooter;
	
	@Override
	public void enterState() 
	{
		_grid = new Grid();
		_bottomShooter = new Shooter(R.drawable.bottomshooter);
		_rightShooter = new Shooter(R.drawable.rightshooter);
		moveBottomShooter(4);
		moveRightShooter(4);
	}

	@Override
	public void leaveState() 
	{
	}

	@Override
	public void update(float dt) 
	{
		if (Shooterland.Input.isMouseClicked())
		{
			if (_grid.isInBounds(Shooterland.Input.getMouseX(), Shooterland.Input.getMouseY()))
			{
				Point p = _grid.getGridSquare(Shooterland.Input.getMouseX(), Shooterland.Input.getMouseY());
				moveBottomShooter(p.x);
				moveRightShooter(p.y);
			}
		}
	}

	@Override
	public void draw(Canvas canvas, float dt) 
	{
		Shooterland.GraphicsManager.drawBitmap(canvas, R.drawable.world1background, 0, 0);
		
		_grid.draw(canvas, dt);
		_bottomShooter.draw(canvas, dt);
		_rightShooter.draw(canvas, dt);
	}
	
	/**
	 * Moves the bottom shooter to the given grid x coordinate.
	 * @param gridX
	 */
	private void moveBottomShooter(int gridX)
	{
		_bottomShooter.moveTo(_grid.getPixelBounds().left + _grid.getSquareSize() * gridX, _grid.getPixelBounds().bottom + 3);
	}
	
	/**
	 * Moves the right shooter to the given grid y coordinate.
	 * @param gridY
	 */
	private void moveRightShooter(int gridY)
	{
		_rightShooter.moveTo(_grid.getPixelBounds().right + 3, _grid.getPixelBounds().top + _grid.getSquareSize() * gridY);
	}
}
