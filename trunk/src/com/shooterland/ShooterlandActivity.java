package com.shooterland;

import com.shooterland.enums.MessageCode;
import com.shooterland.framework.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.format.Time;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class ShooterlandActivity extends Activity
{	
	private ShooterlandView _view;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		Window window = getWindow();
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(0);
		
		if (!SL.Initialized)
			SL.init(this);
		
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    _view = new ShooterlandView(this);
	    setContentView(_view);
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu)
	{
		super.onPrepareOptionsMenu(menu);
		
		menu.clear();
		
		if (SL.CurrentState != null)
			SL.CurrentState.buildMenu(menu);
		
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			SL.Input.handleBackButton();
			return true;
		}	
		
		return false;
	}
	
	protected void onPause()
	{
		
		_view.getThread().pauseExecution();
		super.onPause();
	}
	
	@Override
	protected void onResume()
	{	
		super.onResume();
		_view.getThread().resumeExecution();
	}
	
	public Handler Handler = new Handler() 
	{
		@Override
		public void handleMessage(Message msg) 
		{	
			if (msg.arg1 == MessageCode.Notification.getId())
			{
				Toast.makeText(SL.Context, (String)msg.obj, Toast.LENGTH_SHORT).show();
			}
		}
	};
			
	public class ShooterlandView extends SurfaceView implements SurfaceHolder.Callback
	{
		private ShooterlandThread _thread;
		
		public ShooterlandView(ShooterlandActivity activity)
		{
			super(activity.getApplicationContext());
			
			SurfaceHolder holder = getHolder();
			holder.addCallback(this);
			_thread = new ShooterlandThread(activity, holder);
			_thread.start();
			
			setFocusable(true);
		}
		
		public ShooterlandThread getThread()
		{
			return _thread;
		}
		
	    public boolean onTouchEvent(MotionEvent me) 
		{
	    	SL.Input.handleClick(me.getX(), me.getY());
	    	return true;
		}
		
		public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) 
		{
		}

		public void surfaceCreated(SurfaceHolder holder) 
		{
			_thread.setRunning(true);
		}

		public void surfaceDestroyed(SurfaceHolder holder) 
		{
			_thread.setRunning(false);
		}
	}
		
	public class ShooterlandThread extends Thread
    {
		private ShooterlandActivity _activity;
    	private SurfaceHolder _surfaceHolder;
    	private boolean _running = false;
    	private long _lastFrameMillis;
    	
    	public ShooterlandThread(ShooterlandActivity activity, SurfaceHolder surfaceHolder)
    	{
    		_activity = activity;
    		_surfaceHolder = surfaceHolder;
    	}
    	
    	public void setRunning(boolean running)
    	{
    		synchronized(_surfaceHolder)
    		{
    			_running = running;
    		}
    	}
    	
    	public void pauseExecution()
    	{
    		synchronized(_surfaceHolder)
    		{
    			_running = false;
	    		if (SL.Initialized)
	    			SL.pauseExecution();
    		}
    	}
    	
    	public void resumeExecution()
    	{
    		synchronized(_surfaceHolder)
    		{
    			_running = true;
    			if (SL.Initialized)
    				SL.resumeExecution();
    		}
    	}
    	
    	@Override
    	public void run()
    	{    		            
    		_lastFrameMillis = Utils.currentMillis();    		           
    		
            while (true)
        	{
				while (!_running)
				{
					yield();
				}
            	
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
    				_running = false;
    				String exceptionString = Utils.formatException(e);
    				Log.e("Shooterland", exceptionString);
    				SL.showLongNotification(exceptionString);
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
