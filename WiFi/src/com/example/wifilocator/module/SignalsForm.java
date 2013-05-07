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

public class SignalsForm extends BaseAdapter{

	
	private static ArrayList<Signal> itemDetailsrrayList;
	
	private Integer[] imgid = {                 //----array of wifi level image index----.
			com.example.wifilocator_rebuild.R.drawable.level_0_poor,
			com.example.wifilocator_rebuild.R.drawable.level_1_poor,
			com.example.wifilocator_rebuild.R.drawable.level_2_poor,
			com.example.wifilocator_rebuild.R.drawable.level_3_fair,
			com.example.wifilocator_rebuild.R.drawable.level_4_good,
			com.example.wifilocator_rebuild.R.drawable.level_5_excellent
			};
	private LayoutInflater l_Inflater;
	//constructor
	public SignalsForm(Context context, ArrayList<Signal> results) {
			itemDetailsrrayList = results;
			l_Inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return itemDetailsrrayList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return itemDetailsrrayList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if(convertView==null){
			convertView = l_Inflater.inflate(com.example.wifilocator_rebuild.R.layout.signal_form, null);
			holder = new ViewHolder();
			holder.txt_SSID = (TextView) convertView.findViewById(com.example.wifilocator_rebuild.R.id.ssid);
			holder.txt_name = (TextView) convertView.findViewById(com.example.wifilocator_rebuild.R.id.name);
			holder.img_Le = (ImageView) convertView.findViewById(com.example.wifilocator_rebuild.R.id.wifi_level);
			holder.txt_rate = (TextView) convertView.findViewById(com.example.wifilocator_rebuild.R.id.rate);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.txt_SSID.setText(itemDetailsrrayList.get(position).getSSID());
		holder.txt_name.setText(itemDetailsrrayList.get(position).getPlace());
		
		//------------ set wifi level image of signals basing on its strength.---------------
		int rankSignalStrength= ((itemDetailsrrayList.get(position).getStrength()+100)/10);
		switch(rankSignalStrength){
		case 0: {holder.img_Le.setImageResource(imgid[0]);holder.txt_rate.setText("level: poor");}break;
		case 1: {holder.img_Le.setImageResource(imgid[1]);holder.txt_rate.setText("level: poor");}break;
		case 2: {holder.img_Le.setImageResource(imgid[2]);holder.txt_rate.setText("level: poor");}break;
		case 3: {holder.img_Le.setImageResource(imgid[3]);holder.txt_rate.setText("level: fair");}break;
		case 4: {holder.img_Le.setImageResource(imgid[4]);holder.txt_rate.setText("level: good");}break;
		case 5: {holder.img_Le.setImageResource(imgid[5]);holder.txt_rate.setText("level: excellent");}break;
		default:holder.img_Le.setImageResource(imgid[0]);break;
		}
		return convertView;
	}
	
	/*
	 * set of all things will be shown at Signals listView.
	 */
	static class ViewHolder{
		TextView txt_name;
		TextView txt_SSID;
		TextView txt_rate;
		ImageView img_Le;
	}

}
