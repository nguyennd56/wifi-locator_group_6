package com.example.wifilocator_rebuild;


import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;

public class StartUp extends Activity {
	/*
	 * First Activity of application.
	 * Run when application start.
	 */

	final static int TIME_FOR_LOADING =10; // Time for this activity to load.
	final static int BEGINING_TIME	  =0;
	//variable
	static int timeCounter;
	ProgressBar circleProgressBar;
	int progressStatus = BEGINING_TIME;
	Handler handler = new Handler();
	WifiManager wifiManager;
	
	
	//Method
	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		setContentView(R.layout.startup_layout);
		
		//-- checking wifi is enable? if not turn it on.
		wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		if(!wifiManager.isWifiEnabled()){
			wifiManager.setWifiEnabled(true);
		}
			
		timeCounter = 0; 
        circleProgressBar = (ProgressBar) findViewById(R.id.progressBar1); 

        //---do some work in background thread--- 
        new Thread(new Runnable() 
        { 
             public void run() 
             { 
                  //---do some work here--- 
                 while (progressStatus < TIME_FOR_LOADING) 
                 { 
                      progressStatus = doSomeWork(); 
                 } 

                  //---hides the progress bar--- 

                 handler.post(new Runnable() 
                 { 
                      public void run() 
                      { 
                           //---0 - VISIBLE; 4 - INVISIBLE; 8 - GONE--- 
                           circleProgressBar.setVisibility(View.GONE); 
                      } 
                 });
                 
                 //-- when all is done, run an other activity.
                 try{
	                 Class<?> Myclass= Class.forName("com.example.wifilocator_rebuild.MainTabActivity");
	                 Intent activity = new Intent(StartUp.this, Myclass);
	                 startActivity(activity);
                 }
 				catch(ClassNotFoundException e){
 					e.printStackTrace();
 				}
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
