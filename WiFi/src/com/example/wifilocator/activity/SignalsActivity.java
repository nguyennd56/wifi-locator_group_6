package com.example.wifilocator.activity;

import java.util.ArrayList;

import com.example.wifilocator.module.Location;
import com.example.wifilocator.module.Signal;
import com.example.wifilocator.module.SignalBaseAdapter;
import com.example.wifilocator.module.StorageManager;
import com.example.wifilocator.module.WifiScanner;
import com.example.wifilocator_rebuild.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

/**
 * this activity to show all WIFI signals surrounding,
 * show the current location
 * allow to add location.
 */

public class SignalsActivity extends Activity{

	
	//------variables -------
	public final static String DEFAULT_LOCATION_NAME = " Where are you?";
	public final static int MINIMUM_NUMBER_OF_THE_SAME_SIGNAL = 2;
	public final static int STARTING_SCORE = 0;
	
	private TextView Location;   
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signals_scan_layout);
		Location= (TextView) findViewById(R.id.location_tv);
		//-- show all WIFI signals and current location--
		setListView(); 
		//show the location
		Location.setText(determineLocate(WifiScanner.getScanResults(this)));
	
	}
	 
	
	/**
	 * set the function of add button.
	 * call the AddCurrentLocationActivity.
	 */
	public void onClickAdd(View view) {
		try {
			Class<?> aClass = Class.forName("com.example.wifilocator_rebuild.AddCurrentPlaceActivity");
			Intent intent = new Intent(SignalsActivity.this,aClass);
			intent.putExtra("currentSignals",WifiScanner.getScanResults(this) );
			startActivity(intent);
		}
		catch(ClassNotFoundException exception){
			exception.printStackTrace();
		}
	}
	
	
	/**
	 * set the function of back button.
	 */
	/*
	 * DON'T CARE
	 * Now, It's just an unused function. But in the next release we will use it. 
	 * 
	 * 
	public void onClickBack(View v) {

	}
	*/
	
	/**
	 * set the function of Rescan button.
	 * reset ListView of signals and current location. 
	 */
	public void onClickRescan() {
		setListView();
		Location.setText(determineLocate(WifiScanner.getScanResults(this)));
	}
	
	/**
	 * set the function of Quit button.
	 * exits the application.
	 */
	public void onClickQuit() {
		finish();
		
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		startActivity(intent);
	}
	
	/**
	 * make signals ListView.
	 * using list of scanned signals to generate.
	 * using saved signals to get place of WIFI.
	 */
	public void setListView() {
		//-- get current surrounding signals.
		ArrayList<Signal> scanListSignal = WifiScanner.getScanResults(this);
		//-- get saved signals and compare to current signals..
		ArrayList<Signal> savedListSignal = StorageManager.loadSignals();
		
		setLocation(scanListSignal, savedListSignal);
		//set list view
		final ListView listView = (ListView) findViewById(R.id.listview_signal);
		listView.setAdapter(new SignalBaseAdapter(this,scanListSignal));
	
		/*
		 * DON'T CARE 
		 * It's just for the next release
		 * 
		 * 
		//set on item click
        listView.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> a, View v, int position, long id) { 
        		
        		Signal chosenSignal = (Signal)listView.getItemAtPosition(position);
            	try{
        			Class<?> ourClass = Class.forName("com.example.wifilocator_rebuild.EditSignalPlaceActivity");
        			Intent ourIntent = new Intent(SignalsActivity.this,ourClass);
        			ourIntent.putExtra("clicked", chosenSignal);
        			startActivity(ourIntent);
        		}catch(ClassNotFoundException e){
        			e.printStackTrace();
        		}
        	}  
        });
        */
	}
	
	
	/**
	 * compare between saved WIFI signals and caught WIFI signals. 
	 * if they are the same change place of this signal to place of saved signals.
	 */
	public void setLocation(ArrayList<Signal> savedListSignal, ArrayList<Signal> scanListSignal) { 
    	
		for (int rowIndex = 0; rowIndex < savedListSignal.size(); rowIndex++) {
    		for (int columnIndex = 0; columnIndex < scanListSignal.size(); columnIndex++) {
    			if (savedListSignal.get(rowIndex).getBSSID().equals(scanListSignal.get(columnIndex).getBSSID())) {
    				savedListSignal.get(rowIndex).setPlace(scanListSignal.get(columnIndex).getPlace());
    			}
    		}
    	}
    }
	
	/**
	 * determine location by saved locations and List of current signals.
	 * rank of each Location received form its compareWithThisLocation function.
	 * find the saved location had highest rank.
	 * return string of it and its all location ancestors.
	 */
	private String determineLocate(ArrayList<Signal> scanSignal) {
		
		String locationDetail = DEFAULT_LOCATION_NAME;
		
		Location data = StorageManager.loadLocation();
		ArrayList<Location> locationsSaved = data.getLocationList();
		int score = STARTING_SCORE ;
		for (int index = 0; index < locationsSaved.size(); index++) {
			int ratingScore = locationsSaved.get(index).ratingBasedSignal(scanSignal);
			if ((ratingScore > score)&&(ratingScore > MINIMUM_NUMBER_OF_THE_SAME_SIGNAL)) {
				locationDetail = locationsSaved.get(index).printInfo();
				score = ratingScore;
			}
		}
		
		/*DON'T CARE
		 * it's just some draft code.
		 * -- Remove string of "root" location ("All location") ----
		 */
		//locationDetail=locationDetail.substring(data.toString().length());
		
		return ("Current location: "+locationDetail);
	}

}
