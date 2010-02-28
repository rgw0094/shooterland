package com.shooterland.framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;

import com.shooterland.SL;

public class SessionManager 
{
	private boolean _levelUnlocked[][];
	private final String FileName = "ShooterlandSaveFile";
	
	public SessionManager()
	{
		_levelUnlocked = new boolean[SL.NumWorlds][SL.LevelsPerWorld];
		reset();
	}
	
	public boolean IsLoaded;
	public int Level = 1;
	public int World = 1;
	
	public void resetSaveFile()
	{
		reset();
		save();
	}
	
	public void load()
	{
		FileInputStream fis = null;
		try
		{
			fis = SL.Context.openFileInput(FileName);
			for (int i = 0; i < SL.NumWorlds; i++)
			{
				for (int j = 0; j < SL.LevelsPerWorld; j++)
				{
					boolean levelUnlocked = (fis.read() == 1); 
					_levelUnlocked[i][j] = levelUnlocked;
				}
			}
		} 
		catch (FileNotFoundException e)
		{
			//If no save file has been created yet, create it.
			save();
		}
		catch (IOException ioe)
		{
			SL.showLongNotification(Utils.formatException(ioe));
		}
		finally
		{
			try
			{
				if (fis != null) fis.close();
			} catch (Exception e) { }
		}
		
		_levelUnlocked[0][0] = true;
	}
	
	public void save()
	{
		FileOutputStream fos = null;
		try
		{
			fos = SL.Context.openFileOutput(FileName, Context.MODE_PRIVATE);
			
			for (int i = 0; i < SL.NumWorlds; i++)
			{
				for (int j = 0; j < SL.LevelsPerWorld; j++)
				{
					byte b =_levelUnlocked[i][j] ? (byte)1 : (byte)0;
					fos.write(b);
				}
			}
		}
		catch (Exception e) 
		{ 
			SL.showLongNotification(Utils.formatException(e));
		}
		finally
		{
			try
			{
				if (fos != null) fos.close();
			} catch (Exception e) { }
		}
	}
	
	public boolean doesSaveFileExist()
	{
		try
		{
			SL.Context.openFileInput(FileName);
			return true;
		}
		catch (FileNotFoundException e) { }
		return false;
	}
	
	public boolean isLevelUnlocked(int level)
	{
		return _levelUnlocked[World-1][level - 1];
	}
	
	public void unlockLevel(int level)
	{
		_levelUnlocked[World-1][level-1] = true;
	}
	
	private void reset()
	{
		for (int i = 0; i < SL.NumWorlds; i++)
		{
			for (int j = 0; j < SL.LevelsPerWorld; j++)
			{
				_levelUnlocked[i][j] = false;
			}
		}
		
		for (int i = 0; i < 3; i++)
		{
			_levelUnlocked[0][i] = true;
		}
	}
}
