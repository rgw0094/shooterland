package com.shooterland.entities;

import com.shooterland.framework.SL;
import com.shooterland.states.GameState;

import android.graphics.Canvas;

public class Store 
{
	public boolean IsShowing;
	private GameState _gameState;
	
	public Store(GameState gameState)
	{
		_gameState = gameState;
	}
	
	public void draw(Canvas canvas)
	{
		if (!IsShowing)
			return;
		
		canvas.drawBitmap(SL.Graphics.StoreBackground, (float)SL.GameAreaX + (float)SL.GameAreaWidth * 0.687f, (float)SL.GameAreaHeight * 0.21f, null);
	}
	
	public void update(float dt)
	{
		if (!IsShowing)
			return;
		
		if (SL.Input.isBackClicked())
		{
			IsShowing = false;
		}
	}
}
