package com.shooterland.states;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.shooterland.SL;
import com.shooterland.framework.*;

public class OverworldState extends AbstractState
{	
	private float _timeEntered;
	
	public OverworldState()
	{
		_timeEntered = SL.GameTime;
	}
	
	@Override
	public void enterState() 
	{
		
	}

	@Override
	public void leaveState() 
	{
		
	}

	@Override
	public void update(float dt) 
	{
		if (Utils.timeSince(_timeEntered) > 0.5 && SL.Input.isMouseClicked())
		{
			SL.enterState(new GameState());
		}
	}

	@Override
	public void draw(Canvas canvas, float dt) 
	{
		canvas.drawRect(new Rect(0, 0, SL.ScreenWidth, SL.ScreenHeight), SL.GraphicsManager.BlackPaint);
		
		int size = 15;

		for (int x = 0; x <= SL.ScreenWidth; x += size)
			canvas.drawLine(x, 0, x, SL.ScreenHeight, SL.GraphicsManager.DarkGreenPaint);
		for (int y = 0; y <= SL.ScreenHeight; y += size)
			canvas.drawLine(0, y, SL.ScreenWidth, y, SL.GraphicsManager.DarkGreenPaint);
		
		canvas.drawBitmap(SL.GraphicsManager.WorldMapBackground, SL.GameAreaX, 0, null);
	}

	@Override
	public void pause() 
	{
		//No need to pause the overworld	
	}

	@Override
	public void resume() 
	{
		//No need to pause the overworld	
	}
}
