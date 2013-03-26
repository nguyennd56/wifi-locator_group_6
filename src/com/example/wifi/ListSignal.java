package com.example.wifi;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
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
	
	WifiManager mainWifi;//
    WifiReceiver receiverWifi;
    List<ScanResult> wifiList;
    StringBuilder sb = new StringBuilder();
    TextView NumberOfWiFi;
    
    Button scanRe;
    Button backToMenu;
    Button quit;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(com.example.wifi.R.layout.list_signal_scan);
		//setList
		setListView();
		
		//setButton ReScan
		scanRe = (Button)findViewById(R.id.scan_re);
		scanRe.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setListView();
			}
		});
		
		//setButton Back To Menu
		backToMenu=(Button)findViewById(R.id.back_to_menu);
		backToMenu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
				Class Menuclass = Class.forName("com.example.wifi.Menu");
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
		
	}
	
	
	public void setListView(){
		ArrayList<Signal> image_details = getScanResults();
		ArrayList<Signal> from_saving = getSignalSaved();
		compare(image_details, from_saving);
		final ListView lv1 = (ListView) findViewById(R.id.listview_signal);
		lv1.setAdapter(new SignalsForm(this,image_details));
		//set on item click
        lv1.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> a, View v, int position, long id) { 
        		
        		Signal o = (Signal)lv1.getItemAtPosition(position);
            	//Functions obj_itemDetails = (Functions)o;
        		//Signal o=image_details.get(position);
            	try{
        			Class ourClass = Class.forName("com.example.wifi.Edit");
        			Intent ourIntent = new Intent(ListSignal.this,ourClass);
        			ourIntent.putExtra("clicked", o);
        			startActivity(ourIntent);
        		}catch(ClassNotFoundException e){
        			e.printStackTrace();
        		}
        	}  
        });
	}

	private ArrayList<Signal> getScanResults(){
		//refresh= (Button)findViewById(R.id.btnRefresh);
		NumberOfWiFi = (TextView)findViewById(R.id.txtWifi);
		mainWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
	    receiverWifi = new WifiReceiver();
	    registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
	    //--check if wifi is off then set it to on state
		mainWifi = (WifiManager)getSystemService(Context.WIFI_SERVICE);
		if(!mainWifi.isWifiEnabled()){
			mainWifi.setWifiEnabled(true);
		}
		//start scan
	    mainWifi.startScan();
	    wifiList = mainWifi.getScanResults();

		ArrayList<Signal> signals = new ArrayList<Signal>();
		Signal signal;
		for(int i=0; i<wifiList.size(); i++){
			String ssid = "SSID:".concat(wifiList.get(i).SSID);
			String name = "Name:".concat("unknown");
			int strength = wifiList.get(i).level;
			if(strength>=-45){
				strength=5;
			}
			else if(strength>=-55){
				strength = 4;
			}
			else if(strength>=-65){
				strength = 3;
			}
			else if(strength>=-75){
				strength = 2;
			}
			else if(strength>=-95){
				strength = 1;
			}
			else {
				strength =0;
			}
			signal = new Signal(strength,ssid, name);
			signals.add(signal);
			
		}
		
		return signals;
	}
	
	
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		unregisterReceiver(receiverWifi);
		super.onPause();
	}
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
    	//--check if wifi is off then set it to on state
	    mainWifi = (WifiManager)getSystemService(Context.WIFI_SERVICE);
	    if(!mainWifi.isWifiEnabled()){
	    	mainWifi.setWifiEnabled(true);
	    }
        mainWifi.startScan();
        return super.onMenuItemSelected(featureId, item);
    }
	protected void onResume() {
        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }
    
    class WifiReceiver extends BroadcastReceiver {
        public void onReceive(Context c, Intent intent) {
        	StringBuilder sb = new StringBuilder();
        }
    }
	
    protected ArrayList<Signal> getSignalSaved() {
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
	
    public void compare(ArrayList<Signal> source, ArrayList<Signal> des) {
    	for(int i=0; i< source.size(); i++) {
    		for(int j=0; j< des.size(); j++) {
    			if(source.get(i).equalTo(des.get(j))) {
    				source.get(i).setName(des.get(j).getName());
    			}
    		}
    	}
    }
}