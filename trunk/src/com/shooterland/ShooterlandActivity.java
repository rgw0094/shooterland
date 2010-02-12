package com.shooterland;

import com.shooterland.framework.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.format.Time;
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
		setRequestedOrientation(0);
		
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(new ShooterlandView(this));
	}
	
	public class ShooterlandView extends SurfaceView implements SurfaceHolder.Callback
	{
		private ShooterlandThread _thread;
		
		public ShooterlandView(Context context)
		{
			super(context);
			
			SurfaceHolder holder = getHolder();
			holder.addCallback(this);
			_thread = new ShooterlandThread(holder);
			
			setFocusable(true);
		}
		
	    public boolean onTouchEvent(MotionEvent me) 
		{
	    	SL.Input.handleClick(me.getX(), me.getY());
	    	return true;
		}
		
		public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) 
		{
			SL.setScreenSize(width, height);
		}

		public void surfaceCreated(SurfaceHolder holder) 
		{
			_thread.start();
		}

		public void surfaceDestroyed(SurfaceHolder holder) 
		{
			//We have to tell thread to shut down & wait for it to finish, or else
	        //it might touch the Surface after we return and explode
			boolean retry = true;
			_thread.setRunning(false);
	        while (retry) 
	        {
	            try 
	            {
	            	_thread.join();
	                retry = false;
	            } 
	            catch (InterruptedException e) { }
	        }

		}
	}
		
	public class ShooterlandThread extends Thread
    {
    	private SurfaceHolder _surfaceHolder;
    	private boolean _running = true;
    	private long _lastFrameMillis;
    	
    	public ShooterlandThread(SurfaceHolder surfaceHolder)
    	{
    		_surfaceHolder = surfaceHolder;
    		
    	}
    	
    	public void setRunning(boolean running)
    	{
    		_running = running;
    	}
    	
    	@Override
    	public void run()
    	{
    		SL.init(getApplicationContext());
    		_lastFrameMillis = Utils.currentMillis();
    		
    		while (_running)
    		{
    			Canvas canvas = null;
    			try
    			{    				
    				canvas = _surfaceHolder.lockCanvas();
    				synchronized (_surfaceHolder)
    				{
    					long currentMillis = Utils.currentMillis();
    					float dt = (float)(currentMillis - _lastFrameMillis) / 1000.0f;
    					_lastFrameMillis = currentMillis;
    					
    					SL.update(dt);
    					SL.draw(canvas, dt);
    				}
    			}
    			catch (Exception e)
    			{
    				System.out.println(e.getStackTrace());
    				_running = false;
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
