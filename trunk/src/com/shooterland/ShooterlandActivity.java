package com.shooterland;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

public class ShooterlandActivity extends Activity
{	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		Window window = getWindow();
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);  
		
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(new ShooterlandView(this));
	}
	
	public class ShooterlandView extends SurfaceView implements SurfaceHolder.Callback
	{
		private ShooterlandThread _thread;
		
		public ShooterlandView(Context context)
		{
			super(context);
			
			Shooterland.init(getApplicationContext());
			
			SurfaceHolder holder = getHolder();
			holder.addCallback(this);
			_thread = new ShooterlandThread(holder);
			
			setFocusable(true);
		}
		
	    public boolean onTouchEvent(MotionEvent me) 
		{
	    	Shooterland.Input.handleClick(me.getX(), me.getY());
	    	return true;
		}
		
		public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) 
		{
		}

		public void surfaceCreated(SurfaceHolder holder) 
		{
			_thread.start();
		}

		public void surfaceDestroyed(SurfaceHolder holder) 
		{
		}
	}
		
	public class ShooterlandThread extends Thread
    {
    	private SurfaceHolder _surfaceHolder;
    	private boolean _running = true;
    	
    	public ShooterlandThread(SurfaceHolder surfaceHolder)
    	{
    		_surfaceHolder = surfaceHolder;
    	}
    	
    	@Override
    	public void run()
    	{
    		while (_running)
    		{
    			Canvas canvas = null;
    			try
    			{
    				float dt = 0.0f;
    				canvas = _surfaceHolder.lockCanvas();
    				synchronized (_surfaceHolder)
    				{
    					Shooterland.update(dt);
    					Shooterland.draw(canvas, dt);
    				}
    			} 
    			finally
    			{
    				if (canvas != null)
    				{
    					_surfaceHolder.unlockCanvasAndPost(canvas);
    				}
    			}
    		}
    	}
    }
}
