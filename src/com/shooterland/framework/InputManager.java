package com.shooterland.framework;

import com.shooterland.SL;

public class InputManager 
{
	private boolean _mouseDown;
	private boolean _mouseDownLastFrame;
	private boolean _backDown;
	private boolean _backDownLastFrame;
	private int _mouseX, _mouseY;
	
	public void update(float dt)
	{
		_mouseDownLastFrame = _mouseDown;
		_mouseDown = false;
		_backDownLastFrame = _backDown;
		_backDown = false;
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
	
	/**
	 * @return Whether or not the back button was clicked this frame.
	 */
	public boolean isBackClicked()
	{
		return _backDown && !_backDownLastFrame;
	}
	
	public void handleBackButton()
	{
		_backDown = true;
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
