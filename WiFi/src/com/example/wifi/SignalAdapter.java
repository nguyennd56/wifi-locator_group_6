package com.example.wifi;
import java.util.ArrayList;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SignalAdapter extends BaseAdapter{
	
	//constant
	final int POOR_1_SIGNAL = 0;
	final int POOR_2_SIGNAL = 1;
	final int POOR_3_SIGNAL = 2;
	final int FAIR_SIGNAL = 3;
	final int GOOD_SIGNAL = 4;
	final int EXCELLENT_SIGNAL = 5;
	
	final int POOR_IMAGE_1 = 0;
	final int POOR_IMAGE_2 = 1;
	final int POOR_IMAGE_3 = 2;
	final int FAIR_IMAGE = 3;
	final int GOOD_IMAGE = 4;
	final int EXCELLENT_IMAGE = 5;

	//variables
	private static ArrayList<Signal> SignalitemDetailsrrayList;
	private Integer[] iconId = {
			com.example.wifi.R.drawable.level_0_poor,
			com.example.wifi.R.drawable.level_1_poor,
			com.example.wifi.R.drawable.level_2_poor,
			com.example.wifi.R.drawable.level_3_fair,
			com.example.wifi.R.drawable.level_4_good,
			com.example.wifi.R.drawable.level_5_excellent
			};
	private LayoutInflater layoutInflater;
	//constructor
	public SignalAdapter(){super();}	
	public SignalAdapter(Context context, ArrayList<Signal> results) {
		super();
		SignalitemDetailsrrayList = results;
		layoutInflater = LayoutInflater.from(context);
	}
	
	//implement abstract method	
	@Override
	public int getCount() {return SignalitemDetailsrrayList.size();}
	@Override
	public Object getItem(int arg0) {return SignalitemDetailsrrayList.get(arg0);}
	@Override
	public long getItemId(int arg0) {return arg0;}
	//make signal in list view
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		//set holder
		if(convertView==null){
			convertView = layoutInflater.inflate(com.example.wifi.R.layout.adapter_signal, null);
			viewHolder = new ViewHolder();
			viewHolder.txt_SSID = (TextView) convertView.findViewById(com.example.wifi.R.id.ssid_signal);
			viewHolder.txt_name = (TextView) convertView.findViewById(com.example.wifi.R.id.name_signal);
			viewHolder.icon = (ImageView) convertView.findViewById(com.example.wifi.R.id.wifi_level);
			viewHolder.txt_rate = (TextView) convertView.findViewById(com.example.wifi.R.id.rate_signal);
			convertView.setTag(viewHolder);
		}
		else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.txt_SSID.setText(SignalitemDetailsrrayList.get(position).getSSID());
		viewHolder.txt_name.setText(SignalitemDetailsrrayList.get(position).getName());
		//According to the rate of signal to set Icon for each signal
		switch(SignalitemDetailsrrayList.get(position).getStrength()){
		case POOR_1_SIGNAL: {
			viewHolder.icon.setImageResource(iconId[POOR_IMAGE_1]);
			viewHolder.txt_rate.setText("level: poor");}break;
		case POOR_2_SIGNAL: {
			viewHolder.icon.setImageResource(iconId[POOR_IMAGE_2]);
			viewHolder.txt_rate.setText("level: poor");}break;
		case POOR_3_SIGNAL: {
			viewHolder.icon.setImageResource(iconId[POOR_IMAGE_3]);
			viewHolder.txt_rate.setText("level: poor");}break;
		case FAIR_SIGNAL: {
			viewHolder.icon.setImageResource(iconId[FAIR_IMAGE]);
			viewHolder.txt_rate.setText("level: fair");}break;
		case GOOD_SIGNAL: {
			viewHolder.icon.setImageResource(iconId[GOOD_IMAGE]);
			viewHolder.txt_rate.setText("level: good");}break;
		case EXCELLENT_SIGNAL: {viewHolder.icon.setImageResource(iconId[EXCELLENT_IMAGE]);
			viewHolder.txt_rate.setText("level: excellent");}break;
		default:viewHolder.icon.setImageResource(iconId[POOR_IMAGE_1]);break;
		}
		
		return convertView;
	}
	
	static class ViewHolder{
		TextView txt_name;// name of signal
		TextView txt_SSID;// ssid name
		TextView txt_rate;// rate of signal
		ImageView icon;	  // icon accoding to rate
	}

}
