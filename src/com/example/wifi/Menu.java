package com.example.wifi;

import java.util.ArrayList;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Menu extends Activity {
	
	Button quit;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        
        ArrayList<Functions> image_details = GetSearchResults();
        
        final ListView lv1 = (ListView) findViewById(R.id.listV_main);
        lv1.setAdapter(new FunctionsForm(this, image_details));
        
        lv1.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> a, View v, int position, long id) { 
        		
        		Object o = lv1.getItemAtPosition(position);
            	Functions obj_itemDetails = (Functions)o;
            	try{
        			Class ourClass = Class.forName("com.example.wifi."+ obj_itemDetails.getName());
        			Intent ourIntent = new Intent(Menu.this,ourClass);
        			startActivity(ourIntent);
        		}catch(ClassNotFoundException e){
        			e.printStackTrace();
        		}
            	//Toast.makeText(Menu.this, "You have chosen : " + " " + obj_itemDetails.getName(), Toast.LENGTH_LONG).show();
        	}  
        });
        
        
        quit = (Button)findViewById(R.id.quit_menu);
        quit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		});
        
        
    }
    
    private ArrayList<Functions> GetSearchResults(){
    	ArrayList<Functions> results = new ArrayList<Functions>();
    	
    	Functions item_details3;
    	item_details3 = new Functions();
    	item_details3.setName("ListSignal");
    	item_details3.setImageNumber(1);
    	results.add(item_details3);
    	/*
    	Functions item_details = new Functions();
    	item_details.setName("Scan");
    	item_details.setImageNumber(1);
    	results.add(item_details);
    	*/
    	
    	Functions item_details2;
    	item_details2 = new Functions();
    	item_details2.setName("History");
    	item_details2.setImageNumber(2);
    	results.add(item_details2);
    	
    	return results;
    }
}