package com.shooterland.framework;

import com.shooterland.R;
import com.shooterland.SL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Paint.Align;

public class GraphicsManager 
{
	public Paint BlackPaint;
	public Paint RedPaint;
	public Paint TitlePaint;
		
	//Keep any images that don't need to be transformed as bitmaps because they draw faster.
	public Bitmap MainMenuBackground;
	public Bitmap[] WorldBackgrounds = new Bitmap[1];
	public Bitmap[][] Thingies = new Bitmap[1][6];
	public Bitmap BottomShooter;
	public Bitmap RightShooter;
	
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
		
		BottomShooter = BuildBitmap(R.drawable.bottomshooter, SL.GridSquareSize);
		RightShooter = BuildBitmap(R.drawable.rightshooter, SL.GridSquareSize);
		MainMenuBackground = BuildBitmap(R.drawable.mainmenubackground, SL.ScreenWidth, SL.ScreenHeight);
		
		LoadWorld1();
	}
	
	public void LoadWorld1()
	{
		WorldBackgrounds[0] = BuildBitmap(R.drawable.world1background, SL.ScreenWidth, SL.ScreenHeight);
		Thingies[0][0] = BuildBitmap(R.drawable.world1thingie1, SL.GridSquareSize);
		Thingies[0][1] = BuildBitmap(R.drawable.world1thingie2, SL.GridSquareSize);
		Thingies[0][2] = BuildBitmap(R.drawable.world1thingie3, SL.GridSquareSize);
		Thingies[0][3] = BuildBitmap(R.drawable.world1thingie4, SL.GridSquareSize);
		Thingies[0][4] = BuildBitmap(R.drawable.world1thingie5, SL.GridSquareSize);
		Thingies[0][5] = BuildBitmap(R.drawable.world1thingie6, SL.GridSquareSize);
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
