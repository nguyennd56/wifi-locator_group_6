package com.example.wifilocator_rebuild;

import java.util.ArrayList;

import com.example.wifilocator_rebuild.module.Location;
import com.example.wifilocator_rebuild.module.LocationForm;
import com.example.wifilocator_rebuild.module.Signal;
import com.example.wifilocator_rebuild.module.StorageManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AddCurrentPlaceActivity extends Activity {
	/**
	 * activity occurs when you add this current place.
	 * write down the place name and choose the location containing it.
	 */
	
	//---variable--
	Location root,clickedLocation;
	EditText editNameBox;
	ArrayList<Signal> currentWifiList;
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//-- choose layout and set value for variables.
		setContentView(R.layout.add_current_place);
		editNameBox = (EditText)findViewById(R.id.place_name_edit);
		root=StorageManager.loadLocation();
		currentWifiList= (ArrayList<Signal>) getIntent().getSerializableExtra("currentSignals");
		clickedLocation=root;
		
		//--- make listView of locations---
		setListView(root);
	}
	
	public void setListView(Location currentChoice) {
		ArrayList<Location> allChildren= currentChoice.getAllLocationChildren();
		final ListView lv1 = (ListView) findViewById(R.id.listview_add_location);
		lv1.setAdapter(new LocationForm(this,allChildren));
		//set on item click
		lv1.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> a, View v, int position, long id) { 
        		clickedLocation=(Location)lv1.getItemAtPosition(position);
        		setListView(clickedLocation);
        	}
		});
	}
	
	//----- back to view list of parent location----
	public void onClickBack(View v) {
		if(clickedLocation.getParent()!=null) {
			clickedLocation= (Location)clickedLocation.getParent();
			setListView(clickedLocation);
		}
	}
	
	
	//----- make location object and save it-----
	public void onClickAddHere(View v) {
		String currentPlaceName = editNameBox.getText().toString();
		Location currentLocation = new Location(currentPlaceName,currentWifiList);
		clickedLocation.add(currentLocation);
		StorageManager.saveLocation(root);
		//reset ListView
		setListView(clickedLocation);
		//---display file saved message---
		Toast.makeText(getBaseContext(),
				"Location saved successfully!"+root.getSize()+"  "+root.print(0),
				Toast.LENGTH_SHORT).show();

		//---clears the EditText---
		editNameBox.setText("");
	}
	
	
	
}

