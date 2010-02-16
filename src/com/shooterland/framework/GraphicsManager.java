package com.shooterland.framework;

import com.shooterland.R;
import com.shooterland.SL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Paint.Align;

public class GraphicsManager 
{
	public Paint BlackPaint;
	public Paint RedPaint;
	public Paint TitlePaint;
	public Paint DarkGreenPaint;
	public Paint MoneyPaint;
	public Paint GridPaint;
	public Paint LevelPaint;
		
	//Keep any images that don't need to be transformed as bitmaps because they draw faster.
	public Bitmap MainMenuBackground;
	public Bitmap WorldMapBackground;
	public Bitmap[] WorldBackgrounds = new Bitmap[1];
	public Bitmap[][] Tiles = new Bitmap[1][6];
	public Bitmap BottomShooter;
	public Bitmap RightShooter;
	public Bitmap RoscoeUp1;
	public Bitmap RoscoeUp2;
	public Bitmap RoscoeDown1;
	public Bitmap RoscoeDown2;
	
	public GraphicsManager()
	{
		initResources();
	}
			
	private void initResources()
	{
		BlackPaint = new Paint();
		BlackPaint.setAntiAlias(true);
		BlackPaint.setARGB(255, 0, 0, 0);
		
		RedPaint = new Paint();
		RedPaint.setAntiAlias(true);
		RedPaint.setARGB(255, 255, 0, 0);
		
		TitlePaint = new Paint();
		TitlePaint.setAntiAlias(true);
		TitlePaint.setARGB(255, 0, 0, 0);
		TitlePaint.setTextAlign(Align.CENTER);
		TitlePaint.setTextSize(50.0f);
		
		DarkGreenPaint = new Paint();
		DarkGreenPaint.setAntiAlias(true);
		DarkGreenPaint.setARGB(255, 0, 50, 0);
		
		GridPaint = new Paint();
		GridPaint.setAntiAlias(true);
		GridPaint.setARGB(150, 120, 120, 120);
		
		LevelPaint = new Paint();
		LevelPaint.setAntiAlias(true);
		LevelPaint.setARGB(255, 255, 255, 255);
		LevelPaint.setTextAlign(Align.CENTER);
		LevelPaint.setTextSize(20.0f);
		
		MoneyPaint = new Paint();
		MoneyPaint.setAntiAlias(true);
		MoneyPaint.setARGB(255, 255, 255, 0);
		MoneyPaint.setTextAlign(Align.CENTER);
		MoneyPaint.setTypeface(Typeface.DEFAULT_BOLD);
		MoneyPaint.setTextSize(22.0f);
		
		BottomShooter = BuildBitmap(R.drawable.bottomshooter, SL.GridSquareSize);
		RightShooter = BuildBitmap(R.drawable.rightshooter, SL.GridSquareSize);
		RoscoeUp1 = BuildBitmap(R.drawable.roscoeup1, SL.GridSquareSize);
		RoscoeUp2 = BuildBitmap(R.drawable.roscoeup2, SL.GridSquareSize);
		RoscoeDown1 = BuildBitmap(R.drawable.roscoedown1, SL.GridSquareSize);
		RoscoeDown2 = BuildBitmap(R.drawable.roscoedown2, SL.GridSquareSize);
		MainMenuBackground = BuildBitmap(R.drawable.mainmenubackground, SL.ScreenWidth, SL.ScreenHeight);
		WorldMapBackground = BuildBitmap(R.drawable.worldmap, SL.GameAreaWidth, SL.GameAreaHeight);
		
		LoadWorld1();
	}
	
	public void LoadWorld1()
	{
		WorldBackgrounds[0] = BuildBitmap(R.drawable.world1background, SL.GameAreaWidth, SL.GameAreaHeight);
		Tiles[0][0] = BuildBitmap(R.drawable.world1thingie1, SL.GridSquareSize);
		Tiles[0][1] = BuildBitmap(R.drawable.world1thingie2, SL.GridSquareSize);
		Tiles[0][2] = BuildBitmap(R.drawable.world1thingie3, SL.GridSquareSize);
		Tiles[0][3] = BuildBitmap(R.drawable.world1thingie4, SL.GridSquareSize);
		Tiles[0][4] = BuildBitmap(R.drawable.world1thingie5, SL.GridSquareSize);
		Tiles[0][5] = BuildBitmap(R.drawable.world1thingie6, SL.GridSquareSize);
	}
	
	private Bitmap BuildBitmap(int id, int size)
	{
		return BuildBitmap(id, size, size);
	}
	
	private Bitmap BuildBitmap(int id, int width, int height)
	{
		return Bitmap.createScaledBitmap(BitmapFactory.decodeResource(SL.Resources, id), width, height, true);
	}
}
