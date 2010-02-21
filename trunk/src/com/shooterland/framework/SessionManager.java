package com.shooterland.framework;

import com.shooterland.SL;

public class SessionManager 
{
	public SessionManager()
	{
		LevelComplete = new boolean[SL.NumWorlds][SL.LevelsPerWorld];
	}
	
	public void loadSavedData()
	{
		
	}
	
	public int Level = 1;
	public int World = 1;
	
	public boolean LevelComplete[][];
}
