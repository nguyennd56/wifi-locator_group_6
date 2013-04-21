package com.example.wifi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;



/*
 * This Activity is the first activity of the App
 * its main function is for checking wifi state (Enabled or not)
 * if it is not enabled, then auto set wifi in enabled state
 * */
public class StartUp extends Activity {
	
	//constant
	final static int START_POINT=0;
	final static int END_POINT = 10;
	final static int TIME_UNIT = 500;
	//Variables
	static int progress;				//counting progress
	ProgressBar progressBar;			//view on layout
	int progressStatus = START_POINT;				//update the progress counting
	Handler handler = new Handler();	//manage runnable
		
	//Method
	//1. onCreate() do when Activity first run.
	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		
		setUp();
		
		//Check wifi, and automatically enable if it is off
		checkWiFi();
		
        //---do some work in background thread--- 
        delayForTurningWiFi();
	}
	
	//2. onPause() do when the Activity in pause state
	@Override
	protected void onPause(){
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	private void setUp(){
		setContentView(R.layout.activity_start_up);
		progress = START_POINT; 
        progressBar = (ProgressBar) findViewById(R.id.progressBar1); 

	}
	
	private void delayForTurningWiFi(){
		new Thread(new Runnable(){ 
        	public void run() { 
                  //---count progress--- 
                 while (progressStatus < END_POINT){ 
                      progressStatus = progressIncreasing(); 
                 } 
                  //---hides the progress bar--- 
                 handler.post(new Runnable(){ 
                      public void run(){ 
                           //---0 - VISIBLE; 4 - INVISIBLE; 8 - GONE--- 
                           progressBar.setVisibility(View.GONE); 
                      } 
                 }); 
                 //Change to the next Activity: Menu
                 Intent activity = new Intent("com.example.wifi.MENU");
                 startActivity(activity);
            } 
             //---increasing progress count--- 
            private int progressIncreasing(){ 
                 try{ 
                      //---timer run--- 
                      Thread.sleep (TIME_UNIT); 
                 }catch(InterruptedException e) { 
                      e.printStackTrace(); 
                 } 
                 return ++progress; //increasing...
            } 
        }).start();
	}
	
	private void checkWiFi(){
		WifiManager wifiManager =(WifiManager) getSystemService(Context.WIFI_SERVICE);
		if(!wifiManager.isWifiEnabled()){
			wifiManager.setWifiEnabled(true);
		}
	}
	
}
