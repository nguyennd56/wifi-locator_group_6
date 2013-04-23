package com.example.wifi;
import java.util.ArrayList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SignalsForm extends BaseAdapter{

	private static ArrayList<Signal> itemDetailsrrayList;
	private Integer[] imgid = {
			com.example.wifi.R.drawable.level_0_poor,
			com.example.wifi.R.drawable.level_1_poor,
			com.example.wifi.R.drawable.level_2_poor,
			com.example.wifi.R.drawable.level_3_fair,
			com.example.wifi.R.drawable.level_4_good,
			com.example.wifi.R.drawable.level_5_excellent
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
			convertView = l_Inflater.inflate(com.example.wifi.R.layout.signal_form, null);
			holder = new ViewHolder();
			holder.txt_SSID = (TextView) convertView.findViewById(com.example.wifi.R.id.ssid);
			holder.txt_name = (TextView) convertView.findViewById(com.example.wifi.R.id.name);
			holder.img_Le = (ImageView) convertView.findViewById(com.example.wifi.R.id.wifi_level);
			holder.txt_rate = (TextView) convertView.findViewById(com.example.wifi.R.id.rate);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.txt_SSID.setText(itemDetailsrrayList.get(position).getSSID());
		holder.txt_name.setText(itemDetailsrrayList.get(position).getName());
		switch(itemDetailsrrayList.get(position).getStrength()){
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
	
	static class ViewHolder{
		TextView txt_name;
		TextView txt_SSID;
		TextView txt_rate;
		ImageView img_Le;
	}

}
