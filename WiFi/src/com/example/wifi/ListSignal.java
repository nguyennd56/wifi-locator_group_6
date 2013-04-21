package com.example.wifi;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListSignal extends Activity{
	
	WifiManager wifiManager;//
    WifiReceiver wifiReceiver;
    List<ScanResult> wifiList;
    StringBuilder stringBuilder = new StringBuilder();
    //TextView NumberOfWiFi;
    TextView location;
    
    Button scan;
    Button back;
    Button quit;
    Button savePlace;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(com.example.wifi.R.layout.activity_list_signal);
		//setList
		setListView();
		
		//setButton ReScan
		scan = (Button)findViewById(R.id.scan_re);
		scan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setListView();
			}
		});
		
		//setButton Back To Menu
		back=(Button)findViewById(R.id.back_to_menu);
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
				Class<?> Menuclass = Class.forName("com.example.wifi.Menu");
				Intent backToMenuActivity = new Intent(ListSignal.this,Menuclass);
				startActivity(backToMenuActivity);
				}
				catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			}
		});
		
		//setButton Quit
		quit = (Button)findViewById(R.id.quit_r);
		quit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub	
				finish();
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		});
		
		final ArrayList<Signal> currentListSignals = getScanResults();
		savePlace=(Button)findViewById(R.id.add_location);
		//set Button save place
		savePlace.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
				Class<?> myClass = Class.forName("com.example.wifi.Label");
				Intent intent = new Intent(ListSignal.this,myClass);
				intent.putExtra("currentSignals",currentListSignals );
				startActivity(intent);
				}
				catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			}
				
		});
		location= (TextView)findViewById(R.id.location_detail);
		//location.setText(locationDeterminant(currentListSignals));
	}
	/*
	public String locationDeterminant(ArrayList<Signal> currentListSignals) {
		
		String locationDetail="you are at a unknow place";
		ArrayList<Location> locationsSaved = getLocationSaved();
		int markForLocation=0;
		for(int i=0; i< locationsSaved.size(); i++) {
			int markForThisLocation= locationsSaved.get(i).compareWithThisLocation(currentListSignals);
			if((markForThisLocation>markForLocation)&&(markForThisLocation>2)) {
				locationDetail=locationsSaved.get(i).toString();
			}
		}
		
		return locationDetail;
	}
	*/
	
	public void setListView(){
		ArrayList<Signal> image_details = getScanResults();
		//ArrayList<Signal> from_saving = getSignalSaved();
		//compare(image_details, from_saving);
		final ListView lv1 = (ListView) findViewById(R.id.listview_signal);
		lv1.setAdapter(new SignalAdapter(this,image_details));
		//set on item click
        lv1.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> a, View v, int position, long id) { 
        		
        		Signal o = (Signal)lv1.getItemAtPosition(position);
            	//Functions obj_itemDetails = (Functions)o;
        		//Signal o=image_details.get(position);
            	try{
        			Class<?> ourClass = Class.forName("com.example.wifi.Edit");
        			Intent ourIntent = new Intent(ListSignal.this,ourClass);
        			ourIntent.putExtra("clicked", o);
        			startActivity(ourIntent);
        		}catch(ClassNotFoundException e){
        			e.printStackTrace();
        		}
        	}  
        });
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
	private ArrayList<Signal> getScanResults(){
		//refresh= (Button)findViewById(R.id.btnRefresh);
		//NumberOfWiFi = (TextView)findViewById(R.id.txtWifi);
		wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
	    wifiReceiver = new WifiReceiver();
	    registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
	    //--check if wifi is off then set it to on state
		wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
		if(!wifiManager.isWifiEnabled()){
			wifiManager.setWifiEnabled(true);
			for(int i=0; i<20; i++);
		}
		//start scan
	    wifiManager.startScan();
	    wifiList = wifiManager.getScanResults();

		ArrayList<Signal> signals = new ArrayList<Signal>();
		Signal signal;
		for(int i=0; i<wifiList.size(); i++){
			signal = new Signal(
					wifiList.get(i).SSID,
					wifiList.get(i).BSSID,
					wifiList.get(i).capabilities,
					wifiList.get(i).frequency,
					wifiList.get(i).level,
					wifiList.get(i).timestamp
					);
			signal.setRate();
			signals.add(signal);
			
		}
		
		return signals;
	}
	
	
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		unregisterReceiver(wifiReceiver);
		super.onPause();
	}
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
    	//--check if wifi is off then set it to on state
	    wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
	    if(!wifiManager.isWifiEnabled()){
	    	wifiManager.setWifiEnabled(true);
	    }
        wifiManager.startScan();
        return super.onMenuItemSelected(featureId, item);
    }
	protected void onResume() {
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
        setListView();
    }
    
    class WifiReceiver extends BroadcastReceiver {
        public void onReceive(Context c, Intent intent) {
        }
    }
	
    /*
    protected ArrayList<Signal> getSignalSaved() {  // to get all saved wifi signalss.
    	ArrayList<Signal> _wifiList= new ArrayList<Signal>();
    	try
		{
	    	File sdCard = Environment.getExternalStorageDirectory();
	        File directory = new File (sdCard.getAbsolutePath() + 
	            "/MyFiles");
	        File file = new File(directory, "signal.data");
	        FileInputStream fIn = new FileInputStream(file);
	        ObjectInputStream in = new ObjectInputStream(fIn);
	        while (true) {
	            _wifiList.add( (Signal) in.readObject());
	        }
		}
        catch (EOFException e) {
			e.printStackTrace();
			}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}

	    return _wifiList;
    }
    */
	/*
    public void compare(ArrayList<Signal> source, ArrayList<Signal> des) { // compare between saved wifi signals and caught wifi signals. 
    	for(int i=0; i< source.size(); i++) {
    		for(int j=0; j< des.size(); j++) {
    			if(source.get(i).equalTo(des.get(j))) {
    				source.get(i).setName(des.get(j).getName());
    			}
    		}
    	}
    }*/
    /*
    protected ArrayList<Location> getLocationSaved() {
    	ArrayList<Location> locationList= new ArrayList<Location> ();
    	try
		{
			//---SD Storage---
			
            File sdCard = Environment.getExternalStorageDirectory();
            File directory = new File (sdCard.getAbsolutePath() + 
                "/MyFiles");
            File file = new File(directory, "location.data");
            FileInputStream fIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fIn);
            
            while (true) {
            	Location theLocation=(Location)in.readObject();
            	
	            locationList.add(theLocation);
	            
	            Toast.makeText(getBaseContext(),
						"File loaded successfully!",
						Toast.LENGTH_SHORT).show();
            }
		}
		catch (EOFException e) {
			e.printStackTrace();
			}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
    	return locationList;
    }
    */
}