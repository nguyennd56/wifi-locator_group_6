package com.example.wifilocator_rebuild.module;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;



public class WifiScanner{
	/**
	 * this class to get information of all wifi signals surrounding.
	 * using many class of android wifi library to ScanResult and convert them to Signals object.
	 * return a ArrayList of Signals objects.
	 */
	
	
	public static ArrayList<Signal> getScanResults(Activity activity){
		WifiManager wifiManager = (WifiManager) activity.getSystemService(Context.WIFI_SERVICE);
		WifiReceiver wifiReceiver = new WifiReceiver();
		activity.registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
	    //--check if wifi is off then set it to on state
		wifiManager = (WifiManager)activity.getSystemService(Context.WIFI_SERVICE);
		if(!wifiManager.isWifiEnabled()){
			wifiManager.setWifiEnabled(true);
		}
		//start scan
	    wifiManager.startScan();
	    List<ScanResult> wifiList = wifiManager.getScanResults();

		ArrayList<Signal> signals = new ArrayList<Signal>();
		Signal signal;
		
		//---- cover ScanResult objects to Signals objects-----
		for(int i=0; i<wifiList.size(); i++){
			String ssid = "SSID:".concat(wifiList.get(i).SSID);
			String name = "Name:".concat("unknown");
			String bssid= "BSSID:".concat(wifiList.get(i).BSSID);
			int strength = wifiList.get(i).level;
			signal = new Signal(strength,ssid,bssid, name);
			signals.add(signal);
			
		}
		
		return signals;
	}
}

class WifiReceiver extends BroadcastReceiver {
	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
	 */
        public void onReceive(Context c, Intent intent) {
        }
}