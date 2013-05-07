package com.example.wifilocator_rebuild.module;

import java.util.ArrayList;

import com.example.wifilocator_rebuild.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * this class for making listView of locations.
 * generated from saved locations.
 */

public class LocationForm extends BaseAdapter{

	
	//---variable----
	private static ArrayList<Location> itemDetailsrrayList;
	private LayoutInflater l_Inflater;
	private Integer img= Integer.valueOf(com.example.wifilocator_rebuild.R.drawable.fi_scan);
	
	//---Constructor---
	public LocationForm(Context context, ArrayList<Location> results) {
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
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		//--create ViewHolder object--
		if(convertView==null){
			convertView = l_Inflater.inflate(com.example.wifilocator_rebuild.R.layout.location_form, null);
			holder = new ViewHolder();
			holder.place_name= (TextView) convertView.findViewById(R.id.location_name_shower);
			holder.location_icon=(ImageView) convertView.findViewById(R.id.location_icon);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		//--set name and icon for view holder object---
		holder.place_name.setText(itemDetailsrrayList.get(position).getPlaceName());
		holder.location_icon.setImageResource(img);
		return convertView;
	}
	
	
	/*
	 * set of all things will be shown at Locations listView.
	 */
	static class ViewHolder{
		TextView place_name;
		ImageView location_icon;
	}
}
