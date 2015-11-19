package com.example.restaurantmanager;



import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Orderscreen extends Activity implements OnClickListener {
	TextView orderitem,temp;
	EditText Quantity;
	 Button Place_Order;
	 String tempstring;
	 Integer i;
	 String s;
	 String quanityforsoup;
	 StringBuilder orderdata=null;
	DatabaseHelper orderHelper;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        orderHelper = new DatabaseHelper(Orderscreen.this);
        
     // initialise form widget
        setContentView(R.layout.activity_orderscreen);
       
        orderitem =(TextView)findViewById(R.id.tvno);
        Quantity =(EditText)findViewById(R.id.etquan);
        Place_Order =(Button)findViewById(R.id.baddorder);
       
        s= getIntent().getStringExtra("order");
        
        
       
       
       Quantity.setRawInputType(InputType.TYPE_CLASS_NUMBER);
      
      
      
       Place_Order.setClickable(true);
      
     
       Place_Order.setOnClickListener(this);
      
   	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		 Place_Order.setClickable(true);
		  	
		  orderHelper = new DatabaseHelper(Orderscreen.this);
			//orderHelper.getWritableDatabase();
	}
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		 Place_Order.setClickable(true);
		  	
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//on button click place order
			
			Place_Order.setClickable(false);
			tempstring=Quantity.getText().toString();
		
			i=Integer.parseInt(tempstring); 
			Toast.makeText(Orderscreen.this, s, Toast.LENGTH_LONG);
			
		/*	String intValue = s.replaceAll("[^0-9]", ""); */
			s=s+"  "+ "-"+"  "+ i;
			//add data to model class object
			boolean add = ModelClass.al.add(s);
		
			//	adding the data 
			orderHelper.addOrder(s);
		
			/*int total = Integer.parseInt(intValue)*i;
			Intent i = new Intent(this,Amount.class);
			i.putExtra("qty", i);
			i.putExtra("price", intValue);
			i.putExtra("total", total );
			startActivity(i);*/
				/*Intent mIntent = new Intent(OrderScreen.this,MenuScreen.class);
			startActivity(mIntent);*/
			finish();
		
	}
	public boolean checkType()
	{
		return false;
	}
	
	public void onStop(){
		super.onStop();
		orderHelper.close();
		//orderHelper.cursor.close();
	}
}