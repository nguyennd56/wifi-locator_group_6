package com.example.wifi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class History extends Activity{
	ListView listLocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history_layout);
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
}
