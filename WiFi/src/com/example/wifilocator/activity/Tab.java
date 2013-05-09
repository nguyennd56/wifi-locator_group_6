package com.example.wifilocator.activity;

import java.util.ArrayList;

import com.example.wifilocator_rebuild.R;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")   //remove warning of TabActivity.

/**
 * run after StartUpActivity.
 * create tabs.
 * each tab contain an other activity.
 */
public class Tab extends TabActivity {
	
    //-- Called when the activity is first created. 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_tabs_layout);
        TabHost tabHost = getTabHost();
        // Adding all TabSpec to TabHost
        addTab(generateTabSpec(tabHost), tabHost);
    }
        
    private ArrayList<TabSpec> generateTabSpec(TabHost tabHost){
    	ArrayList<TabSpec> tabSpecs = new ArrayList<TabSpec>();
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
        
        /* DON'T CARE 
         * It's just for next release
         * 
        // Tab for Online
        TabSpec Onlinespec = tabHost.newTabSpec("Online");
        Onlinespec.setIndicator("Online", getResources().getDrawable(R.drawable.icon_videos_tab));
        Intent OnlineIntent = new Intent(this,OnlineActivity .class);
        Onlinespec.setContent(OnlineIntent);
         */
        
        tabSpecs.add(Signalspec);
        tabSpecs.add(Locationspec);
        
        return tabSpecs;
        
    }
    
    private void addTab(final ArrayList<TabSpec> tabSpecs, TabHost tabHost){
    	for(int index=0; index<tabSpecs.size(); index++){
    		tabHost.addTab(tabSpecs.get(index));
    	}
    }
    
}