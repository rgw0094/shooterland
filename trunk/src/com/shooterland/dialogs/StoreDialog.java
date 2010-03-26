package com.shooterland.dialogs;

import com.shooterland.R;
import com.shooterland.enums.DialogResult;
import com.shooterland.framework.SL;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
					StoreDialog.this.dismiss();
				}
          	});
          
          Button cancelButton = (Button)findViewById(R.id.StoreCancelButton);
          cancelButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					SL.Activity.BigHackToDoDialogs = DialogResult.No;
					StoreDialog.this.dismiss();
				}
          	});
     } 
}
