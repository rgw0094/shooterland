package com.shooterland.framework;

import com.shooterland.R;
import com.shooterland.Shooterland;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.*;

public class GraphicsManager 
{
	public Paint BlackPaint;
	public Paint RedPaint;
	
	private Hashtable<Integer, Bitmap> _bitmaps = new Hashtable<Integer, Bitmap>();
	
	public GraphicsManager()
	{
		initResources();
	}
	
	/**
	 * Draws a bitmap at the given location. Loads the bitmap if it hasn't been
	 * loaded yet.
	 * 
	 * @param resourceId The id of the graphic to draw. Use R.drawable to see valid resourceIds.
	 * @param x
	 * @param y
	 */
	public void drawBitmap(Canvas canvas, int resourceId, float x, float y)
	{
		if (!_bitmaps.containsKey(resourceId))
			_bitmaps.put(resourceId, BitmapFactory.decodeResource(Shooterland.Resources, resourceId));
				
		canvas.drawBitmap(_bitmaps.get(resourceId), x, y, null);
	}
	
	/**
	 * Pre-caches the bitmap with the given resourceId.
	 * 
	 * @param resourceId Use R.drawable to see valid bitmap resource ids.
	 */
	public void precacheBitmap(int resourceId)
	{
		_bitmaps.put(resourceId, BitmapFactory.decodeResource(Shooterland.Resources, resourceId));
	}
	
	
	private void initResources()
	{
		BlackPaint = new Paint();
		BlackPaint.setAntiAlias(true);
		BlackPaint.setARGB(255, 0, 0, 0);
		
		RedPaint = new Paint();
		RedPaint.setAntiAlias(true);
		RedPaint.setARGB(255, 255, 0, 0);
	}
}
