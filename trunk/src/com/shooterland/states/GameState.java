package com.shooterland.states;

import java.util.ArrayList;

import android.R.raw;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;

import com.shooterland.*;
import com.shooterland.entities.*;
import com.shooterland.framework.*;
import com.shooterland.enums.*;

public class GameState extends AbstractState
{
	private Grid _grid;
	private Shooter _bottomShooter;
	private Shooter _rightShooter;
	private Rect _shootButton;
	private ArrayList<Thingie> _thingies = new ArrayList<Thingie>();
	//private MediaPlayer _mediaPlayer;
	
	@Override
	public void enterState() 
	{
		_grid = new Grid();
		_bottomShooter = new Shooter(ShooterType.SingleBottom);
		_rightShooter = new Shooter(ShooterType.SingleRight);
		moveBottomShooter(4);
		moveRightShooter(4);
		replenishShooters();
		
		_shootButton = new Rect();
		_shootButton.left = _grid.getPixelBounds().right + 100;
		_shootButton.right = _shootButton.left + 180;
		_shootButton.bottom = _grid.getPixelBounds().top + 200;
		_shootButton.top = _shootButton.bottom - 100;
		
		//_mediaPlayer = MediaPlayer.create(SL.Context, R.raw.world1);
		//_mediaPlayer.start();
	}

	@Override
	public void leaveState() 
	{
		//_mediaPlayer.stop();
	}

	@Override
	public void update(float dt) 
	{
		for (Thingie t : _thingies)
		{
			t.update(dt);
			
			if (t.reachedTarget())
			{
				_grid.addThingie(t.getTargetCol(), t.getTargetRow(), t.getId());
				_thingies.remove(t);
			}
		}
		
		if (SL.Input.isMouseDown())
		{
			if (_grid.isInBounds(SL.Input.getMouseX(), SL.Input.getMouseY()))
			{
				Point p = _grid.getGridSquare(SL.Input.getMouseX(), SL.Input.getMouseY());
				moveBottomShooter(p.x);
				moveRightShooter(p.y);
			} 
		}
			
		if (SL.Input.isMouseClicked())
		{
			if (canShoot() && _shootButton.contains(SL.Input.getMouseX(), SL.Input.getMouseY()))
			{
				shoot();
			}
		}
		
		//After the shooters fired, replenish them when the thingies reach their destination
		if (_bottomShooter.getThingie() == -1 && _thingies.size() == 0)
		{
			replenishShooters();
		}
	}

	@Override
	public void draw(Canvas canvas, float dt) 
	{
		canvas.drawBitmap(SL.GraphicsManager.WorldBackgrounds[SL.SessionManager.CurrentLevel - 1], 0, 0, null);
		canvas.drawRect(_shootButton, SL.GraphicsManager.RedPaint);
		
		_grid.draw(canvas, dt);
		_bottomShooter.draw(canvas, dt);
		_rightShooter.draw(canvas, dt);
		
		for (Thingie t : _thingies)
		{
			t.draw(canvas, dt);
		}
	}
	
	private boolean canShoot()
	{
		return _bottomShooter.getThingie() != -1 && _rightShooter.getThingie() != -1;
	}
	
	private void shoot()
	{
		//Bottom shooter
		int targetCol = _bottomShooter.getGridCoord();
		int targetRow = _grid.getFirstOpenRow(targetCol);
		float targetX = _grid.getPixelBounds().left + targetCol * SL.GridSquareSize;
		float targetY = _grid.getPixelBounds().top + targetRow * SL.GridSquareSize;
		_thingies.add(new Thingie(_bottomShooter.getThingie(), _bottomShooter.getX(), _bottomShooter.getY(), targetX, targetY, targetRow, targetCol));
		_bottomShooter.setThingie(-1);
		
		//Right shooter
		targetRow = _rightShooter.getGridCoord();
		targetCol = _grid.getFirstOpenColumn(targetRow);
		targetX = _grid.getPixelBounds().left + targetCol * SL.GridSquareSize;
		targetY = _grid.getPixelBounds().top + targetRow * SL.GridSquareSize;
		_thingies.add(new Thingie(_rightShooter.getThingie(), _rightShooter.getX(), _rightShooter.getY(), targetX, targetY, targetRow, targetCol));
		_rightShooter.setThingie(-1);
	}	
	
	private void replenishShooters()
	{
		_bottomShooter.setThingie(Utils.randomInt(0, 5));
		_rightShooter.setThingie(Utils.randomInt(0, 5));
	}
	
	/**
	 * Moves the bottom shooter to the given grid x coordinate.
	 * @param gridX
	 */
	private void moveBottomShooter(int gridX)
	{
		_bottomShooter.moveTo(gridX, _grid.getPixelBounds().left + SL.GridSquareSize * gridX, _grid.getPixelBounds().bottom + 3);
	}
	
	/**
	 * Moves the right shooter to the given grid y coordinate.
	 * @param gridY
	 */
	private void moveRightShooter(int gridY)
	{
		_rightShooter.moveTo(gridY, _grid.getPixelBounds().right + 3, _grid.getPixelBounds().top + SL.GridSquareSize * gridY);
	}
}
