package com.shooterland;

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

/**
 * Main game singleton. Except its just a static class and not a singleton so that
 * we don't have to type .Instance everywhere.
 */
public class Shooterland 
{	
	public static Context Context;
	public static Resources Resources;
	public static GraphicsManager GraphicsManager;
	public static AbstractState CurrentState;
	public static InputManager Input;
	
	public static int ScreenWidth;
	public static int ScreenHeight;
		
	public static void  init(Context context)
	{
		Context = context;
		Resources = Context.getResources();
		GraphicsManager = new GraphicsManager();
		Input = new InputManager();
		
		ScreenWidth = 854;
		ScreenHeight = 480;
		
		enterState(new GameState());
	}
	
	public static void update(float dt)
	{
		CurrentState.update(dt);
	}
	
	public static void draw(Canvas canvas, float dt)
	{		
		CurrentState.draw(canvas, dt);
	}
	
	private static void enterState(AbstractState newState)
	{
		if (CurrentState != null)
			CurrentState.leaveState();
		
		CurrentState = newState;
		CurrentState.enterState();
	}
	
}
