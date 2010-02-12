package com.shooterland.framework;

import com.shooterland.SL;

public class InputManager 
{
	private boolean _mouseDown;
	private boolean _mouseDownLastFrame;
	private int _mouseX, _mouseY;
	
	public void update(float dt)
	{
		_mouseDownLastFrame = _mouseDown;
		_mouseDown = false;
	}
	
	/**
	 * @return Whether or not the left mouse button is currently down.
	 */
	public boolean isMouseDown()
	{
		return _mouseDown;
	}
	
	/**
	 * @return Whether or not the left mouse button was clicked this frame.
	 */
	public boolean isMouseClicked()
	{
		return _mouseDown && !_mouseDownLastFrame;
	}
	
	public void handleClick(float x, float y)
	{
		_mouseDown = true;
		_mouseX = (int)x;
		_mouseY = (int)y;
	}
	
	public int getMouseX()
	{
		return _mouseX;
	}
	
	public int getMouseY()
	{
		return _mouseY;
	}
}
