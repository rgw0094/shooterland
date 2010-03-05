package com.shooterland.dialogs;

import com.shooterland.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class StoreView extends LinearLayout
{
	public StoreView(Context context) 
	{
		super(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.store, this);

	}
	
	
}
