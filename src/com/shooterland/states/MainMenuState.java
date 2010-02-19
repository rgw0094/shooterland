package com.shooterland.states;

import android.graphics.Canvas;

import com.shooterland.SL;
import com.shooterland.framework.*;

public class MainMenuState extends AbstractState
{
	@Override
	public void enterState() 
	{
		SL.SoundManager.playMenuMusic();
	}

	@Override
	public void leaveState() 
	{
		
	}

	@Override
	public void update(float dt) 
	{
		if (SL.Input.isMouseClicked())
		{
			SL.enterState(new OverworldState());
		}
	}

	@Override
	public void draw(Canvas canvas, float dt) 
	{
		canvas.drawBitmap(SL.GraphicsManager.MainMenuBackground, 0, 0, null);
	}
}
