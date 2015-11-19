package com.example.restaurantmanager;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class Orderlist extends Activity {
    ArrayAdapter<String> adapter;
    ModelClass orderModelClass;
    ListView list;
    static String s1;
    static String s2;

    Button gotomenu, placedorder;
    DatabaseHelper orderHelper;
    static String s;
    String cust_name, phone, email, order, address;
    int total;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist);
        //initialise list view to dispay data in list

        orderModelClass = new ModelClass();
        list = (ListView) findViewById(R.id.list);
        gotomenu = (Button) findViewById(R.id.bgotomenu);
        placedorder = (Button) findViewById(R.id.bplace);


        //  orderModelClass = orderHelper.getSingleInfo(LoginActivity.KY_PHONE);


        //System.out.println(mClass.getPhone());
        cust_name = orderModelClass.getName();
        phone = orderModelClass.getPhone();
        email = orderModelClass.getEmail();
        address = orderModelClass.getAddress();

        adapter = new ArrayAdapter<String>(Orderlist.this, android.R.layout.simple_list_item_1,
                ModelClass.al);
        list.setAdapter(adapter);
        total = 0;
        for (int i = 0; i < list.getCount(); i++) {
            String temp2 = (String) list.getItemAtPosition(i);
            String intValue = temp2.replaceAll("[^0-9]", "");
            int x = Integer.parseInt(intValue);
            x = (x%10)*(x/10);
            total += x;

        }

        if (ModelClass.al.isEmpty()) {
            Toast.makeText(this, "No order Is placed.Please go to the menu section and order", Toast.LENGTH_LONG).show();

        } else {
            s2 = ModelClass.al.get(0).toString();
        }
        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0,
                                    View view, final int position,
                                    long id) {
                // TODO Auto-generated method stub

                //order remove functioanlity on alert box button
                AlertDialog.Builder builder = new AlertDialog.Builder(Orderlist.this);
                builder.setTitle("Order Remove");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        s = (String) list.getItemAtPosition(position);
                        ModelClass.al.remove(s);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                    }
                });
                AlertDialog alt = builder.create();
                alt.show();
            }
        });
        gotomenu.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent menuIntent = new Intent(Orderlist.this, Menuscreen.class);
                startActivity(menuIntent);
            }
        });
        for (int i = 1; i < ModelClass.al.size(); i++) {
            s1 = ModelClass.al.get(i).toString();
            s2 += "," + s1;
        }


        placedorder.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent j = new Intent(Orderlist.this, Amount.class);
                j.putExtra("total", total);
                startActivity(j);
                finish();
            }
        });
    }
}