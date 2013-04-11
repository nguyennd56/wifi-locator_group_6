package com.example.wifi;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Menu extends Activity {
	//constant
	final static int ICON_INDEX_1 = 1;
	final static int ICON_INDEX_2 = 2;
	
	//Variables
	Button quitButton;

	//Methods
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set Function ListView
		
        setContentView(R.layout.activity_menu);
		quitButton = (Button)findViewById(R.id.quit_menu);
        quitButton.setText("Quit");
        
        final ListView listView = (ListView) findViewById(R.id.menu_list_function);
		ArrayList<Functions> functionList = setFunctionList();
		listView.setAdapter(new FunctionAdapter(this,functionList));
		listView.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) { 
        		
        		Object object = listView.getItemAtPosition(position);
            	Functions object_itemDetails = (Functions)object;
            	try{
        			Class<?> myClass = Class.forName("com.example.wifi."+ object_itemDetails.getName());
        			Intent myIntent = new Intent(Menu.this,myClass);
        			startActivity(myIntent);
        		}catch(ClassNotFoundException e){
        			e.printStackTrace();
        		}
        	}  
        });
		
		quitButton.setOnClickListener(new View.OnClickListener() {
			
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
    
    //Set List Function
    private ArrayList<Functions> setFunctionList(){
    	ArrayList<Functions> results = new ArrayList<Functions>();
    	addFunction(results, "ListSignal", ICON_INDEX_1);
    	addFunction(results, "abc", ICON_INDEX_1);
    	return results;

    }
	
	
	
	

	private void addFunction(ArrayList<Functions> list, String nameFunction, int icon){
		list.add( new Functions(nameFunction, icon));
	}
    
}