package com.shooterland.states;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

import android.R.raw;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.Menu;

import com.shooterland.*;
import com.shooterland.entities.*;
import com.shooterland.framework.*;
import com.shooterland.enums.*;

public class GameState extends AbstractState
{
	private Grid _grid;
	private GameMenu _menu;
	private Shooter _bottomShooter;
	private Shooter _rightShooter;
	private Roscoe _roscoe;
	private int _baddieCount;
	private ArrayList<FlyingTile> _flyingTiles = new ArrayList<FlyingTile>();
	private ArrayList<Point> _recentlyPlacedThingies = new ArrayList<Point>();
	private ArrayList<FadingTile> _fadingTiles = new ArrayList<FadingTile>();
	private float _timeInState = 0.0f;
	private boolean _paused;
	private Paint _blackScreenPaint;
	private FloatingText _pauseFloatingText;
	private Rect _storeButtonRect;

	@Override
	public void enterState() 
	{		
		_grid = new Grid(this);
		_menu = new GameMenu(this);
		_roscoe = new Roscoe(this, 2.0f, 5.0f, _menu.getLeftX() + (float)_menu.getWidth() * 0.1f);
		_bottomShooter = new Shooter(_grid, ShooterType.SingleBottom);
		_rightShooter = new Shooter(_grid, ShooterType.SingleRight);
		_pauseFloatingText = new FloatingText((float)SL.GameAreaWidth * 0.3f, (float)SL.GameAreaHeight * 0.3f, FloatingText.FloatingTextType.Pause);
		moveBottomShooter(4);
		moveRightShooter(4);
		replenishShooters();
		loadLevel();
		
		_blackScreenPaint = new Paint();
		_blackScreenPaint.setARGB(180, 0, 0, 0);
		_blackScreenPaint.setAntiAlias(true);
		
		_storeButtonRect = Utils.BuildCollisionRect(0.83f, 0.12f, 0.14f, 0.09f);
		
		SL.Sound.playWorldMusic(SL.Session.World);
	}

	@Override
	public void leaveState() 
	{
	}

	@Override
	public void update(float dt) 
	{
		if (SL.Input.isBackClicked())
		{
			if (SL.showPrompt("Are you sure you wish to abandon your progress on this level and return to the main menu?"))
			{
				SL.enterState(new OverworldState());
				return;
			}
		}
		
		if (_paused)
		{
		
		}
		else
		{
			_timeInState += dt;
			
			_menu.update(dt);
			_roscoe.update(dt);
			
			boolean areFlyingTiles = _flyingTiles.size() > 0;
			for (FlyingTile t : _flyingTiles)
			{
				t.update(dt);
				
				if (t.reachedTarget())
				{
					_grid.setTile(t.getTargetCol(), t.getTargetRow(), t.getTile());
					_flyingTiles.remove(t);
					
					//If this is a thingie, remember where it landed so we can check for combos
					//once all the flying thingies have landed.
					if (t.getTile().isThingie())
					{
						_recentlyPlacedThingies.add(new Point(t.getTargetCol(), t.getTargetRow()));
					}
				}
			}
			
			//When all of the thingies reach their destination, replenish the shooters
			//and check for combos.
			if (areFlyingTiles && _flyingTiles.size() == 0)
			{
				doCombos(_recentlyPlacedThingies);
				replenishShooters();
				_recentlyPlacedThingies.clear();
			}
			
			//Update fading tiles
			ArrayList<FadingTile> fadedTiles = new ArrayList<FadingTile>();
			for (FadingTile ft : _fadingTiles)
			{
				ft.update(dt);
				if (ft.doneFading())
					fadedTiles.add(ft);
			}
			for (FadingTile ft : fadedTiles)
				_fadingTiles.remove(ft);
			
			if (SL.Input.isClicked(_storeButtonRect))
			{
				SL.showStore();
			} 
			else if (SL.Input.isMouseDown() && _grid.isInBounds(SL.Input.getMouseX(), SL.Input.getMouseY()))
			{
				Point p = _grid.getGridSquare(SL.Input.getMouseX(), SL.Input.getMouseY());
				moveBottomShooter(p.x);
				moveRightShooter(p.y);
			}  
			
			if (_baddieCount == 0 && _fadingTiles.size() == 0)
			{
				SL.enterState(new LevelCompleteState());
			}
		}
	}

	@Override
	public void draw(Canvas canvas, float dt) 
	{
		canvas.drawBitmap(SL.Graphics.WorldBackgrounds[SL.Session.World - 1], SL.GameAreaX, 0, null);
		
		_menu.draw(canvas, dt);
		_roscoe.draw(canvas, dt);
		_grid.draw(canvas, dt);
		_bottomShooter.draw(canvas, dt);
		_rightShooter.draw(canvas, dt);
		
		_grid.highlightRow(canvas, _rightShooter.getGridCoord());
		_grid.highlightColumn(canvas, _bottomShooter.getGridCoord());
		
		for (FlyingTile t : _flyingTiles)
		{
			t.draw(canvas, dt);
		}
		
		for (FadingTile ft : _fadingTiles)
		{
			ft.draw(canvas, dt);
		}
		
		if (_paused)
		{
			canvas.drawRect(new Rect(SL.GameAreaX, 0, SL.GameAreaX + SL.GameAreaWidth, SL.GameAreaHeight), _blackScreenPaint);
			_pauseFloatingText.draw(canvas, dt);
		}
		
		Utils.fillExtraSideSpace(canvas);
		
		//canvas.drawRect(_storeButtonRect, SL.Graphics.RedPaint);
	}
	
	public float getTimeInState()
	{
		return _timeInState;
	}
	
	public boolean isPaused()
	{
		return _paused;
	}
	
	public void onShootButtonClicked()
	{
		//If the shooters are empty, don't do anything
		if (_bottomShooter.getTile().isEmpty() || _rightShooter.getTile().isEmpty())
			return;
		
		//Make sure there is room for both shooters to shoot
		if (!_bottomShooter.canShoot() || !_rightShooter.canShoot())
		{
			//TODO play can't shoot sound
			return;
		}
				
		//Find bottom shooter target
		int targetCol = _bottomShooter.getGridCoord();
		int targetRow = _grid.getFirstOpenRow(targetCol);
		
		
		//Find right shooter target
		int targetRow2 = _rightShooter.getGridCoord();
		int targetCol2 = _grid.getFirstOpenColumn(targetRow2);
		
		//Make sure the right shooter isn't going to hit the same target as the bottom shooter!
		if (targetRow == targetRow2 && targetCol == targetCol2)
		{
			if (targetRow >= targetCol2)
				targetCol2++;
			else
				targetRow++;
		}
		
		float targetX = _grid.getPixelBounds().left + targetCol * SL.GridSquareSize;
		float targetY = _grid.getPixelBounds().top + targetRow * SL.GridSquareSize;
		_flyingTiles.add(new FlyingTile(_bottomShooter.getTile(), _bottomShooter.getX(), _bottomShooter.getY(), targetX, targetY, targetRow, targetCol));
		_bottomShooter.setTile(Tile.Empty);
		
		targetX = _grid.getPixelBounds().left + targetCol2 * SL.GridSquareSize;
		targetY = _grid.getPixelBounds().top + targetRow2 * SL.GridSquareSize;
		_flyingTiles.add(new FlyingTile(_rightShooter.getTile(), _rightShooter.getX(), _rightShooter.getY(), targetX, targetY, targetRow2, targetCol2));
		_rightShooter.setTile(Tile.Empty);
	}
		
	public void onRoscoeReachedPiggy()
	{
		if (_menu.getMoney() == 0)
		{
			//TODO gameover
		}
		
		_menu.setMoney(_menu.getMoney() - 1);
	}
	
	private void replenishShooters()
	{
		_bottomShooter.setTile(_menu.getNextBottomThingie());
		_rightShooter.setTile(_menu.getNextRightThingie());
		_menu.generateNextThingies();
	}
	
	private void doCombos(ArrayList<Point> recentlyPlacedThingies)
	{
		for (Point point : recentlyPlacedThingies)
		{
			//Make sure another combo hasn't already cleared this tile
			if (_grid.getTile(point.x, point.y).isEmpty())
				continue;
			
			ArrayList<Point> combo = _grid.getComboAt(point.x, point.y);
			if (combo != null)
			{
				_menu.setMoney(_menu.getMoney() + (combo.size() - 2));
				for (Point p : combo)
				{
					_fadingTiles.add(new FadingTile(_grid.getTile(p.x, p.y), _grid.getPixelX(p.x), _grid.getPixelY(p.y)));
					_grid.setTile(p.x, p.y, Tile.Empty);
					
					//Kill adjacent cronies
					for (int i = p.x-1; i <= p.x+1; i++)
					{
						for (int j = p.y-1; j <= p.y+1; j++)
						{
							if (_grid.isInGridBounds(i, j) && _grid.getTile(i, j) == Tile.Baddie1)
							{
								_fadingTiles.add(new FadingTile(Tile.Baddie1, _grid.getPixelX(i), _grid.getPixelY(j)));
								_grid.setTile(i, j, Tile.Empty);
								_baddieCount--;
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Moves the bottom shooter to the given grid x coordinate.
	 * @param gridX
	 */
	private void moveBottomShooter(int gridX)
	{
		_bottomShooter.moveTo(gridX, _grid.getPixelBounds().left + SL.GridSquareSize * gridX, _grid.getPixelBounds().bottom);
	}
	
	/**
	 * Moves the right shooter to the given grid y coordinate.
	 * @param gridY
	 */
	private void moveRightShooter(int gridY)
	{
		_rightShooter.moveTo(gridY, _grid.getPixelBounds().right, _grid.getPixelBounds().top + SL.GridSquareSize * gridY);
	}

	private void loadLevel()
	{
		_baddieCount = 0;
		InputStream is = null;
		try
		{
			int resId = Utils.getCurrentLevelResourceId();
	        is = SL.Resources.openRawResource(resId);
            
			is.read();
			is.read();
			is.read();
			is.read();
			is.read();
			
			for (int i = 0; i < SL.GridWidth; i++)
			{
				for (int j = 0; j < SL.GridHeight; j++)
				{
					Tile tile = Tile.class.getEnumConstants()[is.read()];
					_grid.setTile(i, j, tile);
					
					if (tile.isBaddie())
						_baddieCount++;
				}
			}
		}
		catch (Exception e)
		{
			Log.e("Loading Level", Utils.formatException(e));
		}
		finally
		{
			try 
			{ 
				if (is != null)
					is.close(); 
			} catch (IOException ioe) { }
		}
	}

	@Override
	public void buildMenu(Menu menu) 
	{
		MenuOption.Achievements.addToMenu(menu);
		MenuOption.ToggleSound.addToMenu(menu);
		MenuOption.Help.addToMenu(menu);
		
		if (_paused)
			MenuOption.Resume.addToMenu(menu);
		else
			MenuOption.Pause.addToMenu(menu);
	}

	/**
	 * Called when the application is paused (by the user hitting the home key for example).
	 */
	public void notifyAppPaused() 
	{
		_paused = true;
	}
}
