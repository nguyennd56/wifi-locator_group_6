package com.example.wifilocator.module;
import java.util.ArrayList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * this class for making listView of WIFI signals.
 * generated from Signals object.
 */

public class SignalBaseAdapter extends BaseAdapter{

	
	private final static int ICON_ID_POOR_L1 = com.example.wifilocator_rebuild.R.drawable.level_0_poor;
	private final static int ICON_ID_POOR_L2 = com.example.wifilocator_rebuild.R.drawable.level_1_poor;
	private final static int ICON_ID_POOR_L3 = com.example.wifilocator_rebuild.R.drawable.level_2_poor;
	private final static int ICON_ID_FAIR = com.example.wifilocator_rebuild.R.drawable.level_3_fair;
	private final static int ICON_ID_GOOD = com.example.wifilocator_rebuild.R.drawable.level_4_good;
	private final static int ICON_ID_EXCELLENT = com.example.wifilocator_rebuild.R.drawable.level_5_excellent;
	
	
	private final static int POOR_1 = 0;
	private final static int POOR_2 = 1;
	private final static int POOR_3 = 2;
	private final static int FAIR = 3;
	private final static int GOOD = 4;
	private final static int EXCELLENT = 5;
	
	
	private static ArrayList<Signal> listSignal;
	
	private Integer[] iconId = {                 //----array of wifi level image index----.
			ICON_ID_POOR_L1, ICON_ID_POOR_L2, ICON_ID_POOR_L3,
			ICON_ID_FAIR,
			ICON_ID_GOOD,
			ICON_ID_EXCELLENT
			};
	private LayoutInflater layoutInflater;
	//constructor
	public SignalBaseAdapter(Context context, ArrayList<Signal> results) {
			listSignal = results;
			layoutInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listSignal.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return listSignal.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		String name, ssid, rate;
		int icon;
		
		if(convertView==null){
			holder = new ViewHolder();
			holder.setIdHolder(layoutInflater, convertView);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		

		//------------ set wifi level image of signals basing on its strength.---------------
		int rankSignalStrength= ((listSignal.get(position).getStrength()+100)/10);
		switch(rankSignalStrength){
		case POOR_1: 		icon = iconId[0];	rate = "level: poor";		break;
		case POOR_2: 		icon = iconId[1];	rate = "level: poor";		break;
		case POOR_3: 		icon = iconId[2];	rate = "level: poor";		break;
		case FAIR: 			icon = iconId[3];	rate = "level: fair";		break;
		case GOOD: 			icon = iconId[4];	rate = "level: good";		break;
		case EXCELLENT: 	icon = iconId[5];	rate = "level: excellent";	break;
		default:			icon = iconId[0];	rate = "level: unknown";	break;
		}
		ssid = listSignal.get(position).getSSID();
		name = listSignal.get(position).getPlace();
		
		holder = new ViewHolder(name,ssid,rate,icon);
		
		return convertView;
	}
	
	
	
	
	/*
	 * set of all things will be shown at Signals listView.
	 */
	static class ViewHolder{
		private TextView txt_name;
		private TextView txt_SSID;
		private TextView txt_rate;
		private ImageView icon;
		
		public ViewHolder(){}
		
		public ViewHolder(String name, String ssid, String rate, int icon){
			super();
			this.txt_name.setText(name);
			this.txt_SSID.setText(ssid);
			this.txt_rate.setText(rate);
			this.icon.setImageResource(icon);
		}
		
		public void setIdHolder(LayoutInflater layoutInflater, View view){
			view = layoutInflater.inflate(com.example.wifilocator_rebuild.R.layout.signal_form, null);
			this.txt_name = (TextView)view.findViewById(com.example.wifilocator_rebuild.R.id.name);
			this.txt_SSID = (TextView)view.findViewById(com.example.wifilocator_rebuild.R.id.ssid);
			this.txt_rate = (TextView)view.findViewById(com.example.wifilocator_rebuild.R.id.rate);
			this.icon 	  = (ImageView)view.findViewById(com.example.wifilocator_rebuild.R.id.wifi_level);
			view.setTag(this);
		} 
	}

}
