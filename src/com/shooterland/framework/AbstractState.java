package com.shooterland.framework;

import android.graphics.Canvas;

public abstract class AbstractState 
{
	public abstract void update(float dt);
	public abstract void draw(Canvas canvas, float dt);
	public abstract void enterState();
	public abstract void leaveState();	
	public abstract void pause();
	public abstract void resume();
}
