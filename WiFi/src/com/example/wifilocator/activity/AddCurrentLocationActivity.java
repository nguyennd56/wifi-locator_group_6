package com.example.wifilocator.activity;

import java.util.ArrayList;

import com.example.wifilocator.module.Location;
import com.example.wifilocator.module.LocationBaseAdapter;
import com.example.wifilocator.module.Signal;
import com.example.wifilocator.module.StorageManager;
import com.example.wifilocator_rebuild.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * activity occurs when you add this current place.
 * write down the place name and choose the location containing it.
 */


public class AddCurrentLocationActivity extends Activity {

	//-- variables---
	private Location defaultLocation;
	private Location chosenLocation;
	private EditText editName;
	private ArrayList<Signal> catchSignal;
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//-- choose layout and set value for variables.
		setContentView(R.layout.add_current_place);
		editName = (EditText)findViewById(R.id.place_name_edit);
		
		
		//---- Listview location-----
		//load saved location 
		defaultLocation=StorageManager.loadLocation();
		// first we just consider chosenLocation as defaultLocation
		catchSignal = (ArrayList<Signal>) getIntent().getSerializableExtra("currentSignals");
		chosenLocation = defaultLocation;
		//--- make listView of locations---
		setListView(defaultLocation);
	}
	
	public void setListView(Location location) {
		ArrayList<Location> leafLocation = location.getLeafLocation();
		final ListView listView = (ListView) findViewById(R.id.listview_add_location);
		listView.setAdapter(new LocationBaseAdapter(this,leafLocation));
		
		//set on item click
		listView.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> a, View v, int position, long id) { 
        		chosenLocation = (Location)listView.getItemAtPosition(position);
        		setListView(chosenLocation);
        	}
		});
	}
	
	//----- back to view list of parent location----
	public void onClickBack() {
		if (chosenLocation.getParent() != null) {
			chosenLocation = (Location)chosenLocation.getParent();
			setListView(chosenLocation);
		}
	}
	
	
	//----- make location object and save it-----
	public void onClickAddHere() {
		String currentPlaceName = editName.getText().toString();
		Location currentLocation = new Location(currentPlaceName,catchSignal);
		//currentLocation.set("device state", stateKey);
		chosenLocation.add(currentLocation);
		StorageManager.saveLocation(defaultLocation);
		//reset ListView
		setListView(chosenLocation);
		//---display file saved message---
		Toast.makeText(getBaseContext(),"Location saved successfully!"+defaultLocation.getSize()
				+"  "+defaultLocation.print(0),Toast.LENGTH_SHORT).show();

		//---clears the EditText---
		editName.setText("");
	}
		
}