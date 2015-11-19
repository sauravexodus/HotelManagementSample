package com.example.restaurantmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Mainpage extends Activity {

	Button menu,order,about;
	String user_name;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainpage);
		
		Intent myintent = getIntent();
        Bundle extras = myintent.getExtras();
        user_name = extras.getString("cust_name");
        Toast.makeText(Mainpage.this, "Welcome " + user_name ,Toast.LENGTH_LONG ).show();
        
       // initialise form widget
        menu=(Button)findViewById(R.id.bmenu);
        order=(Button)findViewById(R.id.bOrder);
        about=(Button)findViewById(R.id.bAbout);
        
     //   custinfo=(Button)findViewById(R.id.custinfo);
        
        ModelClass.createlist();
        about.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				//starting new activity on button click
				Intent i =new Intent(Mainpage.this,Aboutus.class);
				Mainpage.this.startActivity(i);
			}
		});
        
   menu.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				//starting new activity on button click
				Intent i =new Intent(Mainpage.this,Menuscreen.class);
				Mainpage.this.startActivity(i);
			}
		});
    
   order.setOnClickListener( new OnClickListener() {
		
		public void onClick(View v) {
			//starting new activity on button click
			Intent i =new Intent(Mainpage.this,Orderlist.class);
			Mainpage.this.startActivity(i);
		}
	});
	
	}
}
