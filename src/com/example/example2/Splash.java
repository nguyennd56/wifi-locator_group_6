package com.example.example2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.wifi.WifiManager;
import android.os.Bundle;

public class Splash extends Activity {
	
	WifiManager w;
	//MediaPlayer ourSong;
	@Override
	protected void onCreate(Bundle nguyenlovehien) {
		// TODO Auto-generated method stub
		super.onCreate(nguyenlovehien);
		setContentView(R.layout.splash);
		w = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		if(w.isWifiEnabled()){
			
		}
		else{
			w.setWifiEnabled(true);
		}
		//ourSong = MediaPlayer.create(Splash.this,R.raw.hpt);
		//ourSong.start();
		Thread timer = new Thread(){
			public void run(){
				try{
					
						sleep(1000);
					
				}catch(InterruptedException e){
					
				}
				finally{
					Intent openStartingPoint = new Intent("com.example.example2.MENU");
					
					startActivity(openStartingPoint);
					
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//ourSong.release();
		finish();
	}
	
	
	
}
