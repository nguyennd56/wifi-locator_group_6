package com.example.wifilocator.module;

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

public class LocationBaseAdapter extends BaseAdapter {

	private final static int DEFAULT_ID = Integer.valueOf(com.example.wifilocator_rebuild.R.drawable.fi_scan);
		
	//---variables----
	private static ArrayList<Location> locationList;
	private LayoutInflater layoutInflater;
	
	//---Constructor---
	public LocationBaseAdapter(Context context, ArrayList<Location> results) {
		locationList = results;
		layoutInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return locationList.size();
	}

	@Override
	public Object getItem(int argument) {
		// TODO Auto-generated method stub
		return locationList.get(argument);
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
		if (convertView == null) {
			holder = new ViewHolder();
			holder.setIdHolder(convertView, layoutInflater);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		//--set name and icon for view holder object---\
		holder.setLocationName(locationList.get(position).getLocationName());
		holder.setLocationIcon(DEFAULT_ID);
		return convertView;
	}
}

/*
 * set of all things will be shown at Locations listView.
 */

class ViewHolder {
	private TextView locationName;
	private ImageView locationIcon;
	
	public ViewHolder() {}
	
	public ViewHolder(String name, int id) {
		super();
		this.locationName.setText(name);
		this.locationIcon.setImageResource(id);
	}
	
	public void setLocationName(String name ){
		this.locationName.setText(name);
	}
	
	public void setLocationIcon(int id) {
		this.locationIcon.setImageResource(id);
	}
	
	public void setIdHolder(View view, LayoutInflater layoutInflater) {
		view = layoutInflater.inflate(com.example.wifilocator_rebuild.R.layout.location_form, null);
		this.locationName = (TextView) view.findViewById(R.id.location_name_shower);
		this.locationIcon = (ImageView) view.findViewById(R.id.location_icon);
		view.setTag(this);
	}
	
}