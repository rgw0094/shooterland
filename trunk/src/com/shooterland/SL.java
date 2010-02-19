package com.shooterland;

import com.shooterland.ShooterlandActivity.ShooterlandView;
import com.shooterland.entities.*;
import com.shooterland.framework.*;
import com.shooterland.states.*;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Main game singleton. Except its just a static class and not a singleton so that
 * we don't have to type .Instance everywhere.
 */
public class SL 
{	
	public static ShooterlandActivity Activity;
	public static Context Context;
	public static Resources Resources;
	public static GraphicsManager GraphicsManager;
	public static AbstractState CurrentState;
	public static InputManager Input;
	public static SessionManager SessionManager;
	public static SoundManager SoundManager;
		
	public static float GameTime;
	public static float RealTime;
	public static boolean Initialized;
	
	//Constants that get initialized based on screen size
	public static int ScreenWidth;
	public static int ScreenHeight;
	public static int ScreenCenterX;
	public static int ScreenCenterY;
	public static int GridWidth;
	public static int GridHeight;
	public static int GridSquareSize;
	public static int GameAreaWidth;
	public static int GameAreaHeight;
	public static int GameAreaX;
		
	private static AbstractState _nextState;
	
	public static void  init(ShooterlandActivity activity)
	{	
		Activity = activity;
		Context = activity.getApplicationContext();
		Resources = Context.getResources();
		
		if (Initialized)
			return;
		
		GraphicsManager = new GraphicsManager();
		Input = new InputManager();
		SessionManager = new SessionManager();
		SoundManager = new SoundManager();
		RealTime = GameTime = 0.0f;
		Initialized = true;

		enterState(new MainMenuState());
	}
	
	public static void setScreenSize(int width, int height)
	{
		ScreenWidth = width;
		ScreenHeight = height;
		ScreenCenterX = ScreenWidth / 2;
		ScreenCenterY = ScreenHeight / 2;
		GridWidth = 11;
		GridHeight = 11;
		GridSquareSize = (int)(((float)ScreenHeight * 0.98f) / ((float)GridHeight + 1.0f));
		
		GameAreaHeight = ScreenHeight;
		GameAreaWidth = (int)((float)ScreenHeight * 1.5f);
		GameAreaX = (int)((float)(ScreenWidth - GameAreaWidth) / 2.0f);
	}
	
	public static void update(float dt)
	{		
		//If there is a state change queued up, do it now
		if (_nextState != null)
		{
			if (CurrentState != null)
				CurrentState.leaveState();
			
			CurrentState = _nextState;
			CurrentState.enterState();
			_nextState = null;
		}
		
		GameTime += dt;
		RealTime += dt;
		
		CurrentState.update(dt);
		Input.update(dt);
		SoundManager.update(dt);
	}
	
	public static void draw(Canvas canvas, float dt)
	{			
		CurrentState.draw(canvas, dt);
	}
	
	public static void pauseExecution()
	{
		SoundManager.pauseMusic();
	}
	
	public static void resumeExecution()
	{
		SoundManager.resumeMusic();
	}
	
	public static void enterState(AbstractState newState)
	{
		_nextState = newState;
	}
	
}
