package com.example.restaurantmanager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class Splashscreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashscreen);
		
		Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent a = new Intent(Splashscreen.this,Login.class);
                    startActivity(a);
                }
            }
        };
        timerThread.start();
    }
 
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
 

	}

	


