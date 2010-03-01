package com.shooterland.states;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.view.Menu;

import com.shooterland.SL;
import com.shooterland.entities.Button;
import com.shooterland.enums.MenuOption;
import com.shooterland.framework.*;

public class MainMenuState extends AbstractState
{	
	private ArrayList<Button> _buttons = new ArrayList<Button>();
	
	@Override
	public void enterState() 
	{
		SL.Sound.playMenuMusic();
				
		_buttons.add(new Button(SL.Graphics.MenuButtonForward, "Play", (float)SL.ScreenWidth * 0.85f, (float)SL.ScreenHeight * 0.9f));
		_buttons.add(new Button(SL.Graphics.MenuButtonBack, "Quit", (float)SL.ScreenWidth * 0.15f, (float)SL.ScreenHeight * 0.9f));
		_buttons.add(new Button(SL.Graphics.MenuButtonRound, "Clear Data", SL.ScreenCenterX, (float)SL.ScreenHeight * 0.9f));
	}

	@Override
	public void leaveState() 
	{
	}

	@Override
	public void update(float dt) 
	{
		for (Button button : _buttons)
		{
			if (button.isClicked())
			{
				if (button.getText() == "Quit")
				{
					System.exit(0);
				}
				else if (button.getText() == "Play")
				{
					SL.enterState(new OverworldState());
				} 
				else if (button.getText() == "Clear Data")
				{
					if (SL.showPrompt("Are you sure you wish to clear Shooterland application data? All saved progress will be lost"))
					{
						SL.Session.resetSaveFile();
					}
				}
				break;
			}
		}
	}

	@Override
	public void draw(Canvas canvas, float dt) 
	{
		canvas.drawBitmap(SL.Graphics.MainMenuBackground, 0, 0, null);
				
		for (Button button : _buttons)
		{
			button.draw(canvas);
		}
	}

	@Override
	public void buildMenu(Menu menu) 
	{
		MenuOption.Achievements.addToMenu(menu);
		MenuOption.ToggleSound.addToMenu(menu);
		MenuOption.Help.addToMenu(menu);
	}
}
