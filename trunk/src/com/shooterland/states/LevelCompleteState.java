package com.shooterland.states;

import android.graphics.Canvas;
import android.view.Menu;

import com.shooterland.SL;
import com.shooterland.entities.FloatingText;
import com.shooterland.enums.MenuItem;
import com.shooterland.framework.AbstractState;
import com.shooterland.framework.Utils;

public class LevelCompleteState extends AbstractState 
{
	private float _timeEnteredState;
	private FloatingText _floatingText;
	
	public LevelCompleteState()
	{
		_floatingText = new FloatingText((float)SL.GameAreaX * 0.3f, (float)SL.GameAreaHeight * 0.3f, FloatingText.FloatingTextType.Complete);
	}
	
	@Override
	public void draw(Canvas canvas, float dt) 
	{
		canvas.drawBitmap(SL.GraphicsManager.WorldBackgrounds[SL.SessionManager.World - 1], SL.GameAreaX, 0, null);
		Utils.fillExtraSideSpace(canvas);
		
		_floatingText.draw(canvas, dt);
	}

	@Override
	public void enterState() 
	{	
		_timeEnteredState = SL.GameTime;
	}

	@Override
	public void leaveState() 
	{		
	}

	@Override
	public void update(float dt) 
	{
		if (SL.Input.isBackClicked())
		{
			SL.enterState(new OverworldState());
			return;
		}
		
		if (Utils.timeSince(_timeEnteredState) > 0.4 && SL.Input.isMouseClicked())
		{
			SL.enterState(new OverworldState());
		}
	}

	@Override
	public void buildMenu(Menu menu) 
	{
		MenuItem.Achievements.addToMenu(menu);
		MenuItem.ToggleSound.addToMenu(menu);
		MenuItem.Help.addToMenu(menu);	
	}
}
