package com.example.wifilocator_rebuild;

import java.util.ArrayList;

import com.example.wifilocator_rebuild.module.Location;
import com.example.wifilocator_rebuild.module.SignalsForm;
import com.example.wifilocator_rebuild.module.Signal;
import com.example.wifilocator_rebuild.module.StorageManager;
import com.example.wifilocator_rebuild.module.WifiScanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * this activity to show all Wifi signals surrounding,
 * show the current location
 * allow to add location.
 */

public class SignalsActivity extends Activity{

	
	//------variable -------
	public final static String DEFAULT_LOCATION_NAME= " you are at an unknow place";
	public final static int    MINIMUM_NUMBER_OF_THE_SAME_SIGNAL=2;
	TextView Location;   
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signals_scan_layout);
		Location= (TextView) findViewById(R.id.location_tv);
		
		//-- show all wifi signals and current location--
		setListView(); 
		Location.setText(locationDeterminant(WifiScanner.getScanResults(this)));
	}
	 
	
	/**
	 * set the function of add button.
	 * call the AddCurrentLocationActivity.
	 */
	public void onClickAdd(View v) {
		try{
			Class<?> aClass = Class.forName("com.example.wifilocator_rebuild.AddCurrentPlaceActivity");
			Intent intent = new Intent(SignalsActivity.this,aClass);
			intent.putExtra("currentSignals",WifiScanner.getScanResults(this) );
			startActivity(intent);
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * set the function of back button.
	 */
	public void onClickBack(View v) {

	}
	
	
	/**
	 * set the function of Rescan button.
	 * reset ListView of signals and current location. 
	 */
	public void onClickRescan(View v) {
		setListView();
		Location.setText(locationDeterminant(WifiScanner.getScanResults(this)));
	}
	
	
	/**
	 * set the function of Quit button.
	 * exits the application.
	 */
	public void onClickQuit(View V) {
		finish();
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
	
	
	
	/**
	 * make signals ListView.
	 * using list of scanned signals to generate.
	 * using saved signals to get place of wifi.
	 */
	public void setListView() {
		//-- get current surrounding signals.
		ArrayList<Signal> image_details = WifiScanner.getScanResults(this);
		//-- get saved signals and compare to current signals..
		ArrayList<Signal> from_saving = StorageManager.loadSignals();
		compare(image_details, from_saving);
		final ListView lv1 = (ListView) findViewById(R.id.listview_signal);
		lv1.setAdapter(new SignalsForm(this,image_details));
		//set on item click
        lv1.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> a, View v, int position, long id) { 
        		
        		Signal chosenSignal = (Signal)lv1.getItemAtPosition(position);
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
	}
	
	
	/**
	 * compare between saved wifi signals and caught wifi signals. 
	 * if they are the same change place of this signal to place of saved signals.
	 */
	public void compare(ArrayList<Signal> source, ArrayList<Signal> des) { 
    	for(int i=0; i< source.size(); i++) {
    		for(int j=0; j< des.size(); j++) {
    			if(source.get(i).getSSID().equals(des.get(j).getSSID())) {
    				source.get(i).setPlace(des.get(j).getPlace());
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
	public String locationDeterminant(ArrayList<Signal> currentListSignals) {
		
		String locationDetail=DEFAULT_LOCATION_NAME;
		Location root=StorageManager.loadLocation();
		ArrayList<Location> locationsSaved =root.getAll();
		int markForLocation=0;
		for(int i=0;i< locationsSaved.size(); i++) {
			int markForThisLocation= locationsSaved.get(i).compareToOtherListSignals(currentListSignals);
			if((markForThisLocation>markForLocation)&&(markForThisLocation>MINIMUM_NUMBER_OF_THE_SAME_SIGNAL)) {
				locationDetail=locationsSaved.get(i).printInfo();
				markForLocation=markForThisLocation;
			}
		}
		//-- Remove string of root location ("All location") ----
		locationDetail=locationDetail.substring(root.toString().length());
		return ("Current location: "+locationDetail);
	}
	
}
