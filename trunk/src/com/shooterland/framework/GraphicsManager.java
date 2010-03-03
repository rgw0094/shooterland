package com.shooterland.framework;

import com.shooterland.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;

public class GraphicsManager 
{
	public Paint LoadingPaint;
	public Paint BlackPaint;
	public Paint RedPaint;
	public Paint TitlePaint;
	public Paint DarkGreenPaint;
	public Paint MoneyPaint;
	public Paint GridPaint;
	public Paint LevelPaint;
	public Paint YellowPaint;
	public Paint BaddieSquarePaint;
		
	//Keep any images that don't need to be transformed as bitmaps because they draw faster.
	public Bitmap MainMenuBackground;
	public Bitmap WorldMapBackground;
	public Bitmap[] WorldBackgrounds = new Bitmap[SL.NumWorlds];
	public Bitmap[] WorldTitles = new Bitmap[SL.NumWorlds];
	public Bitmap[][] Tiles = new Bitmap[SL.NumWorlds][17];
	public Bitmap BottomShooter;
	public Bitmap RightShooter;
	public Bitmap RoscoeUp1;
	public Bitmap RoscoeUp2;
	public Bitmap RoscoeDown1;
	public Bitmap RoscoeDown2;
	public Bitmap MenuButtonForward;
	public Bitmap MenuButtonBack;
	public Bitmap MenuButtonRound;
	
	public Bitmap Complete_C;
	public Bitmap Complete_O;
	public Bitmap Complete_M;
	public Bitmap Complete_P;
	public Bitmap Complete_L;
	public Bitmap Complete_E;
	public Bitmap Complete_T;
	
	public Bitmap Pause_P;
	public Bitmap Pause_A;
	public Bitmap Pause_U;
	public Bitmap Pause_S;
	public Bitmap Pause_E;
	
	public GraphicsManager()
	{
		LoadingPaint = new Paint();
		LoadingPaint.setARGB(255, 255, 255, 255);
		LoadingPaint.setTextSize((float)SL.ScreenHeight * 0.1f);
		LoadingPaint.setAntiAlias(true);
		LoadingPaint.setTextAlign(Align.CENTER);
	}
	
	public void initResources()
	{
		BlackPaint = new Paint();
		BlackPaint.setAntiAlias(true);
		BlackPaint.setARGB(255, 0, 0, 0);
		
		RedPaint = new Paint();
		RedPaint.setAntiAlias(true);
		RedPaint.setARGB(255, 255, 0, 0);
		
		YellowPaint = new Paint();
		YellowPaint.setARGB(255, 0, 255, 255);
		YellowPaint.setAntiAlias(true);
		
		BaddieSquarePaint = new Paint();
		BaddieSquarePaint.setAntiAlias(true);
		BaddieSquarePaint.setARGB(255, 255, 0, 0);
		BaddieSquarePaint.setStrokeWidth(2.0f);
		BaddieSquarePaint.setStyle(Style.STROKE);
		
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
		
		MainMenuBackground = BuildBitmap(R.drawable.mainmenubackground, SL.ScreenWidth, SL.ScreenHeight);
		BottomShooter = BuildBitmap(R.drawable.bottomshooter, SL.GridSquareSize);
		RightShooter = BuildBitmap(R.drawable.rightshooter, SL.GridSquareSize);
		RoscoeUp1 = BuildBitmap(R.drawable.roscoeup1, SL.GridSquareSize);
		RoscoeUp2 = BuildBitmap(R.drawable.roscoeup2, SL.GridSquareSize);
		RoscoeDown1 = BuildBitmap(R.drawable.roscoedown1, SL.GridSquareSize);
		RoscoeDown2 = BuildBitmap(R.drawable.roscoedown2, SL.GridSquareSize);
		WorldMapBackground = BuildBitmap(R.drawable.worldmap, SL.GameAreaWidth, SL.GameAreaHeight);
		MenuButtonForward = BuildBitmap(R.drawable.menubutton, (int)((float)SL.GameAreaWidth * 0.288f), (int)((float)SL.GameAreaHeight * 0.120833));
		MenuButtonRound = BuildBitmap(R.drawable.menubutton2, (int)((float)SL.GameAreaWidth * 0.288f), (int)((float)SL.GameAreaHeight * 0.120833));
		MenuButtonBack = CreateRotatedBitmap(MenuButtonForward, 180);
		
		int w = (int)((float)SL.GameAreaWidth * 0.083333f);
		int h = (int)((float)SL.GameAreaWidth * 0.083333f * (5.0f/6.0f));
		Complete_C = BuildBitmap(R.drawable.complete_c, w, h);
		Complete_O = BuildBitmap(R.drawable.complete_o, w, h);
		Complete_M = BuildBitmap(R.drawable.complete_m, w, h);
		Complete_P = BuildBitmap(R.drawable.complete_p, w, h);
		Complete_L = BuildBitmap(R.drawable.complete_l, w, h);
		Complete_E = BuildBitmap(R.drawable.complete_e, w, h);
		Complete_T = BuildBitmap(R.drawable.complete_t, w, h);
		
		Pause_P = BuildBitmap(R.drawable.pause_p, BottomShooter.getWidth() * 2, BottomShooter.getHeight() * 2);
		Pause_A = BuildBitmap(R.drawable.pause_a, BottomShooter.getWidth() * 2, BottomShooter.getHeight() * 2);
		Pause_U = BuildBitmap(R.drawable.pause_u, BottomShooter.getWidth() * 2, BottomShooter.getHeight() * 2);
		Pause_S = BuildBitmap(R.drawable.pause_s, BottomShooter.getWidth() * 2, BottomShooter.getHeight() * 2);
		Pause_E = BuildBitmap(R.drawable.pause_e, BottomShooter.getWidth() * 2, BottomShooter.getHeight() * 2);
		
		LoadWorld1();
	}
	
	public void LoadWorld1()
	{
		WorldBackgrounds[0] = BuildBitmap(R.drawable.world1background, SL.GameAreaWidth, SL.GameAreaHeight);
		WorldTitles[0] = BuildBitmap(R.drawable.world1title, (int)((float)SL.GameAreaWidth * 0.284f), (int)((float)SL.GameAreaHeight * 0.05f));
		Tiles[0][0] = BuildBitmap(R.drawable.world1thingie1, SL.GridSquareSize);
		Tiles[0][1] = BuildBitmap(R.drawable.world1thingie2, SL.GridSquareSize);
		Tiles[0][2] = BuildBitmap(R.drawable.world1thingie3, SL.GridSquareSize);
		Tiles[0][3] = BuildBitmap(R.drawable.world1thingie4, SL.GridSquareSize);
		Tiles[0][4] = BuildBitmap(R.drawable.world1thingie5, SL.GridSquareSize);
		Tiles[0][5] = BuildBitmap(R.drawable.baddie1, SL.GridSquareSize);
		Tiles[0][6] = BuildBitmap(R.drawable.baddie2, SL.GridSquareSize);
		Tiles[0][7] = BuildBitmap(R.drawable.baddie3, SL.GridSquareSize);
		Tiles[0][8] = BuildBitmap(R.drawable.baddie4, SL.GridSquareSize);
		Tiles[0][9] = BuildBitmap(R.drawable.baddie5, SL.GridSquareSize);
		Tiles[0][10] = BuildBitmap(R.drawable.block, SL.GridSquareSize);
		Tiles[0][11] = BuildBitmap(R.drawable.baddieblock1, SL.GridSquareSize);
		Tiles[0][12] = BuildBitmap(R.drawable.baddieblock2, SL.GridSquareSize);
		Tiles[0][13] = BuildBitmap(R.drawable.baddieblock3, SL.GridSquareSize);
		Tiles[0][14] = BuildBitmap(R.drawable.baddieblock4, SL.GridSquareSize);
		Tiles[0][15] = BuildBitmap(R.drawable.baddieblock5, SL.GridSquareSize);
		Tiles[0][16] = BuildBitmap(R.drawable.crackedblock, SL.GridSquareSize);
	}
	
	private Bitmap BuildBitmap(int id, int size)
	{
		return BuildBitmap(id, size, size);
	}
	
	private Bitmap BuildBitmap(int id, int width, int height)
	{
		return Bitmap.createScaledBitmap(BitmapFactory.decodeResource(SL.Resources, id), width, height, true);
	}
	
	private Bitmap CreateRotatedBitmap(Bitmap bitmap, float angle)
	{
		Matrix m = new Matrix();
		m.postRotate(angle);
		return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
	}
}
