package com.example.wifi;

import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Scan extends Activity {
	///Variable
	WifiManager mainWifi;//
    WifiReceiver receiverWifi;
    List<ScanResult> wifiList;
    StringBuilder sb = new StringBuilder();
    TextView NumberOfWiFi;
    //---Oncreat
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scan_layout);
		
		//refresh= (Button)findViewById(R.id.btnRefresh);
		NumberOfWiFi = (TextView)findViewById(R.id.txtWifi);
		mainWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
	    receiverWifi = new WifiReceiver();
	    registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
	    //--check if wifi is off then set it to on state
	    mainWifi = (WifiManager)getSystemService(Context.WIFI_SERVICE);
	    if(!mainWifi.isWifiEnabled()){
	    	mainWifi.setWifiEnabled(true);
	    }
	    //start scan
	    mainWifi.startScan();
	    wifiList = mainWifi.getScanResults();	     
		ListView list = (ListView) findViewById(R.id.listView1);
	

		Product[] items = new Product[wifiList.size()];
		NumberOfWiFi.setText("Detecting "+wifiList.size()+ " WiFi Signals");
		for(int i = 0; i < wifiList.size(); i++){
			items[i]= new Product((wifiList.get(i)).level, (wifiList.get(i)).SSID, "unknow");
		}
		ArrayAdapter<Product> adapter = new ArrayAdapter<Product>(this,android.R.layout.simple_list_item_1,items);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				TextView v =(TextView) view; 
				try{
					Class<?> myClass = Class.forName("com.example.listinandroid.Next");
					Intent myIntent = new Intent(Scan.this,myClass);
					startActivity(myIntent);
				}
				catch(ClassNotFoundException e){
					e.printStackTrace();
				}
				String item = v.getText().toString();
				Toast.makeText(getBaseContext(), item, Toast.LENGTH_SHORT).show();
			}
		});
	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		unregisterReceiver(receiverWifi);
		super.onPause();
	}
	

    public boolean onMenuItemSelected(int featureId, MenuItem item) {
    	//--check if wifi is off then set it to on state
	    mainWifi = (WifiManager)getSystemService(Context.WIFI_SERVICE);
	    if(!mainWifi.isWifiEnabled()){
	    	mainWifi.setWifiEnabled(true);
	    }
        mainWifi.startScan();
        return super.onMenuItemSelected(featureId, item);
    }

    protected void onResume() {
        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }
    
    class WifiReceiver extends BroadcastReceiver {
        public void onReceive(Context c, Intent intent) {
        }
    }

}
class Product {
	private int streng;
	private String name;
	private String locator;
	public Product(){super();}
	public Product(int i, String n, String p){
		super();this.streng=i;this.name=n;this.locator=p;
	}
	public String toString(){
		return String.format("[Name]"+this.locator+
							"\n[Signal Strength]"+this.streng+"" +
							"\n[SSID]"+this.name+"\n");
	
	}
}
