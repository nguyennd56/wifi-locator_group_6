package com.example.wifilocator.activity;

import com.example.wifilocator_rebuild.R;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;

/**
 * First Activity of application.
 * Run when application start.
 */
public class StartUp extends Activity {
	//variables
	final static int DELAY_TIME = 5000;
	
	private ProgressBar progressBar;
	private static WifiManager wifiManager;
	
	//Method
	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		
		setUp();
		
		enableWifi();
		
        //---delay time to ensure that WiFi is on after enabling
        new Thread(new Runnable() { 
             public void run() { 
                  //delay time to enable WiFi 
                 runDelayTime();
                  //run progerssBar
                 runProgressBar();
                 //-- when all is done, run an other activity.
                 runTabActivity();
            }
        }).start(); 
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
	private void setUp() {
		setContentView(R.layout.startup_layout);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1); 
		wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
	}
	
	private static void enableWifi() {
		if (!wifiManager.isWifiEnabled()) {
			wifiManager.setWifiEnabled(true);
		}
	}

	private void runTabActivity() {
		try {
            Class<?> Myclass = Class.forName("com.example.wifilocator_rebuild.MainTabActivity");
            Intent activity = new Intent(StartUp.this, Myclass);
            startActivity(activity);
        }
		catch(ClassNotFoundException exception) {
			exception.printStackTrace();
		}
	}
	
	private void runProgressBar() {
		Handler handler = new Handler();
        handler.post(new Runnable() { 
             public void run() {  
                  progressBar.setVisibility(View.GONE); 
             } 
        });
	}
	
	
	private void runDelayTime(){
		try { 
            //---simulate doing some work--- 
            Thread.sleep (DELAY_TIME); 
		}
		catch (InterruptedException exception) { 
            exception.printStackTrace(); 
       }
	}	
}
