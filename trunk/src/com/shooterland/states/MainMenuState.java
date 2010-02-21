package com.shooterland.states;

import android.graphics.Canvas;
import android.view.Menu;

import com.shooterland.SL;
import com.shooterland.enums.MenuItem;
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

	@Override
	public void buildMenu(Menu menu) 
	{
		MenuItem.Achievements.addToMenu(menu);
		MenuItem.ToggleSound.addToMenu(menu);
		MenuItem.Help.addToMenu(menu);
	}
}
