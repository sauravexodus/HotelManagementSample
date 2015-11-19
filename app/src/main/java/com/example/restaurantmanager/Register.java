package com.example.restaurantmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {
	EditText name,email,phone,address; 
	Button register;
	ModelClass mClass;
	DatabaseHelper Helper;
	public static String c_name,c_mail,c_addrs, c_phone ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		try{
        	Helper = new DatabaseHelper(Register.this);
        	//cust_Helper.getWritableDatabase();
        }
        catch(Exception e){
        	e.printStackTrace();
        }
		
		mClass = new ModelClass();
        register=(Button)findViewById(R.id.breg);
        name = (EditText)findViewById(R.id.etname);
        email =(EditText)findViewById(R.id.etemail);
        phone =(EditText)findViewById(R.id.etphone);
        address =(EditText)findViewById(R.id.etaddress);
        
        register.setOnClickListener(new OnClickListener() {
    		
     	   @Override
 			public void onClick(View v) {
     		 
     		 c_name = name.getText().toString();
     		 //System.out.println(c_name);
     		 c_mail = email.getText().toString();
     		 c_phone = phone.getText().toString();
     		 c_addrs = address.getText().toString();
     		 mClass.setName(c_name);
     		 mClass.setEmail(c_mail);
 			 mClass.setPhone(c_phone);
 			 mClass.setAddress(c_addrs);
     		   if(c_name.equals("")||c_mail.equals("")||c_phone.equals("") || c_addrs.equals("")){
     			  Toast.makeText(Register.this, "All fields are Mandatory", Toast.LENGTH_LONG).show(); 
     		   }
     		   else{
     			   Intent i =new Intent(Register.this,Mainpage.class);
     			   Bundle bundle = new Bundle();
     			   bundle.putString("cust_name", c_name);
     			   i.putExtras(bundle);
     			   startActivity(i);
 				}
     	   }
        });
        
        
	}
		
	public void onResume(){
		super.onResume();
		Helper = new DatabaseHelper(Register.this);
		//cust_Helper.getWritableDatabase();
	}
	public void onStop(){
		super.onStop();
		Helper.close();
	
	}
	
		
}
