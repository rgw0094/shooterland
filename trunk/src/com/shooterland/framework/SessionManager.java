package com.shooterland.framework;

import com.shooterland.SL;

public class SessionManager 
{
	private boolean _levelUnlocked[][];
	
	public SessionManager()
	{
		_levelUnlocked = new boolean[SL.NumWorlds][SL.LevelsPerWorld];
		
		for (int i = 0; i < 3; i++)
		{
			_levelUnlocked[0][i] = true;
		}
	}
	
	public void load()
	{
		
	}
	
	public void save()
	{
		
	}
	
	public boolean isLevelUnlocked(int level)
	{
		return _levelUnlocked[World-1][level - 1];
	}
	
	public void unlockLevel(int level)
	{
		_levelUnlocked[World-1][level-1] = true;
	}
	
	public int Level = 1;
	public int World = 1;
}
