package com.shooterland.entities;

import com.shooterland.SL;
import com.shooterland.states.GameState;

import android.graphics.Canvas;
import android.graphics.Rect;

public class GameMenu 
{
	private GameState _state;
	private int _leftX;
	private int _rightX;
	private int _width;
	private int _money;
	private int _nextBottomThingie;
	private int _nextRightThingie;
	private Rect _shootButton;
	private String _levelString;
	
	public GameMenu(GameState state)
	{
		SL.SoundManager.playWorldMusic(SL.SessionManager.World);
		_state = state;
		_leftX = SL.GameAreaX + SL.ScreenHeight;
		_rightX = _leftX + (SL.GameAreaWidth - SL.ScreenHeight);
		_width = _rightX - _leftX;
		
		_shootButton = new Rect();
		_shootButton.left = _leftX + (int)((float)_width * 0.123f);
		_shootButton.right = _rightX - (int)((float)_width * 0.123f);
		_shootButton.bottom = SL.ScreenHeight - (int)((float)SL.ScreenHeight * 0.05f);
		_shootButton.top = _shootButton.bottom - (int)((float)SL.ScreenHeight * 0.14f);
		
		_levelString = "Level " + SL.SessionManager.World + " - " + SL.SessionManager.Level;
		_money = 0;
	}
	
	public void update(float dt)
	{
		if (SL.Input.isMouseClicked() && _shootButton.contains(SL.Input.getMouseX(), SL.Input.getMouseY()))
		{
			_state.onShootButtonClicked();
		}
	}
	
	public int getMoney()
	{
		return _money;
	}
	
	public void setMoney(int money)
	{
		_money = money;
	}
	
	public int getLeftX()
	{
		return _leftX;
	}
	
	public void generateNextThingies()
	{
		
	}
	
	public int getNextBottomThingie()
	{
		return _nextBottomThingie;
	}
	
	public int getNextRightThingie()
	{
		return _nextRightThingie;
	}
	
	public int getWidth()
	{
		return _width;
	}
	
	public void draw(Canvas canvas, float dt)
	{
		canvas.drawText(_levelString, _leftX + _width / 2, (int)((float)SL.ScreenHeight * 0.09), SL.GraphicsManager.LevelPaint);
		canvas.drawText(_money + "", _leftX + (int)((float)_width * 0.43f), (int)((float)SL.ScreenHeight * 0.185), SL.GraphicsManager.MoneyPaint);
	} 
}
