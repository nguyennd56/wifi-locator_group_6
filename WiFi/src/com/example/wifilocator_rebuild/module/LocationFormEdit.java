package com.example.wifilocator_rebuild.module;

import java.util.ArrayList;

import com.example.wifilocator_rebuild.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * this class for making listView of locations when Edit button is clicked.
 * generated from saved locations.
 * quite similar to class LocationForm but it has a delete button more.
 */

public class LocationFormEdit extends BaseAdapter{

	
	private static ArrayList<Location> itemDetailsrrayList;
	private LayoutInflater l_Inflater;
	private Integer img= Integer.valueOf(com.example.wifilocator_rebuild.R.drawable.fi_scan);
	
	//contructor
	public LocationFormEdit(Context context, ArrayList<Location> results) {
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		if(convertView==null){
			convertView = l_Inflater.inflate(com.example.wifilocator_rebuild.R.layout.location_form_edit, null);
			holder = new ViewHolder();
			holder.place_name= (TextView) convertView.findViewById(R.id.location_name_edit_shower);
			holder.location_icon=(ImageView) convertView.findViewById(R.id.location_edit_icon);
			holder.delete =(Button) convertView.findViewById(R.id.delete_location);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.place_name.setText(itemDetailsrrayList.get(position).getPlaceName());
		holder.location_icon.setImageResource(img);
		
		//--- set click on Click of delete button---
		holder.delete.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Location chosenItem;
				chosenItem= (Location) itemDetailsrrayList.get(position);
				chosenItem.getParent().remove(position);
				holder.delete.setText("Deleted");
				
			}
		});

		return convertView;
	}
	
	
	/*
	 * set of all things will be shown at Edit Locations listView.
	 */
	static class ViewHolder{
		TextView place_name;
		ImageView location_icon;
		Button delete;
	}
}
