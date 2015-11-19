package com.example.restaurantmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Amount extends Activity {
	
	TextView result;
	Button confirm;
	double sum;
	public static String totalamount;
	 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_amount);
		
		result=(TextView)findViewById(R.id.tvtotal);
		confirm=(Button)findViewById(R.id.bOk);
		 
		Intent data = getIntent();
		final int temp = data.getIntExtra("total",0);
		result.setText("Total Amount: "+temp);
		confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				totalamount = temp + "";
				Intent m = new Intent(Amount.this, Last.class);
				startActivity(m);


			}	} );
		
	}

	

}
