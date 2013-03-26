package com.example.wifi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class Startup extends Activity {
	
	

	static int progress;
	ProgressBar progressBar;
	int progressStatus = 0;
	Handler handler = new Handler();
	WifiManager w;
	//MediaPlayer ourSong;
	@Override
	protected void onCreate(Bundle nguyenlovehien) {
		// TODO Auto-generated method stub
		super.onCreate(nguyenlovehien);
		setContentView(R.layout.startup);
		w = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		if(w.isWifiEnabled()){
			
		}
		else{
			w.setWifiEnabled(true);
		}
				
		progress = 0; 
        progressBar = (ProgressBar) findViewById(R.id.progressBar1); 

        //---do some work in background thread--- 
        new Thread(new Runnable() 
        { 
             public void run() 
             { 
                  //---do some work here--- 
                 while (progressStatus < 10) 
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
                 return ++progress; 
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
