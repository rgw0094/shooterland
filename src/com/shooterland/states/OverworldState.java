package com.shooterland.states;

import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Paint.Align;
import android.view.Menu;
import android.widget.Toast;

import com.shooterland.SL;
import com.shooterland.enums.MenuOption;
import com.shooterland.framework.*;

public class OverworldState extends AbstractState
{	
	private float _timeEntered;
	private Paint _connectorPaint;
	private Paint _levelNumberPaint;
	private Paint _selectedLevelPaint;
	private Point[] _levelPoints;
	private float _levelPointRadius;
	private String _selectedLevelString;
	private Rect _playLevelRect;
	private Rect _menuRect;
	
	public OverworldState()
	{
		_timeEntered = SL.GameTime;
		_levelPointRadius =  (float)SL.GameAreaHeight * 0.03f;
		selectLevel(1);
		
		_connectorPaint = new Paint();
		_connectorPaint.setARGB(255, 86, 53, 13);
		_connectorPaint.setAntiAlias(true);
		_connectorPaint.setStrokeWidth(2.0f);
		
		_levelNumberPaint = new Paint();
		_levelNumberPaint.setARGB(255, 255, 255, 255);
		_levelNumberPaint.setAntiAlias(true);
		_levelNumberPaint.setTextAlign(Align.CENTER);
		
		_selectedLevelPaint = new Paint();
		_selectedLevelPaint.setARGB(255, 255, 255, 255);
		_selectedLevelPaint.setAntiAlias(true);
		_selectedLevelPaint.setTextAlign(Align.CENTER);
		_selectedLevelPaint.setTextSize((float)SL.GameAreaHeight * 0.06f);
		
		_playLevelRect = Utils.BuildCollisionRect(0.8f, 0.76f, 0.105f, 0.16f);
		_menuRect = Utils.BuildCollisionRect(0.105f, 0.76f, 0.105f, 0.16f);
		
		initLevelPoints();
	}
	
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
		if (SL.Input.isMouseClicked() && Utils.timeSince(_timeEntered) > 0.3f)
		{
			if (_menuRect.contains(SL.Input.getMouseX(), SL.Input.getMouseY()))
			{
				SL.enterState(new MainMenuState());
				return;
			}
			else if (_playLevelRect.contains(SL.Input.getMouseX(), SL.Input.getMouseY()))
			{
				if (SL.SessionManager.isLevelUnlocked(SL.SessionManager.Level))
				{
					SL.enterState(new GameState());
					return;
				}			
				else
				{
					SL.showShortNotification("Level not unlocked yet.");
				}
			}
			else
			{
				for (int i = 0; i < _levelPoints.length; i++)
				{
					if (Utils.distance(_levelPoints[i].x, _levelPoints[i].y, SL.Input.getMouseX(), SL.Input.getMouseY()) <= (_levelPointRadius * 2.0f))
					{
						selectLevel(i + 1);
					}
				}
			}
		}
		
		if (SL.Input.isBackClicked())
		{
			SL.enterState(new MainMenuState());
			return;
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
		
		//Background and title
		canvas.drawBitmap(SL.GraphicsManager.WorldMapBackground, SL.GameAreaX, 0, null);
		
		Bitmap title = SL.GraphicsManager.WorldTitles[SL.SessionManager.World-1];
		canvas.drawBitmap(title, SL.ScreenCenterX - ((float)title.getWidth() * 0.5f), (float)SL.GameAreaHeight * 0.14f, null);
		
		//Draw level points
		Point previousPoint = null;
		
		for (int i = 0; i < SL.LevelsPerWorld; i++)
		{
			if (previousPoint != null)
				canvas.drawLine(previousPoint.x, previousPoint.y, _levelPoints[i].x, _levelPoints[i].y, _connectorPaint);
			previousPoint = _levelPoints[i];
		}
		
		for (int i = 0; i < SL.LevelsPerWorld; i++)
		{
			canvas.drawCircle(_levelPoints[i].x, _levelPoints[i].y, _levelPointRadius, _connectorPaint);
			if (SL.SessionManager.isLevelUnlocked(i+1))
				canvas.drawCircle(_levelPoints[i].x, _levelPoints[i].y, _levelPointRadius * 0.8f, SL.GraphicsManager.YellowPaint);
		}
		
		for (int i = 0; i < SL.LevelsPerWorld; i++)
		{	
			if (SL.SessionManager.isLevelUnlocked(i+1))
				_levelNumberPaint.setARGB(255,0, 0, 0);
			else
				_levelNumberPaint.setARGB(255, 255, 255, 255);
			
			canvas.drawText((i + 1) + "", _levelPoints[i].x, _levelPoints[i].y + _levelPointRadius * 0.5f, _levelNumberPaint);
		}
		
		//Draw level selection
		canvas.drawBitmap(SL.GraphicsManager.BottomShooter, _levelPoints[SL.SessionManager.Level - 1].x - ((float)SL.GraphicsManager.BottomShooter.getWidth() * 0.5f), 
				_levelPoints[SL.SessionManager.Level - 1].y - ((float)SL.GraphicsManager.BottomShooter.getHeight() * 0.5f), null);
		canvas.drawText(_selectedLevelString, SL.GameAreaX + (float)SL.GameAreaWidth * 0.2f, SL.GameAreaHeight * 0.69f, _selectedLevelPaint);
	}

	@Override
	public void buildMenu(Menu menu) 
	{
		MenuOption.Achievements.addToMenu(menu);
		MenuOption.ToggleSound.addToMenu(menu);
		MenuOption.Help.addToMenu(menu);	
	}
	
	private void selectLevel(int level)
	{
		SL.SessionManager.Level = level;
		_selectedLevelString = "Level " + SL.SessionManager.World + "-" + SL.SessionManager.Level;
	}
	
	private void initLevelPoints()
	{
		_levelPoints = new Point[SL.LevelsPerWorld];
		
		float angleDt = (float)Math.PI / 2.0f;
		float angleAt = 0.08f;
		float angle = 0.0f;
		float x, y;
		for (int i = 0; i < SL.LevelsPerWorld; i++)
		{
			angleDt = Math.max(0.35f, angleDt - angleAt);
			
			if (i == 1)
				angle += Math.PI * 1.5;
			else if (i < 3)
				angle += Math.PI / 2.5f;
			else if (i < 7)
				angle += Math.PI / 4.0f;
			else 
				angle += angleDt;
			
			x = (float)SL.ScreenCenterX + (angle * (float)SL.GameAreaWidth * 0.0208333f) * (float)Math.cos(angle);
			y = (float)SL.GameAreaHeight * 0.45f + (angle * (float)SL.GameAreaHeight * 0.011875f) * (float)Math.sin(angle);
			
			if (i == 0)
			{
				x -= (float)SL.GameAreaWidth * 0.03f;
				y -= (float)SL.GameAreaHeight * 0.05f;
			}
				
			_levelPoints[SL.LevelsPerWorld - 1 - i] = new Point((int)x, (int)y);
		}
	}
}
