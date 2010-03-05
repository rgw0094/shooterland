package com.shooterland.dialogs;

import com.shooterland.R;
import com.shooterland.enums.DialogResult;
import com.shooterland.framework.SL;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StoreDialog extends Dialog
{
	public StoreDialog(Context context) 
	{
		super(context);
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
          super.onCreate(savedInstanceState);

          setContentView(R.layout.store);
          setTitle("Store");
          
          Button buyButton = (Button)findViewById(R.id.StoreBuyButton);
          buyButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					SL.Activity.BigHackToDoDialogs = DialogResult.Yes;
				}
          	});
          
          Button cancelButton = (Button)findViewById(R.id.StoreCancelButton);
          cancelButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					SL.Activity.BigHackToDoDialogs = DialogResult.No;	
				}
          	});
          
/**
          CheckBox cbBeer = (CheckBox) findViewById(R.id.checkboxBeer);
          cbBeer.setChecked(beer);

          CheckBox cbWine = (CheckBox) findViewById(R.id.checkboxWine);
          cbWine.setChecked(wine);
          
          Button buttonOK = (Button) findViewById(R.id.buttonOK);
          buttonOK.setOnClickListener(new OKListener());*/
     } 
}

/**
public class StoreView extends LinearLayout
{
	private SurfaceView _surface;
	private TextView _textView;
	
	public StoreView(Context context) 
	{
		super(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.store, this);
        
        //_surface = (SurfaceView)findViewById(R.id.StoreSurface);
        //_textView = (TextView)findViewById(R.id.StoreText);
        
        
        //_textView.setText("test");
	}
	
	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		setMeasuredDimension((int)((float)SL.GameAreaWidth * 0.8f), (int)((float)SL.GameAreaHeight * 0.8f));
	}
}*/
