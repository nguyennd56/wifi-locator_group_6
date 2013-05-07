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
	
	private static WifiManager wifiManager;
	private static WifiReceiver wifiReceiver;
	
	public static ArrayList<Signal> getScanResults(Activity activity){
		
		setUp(activity);
		
	    //--check if wifi is off then set it to on state
		enableWifi();
		
		ArrayList<Signal> signals = getScan();
		
		return signals;
	}
	
	private static void setUp(Activity activity){
		wifiManager = (WifiManager) activity.getSystemService(Context.WIFI_SERVICE);
		wifiReceiver = new WifiReceiver();
		activity.registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
	}
	
	private static void enableWifi(){
		if(!wifiManager.isWifiEnabled()){
			wifiManager.setWifiEnabled(true);
		}
	}
	
	private static ArrayList<Signal> getScan(){
	
		wifiManager.startScan();
	    List<ScanResult> wifiList = wifiManager.getScanResults();

		ArrayList<Signal> signals = new ArrayList<Signal>();
		
		
		//---- cover ScanResult objects to Signals objects-----
		for(int i=0; i<wifiList.size(); i++){
			
			String ssid = "SSID:".concat(wifiList.get(i).SSID);
			String name = "Name:".concat(ssid);
			String bssid= "BSSID:".concat(wifiList.get(i).BSSID);
			int strength = wifiList.get(i).level;
			
			Signal signal = new Signal(strength,ssid,bssid, name);
			
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


