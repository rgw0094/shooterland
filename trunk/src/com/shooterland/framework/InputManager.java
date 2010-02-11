package com.shooterland.framework;

public class InputManager 
{
	private boolean _mouseDown;
	private boolean _mouseDownLastFrame;
	private float _mouseX, _mouseY;
	
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
		_mouseX = x;
		_mouseY = y;
	}
	
	public float getMouseX()
	{
		return _mouseX;
	}
	
	public float getMouseY()
	{
		return _mouseY;
	}
}
