package com.example.wifilocator_rebuild;



import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;



/**
 * @author Nguyen D. Ngo
 *
 */
public class StartUp extends Activity {
	/*
	 * @task: load necessary condition of application.
	 * @ conditions: Wifi must be enabled 
	 */

	
	private ProgressBar progressBar;

	
	//Method
	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		
		setUp();
		
		//-- checking wifi is enable? if not turn it on.
		setWifiEnable();
        //---delay time, and run main activity
        new Thread(new Runnable(){ 
             public void run(){ 
            	 delayTimeToEnableWiFi();
                  //run progressBar 
                 runningProgressBar(progressBar);
                 //start mainActivity is MainTabActivity
                 startMainActivity();
             } 
        }).start(); 
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
	private void setUp(){
		progressBar = (ProgressBar) findViewById(R.id.progressBar); 
		setContentView(R.layout.startup_layout);
	}
	
	
	private void startMainActivity(){
        try{
            Class<?> Myclass= Class.forName("com.example.wifilocator_rebuild.MainTabActivity");
            Intent activity = new Intent(StartUp.this, Myclass);
            startActivity(activity);
        }
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	/*
	 * 
	 * run delay time: idle a period time
	 */
	final static int DELAY = 3000;
	private void delayTimeToEnableWiFi(){
		try { 
            Thread.sleep (DELAY); 
       } catch (InterruptedException e) 
       { 
            e.printStackTrace(); 
       }
	}
	
	/*
	 * @method's task: enable wifi
	 */
	private void setWifiEnable(){
		WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		if(!wifiManager.isWifiEnabled()){
			wifiManager.setWifiEnabled(true);
		}
	}
	
	private void runningProgressBar(final ProgressBar progressBar){
		Handler handler = new Handler();
        handler.post(new Runnable(){ 
             public void run(){ 
                  progressBar.setVisibility(View.GONE); 
             } 
        });
	}
}
