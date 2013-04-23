package com.example.wifi;



import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class Menu extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        
        TabHost tabHost = getTabHost();
        
        // Tab for Signals
        TabSpec Signalspec = tabHost.newTabSpec("Signals");
        Signalspec.setIndicator("Signals", getResources().getDrawable(R.drawable.icon_photos_tab));
        Intent SignalsIntent = new Intent(this, ListSignal.class);
        Signalspec.setContent(SignalsIntent);
        
        // Tab for History
        TabSpec Historyspec = tabHost.newTabSpec("History");
        // setting Title and Icon for the Tab
        Historyspec.setIndicator("History", getResources().getDrawable(R.drawable.icon_songs_tab));
        Intent HistoryIntent = new Intent(this, History.class);
        Historyspec.setContent(HistoryIntent);
        
        // Tab for Manager
        TabSpec Managerspec = tabHost.newTabSpec("Manager");
        Managerspec.setIndicator("Manager", getResources().getDrawable(R.drawable.icon_videos_tab));
        Intent ManagerIntent = new Intent(this, LocationManager.class);
        Managerspec.setContent(ManagerIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(Signalspec); // Adding Signals tab
        tabHost.addTab(Historyspec); // Adding History tab
        tabHost.addTab(Managerspec); // Adding Manager tab
    }
}