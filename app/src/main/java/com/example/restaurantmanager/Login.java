package com.example.restaurantmanager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.SQLException;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {

	EditText nameText,phoneText;
	Button registeredButton,newUser;
	ModelClass loginModelClass;
	DatabaseHelper dbHelper;
	static final String KEY_NAME = "name";
	static final String KY_PHONE = "phone";
	boolean validate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
loginModelClass = new ModelClass();
		
		try{
			dbHelper = new DatabaseHelper(Login.this);
		//	dbHelper.getWritableDatabase();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		
		
		nameText = (EditText)findViewById(R.id.etn);
		phoneText =(EditText)findViewById(R.id.etmob);
		
		registeredButton = (Button)findViewById(R.id.blogin);
		newUser = (Button)findViewById(R.id.bregh);
		
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		String uname = pref.getString(KEY_NAME, "");
		String mphone = pref.getString(KY_PHONE, "");
		nameText.setText(uname);
		phoneText.setText(mphone);
		
newUser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String cust_name = nameText.getText().toString();
				String mphone =phoneText.getText().toString();
				
				if(cust_name.equals("")||mphone.equals("")){
					Toast.makeText(Login.this, "Please enter the data", Toast.LENGTH_SHORT).show();	
				
				}
				
				else if(dbHelper.validateUser(cust_name, mphone)){
					//Toast.makeText(LoginActivity.this, "allowed", Toast.LENGTH_SHORT).show();
					startManagingCursor(dbHelper.cursor);
					Intent i =new Intent(Login.this,Mainpage.class);
					Bundle bundle = new Bundle();
					bundle.putString("cust_name", cust_name);
					i.putExtras(bundle);
					startActivity(i);
				}
				
				else {
					Toast.makeText(Login.this, "Your are not registered! ", Toast.LENGTH_SHORT).show();
					SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Login.this);
					Editor ed = preferences.edit();
					ed.putString(KEY_NAME, cust_name);
					ed.putString(KY_PHONE, mphone);
					ed.commit();
				}
				
			}
		});
		registeredButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent iL =new Intent(Login.this,Register.class);
				startActivity(iL);
			}
		});
	}
	public void onResume(){
		super.onResume();
		dbHelper = new DatabaseHelper(Login.this);
		//dbHelper.getWritableDatabase();
	}
	public void onStop(){
		super.onStop();
		dbHelper.close();
		//dbHelper.cursor.close();
	}
	

}
