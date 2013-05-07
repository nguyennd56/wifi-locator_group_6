package com.example.wifilocator_rebuild;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")   //remove warning of TabActivity.
public class MainTabActivity extends TabActivity {
	/**
	 * run after StartUpActivity.
	 * create tabs.
	 * each tab contain an other activity.
	 */
	
	private TabHost tabHost = getTabHost();
    //-- Called when the activity is first created. 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_tabs_layout);
        
        
        // Tab for Signals
        TabSpec Signalspec = tabHost.newTabSpec("Signals");
        Signalspec.setIndicator("Signals", getResources().getDrawable(R.drawable.icon_photos_tab));
        Intent SignalsIntent = new Intent(this,SignalsActivity.class);
        Signalspec.setContent(SignalsIntent);
        
        // Tab for Locations
        TabSpec Locationspec = tabHost.newTabSpec("Locations");
        // setting Title and Icon for the Tab
        Locationspec.setIndicator("Locations", getResources().getDrawable(R.drawable.icon_songs_tab));
        Intent LocationsIntent = new Intent(this, LocationsActivity.class);
        Locationspec.setContent(LocationsIntent);
        
        // Tab for Online
        TabSpec Onlinespec = tabHost.newTabSpec("Online");
        Onlinespec.setIndicator("Online", getResources().getDrawable(R.drawable.icon_videos_tab));
        Intent OnlineIntent = new Intent(this,OnlineActivity .class);
        Onlinespec.setContent(OnlineIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(Signalspec); // Adding Signals tab
        tabHost.addTab(Locationspec); // Adding Locations tab
        tabHost.addTab(Onlinespec); // Adding Online tab
        
        
        }
}
