package com.example.wifi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;



public class Startup extends Activity {
	final static int REFRESH_TIME =10; // Time to check again (second).
	//variable
	static int timeCounter;
	ProgressBar progressBar;
	int progressStatus = 0;
	Handler handler = new Handler();
	WifiManager wifiManager;
	
	
	//Method
	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		setContentView(R.layout.startup);
		wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		if(!wifiManager.isWifiEnabled()){
			wifiManager.setWifiEnabled(true);
		}
			
		timeCounter = 0; 
        progressBar = (ProgressBar) findViewById(R.id.progressBar1); 

        //---do some work in background thread--- 
        new Thread(new Runnable() 
        { 
             public void run() 
             { 
                  //---do some work here--- 
                 while (progressStatus < REFRESH_TIME) 
                 { 
                      progressStatus = doSomeWork(); 
                 } 

                  //---hides the progress bar--- 

                 handler.post(new Runnable() 
                 { 
                      public void run() 
                      { 
                           //---0 - VISIBLE; 4 - INVISIBLE; 8 - GONE--- 
                           progressBar.setVisibility(View.GONE); 
                      } 
                 }); 
                 
                 Intent activity = new Intent("com.example.wifi.MENU");
                 startActivity(activity);
            } 

             //---do some long running work here--- 
            private int doSomeWork() 
            { 
                 try { 
                      //---simulate doing some work--- 
                      Thread.sleep (500); 
                 } catch (InterruptedException e) 
                 { 
                      e.printStackTrace(); 
                 } 
                 return ++timeCounter; 
            } 
        }).start(); 
		
		
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//ourSong.release();
		finish();
	}
	
	
	
}
